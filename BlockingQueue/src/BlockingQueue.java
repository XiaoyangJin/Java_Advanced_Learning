import java.util.List;
import java.util.Queue;

public class BlockingQueue<T> {
    private final int maxSize;
    private final Queue<T> queue;

    public BlockingQueue(int maxSize, Queue<T> queue) {
        this.maxSize = maxSize;
        this.queue = queue;
    }

    public synchronized void put(T item) throws InterruptedException{
        while(queue.size() == maxSize){
            wait();
        }
        queue.add(item);
        notifyAll();
    }

    public synchronized T take() throws InterruptedException{
        while (queue.isEmpty()){
            wait();
        }
        T item = queue.poll();
        notifyAll();
        return item;
    }
}
