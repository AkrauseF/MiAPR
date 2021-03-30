

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
include "modelo/conexiondb.php";
?>

<div id="registros">
  <h1>Registro de Medidores</h1>
  <form action="modelo/regMedidorModel.php" method="post">
    <label>Código</label>
    <input type="number" name="codigo">
    <label>Marca</label>
    <input type="text" name="marca">

    <input type="submit" value="Enviar">
    
  </form>
  <a href="medidores.php"><button>Medidores Registrados</button></a>
  
</div>

</body>
</html>