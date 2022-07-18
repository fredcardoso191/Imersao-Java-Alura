import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    private static JsonParser jsonParser;

    public static void main(String[] args) throws Exception {
        //create http connection//search top 250 movies
        String url = "https://imdb-api.com/en/api/top250movies/(Your API Key here)";
        URI uri = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response =  client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //get data of interest only (title, poster, rating)
        var parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);

        //display and manipulate data
        for (Map<String, String> movie : movieList) {
            System.out.println("Title: " + movie.get("title"));
            System.out.println("Image: " + movie.get("image"));
            System.out.println("Rating: " + movie.get("imDbRating"));
            System.out.println();
        }
    }
}
