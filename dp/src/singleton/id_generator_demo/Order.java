package singleton.id_generator_demo;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/3/30
 */
public class Order {
    // 通过依赖注入，将 IdGenerator 传进来
    public void create(IdGenerator idGenerator, String... otherArgs) {
        // ...
        long id = idGenerator.getId();
        // ...
    }
}
