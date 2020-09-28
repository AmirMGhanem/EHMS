package Util;

import Model.Therapist;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;


public class PdfExporter {

    public void Snapshotter(double x,double y, double width, double height) {
        try {
            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle((int) (290 + x), (int) y-30, (int) width+10, (int) height+30);
            BufferedImage image = robot.createScreenCapture(rectangle);
            javafx.scene.image.Image myImage = SwingFXUtils.toFXImage(image, null);
            File f = new File("src/main/resources/Images/Temp.png");
            ImageIO.write(image, "png", f);
            System.out.println("Image Saved");

        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveTherapistPDF() throws IOException, DocumentException, SQLException {
        DBH.therapistDAO TDBH = new DBH.therapistDAO();
        //Create PDF, Initilaize and Open
        Document document = new Document();
        PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/Files/PDF/TherapistsPDF.pdf"));
        document.open();
        //Adding the footer to the pdf file, by class created on the utils
        Util.FooterPageEvent footer = new FooterPageEvent();
        writer.setPageEvent(footer);
        //creating paragraph
        Paragraph p1 = new Paragraph();
        Paragraph pNew5Lines = new Paragraph();
        for(int i=0 ; i<5;i++)
            p1.add("\n");
        //Printing Chunk Text on the pdf
        Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
        p1.add("ID        Name        Address        Gender         Date        ContactNo");
        p1.add("\n-------------------------------------------------------------------------------\n");
        font = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
        for(String s : TDBH.TherapistsPDF())
        {
            Chunk chunk = new Chunk(s, font);
            p1.add(chunk);
            p1.add("\n");
        }
        //Drawing an image from the resources folder
        Image img = Image.getInstance("src/main/resources/Images/banner.png");
        BufferedImage bimg = ImageIO.read(new File("src/main/resources/Images/banner.png"));
        new Chunk(img, 0, 0, true);
        //Get Sizes
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - 0) / img.getWidth()) * 90;
        //setting the scaler on the image for resizing the image by percentage
        img.scalePercent(scaler);
        img.setAlignment(Element.ALIGN_CENTER);
        //creating paragraph and adding the banner with text
        Paragraph preface = new Paragraph();
        preface.add(img);
        document.add(preface);
        document.add(pNew5Lines);
        document.add(p1);
        document.close();
        writer.close();

    }

    public void SavePatientPDF() throws IOException, DocumentException, SQLException {
        DBH.patientDAO PDH = new DBH.patientDAO();
        //Create PDF, Initilaize and Open
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/Files/PDF/PatientPDF.pdf"));
        document.open();
        //Adding the footer to the pdf file, by class created on the utils
        Util.FooterPageEvent footer = new FooterPageEvent();
        writer.setPageEvent(footer);
        //creating paragraph
        Paragraph p1 = new Paragraph();
        Paragraph pNew5Lines = new Paragraph();
        for (int i = 0; i < 5; i++)
            pNew5Lines.add("\n");
        //Printing Chunk Text on the pdf
        com.itextpdf.text.Font font;
        p1.add("ID        Name        Address        Gender         Date        ContactNo");
        p1.add("\n-------------------------------------------------------------------------------\n");
        font = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
        for (String s : PDH.PatientPDF()) {
            Chunk chunk = new Chunk(s, font);
            p1.add(chunk);
            p1.add("\n");
        }
        //Drawing an image from the resources folder
        com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("src/main/resources/Images/banner.png");
        com.itextpdf.text.Image chartimage = Image.getInstance("src/main/resources/Images/Temp.png");
        Paragraph ScreenShotParagraph = new Paragraph();
        ScreenShotParagraph.add(chartimage);
        new Chunk(img, 0, 0, true);
        //Get Sizes
        float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - 0) / img.getWidth()) * 90;
        float chartscaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - 0) / chartimage.getWidth()) * 90;
        //setting the scaler on the image for resizing the image by percentage
        img.scalePercent(scaler);
        img.setAlignment(Element.ALIGN_CENTER);
        chartimage.scalePercent((chartscaler));
        chartimage.setAlignment(Element.ALIGN_CENTER);
        //creating paragraph and adding the banner with text
        Paragraph preface = new Paragraph();
        preface.add(img);
        document.add(preface);
        document.add(pNew5Lines);
        document.add(p1);
        document.add(pNew5Lines);
        document.add(new Paragraph("The Following Chart Diagram Shows Meetings , Medicines , Allergies , Meals Count. \n "));
        document.add(ScreenShotParagraph);
        document.close();
        writer.close();

    }


}
