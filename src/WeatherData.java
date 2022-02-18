import java.util.List;

public class WeatherData {
    private List<WeatherData> weather;
    private int temperature;
    private int humidity;
    private String country;
    private String name;

    public void WeatherData(List<WeatherData> weather, int temperature, int humidity, String country, String name) {
        this.weather = weather;
        this.temperature = temperature;
        this.humidity = humidity;
        this.country = country;
        this.name = name;
    }


}
