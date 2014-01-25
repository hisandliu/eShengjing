<?php
header('Content-type: text/html; charset=utf-8'); 
date_default_timezone_set("Asia/Shanghai");

require_once 'tests/BibleResourceManagerTest.php';

$m = new tests_BibleResourceManagerTest();
$m->getParentItemById();

require_once 'HtmlBuilder.php';
$hb = new HtmlBuilder();
echo $hb->buildColumnCenterHtml(false, 0);