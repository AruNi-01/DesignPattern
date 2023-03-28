package Auth.storage;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/3/22
 */
public interface CredentialStorage {

    String getPasswordByAppID(String appID);

}
