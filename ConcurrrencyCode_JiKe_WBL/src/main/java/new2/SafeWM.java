package new2;

import java.util.concurrent.atomic.AtomicReference;

/**演示设置库存上线的原子类使用方法
 * @author Demon
 * @date 2023/1/15
 */
public class SafeWM {
    class WMRange{
        final int upper;
        final int lower;
        WMRange(int upper,int lower){
            // 省略构造函数实现
            this.upper = 1;
            this.lower = 1;
        }
    }
    final AtomicReference<WMRange> rf = new AtomicReference<>(
            new WMRange(0,0)
    );
    // 设置库存上限
    void setUpper(int v){
        WMRange nr;
        // 需要移动到do循环里面 如果另一线程已经修改过，or到地址已变，会陷入死循环
        WMRange or = rf.get();
        do{
            // 检查参数合法性
            if(v < or.lower){
                throw new IllegalArgumentException();
            }
            nr = new
                    WMRange(v, or.lower);
        }while(!rf.compareAndSet(or, nr));
    }
}
