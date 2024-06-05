public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> intBQ = new BlockingQueue<>(5);
        Thread producer = new Thread(() -> {
            try{
                for(int i = 0; i < 10; i++){
                    intBQ.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted");
            }
        });

        Thread consumer = new Thread(() -> {
            try{
                for(int i = 0; i < 10; i++){
                    int item = intBQ.take();
                    System.out.println("Consumed: " + item);
                    Thread.sleep(150);
                }
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Consumer interrupted");
            }
        });

        producer.start();
        consumer.start();
    }
}