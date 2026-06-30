package GUI;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

import Dominio.*;
import Logica.*;

public class Menu {
    private static ISistema sistema = Sistema.getInstance();
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
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        cargarTabla(modelo);

        JTable tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(24);
        JScrollPane scroll = new JScrollPane(tabla);

        panelPrincipal.add(panelTop, BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila == -1) return;

                String nombre = (String) modelo.getValueAt(fila, 0);

                String[] opciones = {"Modificar", "Eliminar", "Cancelar"};
                int accion = JOptionPane.showOptionDialog(ventana,
                        "¿Qué quieres hacer con \"" + nombre + "\"?",
                        "Acción",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, opciones, opciones[0]);

                if (accion == 0) {
                    try {
                        mostrarFormularioModificar(ventana, fila);
                        cargarTabla(modelo);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else if (accion == 1) {
                    int confirm = JOptionPane.showConfirmDialog(ventana,
                            "¿Seguro que quieres eliminar \"" + nombre + "\"?",
                            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        sistema.eliminarCarta(fila);
						cargarTabla(modelo);
                    }
                }
            }
        });

        volver.addActionListener(e -> {
            ventana.getContentPane().removeAll();
            ventana.add(panelMenu);
            ventana.revalidate();
            ventana.repaint();
        });

        agregar.addActionListener(e -> {
            try {
                mostrarFormularioAgregar(ventana);
                cargarTabla(modelo);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        ventana.getContentPane().removeAll();
        ventana.add(panelPrincipal);
        ventana.revalidate();
        ventana.repaint();
    }

    private void cargarTabla(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        ArrayList<Carta> cartas = sistema.getCartas();
        for (int i = 0; i < cartas.size(); i++) {
            Carta c = cartas.get(i);
            String extra = "";
            String tipo = "";
            if (c instanceof Pokemon p) {
                tipo = "Pokemon";
                extra = "Daño: " + p.getDaño() + " | Energías: " + p.getCantidadEnergias();
            } else if (c instanceof Item it) {
                tipo = "Item";
                extra = "Bonificación: " + it.getBonificacion();
            } else if (c instanceof Supporter s) {
                tipo = "Supporter";
                extra = "Efectos/turno: " + s.getEfecto();
            } else if (c instanceof Energia en) {
                tipo = "Energy";
                extra = "Elemento: " + en.getElemento();
            }
            modelo.addRow(new Object[]{c.getNombre(), c.getRareza(), tipo, extra});
        }
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

    private void mostrarFormularioAgregar(JFrame ventanaPadre) throws IOException {
        String linea = pedirDatosCarta(ventanaPadre, null, null);
        if (linea != null) sistema.crearCarta(linea);
    }

    private void mostrarFormularioModificar(JFrame ventanaPadre, int indice) throws IOException {
        Carta actual = sistema.getCartas().get(indice);

        String[] campos = obtenerCampos(actual);
        String campo = (String) JOptionPane.showInputDialog(ventanaPadre,
                "¿Qué quieres modificar?", "Modificar carta",
                JOptionPane.QUESTION_MESSAGE, null, campos, campos[0]);
        if (campo == null) return;

        String linea = pedirDatosCarta(ventanaPadre, actual, campo);
        if (linea != null) sistema.modificarCarta(indice, linea);
    }

    private String[] obtenerCampos(Carta c) {
        if (c instanceof Pokemon) {
            return new String[]{"Nombre", "Rareza", "Daño", "Cantidad de energías"};
        } else if (c instanceof Item) {
            return new String[]{"Nombre", "Rareza", "Bonificación"};
        } else if (c instanceof Supporter) {
            return new String[]{"Nombre", "Rareza", "Efectos por turno"};
        } else {
            return new String[]{"Nombre", "Rareza", "Elemento"};
        }
    }

    private String pedirDatosCarta(JFrame ventanaPadre, Carta actual, String campo) {
        String nombre  = actual != null ? actual.getNombre() : "";
        int    rareza  = actual != null ? actual.getRareza() : 0;
        String tipo    = actual != null ? obtenerTipo(actual) : null;

        int    daño     = (actual instanceof Pokemon p)   ? p.getDaño()             : 0;
        int    energias = (actual instanceof Pokemon p)   ? p.getCantidadEnergias() : 0;
        int    boni     = (actual instanceof Item i)      ? i.getBonificacion()      : 0;
        int    efectos  = (actual instanceof Supporter s) ? s.getEfecto()            : 0;
        String elemento = (actual instanceof Energia en)  ? en.getElemento()         : "";

        boolean esAgregar = (campo == null);

        if (esAgregar) {
            String[] tipos = {"Pokemon", "Item", "Supporter", "Energy"};
            tipo = (String) JOptionPane.showInputDialog(ventanaPadre, "Elige el tipo:",
                    "Agregar carta", JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
            if (tipo == null) return null;
        }

        if (esAgregar || campo.equals("Nombre")) {
            String input = JOptionPane.showInputDialog(ventanaPadre, "Nombre:", nombre);
            if (input == null) return null;
            nombre = input;
        }
        if (esAgregar || campo.equals("Rareza")) {
            String input = JOptionPane.showInputDialog(ventanaPadre, "Rareza:", rareza == 0 ? "" : rareza);
            if (input == null) return null;
            rareza = Integer.parseInt(input);
        }

        if (tipo.equals("Pokemon")) {
            if (esAgregar || campo.equals("Daño")) {
                String input = JOptionPane.showInputDialog(ventanaPadre, "Daño:", daño == 0 ? "" : daño);
                if (input == null) return null;
                daño = Integer.parseInt(input);
            }
            if (esAgregar || campo.equals("Cantidad de energías")) {
                String input = JOptionPane.showInputDialog(ventanaPadre, "Cantidad de energías:", energias == 0 ? "" : energias);
                if (input == null) return null;
                energias = Integer.parseInt(input);
            }
            return nombre + ";" + rareza + ";Pokemon;" + daño + ";" + energias;
        } else if (tipo.equals("Item")) {
            if (esAgregar || campo.equals("Bonificación")) {
                String input = JOptionPane.showInputDialog(ventanaPadre, "Bonificación:", boni == 0 ? "" : boni);
                if (input == null) return null;
                boni = Integer.parseInt(input);
            }
            return nombre + ";" + rareza + ";Item;" + boni;
        } else if (tipo.equals("Supporter")) {
            if (esAgregar || campo.equals("Efectos por turno")) {
                String input = JOptionPane.showInputDialog(ventanaPadre, "Efectos por turno:", efectos == 0 ? "" : efectos);
                if (input == null) return null;
                efectos = Integer.parseInt(input);
            }
            return nombre + ";" + rareza + ";Supporter;" + efectos;
        } else {
            if (esAgregar || campo.equals("Elemento")) {
                String input = JOptionPane.showInputDialog(ventanaPadre, "Elemento:", elemento);
                if (input == null) return null;
                elemento = input;
            }
            return nombre + ";" + rareza + ";Energy;" + elemento;
        }
    }

    private String obtenerTipo(Carta c) {
        if (c instanceof Pokemon)   return "Pokemon";
        if (c instanceof Item)      return "Item";
        if (c instanceof Supporter) return "Supporter";
        return "Energy";
    }
}