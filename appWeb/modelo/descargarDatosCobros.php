<?php 
include "conexiondb.php";

function descargaMedidores(){
	global $conexion;
	
	$select="SELECT cargo_fijo, metros_subsidio FROM datos_cobros WHERE id_datos=1";
	$query1=mysqli_query($conexion, $select);
	$row= mysqli_fetch_array($query1);
	
	printf ($row[0]);
	printf (",");
	printf ($row[1]);

}
echo descargaMedidores();

?>