<?php 
include "conexiondb.php";


function descargarLecturaAnterior(){
	$numMedidor=$_GET["var"];
	//echo $numMedidor;
	//$numMedidor=3333;
	global $conexion;
	$fechasAct=date("Y-m-d");
	//echo $fechasAct."---";
	//$fechaAnt= date("Y-m-d",strtotime($fechasAct."- 31 days"));
	$mesAct = substr($fechasAct, -5, 2);
	$mesAtn = (int)$mesAct - 1;
	//echo "mes anterior=".$mesAtn;
	
	$anoAct= substr($fechasAct, 0, 4);
	$anoAnt= (int)$anoAct -1;
	//echo $fechasAct.("--");
	//echo $mesAct.("--");
	//echo $mesAtn;
	//echo "añor actual=".$anoAct;


	if($mesAct=="01"){

		$select="SELECT lectura, num_medidor FROM registro_lectura WHERE MONTH(fecha)=12 and YEAR(fecha)=$anoAnt and num_medidor=$numMedidor";

	}else{

		$select="SELECT lectura, num_medidor FROM registro_lectura WHERE MONTH(fecha)=$mesAtn and YEAR(fecha)=$anoAct and num_medidor=$numMedidor";
	}
	
	$query1=mysqli_query($conexion, $select);
	$row=mysqli_fetch_array($query1);

	if(empty($row[0])){
		echo "";
	}else{
		echo $row[0];
		echo ",";
		echo $row[1];

	}

}

echo descargarLecturaAnterior();


?>