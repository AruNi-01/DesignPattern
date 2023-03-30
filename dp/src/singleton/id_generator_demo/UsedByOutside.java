package singleton.id_generator_demo;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/3/30
 */
public class UsedByOutside {
    public static void main(String[] args) {
        User user = new User();
        // 外部使用时，通过传入不同的 ID 生成器来走不同的方法
        user.create(new UserIdGenerator(), "userName", "userType");

        Order order = new Order();
        order.create(new OrderIdGenerator(), "orderName", "orderType");

        /* 输出：
           UserId 生成策略...
           OrderId 生成策略...

           Process finished with exit code 0
         */
    }
}
