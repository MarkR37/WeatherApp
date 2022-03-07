import java.util.List;


public class APIResult {
    public Coord coord;
    public List<Weather> weather;
    public Main main;
    public Wind wind;
    public Sys sys;
    public String base;

    public String name; // its just an object within the json, no need for a class.

    public static class Coord {
        public double lon;
        public double lat;
    }

    public static class Weather {
        public String description;
    }

    public static class Main {
        public double temp;
    }

    public static class Wind {
        public double speed;
        public double deg;
        public double gust;
    }

    public static class Sys {
        public String country;
    }

}
