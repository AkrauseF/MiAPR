<?php 
include 'conexiondb.php';

function obtenerUltimoReg(){
	global $conexion;
	$ultimoId = "SELECT MAX(id_medidores) FROM medidores";// obtener ultimo id
	$query = mysqli_query($conexion, $ultimoId);
	$columna=mysqli_fetch_array($query);
	echo 'Ultimo id: '.$columna[0];
	echo "";
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
	echo 'num: '.$num;
	echo "";
	$newId = $idMedidor;
	while ($num <= $ultimoReg) {
	 	echo 'newID: '.$newId;
		
		echo 'num: '.$num;
		$update = "UPDATE medidores SET id_medidores=$newId WHERE id_medidores=$num";
		$query3 = mysqli_query($conexion, $update);
		$newId= $newId +1;
		$num = $num +1;
	}

}

function actualizarAutoIncrement($ultimoReg){
	global $conexion;
	$ai = $ultimoReg - 1;
	echo 'AI: '.$ai;
	$resetAI="ALTER TABLE medidores AUTO_INCREMENT = $ai";
	$query3 = mysqli_query($conexion, $resetAI) or die;

}

//Codigo para  el test::

$i=1;

while ( $i<= 10) {

	$ultimoReg = obtenerUltimoReg($conexion);
	eliminarMediores($ultimoReg);
	actualizarIdRegistros($i, $ultimoReg);
	actualizarAutoIncrement($ultimoReg);

	$i++;
}

?>
<script>window.location.href = "../medidores.php";</script>