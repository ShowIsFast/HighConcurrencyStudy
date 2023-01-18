package completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * completablefuture 异常处理
 * @author Demon
 * @date 2023/1/18
 */
public class FutureExceptionDemo {
    public static void main(String[] args) {
        // 有异常 需要捕获
        CompletableFuture<Integer>
                f0 = CompletableFuture.supplyAsync(()->(7/0))
                .thenApply(r->r*10);
        System.out.println(f0.join());
        // deal
        CompletableFuture<Integer>
                ffo = CompletableFuture.supplyAsync(()->7/0)
                .thenApply(r->r*10)
                .exceptionally(e->0);
        System.out.println(ffo.join());
    }
}
