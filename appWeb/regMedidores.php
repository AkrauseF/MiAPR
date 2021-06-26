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
  <h1>Registro de Medidores</h1>

<div id="registros">
  <form action="modelo/regMedidorModel.php" method="post">
    <label>Código</label>
    <input type="number" name="codigo" required="required">
    <label>Marca</label>
    <input type="text" name="marca" required="required">

    <input type="submit" value="Enviar">
    
  </form>
  <a href="medidores.php"><button>Medidores Registrados</button></a>
  
</div>

</body>
</html>