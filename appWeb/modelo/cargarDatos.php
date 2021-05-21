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
	
	$selcet ="SELECT id_cliente from clientes join medidores on medidores.id_medidores=clientes.id_medidor where medidores.numero=$num_medidor";
	$query2=mysqli_query($conexion, $selcet);

	$colum = mysqli_fetch_array($query2);

	echo $colum[0];

	$insert="INSERT INTO registro_lectura (lectura, monto, fecha, metros_cubicos, num_medidor, pago)VALUES($lectura,$monto, '$fecha', $metros_cubicos, $num_medidor, 0)";

		$query=mysqli_query($conexion, $insert);
		//echo "Lectura de medidor ".$num_medidor." cargada";

	$insert2="INSERT INTO pagos (fecha, monto, saldo, monto_cancelado, id_cliente)VALUES('$fecha', $monto, $monto, 0, $colum[0])";

		$query3=mysqli_query($conexion, $insert2);

	
}
insertarDatos();


?>

