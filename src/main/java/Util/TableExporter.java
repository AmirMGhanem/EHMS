package Util;

import DBH.workScheduleDAO;
import Model.WorkSchedule;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.Chunk;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableExporter {

    public void TableExport() throws FileNotFoundException, SQLException, MalformedURLException {
        DBH.workScheduleDAO wsdao = new workScheduleDAO();
        ArrayList<WorkSchedule> workScheduleArrayList = new ArrayList<WorkSchedule>();
        workScheduleArrayList = wsdao.SelectAllWS();
        // Creating a PdfDocument object
        String dest = "src/main/resources/Files/PDF/TableExport.pdf";
        PdfWriter writer = new PdfWriter(dest);
        // Creating a PdfDocument object
        PdfDocument pdf = new PdfDocument(writer);
        // Creating a Document object
        Document doc = new Document(pdf);
        float[] pointColWidth = {50F, 50F, 50F, 60F, 70F, 60F, 60F, 60F};
        com.itextpdf.layout.element.Table table = new com.itextpdf.layout.element.Table(pointColWidth);
        table.setStrokeColor(Color.RED);
        StringBuilder sunday1 = new StringBuilder();
        StringBuilder monday1 = new StringBuilder();
        StringBuilder tuesday1 = new StringBuilder();
        StringBuilder wednesday1 = new StringBuilder();
        StringBuilder thursday1 = new StringBuilder();
        StringBuilder friday1 = new StringBuilder();
        StringBuilder saturday1 = new StringBuilder();
        for (WorkSchedule ws : workScheduleArrayList) {
            if (ws.getShift() == 1 && ws.getDay().equalsIgnoreCase("sunday"))
                sunday1.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 1 && ws.getDay().equalsIgnoreCase("monday"))
                monday1.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 1 && ws.getDay().equalsIgnoreCase("tuesday"))
                tuesday1.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 1 && ws.getDay().equalsIgnoreCase("wednesday"))
                wednesday1.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 1 && ws.getDay().equalsIgnoreCase("thursday"))
                thursday1.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 1 && ws.getDay().equalsIgnoreCase("friday"))
                friday1.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 1 && ws.getDay().equalsIgnoreCase("saturday"))
                saturday1.append(ws.getTherapist().getFirstName() + "\n");
        }
        String imageFile = "src/main/resources/Images/dark.png";
        ImageData data = ImageDataFactory.create(imageFile);
        String imageFile2 = "src/main/resources/Images/lightmode.png";
        ImageData data2 = ImageDataFactory.create(imageFile2);
        // Creating the image
        com.itextpdf.layout.element.Image img = new com.itextpdf.layout.element.Image(data);
        com.itextpdf.layout.element.Image img2 = new com.itextpdf.layout.element.Image(data2);
        // Adding image to the cell10
        table.addCell(new Cell().add("Shift").setBold().add(img.setWidth(20).setHeight(20)).add(img2.setWidth(20).setHeight(20)));
        table.addCell(new Cell().add("Sunday").setBold().setBackgroundColor(Color.DARK_GRAY).setFontColor(Color.WHITE));
        table.addCell(new Cell().add("Monday").setBold().setBackgroundColor(Color.DARK_GRAY).setFontColor(Color.WHITE));
        table.addCell(new Cell().add("Tuesday").setBold().setBackgroundColor(Color.DARK_GRAY).setFontColor(Color.WHITE));
        table.addCell(new Cell().add("Wednesday").setBold().setBackgroundColor(Color.DARK_GRAY).setFontColor(Color.WHITE));
        table.addCell(new Cell().add("Thursday").setBold().setBackgroundColor(Color.DARK_GRAY).setFontColor(Color.WHITE));
        table.addCell(new Cell().add("Friday").setBold().setBackgroundColor(Color.DARK_GRAY).setFontColor(Color.WHITE));
        table.addCell(new Cell().add("Saturday").setBold().setBackgroundColor(Color.DARK_GRAY).setFontColor(Color.WHITE));
        Cell morningc1 = new Cell();
        Cell morningc2 = new Cell();
        Cell morningc3 = new Cell();
        Cell morningc4 = new Cell();
        Cell morningc5 = new Cell();
        Cell morningc6 = new Cell();
        Cell morningc7 = new Cell();
        table.addCell(new Cell().add("Morning").setBackgroundColor(Color.GRAY));
        table.addCell(morningc1.add(sunday1.toString()));
        table.addCell(morningc2.add(monday1.toString()));
        table.addCell(morningc3.add(tuesday1.toString()));
        table.addCell(morningc4.add(wednesday1.toString()));
        table.addCell(morningc5.add(thursday1.toString()));
        table.addCell(morningc6.add(friday1.toString()));
        table.addCell(morningc7.add(saturday1.toString()));
        //---------------------------
        StringBuilder sunday2 = new StringBuilder();
        StringBuilder monday2 = new StringBuilder();
        StringBuilder tuesday2 = new StringBuilder();
        StringBuilder wednesday2 = new StringBuilder();
        StringBuilder thursday2 = new StringBuilder();
        StringBuilder friday2 = new StringBuilder();
        StringBuilder saturday2 = new StringBuilder();
        for (WorkSchedule ws : workScheduleArrayList) {
            if (ws.getShift() == 2 && ws.getDay().equalsIgnoreCase("sunday"))
                sunday2.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 2 && ws.getDay().equalsIgnoreCase("monday"))
                monday2.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 2 && ws.getDay().equalsIgnoreCase("tuesday"))
                tuesday2.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 2 && ws.getDay().equalsIgnoreCase("wednesday"))
                wednesday2.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 2 && ws.getDay().equalsIgnoreCase("thursday"))
                thursday2.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 2 && ws.getDay().equalsIgnoreCase("friday"))
                friday2.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 2 && ws.getDay().equalsIgnoreCase("saturday"))
                saturday2.append(ws.getTherapist().getFirstName() + "\n");
        }
        Cell noonc1 = new Cell();
        Cell noonc2 = new Cell();
        Cell noonc3 = new Cell();
        Cell noonc4 = new Cell();
        Cell noonc5 = new Cell();
        Cell noonc6 = new Cell();
        Cell noonc7 = new Cell();
        table.addCell(new Cell().add("Noon").setBackgroundColor(Color.GRAY));
        table.addCell(noonc1.add(sunday2.toString()));
        table.addCell(noonc2.add(monday2.toString()));
        table.addCell(noonc3.add(tuesday2.toString()));
        table.addCell(noonc4.add(wednesday2.toString()));
        table.addCell(noonc5.add(thursday2.toString()));
        table.addCell(noonc6.add(friday2.toString()));
        table.addCell(noonc7.add(saturday2.toString()));
        //-------------------------
        StringBuilder sunday3 = new StringBuilder();
        StringBuilder monday3 = new StringBuilder();
        StringBuilder tuesday3 = new StringBuilder();
        StringBuilder wednesday3 = new StringBuilder();
        StringBuilder thursday3 = new StringBuilder();
        StringBuilder friday3 = new StringBuilder();
        StringBuilder saturday3 = new StringBuilder();
        for (WorkSchedule ws : workScheduleArrayList) {
            if (ws.getShift() == 3 && ws.getDay().equalsIgnoreCase("sunday"))
                sunday3.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 3 && ws.getDay().equalsIgnoreCase("monday"))
                monday3.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 3 && ws.getDay().equalsIgnoreCase("tuesday"))
                tuesday3.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 3 && ws.getDay().equalsIgnoreCase("wednesday"))
                wednesday3.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 3 && ws.getDay().equalsIgnoreCase("thursday"))
                thursday3.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 3 && ws.getDay().equalsIgnoreCase("friday"))
                friday3.append(ws.getTherapist().getFirstName() + "\n");
            if (ws.getShift() == 3 && ws.getDay().equalsIgnoreCase("saturday"))
                saturday3.append(ws.getTherapist().getFirstName() + "\n");
        }
        Cell nightc1 = new Cell();
        Cell nightc2 = new Cell();
        Cell nightc3 = new Cell();
        Cell nightc4 = new Cell();
        Cell nightc5 = new Cell();
        Cell nightc6 = new Cell();
        Cell nightc7 = new Cell();
        table.addCell(new Cell().add("Night").setBackgroundColor(Color.GRAY));
        table.addCell(nightc1.add(sunday3.toString()));
        table.addCell(nightc2.add(monday3.toString()));
        table.addCell(nightc3.add(tuesday3.toString()));
        table.addCell(nightc4.add(wednesday3.toString()));
        table.addCell(nightc5.add(thursday3.toString()));
        table.addCell(nightc6.add(friday3.toString()));
        table.addCell(nightc7.add(saturday3.toString()));
        //Adding header inside paragraph
        Paragraph p = new Paragraph();
        Chunk c = new Chunk("Nursing Working Schedule");
        p.add("Nursing Working Schedule");
        p.setFontSize(25);
        p.setFontColor(Color.BLUE);
        // Adding Table to document
        doc.add(p);
        doc.add(table);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.setVerticalAlignment(VerticalAlignment.MIDDLE);
        //table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // Closing the document
        doc.close();
    }


}
