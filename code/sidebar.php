<?php 
	$top_label = '旧约 Old Testament';
	$bottom_label = '新约 New Testament';
	$top_content = '';
	$bottom_content = '';
?>
				<div id="sidebar">
					<ul>
						<li></li>
						<li id="old_testament" class="widget widget_popularPosts">
							<h3 class="widgettitle"><?php echo $top_label ?></h3>
							<ul class="widget_chapter">
								<?php echo $top_content ?>
							</ul>
						</li>
						<li id="new_testament" class="widget widget_popularPosts">
							<h3 class="widgettitle"><?php echo $bottom_label ?></h3>
							<ul class="widget_chapter">
								<?php echo $bottom_content ?>
							</ul>
						</li>
					</ul>
				</div>