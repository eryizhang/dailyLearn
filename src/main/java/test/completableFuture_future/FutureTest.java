package test.completableFuture_future;

import java.util.concurrent.*;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/8 11:03
 * @version: 1.0
 */
public class FutureTest {
    public static void main(String[] args)
            throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        long now = System.currentTimeMillis();
        Future<Integer> future =
                service.submit(
                        new Callable<Integer>() {

                            @Override
                            public Integer call() throws Exception {
                                try {
                                    Thread.sleep(2000);
                                } catch (Exception e) {

                                }
                                return 1;
                            }
                        });
        System.out.println(System.currentTimeMillis() - now);
        try {
            System.out.println(future.get(1500, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println(System.currentTimeMillis() - now);
        }

        System.out.println("a");
        service.shutdown();
    }
}
