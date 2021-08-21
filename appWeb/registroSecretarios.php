<?php 
session_start();
$usuario =$_SESSION['user'];

if(!isset($usuario)){
  header("Location: login.php");
}
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
include "modelo/conexiondb.php";
?>
<h1>Registro de Secreatrios</h1>
<div id="registros">
  
  <form action="modelo/regSecretarioModel.php" method="post">
    <label>Rut</label>
    <input type="number" name="rut" required="required">
    <label>Nombre</label>
    <input type="text" name="nombre" required="required">
    <label>Apellido Paterno</label>
    <input type="text" name="apellidoP" required="required">
    <label>Apellido Materno</label>
    <input type="text" name="apellidoM" required="required">
    <label>Direccion</label>
    <input type="text" name="direccion" required="required">   
    <label>Usuario</label>
    <input type="text" name="user" required="required">
    <label>Contraseña</label>
    <input type="text" name="pass" required="required">

    <input type="submit" value="Enviar">
    
  </form>
  
</div>

</body>
</html>