<?php
$a=array();
$a['name']="Meghlal Khan";
$a['tol']=$_GET['num1'] + $_GET['num2'];
echo json_encode($a);