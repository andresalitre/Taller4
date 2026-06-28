package GUI;


import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

import Dominio.*;
import Logica.*;


public class Menu {
	
	private ArrayList<Carta> cartas;

	public Menu(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}

	public void iniciar() throws IOException {
        menu();
    }

    public void menu() throws IOException {
        JFrame ventana = new JFrame("TCG");
        JPanel panelPrincipal = new JPanel();

        ventana.setSize(600, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        JButton admin = new JButton("Administración");
        JButton coleccion = new JButton("Colección");
        JButton salir = new JButton("Salir");

        panelPrincipal.add(admin);
        panelPrincipal.add(coleccion);
        panelPrincipal.add(salir);

        admin.addActionListener(e -> {
            try {
                administrador(ventana, panelPrincipal);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        coleccion.addActionListener(e -> {
            try {
                coleccion(ventana, panelPrincipal);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        salir.addActionListener(e -> ventana.dispose());

        ventana.add(panelPrincipal);
        ventana.setVisible(true);
    }

    public void administrador(JFrame ventana, JPanel panelMenu) throws IOException {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton agregar = new JButton("Agregar carta");
        JButton volver = new JButton("Volver");
        panelTop.add(agregar);
        panelTop.add(volver);

        String[] columnas = {"Nombre", "Rareza", "Tipo", "Extra"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        	
        };
    }
        public void coleccion(JFrame ventana, JPanel panelMenu) throws IOException {
            JPanel panelPrincipal = new JPanel();
            JButton rareza = new JButton("Ordenar por rareza");
            JButton nombre = new JButton("Ordenar por nombre");
            JButton poder = new JButton("Ordenar por poder");
            JButton volver = new JButton("Volver");

            panelPrincipal.add(rareza);
            panelPrincipal.add(nombre);
            panelPrincipal.add(poder);
            panelPrincipal.add(volver);

            volver.addActionListener(e -> {
                ventana.getContentPane().removeAll();
                ventana.add(panelMenu);
                ventana.revalidate();
                ventana.repaint();
            });

            ventana.getContentPane().removeAll();
            ventana.add(panelPrincipal);
            ventana.revalidate();
            ventana.repaint();
        }
	
}
