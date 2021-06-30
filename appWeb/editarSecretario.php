<?php 
session_start();
$usuario =$_SESSION['user'];

if(!isset($usuario)){
  header("Location: login.php");
}

include 'modelo/conexiondb.php';
$idUsuario = $_GET['id']; //recepcion de id 

$select = "SELECT rut, nombre, apellidoP, apellidoM, direccion, usuario, contrasena FROM usuarios where id_usuarios = $idUsuario ";
$query = mysqli_query($conexion, $select);
$columna=mysqli_fetch_array($query);
$rut = $columna[0];
$nombre = $columna[1];
$apellidoP = $columna[2];
$apellidoM = $columna[3];
$direccion = $columna[4];
$usuario = $columna[5];
$contrasena = $columna[6];

?>

<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="css/css.css">
  <title>

  </title>
</head>
<body>
  <?php 
include "menu.php";
?>

<div id="registros">
  <h1>Edición de Secretarios</h1>
  <form action="modelo/editarSecretarioModelo.php" method="post">
    <label>Rut</label>
    <input type="number" name="rut" value="<?php echo $rut ?>">
    <label>Nombre</label>
    <input type="text" name="nombre" value="<?php echo $nombre ?>">
    <label>Apellido paterno</label>
    <input type="text" name="apellidoP" value="<?php echo $apellidoP ?>">
    <label>Apellido materno</label>
    <input type="text" name="apellidoM" value="<?php echo $apellidoM ?>">
    <label>Dirreccion</label>
    <input type="text" name="direccion" value="<?php echo $direccion ?>">
    <label>Usuario</label>
    <input type="text" name="usuario" value="<?php echo $usuario ?>">
    <label>Contraseña</label>
    <input type="text" name="contrasena" value="">
 
     <input type="hidden" name="id" value="<?php echo $idUsuario ?>">
    <input type="submit" value="Editar">
    
  </form>
  
</div>

</body>
</html>