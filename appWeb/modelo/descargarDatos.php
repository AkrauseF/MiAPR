<?php 
include "conexiondb.php";

function descargaMedidores(){
	global $conexion;
	$var = $_GET["var"];

	$select2="SELECT medidores.numero, medidores.marca, medidores.id_medidores from clientes INNER JOIN medidores ON medidores.id_medidores = clientes.id_medidor WHERE id_cliente=$var";
	$query2=mysqli_query($conexion, $select2);
	$row= mysqli_fetch_array($query2);
	
	
	printf ($row[0]);
	printf (",");
	printf ($row[1]);
	printf (",");
	printf ($row[2]);
	

}
echo descargaMedidores();

?>