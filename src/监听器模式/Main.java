package 监听器模式;

import 监听器模式.observe.impl.CurrentDisplay;
import 监听器模式.observe.impl.ForestDisplay;
import 监听器模式.observe.impl.StatisticsDisplay;
import 监听器模式.state.State;
import 监听器模式.subject.WeatherData;

/**
 * @author 18190
 * @Date: 2023/1/7  21:04
 * @VERSION 1.0
 */
public class Main {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentDisplay currentDisplay = new CurrentDisplay(weatherData);
        ForestDisplay forestDisplay = new ForestDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);

        weatherData.setState(new State(80,65,32.4f));
        weatherData.setState(new State(82,70,29.4f));
        weatherData.setState(new State(78,90,29.4f));

    }
}
