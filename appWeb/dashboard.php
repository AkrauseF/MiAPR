<?php 
$select = "SELECT * FROM pagos where id_cliente = $idCliente and saldo != 0 ";
$query = mysqli_query($conexion, $select);
$row=mysqli_fetch_array($query);

while ( $row=mysqli_fetch_array($query) ) {
	
	$select1 = "SELECT count(*) FROM pagos where id_cliente = $row[5] and saldo != 0 ";
	$query1 = mysqli_query($conexion, $select1);
	$row1=mysqli_fetch_array($query1);

	if((int)$row1[0] >= 3 ){
		$select2 = "SELECT SUM(saldo) FROM pagos where id_cliente = $row[5] ";// obtener ultimo id
		$query2 = mysqli_query($conexion, $select2);
		$row2=mysqli_fetch_array($query2);

		?>
		
		<div style="border: 1px, solid red">
			<?php echo $row2[0];?>
		</div>

		<?php
	}
}

?>