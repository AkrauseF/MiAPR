<?php 
include "modelo/conexiondb.php";

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
?>
<div id="contenedorCliente">
<h1>Clientes registrados</h1>

  <div class="tabla">

    <table>
        <tr>
        </tr>
        <tr>
         <th>Nº </th>
         <th>Rut </th>
         <th>Nombre</th>
         <th>Apellido Paterno</th>
         <th>Apellido Materno</th>
         <th>Direccion</th>
         <th>Subsidio</th>
         <th>Medidor Asociado</th>
         <th>Nº de sitio</th>
         <th><a href="regCliente.php"><button>Registrar</button></a></th>
         
        
       </tr>

    <?php 
    $select="SELECT * FROM clientes";
    $query=mysqli_query($conexion, $select);
    while($columna=mysqli_fetch_array($query)){ 

    $select1="SELECT numero FROM medidores WHERE id_medidores=$columna[7]";
    $query2=mysqli_query($conexion, $select1);
    $columna2=mysqli_fetch_array($query2);

    $select3="SELECT tipo_sub FROM decretos WHERE id_decreto=$columna[8]";
      $query3=mysqli_query($conexion, $select3);
      $columna3=mysqli_fetch_array($query3);
    ?>
       <tr>
        <td><?php echo $columna[0];?></td>
        <td><?php echo $columna[1];?></td>
        <td><?php echo $columna[2];?></td>
        <td><?php echo $columna[3];?></td>
        <td><?php echo $columna[4];?></td>
        <td><?php echo $columna[5];?></td>
        <td><?php echo $columna3[0];?></td>
        <td><?php echo $columna2[0];?></td>
        <td><?php echo $columna[6];?></td>

       <td><a href="editarCliente.php?id=<?php echo $columna[0];?>"><button>Editar</button></a>
          <a href="modelo/eliminarClientes.php?id=<?php echo $columna[0];?>"><button>Borrar</button></a></td>

      </tr>
    <?php } ?>

    </table>

  </div>
</div>

</body>
</html>