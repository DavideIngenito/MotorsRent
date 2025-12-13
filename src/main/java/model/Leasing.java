package model;

import java.sql.Timestamp;

public class Leasing {

    private int idLeasing;
    private int idUtente;
    private int idAuto;
    private int durataMesi;
    private double anticipo;
    private int kmAnnui;
    private Timestamp dataLeasing;
    private String stato; // "in valutazione", "approvato", "respinto"

    // NUOVI CAMPI (Fondamentali per le JSP del venditore)
    private Utente cliente;
    private Automobile auto;

    public Leasing() {}

    // ... I tuoi getter e setter esistenti per i campi base ...
    public int getIdLeasing() { return idLeasing; }
    public void setIdLeasing(int idLeasing) { this.idLeasing = idLeasing; }

    public int getIdUtente() { return idUtente; }
    public void setIdUtente(int idUtente) { this.idUtente = idUtente; }

    public int getIdAuto() { return idAuto; }
    public void setIdAuto(int idAuto) { this.idAuto = idAuto; }

    public int getDurataMesi() { return durataMesi; }
    public void setDurataMesi(int durataMesi) { this.durataMesi = durataMesi; }

    public double getAnticipo() { return anticipo; }
    public void setAnticipo(double anticipo) { this.anticipo = anticipo; }

    public int getKmAnnui() { return kmAnnui; }
    public void setKmAnnui(int kmAnnui) { this.kmAnnui = kmAnnui; }

    public Timestamp getDataLeasing() { return dataLeasing; }
    public void setDataLeasing(Timestamp dataLeasing) { this.dataLeasing = dataLeasing; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }

    // NUOVI GETTER/SETTER PER GLI OGGETTI ANNIDATI
    public Utente getCliente() { return cliente; }
    public void setCliente(Utente cliente) { this.cliente = cliente; }

    public Automobile getAuto() { return auto; }
    public void setAuto(Automobile auto) { this.auto = auto; }
}