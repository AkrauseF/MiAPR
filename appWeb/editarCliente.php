<?php 
include 'modelo/conexiondb.php';
$idCliente = $_GET['id']; //recepcion de id 

$select = "SELECT rut, nombre, apellido, direccion, subsidio, num_sitio, id_medidor FROM clientes where id_cliente = $idCliente ";// obtener ultimo id
$query = mysqli_query($conexion, $select);
$columna=mysqli_fetch_array($query);
$rut = $columna[0];
$nombre = $columna[1];
$apellido = $columna[2];
$direccion = $columna[3];
$subsidio = $columna[4];
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
    <label>Apellido</label>
    <input type="text" name="apellido" value="<?php echo $apellido ?>">
    <label>Dirreccion</label>
    <input type="text" name="direccion" value="<?php echo $direccion ?>">
    <label>Subsidio</label>
    <select name="subsidio">
      <option value="<?php echo $subsidio ?>"><?php echo $subsidio ?></option>
        <option value="0">0</option>
        <option value="50">50</option>
        <option value="100">100</option>
</select>
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
     <input type="hidden" name="id" value="<?php echo $idCliente ?>">
    <input type="submit" value="Editar">
    
  </form>
  
</div>

</body>
</html>