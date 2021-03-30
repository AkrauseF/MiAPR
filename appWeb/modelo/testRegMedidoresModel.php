<?php
include "conexiondb.php";

/*$marca = $_POST['marca'];
$codigo = $_POST['codigo'];*/


$codigos = array(1111,2222,3333,4444,5555,6666,7777,8888,9999,1000);
$marcas = array("POCO","KAKO","MAST","LGD","JOSA","RARO","YOYO","LOLO","POPA","LAIS");

$i=0;
while ( $i < 10) {
	$insert="INSERT INTO medidores (numero, marca)VALUES($codigos[$i],'$marcas[$i]')";
	$query=mysqli_query($conexion, $insert);

	$i++;
}


?>
<script>window.location.href = "../medidores.php";</script>