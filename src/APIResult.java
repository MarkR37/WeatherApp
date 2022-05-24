import java.util.List;


public class APIResult {
    public Coord coord;
    public List<Weather> weather;
    public Main main;
    public Wind wind;
    public Sys sys;
    public String name;

    public static class Coord {
        public double lon;
        public double lat;
    }

    public static class Weather {
        public String description;
    }

    public static class Main {
        public double temp;
        public double feels_like;
    }

    public static class Wind {
        public double speed;
    }

    public static class Sys {
        public String country;
    }

}
