package 策略模式.使用枚举类隐藏策略实现类;

import 策略模式.Income;
import 策略模式.Income实现类.MerchantAIncome;
import 策略模式.Income实现类.MerchantBIncome;
import 策略模式.Income实现类.MerchantCIncome;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 18190
 * @Date: 2023/1/8  11:51
 * @VERSION 1.0
 */


@SuppressWarnings("all")
public enum MerchantIncomeEnum {

    MERCHANT_A("a", new MerchantAIncome()),
    MERCHANT_B("b", new MerchantBIncome()),
    MERCHANT_C("c", new MerchantCIncome()),;
    private final String key;
    private final Income income;

    MerchantIncomeEnum(String key, Income income) {
        this.key = key;
        this.income = income;
    }

    public static Income getIncome(String key) {
//        转成map
        Map<String, Income> map = Arrays.stream(MerchantIncomeEnum.values()).collect(Collectors.toMap(p -> p.key, p -> p.income));
        return map.get(key);
    }
}
