package model;

import java.time.LocalDate;

public class Preventivo {

    private int idPreventivo;
    private int idUtente;   // cliente
    private int idAuto;     // auto richiesta
    private LocalDate dataPreventivo;
    private String note;
    private String stato; // "inviato", "in lavorazione", "respinto", "accettato"

    public Preventivo() {}

    public Preventivo(int idPreventivo, int idUtente, int idAuto,
                      LocalDate dataPreventivo, String note, String stato) {
        this.idPreventivo = idPreventivo;
        this.idUtente = idUtente;
        this.idAuto = idAuto;
        this.dataPreventivo = dataPreventivo;
        this.note = note;
        this.stato = stato;
    }

    // getter e setter...

}
