package Auth.auth;

import Auth.api.ApiRequest;

/**
 * @desc: 接口鉴权功能接口，供外部使用
 * @author: AruNi_Lu
 * @date: 2023/3/22
 */
public interface ApiAuthenticator {

    void auth(String url);

    void auth(ApiRequest apiRequest);

}
