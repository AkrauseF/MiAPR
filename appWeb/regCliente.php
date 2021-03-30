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

<div id="registros">
  <h1>Registro de clientes</h1>
  <form action="modelo/regClienteModel.php" method="post">
    <label>Rut</label>
    <input type="number" name="rut">
    <label>Nombre</label>
    <input type="text" name="nombre">
    <label>Apellido</label>
    <input type="text" name="apellido">
    <label>Direccion</label>
    <input type="text" name="direccion">
    <label>Subsidio</label>
    <select name="subsidio">
        <option value="0">0</option>
        <option value="50">50</option>
        <option value="100">100</option>
    </select>    
    <label>Nº Sitio</label>
    <input type="text" name="numSitio">

    <label>Medidor</label>
    <select name="medidor">
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

    <input type="submit" value="Enviar">
    
  </form>
  <a href="clientes.php"><button>Clientes Registrados</button></a>
  
</div>

</body>
</html>