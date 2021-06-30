<?php
include 'conexiondb.php';
session_start();

$usuario = $_POST['user'];
$contrasena = $_POST['pass'];

if(!empty($usuario) and !empty($contrasena)){

	$select= "SELECT usuario, contrasena FROM usuarios WHERE usuario = '$usuario'";

	if($query= mysqli_query($conexion, $select)){
		$columna = mysqli_fetch_array($query);
//
		if($usuario == $columna[0] and hash('sha256',$contrasena)==$columna[1]){
			$_SESSION['user']=$columna[0];
			?><script>
			alert('Credenciales correctas.');
			window.location.href = "../index.php";
			</script><?php
			//header("Location: ../index.php");
		}else{
			?><script>
			alert('Credenciales invalidas.');
			window.location.href = "../login.php";
			</script><?php
			
		}
	}else{
		?><script>
			alert('Credenciales invalidas.');
			window.location.href = "../login.php";
			</script><?php
	}
}else{

	header("Location: ../login.php");	

}
 
?>