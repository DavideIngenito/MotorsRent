package model;

import java.time.LocalDate;

public class Leasing {

    private int idLeasing;
    private int idUtente;
    private int idAuto;
    private int durataMesi;
    private double anticipo;
    private int kmAnnui;
    private LocalDate dataLeasing;
    private String stato; // "in valutazione", "approvato", "respinto"

    public Leasing() {}

    public Leasing(int idLeasing, int idUtente, int idAuto,
                   int durataMesi, double anticipo, int kmAnnui,
                   LocalDate dataLeasing, String stato) {
        this.idLeasing = idLeasing;
        this.idUtente = idUtente;
        this.idAuto = idAuto;
        this.durataMesi = durataMesi;
        this.anticipo = anticipo;
        this.kmAnnui = kmAnnui;
        this.dataLeasing = dataLeasing;
        this.stato = stato;
    }

    public int getIdLeasing() {
        return idLeasing;
    }

    public void setIdLeasing(int idLeasing) {
        this.idLeasing = idLeasing;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public int getDurataMesi() {
        return durataMesi;
    }

    public void setDurataMesi(int durataMesi) {
        this.durataMesi = durataMesi;
    }

    public double getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(double anticipo) {
        this.anticipo = anticipo;
    }

    public int getKmAnnui() {
        return kmAnnui;
    }

    public void setKmAnnui(int kmAnnui) {
        this.kmAnnui = kmAnnui;
    }

    public LocalDate getDataLeasing() {
        return dataLeasing;
    }

    public void setDataLeasing(LocalDate dataLeasing) {
        this.dataLeasing = dataLeasing;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}
