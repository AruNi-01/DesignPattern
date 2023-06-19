package observer.classic_observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023-06-13
 */

// 观察者
interface Observer {
    // 观察者—更新 Message 操作
    void update(Message message);
}

// 被观察者
interface Subject {
    // 注册/移除观察者
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    // 通知所有的观察者
    void notifyObservers(Message message);
}

// 具体的被观察者
class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Message message) {
        // 通知所有的观察者 update message
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// 具体的观察者 01
class ConcreteObserver01 implements Observer {
    @Override
    public void update(Message message) {
        // TODO: 获取消息通知，执行 ConcreteObserver01 自己的逻辑
        System.out.println("ConcreteObserver01 is notified.");
    }
}

// 具体的观察者 02
class ConcreteObserver02 implements Observer {
    @Override
    public void update(Message message) {
        // TODO: 获取消息通知，执行 ConcreteObserver02 自己的逻辑
        System.out.println("ConcreteObserver02 is notified.");
    }
}

public class ClassicObserver {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserver01());
        subject.registerObserver(new ConcreteObserver02());
        // TODO: 发生某种事情，通知所有的观察者
        subject.notifyObservers(new Message());
    }
}

class Message {
}
