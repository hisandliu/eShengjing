<?php
require_once 'bible_functions.php';

global $bible_code;

//if (!isset($_SESSION['bible_code'])) {
//	return;
//}
//$code = $_SESSION['bible_code'];
//if ("index" != strtolower($code)) {
//	return;
//}

$code = $bible_code;
if ("index" != strtolower($code)) {
	return;
}

$homepage = get_homepage();
$rooturl = get_rooturl();
$searchurl = get_searchurl();

require_once 'config.php';
require_once 'HtmlBuilder.php';

$htmlloader = new HtmlBuilder();
$htmlloader->homepage = $homepage;
$htmlloader->rooturl = $rooturl;
$htmlloader->code = $code;
$htmlloader->chapter_url_template = CHAPTER_URL_TEMPLATE;

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>圣经在线阅读</title>
<link rel="stylesheet" href="./design/style.css" type="text/css"
	media="screen" />
<meta name="description" content="" />
<meta name="keywords" content="" />
</head>
<body>
<div id="wrapper">
<div id="header">
<div class="wrapper">
<h1><a href="<?php print $homepage?>" title="圣经在线阅读">圣经在线阅读</a></h1>
<ul class="menu">
	<li class="current_page_item"><a href="<?php print $homepage?>"
		title="Home"><span>主页</span></a></li>
	<li><a href="<?php print $rooturl?>about.php" title="About"><span>关于</span></a>
	</li>
</ul>
<form style="display: block;" method="get" id="searchform"
	action="<?php print $searchurl?>">
<div><input type="text" value="" name="s" id="s" class="s"
	onblur="if (value ==''){this.className='s';} if (value !='') {this.className='s_over';} "
	onfocus="this.className='s_over';" /> <input type="submit"
	id="searchsubmit" value="Search" /></div>
</form>
</div>
</div>
<div class="hr"></div>
<div class="wrapper">
<div id="content">
<div class="post" id="post-246">
<div>
<h2>旧约全书 Old Testament</h2>
</div>
<div class="entry">
<p>
《旧约圣经》是基督教对《圣经》全书的前一部分的常用称呼。旧约圣经原是犹太教主要经籍《塔纳赫》，《圣经》本身显示《希伯来圣经》从摩西带领以色列人出埃及时开始撰写，直到耶稣降生前大约500年完成，前后经历大约1000年左右；有学者认为是由巴比伦之囚时期开始直到公元前一世纪，在此段约240年的时间写成，后来被基督宗教全盘收纳为《圣经全书》的前部分，伊斯兰教的《古兰经》很多内容也相同。不同的基督宗教所承认的《旧约圣经》略有不同，天主教版本承认46卷；东正教版本承认50卷；基督新教版本则为39卷。
</p>
</div>
<div class="links">

<ul style="float: left; width: 195px;">
<?php echo $htmlloader->buildBookCenterHtml(false, 0); ?>
</ul>
<ul style="float: left; width: 180px;">
<?php echo $htmlloader->buildBookCenterHtml(false, 1); ?>
</ul>
<ul style="float: right; width: 180px;">
<?php echo $htmlloader->buildBookCenterHtml(false, 2); ?>
</ul>
</div>
</div>
<div class="hr"></div>
<div class="post" id="post-246">
<div>
<h2>新约全书 New Testament</h2>
</div>
<div class="entry">
<p>
新约全书共27卷。新约的内容可以大致分为几个不同的类别，例如：四福音书和一些书信。但是他们都有着共同的主题，即强调耶稣的身份、生平和地位。耶稣去世及复活升天之后，基督徒就在各地宣讲耶稣的言语和作为。几年之内，地中海东岸就建立了一些基督教会。新约中的一些书信就是部份杰出的基督徒写给这些新建立的教会的。而耶稣的言语到了60年之后才被记录为文字，这就是所说的福音书。
</p>
</div>
<div class="links">
<ul style="float: left; width: 195px;">
<?php echo $htmlloader->buildBookCenterHtml(true, 0); ?>
</ul>
<ul style="float: left; width: 180px;">
<?php echo $htmlloader->buildBookCenterHtml(true, 1); ?>
</ul>
<ul style="float: right; width: 180px;">
<?php echo $htmlloader->buildBookCenterHtml(true, 2); ?>
</ul>
</div>
</div>
</div>
<div id="sidebar">
<ul>
	<li></li>
	<li id="old_testament" class="widget widget_popularPosts">
	<h3 class="widgettitle">旧约 Old Testament</h3>
	<ul class="widget_chapter">
	<?php echo $htmlloader->buildBookSideHtml(false); ?>
	</ul>
	</li>
	<li id="new_testament" class="widget widget_popularPosts">
	<h3 class="widgettitle">新约 New Testament</h3>
	<ul class="widget_chapter">
	<?php echo $htmlloader->buildBookSideHtml(true); ?>
	</ul>
	</li>
</ul>
</div>
</div>
<div class="hr"></div>
<div class="clear"></div>
<div id="footer">
<div class="wrapper">
<p>&copy; 2011~2012 <a href="<?php print $homepage?>">圣经在线阅读</a></p>
</div>
</div>
</div>
<div style="display: none;"></div>
</body>
</html>