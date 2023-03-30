package singleton;

/**
 * @desc: 之前讲的所有的单例模式，其实都可以使用反射来获取到构造器，然后创建新的实例，
 * 从而破坏了单例性。
 * 而使用枚举的方式，既简单又能有效防止反射暴力破解，因为 JDK 中明确给出不能通过反射来调用私有构造方法。
 * 但是枚举方式不支持懒加载，不过它能绝对的保证实例的单一。
 * @author: AruNi_Lu
 * @date: 2023/3/30
 */
public enum HungrySingletonEnum {

    INSTANCE;

    public HungrySingletonEnum getInstance() {
        return INSTANCE;
    }

}
