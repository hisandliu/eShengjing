<?php
//header('Content-type: text/html; charset=utf-8'); 
date_default_timezone_set("Asia/Shanghai");

require_once 'config.php';
require_once 'org/hisand/db/BasicDAO.php';

$bible_code = '';
if (isset($_GET['p'])) {
	$_SESSION['bible_code'] = $_GET['p'];
	$bible_code = $_GET['p']; 
}
else {
	$_SESSION['bible_code'] = 'index';
	$bible_code = 'index';
}

if ('index' == $bible_code) {
	require_once 'home.php';
}
else {
	require_once 'article.php';
}

/*
$dao = new org_hisand_db_BasicDAO();
$value = $dao->scalar('select id from resource where id=3');
echo '<br/>' . $value;
*/