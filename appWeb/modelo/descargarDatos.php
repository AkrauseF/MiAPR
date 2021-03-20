<?php 
include "conexiondb.php";




function descargaMedidores(){
	global $conexion;
	$var = $_GET["var"];
	$select="SELECT * FROM medidores WHERE id_medidores=$var";
	$query1=mysqli_query($conexion, $select);
	$row= mysqli_fetch_array($query1);
	
	printf ($row[0]);
	printf (",");
	printf ($row[1]);
	printf (",");
	printf ($row[2]);

}

echo descargaMedidores();



?>