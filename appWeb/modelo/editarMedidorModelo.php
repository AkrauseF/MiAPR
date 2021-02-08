<?php 
include 'conexiondb.php';
$codigo = $_POST['codigo']; //recepcion de id a actualizar
$marca = $_POST['marca'];
$id = $_POST['id']; //recepcion de id a actualizar


$update = "UPDATE medidores SET numero=$codigo WHERE id_medidores=$id";
$query = mysqli_query($conexion, $update);

$update2 = "UPDATE medidores SET marca='$marca' WHERE id_medidores=$id";
$query2 = mysqli_query($conexion, $update2);

?>
<script>window.location.href = "../medidores.php";</script>




