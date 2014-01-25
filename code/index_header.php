<?php 
	global $rooturl;
	global $homepage;
	global $code;
	$selected = 'class="current_page_item"';
	$ccode = strtolower($code);
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>圣经在线阅读</title>
		<link rel="stylesheet" href="./design/style.css" type="text/css" media="screen">
		<meta name="description" content="">
		<meta name="keywords" content="">
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<div class="wrapper">
					<h1><a href="<?php echo $homepage ?>" title="圣经在线阅读">圣经在线阅读</a></h1>
					<ul class="menu">
						<li <?php echo $ccode== 'index' ? $selected : ''; ?>>
							<a href="<?php echo $rooturl ?>" title="Home"><span>主页</span></a>
						</li>
						<li <?php echo $ccode!= 'index' ? $selected : ''; ?>>
							<a href="<?php echo $rooturl ?>about.html" title="About"><span>关于</span></a>
						</li>
					</ul>
					<form style="display:none;" method="get" id="searchform" action="${HOME_PAGE}">
						<div>
							<input type="text" value="" name="s" id="s" class="s" onblur="if (value == ''){this.className='';} if (value !='') {this.className='s_over';} " onfocus="this.className='s_over';">
							<input type="submit" id="searchsubmit" value="Search">
						</div>
					</form>
				</div>
			</div>
			<div class="hr"></div>
			<div class="wrapper">