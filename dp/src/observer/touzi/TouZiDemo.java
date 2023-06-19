package observer.touzi;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023-06-14
 */
public class TouZiDemo {
}

// 监听器（观察者）
interface RegisterListener {
    void handleRegisterSuccess(long userId);
}

class RegisterPromotionListener implements RegisterListener {
    private PromotionService promotionService;

    @Override
    public void handleRegisterSuccess(long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }
}

class RegisterNotificationListener implements RegisterListener {
    private NotificationService notificationService;

    @Override
    public void handleRegisterSuccess(long userId) {
        notificationService.sendInboxMessage(userId, "Welcome...");
    }
}

// 触发器（被监听者）
class RegisterDispatcher {
    private List<RegisterListener> listeners = new ArrayList<>();

    public void addListener(RegisterListener listener) {
        listeners.add(listener);
    }

    public void removeListener(RegisterListener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners(long userId) {
        for (RegisterListener listener : listeners) {
            listener.handleRegisterSuccess(userId);
        }
    }
}

class UserController {
    private UserService userService;

    private RegisterDispatcher registerDispatcher;

    public Long register(String telephone, String password) {
        //省略输入参数的校验代码
        // 省略 userService.register() 异常的 try-catch 代码
        long userId = userService.register(telephone, password);

        // 添加需要的监听器
        addListenerOfRegister();
        // 通知所有监听器（实际情况可以另起一个线程来处理）
        registerDispatcher.notifyListeners(userId);

        return userId;
    }

    private void addListenerOfRegister() {
        registerDispatcher.addListener(new RegisterPromotionListener());
        registerDispatcher.addListener(new RegisterNotificationListener());
    }
}
