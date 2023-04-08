package bridge;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/4/8
 */
public class MediumCoffee extends Coffee {
    public MediumCoffee(ISugarDegree sugarDegree) {
        super(sugarDegree);
    }

    @Override
    public void orderCoffee(int count) {
        sugarDegree.chooseSugarDegree();
        System.out.println(String.format("中杯杯咖啡%d杯", count));
    }
}
