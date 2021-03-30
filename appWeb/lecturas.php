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
?>
<div id="contenedorCliente">
<h1>Lecturas registradas</h1>

  <div class="tabla">

    <table>

        <tr>
         <th>Nº </th>
        <th>Lectura </th> 
         <!-- <th>Monto</th>-->
         <th>Fecha</th>
        <!-- <th>Metros Cubicos</th>-->
         <th>Medidor</th>
        <!-- <th>Pago</th>-->

         <!---<th><a href="regMedidores.php"><button>Registrar</button></a></th>-->
         
        
       </tr>

    <?php 

    $select="SELECT * FROM registro_lectura";
    $query=mysqli_query($conexion, $select);

   
    while($columna=mysqli_fetch_array($query)){ 

    ?>
      

       <tr>
        <td><?php echo $columna[0];?></td>
       <td><?php echo $columna[1];?></td>
        <!--<td><?php echo $columna[2];?></td>-->
        <td><?php echo $columna[3];?></td>
      <!-- <td><?php echo $columna[4];?></td>-->
        <td><?php echo $columna[5];?></td>
       <!-- <td><?php //echo $columna[6];?></td>-->
        
        <td><a href="editarLectura.php?id=<?php echo $columna[0];?>"><button>Editar</button></a>
        <a href="modelo/eliminarLecturas.php?id=<?php echo $columna[0];?>"><button>Borrar</button></a></td>

      </tr>
    <?php } ?>

    </table>
  </div>
</div>

</body>
</html>