public class Main {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    static float[] arr = new float[SIZE];
    static float[] b0 = new float[HALF];
    static float[] b1 = new float[HALF];

    public static void main(String[] args) {

        firstRum();
        secondRum();

    }

    /**
     * Замер времени с 1 потоком
     */
    public static void firstRum() {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println(System.currentTimeMillis() - a);
    }

    /**
     * Замер времени с 2мя потоками
     */
    public static synchronized void secondRum() {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();

        float[][] b = {b0, b1};

//Деление массива на два
        System.arraycopy(arr, 0, b0, 0, HALF);
        System.arraycopy(arr, HALF, b1, 0, HALF);

        MyThread[] myThreads = new MyThread[2];
        for (int i = 0; i < myThreads.length; i++){
            myThreads[i] = new MyThread("myThread #" + i, b[i]);
            myThreads[i].start();
        }

        try {
            for (MyThread thread : myThreads){
                thread.join();
            }
        } catch (InterruptedException e){
            e.getStackTrace();
        }

// Обратная склейка
        System.arraycopy(b0, 0, arr, 0, HALF);
        System.arraycopy(b1, 0, arr, HALF, HALF);

        System.out.println(System.currentTimeMillis() - a);

    }
}