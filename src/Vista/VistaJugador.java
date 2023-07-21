package Vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Controlador.ControladorJugador;
import Datos.Jugador;

public class VistaJugador extends JFrame implements ActionListener {

    private JTextField idField, nombreField, dorsalField, alturaField;
    private JButton insertarBtn, borrarBtn, actualizarBtn, listarBtn;

    public VistaJugador() {
        super("Gestión de Jugadores");
        setLayout(new GridLayout(5, 2));

        // Componentes de la interfaz gráfica
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();
        JLabel dorsalLabel = new JLabel("Dorsal:");
        dorsalField = new JTextField();
        JLabel alturaLabel = new JLabel("Altura:");
        alturaField = new JTextField();

        //*********Probieren und danach loeschen 
         listarBtn = new JButton("Listar");
        
        
        insertarBtn = new JButton("Insertar");
        borrarBtn = new JButton("Borrar");
        actualizarBtn = new JButton("Actualizar");

        // Agregar ActionListener a los botones
        insertarBtn.addActionListener(this);
        borrarBtn.addActionListener(this);
        actualizarBtn.addActionListener(this);
        
        
        
        //********Probieren und danach loeschen
        listarBtn.addActionListener(this);

        // Agregar componentes a la interfaz gráfica
        add(idLabel);
        add(idField);
        add(nombreLabel);
        add(nombreField);
        add(dorsalLabel);
        add(dorsalField);
        add(alturaLabel);
        add(alturaField);
        add(insertarBtn);
        add(borrarBtn);
        add(actualizarBtn);
        
        //********Probieren und danach loeschen
        
        add(listarBtn);
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insertarBtn) {
            try {
				insertarJugador();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
        } else if (e.getSource() == borrarBtn) {
            borrarJugador();
        } else if (e.getSource() == actualizarBtn) {
            actualizarJugador();
        }else if (e.getSource() == listarBtn) { //  el botón "Listar"
        	 try {
				mostrarListaJugadores();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    }
    }

    private void mostrarListaJugadores() throws ClassNotFoundException {
        List<Jugador> jugadores = ControladorJugador.obtenerListaJugadores();
        if (jugadores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay jugadores registrados.", "Lista de Jugadores", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder listaTexto = new StringBuilder();
            for (Jugador jugador : jugadores) {
                listaTexto.append(jugador.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(this, listaTexto.toString(), "Lista de Jugadores", JOptionPane.INFORMATION_MESSAGE);
        }
    }

	private void insertarJugador() throws ClassNotFoundException {
        int id = Integer.parseInt(idField.getText());
        String nombre = nombreField.getText();
        int dorsal = Integer.parseInt(dorsalField.getText());
        double altura = Double.parseDouble(alturaField.getText());
        
        try {
            if (ControladorJugador.isIDExists(id)) {
                JOptionPane.showMessageDialog(this, "\u001B[32mEl ID ingresado ya existe.\u001B[0m", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

        Jugador jugador = new Jugador(id, nombre, dorsal, altura);
        ControladorJugador.insertarJugador(jugador);
        JOptionPane.showMessageDialog(this, "Datos del jugador insertados correctamente.");
    }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void borrarJugador() {
        int id = Integer.parseInt(idField.getText());
        ControladorJugador.borrarJugador(id);
        JOptionPane.showMessageDialog(this, "Datos del jugador borrados correctamente.");
    }

    private void actualizarJugador() {
        int id = Integer.parseInt(idField.getText());
        String nombre = nombreField.getText();
        int dorsal = Integer.parseInt(dorsalField.getText());
        double altura = Double.parseDouble(alturaField.getText());

        Jugador jugador = new Jugador(id, nombre, dorsal, altura);
        ControladorJugador.actualizarJugador(jugador);
        JOptionPane.showMessageDialog(this, "Datos del jugador actualizados correctamente.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaJugador());
    }
}