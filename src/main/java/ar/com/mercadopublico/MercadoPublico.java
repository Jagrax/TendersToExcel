package ar.com.mercadopublico;

import ar.com.HtmlUtil;
import ar.com.Tender;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MercadoPublico {

    private final static String pageFormat = "dd-MM-yyyy hh:mm:ss";

    public List<Tender> process(String link, Document htmlDocument, String excelFormat) throws IOException {
        Tender tender = initializeTender(link, htmlDocument, excelFormat);

        boolean hasOffers = false;
        // Me fijo si tiene habilidato el Cuadro de Ofertas
        Element imgCuadroDeOfertas = htmlDocument.getElementById("imgCuadroOferta");
        if (imgCuadroDeOfertas != null && !imgCuadroDeOfertas.hasAttr("disabled")) {
            hasOffers = true;
        }

        return generateTenders(tender, htmlDocument, hasOffers);
    }

    private Tender initializeTender(String source, Document htmlDocument, String excelFormat) {
        Tender tender = new Tender();
        tender.setCountry("Chile");
        tender.setApi("");
        tender.setFileNumber(HtmlUtil.getElementById(htmlDocument, "lblNumLicitacion"));
        tender.setBuyer(HtmlUtil.getElementById(htmlDocument, "lblResponsable"));
        tender.setStatus(decodeStatus(HtmlUtil.getElementById(htmlDocument, "lblFicha1Estado")));
        tender.setTenderTypeAndMechanism(decodeTenderTypeAndMechanism(HtmlUtil.getElementById(htmlDocument, "lblFicha1Convocatoria") + "/" + HtmlUtil.getElementById(htmlDocument, "lblFicha1Tipo") + "/" + HtmlUtil.getElementById(htmlDocument, "lblFicha1Etapas")));
        tender.setTenderTitle(HtmlUtil.getElementById(htmlDocument, "lblNombreLicitacion"));
        tender.setUnitReferencePrice("0");
        tender.setReferencePrice("0");
        tender.setCurrency(HtmlUtil.getElementById(htmlDocument, "lblFicha1Moneda"));
        tender.setTenderPublication(HtmlUtil.getElementByIdAsDate(htmlDocument, "lblFicha3Publicacion", pageFormat, excelFormat));
        tender.setLimitDateForOfferSubmission(HtmlUtil.getElementByIdAsDate(htmlDocument, "lblFicha3Cierre", pageFormat, excelFormat));
        tender.setOpeningDateOffersAllowed(HtmlUtil.getElementByIdAsDate(htmlDocument, "lblFicha3ActoAperturaTecnica", pageFormat, excelFormat));
        tender.setAwardingClosingDate(HtmlUtil.getElementByIdAsDate(htmlDocument, "lblFicha3Adjudicacion", pageFormat, excelFormat));
        tender.setContractDuration(HtmlUtil.getElementById(htmlDocument, "lblFicha7TiempoContrato").replace("Meses", "months"));
        tender.setImportVerification("N/A");
        tender.setSource(source);
        tender.setDate(new SimpleDateFormat("dd-MMM-yyyy").format(new Date()));
        tender.setName("Dana");
        return tender;
    }

    private List<Tender> generateTenders(Tender baseTender, Document htmlDocument, boolean hasOffers) throws IOException {
        List<Tender> tenders = new ArrayList<>();
        List<Product> products = findProducts(htmlDocument, hasOffers);
        for (Product product : products) {
            if (hasOffers) {
                for (Offer offer : product.getOffers()) {
                    Tender tender = new Tender(baseTender);
                    tender.setProductDescription(product.getDescripcion());
                    tender.setQty(product.getCantidad());
                    tender.setUnit(product.getUnidad());
                    tender.setOfferedUnitPrice(offer.getPrecioUnitario());
                    tender.setOfferedTotalPrice(offer.getMonto());
                    tender.setParticipant(offer.getProveedor());
                    tender.setOfferedProductDetails(offer.getEspecificacionesDelProveedor());
                    tenders.add(tender);
                }
            } else {
                Tender tender = new Tender(baseTender);
                tender.setProductDescription(product.getDescripcion());
                tender.setQty(product.getCantidad());
                tender.setUnit(product.getUnidad());
                tender.setOfferedUnitPrice("0.00");
                tender.setOfferedTotalPrice("0.00");
                tender.setParticipant("N/A");
                tender.setOfferedProductDetails("N/A");
                tenders.add(tender);
            }
        }
        return tenders;
    }

    private Map<String, List<Offer>> clasifyOffers(List<Offer> offers) {
        Map<String, List<Offer>> clasifiedOffers = new HashMap<>();
        for (Offer offer : offers) {
            List<Offer> clasifiedOffersList = clasifiedOffers.computeIfAbsent(offer.getId(), k -> new ArrayList<>());
            clasifiedOffersList.add(offer);
        }

        return clasifiedOffers;
    }

    private List<Product> findProducts(Document htmlDocument, boolean hasOffers) throws IOException {
        Map<String, List<Offer>> clasifiedOffers = null;
        if (hasOffers) {
            clasifiedOffers = clasifyOffers(findOffers(htmlDocument));
        }
        List<Product> productsOrServices = new ArrayList<>();
        boolean continueLooping = true;
        for (int n = 2; continueLooping; n++) {
            String count = n < 10 ? "0" + n : String.valueOf(n);
            if (htmlDocument.getElementById( "grvProducto_ctl" + count + "_lblDescripcion") == null) {
                continueLooping = false;
            } else {
                Product product = new Product();
                product.setName(HtmlUtil.getElementById(htmlDocument, "grvProducto_ctl" + count + "_lblProducto"));
                product.setCantidad(HtmlUtil.getElementById(htmlDocument, "grvProducto_ctl" + count + "_lblCantidad"));
                product.setUnidad(HtmlUtil.getElementById(htmlDocument, "grvProducto_ctl" + count + "_lblUnidad"));
                product.setCategoria(HtmlUtil.getElementById(htmlDocument, "grvProducto_ctl" + count + "_lblCategoria"));
                product.setDescripcion(HtmlUtil.getElementById(htmlDocument, "grvProducto_ctl" + count + "_lblDescripcion"));
                if (hasOffers) {
                    product.setOffers(clasifiedOffers.get(product.getDescripcion()));
                }
                productsOrServices.add(product);
            }
        }

        return productsOrServices;
    }

    private List<Offer> findOffers(Document htmlDocument) throws IOException {
        List<Offer> offers = new ArrayList<>();
        String baseUrl = "http://www.mercadopublico.cl";
        String cuadroOfertaUrl = baseUrl + htmlDocument.getElementById("imgCuadroOferta").attr("href");
        Document cuadroDeOfertas = Jsoup.parse(Jsoup.connect(cuadroOfertaUrl).execute().body());
        if (cuadroDeOfertas != null) {
            Element frameEncabezado = cuadroDeOfertas.getElementById("Encabezado");
            if (frameEncabezado != null) {
                String frameEncabezadoSrc = frameEncabezado.attr("src");
                String urlEncabezado = cuadroOfertaUrl.substring(0, cuadroOfertaUrl.lastIndexOf("/") + 1) + frameEncabezadoSrc;
                Document encabezado = Jsoup.parse(Jsoup.connect(urlEncabezado).execute().body());
                if (encabezado != null) {
                    Element btnCuadroLinea = encabezado.getElementById("BtnCuadroLinea");
                    if (btnCuadroLinea != null) {
                        String cuadroComparativoPorLineaUrl = btnCuadroLinea.attr("onclick");
                        if (cuadroComparativoPorLineaUrl.startsWith("parent.Cuerpo.location='../../../..")) {
                            cuadroComparativoPorLineaUrl = cuadroComparativoPorLineaUrl.replace("parent.Cuerpo.location='../../../..", "");
                            cuadroComparativoPorLineaUrl = cuadroComparativoPorLineaUrl.replace("';return false;", "");
                            Document cuadroComparativoPorLineaHtmlDocument = Jsoup.parse(Jsoup.connect(baseUrl + cuadroComparativoPorLineaUrl).execute().body());
                            if (cuadroComparativoPorLineaHtmlDocument != null) {
                                boolean continueLooping = true;
                                for (int n = 2; continueLooping; n++) {
                                    String count = n < 10 ? "0" + n : String.valueOf(n);
                                    if (cuadroComparativoPorLineaHtmlDocument.getElementById("dgRFB_ctl" + count + "_lblSpec") == null) {
                                        continueLooping = false;
                                    } else {
                                        Element elemlblSpec = cuadroComparativoPorLineaHtmlDocument.getElementById("dgRFB_ctl" + count + "_lblSpec");
                                        String spec = elemlblSpec.text();
                                        Element tbody = elemlblSpec.parent().parent().parent();
                                        Elements allTr = tbody.getElementsByTag("tr");
                                        boolean tableTitlesFound = false;
                                        for (Element tr : allTr) {
                                            if (tr.child(0).text().equals("Proveedor")) {
                                                tableTitlesFound = true;
                                                continue;
                                            }

                                            if (!tableTitlesFound) continue;

                                            Elements tds = tr.getElementsByTag("td");
                                            Offer oferta = new Offer();
                                            oferta.setId(spec);
                                            oferta.setProveedor(tds.get(0).text());
                                            oferta.setNombreDeLaOferta(tds.get(1).text());
                                            oferta.setEspecificacionesDelProveedor(tds.get(2).text());
                                            oferta.setCantidadOfertada(tds.get(3).text());
                                            oferta.setPrecioUnitario(tds.get(4).text());
                                            oferta.setMoneda(tds.get(5).text());
                                            oferta.setMonto(tds.get(6).text());

                                            offers.add(oferta);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return offers;
    }

    private String decodeStatus(String undecodedStatus) {
        String status;
        if (undecodedStatus.startsWith("Cerrada")) {
            status = "Closed";
        } else if (undecodedStatus.startsWith("Adjudicada")) {
            status = "Awarded";
        } else if (undecodedStatus.startsWith("Publicada")) {// Consulta a Dana que texto mapea a Published
            status = "Published";
        } else if (undecodedStatus.startsWith("Desierta")) {
            status = "Vacant";
        } else {
            status = undecodedStatus;
        }

        return status;
    }

    private String decodeTenderTypeAndMechanism(String undecodedTenderTypeAndMechanism) {
        String tenderTypeAndMechanism = undecodedTenderTypeAndMechanism;
        String[] splitedUndecodedTenderTypeAndMechanism = undecodedTenderTypeAndMechanism.split("[/]");
        if (splitedUndecodedTenderTypeAndMechanism[1].startsWith("Pública-Licitación Pública igual o superior a ")) {
            String secondoPart = splitedUndecodedTenderTypeAndMechanism[1].substring("Pública-Licitación Pública igual o superior a ".length());
            secondoPart = secondoPart.replace("e inferior a", "and lower than");
            tenderTypeAndMechanism = "Public open tender equal to or greater than " + secondoPart + ".";
        } else if (splitedUndecodedTenderTypeAndMechanism[1].startsWith("Pública-Licitación Pública inferior a ")) {
            tenderTypeAndMechanism = "Public open tender lower than " + splitedUndecodedTenderTypeAndMechanism[1].substring("Pública-Licitación Pública inferior a ".length()) + ".";
        }

        if (splitedUndecodedTenderTypeAndMechanism[0].equals("ABIERTO")) {
            tenderTypeAndMechanism += " Opening process stages: ";
            if (splitedUndecodedTenderTypeAndMechanism[2].equals("Una Etapa")) {
                tenderTypeAndMechanism += "One.";
            } else if (splitedUndecodedTenderTypeAndMechanism[2].equals("Dos Etapas")) {
                tenderTypeAndMechanism += "Two.";
            }
        }

        return tenderTypeAndMechanism;
    }
}
