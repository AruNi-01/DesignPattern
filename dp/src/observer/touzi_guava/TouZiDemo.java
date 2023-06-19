package observer.touzi_guava;

import observer.eventbus.AsyncEventBus;
import observer.eventbus.EventBus;
import observer.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023-06-14
 */
public class TouZiDemo {
    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.register("phone", "pwd");
    }
}

// 无需实现任何 Listener 接口
class RegisterPromotionListener {
    private PromotionService promotionService = new PromotionService();

    // 使用 @Subscribe 声明监听者
    @Subscribe
    public void handleRegisterSuccess(Long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }
}

class RegisterNotificationListener {
    private NotificationService notificationService = new NotificationService();

    @Subscribe
    public void handleRegisterSuccess(Long userId) {
        notificationService.sendInboxMessage(userId, "Welcome...");
    }
}

class UserController {
    private UserService userService = new UserService();

    private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20;
    // eventBus = new EventBus(); // 同步阻塞模式
    private EventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE));

    // 向 eventBus 中注册监听者
    private void setRegisterListeners(List<Object> listeners) {
        for (Object listener : listeners) {
            eventBus.register(listener);
        }
    }

    public Long register(String telephone, String password) {
        // 省略输入参数的校验代码
        // 省略 userService.register() 异常的 try-catch 代码
        long userId = userService.register(telephone, password);

        addListenerOfRegister();

        // 通过 eventBus.post 派发消息（触发被 @Subscribe 注解的方法）
        eventBus.post(userId);

        return userId;
    }

    // 添加 Register 相关的 Listener，实际场景可以把 List 作为参数，由外部调用者决定要注入什么 Listener
    private void addListenerOfRegister() {
        List<Object> listeners = new ArrayList<>();
        listeners.add(new RegisterNotificationListener());
        listeners.add(new RegisterPromotionListener());
        setRegisterListeners(listeners);
    }
}
