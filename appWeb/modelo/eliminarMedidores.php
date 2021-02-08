<?php 
include 'conexiondb.php';
$idMedidor = $_GET['id']; //recepcion de id a eliminar


function obtenerUltimoReg(){
	global $conexion;
	$ultimoId = "SELECT MAX(id_medidores) FROM medidores";// obtener ultimo id
	$query = mysqli_query($conexion, $ultimoId);
	$columna=mysqli_fetch_array($query);
	
	return $columna[0];
}

function eliminarMediores($idMedidor){
	global $conexion;
	$delete = "DELETE FROM medidores WHERE id_medidores = $idMedidor"; //se borra fila
	$query2 = mysqli_query( $conexion, $delete) or die;
}

function actualizarIdRegistros($idMedidor, $ultimoReg ){
	global $conexion;
	$num = $idMedidor + 1;
	
	$newId = $idMedidor;
	while ($num <= $ultimoReg) {
	 	
		$update = "UPDATE medidores SET id_medidores=$newId WHERE id_medidores=$num";
		$query3 = mysqli_query($conexion, $update);
		$newId= $newId +1;
		$num = $num +1;
	}

}

function actualizarAutoIncrement($ultimoReg){
	global $conexion;
	$ai = $ultimoReg - 1;
	
	$resetAI="ALTER TABLE medidores AUTO_INCREMENT = $ai";
	$query3 = mysqli_query($conexion, $resetAI) or die;

}

$ultimoReg = obtenerUltimoReg($conexion);
eliminarMediores($idMedidor);
actualizarIdRegistros($idMedidor, $ultimoReg);
actualizarAutoIncrement($ultimoReg);




?>
<script>window.location.href = "../medidores.php";</script>