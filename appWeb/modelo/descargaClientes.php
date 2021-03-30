<?php 
include "conexiondb.php";

function descargaMedidores(){
	global $conexion;
	$var = $_GET["var"];
    //$var = 1;

	$select="SELECT * FROM clientes WHERE id_cliente=$var";
	$query1=mysqli_query($conexion, $select);
	$row= mysqli_fetch_array($query1);
	
	printf ($row[1]);
	printf (",");
	printf ($row[2]);
	printf (",");
	printf ($row[3]);
	printf (",");
	printf ($row[4]);
	printf (",");
	printf ($row[5]);
	printf (",");
	printf ($row[6]);
	printf (",");
	printf ($row[7]);

}
echo descargaMedidores();

?>