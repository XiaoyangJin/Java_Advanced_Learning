import java.util.List;
import java.util.Queue;

public class BlockingQueue<T> {
    private final int maxSize;
    private final Queue<T> queue;

    public BlockingQueue(int maxSize, Queue<T> queue) {
        this.maxSize = maxSize;
        this.queue = queue;
    }
}
