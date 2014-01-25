<?php
require_once 'bible_functions.php';

global $bible_code;

$code = $bible_code;

$homepage = get_homepage();
$rooturl = get_rooturl();
$searchurl = get_searchurl();

require_once 'config.php';
require_once 'ChapterHtmlBuilder.php';

$loader = new ChapterHtmlBuilder($code);
$loader->homepage = $homepage;
$loader->rooturl = $rooturl;
$loader->chapter_url_template = CHAPTER_URL_TEMPLATE;

$item = $loader->getItem();
if ($item == null) {
	header("Location:404.php");
}

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><?php echo $loader->getTitle() ?></title>
<link rel="stylesheet" href="./design/style.css" type="text/css" />
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
	<li><a href="<?php print $rooturl?>about.php"><span>关于</span></a></li>
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
<div class="post">
<h2><?php echo $loader->getChapterTitle() ?></h2>
<div class="navigation">
<div class='wp-pagenavi'><?php echo $loader->buildNav() ?></div>
</div>
<div class="section"><?php echo $loader->getStatementHtml() ?></div>
<div class="navigation">
<div class='wp-pagenavi'><?php echo $loader->buildNav() ?></div>
</div>
</div>
</div>
<div id="sidebar">
<ul>
	<li></li>
	<li id="old_testament" class="widget widget_popularPosts">
	<h3 class="widgettitle"><?php echo $loader->getTopSideLabel() ?></h3>
	<ul class="widget_chapter">
	<?php echo $loader->getTopSideColumn() ?>
	</ul>
	</li>
	<li id="new_testament" class="widget widget_popularPosts">
	<h3 class="widgettitle"><?php echo $loader->getBottomSideLabel() ?></h3>
	<ul class="widget_chapter">
	<?php echo $loader->getBottomSideColumn() ?>
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
