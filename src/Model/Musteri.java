package Model;

public class Musteri extends Kisi {

    private Adres adres;

    public Musteri(String ad, String soyad, String telefon, Adres adres) {
        super( ad, soyad, telefon);
        this.adres = adres;
    }

    public Adres getAdres() {
        return adres;
    }

    @Override
    public String getRol() {
        return "Müşteri";
    }
}
