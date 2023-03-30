package singleton.id_generator_demo;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/3/30
 */
public class UserIdGenerator implements IdGenerator {
    @Override
    public long getId() {
        // UserId 生成策略...
        System.out.println("UserId 生成策略...");
        return 1L;
    }
}
