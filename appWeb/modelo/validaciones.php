<?php 
include 'conexiondb.php';
function limpirarString($string){
	$string= strval($string);
	$letras=" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	/*$letras=", ,a,b,c,d,e,f,g,h,i,j,k,l,m,n,ñ,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,Ñ,O,P,Q,R,S,T,U,V,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0,0";*/
	$lista = explode(",",$letras);
	
	for($i=0;$i<strlen($string); $i++){
		 if (strpos($letras, substr($string,$i,1))===false){
         return false;
     	 }
   	}
   	return true;
}	

function restringeLargo($string, $largo){
	if(strlen($string) >= $largo){
		return true;
	}else{
		return false;
	}
}

function verificarUsuarioEnDB($usuario){
	global $conexion;
	$flag=false;
	$select="SELECT usuario FROM usuarios";
	$query=mysqli_query($conexion, $select);

	while($colum = mysqli_fetch_array($query)){
		if($usuario==$colum[0]){
			$flag= true;
			break;
		}else{
			$flag= false;
		}
	}

	return $flag;

}


function valida_rut($rut){
    $rut = preg_replace('/[^k0-9]/i', '', $rut);
    $dv  = substr($rut, -1);
    $numero = substr($rut, 0, strlen($rut)-1);
    $i = 2;
    $suma = 0;
    foreach(array_reverse(str_split($numero)) as $v){
        if($i==8){
            $i = 2;
        }
        $suma += $v * $i;
        ++$i;
    }

    $dvr = 11 - ($suma % 11);
    
    if($dvr == 11){
        $dvr = 0;
    }
    if($dvr == 10){
        $dvr = 'K';
    }

    if($dvr == strtoupper($dv)){
        return true;
    }
    else{
        return false;
    }
}

/*if(valida_rut("17826183523")){
		echo "si";
}else{
		echo "no";
}*/

?>