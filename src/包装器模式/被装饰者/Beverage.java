package 包装器模式.被装饰者;

import java.math.BigDecimal;

/**
 * @author 18190
 * @Date: 2023/1/8  15:29
 * @VERSION 1.0
 * 饮料 抽象类
 */
public abstract class Beverage {
    String decription = "unknown Beverage";

    public   String getDecription() {
        return decription;

    }

    public abstract BigDecimal cost();
}
