package 工厂模式.工厂方法模式.对象;

/**
 * @author 18190
 * @Date: 2023/1/8  21:06
 * @VERSION 1.0
 */
public class Pizza {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void prepare() {
        System.out.println("prepare");
    }

    public void bake() {
        System.out.println("bake");
    }

    public void cut() {
        System.out.println("cut");
    }

    public void box() {
        System.out.println("box");
    }
}
