/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package miaplicaciongui;

/**
 *
 * @author Harol
 */

import javax.swing.*;  //Librerias para interfaz GUI
import java.awt.*;   //Librerias para interfaz gestion de eventos
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.sql.Connection; //Librerias para conexion MySql
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MiAplicacionGUI {

    private static final String DB_URL = "jdbc:mysql://192.168.20.108/MiBaseDeDatos"; // URL de la base de datos
    private static final String DB_USER = "politecnico"; // Usuario de la base de datos
    private static final String DB_PASSWORD = "123456"; // Contraseña del usuario de la base de datos

    public static void main(String[] args) {
        // Ejecuta la creación y visualización de la interfaz gráfica en el hilo de Swing
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Crea la ventana principal
        JFrame frame = new JFrame("APLICACION GUS - Gestion de usuarios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura la acción de cierre al hacer clic en el botón de cierre
        frame.setSize(600, 400); // Establece el tamaño de la ventana
        frame.setLayout(new BorderLayout()); // Configura el diseño de la ventana como BorderLayout
        frame.setLocationRelativeTo(null); // Centrar Pantalla 

        // Crea un panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Establece el diseño de FlowLayout para el panel de botones

        // Crea botones para las operaciones CRUD
        JButton crearButton = new JButton("Crear");
        JButton leerButton = new JButton("Leer");
        JButton actualizarButton = new JButton("Actualizar");
        JButton eliminarButton = new JButton("Eliminar");

        // Agrega los botones al panel
        buttonPanel.add(crearButton);
        buttonPanel.add(leerButton);
        buttonPanel.add(actualizarButton);
        buttonPanel.add(eliminarButton);

        // Crea un área de texto para mostrar resultados
        JTextArea outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false); // Configura el área de texto como solo de lectura
        JScrollPane scrollPane = new JScrollPane(outputTextArea); // Crea un panel de desplazamiento para el área de texto

        // Agrega el panel de botones en la parte superior de la ventana y el área de texto en el centro
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true); // Hace que la ventana sea visible

        // ActionListener para el botón "Crear"
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    // Lógica para la operación de Crear
                    // Solicita datos
                    String nombre = JOptionPane.showInputDialog("Nombre: ");
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad: "));
                    String estadoCivil = JOptionPane.showInputDialog("Estado Civil: ");

                    // Prepara la consulta SQL
                    String insertQuery = "INSERT INTO Personas (nombre, edad, estado_civil) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, nombre);
                    preparedStatement.setInt(2, edad);
                    preparedStatement.setString(3, estadoCivil);

                    // Ejecuta la consulta y verifica el número de filas afectadas
                    int rowCount = preparedStatement.executeUpdate();

                    if (rowCount > 0) {
                        outputTextArea.append("Datos ingresados con éxito.\n");
                    } else {
                        outputTextArea.append("Error al ingresar datos.\n");
                    }
                } catch (SQLException ex) {
                    // Manejo de errores de conexión a la base de datos
                    ex.printStackTrace();
                    outputTextArea.append("Error de conexión a la base de datos\n");
                }
            }
        });

        // ActionListener para el botón "Leer"
        leerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    // Lógica para la operación de Leer
                    // Consulta la base de datos
                    String selectQuery = "SELECT id, nombre, edad, estado_civil FROM Personas";
                    PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Limpia el área de texto
                    outputTextArea.setText("");

                    // Muestra los resultados en el área de texto
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nombre = resultSet.getString("nombre");
                        int edad = resultSet.getInt("edad");
                        String estadoCivil = resultSet.getString("estado_civil");
                        outputTextArea.append("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad + ", Estado Civil: " + estadoCivil + "\n");
                    }
                } catch (SQLException ex) {
                    // Manejo de errores de conexión a la base de datos
                    ex.printStackTrace();
                    outputTextArea.append("Error al consultar datos.\n");
                }
            }
        });

        // ActionListener para el botón "Actualizar"
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    // Lógica para la operación de Actualizar
                    // Solicita datos
                    int id = Integer.parseInt(JOptionPane.showInputDialog("ID de la persona a actualizar: "));
                    String nombre = JOptionPane.showInputDialog("Nuevo Nombre: ");
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Nueva Edad: "));
                    String estadoCivil = JOptionPane.showInputDialog("Nuevo Estado Civil: ");

                    // Prepara la consulta SQL
                    String updateQuery = "UPDATE Personas SET nombre = ?, edad = ?, estado_civil = ? WHERE id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setString(1, nombre);
                    preparedStatement.setInt(2, edad);
                    preparedStatement.setString(3, estadoCivil);
                    preparedStatement.setInt(4, id);

                    // Ejecuta la consulta y verifica el número de filas afectadas
                    int rowCount = preparedStatement.executeUpdate();

                    if (rowCount > 0) {
                        outputTextArea.append("Datos actualizados con éxito.\n");
                    } else {
                        outputTextArea.append("No se encontró ninguna persona con ese ID.\n");
                    }
                } catch (SQLException ex) {
                    // Manejo de errores de conexión a la base de datos
                    ex.printStackTrace();
                    outputTextArea.append("Error al actualizar datos.\n");
                }
            }
        });

        // ActionListener para el botón "Eliminar"
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    // Lógica para la operación de Eliminar
                    // Solicita un ID
                    int id = Integer.parseInt(JOptionPane.showInputDialog("ID de la persona a eliminar: "));

                    // Prepara la consulta SQL
                    String deleteQuery = "DELETE FROM Personas WHERE id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                    preparedStatement.setInt(1, id);

                    // Ejecuta la consulta y verifica el número de filas afectadas
                    int rowCount = preparedStatement.executeUpdate();

                    if (rowCount > 0) {
                        outputTextArea.append("Datos eliminados con éxito.\n");
                    } else {
                        outputTextArea.append("No se encontró ninguna persona con ese ID.\n");
                    }
                } catch (SQLException ex) {
                    // Manejo de errores de conexión a la base de datos
                    ex.printStackTrace();
                    outputTextArea.append("Error al eliminar datos.\n");
                }
            }
        });
    }
}
