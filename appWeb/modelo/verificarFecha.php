<?php 
include "conexiondb.php";

function consultaUltimoIdRegistroLectura(){
	global $conexion;
	$selectUltId="SELECT MAX(id_registro) FROM registro_lectura";
	$query=mysqli_query($conexion, $selectUltId);
	$colum = mysqli_fetch_array($query);

	return $colum[0];
}

function verificarFecha(){
	//$fecha = "2021-10-27";
	$fecha = $_POST["fecha"];

	$ultId = consultaUltimoIdRegistroLectura();
	global $conexion;

	$ultId = consultaUltimoIdRegistroLectura();
	global $conexion;

	$verificaVacia="SELECT COUNT(*) as total FROM registro_lectura";
	$query1=mysqli_query($conexion, $verificaVacia);
	$columna2= mysqli_fetch_array($query1);

	if($columna2[0]==0){
		echo "0";
	}else{
		$select="SELECT fecha FROM registro_lectura WHERE $ultId=id_registro";
		$query=mysqli_query($conexion, $select);
		$colum=mysqli_fetch_array($query);
		
		if($colum[0]== $fecha){
			echo "1";
		}else{
			echo "0";
		}
	}

}

verificarFecha();

?>