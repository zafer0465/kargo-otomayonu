package Servis;

import Model.Kargo;
import Model.Kurye;

import java.util.ArrayList;
import java.util.List;


public class KargoYonetimSistemi {

    // Sistemdeki kargolar
    private List<Kargo> kargolar = new ArrayList<>();

    // Sistemdeki kuryeler
    private List<Kurye> kuryeler = new ArrayList<>();

    // Yeni kargo ekler
    public void kargoEkle(Kargo kargo) {
        kargolar.add(kargo);
    }

    // Yeni kurye ekler
    public void kuryeEkle(Kurye kurye) {
        kuryeler.add(kurye);
    }

    // Kargo listesinin kopyasını döndürür
    public List<Kargo> getKargolar() {
        return new ArrayList<>(kargolar);
    }

    // Kurye listesinin kopyasını döndürür
    public List<Kurye> getKuryeler() {
        return new ArrayList<>(kuryeler);
    }
}

