package business.service.user;

import business.dao.login.UserDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;
import business.service.util.EJBUtility;
import persistence.dto.UserAccount;
import persistence.exceptions.UpdateEntityException;

import javax.servlet.http.HttpSession;
import java.util.GregorianCalendar;

/**
 * Classe de autenticação de usuário.
 */
public class AuthBean {

    private UserDAO loginDAO;
    /**
     * Flag para habilitar hash de senha
     */
    public static final boolean HashedPwd = true;
    /**
     * Flag para desabilitar hash de senha
     */
    public static final boolean NonHashedPwd = false;

    public AuthBean() {
        loginDAO = new UserDAO();
    }

    /**
     * Autentica um usuário
     *
     * @param email    - Email do usuário.
     * @param password - Senha do usuário.
     * @param session  - Sessão do browser.
     * @param hashed   - Flag indicando se o hash está ativado ou não.
     * @throws UnreachableDataBaseException
     * @throws UserNotFoundException
     * @throws UpdateEntityException
     * @throws IllegalArgumentException
     */
    public void authenticate(String email, String password, HttpSession session, boolean hashed) throws UnreachableDataBaseException, UserNotFoundException, IllegalArgumentException, UpdateEntityException {

        UserAccount check = loginDAO.findUserByEmail(email);    // Busca o usuário no banco de dados.
        if (check == null) {    // Se nenhum usuário for encontrado, setar na sessão que o login não foi sucedido.
            session.setAttribute("userLoginSucefull", false);
            return;
        }

        String hashedPassword = null;
        if (hashed == HashedPwd)
            hashedPassword = EJBUtility.getHash(password, "MD5");    // Hashea a senha fornecido para a comparação.
        else
            hashedPassword = password;

        if (check.getPassword().equals(hashedPassword)) {    // Verifica se a senha fornecido é a mesma da cadastrada.
            // Gera um token.
            String sessionToken = EJBUtility.genRandomToken("LOG");
            // Grava o token no banco de dados.
            GregorianCalendar tokenDate = new GregorianCalendar();
            check.setGeneratedToken(sessionToken);
            check.setTokenDate(tokenDate);
            loginDAO.updateUser(check);
            // Define os atributos de sessão.
            session.setAttribute("userMail", check.getEmail());
            session.setAttribute("userName", check.getName());
            session.setAttribute("userPermissions", check.getProfile().getPermissions());
            session.setAttribute("userAccessToken", sessionToken);
            session.setAttribute("userLoginSuccessfull", true);
        } else throw new UserNotFoundException("Senha errada");
    }

    /**
     * Verifica se o usuário está logado.
     *
     * @param session
     * @return
     */
    public boolean isLoggedIn(HttpSession session) {
        return (session.getAttribute("userLoginSuccessfull") != null && (Boolean) session.getAttribute("userLoginSuccessfull"));
    }

    /**
     * Verifica se o token da sessão é válido.
     *
     * @param session - Sessão do usuário.
     * @return <b>true</b> se o token da sessão é válido.<br><b>false</b> caso contrário.
     * @throws UnreachableDataBaseException
     */
    public boolean validateToken(HttpSession session) throws UnreachableDataBaseException {
        UserAccount user;
        // Pega os atributos da sessão.
        String userMail = (String) session.getAttribute("userMail");
        String userToken = (String) session.getAttribute("userAccessToken");
        if (userMail == null || userToken == null) return false;
        try {
            user = loginDAO.findUserByEmail(userMail); // Busca o usuário no banco de dados
            if (!user.getGeneratedToken().equals(userToken)) { // Comparação dos tokens
                session.invalidate();    // Força o logout.
                return false; // Se os tokens divergirem, negar a ação.
            }
        } catch (UserNotFoundException e) {
            return false;
        }
        return false;
    }

    /**
     * Verifica se o usuário tem permissão para realizar uma operação.
     *
     * @param requiredPermission - Flag de permissão requerido para a operação.
     * @param session            - Sessão do usuário.
     * @param tokenRequired      - Flag indicando se a operação requer autenticação por token.
     * @throws UserNotFoundException
     * @throws UnreachableDataBaseException
     * @return    <b>true</b> se o usuário está liberado para realizar a operação.<br><b>false</b> caso contrário.
     */
    public boolean allowedOperation(String requiredPermission, HttpSession session, boolean tokenRequired) throws UnreachableDataBaseException {
        if (session.getAttribute("userLoginSuccessfull") != null && !(Boolean) session.getAttribute("userLoginSuccessfull"))
            return false;    // Verifica se o usuário está logado
        if (tokenRequired) {    // Verifica se a ação requer autenticação por token
            if (!validateToken(session)) return false;
        }

        for (String s : (String[]) session.getAttribute("userPermissions"))
            if (s.equals(requiredPermission))
                return true;    // Verifica se o usuário tem permissão para executar uma derteminada ação.
        return false;
    }
}
