<?php

include "conexiondb.php";


function insertarDatos(){
	global $conexion;

	/*$lectura=123412;
	$monto=1200;
	$fecha="2021-02-12";
	$metros_cubicos=4;
	$num_medidor=1111;*/

	$lectura=(int)$_POST['lectura'];
	$monto=(int)$_POST['monto'];
	$fecha=$_POST['fecha'];
	$metros_cubicos=(int)$_POST['metros_cubicos'];
	$num_medidor=(int)$_POST['num_medidor'];


	$insert="INSERT INTO registro_lectura (lectura, monto, fecha, metros_cubicos, num_medidor, pago)VALUES($lectura,$monto, '$fecha', $metros_cubicos, $num_medidor, 0)";

		$query=mysqli_query($conexion, $insert);
		echo "Lectura de medidor ".$num_medidor." cargada";

	
}
insertarDatos();


?>

