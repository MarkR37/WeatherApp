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
    private final Scanner scanner = new Scanner(System.in);

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String,Object>>() {}.getType()
        );
        return map;
    }

    private void giveZip () {
        System.out.println("Type in your Zip code: ");
    }

    private void printInformation(Map<String, Object> mainMap, Map<String, Object> sysMap, Map<String, Object> weatherMap, String ZIPCODE ) {
        System.out.println("Units of Measurement are in Imperial");
        System.out.println("Temperature: " + mainMap.get("temp"));
        System.out.println("Humidity: " + mainMap.get("humidity"));
        System.out.println("Country: " + sysMap.get("country"));
        System.out.println("Weather: " + weatherMap.get("main") +"\nDescription: "+weatherMap.get("description"));
        System.out.println("Zip Code: " + ZIPCODE);
    }
    private void read() {

        String API_KEY = "69b953300c17615eb1dbd933559cadb2";
        giveZip();
        String ZIPCODE = scanner.next();
        String urlString = "http://api.openweathermap.org/data/2.5/weather?zip="+ZIPCODE+"&appid="+API_KEY+"&units=imperial";
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line=rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> sysMap = jsonToMap(respMap.get("sys").toString());
            ArrayList<Map<String, Object>> weathers = (ArrayList<Map<String, Object>>) respMap.get("weather");
            Map<String, Object> weatherMap = weathers.get(0);

            printInformation(mainMap, sysMap, weatherMap, ZIPCODE);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public static void main (String[] args) {
    WeatherApp w = new WeatherApp();
    w.read();
    }
}
