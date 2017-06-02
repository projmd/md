package example.tryrssparse.DataObject;

/**
 * Created by redhwan on 2/8/2017.
 */


public  class New {
    private String ntitle="";
    private String ndetails="";
    private String nUrl="";
    private String niconUrl="";
    private String pubDate="";


//
//    public New(String title, String details, String url, String iconUrl) {
//        ntitle = title;
//        ndetails = details;
//        nUrl = url;
//        niconUrl = iconUrl;
//    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNdetails() {
        return ndetails;
    }

    public void setNdetails(String ndetails) {
        this.ndetails = ndetails;
    }

    public String getnUrl() {
        return nUrl;
    }

    public void setnUrl(String nUrl) {
        this.nUrl = nUrl;
    }

    public String getNiconUrl() {
        return niconUrl;
    }

    public void setNiconUrl(String niconUrl) {
        this.niconUrl = niconUrl;
    }



}

