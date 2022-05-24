import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class WeatherApp {
    private static final String ZIP_PROMPT = "Type in your Zip code: ";
    private static final String API_KEY = "69b953300c17615eb1dbd933559cadb2";
    public static final String ASK_USER = "If you want to check another zipcode Type 'y' . If you want to exit Type 'n'";
    private final Scanner scanner = new Scanner(System.in);
    private final Gson gson = new Gson();
    private APIResult apiResult = new APIResult();
    private String zipcode;

    private String promptForInput(String prompt) {
        System.out.println(prompt);
        return scanner.next();
    }

    private String getWeatherInformation(String zipcode) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?zip=" + zipcode + "&appid=" + API_KEY + "&units=imperial");
        URLConnection conn = url.openConnection();
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        WeatherApp w = new WeatherApp();
        w.run();
    }

    public void askUserIfNewZip() throws IOException {
        System.out.println(ASK_USER);
        String x = scanner.next();
        if (x.equals("y")) {
            run();
        }
        System.exit(0);
    }

    public void generateData() throws IOException {
        zipcode = promptForInput(ZIP_PROMPT);
        String weatherInformation = getWeatherInformation(zipcode);
        apiResult = gson.fromJson(weatherInformation, APIResult.class);
    }

    public void generalInfo() {
        System.out.println("City: " + apiResult.name);
        System.out.println("Zipcode: " + zipcode);
        System.out.println("Country: " + apiResult.sys.country);
        System.out.println("Temperature: " + apiResult.main.temp + " °F");
        System.out.println("Feels like: " + apiResult.main.feels_like + " °F");
        System.out.println("Weather: " + apiResult.weather.get(0).description);
        System.out.println("Current Coordinate Latitude: " + apiResult.coord.lat);
        System.out.println("Current Coordinate Longitude: " + apiResult.coord.lon);
    }

    public void windSpeed() {
        if (apiResult.wind.speed < 20) {
            System.out.println("Wind: Breezy");
        } else if (apiResult.wind.speed > 21 && apiResult.wind.speed < 35) {
            System.out.println("Wind: Windy");
        } else {
            System.out.println("Wind: Very Windy");
        }
    }


    public void run() throws IOException {
        generateData();
        generalInfo();
        windSpeed();
        askUserIfNewZip();
    }


    //copy to see json with proper units>
    //https://api.openweathermap.org/data/2.5/weather?zip=33496,us&appid=69b953300c17615eb1dbd933559cadb2&units=imperial

    //Goals: If user wants to check another zipcode, allow them, else exit. DONE
    //Fix Weather to display the Description. DONE
    //Give displayInfo Less to do. DONE

}