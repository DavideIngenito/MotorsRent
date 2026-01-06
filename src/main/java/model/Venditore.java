package model;

public class Venditore extends Utente {

    public Venditore() {}

    public Venditore(int idUtente, String nome, String cognome, String email,
                     String password, String telefono) {
        super(idUtente, nome, cognome, email, password, telefono, "VENDITORE");
    }
}
