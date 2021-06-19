<?php
include "conexiondb.php";
include "validaciones.php";

/*echo "-rut".$rut = $_POST['rut'];
echo "-nombre".$nombre = $_POST['nombre'];
echo "-apellidoP".$apellidoP = $_POST['apellidoP'];
echo "-apellidoM".$apellidoM = $_POST['apellidoM'];
echo "-direccion".$direccion = $_POST['direccion'];*/
//$subsidio = $_POST['subsidio'];
$numSitio = $_POST['numSitio'];
$id_medidor = $_POST['medidor'];
$id_decreto = $_POST['decreto'];
$rut = $_POST['rut'];
$nombre = $_POST['nombre'];
$apellidoP = $_POST['apellidoP'];
$apellidoM = $_POST['apellidoM'];
$direccion = $_POST['direccion'];
//$subsidio = $_POST['subsidio'];
$numSitio = $_POST['numSitio'];
$id_medidor = $_POST['medidor'];
$id_decreto = $_POST['decreto'];
//$id=$_POST['id'];

if(!limpirarString($rut) or !limpirarString($nombre) or !limpirarString($apellidoP) or !limpirarString($apellidoM) or !limpirarString($direccion) or !limpirarString($numSitio)){

	?> <script>alert('Ingrese sólo números y letras, la ñ reemplácela por una n y no utilice tildes.')</script><?php

}elseif(restringeLargo($rut, 15) or restringeLargo($nombre, 15) or restringeLargo($apellidoP, 15) or restringeLargo($apellidoM, 15) or restringeLargo($direccion, 35) or restringeLargo($numSitio, 15)){
	
	?> <script>alert('Uno de los campos supera el limite de caracteres. Intentelo de nuevo')</script><?php
}else{

	$insert="INSERT INTO clientes (rut, nombre, apellido_p, apellido_m, direccion, num_sitio, id_medidor, id_decreto)VALUES($rut,'$nombre', '$apellidoP', '$apellidoM','$direccion',$numSitio,$id_medidor, $id_decreto)";
	$query=mysqli_query($conexion, $insert);
}
?>
<script>window.location.href = "../clientes.php";</script>


