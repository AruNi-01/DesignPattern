package singleton.singleton_thread_only;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/3/30
 */
public class SingletonThreadOnly {

    private static final Map<Long, SingletonThreadOnly> instance = new ConcurrentHashMap<>();

    private SingletonThreadOnly() { }

    public static SingletonThreadOnly getInstance() {
        long id = Thread.currentThread().getId();
        instance.putIfAbsent(id, new SingletonThreadOnly());
        return instance.get(id);
    }
}
