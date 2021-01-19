<?php 
include 'modelo/conexiondb.php';
$idMedidor = $_GET['id']; //recepcion de id 

$select = "SELECT numero, marca FROM medidores where id_medidores = $idMedidor ";// obtener ultimo id
$query = mysqli_query($conexion, $select);
$columna=mysqli_fetch_array($query);
$codigo = $columna[0];
$marca = $columna[1];
?>

<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="css/css.css">
  <title>

  </title>
</head>
<body>


<div id="regMedidores">

  <form action="modelo/editarMedidorModelo.php" method="post">
    <label>Código</label>
    <input type="number" name="codigo" value="<?php echo $codigo ?>">
    <label>Marca</label>
    <input type="text" name="marca" value="<?php echo $marca ?>">
    
     <input type="hidden" name="id" value="<?php echo $idMedidor ?>">
    <input type="submit" value="Editar">
    
  </form>
  
</div>

</body>
</html>