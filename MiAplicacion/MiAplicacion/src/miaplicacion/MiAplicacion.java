/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package miaplicacion;

/**
 *
 * @author Harol
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MiAplicacion {

    // Configuración de la base de datos
    private static final String DB_URL = "jdbc:mysql://192.168.20.108/MiBaseDeDatos";
    private static final String DB_USER = "politecnico";
    private static final String DB_PASSWORD = "123456";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.println("POLITECNICO INTERNACIONAL | PROGRAMACION II");
                System.out.println(" ");
                System.out.println("APLICACION GUS - Gestion de usuarios");
                System.out.println("CRUD: Crear | Leer | Actualizar | Eliminar ");
                System.out.println("1. Ingresar datos");
                System.out.println("2. Eliminar datos");
                System.out.println("3. Consultar datos");
                System.out.println("4. Actualizar datos");
                System.out.println("5. Salir");
                System.out.println(" ");
                System.out.println("CREDITOS");
                System.out.println("Desarrollado por: Harol Hernan Torres - Harol.Torres@pi.edu.co");
                System.out.println("Ideas ITR | Bogota D.C, Colombia | 2023");
                System.out.println(" ");
                System.out.print("Selecciona una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        ingresarDatos(connection, scanner);
                        break;
                    case 2:
                        eliminarDatos(connection, scanner);
                        break;
                    case 3:
                        consultarDatos(connection);
                        break;
                    case 4:
                        actualizarDatos(connection, scanner);
                        break;
                    case 5:
                        System.out.println("Gracias por tu visita.");
                        System.out.println("Harol H. Torres Neuta - Harol.Torres@pi.edu.co");
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void ingresarDatos(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Estado Civil: ");
        String estadoCivil = scanner.nextLine();

        String insertQuery = "INSERT INTO Personas (nombre, edad, estado_civil) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, nombre);
        preparedStatement.setInt(2, edad);
        preparedStatement.setString(3, estadoCivil);
        preparedStatement.executeUpdate();
        System.out.println("Datos ingresados con éxito.");
    }

    private static void eliminarDatos(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("ID de la persona a eliminar: ");
        int id = scanner.nextInt();

        String deleteQuery = "DELETE FROM Personas WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.setInt(1, id);
        int rowCount = preparedStatement.executeUpdate();
        
        if (rowCount > 0) {
            System.out.println("Datos eliminados con éxito.");
        } else {
            System.out.println("No se encontró ninguna persona con ese ID.");
        }
    }

    private static void consultarDatos(Connection connection) throws SQLException {
        String selectQuery = "SELECT id, nombre, edad, estado_civil FROM Personas";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            int edad = resultSet.getInt("edad");
            String estadoCivil = resultSet.getString("estado_civil");
            System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad + ", Estado Civil: " + estadoCivil);
        }
    }

    private static void actualizarDatos(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("ID de la persona a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nuevo Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo Estado Civil: ");
        String estadoCivil = scanner.nextLine();

        String updateQuery = "UPDATE Personas SET nombre = ?, edad = ?, estado_civil = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setString(1, nombre);
        preparedStatement.setInt(2, edad);
        preparedStatement.setString(3, estadoCivil);
        preparedStatement.setInt(4, id);
        int rowCount = preparedStatement.executeUpdate();

        if (rowCount > 0) {
            System.out.println("Datos actualizados con éxito.");
        } else {
            System.out.println("No se encontró ninguna persona con ese ID.");
        }
    }
}
