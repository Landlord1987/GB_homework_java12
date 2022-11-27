public class MyThread extends Thread{

    float[] arr;
    MyThread(String name, float[] arr) {
        super(name);
        this.arr = arr;
    }

    @Override
    public void run() {
        System.out.printf("Поток %s запущен...\n", getName());

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.printf("Поток %s завершил работу...\n", getName());
    }
}
