package business.dao.login;

import business.exceptions.login.ProfileNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.Profile;
import persistence.util.DataAccessLayerException;

import java.util.List;

public class ProfileDAO {

    private static Profile defaultProfile = null;

    private static final String defaultProfileName = "default";

    private PersistenceAccess manager;

    public ProfileDAO() {
        manager = new PersistenceAccess();
        initDefaultProfile();
    }

    public static Profile getDefaultProfile() {
        if (defaultProfile == null) initDefaultProfile();
        return defaultProfile;
    }

    private static void initDefaultProfile() {
        if (defaultProfile == null) {
            try {
                defaultProfile = findDefaultProfile();
            } catch (UnreachableDataBaseException e) {
                e.printStackTrace();
            } catch (ProfileNotFoundException e) {

            }
        }
    }

    private static Profile findDefaultProfile() throws UnreachableDataBaseException, ProfileNotFoundException {
        PersistenceAccess manager;
        manager = new PersistenceAccess();
        List<DTO> resultSet = null;
        try {
            resultSet = manager.findEntity("from ProfileMO where profile = '" + defaultProfileName + "'");
            if (resultSet == null) {
                throw new ProfileNotFoundException();
            } else return (Profile) resultSet.get(0);
        } catch (DataAccessLayerException e) {
            e.printStackTrace();
            throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
        }
    }

    public synchronized void createProfile(String profile) throws UnreachableDataBaseException {
        Profile newProfile = new Profile(profile, new String[]{"default"});
        try {
            manager.saveEntity(newProfile);
        } catch (DataAccessLayerException e) {
            e.printStackTrace();
            throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
        }
    }

    public synchronized void createProfile(String profile, String[] permissions) throws UnreachableDataBaseException {
        Profile newProfile = new Profile(profile, permissions);
        try {
            manager.saveEntity(newProfile);
        } catch (DataAccessLayerException e) {
            e.printStackTrace();
            throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
        }
    }

    public synchronized void removeProfile(String profile) throws UnreachableDataBaseException, ProfileNotFoundException {
        List<DTO> resultSet = null;
        try {
            resultSet = manager.findEntity("from ProfileMO where profile = '" + profile + "'");
            if (resultSet == null) {
                throw new ProfileNotFoundException();
            } else {
                for (DTO check : resultSet) {
                    if (((Profile) check).getProfile().equals(profile)) manager.deleteEntity(check);
                }
            }
        } catch (DataAccessLayerException e) {
            e.printStackTrace();
            throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
        }
    }

    public Profile findProfileByName(String profile) throws UnreachableDataBaseException, ProfileNotFoundException {
        List<DTO> resultSet = null;
        try {
            resultSet = manager.findEntity("from ProfileMO where profile = '" + profile + "'");
            if (resultSet == null) {
                throw new ProfileNotFoundException();
            } else return (Profile) resultSet.get(0);
        } catch (DataAccessLayerException e) {
            e.printStackTrace();
            throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
        }
    }

    public List<DTO> getAllProfiles() throws UnreachableDataBaseException, ProfileNotFoundException {
        List<DTO> resultSet = null;
        try {
            resultSet = manager.findEntity("from ProfileMO order by profile");
            if (resultSet == null) throw new ProfileNotFoundException("Nenhum perfil encontrado");
            else return resultSet;
        } catch (DataAccessLayerException e) {
            e.printStackTrace();
            throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
        }
    }

}
