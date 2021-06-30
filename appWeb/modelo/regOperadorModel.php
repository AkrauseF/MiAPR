<?php
include "conexiondb.php";
include "validaciones.php";

/*echo "-rut".$rut = $_POST['rut'];
echo "-nombre".$nombre = $_POST['nombre'];
echo "-apellidoP".$apellidoP = $_POST['apellidoP'];
echo "-apellidoM".$apellidoM = $_POST['apellidoM'];
echo "-direccion".$direccion = $_POST['direccion'];*/
//$subsidio = $_POST['subsidio'];

$rut = $_POST['rut'];
$nombre = $_POST['nombre'];
$apellidoP = $_POST['apellidoP'];
$apellidoM = $_POST['apellidoM'];
$direccion = $_POST['direccion'];
$usuario = $_POST['user'];
$contrasena = $_POST['pass'];
//$id=$_POST['id'];

if(!limpirarString($rut) or !limpirarString($nombre) or !limpirarString($apellidoP) or !limpirarString($apellidoM) or !limpirarString($direccion) or !limpirarString($contrasena)){

	?> <script>alert('Ingrese sólo números y letras, la ñ reemplácela por una n y no utilice tildes.')</script><?php

}elseif(restringeLargo($rut, 15) or restringeLargo($nombre, 15) or restringeLargo($apellidoP, 15) or restringeLargo($apellidoM, 15) or restringeLargo($direccion, 35) or restringeLargo($contrasena, 15)){
	
	?> <script>alert('Uno de los campos supera el limite de caracteres. Intentelo de nuevo')</script><?php

}elseif(!valida_rut($rut)){
	
	?> <script>alert('El rut ingresado no corresponde')</script><?php

}elseif(verificarUsuarioEnDB($usuario)){

		?> <script>alert('El usuario ya existe.')</script><?php

}else{

	//$pass = hash('sha256', $contrasena);

	$insert="INSERT INTO operadores (rut, nombre, apellidoP, apellidoM, direccion, usuario, contrasena)VALUES($rut,'$nombre', '$apellidoP', '$apellidoM','$direccion','$usuario','$contrasena')";

	if($query=mysqli_query($conexion, $insert)){
		?> <script>alert('Usuario creado con exito!.')</script><?php
	}

}
?>
<script>window.location.href = "../operadores.php";</script>