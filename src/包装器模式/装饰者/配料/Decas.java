package 包装器模式.装饰者.配料;

import 包装器模式.被装饰者.Beverage;
import 包装器模式.装饰者.Decorator;

import java.math.BigDecimal;

/**
 * @author 18190
 * @Date: 2023/1/8  15:37
 * @VERSION 1.0
 *
 * 摩卡
 */
public class Decas extends Decorator {
    public Decas(Beverage b){
        super(b);
    }
    @Override
    public BigDecimal cost() {
        return getSum().add(BigDecimal.valueOf(0.15));
    }

    @Override
    public String getDecription() {
        return getB().getDecription()+"+摩卡("+BigDecimal.valueOf(0.15).toString() + ")";
    }
}
