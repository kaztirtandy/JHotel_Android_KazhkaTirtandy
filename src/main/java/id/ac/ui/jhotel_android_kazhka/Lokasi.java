package id.ac.ui.jhotel_android_kazhka;

public class Lokasi {
    private float x_coord;
    private float y_coord;
    private String deskripsiLokasi;

    public Lokasi(float x_coord, float y_coord, String deskripsiLokasi) {
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.deskripsiLokasi = deskripsiLokasi;
    }

    public float getX() {
        return x_coord;
    }
    public float getY() {
        return y_coord;
    }
    public String getDeskripsi() {
        return deskripsiLokasi;
    }

    public void setX (float x_coord) {
        this.x_coord = x_coord;
    }
    public void setY (float y_coord) {
        this.y_coord = y_coord;
    }
    public void setDeskripsi (String deskripsi) {
        deskripsiLokasi = deskripsi;
    }
}
