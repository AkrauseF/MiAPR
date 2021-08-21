<!DOCTYPE html>
<html>
<head>
	<title></title>
	<link rel="stylesheet" type="text/css" href="css/css.css">
</head>
<body>

<?php

include 'conexiondb.php';
$fecha=$_POST['fecha'];
$mes= substr($fecha, -2);
$pattern = $_POST['pattern'];

	  if(empty($_POST['pattern']) and empty($_POST['fecha'])){
    $sql = "SELECT clientes.nombre, pagos.fecha, pagos.monto, pagos.saldo, pagos.monto_cancelado FROM pagos inner JOIN clientes on clientes.id_cliente = pagos.id_cliente order by fecha desc";
    }

    if(!empty($_POST['pattern'])){
    $sql = "SELECT clientes.nombre, pagos.fecha, pagos.monto, pagos.saldo, pagos.monto_cancelado FROM pagos inner JOIN clientes on clientes.id_cliente = pagos.id_cliente where nombre LIKE '%$pattern%' order by fecha desc " ;
    }
   
    
    if(!empty($_POST['fecha'])){

      $sql = "SELECT clientes.nombre, pagos.fecha, pagos.monto, pagos.saldo, pagos.monto_cancelado FROM pagos inner JOIN clientes on clientes.id_cliente = pagos.id_cliente where MONTH(pagos.fecha)=$mes and YEAR(pagos.fecha)=2021 order by fecha desc";
    }
    if(!empty($_POST['pattern']) and !empty($_POST['fecha'])){
    $sql = "SELECT clientes.nombre, pagos.fecha, pagos.monto, pagos.saldo, pagos.monto_cancelado FROM pagos inner JOIN clientes on clientes.id_cliente = pagos.id_cliente where MONTH(pagos.fecha)=$mes and YEAR(pagos.fecha)=2021 and nombre LIKE '%$pattern%' order by fecha desc";
    }

    if($result = mysqli_query($conexion, $sql)){

        $numrows = $result->num_rows;

        if($numrows > 0){ ?>
        <div class="tabla">
        <table>
        <tr>
        </tr>
        <tr>
    
         <th>Nombre</th>
         <th>Fecha</th>
         <th>Monto</th>
         <th>Saldo</th>
         <th>Monto Cancelado</th>

           
       </tr>

           <?php while($row = mysqli_fetch_array($result)){?>
       <tr>
        <td><?php echo $row[0]?></td>
        <td><?php echo $row[1]?></td>
        <td><?php echo $row[2]?></td>
        <td><?php echo $row[3]?></td>
        <td><?php echo $row[4] ?></td>

      </tr>
    	<?php } ?>

    </table>
    </div>
    <?php

 
        }else{
            print "No se Encontraron Coincidencias.";
        }
    }else{
      
    }
 

?>

</body>
</html>
