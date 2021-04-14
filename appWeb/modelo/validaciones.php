<?php 

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

/*if(limpirarString("hola")){
	echo "si";
}else{
	echo "no";
}*/




?>