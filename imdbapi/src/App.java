import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    private static JsonParser jsonParser;
    public static void main(String[] args) {
        //create http connection//search top 250 movies or most popular movies
//        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
//        ContentExtractor extractor = new NasaContentExtractor();
//      or
        ContentExtractor extractor = new ImdbContentExtractor();
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";

        String json = new ClientHttp().getDatas(url);

        //display and manipulate data
        var generate = new StickerGenerator();

        List<Content> contents = extractor.extractContent(json);

        for (int i = 0; i < 3; i++) {
            try {
                Content content = contents.get(i);
                InputStream inputStream = new URL(content.getImageUrl()).openStream();
                String fileName = content.getTitle() + ".png";

                generate.create(inputStream, fileName);

                System.out.println("Title: " + content.getTitle());
            } catch (Exception ex) {
                System.out.print(ex);
            }
            System.out.println();
        }
    }
}