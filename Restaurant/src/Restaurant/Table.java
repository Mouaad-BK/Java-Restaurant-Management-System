package Restaurant;

public class Table {

    private int idTable;
    private String etatTable;
    private Reservation reservation;

    public Table(int idTable, String etatTable) {
    }
    public Table (){

    }

    public int getIdTable() {
        return idTable;
    }
    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public String getEtatTable() {
        return etatTable;
    }
    public void setEtatTable(String etatTable) {
        this.etatTable = etatTable;
    }

    public Reservation getReservation() {
        return reservation;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

}
