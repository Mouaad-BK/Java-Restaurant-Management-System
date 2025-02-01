package Restaurant;

public class Plat {

    private int idPlat;
    private String nomPlat;
    private float prix;
    private String typePlat;

    public Plat(int idPlat, String nomPlat, float prix, String typePlat) {
        this.idPlat = idPlat;
        this.nomPlat = nomPlat;
        this.prix = prix;
        this.typePlat = typePlat;
    }

    public int getIdPlat() {
        return idPlat;
    }
    public void setIdPlat(int idPlat) {
        this.idPlat = idPlat;
    }

    public String getNomPlat() {
        return nomPlat;
    }
    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }

    public float getPrix() {
        return prix;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getTypePlat() {
        return typePlat;
    }
    public void setTypePlat(String typePlat) {
        this.typePlat = typePlat;
    }

    @Override
    public String toString() {
        return "Plat{ id: "+idPlat+" , Nom: "+nomPlat+" , Prix: "+prix+" , Type: "+typePlat+" }";
    }

}
