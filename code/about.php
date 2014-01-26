<?php
require_once 'bible_functions.php';

$homepage = get_homepage();
$rooturl = get_rooturl(); 
$searchurl = get_searchurl();

require_once 'config.php';
require_once 'HtmlBuilder.php';

$htmlloader = new HtmlBuilder();
$htmlloader->homepage = $homepage;
$htmlloader->rooturl = $rooturl;
$htmlloader->chapter_url_template = CHAPTER_URL_TEMPLATE;

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>关于我们 - 圣经在线阅读</title>
		<link rel="stylesheet" href="./design/style.css" type="text/css" media="screen" />
		<meta name="description" content="圣经在线阅读" />
		<meta name="keywords" content="关于我们" />
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<div class="wrapper">
					<h1><a href="<?php print $homepage?>" title="圣经在线阅读">圣经在线阅读</a></h1>
					<ul class="menu">
						<li>
							<a href="<?php print $homepage?>" title="Home"><span>主页</span></a>
						</li>
						<li class="current_page_item">
							<a href="<?php print $rooturl?>about.php" title="About"><span>关于</span></a>
						</li>
					</ul>
					<form style="display:block;" method="get" id="searchform" action="<?php print $searchurl?>">
						<div>
							<input type="text" value="" name="s" id="s" class="s"
								onblur="if (value ==''){this.className='s';} if (value !='') {this.className='s_over';} "
								onfocus="this.className='s_over';" /> <input type="submit"
								id="searchsubmit" value="Search" />
						</div>
					</form>
				</div>
			</div>
			<div class="hr"></div>
			<div class="wrapper">
				<div id="content">
					<div class="post" id="post-246">
						<div>
							<h2>关于我们</h2>
						</div>
						<div class="entry">
							<p>
								《圣经在线阅读》提供中文圣经和合本阅读及查询服务，收录有关基督教的文章。
							</p>
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
					<p>
						&copy; 2011~2012 <a href="<?php print $homepage?>">圣经在线阅读</a>
					</p>
				</div>
			</div>
		</div>
		<div style="display:none;"></div>
	</body>
</html>
