<?php 
include "modelo/conexiondb.php";
?>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Highcharts Example</title>

        <style type="text/css">
#container {
    height: 400px;
    border:0px solid black;
    border-radius: 15px;
    box-shadow: 0px 0px 5px 2px gray;

 
}

.highcharts-figure, .highcharts-data-table table {
    min-width: 320px; 
    max-width: 800px;
    margin: 1em auto;
}

.highcharts-data-table table {
    font-family: Verdana, sans-serif;
    border-collapse: collapse;
    border: 1px solid #EBEBEB;
    margin: 10px auto;
    text-align: center;
    width: 100%;
    max-width: 500px;
}
.highcharts-data-table caption {
    padding: 1em 0;
    font-size: 1.2em;
    color: #555;

}
.highcharts-data-table th {
    font-weight: 600;
    padding: 0.5em;
}
.highcharts-data-table td, .highcharts-data-table th, .highcharts-data-table caption {
    padding: 0.5em;
}
.highcharts-data-table thead tr, .highcharts-data-table tr:nth-child(even) {
    background: #f8f8f8;
}
.highcharts-data-table tr:hover {
    background: #f1f7ff;
}

        </style>
    </head>
    <body>
<script src="graf/code/highcharts.js"></script>
<script src="graf/code/highcharts-more.js"></script>
<script src="graf/code/modules/exporting.js"></script>
<script src="graf/code/modules/export-data.js"></script>
<script src="graf/code/modules/accessibility.js"></script>

<figure class="highcharts-figure">
    <div id="container"></div>
    <br>

    <button id="plain">Vertical</button>
    <button id="inverted">Horizontal</button>
    <button id="polar">Polar</button>
</figure>

<?php
    $select="SELECT * FROM clientes";
    $query=mysqli_query($conexion, $select);
    
?>


        <script type="text/javascript">
const chart = Highcharts.chart('container', {
    title: {
        text: 'Clientes Moroso'
    },
    subtitle: {
        text: 'Vertical'
    },
    xAxis: {
        categories:[
        <?php while ($columna=mysqli_fetch_array($query)) {

            $select4="SELECT sum(saldo) FROM pagos where id_cliente=$columna[0]";
            $query4=mysqli_query($conexion, $select4);
            $row4=mysqli_fetch_array($query4);

            if((int)$row4[0]>0){
        ?>
          
  
      '<?php echo $columna[2]." ".$columna[3]?>', <?php }} ?>]
    },
    series: [{
        type: 'column',
        colorByPoint: true,
        data: [
        <?php

    $select2="SELECT * FROM clientes";
    $query2=mysqli_query($conexion, $select2);
    ?>
        <?php while ($row2=mysqli_fetch_array($query2)) { 

        $select3="SELECT sum(saldo) FROM pagos where id_cliente=$row2[0]";
        $query3=mysqli_query($conexion, $select3);
        $row3=mysqli_fetch_array($query3);

        if((int)$row3[0]>0){
        echo $row3[0]?>, <?php }} ?>

        ],
        showInLegend: false 
    }]
});

document.getElementById('plain').addEventListener('click', () => {
    chart.update({
        chart: {
            inverted: false,
            polar: false
        },
        subtitle: {
            text: 'Horizontal'
        }
    });
});

document.getElementById('inverted').addEventListener('click', () => {
    chart.update({
        chart: {
            inverted: true,
            polar: false
        },
        subtitle: {
            text: 'Horizontal'
        }
    });
});

document.getElementById('polar').addEventListener('click', () => {
    chart.update({
        chart: {
            inverted: false,
            polar: true
        },
        subtitle: {
            text: 'Polar'
        }
    });
});

        </script>
    </body>
</html>
