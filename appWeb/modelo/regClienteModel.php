<?php
include "conexiondb.php";
include "validaciones.php";

echo "-rut".$rut = $_POST['rut'];
echo "-nombre".$nombre = $_POST['nombre'];
echo "-apellidoP".$apellidoP = $_POST['apellidoP'];
echo "-apellidoM".$apellidoM = $_POST['apellidoM'];
echo "-direccion".$direccion = $_POST['direccion'];
//$subsidio = $_POST['subsidio'];
echo "-numSitio".$numSitio = $_POST['numSitio'];
echo "-id_medidor".$id_medidor = $_POST['medidor'];
echo "-id_decreto".$id_decreto = $_POST['decreto'];
$id=$_POST['id'];

if(!limpirarString($rut) or !limpirarString($nombre) or !limpirarString($apellidoP) or !limpirarString($apellidoM) or !limpirarString($direccion) or !limpirarString($numSitio) or !limpirarString($idMedidor) or !limpirarString($id_decreto)){

	?> <script>alert('Ingrese sólo números y letras, la ñ reemplácela por una n.')</script><?php

}elseif(restringeLargo($rut, 10) or restringeLargo($nombre, 5) or restringeLargo($apellidoP, 10) or restringeLargo($apellidoM, 10) or restringeLargo($direccion, 10) or restringeLargo($numSitio, 10) or restringeLargo($idMedidor, 10) or restringeLargo($idDecreto, 10)){
	
	?> <script>alert('Uno de los campos supera el limite de caracteres. Intentelo de nuevo')</script><?php
}else{

	$insert="INSERT INTO clientes (rut, nombre, apellido_p, apellido_m, direccion, num_sitio, id_medidor, id_decreto)VALUES($rut,'$nombre', '$apellidoP', '$apellidoM','$direccion',$numSitio,$id_medidor, $id_decreto)";
	$query=mysqli_query($conexion, $insert);
}
?>

<script>window.location.href = "../clientes.php";</script>
