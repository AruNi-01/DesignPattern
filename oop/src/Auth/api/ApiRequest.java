package Auth.api;

import Auth.utils.UrlUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc: 与 URL 相关的类，使用 ApiRequest 更具通用性
 * @author: AruNi_Lu
 * @date: 2023/3/21
 */
public class ApiRequest {

    private String baseUrl;
    private String appID;
    private String token;
    private long timestamp;

    /**
     * 从 fullUrl 中解析出各个属性来填充 ApiRequest
     * @param fullUrl 完整的请求路径
     * @return ApiRequest
     */
    public static ApiRequest createFromFullUrl(String fullUrl) {
        // 使用 UrlUtils 工具类，解析 URL
        UrlUtils.UrlEntity urlEntity = UrlUtils.parseUrl(fullUrl);
        String baseUrl = urlEntity.getBaseUrl();
        Map<String, String> encryptionParams = urlEntity.getEncryptionParams();
        String appID = encryptionParams.get("appID");
        String token = encryptionParams.get("token");
        long timestamp = Long.parseLong(encryptionParams.get("timestamp"));
        return new ApiRequest(baseUrl, appID, token, timestamp);
    }

    public ApiRequest(String baseUrl, String appId, String token, long timestamp) {
        this.baseUrl = baseUrl;
        this.appID = appId;
        this.token = token;
        this.timestamp = timestamp;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getAppId() {
        return appID;
    }

    public String getToken() {
        return token;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
