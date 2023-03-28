package Auth.utils;

import Auth.constant.ApiRequestConstant;

import java.util.*;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/3/21
 */
public class UrlUtils {

    /**
     * 存储 URL 相关信息，如 baseURL、encryptionParams 专门存储 baseURL 后面的加密参数（不包括请求本身的参数）
     */
    public static class UrlEntity {
        private String baseUrl;
        private Map<String, String> encryptionParams;

        public String getBaseUrl() {
            return baseUrl;
        }

        public Map<String, String> getEncryptionParams() {
            return encryptionParams;
        }
    }

    /**
     * 解析 fullUrl，得到 UrlEntity（baseURL、token、appID、时间戳等）
     * @param fullUrl 完全的 url，例如：https://api.mycloud.com/v1/user?id=1&appID=xx&timestamp=123&token=xx
     * @return UrlEntity
     */
    public static UrlEntity parseUrl(String fullUrl) {
        UrlEntity urlEntity = new UrlEntity();
        fullUrl = fullUrl.trim();
        if ("".equals(fullUrl)) {
            return urlEntity;
        }
        String[] urlParts = fullUrl.split("\\?");
        urlEntity.baseUrl = urlParts[0];
        if (urlParts.length == 1) {     // 无参数
            return urlEntity;
        }

        String[] params = urlParts[1].split("&");
        // 使用有序的 LinkedHashMap。因为 encryptionParams 只有后三个，即 appID、timestamp、token。
        // baseUrl 请求自带的参数在前，encryptionParams 在后
        Map<String, String> paramsMap = new LinkedHashMap<>();
        for (String param : params) {
            String[] kv = param.split("=");
            paramsMap.put(kv[0], kv[1]);
        }

        int sz = paramsMap.size();
        // baseParams 有值，需要拼接到 baseUrl 上
        if (sz > ApiRequestConstant.ENCRYPTION_PARAMS_COUNTS) {
            Set<Map.Entry<String, String>> entries = paramsMap.entrySet();
            StringBuilder baseParams = new StringBuilder();
            int i = 0;
            for (Map.Entry<String, String> entry : entries) {
                baseParams.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
                if (++i >= sz - ApiRequestConstant.ENCRYPTION_PARAMS_COUNTS) {
                    baseParams.deleteCharAt(baseParams.length() - 1);
                    break;
                }
            }
            urlEntity.baseUrl = urlEntity.baseUrl + "?" + baseParams;

            // 删除 baseUrl 中的请求参数
            Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet().iterator();
            for (int size = paramsMap.size();
                 size > ApiRequestConstant.ENCRYPTION_PARAMS_COUNTS
                         && iterator.hasNext(); size--) {
                iterator.next();
                iterator.remove();
            }
        }
        urlEntity.encryptionParams = paramsMap;
        return urlEntity;
    }


    // 测试
    public static void main(String[] args) {
        UrlEntity entity = parseUrl("https://api.mycloud.com/v1/user?id=1&appID=cvuAS23lcuvekb5&timestamp=1489344829&token=sJ5yKBZz8krb");
        Map<String, String> encryptionParams = entity.getEncryptionParams();
        System.out.println("baseUrl: " + entity.getBaseUrl());
        System.out.println("encryptionParams: ");
        for (Map.Entry<String, String> entry : encryptionParams.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        /** 输出：
         * baseUrl: https://api.mycloud.com/v1/user?id=1
         * encryptionParams:
         * appID: cvuAS23lcuvekb5
         * timestamp: 1489344829
         * token: sJ5yKBZz8krb
         */
    }

}
