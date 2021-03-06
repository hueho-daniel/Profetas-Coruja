package business.service.user;

import business.dao.login.ProfileDAO;
import business.dao.login.UserDAO;
import business.exceptions.login.*;
import business.service.util.EJBUtility;
import business.service.util.Regex;
import persistence.dto.DTO;
import persistence.dto.Profile;
import persistence.dto.UserAccount;
import persistence.exceptions.UpdateEntityException;

import java.util.List;

public class AdminBean {

    private UserDAO userDAO;
    private ProfileDAO profileDAO;

    private final String profileNamePattern = "([a-z0-9]){3,}";
    private final Regex profileNameChecker = new Regex(profileNamePattern);

    private final String emailPattern = "([A-Za-z0-9])([_.-]?[A-Za-z0-9])*@([A-Za-z0-9]+)(\\.[A-Za-z0-9]+)+";
    private final Regex emailChecker = new Regex(emailPattern);

    public AdminBean() {
        userDAO = new UserDAO();
        profileDAO = new ProfileDAO();
    }

    public void addProfile(String profile, String[] permissions)
            throws UnreachableDataBaseException, IncorrectProfileInformationException {
        try {
            if (!profileNameChecker.check(profile)) throw new IncorrectProfileInformationException("Nome inválido");
            Profile check = profileDAO.findProfileByName(profile);
            if (check.getProfile().equals(profile))
                throw new IncorrectProfileInformationException("Nome de profile já existe.");
        } catch (ProfileNotFoundException e) {
            profileDAO.createProfile(profile, permissions);
        } catch (UnreachableDataBaseException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void addUser(String email, String name, String password, String profile) throws UnreachableDataBaseException, IncorrectLoginInformationException, DuplicateUserException {
        try {
            if (!emailChecker.check(email))
                throw new IncorrectLoginInformationException("Email inválido");

            UserAccount check;

            try {
                check = userDAO.findUserByEmail(email);

                if (check != null)
                    throw new DuplicateUserException();

            } catch (UserNotFoundException e) {
                Profile p_dto = null;
                try {
                    p_dto = profileDAO.findProfileByName(profile);
                } catch (ProfileNotFoundException f) {
                    e.printStackTrace();
                }
                userDAO.addUser(email, name, EJBUtility.getHash(password), p_dto);
            }
        } catch (UnreachableDataBaseException e) {
            e.printStackTrace();
        }
    }

    public void changeUserProfile(String email, String novo_perfil)
            throws IncorrectProfileInformationException, UnreachableDataBaseException, UserNotFoundException,
            ProfileNotFoundException, IllegalArgumentException, UpdateEntityException {
        userDAO.changeUserProfile(email, profileDAO.findProfileByName(novo_perfil));
    }

    public void deletarUsuario(String email) throws UnreachableDataBaseException, UserNotFoundException {
        userDAO.removeUser(email);
    }

    public List<DTO> getAllAvailableProfiles() throws UnreachableDataBaseException, ProfileNotFoundException {
        return profileDAO.getAllProfiles();
    }

}
