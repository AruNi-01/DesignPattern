package Auth.storage;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/3/22
 */
public class MySQLCredentialStorage implements CredentialStorage {

    // 模拟数据库
    Map<String, String> mySQL = new HashMap<>();
    public MySQLCredentialStorage() {
        mySQL.put("id1", "pwd1");
        mySQL.put("id2", "pwd2");
        mySQL.put("id3", "pwd3");
    }

    @Override
    public String getPasswordByAppID(String appID) {
        return mySQL.get(appID);
    }
}
