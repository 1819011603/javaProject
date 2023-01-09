package 策略模式.Income实现类;

import 策略模式.Income;

/**
 * @author 18190
 * @Date: 2023/1/8  11:53
 * @VERSION 1.0
 */
public class MerchantAIncome implements Income {
    @Override
    public String addCustomer(String customer) {
        System.out.println("渠道商A入驻"+customer+"成功");
        return "success";
    }
}