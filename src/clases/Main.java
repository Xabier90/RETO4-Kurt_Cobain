package clases;

import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Acceso_a_GreenData.iniciar();
        });
    }
}