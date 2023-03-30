package singleton;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/3/30
 */
public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
        System.out.println(Thread.currentThread().getName() +  "调用了无参构造器");
    }

    public static HungrySingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(HungrySingleton::getInstance).start();
        }
    }

}
