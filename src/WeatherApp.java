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
    private final Scanner scanner = new Scanner(System.in);
    private Gson gson = new Gson();

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

    public void run() throws IOException {
        String zipcode = promptForInput(ZIP_PROMPT);
        String weatherInformation = getWeatherInformation(zipcode);
        APIResult apiResult = gson.fromJson(weatherInformation, APIResult.class);
        System.out.println(apiResult.coord.lat);
    }
}