# MiAPR v4.0.0
Sistema que agiliza los procesos de registro de lectura y gestión de cobros del agua potable rural de Villa San Pedro, comuna de Pucón.

## Descripción
Esta versión incluye los módulos de registro de medidores y registro de clientes de la app web, donde en este último módulo se podrá asociar a los clientes con los medidores.
Por otro lado en la app móvil vienen incluidos los módulos de importación y exportación de datos entre la app web y la app móvil lo que permitirá poder ejecutar el módulo de identificación de medidores por medio de la captura de código QR y registrar el número de lectura de este que representa los metros cúbicos consumidos por el cliente que tiene asociado dicho medidor.

## El producto resultante de esta iteración consta de las siguientes funcionalidades:

### Funcionalidades deL release anterior
•	Captura de código QR mediante cámara de dispositivo móvil.

•	Interfaz de control de captura y visualización de códigos QR de la app móvil.

•	Validador de códigos capturados por medio de la app móvil. Sólo se capturarán códigos que contengan números y con un largo máximo de 15 caracteres.

•	Registro de medidores en app web.

•	Edición de medidores en app web.

•	Eliminacíon de medidores en app web.

•	Importación de datos de medidores registrados en app web hacia app móvil.

•	Exportación de datos de las lecturas asociadas a los medidores hacia la app web.

### Funcionalidades añadidas del releas actual 

•	Registro de lecturas asociadas a un medidor en la app móvil.

•	Registro de clientes en aplicación web.


## Instrucciones de instalación de la app móvil
1.- Descargue en el dispositivo móvil el instalador de la aplicación desde el repositorio de GitHub en el siguiente link:   https://github.com/AkrauseF/MiAPR/raw/main/app/entregable/release/MiAPR_4_0_0.apk 

2.- Si tiene una versión anterior de esta aplicación, desintálela del dispositivo móvil.

3.- Instale la aplicación aceptando los permisos solicitados.

4.- Ejecute la aplicación.

## Instrucciones de instalación de la app web

1.- Descargue los archivos comprimidos desde el siguinte link: https://github.com/AkrauseF/MiAPR/archive/v4.0.0.zip

2.- Descomprima el archivo v2.0.0.zip y monte los archivos y directorios contenidos en la carpeta "appWeb" en su servidor web.

3.- En el directorio "modelo", dentro del directorio "appWeb", se encontrará un archivo llamado "conexiondb.php" en el cual debe reemplazar los datos de acceso a su servicio de base de datos mysql. 

#### NOTA: 
    Los servicios utilizados para la ejecución del servidor fueron: Apache 2.4.46, PHP 7.2.33 y MariaDB 10.4.14. Todos contenidos en el paquete de software Xampp 7.2.33.

## Instrucciones de uso de la app móvil

### Importar medidores:
1.- Presione el botón "IMPORTAR".

2.- Una vez finalizado el procesos de importación resgrese al menú anterior y presione el botón "LISTA MEDIDORES" para verificar los datos de los medidores importados.

### Capturar un número de medidor:
1.- Presione el botón “CAPTURAR QR”.

2.- Apunte la cámara hacia el código QR que desea capturar.

3.- Visualice en el dispositivo móvil el código capturado o el error en caso de que el código no sea válido.

### Exportar datos hacia la app web:
1.- Una vez registrado la totalidad de lecturas correspondiente a los medidores importados, conecte el dispositivo móvil a la red donde se encuentra el equipo que contiene la app web.

2.Presione el botón “EXPORTAR” y espere la confirmación de que los tados fueron exportados correctamente.


## Instrucciones de uso de la app web

### Registro de medidores:
1.- Ingrese a su navegador web y escriba la raíz de la dirección donde se encuentra montado el sitio web y agregue /regMedidores.php.

2.- Ingrese el código de identificación del medidor y su marca, luego presione enviar, esto redireccionará hacia la página de medidores registrados.

3.- Cada registro de medidores tendrá un botón para eliminar o editar los datos de este.

4.- Si presiona el botón eliminar se borrará el registro del medidor seleccionado.

5.- Si presiona el botón editar, la página redireccionará hacia el formulario de edición de medidores, donde podrá modificar los datos del medidor seleccionado. Al presionar el botón editar se redireccionará hacia la página de medidores registrados, donde podrá observar los cambios realizados.

### Registro de clientes:
1.- Ingrese a su navegador web y escriba la raíz de la dirección donde se encuentra montado el sitio web y agregue /regCliente.php.

2.- Ingrese los datos del cliente en el formulario y en el campo de "Medidor" seleccione un medidor de la lista de medidores disponibles, luego presione enviar, esto redireccionará hacia la página de clientes registrados.

3.- Para volver a registrar otro cliente presione el botón "Registrar".

