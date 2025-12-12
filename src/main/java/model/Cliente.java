package model;

public class Cliente extends Utente {

    public Cliente() {}

    public Cliente(int idUtente, String nome, String cognome, String email,
                   String password, String telefono) {
        super(idUtente, nome, cognome, email, password, telefono, "cliente");
    }
}
