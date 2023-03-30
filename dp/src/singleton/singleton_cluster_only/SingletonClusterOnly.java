package singleton.singleton_cluster_only;

import singleton.singleton_thread_only.SingletonThreadOnly;

/**
 * @desc:
 * @author: AruNi_Lu
 * @date: 2023/3/30
 */
public class SingletonClusterOnly {

    private static SingletonClusterOnly instance;
    // 外部共享存储区
    private static SharedObjectStorage storage = FileSharedObjectStorage(/*入参省略，比如文件地址*/);
    // 分布式锁
    private static DistributedLock lock = new DistributedLock();

    private SingletonClusterOnly() { }

    public synchronized static SingletonClusterOnly getInstance() {
        // 各进程进来，发现实例为空才从文件获取，否则就直接返回实例
        if (instance == null) {
            // 加锁，避免其他进程再从文件中获取实例
            lock.lock();
            instance = storage.load(SingletonClusterOnly.class);
        }
        return instance;
    }

    /**
     * 某进程使用完毕后，对实例进行释放，让其他进程能从文件中获取实例
     */
    public synchronized void freeInstance() {
        storage.save(this, SingletonClusterOnly.instance);
        instance = null;
        lock.unlock();
    }
}

// 使用示例
SingletonClusterOnly singleton = SingletonClusterOnly.getInstance();
// 使用单例 singleton...
singleton.freeInstance();