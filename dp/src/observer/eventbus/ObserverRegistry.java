package observer.eventbus;

import com.google.common.base.Preconditions;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @desc: Observer 注册表
 * @author: AruNi_Lu
 * @date: 2023-06-18
 */
public class ObserverRegistry {

    // Observer 注册表
    private ConcurrentMap<Class<?>, CopyOnWriteArraySet<ObserverAction>> registry = new ConcurrentHashMap<>();

    /**
     * 注册 observer
     * @param observer observer 类
     */
    public void register(Object observer) {
        // 获取该 observer 类的所有 ObserverAction
        Map<Class<?>, Collection<ObserverAction>> observerActions = findAllObserverActions(observer);

        // 遍历所有 ObserverAction，添加到 registry
        for (Map.Entry<Class<?>, Collection<ObserverAction>> entry : observerActions.entrySet()) {
            Class<?> eventType = entry.getKey();
            Collection<ObserverAction> eventActions = entry.getValue();

            CopyOnWriteArraySet<ObserverAction> registeredEventActions = registry.get(eventType);

            // 初始化，避免并发场景下出现错误
            if (registeredEventActions == null) {
                registry.putIfAbsent(eventType, new CopyOnWriteArraySet<>());
                registeredEventActions = registry.get(eventType);
            }
            registeredEventActions.addAll(eventActions);
        }
    }

    /**
     * 消息可匹配函数：获取与该 event 向匹配的所有 ObserverAction（发送消息类型的同类或父类）
     * @param event event
     * @return 所有 ObserverAction
     */
    public List<ObserverAction> getMatchedObserverActions(Object event) {
        List<ObserverAction> matchedObservers = new ArrayList<>();

        Class<?> postedEventType = event.getClass();
        for (Map.Entry<Class<?>, CopyOnWriteArraySet<ObserverAction>> entry : registry.entrySet()) {
            Class<?> eventType = entry.getKey();
            CopyOnWriteArraySet<ObserverAction> eventActions = entry.getValue();

            // 判断 postedEventType 是否由 eventType 派生而来，是说明该 eventType 对应的 eventActions 符合条件
            if (eventType.isAssignableFrom(postedEventType)) {
                matchedObservers.addAll(eventActions);
            }
        }
        return matchedObservers;
    }

    /**
     * 获取该 observer 类的所有 ObserverAction
     * @param observer observer 类
     * @return 所有 eventType 和其对应的 ObserverAction
     */
    private Map<Class<?>, Collection<ObserverAction>> findAllObserverActions(Object observer) {
        Map<Class<?>, Collection<ObserverAction>> observerActions = new HashMap<>();

        Class<?> clazz = observer.getClass();

        // 获取该类所有使用了 @Subscribe 标注的方法，遍历将其添加进 observerActions
        for (Method method : getAnnotatedMethods(clazz)) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> eventType = parameterTypes[0];

            // 初始化
            if (!observerActions.containsKey(eventType)) {
                observerActions.put(eventType, new ArrayList<>());
            }
            observerActions.get(eventType).add(new ObserverAction(observer, method));
        }
        return observerActions;
    }

    /**
     * 获取该类使用了 @Subscribe 标注的方法
     * @param clazz 类
     * @return 符合条件的方法集合
     */
    private List<Method> getAnnotatedMethods(Class<?> clazz) {
        List<Method> annotatedMethods = new ArrayList<>();

        for (Method method : clazz.getDeclaredMethods()) {
            // 判断该方法是否用了 @Subscribe 标注
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                // 方法的参数必须只有一个
                Preconditions.checkArgument(parameterTypes.length == 1,
                        "Method %s has @Subscribe annotation but has %s parameters."
                                + "Subscriber methods must have exactly 1 parameter.",
                        method, parameterTypes.length);
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }

}
