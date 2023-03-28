package Auth.auth;

import Auth.api.ApiRequest;
import Auth.storage.CredentialStorage;
import Auth.storage.MySQLCredentialStorage;
import Auth.token.AuthToken;

import javax.swing.*;

/**
 * @desc: 接口鉴权功能入口类
 * @author: AruNi_Lu
 * @date: 2023/3/22
 */
public class DefaultApiAuthenticator implements ApiAuthenticator {

    private CredentialStorage credentialStorage;

    /**
     * 默认使用 MySQLCredentialStorage
     */
    public DefaultApiAuthenticator() {
        this(new MySQLCredentialStorage());
    }

    public DefaultApiAuthenticator(CredentialStorage credentialStorage) {
        this.credentialStorage = credentialStorage;
    }

    @Override
    public void auth(String url) {
        ApiRequest apiRequest = ApiRequest.createFromFullUrl(url);
        auth(apiRequest);
    }

    @Override
    public void auth(ApiRequest apiRequest) {
        String baseUrl = apiRequest.getBaseUrl();
        String appID = apiRequest.getAppId();
        String token = apiRequest.getToken();
        long timestamp = apiRequest.getTimestamp();

        AuthToken clientAuthToken = new AuthToken(timestamp, token);
        if (clientAuthToken.isExpired()) {
            throw new RuntimeException("Token is expired.");
        }

        String password = credentialStorage.getPasswordByAppID(appID);
        AuthToken serverAuthToken = AuthToken.creatAuthToken(baseUrl, appID, password, timestamp);
        if (!serverAuthToken.match(clientAuthToken)) {
            throw new RuntimeException("Token verification failed.");
        }

        System.out.println("√ 您拥有该接口的权限，正在处理您的接口请求...");
    }
}
