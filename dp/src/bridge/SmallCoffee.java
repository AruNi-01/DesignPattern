package bridge;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/4/8
 */
public class SmallCoffee extends Coffee {
    public SmallCoffee(ISugarDegree sugarDegree) {
        super(sugarDegree);
    }

    @Override
    public void orderCoffee(int count) {
        sugarDegree.chooseSugarDegree();
        System.out.println(String.format("小杯咖啡%d杯", count));
    }
}
