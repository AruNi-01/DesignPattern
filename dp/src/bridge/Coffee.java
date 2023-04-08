package bridge;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/4/8
 */
public abstract class Coffee {

    // 通过组合的方式将 Coffee 的糖度添加进来
    protected ISugarDegree sugarDegree;

    // 通过依赖注入将 ICoffeeAdditives 注入进来
    public Coffee(ISugarDegree sugarDegree) {
        this.sugarDegree = sugarDegree;
    }

    public abstract void orderCoffee(int count);

}
