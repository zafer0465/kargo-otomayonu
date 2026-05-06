package Model;

public abstract class Kisi {


    protected String ad;
    protected String soyad;
    protected String telefon;

    public Kisi(String ad, String soyad, String telefon) {

        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getAdSoyad() {
        return ad + " " + soyad;
    }

    public abstract String getRol();
}
