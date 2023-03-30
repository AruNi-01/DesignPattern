package singleton;

/**
 * @desc: 静态内部类实现懒加载单例
 * @author: AruNi_Lu
 * @date: 2023/3/30
 */
public class LazySingletonInnerStatic {

    private LazySingletonInnerStatic() { }

    /**
     * 一个静态内部类，在调用 getInstance() 是，才会加载此类，然后进行实例化。
     * instance 的唯一性，创建过程中的线程安全，都由 JVM 来保证。
     * 具体来说：在类加载的最后一个阶段，即类的初始化阶段时，会执行构造器的 <clinit> 方法，
     * 该方法由类里面所有的类变量的赋值动作和静态代码块组成的。JVM内部会保证一个类的 <clinit> 方法
     * 在多线程环境下被正确的加锁同步，从而保证了线程安全的创建实例对象。
     * <p/>
     * 另外，实例变量不用使用 volatile，因为只有一个线程会执行具体的类的初始化代码 <clinit>，
     * 也就是即使有指令重排序，因为根本不会影响到其他线程（其他线程都在等待，初始化后再唤醒它们）。
     */
    private static class SingletonHolder {
        private static final LazySingletonInnerStatic instance = new LazySingletonInnerStatic();
    }

    public static LazySingletonInnerStatic getInstance() {
        return SingletonHolder.instance;
    }

}
