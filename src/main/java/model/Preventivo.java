package model;

import java.sql.Timestamp;

public class Preventivo {
    private int idPreventivo;
    private int idUtente;
    private int idAuto;
    private Timestamp dataRichiesta;
    private String note;
    private String stato;

    // OGGETTI ANNIDATI (Fondamentali per il dettaglio)
    private Utente utente;      // <--- Deve chiamarsi 'utente' per funzionare con ${richiesta.utente}
    private Automobile auto;    // <--- Deve chiamarsi 'auto'

    // Costruttori
    public Preventivo() {}

    // Getter e Setter standard
    public int getIdPreventivo() { return idPreventivo; }
    public void setIdPreventivo(int idPreventivo) { this.idPreventivo = idPreventivo; }

    public int getIdUtente() { return idUtente; }
    public void setIdUtente(int idUtente) { this.idUtente = idUtente; }

    public int getIdAuto() { return idAuto; }
    public void setIdAuto(int idAuto) { this.idAuto = idAuto; }

    public Timestamp getDataRichiesta() { return dataRichiesta; }
    public void setDataRichiesta(Timestamp dataRichiesta) { this.dataRichiesta = dataRichiesta; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }

    // GETTER E SETTER OGGETTI COMPLESSI
    public Utente getUtente() { return utente; }  // <--- Questo è il metodo che cercava la JSP!
    public void setUtente(Utente utente) { this.utente = utente; }

    public Automobile getAuto() { return auto; }
    public void setAuto(Automobile auto) { this.auto = auto; }
}