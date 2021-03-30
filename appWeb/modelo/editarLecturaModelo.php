<?php 
include 'conexiondb.php';
$lectura = $_POST['lectura']; //recepcion de id a actualizar
//$monto = $_POST['monto'];
$fecha = $_POST['fecha'];
//$metroC = $_POST['metrosC'];
$medidor = $_POST['medidor'];
//$pago = $_POST['pago'];
$id = $_POST['id']; //recepcion de id a actualizar
//echo "Id de envio:".$id."--";


$select="SELECT clientes.subsidio FROM clientes inner join medidores on clientes.id_medidor=medidores.id_medidores where medidores.numero = $medidor";
$query=mysqli_query($conexion, $select);
$columna=mysqli_fetch_array($query);
$subsidio = $columna[0];


$select2="SELECT cargo_fijo, metros_subsidio FROM datos_cobros";
$query2=mysqli_query($conexion, $select2);
$columna2=mysqli_fetch_array($query2);
$cargoFijo= $columna2[0];
$metrosC= $columna2[1];

//$fechasAct=date("Y-m-d");
	//echo $fechasAct;
$fechaAnt= date("Y-m-d",strtotime($fecha."- 1 days")); 
echo "Fecha anterior: ".$fechaAnt."-- ";

$select3="SELECT lectura FROM registro_lectura where fecha='$fechaAnt' and num_medidor=$medidor";
$query3=mysqli_query($conexion, $select3);
$columna3=mysqli_fetch_array($query3);

/*if(empty($columna3[0])){
	echo"no existe lectura anterior";
}else{
	$lecturaAnt= $columna3[0];
echo "lect ant: ".$lecturaAnt."---";
}*/

$metroCUB = $lectura - $lecturaAnt;

/*$subCien = ($metrosC * 400 + $cargoFijo);
$subCincuenta =($metrosC * 400 + $cargoFijo)/2;
 echo $subCien;
 echo $subCincuenta;*/


/*if($subsidio == 100){
	echo "-es 100";
	$monto = ($metroCUB*400 + $cargoFijo) - $subCien;
	echo "-".$monto;
	$update2 = "UPDATE registro_lectura SET monto=$monto WHERE id_registro=$id";
$query2 = mysqli_query($conexion, $update2);

}elseif ($subsidio == 50) {
echo "-es 50";
 $monto2 = ($metroCUB*400 + $cargoFijo) - $subCincuenta;
echo "-".$monto2;
$update2 = "UPDATE registro_lectura SET monto=$monto2 WHERE id_registro=$id";
$query2 = mysqli_query($conexion, $update2);

}elseif ($subsidio == 0) {
echo "-es 0";
$monto = $metroCUB*400 + $cargoFijo;
echo "-".$monto;
$update2 = "UPDATE registro_lectura SET monto=$monto WHERE id_registro=$id";
$query2 = mysqli_query($conexion, $update2);
}else{
	echo "-No se sabe";
} */



/*echo $codigo."\n";
echo $marca."\n";
echo $id."\n";*/

$update = "UPDATE registro_lectura SET lectura=$lectura WHERE id_registro=$id";
$query = mysqli_query($conexion, $update);



$update2 = "UPDATE registro_lectura SET fecha='$fecha' WHERE id_registro=$id";
$query2 = mysqli_query($conexion, $update2);

/*$update2 = "UPDATE registro_lectura SET metros_cubicos='$metroCUB' WHERE id_registro=$id";
$query2 = mysqli_query($conexion, $update2);*/

$update2 = "UPDATE registro_lectura SET num_medidor='$medidor' WHERE id_registro=$id";
$query2 = mysqli_query($conexion, $update2);

/*$update2 = "UPDATE registro_lectura SET pago='$pago' WHERE id_registro=$id";
$query2 = mysqli_query($conexion, $update2); */

?>
<!--<script>window.location.href = "../lecturas.php";</script> -->
<script>window.location.href = "../lecturas.php";</script>
