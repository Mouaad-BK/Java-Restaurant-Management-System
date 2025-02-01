package Restaurant;

import java.util.Date;

public class Paiment {

    private int idPAiment;
    private int idCommande;
    private Date datePaiment;
    private double prix;
    private String typePaiment;
    private Commande commande;

    public int getIdPAiment() {
        return idPAiment;
    }

    public void setIdPAiment(int idPAiment) {
        this.idPAiment = idPAiment;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Date getDatePaiment() {
        return datePaiment;
    }

    public void setDatePaiment(Date datePaiment) {
        this.datePaiment = datePaiment;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getTypePaiment() {
        return typePaiment;
    }

    public void setTypePaiment(String typePaiment) {
        this.typePaiment = typePaiment;
    }

    public Commande getCommande() {
        return commande;
    }
    public void setCommande(Commande commande) {
        this.commande = commande;
    }

}
