package builder;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/4/4
 */
public class Usage {
    public static void main(String[] args) {
        // 抛出 IllegalArgumentException，因为 minIdle > maxIdle
        ResourcePoolConfig config = new ResourcePoolConfig.Builder()
                .setName("dbconnectionpool")
                .setMaxTotal(16)
                .setMaxIdle(10)
                .setMinIdle(12)
                .build();
    }
}
