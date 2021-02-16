<?php
include "conexiondb.php";



	$selectUltId="SELECT MAX(id_medidores) FROM medidores";
	$query=mysqli_query($conexion, $selectUltId);

	$colum = mysqli_fetch_array($query);

	echo $colum[0];

 ?>