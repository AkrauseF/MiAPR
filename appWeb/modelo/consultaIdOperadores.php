<?php
include "conexiondb.php";

	$selectUltId="SELECT MAX(id_operadores) FROM operadores";
	$query=mysqli_query($conexion, $selectUltId);

	$colum = mysqli_fetch_array($query);

	echo $colum[0];

 ?>