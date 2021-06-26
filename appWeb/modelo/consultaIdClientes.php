<?php
include "conexiondb.php";

	$selectUltId="SELECT MAX(id_cliente) FROM clientes";
	$query=mysqli_query($conexion, $selectUltId);

	$colum = mysqli_fetch_array($query);

	echo $colum[0];

 ?>