<?php 
session_start();
$usuario =$_SESSION['user'];

if(!isset($usuario)){
  header("Location: login.php");
}

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
$selectx="SELECT * FROM datos_cobros";
$queryx=mysqli_query($conexion, $selectx);
$columnax=mysqli_fetch_array($queryx);
$cargoFijo=intval($columnax[1]);
$metrosSub=intval($columnax[2]);
$valorMetro=intval($columnax[3]);

$selecty="SELECT fecha FROM registro_lectura ORDER by id_registro DESC LIMIT 1";
$queryy=mysqli_query($conexion, $selecty);
$columnay=mysqli_fetch_array($queryy);
$ultimaFecha = $columnay[0];
?>
<div id="contenedorCliente">
<h1>Informe de subsidiarios</h1>

  <div class="tabla2">

    <table>
        <tr>
        </tr>
        <tr>
         <th>Nº </th>
         <th>Rut </th>
         <th>Apellido pat.</th>
         <th>Apellido mat.</th>
         <th>Nombres</th>
         <th>Direccion</th>
         <th>Num. Decreto</th>
         <th>Fecha decreto</th>
         <th>Puntaje</th>
         <th>Fecha encuesta</th>
         <th>Numero Socio</th>
         <th>VNU</th>
         <th>Numero viviendas</th>
         <th>Consumo</th>
         <th>Subsidio(SUSB)</th>
         <th>Dif. Subsidio(BEN)</th>
         <th>Deuda en meses</th>
         <th>Monto deuda</th>
         <th>Tipo subsdio(OBS)</th>
         
        
       </tr>

    <?php

    $select="SELECT * FROM clientes"; //Seleccion de clientes registrados.
    $query=mysqli_query($conexion, $select);
    $cont=1;
    while($columna=mysqli_fetch_array($query)){ 
    	
	    $select1="SELECT * FROM decretos WHERE id_decreto=$columna[8]";//Seleccion de informacion de subsidios asociado a un clinete.
	    $query2=mysqli_query($conexion, $select1);
	    $columna2=mysqli_fetch_array($query2);
        
	    if($columna2[8]=="0"){//tipo subsidio
	    	continue;

	    }else{
	    	$select3="SELECT numero FROM medidores INNER JOIN clientes on clientes.id_medidor = medidores.id_medidores WHERE clientes.id_cliente=$columna[0]";
	    	$query3=mysqli_query($conexion, $select3);
	    	$columna3=mysqli_fetch_array($query3);
	    	
	    	$select4="SELECT metros_cubicos, monto FROM registro_lectura WHERE num_medidor=$columna3[0] and fecha='$ultimaFecha'";// seleccion de consumo de agua del ultimo mes registrado.

	    	$query4=mysqli_query($conexion, $select4);
	    	$columna4=mysqli_fetch_array($query4);
            if(empty($columna4)){
                $susb="No registra";
                $ben="No registra";
                $metroCub ="No registra";
            }else{
                $metroCub =$columna4[0];
                
                $susb="";
                $ben="";
            
                if($columna2[8]=="10"){//tipo subsidio
                    if(intval($columna4[0])<= $metrosSub){//metros cubicos
                        $susb=$columna4[1];//monto
                        $ben=$columna4[1];//monto
                    }else{
                        $susb=($metrosSub*$valorMetro+$cargoFijo)/2;
                        $ben=$columna4[1];
                                    }
                }else{
                    if(intval($columna4[0])<= $metrosSub){
                        $susb=$columna4[0]*$valorMetro+$cargoFijo;
                        $ben=$columna4[1];
                    }else{
                        $susb=$metrosSub*$valorMetro+$cargoFijo;
                        $ben=$columna4[1];
                        }
                }
            }

        }
	    	


	    	

    ?>
       <tr>
        <td><?php echo $cont;?></td>
        <td><?php echo $columna[1];?></td>
        <td><?php echo $columna[3];?></td>
        <td><?php echo $columna[4];?></td>
        <td><?php echo $columna[2];?></td>
        <td><?php echo $columna[5];?></td>

        <td><?php echo $columna2[1];?></td>
        <td><?php echo $columna2[2];?></td>
        <td><?php echo $columna2[3];?></td>
        <td><?php echo $columna2[4];?></td>
        <td><?php echo $columna2[5];?></td>
        <td><?php echo $columna2[6];?></td>
        <td><?php echo $columna2[7];?></td>
        <!--Consumo metros cubicos-->
        <td><?php echo $metroCub;?></td>

        <td><?php echo $susb;?></td>

        <td><?php echo $ben;?></td>
        <td><?php echo "0";?></td>
        <td><?php echo "0";?></td>


        <!--tipo subsidio-->
        <td><?php echo $columna2[8];?></td>



      </tr>
    <?php $cont=$cont+1;} ?>

    </table>


  </div>
  <a href="modelo/genExcel.php"><button style="margin-top: 10px; margin-left: 20px;">GENERAR INFORME</button></a></td>

</div>

</body>
</html>
