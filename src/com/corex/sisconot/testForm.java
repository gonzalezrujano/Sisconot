package com.corex.sisconot;

import javax.swing.*;

public class testForm {
    public static class MarcoSencillo extends JFrame {
        private static final int ANCHO = 800, ALTO = 600;

        public MarcoSencillo() {
            setTitle("Sisconot Adultos");
            setSize(ANCHO, ALTO);
            setLocation(ANCHO / 2, ALTO / 2);
        }
    }
}
