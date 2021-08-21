<?php 
include 'conexiondb.php';
$idCliente = $_GET['id']; //recepcion de id a eliminar
echo 'recepcion: '.$idCliente;
echo "";

function obtenerUltimoReg(){
	global $conexion;
	$ultimoId = "SELECT MAX(id_usuarios) FROM usuarios";// obtener ultimo id
	$query = mysqli_query($conexion, $ultimoId);
	$columna=mysqli_fetch_array($query);
	echo 'Ultimo id: '.$columna[0];
	echo "";
	return $columna[0];
}

function eliminarClientes($idCliente){
	echo "se elimino supouestamente";
	global $conexion;
	$delete = "DELETE FROM usuarios WHERE id_usuarios = $idCliente"; //se borra fila
	$query2 = mysqli_query( $conexion, $delete) or die;
	echo "se elimino supouestamente";
}

function actualizarIdRegistros($idCliente, $ultimoReg ){
	global $conexion;
	$num = $idCliente + 1;
	//echo 'num: '.$num;
	//echo "";
	$newId = $idCliente;
	while ($num <= $ultimoReg) {
	 //	echo 'newID: '.$newId;
		
		//echo 'num: '.$num;
		$update = "UPDATE usuarios SET id_usuarios=$newId WHERE id_usuarios=$num";
		$query3 = mysqli_query($conexion, $update);
		$newId= $newId +1;
		$num = $num +1;
	}

}

function actualizarAutoIncrement($ultimoReg){
	global $conexion;
	$ai = $ultimoReg - 1;
	//echo 'AI: '.$ai;
	$resetAI="ALTER TABLE usuarios AUTO_INCREMENT = $ai";
	$query3 = mysqli_query($conexion, $resetAI) or die;

}

$ultimoReg = obtenerUltimoReg($conexion);
eliminarClientes($idCliente);
actualizarIdRegistros($idCliente, $ultimoReg);
actualizarAutoIncrement($ultimoReg);
?>
<script>window.location.href = "../secretarios.php";</script>