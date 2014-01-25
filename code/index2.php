<?php
header('Content-type: text/html; charset=utf-8'); 

require_once 'bible_functions.php';
require_once 'org/hisand/bible/BibleResourceManager.php';
require_once 'org/hisand/db/BasicDAO.php';
require_once 'org/hisand/db/ConnectionHelper.php';

$homepage = get_homepage();
$rooturl = get_rooturl();
$searchurl = get_searchurl();


require_once 'config.php';
require_once 'HtmlBuilder.php';

$htmlloader = new HtmlBuilder();
$htmlloader->homepage = $homepage;
$htmlloader->rooturl = $rooturl;
$htmlloader->chapter_url_template = CHAPTER_URL_TEMPLATE;
//echo $htmlloader->buildBookSideHtml(true);

/*		
$dao = new org_hisand_db_BasicDAO();
$sql = 'select * from resource where id=2';
$item = $dao->queryItem($sql);
echo "item:";
foreach ($item as $key => $value) {
	echo $key . ':' . $value . ";";
}
echo '<br/>';
*/
/*
$dao = new org_hisand_db_BasicDAO();
$sql = "select id from resource where code='New_Testament'";
$item = $dao->scalar($sql);
echo $item;
echo '<br/>';

$m = new org_hisand_bible_BibleResourceManager();

$item = $m->getItem(2);
echo "item:";
foreach ($item as $key => $value) {
	echo $key . ':' . $value . ";";
}
echo '<br/>';
*/


$m = new org_hisand_bible_BibleResourceManager();
$id = $m->getIdByCode('New_Testament');
echo "id:" . $id;
echo '<br/>';

$list = $m->getChildsByCode('New_Testament');
echo "count:" . count($list);
echo '<br/>';

foreach ($list as $item) {
	foreach ($item as $key => $value) {
		echo $key . ':' . $value . ";";
	}
	echo "<br/>\n";
}

echo $homepage;

echo '<br/>';

echo $searchurl;


/*
$conn = org_hisand_db_ConnectionHelper::getConnection();
$conn->exec("SET NAMES 'utf8';");
$stmt = $conn->prepare('select id,cnname from resource where id<? and cnname like ? ');
$stmt->execute(array(100, '%1%'));
$list = $stmt->fetchAll(PDO::FETCH_ASSOC);
print_r($list);
foreach ($list as $item) {
	foreach ($item as $key => $value) {
		echo $key . ':' . $value . ";";
	}
	echo "<br/>\n";
}
*/
echo '<br/>end';
