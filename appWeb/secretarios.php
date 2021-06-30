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
<h1>Secreatarios registrados</h1>

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
         <th>Usuario</th>
         <th>Contraseña</th>

         <th><a href="registroSecretarios.php"><button>Registrar</button></a></th>
         
        
       </tr>

    <?php 
    $select="SELECT * FROM usuarios";
    $query=mysqli_query($conexion, $select);
    while($columna=mysqli_fetch_array($query)){ 

    ?>
       <tr>
        <td><?php echo $columna[0];?></td>
        <td><?php echo $columna[1];?></td>
        <td><?php echo $columna[2];?></td>
        <td><?php echo $columna[3];?></td>
        <td><?php echo $columna[4];?></td>
        <td><?php echo $columna[5];?></td>
        <td><?php echo $columna[6];?></td>
        <td><?php echo $columna[7];?></td>

       <td><a href="editarSecretario.php?id=<?php echo $columna[0];?>"><button>Editar</button></a>
          <a href="modelo/eliminarSecretarios.php?id=<?php echo $columna[0];?>"><button>Borrar</button></a></td>

      </tr>
    <?php } ?>

    </table>

  </div>
</div>

</body>
</html>