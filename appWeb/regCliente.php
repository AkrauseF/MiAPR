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
<h1>Registro de clientes</h1>
<div id="registros">
  
  <form action="modelo/regClienteModel.php" method="post">
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
    <label>Nº Sitio</label>
    <input type="number" name="numSitio" required="required">
    <label>Medidor</label>
    <select name="medidor" required="required">
        <option value="0">Seleccione:</option>
        <?php
// Realizamos la consulta para extraer los datos
          $select="SELECT * FROM medidores";
          $query=mysqli_query($conexion, $select);

          while($columna=mysqli_fetch_array($query)){

          $select1="SELECT id_medidor FROM clientes where id_medidor=$columna[0]";
          $query1=mysqli_query($conexion, $select1);
          $columna2=mysqli_fetch_array($query1);
          $vacio = "vacio";
          if(empty($columna2[0])){
            echo '<option value="'.$columna[0].'">'.$columna[1].' '.$columna[2].'</option>';

          }else{
            continue;          
          }

          }
        ?>
    </select>
    <br>
    <label>Decreto</label>
    <select name="decreto" required="required">
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
          //if(empty($columna4[0])){
            echo '<option value="'.$columna3[0].'">Número decreto: '.$columna3[1].' - tipo: '.$columna3[8].'</option>';

          //}else{
           // continue;          
          //}

          }
        ?>
    </select> 

    <input type="submit" value="Enviar" required="required">
    
  </form>
  <a href="clientes.php"><button>Clientes Registrados</button></a>
  
</div>

</body>
</html>