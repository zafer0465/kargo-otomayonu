package Model;

public class Kargo {

    private int kargoNo;

    private Musteri gonderici;

    private Musteri alici;

    private Kurye kurye;

    private KargoDurumu durum;


    public Kargo(int kargoNo, Musteri gonderici, Musteri alici) {
        this.kargoNo = kargoNo;
        this.gonderici = gonderici;
        this.alici = alici;
        this.durum = KargoDurumu.KABUL_EDILDI;
    }


    public int getKargoNo() {
        return kargoNo;
    }


    public Musteri getGonderici() {
        return gonderici;
    }

    public Musteri getAlici() {
        return alici;
    }


    public Kurye getKurye() {
        return kurye;
    }

    public KargoDurumu getDurum() {
        return durum;
    }

    // Kargonun durumunu günceller
    public void durumGuncelle(KargoDurumu yeniDurum) {
        this.durum = yeniDurum;
    }

    // Kargoya kurye atar
    public void kuryeAta(Kurye kurye) {
        this.kurye = kurye;
        // Kargo aynı zamanda kuryenin listesine eklenir
        kurye.kargoEkle(this);
    }
}
