<?php 
session_start();
$usuario =$_SESSION['user'];

if(!isset($usuario)){
	header("Location: login.php");
}
	#
include "modelo/conexiondb.php";
?>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="css/css.css">
  <title>

  </title>
</head>
<body>
  <?php 
include "menu.php";
?>

<?php
//selecciona todos los registros que tengan deuda 
$select = "SELECT * FROM pagos where saldo != 0 group by id_cliente";
$query = mysqli_query($conexion, $select);

?>
<div class="contenedor">
<div class="dashLeft">
<div class="dash">
 <h3>Alerta de corte de servicio</h3> <?php
 $cero="No hay datos";
 
while ( $row=mysqli_fetch_array($query) ) {

	//Por cliente cuenta la cantidad de deudas.
	$select1 = "SELECT count(*) FROM pagos where id_cliente = $row[5] and saldo != 0 ";
	$query1 = mysqli_query($conexion, $select1);
	$row1=mysqli_fetch_array($query1);
	

	if((int)$row1[0] >= 3 ){//si tiene 3 o mas deudas suma la deuda y la muestra.
		$select2 = "SELECT SUM(saldo) FROM pagos where id_cliente = $row[5] ";// obtener ultimo id
		$query2 = mysqli_query($conexion, $select2);
		$row2=mysqli_fetch_array($query2);

		$select3 = "SELECT * FROM clientes where id_cliente = $row[5] ";// obtener ultimo id
		$query3 = mysqli_query($conexion, $select3);
		$row3=mysqli_fetch_array($query3);
		$cero="";
		echo $cero;
			?><a href="pagos.php?rut=<?php echo $row3[1]?>"><?php
			 echo $row3[2]." ".$row3[3]." $".$row2[0];?></a><?php
			 echo "<br>";
			 echo "<br>";
	}
	
}
?>
</div>
</div>
	<div class="dashCenter">
		<!--<div class="grafico">
			<?php 
		include "grafico.php";
			?>-->
		</div>
		
	</div>
	
	</div>
</div>
</body>
</body>
</html>
