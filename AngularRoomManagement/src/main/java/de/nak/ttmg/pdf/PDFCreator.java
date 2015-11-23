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
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        if (object!= null) {
            pdf.createPDF(stream, object);
        } else {
            pdf.createErrorPDF(stream, type + " with id " + id + " could not be found!");
        }

        stream.flush();
        byte[] pdfContent = stream.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(pdfContent);
        return new InputStreamResource(inputStream);
    }
}
