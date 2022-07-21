import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class StickerGenerator {
    public void create(InputStream inputStream, String fileName) throws IOException {
        //image reading
        BufferedImage originalMovie = ImageIO.read(inputStream);

        //create a new image in memory with transparency and new size
        int width = originalMovie.getWidth();
        int height = originalMovie.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        //copy original image to new image (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalMovie, 0, 0, null);

        //configure the font
        graphics.setColor(Color.BLUE);
        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));

        FontMetrics metrics = graphics.getFontMetrics();

        //write phrase in a new image
//        graphics.drawString("TOPZERA", 100, newHeight - 100);
//        graphics.drawString(text, 250, newHeight - 700);
        String text = "TOPZERA";
        int positionX = (newImage.getWidth() - metrics.stringWidth(text)) / 2;
        float positionY = (newImage.getHeight() - metrics.getHeight()) / 2.5f + metrics.getAscent();
        graphics.drawString(text, positionX, positionY);

        //write image to file
        if (new File("output/").mkdir()) {
            System.out.println("Created directory.");
            ImageIO.write(newImage, "png", new File("output/" + fileName));
        } else if (new File("output/").exists()){
            //System.out.println("Directory already exists.");
            ImageIO.write(newImage, "png", new File("output/" + fileName));
        } else {
            System.out.println("Error create directory.");
        }
    }
}
