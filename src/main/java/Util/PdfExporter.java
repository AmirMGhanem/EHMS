package Util;

import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfExporter {






    public void Snapshotter(double x,double y, double width, double height) {
        try {
            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle((int) (290 + x), (int) y, (int) width, (int) height);
            BufferedImage image = robot.createScreenCapture(rectangle);
            javafx.scene.image.Image myImage = SwingFXUtils.toFXImage(image, null);
            File f = new File("src/main/resources/Images/Temp.png");
            ImageIO.write(image, "png", f);
            System.out.println("Image Saved");

        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }

}
