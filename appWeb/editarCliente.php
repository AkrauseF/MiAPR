<?php 
session_start();
$usuario =$_SESSION['user'];

if(!isset($usuario)){
  header("Location: login.php");
}

include 'modelo/conexiondb.php';
$idCliente = $_GET['id']; //recepcion de id 

$select = "SELECT rut, nombre, apellido_p, apellido_m, direccion, num_sitio, id_medidor, id_decreto FROM clientes where id_cliente = $idCliente ";// obtener ultimo id
$query = mysqli_query($conexion, $select);
$columna=mysqli_fetch_array($query);
$rut = $columna[0];
$nombre = $columna[1];
$apellidoP = $columna[2];
$apellidoM = $columna[3];
$direccion = $columna[4];
$numSitio = $columna[5];

$select3 = "SELECT numero, marca FROM medidores where id_medidores = $columna[6] ";// obtener ultimo id
$query3 = mysqli_query($conexion, $select3);
$columna3=mysqli_fetch_array($query3);

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
  <h1>Edición de clientes</h1>
  <form action="modelo/editarClienteModelo.php" method="post">
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
    <!--<label>Subsidio</label>
    <select name="subsidio">
      <option value="<?php echo $subsidio ?>"><?php echo $subsidio ?></option>
        <option value="0">0</option>
        <option value="10">10</option>
        <option value="20">20</option>
</select>-->
    <label>Nº de sitio</label>
    <input type="number" name="numSitio" value="<?php echo $numSitio ?>">
    <label>Medidor</label>
<select name="medidor">
          <?php

         echo '<option name="medidor" value="'.$columna[0].'">'.$columna3[0].' '.$columna3[1].'</option>';
// Realizamos la consulta para extraer los datos
          $select="SELECT * FROM medidores";
          $query=mysqli_query($conexion, $select);

          while($columna=mysqli_fetch_array($query)){

          $select1="SELECT id_medidor FROM clientes where id_medidor=$columna[0]";
          $query1=mysqli_query($conexion, $select1);
          $columna2=mysqli_fetch_array($query1);
          $vacio = "vacio";
          if(empty($columna2[0])){
            echo '<option name="medidor" value="'.$columna[0].'">'.$columna[1].' '.$columna[2].'</option>';

          }else{
            continue;          
          }

          }
        ?>
</select>
<label>Decreto</label>
    <select name="decreto">
      <option value="1">Sin decreto:</option>
        <?php
// Realizamos la consulta para extraer los datos
          $select3="SELECT * FROM decretos";
          $query3=mysqli_query($conexion, $select3);

          while($columna3=mysqli_fetch_array($query3)){

          $select4="SELECT id_decreto FROM clientes where id_decreto=$columna3[0]";
          $query4=mysqli_query($conexion, $select4);
          $columna4=mysqli_fetch_array($query4);
          $vacio = "vacio";
          if(empty($columna4[0])){
            echo '<option value="'.$columna3[0].'">Número decreto: '.$columna3[1].' - tipo: '.$columna3[8].'</option>';

          }else{
            continue;          
          }

          }
        ?>
    </select> 
     <input type="hidden" name="id" value="<?php echo $idCliente ?>">
    <input type="submit" value="Editar">
    
  </form>
  
</div>

</body>
</html>