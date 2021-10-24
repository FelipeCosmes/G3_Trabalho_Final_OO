package app;

import entidades.Republica;

public class Main {
    public static void main(String[] args) {

        Republica republica = new Republica(true);
        
        republica.lerPessoas();
        republica.lerDespesas();
        
        UI.menu(republica);
    }
}
