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
public class TimeTableCreator {

    /**
     * Creates a PDF for any HasAvailability Object
     * @param object to createPDF time table for
     * @param id of the object for printing error message
     * @return InputStreamResource to be returned to client.
     * @throws IOException
     */
    public static InputStreamResource createPDF(HasAvailability object, Long id) throws IOException{
        PDFCreator pdf = new PDFCreator();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        if (object!= null) {
            pdf.createPDF(stream, object);
        } else {
            pdf.createErrorPDF(stream, "Object with id " + id + " could not be found!");
        }

        stream.flush();
        byte[] pdfContent = stream.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(pdfContent);
        return new InputStreamResource(inputStream);
    }
}
