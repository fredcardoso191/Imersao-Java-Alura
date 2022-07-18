import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    private static JsonParser jsonParser;
    public static void main(String[] args) throws Exception {
        //create http connection//search top 250 movies
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI uri = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response =  client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        //get data of interest only (title, poster, rating)
        var parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);

        //display and manipulate data
        for (Map<String, String> movie : movieList) {
            System.out.println("\nTitle: " + movie.get("title"));
            System.out.println("Image: " + movie.get("image"));
            System.out.println("Rating: " + movie.get("imDbRating"));
            double rating = Double.parseDouble(movie.get("imDbRating"));
            System.out.print("Star Rating: ");
            for (double i = 0; i < Math.round(rating); i++) {
                System.out.print("\u2606");
                //System.out.print("\u2B50");
                //System.out.print("*");
            }
            System.out.print("\n");
        }
    }
}