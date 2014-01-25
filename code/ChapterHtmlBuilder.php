<?php
require_once 'org/hisand/bible/BibleResourceManager.php';
require_once 'HtmlBuilder.php';

class ChapterHtmlBuilder extends HtmlBuilder {
	
	private $id;
	
	function __construct($code) {
		$this->code = $code;
		$this->item = $this->getItem();
		$this->id = $this->item['id'];
		$this->codelink = '-';
	}
	
	private $item = null;
	public function getItem() {
		if ($this->item == null) {
			$m = new org_hisand_bible_BibleResourceManager();
			$this->item = $m->getItemByCode($this->code);
		}
		return $this->item;
	}
	
	private $parentItem = null;
	public function getParentItem() {
		if ($this->parentItem == null) {
			$m = new org_hisand_bible_BibleResourceManager();
			$this->parentItem = $m->getParentItemById($this->id);
		}
		return $this->parentItem;
	}
	
	private $parentParentItem = null;
	public function getParentParentItem() {
		if ($this->parentParentItem == null) {
			$m = new org_hisand_bible_BibleResourceManager();
			$p = $m->getParentItemById($this->id);
			$pid = $p["id"];
			$this->parentParentItem = $m->getParentItemById($pid);
		}
		return $this->parentParentItem;
	}
	
	private $title = null;
	public function getTitle() {
		if ($this->title == null) {
			$entity = $this->getItem();
			$parent = $this->getParentItem();
			$this->title = $parent['cnname'] . '(第' . $entity['sort_order'] . '章) - 圣经在线阅读';
		}
		return $this->title;
	}
	
	private $chaptertitle = null;
	public function getChapterTitle() {
		if ($this->chaptertitle == null) {
			$entity = $this->getItem();
			$parent = $this->getParentItem();
			$parentparent = $this->getParentParentItem();
			//旧约 - 创世记(Genesis) - 第 3 章
			$this->chaptertitle = $parentparent['cnname'] . ' - ' . $parent['cnname'] . '(' . $parent['enname'] . ') - 第 ' . $entity['sort_order'] . ' 章';
		}
		return $this->chaptertitle;
	}
	
	private $topsidelabel = null;
	public function getTopSideLabel() {
		$parentparent = $this->getParentParentItem();
		if ($parentparent['code'] == 'New_Testament') {
			$this->topsidelabel = '新约 New Testament';
		}
		else {
			$this->topsidelabel = '旧约 Old Testament';
		}
		return $this->topsidelabel;
	}
	
	private $bottomsidelabel = null;
	public function getBottomSideLabel() {
		$parentparent = $this->getParentParentItem();
		if ($parentparent['code'] == 'New_Testament') {
			$this->bottomsidelabel = '旧约 Old Testament';
		}
		else {
			$this->bottomsidelabel = '新约 New Testament';
		}
		return $this->bottomsidelabel;
	}
	
	public function getTopSideColumn() {
		$parentparent = $this->getParentParentItem();
		if ($parentparent['code'] == 'New_Testament') {
			return $this->buildBookSideHtml(true);
		}
		else {
			return $this->buildBookSideHtml(false);
		}
	}
	
	private $statementList;
	private function getStatementList() {
		if ($this->statementList == null) {
			$m = new org_hisand_bible_BibleResourceManager();
			$this->statementList = $m->getChildsById($this->id);
		}
		return $this->statementList;
	}
	
	public function getBottomSideColumn() {
		$parentparent = $this->getParentParentItem();
		if ($parentparent['code'] == 'New_Testament') {
			return $this->buildBookSideHtml(false);
		}
		else {
			return $this->buildBookSideHtml(true);
		}
	}
	
	public function getStatementHtml() {
		$list = $this->getStatementList();
		$html = '';
		foreach ($list as $item) {
			$html = $html . $this->toOne($item);
		}
		return $html;
	}
	
	//<div >
	//	<span>99:99</span>
	//	<div>
	//		<p class="cn">中文</p>
	//		<p class="en">英文</p>
	//	</div>
	//</div>
	private function toOne($item) {
		$name = $item['cnname'];
		$cncontent = $item['cncontent'];
		$encontent = $item['encontent'];
		
        $h = '';
        $h = $h . '<div>';
        $h = $h . '<span>' . $name . '</span>';
        $h = $h . '<div>';
        $h = $h . '<p class="cn">' . $cncontent . '</p>';
        $h = $h . '<p class="en">' . $encontent . '</p>';
        $h = $h . '</div>';
        $h = $h . '</div>';
        
        return $h;
    }
    
