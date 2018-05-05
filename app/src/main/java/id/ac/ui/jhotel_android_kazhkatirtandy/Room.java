package id.ac.ui.jhotel_android_kazhkatirtandy;

public class Room {
    private String tipeKamar;
    private String roomNumber;
    private double dailyTariff;
    private String statusKamar;

    public Room(String tipeKamar, String roomNumber, double dailyTariff, String statusKamar) {
        this.tipeKamar = tipeKamar;
        this.roomNumber = roomNumber;
        this.dailyTariff = dailyTariff;
        this.statusKamar = statusKamar;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getDailyTariff() {
        return dailyTariff;
    }

    public String getStatusKamar() {
        return statusKamar;
    }

    public String getTipeKamar() {
        return tipeKamar;
    }

    public void setDailyTariff(double dailyTariff) {
        this.dailyTariff = dailyTariff;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setTipeKamar(String tipeKamar) {
        this.tipeKamar = tipeKamar;
    }

    public void setStatusKamar(String statusKamar) {
        this.statusKamar = statusKamar;
    }
}
