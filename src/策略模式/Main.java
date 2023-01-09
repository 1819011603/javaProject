package 策略模式;

import 策略模式.Context包装策略.Context;

/**
 * http://www.mark-to-win.com/tutorial/175130.html
 * @author 18190
 * @Date: 2023/1/8  11:57
 * @VERSION 1.0
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        context.addCustomer("a","“商户1”");
        context.addCustomer("b","“商户2”");
        context.addCustomer("c","“商户3”");
    }
}
