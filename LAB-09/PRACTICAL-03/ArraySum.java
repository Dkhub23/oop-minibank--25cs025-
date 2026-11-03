
class Total {

    long sum = 0;

    void add(long value) {
        sum += value;
    }

    synchronized void synchronizedAdd(long value) {
        sum += value;
    }
}

public class ArraySum {

    static final int THREADS = 4;
    static final int SIZE = 1000000;

    public static void main(String[] args) throws InterruptedException {

        int[] arr = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }

        long expected = SIZE;

        // 1. Without synchronization

        Total total1 = new Total();
        Thread[] t1 = new Thread[THREADS];

        long start = System.nanoTime();

        for (int i = 0; i < THREADS; i++) {

            final int startIndex = i * SIZE / THREADS;
            final int endIndex = (i + 1) * SIZE / THREADS;

            t1[i] = new Thread(() -> {

                for (int j = startIndex; j < endIndex; j++) {
                    total1.add(arr[j]);
                }

            });

            t1[i].start();
        }

        for (Thread t : t1) {
            t.join();
        }

        long time1 = System.nanoTime() - start;

        System.out.println("Without synchronization: " + total1.sum);
        System.out.println("Expected: " + expected);
        System.out.println("Time: " + time1 / 1000000.0 + " ms");


        // 2. With synchronization

        Total total2 = new Total();
        Thread[] t2 = new Thread[THREADS];

        start = System.nanoTime();

        for (int i = 0; i < THREADS; i++) {

            final int startIndex = i * SIZE / THREADS;
            final int endIndex = (i + 1) * SIZE / THREADS;

            t2[i] = new Thread(() -> {

                for (int j = startIndex; j < endIndex; j++) {
                    total2.synchronizedAdd(arr[j]);
                }

            });

            t2[i].start();
        }

        for (Thread t : t2) {
            t.join();
        }

        long time2 = System.nanoTime() - start;

        System.out.println("\nWith synchronization: " + total2.sum);
        System.out.println("Expected: " + expected);
        System.out.println("Time: " + time2 / 1000000.0 + " ms");


        // 3. Using local totals

        long[] partialSum = new long[THREADS];
        Thread[] t3 = new Thread[THREADS];

        start = System.nanoTime();

        for (int i = 0; i < THREADS; i++) {

            final int index = i;
            final int startIndex = i * SIZE / THREADS;
            final int endIndex = (i + 1) * SIZE / THREADS;

            t3[i] = new Thread(() -> {

                long sum = 0;

                for (int j = startIndex; j < endIndex; j++) {
                    sum += arr[j];
                }

                partialSum[index] = sum;

            });

            t3[i].start();
        }

        for (Thread t : t3) {
            t.join();
        }

        long finalSum = 0;

        for (int i = 0; i < THREADS; i++) {
            finalSum += partialSum[i];
        }

        long time3 = System.nanoTime() - start;

        System.out.println("\nUsing local totals: " + finalSum);
        System.out.println("Expected: " + expected);
        System.out.println("Time: " + time3 / 1000000.0 + " ms");
    }
}
