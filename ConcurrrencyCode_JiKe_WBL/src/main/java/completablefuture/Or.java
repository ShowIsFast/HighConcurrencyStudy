package completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static completablefuture.SSPC.sleep;

/**
 * or聚合关系
 * @author Demon
 * @date 2023/1/18
 */
public class Or {
    public static void main(String[] args) {
        Random random = new Random(47);
        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(()->{
                    int t = random.nextInt(10);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    int t = random.nextInt(10);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });
        CompletableFuture<String> f3 =
                f1.applyToEither(f2,s -> s);
        System.out.println(f3.join());
    }
}
