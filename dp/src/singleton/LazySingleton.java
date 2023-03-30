package singleton;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/3/30
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println(Thread.currentThread().getName() +  "调用了无参构造器");
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    // 测试
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(LazySingleton::getInstance).start();
        }
    }
}
