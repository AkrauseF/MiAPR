<?php 
require 'Classes/PHPExcel.php';
include 'conexiondb.php';

$objPHPExcel = new PHPExcel();

$objPHPExcel->getProperties()
->setCreator('Codigos de Programacion')
->setTitle('Excel en PHP')
->setDescription('Documento de Prueba')
->setKeywords('excle phpexcel php')
->setCategory('Ejemplo');


$objPHPExcel->setActiveSheetIndex(0);
$objPHPExcel->getActiveSheet()->setTitle('Hoja1');



$objPHPExcel->getActiveSheet()->setCellValue('A1','RUT');
$objPHPExcel->getActiveSheet()->setCellValue('B1','NOMBRE');
$objPHPExcel->getActiveSheet()->setCellValue('C1','APELLIDO');
$objPHPExcel->getActiveSheet()->setCellValue('D1','DIRECCION');
$objPHPExcel->getActiveSheet()->setCellValue('E1','SUBSIDIO');
$objPHPExcel->getActiveSheet()->setCellValue('F1','NUMERO DE SITIO');
$objPHPExcel->getActiveSheet()->getColumnDimension('A')->setAutoSize(true);
$objPHPExcel->getActiveSheet()->getColumnDimension('B')->setAutoSize(true);
$objPHPExcel->getActiveSheet()->getColumnDimension('C')->setAutoSize(true);
$objPHPExcel->getActiveSheet()->getColumnDimension('D')->setAutoSize(true);
$objPHPExcel->getActiveSheet()->getColumnDimension('E')->setAutoSize(true);
$objPHPExcel->getActiveSheet()->getColumnDimension('F')->setAutoSize(true);


//EDITAR EXCEL
$select="SELECT * FROM clientes";
    $query=mysqli_query($conexion, $select);
    $cont=2;
    while($columna=mysqli_fetch_array($query)){ 
    	$num=(string)$cont;

		$objPHPExcel->getActiveSheet()->setCellValue('A'.$num,$columna[1]);
		$objPHPExcel->getActiveSheet()->setCellValue('B'.$num,$columna[2]);
		$objPHPExcel->getActiveSheet()->setCellValue('C'.$num,$columna[3]);
		$objPHPExcel->getActiveSheet()->setCellValue('D'.$num,$columna[4]);
		$objPHPExcel->getActiveSheet()->setCellValue('E'.$num,$columna[5]);
		$objPHPExcel->getActiveSheet()->setCellValue('F'.$num,$columna[6]);
		$cont=$cont+1;
}


//CREAR ARCHIVO
header('Content-Type: application/vnd.ms-excel');
header('Content-Disposition: attachment;filename="Excel.xls"');
header('Cache-Control: max-age=0');

$objWrite= PHPExcel_IOFactory::createWriter($objPHPExcel, 'Excel5');
$objWrite->save('php://output');




?>
