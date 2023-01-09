package 包装器模式.被装饰者.impl;

import 包装器模式.被装饰者.Beverage;

import java.math.BigDecimal;

/**
 * @author 18190
 * @Date: 2023/1/8  15:33
 * @VERSION 1.0
 * 深倍
 */
public class HourseBlend extends Beverage {

    @Override
    public String getDecription() {
        return "深倍("+cost().toString() + ")";
    }

    @Override
    public BigDecimal cost() {
        return BigDecimal.valueOf(0.88);
    }
}
