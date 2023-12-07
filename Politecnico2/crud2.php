<?php
$user = "example_user";
$password = "password";
$database = "example_database";
$table = "todo_list";

try {
    $db = new PDO("mysql:host=localhost;dbname=$database", $user, $password);
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Función para leer y mostrar todos los elementos de la tabla
    function readData($db, $table)
    {
        echo "<h2>TODO</h2><ol>";
        $query = $db->query("SELECT * FROM $table");
        while ($row = $query->fetch(PDO::FETCH_ASSOC)) {
            echo "<li>" . $row['content'] . " 
            <a href='?action=edit&id=" . $row['item_id'] . "'>Editar</a> 
            <a href='?action=delete&id=" . $row['item_id'] . "'>Eliminar</a></li>";
        }
        echo "</ol>";
    }

    // Función para insertar un nuevo elemento en la tabla
    function insertData($db, $table, $content)
    {
        $stmt = $db->prepare("INSERT INTO $table (content) VALUES (:content)");
        $stmt->bindParam(':content', $content);
        $stmt->execute();
    }

    // Función para actualizar un elemento en la tabla
    function updateData($db, $table, $id, $content)
    {
        $stmt = $db->prepare("UPDATE $table SET content = :content WHERE item_id = :id");
        $stmt->bindParam(':content', $content);
        $stmt->bindParam(':id', $id);
        $stmt->execute();
    }

    // Función para eliminar un elemento de la tabla
    function deleteData($db, $table, $id)
    {
        $stmt = $db->prepare("DELETE FROM $table WHERE item_id = :id");
        $stmt->bindParam(':id', $id);
        $stmt->execute();
    }

    // Manejo de acciones
    if (isset($_GET['action'])) {
        switch ($_GET['action']) {
            case 'delete':
                if (isset($_GET['id'])) {
                    deleteData($db, $table, $_GET['id']);
                }
                break;
            case 'edit':
                if (isset($_GET['id'])) {
                    // Aquí puedes redirigir a otra página para la edición o implementar un formulario de edición aquí mismo.
                    // En este ejemplo, simplemente mostrará el contenido del elemento seleccionado.
                    $query = $db->prepare("SELECT * FROM $table WHERE item_id = :id");
                    $query->bindParam(':id', $_GET['id']);
                    $query->execute();
                    $row = $query->fetch(PDO::FETCH_ASSOC);
                    echo "<h2>Editar tarea</h2>
                          <form method='post' action=''>
                              <input type='text' name='content' value='" . $row['content'] . "'>
                              <input type='hidden' name='id' value='" . $row['item_id'] . "'>
                              <input type='submit' name='update' value='Actualizar'>
                          </form>";
                }
                break;
        }
    }

    // Procesamiento del formulario de inserción o actualización
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        if (isset($_POST['insert'])) {
            insertData($db, $table, $_POST['content']);
        } elseif (isset($_POST['update'])) {
            updateData($db, $table, $_POST['id'], $_POST['content']);
        }
    }

} catch (PDOException $e) {
    print "Error!: " . $e->getMessage() . "<br/>";
    die();
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo List App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        nav {
            background-color: #333;
            overflow: hidden;
            color: white;
            text-align: center;
            padding: 14px 0;
        }

        nav a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        nav a:hover {
            background-color: #ddd;
            color: black;
        }

        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>

<body>

<!-- Navbar -->
<nav>
    <a href="index.html">Inicio</a>
    <a href="Informacion.html">Informacion</a>
</nav>



<!-- Contenido de la página (Todo List) -->
<div style="padding: 16px;">
    <?php
    // Mostrar la lista actualizada después de realizar operaciones CRUD
    readData($db, $table);
    ?>

    <!-- Formulario para insertar nuevos elementos -->
    <h2>Agregar nueva tarea</h2>
    <form method="post" action="">
        <input type="text" name="content" placeholder="Nueva tarea">
        <input type="submit" name="insert" value="Agregar">
    </form>
</div>

<!-- Footer -->
<footer>
    <p>&copy; <?php echo date("Y"); ?> Programación II | Politecnico Internacional </p>
</footer>

</body>
</html>
