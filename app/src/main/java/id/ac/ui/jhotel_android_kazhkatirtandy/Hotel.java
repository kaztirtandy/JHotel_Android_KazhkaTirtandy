package id.ac.ui.jhotel_android_kazhkatirtandy;

public class Hotel {
    private String nama;
    private Lokasi lokasi;
    private int bintang;
    private int id;

    public Hotel (String nama, Lokasi lokasi, int bintang) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.bintang = bintang;
        /*this.id = DatabaseHotel.getLastHotelId() + 1;*/
    }

    public int getBintang() {
        return bintang;
    }
    public String getNama() {
        return nama;
    }
    public Lokasi getLokasi() {
        return lokasi;
    }
    public int getId() {
        return id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setLokasi(Lokasi lokasi) {
        this.lokasi = lokasi;
    }
    public void setBintang(int bintang) {
        this.bintang = bintang;
    }
}
