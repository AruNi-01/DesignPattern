package singleton.id_generator_demo;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/3/30
 */
public class OrderIdGenerator implements IdGenerator {
    @Override
    public long getId() {
        // OrderId 生成策略...
        System.out.println("OrderId 生成策略...");
        return 2L;
    }
}
