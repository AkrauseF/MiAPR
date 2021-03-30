<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="css/css.css">
  <title>

  </title>
</head>
<body>

<div id="regMedidores">
  <h1>Registro de Medidores</h1>

  <?php $i = 0;
          $codigos = array(1111,2222,3333,4444,5555,6666,7777,8888,9999,1000);
          $marcas = array("POCO","KAKO","MAST","LGD","JOSA","RARO","YOYO","LOLO","POPA","LAIS");
          
  ?>
  <form name="formulario"action="modelo/regMedidorModel.php" method="post">
    

    <label>Código</label>
    <input type="number" id="cod" name="codigo" value="<?php echo $codigos[$i] ?>">
    <label>Marca</label>
    <input type="text" id="marc" name="marca" value="<?php echo $marcas[$i] ?>">
   
    
  </form>
 <?php while($i<10){ ?>
      <script>
  document.getElementById("cod").value="<?php echo $codigos[$i] ?>";
  document.getElementById("marc").value="<?php echo $marcas[$i] ?>";

  </script>
  
<?php $i++;} ?>
<script >  document.forms["formulario"].submit();
</script>





 

</div>

</body>
</html>