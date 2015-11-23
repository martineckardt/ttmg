package de.nak.ttmg.pdf;

import de.nak.ttmg.model.HasAvailability;
import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by felixb on 30/10/15.
 * Creates a TimeTable for any HasAvailability Object
 */
public class PDFCreator {

    /**
     * Creates a PDF for any HasAvailability Object
     * @param object to createPDF time table for
     * @param id of the object for printing error message
     * @return InputStreamResource to be returned to client.
     * @throws IOException
     */
    public static InputStreamResource createPDF(HasAvailability object, String type, Long id) throws IOException{
        TimeTableCreator pdf = new TimeTableCreator();
        //Using an Output stream enables creation of the pdf in ram
        //without saving a document to a temp. folder on disk.
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        if (object!= null) {
            pdf.createPDF(stream, object);
        } else {
            //if an error occurred (e.g. object not found) create an error object
            pdf.createErrorPDF(stream, type + " with id " + id + " could not be found!");
        }

        stream.flush();
        byte[] pdfContent = stream.toByteArray();
        //Create an inputstream with the data of the pdf
        InputStream inputStream = new ByteArrayInputStream(pdfContent);
        return new InputStreamResource(inputStream);
    }
}
