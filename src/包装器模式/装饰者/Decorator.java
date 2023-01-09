package 包装器模式.装饰者;

import 包装器模式.被装饰者.Beverage;

import java.math.BigDecimal;

/**
 * @author 18190
 * @Date: 2023/1/8  15:31
 * @VERSION 1.0
 * 装饰者
 * 配料  继承 被装饰着
 */
public abstract class Decorator extends Beverage {
//    private double sum = 0;
    private BigDecimal sum = new BigDecimal("0.0");
    private Beverage b;
    public Decorator(Beverage b){
        this.b = b;
        sum = sum.add(b.cost());
    }

    public Decorator(){

    }

    protected BigDecimal getSum() {
        return sum;
    }

    protected Beverage getB() {
        return b;
    }

    @Override
    public abstract String getDecription() ;

    @Override
    public BigDecimal cost() {
        return getSum();
    }
}
