<?php 
include "modelo/conexiondb.php";
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
$selectx="SELECT * FROM datos_cobros";
$queryx=mysqli_query($conexion, $selectx);
$columnax=mysqli_fetch_array($queryx);
$cargoFijo=intval($columnax[1]);
$metrosSub=intval($columnax[2]);
$valorMetro=intval($columnax[3]);

$selecty="SELECT fecha FROM registro_lectura ORDER by id_registro DESC LIMIT 1";
$queryy=mysqli_query($conexion, $selecty);
$columnay=mysqli_fetch_array($queryy);
$ultimaFecha = $columnay[0];
?>
<div id="contenedorCliente">
<h1>Decretos Registrados</h1>

  <div class="tabla">

    <table>
        <tr>
        </tr>
        <tr>
         <th>Nº </th>
         <th>Num. Decreto</th>
         <th>Fecha decreto</th>
         <th>Puntaje</th>
         <th>Fecha encuesta</th>
         <th>Numero Socio</th>
         <th>VNU</th>
         <th>Numero viviendas</th>
         <th>Tipo subsdio(OBS)</th>
         
        
       </tr>

    <?php

    $select="SELECT * FROM decretos"; //Seleccion de clientes registrados.
    $query=mysqli_query($conexion, $select);
    $cont=1;
    while($columna2=mysqli_fetch_array($query)){ 
      
    ?>
       <tr>
        <td><?php echo $columna2[0];?></td>
        <td><?php echo $columna2[1];?></td>
        <td><?php echo $columna2[2];?></td>
        <td><?php echo $columna2[3];?></td>
        <td><?php echo $columna2[4];?></td>
        <td><?php echo $columna2[5];?></td>
        <td><?php echo $columna2[6];?></td>
        <td><?php echo $columna2[7];?></td>
        <!--tipo subsidio-->
        <td><?php echo $columna2[8];?></td>
      </tr>
    <?php $cont=$cont+1;} ?>

    </table>

</div>

</div>

</body>
</html>
