<?php 

include "conexiondb.php";

echo $montoPago=(int)$_POST['montoPago'];
echo" ";
//$saldoAnterior=$_POST['saldoAnterior'];
echo $idCliente=$_POST['idCliente'];
echo" ";
echo $montoMesActual=$_POST['montoMesActual'];
echo" ";
echo $idPagoMesActual=$_POST['idPagoMesActual'];

$select1 = "SELECT count(*) FROM pagos where id_cliente = $idCliente and saldo != 0 ";
$query1 = mysqli_query($conexion, $select1);
$row1=mysqli_fetch_array($query1);

$select7 = "SELECT * FROM pagos where id_cliente = $idCliente and saldo != 0 ";
$query7 = mysqli_query($conexion, $select7);

//$montoPago=400;
//$montoMesActual=1200;
//$idCliente=1;

$num=1;
//while($num<=(int)$row1[0]){
while($row7=mysqli_fetch_array($query7)){

	$select2 = "SELECT saldo, id_pago FROM pagos where id_pago = $row7[0] and saldo != 0 ";

	$query2 = mysqli_query($conexion, $select2);
	$row=mysqli_fetch_array($query2);
	if($query2){

		echo ":::::saldo analizado: ".$row[0].", ";
		echo "Monto pagado: ".$montoPago.", ";

		if((int)$row[0]<=$montoPago){
			//monto pagado - saldo:
			echo "resta: ".$montoPago." - ".(int)$row[0]." : ".$montoPago=$montoPago - (int)$row[0];
			echo ", ";
			//echo "saldo =".(int)$row[0];//saldo = 0;
			$update = "UPDATE pagos SET saldo=0, monto_cancelado=$row[0] WHERE id_cliente=$idCliente and id_pago=$row[1]";
			$query = mysqli_query($conexion, $update);

			if($query){
				echo "Actualización de correcta, ";

			}else{
				echo "No se realizó la Actualización, ";
				echo "Resumen:: monto cancelado: ".(int)$row[0].", id cliente: ".$idCliente.", id pago: ".$row[1]."---";
			}
			echo "Monto pagado: ".$montoPago.", ";
			
		}else{

			//echo "resto saldo - montoPagado: ";
			echo "resto: ".$row[0] - $montoPago;
			echo ", ";
			echo "saldo =".$row[0]=(int)$row[0]- $montoPago;

			$update = "UPDATE pagos SET saldo=$row[0], monto_cancelado=$montoPago WHERE id_cliente=$idCliente and id_pago=$row[1]";
			$query = mysqli_query($conexion, $update);

			$montoPago=0;
			echo "Monto pagado: ".$montoPago.", ";

		}

	}else{
		echo "no";
		break;
	}

	$num++;
}

$select3 = "SELECT rut FROM clientes where id_cliente = $idCliente";
$query3 = mysqli_query($conexion, $select3);
$row=mysqli_fetch_array($query3);
?>
<script>window.location.href = "../pagos.php?rut=<?php echo $row[0]?>";</script><?php

//fin true

/*
if($montoPago >0){
	echo "resto montoMesActula - montoPagado, -->".$montoPago;
	echo "saldo mes actual = ".$saldoMesActual= $montoMesActual-$montoPago;
	$update2 = "UPDATE pagos SET saldo=$saldoMesActual, monto_cancelado=$montoPago WHERE id_cliente=$idCliente and id_pago=$idPagoMesActual";
	$query2 = mysqli_query($conexion, $update2);

}else{
	echo "saldo mes actual = ".$montoMesActual;
	$update3 = "UPDATE pagos SET saldo=$montoMesActual WHERE id_cliente=$idCliente and id_pago=$idPagoMesActual";
	$query3 = mysqli_query($conexion, $update3);
}

*/


?>