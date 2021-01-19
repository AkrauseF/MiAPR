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

<div id="contenedorCliente">
  <h1>Medidores registrados</h1>

  <div class="tabla">

    <table>

        <tr>
         <th>Nº </th>
         <th>Codigo </th>
         <th>Marca</th>
         <th></th>
         
        
       </tr>

    <?php 
    $select="SELECT * FROM medidores";
    $query=mysqli_query($conexion, $select);
    while($columna=mysqli_fetch_array($query)){ 

    ?>
      

       <tr>
        <td><?php echo $columna[0];?></td>
        <td><?php echo $columna[1];?></td>
        <td><?php echo $columna[2];?></td>
        <td><a href="editarMedidor.php?id=<?php echo $columna[0];?>"><button>Editar</button></a>
          <a href="modelo/eliminarMedidores.php?id=<?php echo $columna[0];?>"><button>Borrar</button></a></td>

      </tr>
    <?php } ?>

    </table>
  </div>
</div>

</body>
</html>