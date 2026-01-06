package model;

public class Amministratore extends Utente {

    public Amministratore() {
        super();
        this.setRuolo("ADMIN");
    }

    public Amministratore(int idUtente, String nome, String cognome, String email,
                          String password, String telefono) {
        super(idUtente, nome, cognome, email, password, telefono, "ADMIN");
    }
}