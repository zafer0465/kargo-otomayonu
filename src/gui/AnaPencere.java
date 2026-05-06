package gui;

import Model.*;
import Servis.KargoYonetimSistemi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


 //AnaPencere
 // Kargo otomasyonu için ana grafik arayüz sınıfı

public class AnaPencere {

    // Kargo listesini göstermek için Swing bileşenleri
    private JList<String> liste;
    private DefaultListModel<String> listeModel = new DefaultListModel<>();

    // İş katmanı ve bellek içi kargo listesi
    private KargoYonetimSistemi sistem = new KargoYonetimSistemi();
    private List<Kargo> kargoListesi = new ArrayList<>();


    public AnaPencere() {

        // Örnek kuryeler
        Kurye k1 = new Kurye("Mehmet", "Kaya", "5551111111");
        Kurye k2 = new Kurye("Ahmet", "Demir", "5552222222");
        sistem.kuryeEkle(k1);
        sistem.kuryeEkle(k2);

        // Ana pencere (Frame)
        JFrame f = new JFrame("Kargo Otomasyonu");
        f.setSize(950, 650);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ana panel – absolute layout kullanılıyor
        JPanel p = new JPanel(null);
        p.setBackground(new Color(46, 139, 87));
        f.add(p);

        // Başlık
        JLabel baslik = new JLabel("Kargo Otomasyonu Yönetim Sistemi");
        baslik.setFont(new Font("Segoe UI", Font.BOLD, 20));
        baslik.setBounds(280, 10, 450, 30);
        p.add(baslik);

        // Kargo numarası ile arama alanı
        JLabel lblAra = new JLabel("Kargo No Ara:");
        lblAra.setBounds(615, 55, 100, 20);
        p.add(lblAra);

        JTextField txtAra = new JTextField();
        txtAra.setBounds(700, 55, 120, 22);
        p.add(txtAra);

        JButton btnAra = new JButton("Ara");
        btnAra.setBounds(830, 55, 80, 22);
        p.add(btnAra);

        // Form yerleşimi için koordinatlar
        int lx = 20, fx = 160, y = 60;

        // Kargo numarası alanı
        p.add(lbl("Kargo No:", lx, y));
        JTextField txtNo = txt(fx, y);
        p.add(txtNo);

        // Gönderici bilgileri
        y += 35;
        p.add(section("Gönderici Bilgileri", lx, y)); y += 25;

        JTextField gAd = field(p,"Ad:",lx,fx,y); y+=25;
        JTextField gSoyad = field(p,"Soyad:",lx,fx,y); y+=25;
        JTextField gTel = field(p,"Telefon:",lx,fx,y); y+=25;
        JTextField gIl = field(p,"İl:",lx,fx,y); y+=25;
        JTextField gIlce = field(p,"İlçe:",lx,fx,y); y+=30;

        // Alıcı bilgileri
        p.add(section("Alıcı Bilgileri", lx, y)); y += 25;

        JTextField aAd = field(p,"Ad:",lx,fx,y); y+=25;
        JTextField aSoyad = field(p,"Soyad:",lx,fx,y); y+=25;
        JTextField aTel = field(p,"Telefon:",lx,fx,y); y+=25;
        JTextField aIl = field(p,"İl:",lx,fx,y); y+=25;
        JTextField aIlce = field(p,"İlçe:",lx,fx,y); y+=30;

        // Kurye seçimi
        p.add(lbl("Kurye:", lx, y));
        JComboBox<Kurye> cmbKurye = new JComboBox<>(new Kurye[]{k1, k2});
        cmbKurye.setBounds(fx, y, 200, 25);
        p.add(cmbKurye);

        // Kargo durumu seçimi
        y += 30;
        p.add(lbl("Durum:", lx, y));
        JComboBox<KargoDurumu> cmbDurum =
                new JComboBox<>(KargoDurumu.values());
        cmbDurum.setBounds(fx, y, 200, 25);
        p.add(cmbDurum);

        // Kargo ekleme butonu
        JButton btnEkle = btn("Kargo Kaydet", fx, y+40);
        p.add(btnEkle);

        // Kargo listesi başlığı
        JLabel l = new JLabel("Kayıtlı Kargolar");
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        l.setBounds(430, 50, 200, 20);
        p.add(l);

        // Kargo listesi
        liste = new JList<>(listeModel);
        JScrollPane sp = new JScrollPane(liste);
        sp.setBounds(430, 80, 480, 350);
        p.add(sp);

        // Güncelle ve sil butonları
        JButton btnGuncelle = btn("Durum Güncelle", 430, 460);
        JButton btnSil = btn("Sil", 690, 460);
        p.add(btnGuncelle);
        p.add(btnSil);


         //KARGO EKLEME

        btnEkle.addActionListener(e -> {
            try {
                // Boş alan kontrolü
                if (txtNo.getText().isEmpty() ||
                        gAd.getText().isEmpty() ||
                        aAd.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(f,
                            "Zorunlu alanlar boş olamaz");
                    return;
                }

                // Kargo numarasını sayıya çevir
                int no = Integer.parseInt(txtNo.getText());

                // Aynı kargo no var mı kontrolü
                for (Kargo kg : kargoListesi) {
                    if (kg.getKargoNo() == no) {
                        JOptionPane.showMessageDialog(f,
                                "Bu kargo numarası zaten kayıtlı");
                        return;
                    }
                }

                // Gönderici oluştur
                Musteri g = new Musteri(
                        gAd.getText(), gSoyad.getText(), gTel.getText(),
                        new Adres(gIl.getText(), gIlce.getText())
                );

                // Alıcı oluştur
                Musteri a = new Musteri(
                        aAd.getText(), aSoyad.getText(), aTel.getText(),
                        new Adres(aIl.getText(), aIlce.getText())
                );

                // Kargo oluştur ve ayarları yap
                Kargo k = new Kargo(no, g, a);
                k.kuryeAta((Kurye) cmbKurye.getSelectedItem());
                k.durumGuncelle((KargoDurumu) cmbDurum.getSelectedItem());

                // Sisteme ekle
                sistem.kargoEkle(k);
                kargoListesi.add(k);

                // Listeye yaz
                listeModel.addElement(
                        "No: " + no +
                                " | " + g.getAd() +
                                " → " + a.getAd() +
                                " | " + k.getDurum()
                );

                JOptionPane.showMessageDialog(f, "Kargo kaydedildi");

            } catch (NumberFormatException ex) {
                // Sayı formatı hatası
                JOptionPane.showMessageDialog(f,
                        "Hata! alanları dikkatli doldurun");
            }
            // Form temizleme
            txtNo.setText("");
            gAd.setText("");
            gSoyad.setText("");
            gTel.setText("");
            gIl.setText("");
            gIlce.setText("");
            aAd.setText("");
            aSoyad.setText("");
            aTel.setText("");
            aIl.setText("");
            aIlce.setText("");
            cmbKurye.setSelectedIndex(0);
            cmbDurum.setSelectedIndex(0); });


         //KARGO DURUM GÜNCELLEME

        btnGuncelle.addActionListener(e -> {
            int i = liste.getSelectedIndex();
            if (i == -1) return;

            Kargo k = kargoListesi.get(i);

            // Yeni durum seçimi
            KargoDurumu yeni =
                    (KargoDurumu) JOptionPane.showInputDialog(
                            f,
                            "Yeni Durum",
                            "Durum Güncelle",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            KargoDurumu.values(),
                            k.getDurum()
                    );

            // Kullanıcı iptal etmediyse
            if (yeni != null) {
                k.durumGuncelle(yeni);
                listeModel.set(i,
                        "No: " + k.getKargoNo() +
                                " | " + k.getGonderici().getAd() +
                                " → " + k.getAlici().getAd() +
                                " | " + yeni
                );
            }
        });


          //KARGO SİLME

        btnSil.addActionListener(e -> {
            int i = liste.getSelectedIndex();
            if (i == -1) return;

            kargoListesi.remove(i);
            listeModel.remove(i);
        });


         //KARGO NO İLE ARAMA

        btnAra.addActionListener(e -> {
            try {
                int no = Integer.parseInt(txtAra.getText());
                boolean bulundu = false;

                for (int i = 0; i < kargoListesi.size(); i++) {
                    if (kargoListesi.get(i).getKargoNo() == no) {
                        liste.setSelectedIndex(i);
                        bulundu = true;
                        break;
                    }
                }

                if (!bulundu) {
                    JOptionPane.showMessageDialog(f,
                            "Kargo bulunamadı");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(f,
                        "Geçerli bir kargo numarası girin");
            }
        });

        // Pencere ortalanır ve gösterilir
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    // ------------------ YARDIMCI METOTLAR ------------------

    // Standart label oluşturur
    private JLabel lbl(String t,int x,int y){
        JLabel l=new JLabel(t);
        l.setBounds(x,y,130,20);
        return l;
    }

    // Bölüm başlığı label’ı
    private JLabel section(String t,int x,int y){
        JLabel l=new JLabel(t);
        l.setFont(new Font("Segoe UI",Font.BOLD,13));
        l.setBounds(x,y,200,20);
        return l;
    }

    // TextField oluşturur
    private JTextField txt(int x,int y){
        JTextField t=new JTextField();
        t.setBounds(x,y,200,22);
        return t;
    }

    // Label + TextField birlikte oluşturur
    private JTextField field(JPanel p,String label,int lx,int fx,int y){
        p.add(lbl(label,lx,y));
        JTextField t=txt(fx,y);
        p.add(t);
        return t;
    }

    // Standart buton oluşturur
    private JButton btn(String t,int x,int y){
        JButton b=new JButton(t);
        b.setBounds(x,y,200,35);
        b.setBackground(new Color(212,175,55));
        b.setForeground(Color.WHITE);
        return b;
    }
}
