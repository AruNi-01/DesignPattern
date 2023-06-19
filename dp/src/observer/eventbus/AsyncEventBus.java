package observer.eventbus;

import java.util.concurrent.Executor;

/**
 * @desc: 异步非阻塞的观察者模式
 * @author: AruNi_Lu
 * @date: 2023-06-18
 */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}
