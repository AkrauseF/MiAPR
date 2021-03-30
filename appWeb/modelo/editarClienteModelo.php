<?php 
include 'conexiondb.php';
$rut = $_POST['rut']; //recepcion de id a actualizar
$nombre = $_POST['nombre'];
$apellido = $_POST['apellido'];
$direccion = $_POST['direccion'];
$subsidio = $_POST['subsidio'];
$numSitio = $_POST['numSitio'];
$idMedidor = $_POST['medidor'];
$id = $_POST['id']; //recepcion de id a actualizar

/*echo $codigo."\n";
echo $marca."\n";
echo $id."\n";*/

$update = "UPDATE clientes SET rut=$rut WHERE id_cliente=$id";
$query = mysqli_query($conexion, $update);

$update2 = "UPDATE clientes SET nombre='$nombre' WHERE id_cliente=$id";
$query2 = mysqli_query($conexion, $update2);

$update2 = "UPDATE clientes SET apellido='$apellido' WHERE id_cliente=$id";
$query2 = mysqli_query($conexion, $update2);

$update2 = "UPDATE clientes SET direccion='$direccion' WHERE id_cliente=$id";
$query2 = mysqli_query($conexion, $update2);

$update2 = "UPDATE clientes SET subsidio='$subsidio' WHERE id_cliente=$id";
$query2 = mysqli_query($conexion, $update2);

$update2 = "UPDATE clientes SET num_sitio='$numSitio' WHERE id_cliente=$id";
$query2 = mysqli_query($conexion, $update2);

$update2 = "UPDATE clientes SET id_medidor='$idMedidor' WHERE id_cliente=$id";
$query2 = mysqli_query($conexion, $update2);

?>
<script>window.location.href = "../clientes.php";</script>

