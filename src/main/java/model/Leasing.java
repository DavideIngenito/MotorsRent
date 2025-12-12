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

    // getter e setter...

}
