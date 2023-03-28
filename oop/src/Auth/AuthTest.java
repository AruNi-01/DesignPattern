package Auth;

import Auth.auth.ApiAuthenticator;
import Auth.auth.DefaultApiAuthenticator;
import Auth.token.AuthToken;

/**
 * @desc: 测试类
 * @author: AruNi_Lu
 * @date: 2023/3/22
 */
public class AuthTest {
    public static void main(String[] args) {
        ApiAuthenticator apiAuthenticator = new DefaultApiAuthenticator();
        // https://api.mycloud.com/v1/user?id=1&AppID=cvuAS23lcuvekb5&Timestamp=1489344829&token=sJ5yKBZz8krb
        String baseUrl = "https://api.mycloud.com/v1/user?id=1";
        String appID = "id1";
        String password = "pwd1";
        long timestamp = System.currentTimeMillis();
        String token = AuthToken.generateToken(baseUrl, appID, password, timestamp);
        String finalRequestUrl = baseUrl + "&appID=" + appID + "&timestamp=" + timestamp + "&token=" + token;
        System.out.println("finalRequestUrl: " + finalRequestUrl);
        apiAuthenticator.auth(finalRequestUrl);

        /*
        输出：
        finalRequestUrl: https://api.mycloud.com/v1/user?id=1&appID=id1&timestamp=1679496947777&token=6ULXSkUpv/x7uBj4wvs3rdSxbWjx5qUWvDut56DO9zIeq
        √ 您拥有该接口的权限，正在处理您的接口请求...
         */
    }
}
