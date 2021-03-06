package business.dao.login;

import business.exceptions.login.ProfileNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import persistence.dto.Profile;

public class ProfileDAOTest {

    public static boolean checkPermission(String[] userPermission, String requiredPermission) {
        for (String permission : userPermission)
            if (permission.equals(requiredPermission)) return true;
        return false;
    }

    public static void main(String args[]) throws UnreachableDataBaseException, ProfileNotFoundException {
        ProfileDAO dao = new ProfileDAO();
        dao.createProfile("teste", new String[]{"default", "user", "admin", "master", "legend"});
        Profile l = dao.findProfileByName("teste");
        dao.removeProfile("teste");
        for (String s : l.getPermissions()) {
            System.out.println(s);
        }
        System.out.println(l.getProfile());


    }
}
