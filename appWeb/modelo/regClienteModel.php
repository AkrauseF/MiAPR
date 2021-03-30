<?php
include "conexiondb.php";

$rut = $_POST['rut'];
$nombre = $_POST['nombre'];
$apellido = $_POST['apellido'];
$direccion = $_POST['direccion'];
$subsidio = $_POST['subsidio'];
$numSitio = $_POST['numSitio'];
$id_medidor = $_POST['medidor'];
/*echo $rut.";";
echo $nombre.";";
echo $apellido.";";
echo $direccion.";";
echo $subsidio.";";
echo $numSitio.";";
echo $id_medidor.";";*/


$insert="INSERT INTO clientes (rut, nombre, apellido, direccion, subsidio, num_sitio, id_medidor)VALUES($rut,'$nombre', '$apellido','$direccion', '$subsidio',$numSitio,$id_medidor)";
$query=mysqli_query($conexion, $insert);



?>
<!--<script>window.location.href = "../clientes.php";</script>-->