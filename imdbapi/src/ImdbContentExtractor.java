import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImdbContentExtractor implements ContentExtractor {
    public List<Content> extractContent(String json) {
        //get data of interest only (title, poster, rating)
        var parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        //add contents list
        for (Map<String, String> attributes : attributesList) {
            String title = attributes.get("title");
            String imageUrl = attributes.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");

            var content = new Content(title, imageUrl);

            contents.add(content);
        }
        return contents;
    }
}
