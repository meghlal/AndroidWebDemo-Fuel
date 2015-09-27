<?php
$name=$a=array();

$name[0]['fname']="Meghlal";
$name[0]['lname']="Khan";
$name[1]['fname']="Bappa";
$name[1]['lname']="Khan";

$a['name']=$name;
$a['tol']=$_GET['num1'] + $_GET['num2'];
echo json_encode($a);