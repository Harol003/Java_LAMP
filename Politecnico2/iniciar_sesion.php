<?php
session_start();

// Verificar si el usuario ya está autenticado, redirigir a Todolist si es así
if (isset($_SESSION['user_id'])) {
    header("Location: crud2.php");
    exit();
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $user = "example_user";
    $password = "password";
    $database = "example_database";
    $table = "users";

    try {
        $db = new PDO("mysql:host=localhost;dbname=$database", $user, $password);
        $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        // Obtener datos del formulario
        $username = $_POST['username'];
        $password = $_POST['password'];

        // Consulta para obtener el usuario
        $query = $db->prepare("SELECT * FROM $table WHERE username = :username");
        $query->bindParam(':username', $username);
        $query->execute();
        $userRow = $query->fetch(PDO::FETCH_ASSOC);

        // Verificar la contraseña utilizando password_verify
        if ($userRow && password_verify($password, $userRow['password'])) {
            // Credenciales válidas, establecer la sesión y redirigir a Todolist
            $_SESSION['user_id'] = $userRow['user_id'];
            header("Location: crud2.php");
            exit();
        } else {
            $error = "Usuario o contraseña incorrectos";
        }

    } catch (PDOException $e) {
        $error = "Error de conexión a la base de datos";
    }
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Acceso</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-color: #f4f4f4;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        input {
            margin-bottom: 10px;
            padding: 8px;
            width: 100%;
        }

        button {
            background-color: #4caf50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>

<!-- Navbar -->
<nav>
    <a href="index.html">Inicio</a>
    <a href="formulario_registro.php">Registro de usuarios</a>
    <a href="iniciar_sesion.php">Login</a>
    <a href="Informacion.html">Informacion</a>
</nav>

<body>

<form method="post" action="">
    <h2>Inicio de Sesión</h2>

    <?php
    if (isset($error)) {
        echo "<p style='color: red;'>$error</p>";
    }
    ?>

    <label for="username">Usuario:</label>
    <input type="text" id="username" name="username" required>

    <label for="password">Contraseña:</label>
    <input type="password" id="password" name="password" required>

    <button type="submit">Iniciar Sesión</button>
</form>

<!-- Footer -->
<footer>
    <p>&copy; <?php echo date("Y"); ?> Programación II | Politecnico Internacional </p>
</footer>

</body>
</html>
