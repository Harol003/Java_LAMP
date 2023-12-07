<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $user = "example_user";
    $password = "password";
    $database = "example_database";
    $table = "users";

    try {
        $db = new PDO("mysql:host=localhost;dbname=$database", $user, $password);
        $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $username = $_POST['username'];
        $password = $_POST['password'];

        // Hash de la contraseña
        $hashedPassword = password_hash($password, PASSWORD_DEFAULT);

        // Insertar usuario en la base de datos
        $query = $db->prepare("INSERT INTO $table (username, password) VALUES (:username, :password)");
        $query->bindParam(':username', $username);
        $query->bindParam(':password', $hashedPassword);
        $query->execute();

        echo "Usuario registrado exitosamente.";

    } catch (PDOException $e) {
        echo "Error de conexión a la base de datos: " . $e->getMessage();
    }
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Usuario</title>
    <style>
        /* Estilos del formulario (pueden personalizarse según tus necesidades) */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        nav {
            background-color: #ddd;
            padding: 10px;
        }

        nav a {
            margin: 0 10px;
            text-decoration: none;
            color: #333;
        }

        footer {
            background-color: #ddd;
            padding: 10px;
            text-align: center;
        }
    </style>
</head>

<body>

<!-- Navbar -->
<nav>
    <a href="index.html">Inicio</a>
    <a href="formulario_registro.php">Registro de usuarios</a>
    <a href="iniciar_sesion.php">Login</a>
    <a href="Informacion.html">Informacion</a>
</nav>
 
<form method="post" action="">
    <h2>Registro de Usuario</h2>

    <label for="username">Usuario:</label>
    <input type="text" id="username" name="username" required>

    <label for="password">Contraseña:</label>
    <input type="password" id="password" name="password" required>

    <button type="submit">Registrarse</button>
</form>

<!-- Footer -->
<footer>
    <p>&copy; <?php echo date("Y"); ?> Programación II | Politécnico Internacional </p>
</footer>

</body>
</html>
