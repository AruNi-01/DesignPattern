package singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/3/30
 */
public class LazySingletonDCL {

    /**
     * new LazySingletonDCL() 分为三步：
     * 1. 在堆中分配空间
     * 2. 创建实例
     * 3. 将堆中的地址值赋给 instance
     * 所以需要使用 volatile 来避免上面三个步骤的重排序。
     * 否则如果线程 A 的执行顺序为 1->3->2，这样其他线程就可能获取到一个 null 实例。
     */
    private static volatile LazySingletonDCL instance;

    private LazySingletonDCL() {
        System.out.println(Thread.currentThread().getName() +  "调用了无参构造器");
    }

    public static LazySingletonDCL getInstance() {
        if (instance == null) {
            synchronized (LazySingletonDCL.class) { // 类级别的锁
                if (instance == null) {
                    instance = new LazySingletonDCL();
                }
            }
        }
        return instance;
    }

    // 通过反射暴力获取私有构造器，从而创建新实例，破坏单例
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        LazySingletonDCL instance1 = LazySingletonDCL.getInstance();
        LazySingletonDCL instance2 = LazySingletonDCL.getInstance();
        // 获取到无参构造器
        Constructor<LazySingletonDCL> declaredConstructor = LazySingletonDCL.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);    // 暴力破解，无视私有构造器
        // 通过反射创建的对象
        LazySingletonDCL reflectInstance = declaredConstructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2);
        System.out.println(reflectInstance);
    }

}
