package test.completableFuture_future;

import java.util.concurrent.CompletableFuture;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/8 11:20
 * @version: 1.0
 */
public class CompletableFutureTest {
  public static void main(String[] args) throws Exception {
    CompletableFutureTest.test1();
    System.out.println("test2");
    CompletableFutureTest.test2();
    System.out.println("test3");
    CompletableFutureTest.test3();
    System.out.println("anyofAllof");
    CompletableFutureTest.anyofAllof();
    System.out.println("thenCompose");
    CompletableFutureTest.thenCompose();
    System.out.println("thenCombine ");
    CompletableFutureTest.thenCombine();
    System.out.println("thenAccept ");
    CompletableFutureTest.thenAccept();
  }

  private static void test1() throws Exception {
    CompletableFuture<String> completableFuture = new CompletableFuture();
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                // 模拟执行耗时任务
                System.out.println("task1 doing...");
                try {
                  // throw new InterruptedException();
                  Thread.sleep(500);
                  /* String s = null;
                  boolean b = false;
                  if (b) {
                    s = "";
                  }
                  s.length();*/
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                // 告诉completableFuture任务已经完成
                completableFuture.complete("result");
              }
            })
        .start();
    // 获取任务结果，如果没有完成会一直阻塞等待
    System.out.println("get1:");
    String result = completableFuture.get();
    System.out.println("计算结果1:" + result);
  }

  private static void test2() throws Exception {
    CompletableFuture<String> completableFuture = new CompletableFuture();
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                try {
                  // 模拟执行耗时任务
                  System.out.println("task2 doing...");
                  try {
                    Thread.sleep(500);
                    String s = null;
                    boolean b = false;
                    if (b) {
                      s = "";
                    }
                    s.length();
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  // throw new RuntimeException("抛异常了");
                } catch (Exception e) {
                  // 告诉completableFuture任务发生异常了
                  e.printStackTrace();
                  completableFuture.complete("ex");
                  completableFuture.completeExceptionally(e);
                }
              }
            })
        .start();
    // 获取任务结果，如果没有完成会一直阻塞等待
    System.out.println("get2:");
    String result = completableFuture.get();
    System.out.println("计算结果2:" + result);
  }

  private static void test3() throws Exception {
    // supplyAsync内部使用ForkJoinPool线程池执行任务
    CompletableFuture<String> completableFuture =
        CompletableFuture.supplyAsync(
            () -> {
              // 模拟执行耗时任务
              System.out.println("task3 doing...");
              try {
                Thread.sleep(300);
                /* String s = null;
                boolean b = false;
                if (b) {
                  s = "";
                }
                s.length();*/
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              // 返回结果
              return "result";
            });
    System.out.println("计算结果3:" + completableFuture.get());
  }

  private static void anyofAllof() throws Exception {

    CompletableFuture<String> completableFuture1 =
        CompletableFuture.supplyAsync(
            () -> {
              // 模拟执行耗时任务
              System.out.println("task1 doing...");
              try {
                Thread.sleep(500);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              // 返回结果
              return "result1";
            });

    CompletableFuture<String> completableFuture2 =
        CompletableFuture.supplyAsync(
            () -> {
              // 模拟执行耗时任务
              System.out.println("task2 doing...");
              try {
                Thread.sleep(800);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              // 返回结果
              return "result2";
            });

    CompletableFuture<Object> anyResult =
        CompletableFuture.anyOf(completableFuture1, completableFuture2);

    System.out.println("第一个完成的任务结果:" + anyResult.get());

    CompletableFuture<Void> allResult =
        CompletableFuture.allOf(completableFuture1, completableFuture2);

    // 阻塞等待所有任务执行完成
    allResult.join();
    System.out.println("所有任务执行完成");
  }

  private static void thenCompose() throws Exception {

    CompletableFuture<String> completableFuture1 =
        CompletableFuture.supplyAsync(
            () -> {
              // 模拟执行耗时任务
              System.out.println("task1 doing...");
              try {
                Thread.sleep(3000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              // 返回结果
              return "result1";
            });

    // 等第一个任务完成后，将任务结果传给参数result，执行后面的任务并返回一个代表任务的completableFuture
    CompletableFuture<String> completableFuture2 =
        completableFuture1.thenCompose(
            result ->
                CompletableFuture.supplyAsync(
                    () -> {
                      // 模拟执行耗时任务
                      System.out.println("task2 doing...");
                      try {
                        Thread.sleep(3000);
                      } catch (InterruptedException e) {
                        e.printStackTrace();
                      }
                      // 返回结果
                      return "result2";
                    }));

    System.out.println(completableFuture2.get());
  }

  private static void thenCombine() throws Exception {

    CompletableFuture<Integer> completableFuture1 =
        CompletableFuture.supplyAsync(
                () -> {
                  // 模拟执行耗时任务
                  System.out.println("task1 doing...");
                  try {
                    Thread.sleep(3000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  // 返回结果
                  return 100;
                })
            .thenCombine(
                CompletableFuture.supplyAsync(
                    () -> {
                      // 模拟执行耗时任务
                      System.out.println("task2 doing...");
                      try {
                        Thread.sleep(3000);
                      } catch (InterruptedException e) {
                        e.printStackTrace();
                      }
                      // 返回结果
                      return 2000;
                    }),
                // 合并函数
                (result1, result2) -> result1 + result2);

    // 将第一个任务与第二个任务组合一起执行，都执行完成后，将两个任务的结果合并
    /*CompletableFuture<Integer> completableFuture2 =
    completableFuture1.thenCombine(
        // 第二个任务
        CompletableFuture.supplyAsync(
            () -> {
              // 模拟执行耗时任务
              System.out.println("task2 doing...");
              try {
                Thread.sleep(3000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              // 返回结果
              return 2000;
            }),
        // 合并函数
        (result1, result2) -> result1 + result2);*/

    System.out.println(completableFuture1.get());
  }

  private static void thenAccept() throws Exception {

    CompletableFuture<Integer> completableFuture1 =
        CompletableFuture.supplyAsync(
            () -> {
              // 模拟执行耗时任务
              System.out.println("task1 doing...");
              try {
                Thread.sleep(3000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              // 返回结果
              return 100;
            });

    // 注册完成事件
    completableFuture1.thenAccept(result -> System.out.println("task1 done,result:" + result));

    CompletableFuture<Integer> completableFuture2 =
        // 第二个任务
        CompletableFuture.supplyAsync(
            () -> {
              // 模拟执行耗时任务
              System.out.println("task2 doing...");
              try {
                Thread.sleep(3000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              // 返回结果
              return 2000;
            });

    // 注册完成事件
    completableFuture2.thenAccept(result -> System.out.println("task2 done,result:" + result));

    // 将第一个任务与第二个任务组合一起执行，都执行完成后，将两个任务的结果合并
    CompletableFuture<Integer> completableFuture3 =
        completableFuture1.thenCombine(
            completableFuture2,
            // 合并函数
            (result1, result2) -> {
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              return result1 + result2;
            });

    System.out.println(completableFuture3.get());
  }
}
