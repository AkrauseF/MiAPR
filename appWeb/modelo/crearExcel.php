<?php 
require 'Classes/PHPExcel.php';
include 'conexiondb.php';

$objPHPExcel = new PHPExcel();
echo $malo;

$objPHPExcel->getProperties()
->setCreator('Codigos de Programacion')
->setTitle('Excel en PHP')
->setDescription('Documento de Prueba')
->setKeywords('excle phpexcel php')
->setCategory('Ejemplo');

$objPHPExcel->setActiveSheetIndex(0);
$objPHPExcel->getActiveSheet()->setTitle('Hoja1');

//CREAR ARCHIVO
header('Content-Type: application/vnd.ms-excel');
header('Content-Disposition: attachment;filename="Excel.xls"');
header('Cache-Control: max-age=0');

$objWrite= PHPExcel_IOFactory::createWriter($objPHPExcel, 'Excel5');
$objWrite->save('php://output');




