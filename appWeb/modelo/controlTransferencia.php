<?php
include "conexiondb.php";

$switch = $_GET['tran'];

$update = "UPDATE control_tran SET valor=$switch WHERE id=1";
$query3 = mysqli_query($conexion, $update);
?>
<script>window.location.href = "../index.php";</script>
