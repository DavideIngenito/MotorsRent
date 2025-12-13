package model;

public class Automobile {

    private int idAuto;
    private String marca;
    private String modello;
    private int anno;
    private double prezzo;
    private int chilometraggio;
    private String stato; // "nuova", "usata"
    private String descrizione;
    private String immagine; // path file
    private boolean disponibilita; // true = disponibile

    public Automobile() {}

    public Automobile(int idAuto, String marca, String modello, int anno,
                      double prezzo, int chilometraggio, String stato,
                      String descrizione, String immagine, boolean disponibilita) {
        this.idAuto = idAuto;
        this.marca = marca;
        this.modello = modello;
        this.anno = anno;
        this.prezzo = prezzo;
        this.chilometraggio = chilometraggio;
        this.stato = stato;
        this.descrizione = descrizione;
        this.immagine = immagine;
        this.disponibilita = disponibilita;
    }

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getChilometraggio() {
        return chilometraggio;
    }

    public void setChilometraggio(int chilometraggio) {
        this.chilometraggio = chilometraggio;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public boolean getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }
}
