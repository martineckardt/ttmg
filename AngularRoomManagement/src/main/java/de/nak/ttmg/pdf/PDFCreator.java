package de.nak.ttmg.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.nak.ttmg.model.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Set;

/**
 * Created by felixb on 29/10/15.
 */
public class PDFCreator {

    private static Font.FontFamily fontFamily = Font.FontFamily.TIMES_ROMAN;

    public void createPDF (OutputStream stream, HasAvailability object){

        Document doc = new Document();
        PdfWriter docWriter = null;

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            //special font sizes
            Font bfBold12 = new Font(fontFamily, 12, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(fontFamily, 12);
            Font bf20 = new Font(fontFamily, 20);

            docWriter = PdfWriter.getInstance(doc , stream);

            //document header attributes
            doc.addAuthor("TimeTable Management");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("Behrendt, Eckardt, Kampe");
            doc.addTitle("Time Table for " + object.getObjectType());
            doc.setPageSize(PageSize.A4);

            //open document
            doc.open();


            Paragraph header = new Paragraph("Time Table for " + object.getObjectType() + " " + object.getReadableString(), bf20);
            doc.add(header);

            //specify column widths
            float[] columnWidths = {1.5f, 2f, 5f, 3f, 2f};
            //create PDF table with the given widths
            PdfPTable table = new PdfPTable(columnWidths);
            // set table width a percentage of the page width
            table.setWidthPercentage(90f);

            //insert column headings
            insertCell(table, "Course Name", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Centurias", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Rooms", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Tutor", Element.ALIGN_CENTER, 1, bfBold12);
            insertCell(table, "Events", Element.ALIGN_CENTER, 1, bfBold12);
            table.setHeaderRows(1);

            //just some random data to fill
            for(Course course : object.getCourses()){
                insertCell(table, course.getName(), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, convertObjectToString(course.getParticipants()), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, convertObjectToString(course.getRooms()), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, course.getTutor().getReadableString(), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, convertObjectToString(course.getEvents()), Element.ALIGN_LEFT, 1, bf12);
            }
            if (object.getCourses().isEmpty()) {
                Paragraph emptyMessage = new Paragraph("This " + object.getObjectType() + " has no courses.");
                doc.add(emptyMessage);
            } else {
                //add the PDF table to the paragraph
                Paragraph paragraph = new Paragraph(" "); // Adds linebreak
                paragraph.add(table);
                doc.add(paragraph);
            }
        }
        catch (DocumentException dex)
        {
            dex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (doc != null){
                //close the document
                doc.close();
            }
            if (docWriter != null){
                //close the writer
                docWriter.close();
            }
        }
    }

    public void createErrorPDF(OutputStream stream, String message) {
        Document doc = new Document();
        PdfWriter docWriter = null;
        try {
            Font bfBold12 = new Font(fontFamily, 20, Font.BOLD, new BaseColor(255, 0, 0));
            Font bf12 = new Font(fontFamily, 12);
            docWriter = PdfWriter.getInstance(doc, stream);

            doc.addAuthor("TimeTable Management");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("Behrendt, Eckardt, Kampe");
            doc.addTitle("Error Report");
            doc.setPageSize(PageSize.A4);

            doc.open();
            Paragraph header = new Paragraph("Error", bfBold12);
            doc.add(header);
            Paragraph paragraph = new Paragraph(message);
            doc.add(paragraph);
        }catch (DocumentException dex)
        {
            dex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (doc != null){
                doc.close();
            }
            if (docWriter != null){
                docWriter.close();
            }
        }

    }

    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);

    }

    private String convertObjectToString(Set<? extends HasReadableString> objects) {
        StringBuilder sb = new StringBuilder();
        for (HasReadableString object: objects) {
            sb.append(object.getReadableString());
            sb.append(", ");
        }
        String string = sb.toString();
        string = string.substring(0, string.length()-2);
        return string;
    }
}
