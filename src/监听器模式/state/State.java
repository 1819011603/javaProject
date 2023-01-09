package 监听器模式.state;

/**
 * @author 18190
 * @Date: 2023/1/7  20:13
 * @VERSION 1.0
 */
public class State {
    int Temperature;
    int humidity;
    float pressure;

    public State(int temperature, int humidity, float pressure) {
        Temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public State() {
    }

    public int getTemperature() {
        return Temperature;
    }

    public void setTemperature(int temperature) {
        Temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
}
