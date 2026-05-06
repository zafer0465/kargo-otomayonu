package Model;

import java.util.ArrayList;
import java.util.List;

public class Kurye extends Kisi {

    // Kuryeye atanmış kargolar
    private List<Kargo> kargolar = new ArrayList<>();

    // Kurye oluşturur
    public Kurye(String ad, String soyad, String telefon) {
        super(ad, soyad, telefon);

    }

    // Kuryeye yeni kargo ekler
    protected void kargoEkle(Kargo kargo) {
        kargolar.add(kargo);
    }

    // Kuryenin kargolarının kopyasını döndürür
    public List<Kargo> getKargolar() {

        return new ArrayList<>(kargolar);
    }


    @Override
    public String getRol() {
        return "Kurye";
    }

    // JComboBox’ta düzgün görünmesi için
    @Override
    public String toString() {
        return ad + " " + soyad;
    }
}
