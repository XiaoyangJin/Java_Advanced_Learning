import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<E> {
    private final Object[] queue;
    private final ReentrantLock lock;
    private final Condition full;
    private final Condition empty;
    private int size;
    private int start, end;

    public ArrayBlockingQueue(int size) {
        this.size = size;
        queue = new Object[size];
        lock = new ReentrantLock();
        full = lock.newCondition();
        empty = lock.newCondition();
    }
    //pull, offer
    public E poll(){
        lock.lock();
        try{
            while(size == 0){
                //empty, thread should waiting
                empty.await();
            }
            E ele = (E)queue[start++];
            start %= queue.length;
            size--;
            full.signal(); //give a signal of finish
            return ele;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public void offer(E e){
        lock.lock();
        try{
            while(size == queue.length){
                full.await();
            }
            E ele = (E)queue[start++];
            start %= queue.length;
            size--;
            full.signal(); //give a signal of finish
            return ele;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
