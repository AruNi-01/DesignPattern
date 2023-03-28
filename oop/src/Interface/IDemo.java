package Interface;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/3/18
 */
public interface IDemo {

    // 变量，默认使用 public static final 修饰
    int publicVal = 0;

    // 普通方法，默认使用 public abstract 修饰，实现类必须实现
    void method();

    // 默认方法，实现类可自行决定是否实现
    default void defaultMethod() {
    }

    // 私有方法，实现类没有实现权力
    private void privateMethod() {
    }

    // 静态方法，实现类没有实现权力，可直接通过 . 调用
    static void staticMethod() {
    }

}
