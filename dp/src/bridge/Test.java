package bridge;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/4/8
 */
public class Test {
    public static void main(String[] args) {
        Coffee largeWithSugarDegreeNormal = new LargeCoffee(new SugarDegreeNormal());
        largeWithSugarDegreeNormal.orderCoffee(3);
    }
}
