import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class WeatherApp {
    private static final String ZIP_PROMPT = "Type in your Zip code: ";
    private static final String API_KEY = "69b953300c17615eb1dbd933559cadb2";
    private final Scanner scanner = new Scanner(System.in);

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );
        return map;
    }

    public static void main(String[] args) {
        WeatherApp w = new WeatherApp();
        w.run();
    }

    private String formatWeatherInformation(Map<String, Object> respMap, String zipcode) {
        Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
        Map<String, Object> sysMap = jsonToMap(respMap.get("sys").toString());
        ArrayList<Map<String, Object>> weathers = (ArrayList<Map<String, Object>>) respMap.get("weather");
        Map<String, Object> weatherMap = weathers.get(0);
        return "Units of Measurement are in Imperial\n"
                + "Temperature: " + mainMap.get("temp") + "\n"
                + "Humidity: " + mainMap.get("humidity") + "\n"
                + "Country: " + sysMap.get("country") + "\n"
                + "Weather: " + weatherMap.get("main") + "\nDescription: " + weatherMap.get("description") + "\n"
                + "Zip Code: " + zipcode;
    }

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

    public void run() {
        String zipcode = promptForInput(ZIP_PROMPT);

        try {
            String weatherInformation = getWeatherInformation(zipcode);
            Map<String, Object> respMap = jsonToMap(weatherInformation);
            System.out.println(formatWeatherInformation(respMap, zipcode));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}