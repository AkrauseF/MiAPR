<?php 
include 'conexiondb.php';
include 'validaciones.php';

$rut = $_POST['rut']; //recepcion de id a actualizar
$nombre = $_POST['nombre'];
$apellidoP = $_POST['apellidoP'];
$apellidoM = $_POST['apellidoM'];
$direccion = $_POST['direccion'];
$usuario = $_POST['usuario'];
$contrasena = $_POST['contrasena'];
$id = $_POST['id'];
 //recepcion de id a actualizar

if(!limpirarString($rut) or !limpirarString($nombre) or !limpirarString($apellidoP) or !limpirarString($apellidoM) or !limpirarString($direccion) or !limpirarString($numSitio)){

	?> <script>alert('Ingrese sólo números y letras, la ñ reemplácela por una n y no utilice tildes.')</script><?php

}elseif(restringeLargo($rut, 15) or restringeLargo($nombre, 15) or restringeLargo($apellidoP, 15) or restringeLargo($apellidoM, 15) or restringeLargo($direccion, 35) or restringeLargo($numSitio, 15)){
	
	?> <script>alert('Uno de los campos supera el limite de caracteres. Intentelo de nuevo')</script><?php
}else{
	
	$update = "UPDATE usuarios SET rut=$rut WHERE id_usuarios=$id";
	$query = mysqli_query($conexion, $update);

	$update2 = "UPDATE usuarios SET nombre='$nombre' WHERE id_usuarios=$id";
	$query2 = mysqli_query($conexion, $update2);

	$update2 = "UPDATE usuarios SET apellidoP='$apellidoP' WHERE id_usuarios=$id";
	$query2 = mysqli_query($conexion, $update2);

	$update2 = "UPDATE usuarios SET apellidoP='$apellidoM' WHERE id_usuarios=$id";
	$query2 = mysqli_query($conexion, $update2);

	$update2 = "UPDATE usuarios SET direccion='$direccion' WHERE id_usuarios=$id";
	$query2 = mysqli_query($conexion, $update2);

	$update2 = "UPDATE usuarios SET usuario='$usuario' WHERE id_usuarios=$id";
	$query2 = mysqli_query($conexion, $update2);

	$pass = hash('sha256', $contrasena);

	$update2 = "UPDATE usuarios SET contrasena='$pass' WHERE id_usuarios=$id";
	$query2 = mysqli_query($conexion, $update2);

}

?>
<script>window.location.href = "../secretarios.php";</script>



