import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    private static JsonParser jsonParser;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - Most Popular Movies. | 2 - Top 250 Movies.");
        System.out.print("Enter a valid value: ");
        int value = sc.nextInt();

        //create http connection//search top 250 movies
        String url = (value == 1) ? "https://imdb-api.com/en/API/MostPopularMovies/(Your API key here)" :
                "https://imdb-api.com/en/API/Top250Movies/(Your API key here)";

        //API Top 250 Movies reserve: https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060

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
            try {
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
            } catch (NumberFormatException ex) {
                System.out.print("Invalid format.");
            }
            System.out.println("\n");
        }
        sc.close();
    }
}