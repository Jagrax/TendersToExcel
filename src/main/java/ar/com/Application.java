package ar.com;

import ar.com.mercadopublico.MercadoPublico;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    private static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private final static String excelFormat = "dd-MMM-yyyy";
    private final static String[] columns = {"Country", "API", "File number", "Buyer", "Status", "Tender type and mechanism", "Tender title", "Product description", "Qty", "Unit", "Unit reference price", "Reference price", "Currency", "Tender publication", "Limit date for offer submission", "Opening date (offers allowed)", "Awarding/Closing date", "Contract duration", "Delivery terms", "Offered unit price", "Offered total price", "Participant", "Participant Country", "Offered product details", "Rejection/ Award reason", "Import verification", "Observations", "Source", "Date", "Name", "Reviewed by"};
    private final static String linkFilePath = "C:\\Licitaciones\\links.txt";
    private final static String xlsxFileName = "C:\\Licitaciones\\" + new SimpleDateFormat("hhmmss-dd-MM-yyyy").format(new Date()) + "-For Dana with love.xlsx";
    private final static String CL_MERCADOPUBLICO = "www.mercadopublico.cl";

    public static void main(String[] args) throws Exception {
        // Leo y obtengo las URLs del archivo
        logOut("Leyendo el archivo de links...");
        List<String> links = Files.lines(Paths.get(linkFilePath)).collect(Collectors.toList());
        logOut("Archivo de links leido correctamente (cant. de lineas: " + links.size() + ")");
        // Si hay urls
        if (!links.isEmpty()) {
            List<Tender> tenders = new ArrayList<>();
            String[] schemes = {"http", "https"};
            UrlValidator urlValidator = new UrlValidator(schemes);
            logOut("Validando URLs...");
            for (String link : links) {
                if (urlValidator.isValid(link)) {
                    logOut("OK......" + link);
                    logOut("Intentando obtener el documento HTML...");
                    Connection.Response execute = Jsoup.connect(link).execute();
                    Document htmlDocument = Jsoup.parse(execute.body());

                    if (htmlDocument != null) {
                        logOut("El documento HTML se obtuvo correctamente. Procedo a procesar su informacion...");
                        if (link.contains(CL_MERCADOPUBLICO)) {
                            tenders.addAll(new MercadoPublico().process(link, htmlDocument, excelFormat));
                        }
                        logOut("Procesamiento ok");
                    } else {
                        logOut("No se pudo obtener el HTML de la pagina: " + link);
                    }
                } else {
                    logOut("ERROR..." + link);
                    logOut("No se procesara esta url");
                }
            }

            logOut("La informacion de todas las URLs fue procesada, se generaron " + tenders.size() + " tenders. Procedo a crear el excel...");
            if (!tenders.isEmpty()) {
                Workbook tenderTracking = new XSSFWorkbook();
                CreationHelper creationHelper = tenderTracking.getCreationHelper();
                Sheet movil = tenderTracking.createSheet("MOVIL");

                Font headerFont = tenderTracking.createFont();
                headerFont.setFontName("Calibri");
                headerFont.setFontHeightInPoints((short) 11);
                headerFont.setColor(IndexedColors.WHITE.getIndex());

                // Create a CellStyle with the font
                CellStyle titleCellStyle = tenderTracking.createCellStyle();
                titleCellStyle.setFont(headerFont);
                titleCellStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
                titleCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                Row titles = movil.createRow(0);
                for (int i = 0; i < columns.length; i++) {
                    Cell cell = titles.createCell(i);
                    cell.setCellValue(columns[i]);
                    cell.setCellStyle(titleCellStyle);
                }

                CellStyle dateCellStyle = tenderTracking.createCellStyle();
                dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(excelFormat));

                int rowNum = 1;
                for (Tender licitacion : tenders) {
                    Row row = movil.createRow(rowNum++);
                    row.createCell(0).setCellValue(licitacion.getCountry());
                    row.createCell(1).setCellValue(licitacion.getApi());
                    row.createCell(2).setCellValue(licitacion.getFileNumber());
                    row.createCell(3).setCellValue(licitacion.getBuyer());
                    row.createCell(4).setCellValue(licitacion.getStatus());
                    row.createCell(5).setCellValue(licitacion.getTenderTypeAndMechanism());
                    row.createCell(6).setCellValue(licitacion.getTenderTitle());
                    row.createCell(7).setCellValue(licitacion.getProductDescription());
                    row.createCell(8).setCellValue(licitacion.getQty());
                    row.createCell(9).setCellValue(licitacion.getUnit());
                    row.createCell(10).setCellValue(licitacion.getUnitReferencePrice());
                    row.createCell(11).setCellValue(licitacion.getReferencePrice());
                    row.createCell(12).setCellValue(licitacion.getCountry());
                    Cell tenderPublication = row.createCell(13);
                    tenderPublication.setCellValue(licitacion.getTenderPublication());
                    tenderPublication.setCellStyle(dateCellStyle);
                    Cell limitDateForOfferSubmission = row.createCell(14);
                    limitDateForOfferSubmission.setCellValue(licitacion.getLimitDateForOfferSubmission());
                    limitDateForOfferSubmission.setCellStyle(dateCellStyle);
                    Cell openingDateOffersAllowed = row.createCell(15);
                    openingDateOffersAllowed.setCellValue(licitacion.getOpeningDateOffersAllowed());
                    openingDateOffersAllowed.setCellStyle(dateCellStyle);
                    Cell awardingClosingDate = row.createCell(16);
                    awardingClosingDate.setCellValue(licitacion.getAwardingClosingDate());
                    awardingClosingDate.setCellStyle(dateCellStyle);
                    row.createCell(17).setCellValue(licitacion.getContractDuration());
                    row.createCell(18).setCellValue(licitacion.getDeliveryTerms());
                    row.createCell(19).setCellValue(licitacion.getOfferedUnitPrice());
                    row.createCell(20).setCellValue(licitacion.getOfferedTotalPrice());
                    row.createCell(21).setCellValue(licitacion.getParticipant());
                    row.createCell(22).setCellValue(licitacion.getParticipantCountry());
                    row.createCell(23).setCellValue(licitacion.getOfferedProductDetails());
                    row.createCell(24).setCellValue(licitacion.getRejectionAwardReason());
                    row.createCell(25).setCellValue(licitacion.getImportVerification());
                    row.createCell(26).setCellValue(licitacion.getObservations());
                    row.createCell(27).setCellValue(licitacion.getSource());
                    row.createCell(28).setCellValue(licitacion.getDate());
                    row.createCell(29).setCellValue(licitacion.getName());
                    row.createCell(30).setCellValue(licitacion.getReviewedBy());
                }

                for(int i = 0; i < columns.length; i++) {
                    movil.autoSizeColumn(i);
                }

                // Write the output to a file
                FileOutputStream fileOut = new FileOutputStream(xlsxFileName);
                tenderTracking.write(fileOut);
                fileOut.close();

                // Closing the workbook
                tenderTracking.close();
                logOut("Excel creado correctamente: " + xlsxFileName);
            } else {
                logOut("El archivo no contiene lineas");
            }

            logOut("Proceso finalizado!");
        }
    }

    private static void logOut(String msg) {
        System.out.println(sdf.format(new Date()) + " [Html2Xlsx - MercadoPublic.cl] " + msg);
    }
}
