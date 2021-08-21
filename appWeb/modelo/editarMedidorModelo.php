<?php 
include 'conexiondb.php';
include 'validaciones.php';

$codigo = $_POST['codigo']; //recepcion de id a actualizar
$marca = $_POST['marca'];
$id = $_POST['id']; //recepcion de id a actualizar

if(limpirarString($codigo) and limpirarString($marca) and limpirarString($id) ){

	$update = "UPDATE medidores SET numero=$codigo WHERE id_medidores=$id";
	$query = mysqli_query($conexion, $update);

	$update2 = "UPDATE medidores SET marca='$marca' WHERE id_medidores=$id";
	$query2 = mysqli_query($conexion, $update2);
}else{
	?> <script>alert('Ingrese sólo números y letras, la ñ reemplácela por una n.')</script><?php
}

?>
<script>window.location.href = "../medidores.php";</script>




