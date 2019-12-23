package ar.com;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HtmlUtil {

    public static String getElementById(Document htmlDocument, String elemId) {
        Element element = htmlDocument.getElementById(elemId);
        return element != null ? element.text() : "404 - Not found";
    }

    public static String getElementByIdAsDate(Document htmlDocument, String elementId, String pageFormat, String toFormat) {
        Element element = htmlDocument.getElementById(elementId);
        if (element != null) {
            try {
                Date date = new SimpleDateFormat(pageFormat).parse(element.text());
                return new SimpleDateFormat(toFormat).format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return element.text();
            }
        }

        return "404 - Not found";
    }
}
