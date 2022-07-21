import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {
    public String getDatas(String url) {
        try {
            URI uri = URI.create(url);
            var client = java.net.http.HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(uri).GET().build();
            HttpResponse<String> response = null;
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
