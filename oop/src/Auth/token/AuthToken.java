package Auth.token;

import Auth.api.ApiRequest;
import Auth.utils.SHAUtils;

/**
 * @desc: token 有关，负责 token 的生成、解析
 * @author: AruNi_Lu
 * @date: 2023/3/21
 */
public class AuthToken {

    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 5 * 1000;

    private long createTime;
    private String token;
    private long expiredTimeInterval;

    public AuthToken(long createTime, String token, long expiredTimeInterval) {
        this.createTime = createTime;
        this.token = token;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    public AuthToken(long createTime, String token) {
        this(createTime, token, DEFAULT_EXPIRED_TIME_INTERVAL);
    }

    /**
     * 使用默认的失效时间间隔 DEFAULT_EXPIRED_TIME_INTERVAL 生成 AuthToken
     * @param baseUrl
     * @param appId
     * @param password
     * @param timestamp 生成 token 时的时间戳
     * @return
     */
    public static AuthToken creatAuthToken(String baseUrl, String appId, String password, long timestamp) {
        String token = generateToken(baseUrl, appId, password, timestamp);
        return new AuthToken(timestamp, token);
    }

    /**
     * 使用自定义的失效时间间隔 expiredTimeInterval 生成 AuthToken
     * @param baseUrl
     * @param appId
     * @param password
     * @param timestamp 生成 token 时的时间戳
     * @param expiredTimeInterval 自定义的失效时间间隔
     * @return
     */
    public static AuthToken creatAuthToken(String baseUrl, String appId, String password, long timestamp, long expiredTimeInterval) {
        String token = generateToken(baseUrl, appId, password, timestamp);
        return new AuthToken(timestamp, token, expiredTimeInterval);
    }

    /**
     * 加密生成 token
     */
    public static String generateToken(String baseUrl, String appId, String password, long timestamp) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl)
                .append("&").append("appId").append("=").append(appId)
                .append("&").append("password").append("=").append(password)
                .append("&").append("timestamp").append("=").append(timestamp);
        return SHAUtils.SHA(sb.toString());
    }

    /**
     * 判断是否超过失效时间间隔
     */
    public boolean isExpired() {
        return (System.currentTimeMillis() - this.createTime) > this.expiredTimeInterval;
    }

    /**
     * 判断两个 token 是否是否匹配
     */
    public boolean match(AuthToken authToken) {
        return this.token.equals(authToken.token);
    }

    public long getCreateTime() {
        return createTime;
    }

    public String getToken() {
        return token;
    }

    public long getExpiredTimeInterval() {
        return expiredTimeInterval;
    }
}
