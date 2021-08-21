<?php 
function hashPass($pass){

	$pass = hash('sha256', $pass);

	return $pass;

}

echo hashPass(8923410);

?>