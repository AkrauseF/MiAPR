<?php 
include "conexiondb.php";

function descargaMedidores(){
	global $conexion;
	$var = $_GET["var"];
    //$var = 2;

	$select="SELECT * FROM clientes WHERE id_cliente=$var";
	$query1=mysqli_query($conexion, $select);
	$row= mysqli_fetch_array($query1);

	$select2="SELECT tipo_sub FROM decretos WHERE id_decreto=$row[9]";
	$query2=mysqli_query($conexion, $select2);
	$row2= mysqli_fetch_array($query2);// subsidio */
	
	printf ($row[1]);
	printf (",");
	printf ($row[2]);
	printf (",");
	printf ($row[3]);
	printf (",");
	printf ($row[5]);
	printf (",");
	printf ($row2[0]);//subsidio
	printf (",");
	printf ($row[7]);
	printf (",");
	printf ($row[8]);
//verficar que el subsidio se este descargando bien hacia la app movil, por cambio de dato en otra tabla (decretos)
}
echo descargaMedidores();

?>