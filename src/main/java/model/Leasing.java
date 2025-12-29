package model;

import java.sql.Timestamp;

public class Leasing {
    private int idLeasing;
    private int idUtente;
    private int idAuto;
    private int durataMesi;
    private double anticipo;
    private int kmAnnui;
    private Timestamp dataRichiesta;

    private String note;    // <-- Note inserite dal cliente alla richiesta
    private String stato;

    // Campi risposta venditore
    private double rataMensile;
    private String messaggioVenditore;

    // Oggetti complessi
    private Utente utente;
    private Automobile auto;

    public Leasing() {}

    // Getter e Setter
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

    public Timestamp getDataRichiesta() { return dataRichiesta; }
    public void setDataRichiesta(Timestamp dataRichiesta) { this.dataRichiesta = dataRichiesta; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }

    public double getRataMensile() { return rataMensile; }
    public void setRataMensile(double rataMensile) { this.rataMensile = rataMensile; }

    public String getMessaggioVenditore() { return messaggioVenditore; }
    public void setMessaggioVenditore(String messaggioVenditore) { this.messaggioVenditore = messaggioVenditore; }

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public Automobile getAuto() { return auto; }
    public void setAuto(Automobile auto) { this.auto = auto; }
}