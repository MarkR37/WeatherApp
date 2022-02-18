import java.util.List;

public class APIResult {
    public Coord coord;
    public List<Weather> weather;
    public String base;

    public static class Coord {
        public double lon;
        public double lat;
    }

    public static class Weather {

    }
}
