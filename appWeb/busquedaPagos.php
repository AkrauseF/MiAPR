<?php
error_reporting(0);

session_start();
$usuario =$_SESSION['user'];

if(!isset($usuario)){
  header("Location: login.php");
}
?>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/css.css">

  <meta charset="utf-8">
 
</head>

<body onload="buscar()">
  <?php 
include "menu.php"; 
?>
<h1>Búsqueda de clientes</h1>
<div style="margin-top: 10px; margin-left: 30px;" >
<label for="start">Fecha:</label>
<input type="month" id="start" name="fecha" value="Sin Mes">
<label for="start">Nombre:</label>
<input type="search" placeholder="Buscar" id="buscar">
</div>

<div id="datos" style="margin-top: 10px;"></div>
</html>

<script>
	
	function buscar(){
    var pattern = document.getElementById('buscar').value;
    var fecha = document.getElementById('start').value;
    var solicitud = new XMLHttpRequest();
    var data  = new FormData();
    var url = 'modelo/proc.php';

    data.append("pattern", pattern);
    data.append("fecha", fecha);
    solicitud.open('POST',url, true);
    solicitud.send(data);


    solicitud.addEventListener('load', function(e){
    var cajadatos = document.getElementById('datos');
    cajadatos.innerHTML = e.target.responseText;
        
    }, false);
}

window.addEventListener('load', function(){
document.getElementById('buscar').addEventListener('input', buscar, false);
document.getElementById('start').addEventListener('input', buscar, false);
}, false);
</script>

<?php

$fecha = $_POST["fecha"];
//echo $fecha;

 ?>