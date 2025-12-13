package model;

import java.sql.Timestamp;

public class Preventivo {

    private int idPreventivo;
    private int idUtente;   // cliente
    private int idAuto;     // auto richiesta
    private Timestamp dataPreventivo;
    private String note;
    private String stato; // "inviato", "in lavorazione", "respinto", "accettato"

    public Preventivo() {}

    public Preventivo(int idPreventivo, int idUtente, int idAuto,
                      Timestamp dataPreventivo, String note, String stato) {
        this.idPreventivo = idPreventivo;
        this.idUtente = idUtente;
        this.idAuto = idAuto;
        this.dataPreventivo = dataPreventivo;
        this.note = note;
        this.stato = stato;
    }

    public int getIdPreventivo() {
        return idPreventivo;
    }

    public void setIdPreventivo(int idPreventivo) {
        this.idPreventivo = idPreventivo;
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

    public Timestamp getDataPreventivo() {
        return dataPreventivo;
    }

    public void setDataPreventivo(Timestamp dataPreventivo) {
        this.dataPreventivo = dataPreventivo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}
