package com.example.cat304project;

public class CarpoolOffer {
    String startpoint;
    String endpoint;
    String offerdate;
    String offertime;
    String offerfares;
    String offergender;

    public CarpoolOffer(String startpoint, String endpoint, String offerdate,
                        String offertime, String offerfares, String offergender) {
        this.startpoint = startpoint;
        this.endpoint = endpoint;
        this.offerdate = offerdate;
        this.offertime = offertime;
        this.offerfares = offerfares;
        this.offergender = offergender;
    }

    public String getStartpoint() {
        return startpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getOfferdate() {
        return offerdate;
    }

    public String getOffertime() {
        return offertime;
    }

    public String getOfferfares() {
        return offerfares;
    }

    public String getOffergender() {
        return offergender;
    }
}