    private $nav = null;
    public function buildNav() {
    	if ($this->nav == null) {
    		$this->nav = $this->buildNav0();
    	}
    	return $this->nav;
    }
	//<span class='pages'>Page 1 of 7</span><span class='current'>1</span><a href='' class='page larger'>2</a>
	//	<a href='' class='page larger'>3</a><a href='' class='page larger'>4</a>
	//	<a href='' class='page larger'>5</a><span class='extend'>...</span>
	//	<a href='' class='nextpostslink'>»</a>
	//	<a href='' class='last'>Last »</a>
	private function buildNav0() {
		$item = $this->getItem();
		$pitem = $this->getParentItem();
		$sort_order = $item['sort_order'];
		$count = $pitem['child_count'];
		$pcode = $pitem['code'];
		$size = 5;
		$codelink = $this->codelink;
		$code = '';
		$rooturl = $this->rooturl;
		
		$html = '';
		$html = '<span class="pages">共 ' . $count . ' 章</span>';
		
		$line = '';
		$url = '';
	
		if ($count <= 1) {
			return $html;
		}
		
		$lastsizeindex = floor(($count - 1) / $size);
		$sizeindex = floor(($sort_order - 1) / $size);
		$beginindex = $sizeindex * $size + 1; 
		$endindex = $sizeindex * $size + $size;
		if ($sizeindex >= $lastsizeindex) {
			$endindex = $count;
		}
		
		if ($sort_order > $size) {
			$code = $pcode . $codelink . '1';
			$url = $rooturl . $this->buildUrl($code);
			$line = '<a href="' . $url . '" class="first">首</a>';
			$html = $html . $line;
		}
		
		if ($sort_order > 1) {
			$code = $pcode . $codelink . ($sort_order - 1);
			$url = $rooturl . $this->buildUrl($code);
			$line = '<a href="' . $url . '" class="previouspostslink">«</a>';
			$html = $html . $line;
		}
		
		for ($i = $beginindex; $i <= $endindex; $i++) {
			$code = $pcode . $codelink . $i;
			$url = $rooturl . $this->buildUrl($code);
			if ($i == $sort_order) {
				$line = '<span class="current">' . $sort_order . '</span>';
			}
			else if ($i < $sort_order) {
				$line = '<a href="' . $url . '" class="page smaller">' . $i . '</a>';
			}
			else {
				$line = '<a href="' . $url . '" class="page larger">' . $i . '</a>';
			}
			$html = $html . $line;
		}
		
		if ($sort_order < $count) {
			$code = $pcode . $codelink . ($sort_order + 1);
			$url = $rooturl . $this->buildUrl($code);
			$line = '<a href="' . $url . '" class="nextpostslink">»</a>';
			$html = $html . $line;
		}
		
		if ($sizeindex < $lastsizeindex) {
			$code = $pcode . $codelink . $count;
			$url = $rooturl . $this->buildUrl($code);
			$line = '<a href="' . $url . '" class="last">尾</a>';
			$html = $html . $line;
		}
		
		return $html;
	}
	
//<div >
//	<span>99:99</span>
//	<div>
//		<p class="cn">中文</p>
//		<p class="en">英文</p>
//	</div>
//</div>
/*
	private String toOne(ResultSet rs) throws SQLException {
		String name = rs.getString("cnname");
		String cncontent = rs.getString("cncontent");
		String encontent = rs.getString("encontent");
		
        StringBuffer sb = new StringBuffer();
        sb.append("<div>\n");
        sb.append("\t<span>" + name + "</span>\n");
        sb.append("\t<div>\n");
        sb.append("\t\t<p class=\"cn\">" + cncontent + "</p>\n");
        sb.append("\t\t<p class=\"en\">" + encontent + "</p>\n");
        sb.append("\t</div>\n");
        sb.append("</div>");
        
        return sb.toString();
    }
    */
	
	
}