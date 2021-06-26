<?php 
session_start();
$usuario =$_SESSION['user'];

if(!isset($usuario)){
  header("Location: login.php");
}
error_reporting(0);
include "modelo/conexiondb.php";
?>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Expires" content="0">
  <meta http-equiv="Last-Modified" content="0">
  <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
  <meta http-equiv="Pragma" content="no-cache">

  <link rel="stylesheet" type="text/css" href="css/css.css">
  <title>

  </title>
</head>
<body>
  <?php 
  
include "menu.php";
?>


<h1>Registro de pagos</h1>

<div class="buscador">
<form action="#" method="post">
   <label>Rut</label>
    <input type="number" name="rut" value="<?php echo $rutG;?>">
    <input type="submit" value="Buscar">
</form>
</div>

<?php 
$rutG = $_GET['rut'];
$rutP = $_POST['rut'];
if(!isset($rutG)){
  $rut = $rutP;
}else{
  $rut = $rutG;

}
$select = "SELECT rut, nombre, apellido_p, apellido_m, direccion, num_sitio, id_medidor, id_decreto, id_cliente FROM clientes where rut = $rut ";// obtener ultimo id
$query = mysqli_query($conexion, $select);
$columna=mysqli_fetch_array($query);

$select2 = "SELECT monto, id_pago, saldo FROM pagos where id_cliente = $columna[8] order by id_pago DESC ";// obtener ultimo id
$query2 = mysqli_query($conexion, $select2);
$columna2=mysqli_fetch_array($query2);
$montMesAct = $columna2[2];
$saldo=$columna2[2];


$select3 = "SELECT SUM(saldo) FROM pagos where id_cliente = $columna[8] ";// obtener ultimo id
$query3 = mysqli_query($conexion, $select3);
$columna3=mysqli_fetch_array($query3);

//cuenta cuantos registros tienen saldos de un cliente en particular
$select5="SELECT count(*) FROM pagos where id_cliente = $columna[8] and saldo != 0";
$query5=mysqli_query($conexion, $select5);
$columna5=mysqli_fetch_array($query5);

$montoAPagar= (int)$columna3[0];

$saldoAnterior= $montoAPagar - $montMesAct; 

?>
<div class="container">
  <h2 style="margin-left: 10%">Rut: <?php echo $rut; ?></h2>

<form action="modelo/pagarModelo.php" method="post">

 <div class="left">

    <label>Nombre</label>
    <input type="text" name="nombre" value="<?php echo $columna[1] ?>" readonly>
   <label>Dirreccion</label>
    <input type="text" name="direccion" value="<?php echo $columna[4] ?>" readonly>
    <label>Saldo de meses anteriores</label>
    <input type="number" name="saldoAnterior" value="<?php echo $saldoAnterior ?>" readonly>
    <input type="hidden" name="idCliente" value="<?php echo $columna[8] ?>" readonly>

 </div>
  <div class="center">

     <label>Apellido paterno</label>
    <input type="text" name="apellidoP" value="<?php echo $columna[2] ?>" readonly>
    <label>Nº de sitio</label>
    <input type="number" name="numSitio" value="<?php echo $columna[5] ?>" readonly>
      <label>Monto del Mes Actual</label>
    <input type="number" name="montoMesActual" value="<?php echo $montMesAct ?>" readonly>
        <input type="hidden" name="idPagoMesActual" value="<?php echo $columna2[1] ?>" readonly>



  </div>
  <div class="rigth">
    <label>Apellido materno</label>
    <input type="text" name="apellidoM" value="<?php echo $columna[3] ?>" readonly>
    <label>Deudas mensuales</label>
    <input type="number" name="monto" value="<?php echo $columna5[0] ?>" readonly>
     <label>Monto Deuda</label>
    <input type="number" name="montoDeuda" value="<?php echo $montoAPagar ?>" readonly>
    <label>Monto a Pagar</label>
    <input type="number" name="montoPago" value="<?php echo $montoAPagar ?>" >
    <input type="submit" value="Pagar">
  </div>
  </form>
</div>
<br>
 



 <div id="contenedorCliente">
<h1>Deudas</h1>

  <div class="tabla">

    <table>
        <tr>
        </tr>
        <tr>
         <th>Fecha </th>
         <th>Monto </th>
         <th>Saldo</th>
         <th>Monto Cancelado</th>
         
       </tr>

    <?php 
    $select4="SELECT * FROM pagos where id_cliente = $columna[8] and saldo != 0";
    $query4=mysqli_query($conexion, $select4);
    while($columna4=mysqli_fetch_array($query4)){ 
  
    ?>
       <tr>    
        <td><?php echo $columna4[1];?></td>
        <td><?php echo $columna4[2];?></td>
        <td><?php echo $columna4[3];?></td>
        <td><?php echo $columna4[4];?></td>
      </tr>
    <?php } ?>

    </table>

  </div>
</div>


</body>
</html>