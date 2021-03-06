package business.service.user;

import business.dao.login.UserDAO;
import business.exceptions.MailNotConfiguredException;
import business.exceptions.login.DuplicateUserException;
import business.exceptions.login.IncorrectLoginInformationException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;
import business.service.util.EJBUtility;
import business.service.util.Regex;
import business.service.util.SendMail;
import persistence.dto.UserAccount;
import persistence.exceptions.UpdateEntityException;

public class RegisterUserBean {

    private UserDAO userDAO;

    private final String emailPattern = "([A-Za-z0-9])([_.-]?[A-Za-z0-9])*@([A-Za-z0-9]+)(\\.[A-Za-z0-9]+)+";
    private final Regex emailChecker = new Regex(emailPattern);

    public RegisterUserBean() {
        userDAO = new UserDAO();
    }

    public void addUser(String email, String password, String name) throws IncorrectLoginInformationException, DuplicateUserException {
        try {
            if (!emailChecker.check(email)) throw new IncorrectLoginInformationException("Email inválido");
            UserAccount check;
            try {
                check = userDAO.findUserByEmail(email);
                if (check != null) throw new DuplicateUserException();
            } catch (UserNotFoundException e) {
                userDAO.addUser(email, name, EJBUtility.getHash(password, "MD5"), null);
            }
        } catch (UnreachableDataBaseException e) {
            e.printStackTrace();
        }
    }

    public String recuperarSenha(String email) throws UnreachableDataBaseException, UserNotFoundException, IllegalArgumentException, UpdateEntityException, MailNotConfiguredException {
        UserAccount user = userDAO.findUserByEmail(email);
        String newPassword = EJBUtility.genRandomString(6);
        SendMail mail = new SendMail();
        user.setPassword(EJBUtility.getHash(newPassword, "MD5"));
        userDAO.updateUser(user);
        try {
            mail.send("Não Responder", user.getEmail(), "Sua nova senha do Grão Pará!", "Aqui está a sua nova senha do Grão Pará.\n\n" + newPassword);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return newPassword;
    }

    public void atualizarUsuario(UserAccount user) throws UnreachableDataBaseException, UserNotFoundException, IllegalArgumentException, UpdateEntityException {
        userDAO.updateUser(user);
    }

}
