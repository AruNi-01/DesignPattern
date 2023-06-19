package observer.eventbus;

import com.google.common.base.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @desc: 用来表示 @Subscribe 注解的方法
 * @author: AruNi_Lu
 * @date: 2023-06-18
 */
public class ObserverAction {

    private Object target;
    private Method method;

    public ObserverAction(Object target, Method method) {
        // Preconditions.checkNotNull() 由 google.common.base 提供
        this.target = Preconditions.checkNotNull(target);
        this.method = method;

        // 暴力破解，使其具有直接访问和修改私有元素的能力
        this.method.setAccessible(true);
    }

    /**
     * 执行方法
     * @param event method 方法的参数
     */
    public void execute(Object event) {
        try {
            method.invoke(target, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // 注意重写 equals 和 hashcode 方法，因为该类被 Set 使用，判断重复需要用到 hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObserverAction that = (ObserverAction) o;

        if (!Objects.equals(target, that.target)) return false;
        return Objects.equals(method, that.method);
    }

    @Override
    public int hashCode() {
        int result = target != null ? target.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        return result;
    }
}
