<?php 
include 'modelo/conexiondb.php';
$idLectura = $_GET['id']; //recepcion de id 

$select = "SELECT lectura, monto, fecha, metros_cubicos, num_medidor, pago FROM registro_lectura where id_registro = $idLectura ";// obtener ultimo id
$query = mysqli_query($conexion, $select);
$columna=mysqli_fetch_array($query);
$lectura = $columna[0];
$monto = $columna[1];
$fecha = $columna[2];
$metrosC = $columna[3];
$medidor = $columna[4];
$pago = $columna[5];

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
  <h1>Edición de Lecturas</h1>
  <form action="modelo/editarLecturaModelo.php" method="post">
    <label>Lectura</label>
    <input type="number" name="lectura" value="<?php echo $lectura ?>">
     <!--<label>Monto</label>
   <input type="text" name="monto" value="<?php echo $monto ?>"  > -->
    <label>Fecha</label>
    <input type="text" name="fecha" value="<?php echo $fecha ?>" >
   <!-- <label>Metros Cubicos</label> 
    <input type="text" name="metrosC" value="<?php echo $metrosC ?> "readonly>-->
    <label>Medidor</label>
    <input type="number" name="medidor" value="<?php echo $medidor ?>" readonly>
    <!--<label>Pago</label>
    <select name="pago">
        <option value="0">NO</option>
        <option value="1">SI</option>

</select> -->
    
     <input type="hidden" name="id" value="<?php echo $idLectura ?>">
    <input type="submit" value="Editar">
    
  </form>
  
</div>

</body>
</html>