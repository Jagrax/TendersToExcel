package ar.com;

public class Tender {

    private String country;
    private String api;
    private String fileNumber;
    private String buyer;
    private String status;
    private String tenderTypeAndMechanism;
    private String tenderTitle;
    private String productDescription;
    private String qty;
    private String unit;
    private String unitReferencePrice;
    private String referencePrice;
    private String currency;
    private String tenderPublication;
    private String limitDateForOfferSubmission;
    private String openingDateOffersAllowed;
    private String awardingClosingDate;
    private String contractDuration;
    private String deliveryTerms;
    private String offeredUnitPrice;
    private String offeredTotalPrice;
    private String participant;
    private String participantCountry;
    private String offeredProductDetails;
    private String rejectionAwardReason;
    private String importVerification;
    private String observations;
    private String source;
    private String date;
    private String name;
    private String reviewedBy;

    public Tender() {
    }

    public Tender(Tender tender) {
        this.setCountry(tender.getCountry());
        this.setApi(tender.getApi());
        this.setFileNumber(tender.getFileNumber());
        this.setBuyer(tender.getBuyer());
        this.setStatus(tender.getStatus());
        this.setTenderTypeAndMechanism(tender.getTenderTypeAndMechanism());
        this.setTenderTitle(tender.getTenderTitle());
        this.setProductDescription(tender.getProductDescription());
        this.setQty(tender.getQty());
        this.setUnit(tender.getUnit());
        this.setUnitReferencePrice(tender.getUnitReferencePrice());
        this.setReferencePrice(tender.getReferencePrice());
        this.setCurrency(tender.getCurrency());
        this.setTenderPublication(tender.getTenderPublication());
        this.setLimitDateForOfferSubmission(tender.getLimitDateForOfferSubmission());
        this.setOpeningDateOffersAllowed(tender.getOpeningDateOffersAllowed());
        this.setAwardingClosingDate(tender.getAwardingClosingDate());
        this.setContractDuration(tender.getContractDuration());
        this.setDeliveryTerms(tender.getDeliveryTerms());
        this.setOfferedUnitPrice(tender.getOfferedUnitPrice());
        this.setOfferedTotalPrice(tender.getOfferedTotalPrice());
        this.setParticipant(tender.getParticipant());
        this.setParticipantCountry(tender.getParticipantCountry());
        this.setOfferedProductDetails(tender.getOfferedProductDetails());
        this.setRejectionAwardReason(tender.getRejectionAwardReason());
        this.setImportVerification(tender.getImportVerification());
        this.setObservations(tender.getObservations());
        this.setSource(tender.getSource());
        this.setDate(tender.getDate());
        this.setName(tender.getName());
        this.setReviewedBy(tender.getReviewedBy());
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTenderTypeAndMechanism() {
        return tenderTypeAndMechanism;
    }

    public void setTenderTypeAndMechanism(String tenderTypeAndMechanism) {
        this.tenderTypeAndMechanism = tenderTypeAndMechanism;
    }

    public String getTenderTitle() {
        return tenderTitle;
    }

    public void setTenderTitle(String tenderTitle) {
        this.tenderTitle = tenderTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitReferencePrice() {
        return unitReferencePrice;
    }

    public void setUnitReferencePrice(String unitReferencePrice) {
        this.unitReferencePrice = unitReferencePrice;
    }

    public String getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(String referencePrice) {
        this.referencePrice = referencePrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTenderPublication() {
        return tenderPublication;
    }

    public void setTenderPublication(String tenderPublication) {
        this.tenderPublication = tenderPublication;
    }

    public String getLimitDateForOfferSubmission() {
        return limitDateForOfferSubmission;
    }

    public void setLimitDateForOfferSubmission(String limitDateForOfferSubmission) {
        this.limitDateForOfferSubmission = limitDateForOfferSubmission;
    }

    public String getOpeningDateOffersAllowed() {
        return openingDateOffersAllowed;
    }

    public void setOpeningDateOffersAllowed(String openingDateOffersAllowed) {
        this.openingDateOffersAllowed = openingDateOffersAllowed;
    }

    public String getAwardingClosingDate() {
        return awardingClosingDate;
    }

    public void setAwardingClosingDate(String awardingClosingDate) {
        this.awardingClosingDate = awardingClosingDate;
    }

    public String getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(String contractDuration) {
        this.contractDuration = contractDuration;
    }

    public String getDeliveryTerms() {
        return deliveryTerms;
    }

    public void setDeliveryTerms(String deliveryTerms) {
        this.deliveryTerms = deliveryTerms;
    }

    public String getOfferedUnitPrice() {
        return offeredUnitPrice;
    }

    public void setOfferedUnitPrice(String offeredUnitPrice) {
        this.offeredUnitPrice = offeredUnitPrice;
    }

    public String getOfferedTotalPrice() {
        return offeredTotalPrice;
    }

    public void setOfferedTotalPrice(String offeredTotalPrice) {
        this.offeredTotalPrice = offeredTotalPrice;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getParticipantCountry() {
        return participantCountry;
    }

    public void setParticipantCountry(String participantCountry) {
        this.participantCountry = participantCountry;
    }

    public String getOfferedProductDetails() {
        return offeredProductDetails;
    }

    public void setOfferedProductDetails(String offeredProductDetails) {
        this.offeredProductDetails = offeredProductDetails;
    }

    public String getRejectionAwardReason() {
        return rejectionAwardReason;
    }

    public void setRejectionAwardReason(String rejectionAwardReason) {
        this.rejectionAwardReason = rejectionAwardReason;
    }

    public String getImportVerification() {
        return importVerification;
    }

    public void setImportVerification(String importVerification) {
        this.importVerification = importVerification;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    @Override
    public String toString() {
        return "Tender [" +
                "country=" + country +
                ", api=" + api +
                ", fileNumber=" + fileNumber +
                ", buyer=" + buyer +
                ", status=" + status +
                ", tenderTypeAndMechanism=" + tenderTypeAndMechanism +
                ", tenderTitle=" + tenderTitle +
                ", productDescription=" + productDescription +
                ", qty=" + qty +
                ", unit=" + unit +
                ", unitReferencePrice=" + unitReferencePrice +
                ", referencePrice=" + referencePrice +
                ", currency=" + currency +
                ", tenderPublication=" + tenderPublication +
                ", limitDateForOfferSubmission=" + limitDateForOfferSubmission +
                ", openingDateOffersAllowed=" + openingDateOffersAllowed +
                ", awardingClosingDate=" + awardingClosingDate +
                ", contractDuration=" + contractDuration +
                ", deliveryTerms=" + deliveryTerms +
                ", offeredUnitPrice=" + offeredUnitPrice +
                ", offeredTotalPrice=" + offeredTotalPrice +
                ", participant=" + participant +
                ", participantCountry=" + participantCountry +
                ", offeredProductDetails=" + offeredProductDetails +
                ", rejectionAwardReason=" + rejectionAwardReason +
                ", importVerification=" + importVerification +
                ", observations=" + observations +
                ", source=" + source +
                ", date=" + date +
                ", name=" + name +
                ", reviewedBy=" + reviewedBy +
                ']';
    }
}
