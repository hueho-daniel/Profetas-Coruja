package webview.servlet.account;

import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;
import business.service.user.RegisterUserBean;
import business.service.user.SearchUserBean;
import business.service.util.EJBUtility;
import persistence.dto.UserAccount;
import persistence.exceptions.UpdateEntityException;
import webview.util.AlertsUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AccountRecoveryServlet
 */
@WebServlet("/doPasswordRecovery")
public class AccountRecoveryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountRecoveryServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SearchUserBean busca = new SearchUserBean();
        RegisterUserBean cadastro = new RegisterUserBean();
        String newPassword = null;
        String email = request.getParameter("email");

        try {
            UserAccount user = busca.findUser(email);
            newPassword = EJBUtility.genRandomString(6);
            user.setPassword(EJBUtility.getHash(newPassword, "MD5"));
            cadastro.atualizarUsuario(user);

            AlertsUtility.alertAndRedirectHistory(response, "Nova senha gerado para " + email + ": " + newPassword);

        } catch (UnreachableDataBaseException e) {

            AlertsUtility.alertAndRedirectHistory(response, "Problema ao se conectar ao banco de dados.");
            e.printStackTrace();
        } catch (UserNotFoundException e) {

            AlertsUtility.alertAndRedirectHistory(response, "Usuário não encontrado.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (UpdateEntityException e) {
            e.printStackTrace();
        }
    }

}