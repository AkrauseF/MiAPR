<?php
include "conexiondb.php";


	$selectUltId="SELECT count(*) FROM datos_cobros";
	//$selectUltId="SELECT MAX(id_registro) FROM registro_lectura";
	$query=mysqli_query($conexion, $selectUltId);

	$colum = mysqli_fetch_array($query);

	echo $colum[0];

 ?>