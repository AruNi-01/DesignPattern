package observer.eventbus;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @desc: 同步阻塞的观察者模式
 * @author: AruNi_Lu
 * @date: 2023-06-18
 */
public class EventBus {

    private Executor executor;
    private ObserverRegistry registry = new ObserverRegistry();

    public EventBus() {
        // MoreExecutors.directExecutor()，Guava 提供的单线程工具类
        this(MoreExecutors.directExecutor());
    }

    protected EventBus(Executor executor) {
        this.executor = executor;
    }

    public void register(Object object) {
        registry.register(object);
    }

    public void post(Object event) {
        // 获取消息可匹配的函数
        List<ObserverAction> observerActions = registry.getMatchedObserverActions(event);

        for (ObserverAction observerAction : observerActions) {
            executor.execute(() -> observerAction.execute(event));
        }
    }
}
