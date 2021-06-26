<?php
include "conexiondb.php";


	$selectUltId="SELECT COUNT(fecha) FROM registro_lectura WHERE fecha = (SELECT fecha from registro_lectura ORDER BY id_registro DESC LIMIT 1)";
	//$selectUltId="SELECT MAX(id_registro) FROM registro_lectura";
	$query=mysqli_query($conexion, $selectUltId);

	$colum = mysqli_fetch_array($query);

	echo $colum[0];

 ?>