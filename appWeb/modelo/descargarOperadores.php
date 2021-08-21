<?php 
include "conexiondb.php";

function descargaMedidores(){
	global $conexion;
	$var = $_GET["var"];
    //$var = 14;

	$select="SELECT * FROM operadores WHERE id_operadores=$var";
	$query1=mysqli_query($conexion, $select);
	$row= mysqli_fetch_array($query1);
	
	printf ($row[6]);
	printf (",");
	printf ($row[7]);


//verficar que el subsidio se este descargando bien hacia la app movil, por cambio de dato en otra tabla (decretos)
}
echo descargaMedidores();

?>