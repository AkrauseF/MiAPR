<?php 
include 'conexiondb.php';
$idLectura = $_GET['id']; //recepcion de id a eliminar
//echo 'recepcion: '.$idLectura;
//echo "";

function obtenerUltimoReg(){
	global $conexion;
	$ultimoId = "SELECT MAX(id_registro) FROM registro_lectura";// obtener ultimo id
	$query = mysqli_query($conexion, $ultimoId);
	$columna=mysqli_fetch_array($query);
//	echo 'Ultimo id: '.$columna[0];
//	echo "";
	return $columna[0];
}

function eliminarMediores($idLectura){
	global $conexion;
	$delete = "DELETE FROM registro_lectura WHERE id_registro = $idLectura"; //se borra fila
	$query2 = mysqli_query( $conexion, $delete) or die;
}

function actualizarIdRegistros($idLectura, $ultimoReg ){
	global $conexion;
	$num = $idLectura + 1;
//	echo 'num: '.$num;
//	echo "";
	$newId = $idLectura;
	while ($num <= $ultimoReg) {
//	 	echo 'newID: '.$newId;
		
//		echo 'num: '.$num;
		$update = "UPDATE registro_lectura SET id_registro=$newId WHERE id_registro=$num";
		$query3 = mysqli_query($conexion, $update);
		$newId= $newId +1;
		$num = $num +1;
	}

}

function actualizarAutoIncrement($ultimoReg){
	global $conexion;
	$ai = $ultimoReg - 1;
//	echo 'AI: '.$ai;
	$resetAI="ALTER TABLE registro_lectura AUTO_INCREMENT = $ai";
	$query3 = mysqli_query($conexion, $resetAI) or die;

}

$ultimoReg = obtenerUltimoReg($conexion);
eliminarMediores($idLectura);
actualizarIdRegistros($idLectura, $ultimoReg);
actualizarAutoIncrement($ultimoReg);




?>
<!--<script>window.location.href = "../lecturas.php";</script> -->
<script>window.location.href = "../lecturas.php";</script>