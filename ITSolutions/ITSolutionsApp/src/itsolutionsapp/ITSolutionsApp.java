/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package itsolutionsapp;

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

public class ITSolutionsApp {

    private static final String DB_URL = "jdbc:mysql://192.168.20.108/ITSolutions";
    private static final String DB_USER = "politecnico";
    private static final String DB_PASSWORD = "123456";

    public static void main(String[] args) {
        try {
             // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("POLITECNICO INTERNACIONAL|PROGRAMACION II");
                System.out.println(" ");
                System.out.println("Sistema de informacion para la gestion de Clientes, Empleados, Proveedores, Inventario.");
                System.out.println(" ");
                System.out.println("ITSolutions - Menu Principal");
                  // Mostrar las opciones disponibles
                System.out.println(" ");
                System.out.println("GESTION DE CLIENTES");
                System.out.println("1. Ingresar Cliente");
                System.out.println("2. Eliminar Cliente");
                System.out.println("3. Consultar Clientes");
                System.out.println("4. Actualizar Cliente");
                System.out.println(" ");
                System.out.println("GESTION DE EMPLEADOS");
                System.out.println("5. Ingresar Empleado");
                System.out.println("6. Eliminar Empleado");
                System.out.println("7. Consultar Empleados");
                System.out.println("8. Actualizar Empleado");
                System.out.println(" ");
                System.out.println("GESTION DE PROVEEDORES");
                System.out.println("9. Ingresar Proveedor");
                System.out.println("10. Eliminar Proveedor");
                System.out.println("11. Consultar Proveedores");
                System.out.println("12. Actualizar Proveedor");
                System.out.println(" ");
                System.out.println("GESTION DE INVENTARIO");
                System.out.println("13. Ingresar Parte al Inventario");
                System.out.println("14. Eliminar Parte del Inventario");
                System.out.println("15. Consultar Inventario");
                System.out.println("16. Actualizar Parte del Inventario");
                System.out.println(" ");
                System.out.println("17. SALIR DE LA APP");
                System.out.println(" ");
                System.out.println("CREDITOS");
                System.out.println("Desarrollado por: Harol Hernan Torres - Harol.Torres@pi.edu.co");
                System.out.println("Ideas ITR | Bogota D.C, Colombia | 2023");
                System.out.println(" ");
                System.out.print("Selecciona una opcion: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        ingresarCliente(connection, scanner);
                        break;
                    case 2:
                        eliminarCliente(connection, scanner);
                        break;
                    case 3:
                        consultarClientes(connection);
                        break;
                    case 4:
                        actualizarCliente(connection, scanner);
                        break;
                    case 5:
                        ingresarEmpleado(connection, scanner);
                        break;
                    case 6:
                        eliminarEmpleado(connection, scanner);
                        break;
                    case 7:
                        consultarEmpleados(connection);
                        break;
                    case 8:
                        actualizarEmpleado(connection, scanner);
                        break;
                    case 9:
                        ingresarProveedor(connection, scanner);
                        break;
                    case 10:
                        eliminarProveedor(connection, scanner);
                        break;
                    case 11:
                        consultarProveedores(connection);
                        break;
                    case 12:
                        actualizarProveedor(connection, scanner);
                        break;
                    case 13:
                        ingresarParteInventario(connection, scanner);
                        break;
                    case 14:
                        eliminarParteInventario(connection, scanner);
                        break;
                    case 15:
                        consultarInventario(connection);
                        break;
                    case 16:
                        actualizarParteInventario(connection, scanner);
                        break;
                    case 17:
                        System.out.println("Saliendo de la aplicación.");
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            }
        } catch (SQLException e) {
        }
    }

