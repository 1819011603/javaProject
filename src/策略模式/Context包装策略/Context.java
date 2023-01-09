package 策略模式.Context包装策略;

import 策略模式.Income;
import 策略模式.使用枚举类隐藏策略实现类.MerchantIncomeEnum;

/**
 * @author 18190
 * @Date: 2023/1/8  11:57
 * @VERSION 1.0
 */
public class Context {


    public Context( ) {

    }

    public String addCustomer(String key,String customer) {
        Income income = MerchantIncomeEnum.getIncome(key);
        return income.addCustomer(customer);
    }
}
