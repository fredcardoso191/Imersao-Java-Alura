import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
    public static void main(String[] args) throws Exception {
        var url = "https://imdb-api.com/en/API/Top250Movies/k_om97iutj";
        var client = HttpClient.newHttpClient();
        URI uri = URI.create(url);
        var request = HttpRequest.newBuilder(uri).build();

        String json = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        
        System.out.println(json);
    }
}
