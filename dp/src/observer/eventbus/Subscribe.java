package observer.eventbus;

import com.google.common.annotations.Beta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @desc: 用于标明观察者中的哪个函数可以接受消息
 * 注解 @Beta 是 Guava 中一个表示处于 Beta 阶段的 API（无影响）
 * @author: AruNi_Lu
 * @date: 2023-06-18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Beta
public @interface Subscribe {
}
