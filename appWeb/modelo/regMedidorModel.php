<?php
include "conexiondb.php";

$marca = $_POST['marca'];
$codigo = $_POST['codigo'];

$insert="INSERT INTO medidores (numero, marca)VALUES($codigo,'$marca')";
$query=mysqli_query($conexion, $insert);

header('Location: ../medidores.php')


?>