    // Método para ingresar un cliente en la base de datos
    private static void ingresarCliente(Connection connection, Scanner scanner) {
        try {
            System.out.print("Nombre del cliente: ");
            String nombre = scanner.nextLine();
            System.out.print("Edad: ");
            int edad = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Estado Civil: ");
            String estadoCivil = scanner.nextLine();

            String insertQuery = "INSERT INTO Clientes (Nombre, Edad, EstadoCivil) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, edad);
            preparedStatement.setString(3, estadoCivil);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Cliente ingresado con éxito.");
            } else {
                System.out.println("Error al ingresar el cliente.");
            }
        } catch (SQLException e) {
        }
    }

    // Método para eliminar un cliente en la base de datos
    private static void eliminarCliente(Connection connection, Scanner scanner) {
        try {
            System.out.print("ID del cliente a eliminar: ");
            int clienteID = scanner.nextInt();

            String deleteQuery = "DELETE FROM Clientes WHERE ClienteID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, clienteID);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Cliente eliminado con éxito.");
            } else {
                System.out.println("No se encontró ningún cliente con ese ID.");
            }
        } catch (SQLException e) {
        }
    }

    // Método para consultar un cliente en la base de datos
    private static void consultarClientes(Connection connection) {
        try {
            String selectQuery = "SELECT ClienteID, Nombre, Edad, EstadoCivil FROM Clientes";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int clienteID = resultSet.getInt("ClienteID");
                String nombre = resultSet.getString("Nombre");
                int edad = resultSet.getInt("Edad");
                String estadoCivil = resultSet.getString("EstadoCivil");
                System.out.println("ID: " + clienteID + ", Nombre: " + nombre + ", Edad: " + edad + ", Estado Civil: " + estadoCivil);
            }
        } catch (SQLException e) {
        }
    }

    // Método para actualizar un cliente en la base de datos
    private static void actualizarCliente(Connection connection, Scanner scanner) {
        try {
            System.out.print("ID del cliente a actualizar: ");
            int clienteID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nueva edad: ");
            int edad = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nuevo estado civil: ");
            String estadoCivil = scanner.nextLine();

            String updateQuery = "UPDATE Clientes SET Nombre = ?, Edad = ?, EstadoCivil = ? WHERE ClienteID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, edad);
            preparedStatement.setString(3, estadoCivil);
            preparedStatement.setInt(4, clienteID);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Cliente actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún cliente con ese ID.");
            }
        } catch (SQLException e) {
        }
    }

    // Método para ingresar un empleado en la base de datos
    private static void ingresarEmpleado(Connection connection, Scanner scanner) {
        try {
            System.out.print("Nombre del empleado: ");
            String nombre = scanner.nextLine();
            System.out.print("Código de empleado: ");
            String codigoEmpleado = scanner.nextLine();
            System.out.print("Área: ");
            String area = scanner.nextLine();
            System.out.print("Sede: ");
            String sede = scanner.nextLine();

            String insertQuery = "INSERT INTO Empleados (Nombre, CodigoEmpleado, Area, Sede) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, codigoEmpleado);
            preparedStatement.setString(3, area);
            preparedStatement.setString(4, sede);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Empleado ingresado con éxito.");
            } else {
                System.out.println("Error al ingresar el empleado.");
            }
        } catch (SQLException e) {
        }
    }
    
    // Método para eliminar un empleado en la base de datos
    private static void eliminarEmpleado(Connection connection, Scanner scanner) {
        try {
            System.out.print("ID del empleado a eliminar: ");
            int empleadoID = scanner.nextInt();

            String deleteQuery = "DELETE FROM Empleados WHERE EmpleadoID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, empleadoID);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Empleado eliminado con éxito.");
            } else {
                System.out.println("No se encontró ningún empleado con ese ID.");
            }
        } catch (SQLException e) {
        }
    }
    
    // Método para consultar un empleado en la base de datos
    private static void consultarEmpleados(Connection connection) {
        try {
            String selectQuery = "SELECT EmpleadoID, Nombre, CodigoEmpleado, Area, Sede FROM Empleados";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int empleadoID = resultSet.getInt("EmpleadoID");
                String nombre = resultSet.getString("Nombre");
                String codigoEmpleado = resultSet.getString("CodigoEmpleado");
                String area = resultSet.getString("Area");
                String sede = resultSet.getString("Sede");
                System.out.println("ID: " + empleadoID + ", Nombre: " + nombre + ", Código de Empleado: " + codigoEmpleado + ", Área: " + area + ", Sede: " + sede);
            }
        } catch (SQLException e) {
        }
    }

    // Método para actualizar un empleado en la base de datos
    private static void actualizarEmpleado(Connection connection, Scanner scanner) {
        try {
            System.out.print("ID del empleado a actualizar: ");
            int empleadoID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nuevo código de empleado: ");
            String codigoEmpleado = scanner.nextLine();
            System.out.print("Nueva área: ");
            String area = scanner.nextLine();
            System.out.print("Nueva sede: ");
            String sede = scanner.nextLine();

            String updateQuery = "UPDATE Empleados SET Nombre = ?, CodigoEmpleado = ?, Area = ?, Sede = ? WHERE EmpleadoID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, codigoEmpleado);
            preparedStatement.setString(3, area);
            preparedStatement.setString(4, sede);
            preparedStatement.setInt(5, empleadoID);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Empleado actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún empleado con ese ID.");
            }
        } catch (SQLException e) {
        }
    }

    // Método para ingresar un proveedor en la base de datos
    private static void ingresarProveedor(Connection connection, Scanner scanner) {
        try {
            System.out.print("NIT del proveedor: ");
            String nit = scanner.nextLine();
            System.out.print("Nombre del proveedor: ");
            String nombre = scanner.nextLine();
            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();
            System.out.print("Correo: ");
            String correo = scanner.nextLine();
            System.out.print("Dirección física: ");
            String direccion = scanner.nextLine();

            String insertQuery = "INSERT INTO Proveedores (Nit, Nombre, Telefono, Correo, DireccionFisica) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nit);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, telefono);
            preparedStatement.setString(4, correo);
            preparedStatement.setString(5, direccion);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Proveedor ingresado con éxito.");
            } else {
                System.out.println("Error al ingresar el proveedor.");
            }
        } catch (SQLException e) {
        }
    }
    // Método para eliminar un proveedor en la base de datos
    private static void eliminarProveedor(Connection connection, Scanner scanner) {
        try {
            System.out.print("NIT del proveedor a eliminar: ");
            String nit = scanner.nextLine();

            String deleteQuery = "DELETE FROM Proveedores WHERE Nit = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, nit);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Proveedor eliminado con éxito.");
            } else {
                System.out.println("No se encontró ningún proveedor con ese NIT.");
            }
        } catch (SQLException e) {
        }
    }
     // Método para consultar un proveedor en la base de datos
    private static void consultarProveedores(Connection connection) {
        try {
            String selectQuery = "SELECT Nit, Nombre, Telefono, Correo, DireccionFisica FROM Proveedores";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nit = resultSet.getString("Nit");
                String nombre = resultSet.getString("Nombre");
                String telefono = resultSet.getString("Telefono");
                String correo = resultSet.getString("Correo");
                String direccion = resultSet.getString("DireccionFisica");
                System.out.println("NIT: " + nit + ", Nombre: " + nombre + ", Teléfono: " + telefono + ", Correo: " + correo + ", Dirección Física: " + direccion);
            }
        } catch (SQLException e) {
        }
    }
    // Método para actualziar un proveedor en la base de datos
    private static void actualizarProveedor(Connection connection, Scanner scanner) {
        try {
            System.out.print("NIT del proveedor a actualizar: ");
            String nit = scanner.nextLine();

            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nuevo teléfono: ");
            String telefono = scanner.nextLine();
            System.out.print("Nuevo correo: ");
            String correo = scanner.nextLine();
            System.out.print("Nueva dirección física: ");
            String direccion = scanner.nextLine();

            String updateQuery = "UPDATE Proveedores SET Nombre = ?, Telefono = ?, Correo = ?, DireccionFisica = ? WHERE Nit = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, telefono);
            preparedStatement.setString(3, correo);
            preparedStatement.setString(4, direccion);
            preparedStatement.setString(5, nit);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Proveedor actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún proveedor con ese NIT.");
            }
        } catch (SQLException e) {
        }
    }
    // Método para ingresar una Parte del Invetario en la base de datos
    private static void ingresarParteInventario(Connection connection, Scanner scanner) {
        try {
            System.out.print("Nombre de la parte: ");
            String parte = scanner.nextLine();
            System.out.print("Precio: ");
            double precio = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Cantidad en stock: ");
            int cantidadStock = scanner.nextInt();
            scanner.nextLine();
            System.out.print("¿En garantía? (true/false): ");
            boolean enGarantia = scanner.nextBoolean();

            String insertQuery = "INSERT INTO Inventario (Parte, Precio, CantidadStock, EstadoGarantia) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, parte);
            preparedStatement.setDouble(2, precio);
            preparedStatement.setInt(3, cantidadStock);
            preparedStatement.setBoolean(4, enGarantia);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Parte ingresada al inventario con éxito.");
            } else {
                System.out.println("Error al ingresar la parte al inventario.");
            }
        } catch (SQLException e) {
        }
    }
     // Método para Eliminar una Parte del Invetario en la base de datos
    private static void eliminarParteInventario(Connection connection, Scanner scanner) {
        try {
            System.out.print("ID de la parte a eliminar: ");
            int parteID = scanner.nextInt();

            String deleteQuery = "DELETE FROM Inventario WHERE ParteID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, parteID);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Parte del inventario eliminada con éxito.");
            } else {
                System.out.println("No se encontró ninguna parte con ese ID.");
            }
        } catch (SQLException e) {
        }
    }
    // Método para consultar una Parte del Invetario en la base de datos
    private static void consultarInventario(Connection connection) {
        try {
            String selectQuery = "SELECT ParteID, Parte, Precio, CantidadStock, EstadoGarantia FROM Inventario";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int parteID = resultSet.getInt("ParteID");
                String parte = resultSet.getString("Parte");
                double precio = resultSet.getDouble("Precio");
                int cantidadStock = resultSet.getInt("CantidadStock");
                boolean enGarantia = resultSet.getBoolean("EstadoGarantia");
                System.out.println("ID: " + parteID + ", Parte: " + parte + ", Precio: " + precio + ", Cantidad en Stock: " + cantidadStock + ", En Garantía: " + enGarantia);
            }
        } catch (SQLException e) {
        }
    }
    // Método para actualizar una Parte del Invetario en la base de datos
    private static void actualizarParteInventario(Connection connection, Scanner scanner) {
        try {
            System.out.print("ID de la parte a actualizar: ");
            int parteID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nuevo nombre de la parte: ");
            String parte = scanner.nextLine();
            System.out.print("Nuevo precio: ");
            double precio = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Nueva cantidad en stock: ");
            int cantidadStock = scanner.nextInt();
            scanner.nextLine();
            System.out.print("¿Nueva garantía? (true/false): ");
            boolean enGarantia = scanner.nextBoolean();

            String updateQuery = "UPDATE Inventario SET Parte = ?, Precio = ?, CantidadStock = ?, EstadoGarantia = ? WHERE ParteID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, parte);
            preparedStatement.setDouble(2, precio);
            preparedStatement.setInt(3, cantidadStock);
            preparedStatement.setBoolean(4, enGarantia);
            preparedStatement.setInt(5, parteID);
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Parte del inventario actualizada con éxito.");
            } else {
                System.out.println("No se encontró ninguna parte con ese ID.");
            }
        } catch (SQLException e) {
        }
    }
}
