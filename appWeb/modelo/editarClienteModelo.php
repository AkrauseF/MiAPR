<?php 
include 'conexiondb.php';
include 'validaciones.php';

$rut = $_POST['rut']; //recepcion de id a actualizar
$nombre = $_POST['nombre'];
$apellidoP = $_POST['apellidoP'];
$apellidoM = $_POST['apellidoM'];
$direccion = $_POST['direccion'];
//$subsidio = $_POST['subsidio'];
$numSitio = $_POST['numSitio'];
$idMedidor = $_POST['medidor'];
$idDecreto = $_POST['decreto'];
$id = $_POST['id'];
 //recepcion de id a actualizar

if(!limpirarString($rut) or !limpirarString($nombre) or !limpirarString($apellidoP) or !limpirarString($apellidoM) or !limpirarString($direccion) or !limpirarString($numSitio) or !limpirarString($idMedidor) or !limpirarString($idDecreto)){

	?> <script>alert('Ingrese sólo números y letras, la ñ reemplácela por una n.')</script><?php

}elseif(restringeLargo($rut, 10) or restringeLargo($nombre, 5) or restringeLargo($apellidoP, 10) or restringeLargo($apellidoM, 10) or restringeLargo($direccion, 10) or restringeLargo($numSitio, 10) or restringeLargo($idMedidor, 10) or restringeLargo($idDecreto, 10)){
	
	?> <script>alert('Uno de los campos supera el limite de caracteres. Intentelo de nuevo')</script><?php
}else{
	
	$update = "UPDATE clientes SET rut=$rut WHERE id_cliente=$id";
	$query = mysqli_query($conexion, $update);

	$update2 = "UPDATE clientes SET nombre='$nombre' WHERE id_cliente=$id";
	$query2 = mysqli_query($conexion, $update2);

	$update2 = "UPDATE clientes SET apellido_p='$apellidoP' WHERE id_cliente=$id";
	$query2 = mysqli_query($conexion, $update2);

	$update2 = "UPDATE clientes SET apellido_m='$apellidoM' WHERE id_cliente=$id";
	$query2 = mysqli_query($conexion, $update2);

	$update2 = "UPDATE clientes SET direccion='$direccion' WHERE id_cliente=$id";
	$query2 = mysqli_query($conexion, $update2);

	//$update2 = "UPDATE clientes SET subsidio='$subsidio' WHERE id_cliente=$id";
	//$query2 = mysqli_query($conexion, $update2);

	$update2 = "UPDATE clientes SET num_sitio='$numSitio' WHERE id_cliente=$id";
	$query2 = mysqli_query($conexion, $update2);

	$update2 = "UPDATE clientes SET id_medidor='$idMedidor' WHERE id_cliente=$id";
	$query2 = mysqli_query($conexion, $update2);

	$update2 = "UPDATE clientes SET id_decreto='$idDecreto' WHERE id_cliente=$id";
	$query2 = mysqli_query($conexion, $update2);
	
}

?>
<script>window.location.href = "../clientes.php";</script>



