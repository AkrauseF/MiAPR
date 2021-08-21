<?php

include "conexiondb.php";

function idUltimoMedidor(){
	global $conexion;
	$selectUltId="SELECT MAX(id_medidores) FROM medidores";
	$query=mysqli_query($conexion, $selectUltId);
	$colum = mysqli_fetch_array($query);
	echo $colum[0];

}
idUltimoMedidor()


?>