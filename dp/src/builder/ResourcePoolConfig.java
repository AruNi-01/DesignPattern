package builder;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/4/3
 */
public class ResourcePoolConfig {

    private String name;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;

    private ResourcePoolConfig(Builder builder) {
        this.name = builder.name;
        this.maxTotal = builder.maxIdle;
        this.maxIdle = builder.maxIdle;
        this.minIdle = builder.minIdle;
    }

    /**
     * ResourcePoolConfig 的建造者，当然也可以将 Builder 类
     * 设计成独立的非内部类 ResourcePoolConfigBuilder。
     */
    public static class Builder {
        private static final int DEFAULT_MAX_TOTAL = 8;
        private static final int DEFAULT_MAX_IDLE = 8;
        private static final int DEFAULT_MIN_IDLE = 0;

        private String name;
        private int maxTotal = DEFAULT_MAX_TOTAL;
        private int maxIdle = DEFAULT_MAX_IDLE;
        private int minIdle = DEFAULT_MIN_IDLE;

        /**
         * 真正创建对象的方法。校验逻辑放到此方法来做，包括必填项校验、依赖关系校验等
         */
        public ResourcePoolConfig build() {
            if (name.isBlank()) {
                throw new IllegalArgumentException("name should not be empty.");
            }
            if (maxIdle > maxTotal) {
                throw new IllegalArgumentException("maxIdle should less equals maxTotal.");
            }
            if (minIdle > maxTotal || minIdle > maxIdle) {
                throw new IllegalArgumentException("minIdle should less equals maxIdle and maxTotal.");
            }
            return new ResourcePoolConfig(this);
        }

        // 以下是 Builder 对外提供的 setter 方法
        public Builder setName(String name) {
            if (name.isBlank()) {
                throw new IllegalArgumentException("name should not be empty.");
            }
            this.name = name;
            return this;
        }

        public Builder setMaxTotal(int maxTotal) {
            if (maxTotal <= 0) {
                throw new IllegalArgumentException("maxTotal should be positive.");
            }
            this.maxTotal = maxTotal;
            return this;
        }

        public Builder setMaxIdle(int maxIdle) {
            if (maxIdle < 0) {
                throw new IllegalArgumentException("maxIdle should be negative.");
            }
            this.maxIdle = maxIdle;
            return this;
        }

        public Builder setMinIdle(int minIdle) {
            if (minIdle < 0) {
                throw new IllegalArgumentException("minIdle should be negative.");
            }
            this.minIdle = minIdle;
            return this;
        }
    }
}
