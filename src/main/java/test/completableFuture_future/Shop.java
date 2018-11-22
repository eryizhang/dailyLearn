package test.completableFuture_future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * <p>
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/9 13:45
 * @version: 1.0
 */
public class Shop {

    private final Random random = new Random();

    private String name;

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Shop(String name) {
        super();
        this.name = name;
    }

    private static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    double getPrice(String product) {
        Shop.delay();
        return random.nextDouble() * 100;
    }

    //	public Future<Double> getPriceAsync(String product){
    //		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
    //		new Thread(()->futurePrice.complete(getPrice(product))).start();
    //		return futurePrice;
    //	}

    /**
     * 推荐使用这种方式
     * 1.将getPrice()方法转为异步调用。
     * 2.supplyAsync参数是一个生产者。Supplier 没有参数返回一个对象。若lambda没有参数并且可以返回对象则满足
     * 3.supplyAsync内部对Supplier方法对异常进行处理，避免因为异常程序永久阻塞。
     */
    private Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> getPrice(product));
    }

    public static void main(String[] args) throws Exception {
        Shop shop = new Shop("bestShop");
        long start = System.currentTimeMillis();
        Future<Double> futurePrice = shop.getPriceAsync("some product");
        System.out.println("调用返回，耗时" + (System.currentTimeMillis() - start));
        double price = futurePrice.get();
        System.out.println("价格返回，耗时" + (System.currentTimeMillis() - start));
    }
}

