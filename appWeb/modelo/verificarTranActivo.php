<?php
error_reporting(0);
include "conexiondb.php";

$var = $_GET["var"];


function obtenerActivador(){
	global $conexion;

	$select="SELECT valor FROM control_tran WHERE id=1";
	$query1=mysqli_query($conexion, $select);
	$row= mysqli_fetch_array($query1);
	
	return $row[0];

}

if($var=="xo"){
	echo obtenerActivador();
}else{
	
}


?>