package model;

public class Amministratore extends Utente {

    public Amministratore() {
        super();
        this.setRuolo("ADMIN"); // Imposto il default
    }

    public Amministratore(int idUtente, String nome, String cognome, String email,
                          String password, String telefono) {
        // NOTA: Assicurati che la stringa "ADMIN" coincida con quella usata nel DB e nella LoginServlet
        super(idUtente, nome, cognome, email, password, telefono, "ADMIN");
    }
}