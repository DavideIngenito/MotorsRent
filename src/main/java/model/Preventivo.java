package model;

import java.sql.Timestamp;

public class Preventivo {

    private int idPreventivo;
    private int idUtente;
    private int idAuto;
    private Timestamp dataPreventivo;
    private String note;
    private String stato;

    // CAMPI AGGIUNTI PER LA VISUALIZZAZIONE
    private Utente cliente;
    private Automobile auto;

    public Preventivo() {}

    // Getter e Setter standard
    public int getIdPreventivo() { return idPreventivo; }
    public void setIdPreventivo(int idPreventivo) { this.idPreventivo = idPreventivo; }

    public int getIdUtente() { return idUtente; }
    public void setIdUtente(int idUtente) { this.idUtente = idUtente; }

    public int getIdAuto() { return idAuto; }
    public void setIdAuto(int idAuto) { this.idAuto = idAuto; }

    public Timestamp getDataPreventivo() { return dataPreventivo; }
    public void setDataPreventivo(Timestamp dataPreventivo) { this.dataPreventivo = dataPreventivo; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }

    // GETTER E SETTER PER GLI OGGETTI (FONDAMENTALI PER LA JSP)
    public Utente getCliente() { return cliente; }
    public void setCliente(Utente cliente) { this.cliente = cliente; }

    public Automobile getAuto() { return auto; }
    public void setAuto(Automobile auto) { this.auto = auto; }
}