package business.service.user;

import business.dao.login.UserDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;
import persistence.dto.DTO;
import persistence.dto.UserAccount;

import java.util.List;

public class SearchUserBean {

    UserDAO user;

    public SearchUserBean() {
        user = new UserDAO();
    }

    public List<DTO> listUsers() throws UnreachableDataBaseException, UserNotFoundException {
        return user.listAllUsers();
    }

    public UserAccount findUser(String email) throws UnreachableDataBaseException, UserNotFoundException {
        return user.findUserByEmail(email);
    }

}
