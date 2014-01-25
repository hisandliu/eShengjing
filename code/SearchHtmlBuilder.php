<?php
require_once 'HtmlBuilder.php';
require_once 'org/hisand/bible/BibleResourceManager.php';

class SearchHtmlBuilder extends HtmlBuilder {
	
	private $word = null;
	private $pageindex = 1;
	private $pagesize = 20;
	
	function __construct($word, $pageindex) {
		$this->word = $word;
		$this->pageindex = $pageindex;
		$this->codelink = '-';
	}

	private $title = null;
	public function getTitle() {
		return $this->word . ' - 圣经在线阅读';
	}
	
	private $chaptertitle = null;
	public function getChapterTitle() {
		return '"' . $this->word . '"' . '的查询结果';
	}
	
	private $topsidelabel = null;
	public function getTopSideLabel() {
		$this->topsidelabel = '旧约 Old Testament';
		return $this->topsidelabel;
	}
	
	private $bottomsidelabel = null;
	public function getBottomSideLabel() {
		$this->bottomsidelabel = '新约 New Testament';
		return $this->bottomsidelabel;
	}
	
	public function getTopSideColumn() {
		return $this->buildBookSideHtml(false);
	}
	
	public function getBottomSideColumn() {
		return $this->buildBookSideHtml(true);
	}
	
	private $statementList;
	private function getStatementList() {
		if ($this->word == null) {
			return '';
		}
		if ($this->statementList == null) {
			$m = new org_hisand_bible_BibleResourceManager();
			$this->statementList = $m->getListByContent($this->word, $this->pageindex, $this->pagesize);
		}
		return $this->statementList;
	}
	
	private $count = -1;
	private function getCount() {
		if ($this->word == null) {
			return 0;
		}
		if ($this->count == -1) {
			$m = new org_hisand_bible_BibleResourceManager();
			$this->count = $m->getCountByContent($this->word);
		}
		return $this->count;
	}
	
	public function getStatementHtml() {
		$list = $this->getStatementList();
		if (count($list) <= 0) {
			return '<h3>找不到和您的查询条件相符的内容或信息。</h3>';
		}
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
		$parent_code = $item['parent_code'];
		$title = $item['cntitle'];
		$cncontent = $item['cncontent'];
		$encontent = $item['encontent'];
		
		$url = $this->rooturl . $this->buildUrl($parent_code);
		
        $h = '';
        $h = $h . '<div>';
        $h = $h . '<span><a href="' . $url . '">' . $title . '</a></span>';
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
		$pageindex =  $this->pageindex;
		$total_count = $this->getCount();
		$size = 5;
		$codelink = $this->codelink;
		
		$count = ceil($total_count / $this->pagesize);
	
		$html = '';
		$html = '<span class="pages">共 ' . $count . ' 页</span>';
		
		$line = '';
		$url = '';
	
		if ($count <= 1) {
			return $html;
		}
		
		$lastsizeindex = floor(($count - 1) / $size);
		$sizeindex = floor(($pageindex - 1) / $size);
		$beginindex = $sizeindex * $size + 1; 
		$endindex = $sizeindex * $size + $size;
		if ($sizeindex >= $lastsizeindex) {
			$endindex = $count;
		}
		
		$burl = $this->rooturl . 'search.php?s=' . $this->word;
		
		if ($pageindex > $size) {
			$url = $burl;
			$line = '<a href="' . $url . '" class="first">首</a>';
			$html = $html . $line;
		}
		
		if ($pageindex > 1) {
			$url = $burl . '&amp;page=' . ($pageindex - 1);
			$line = '<a href="' . $url . '" class="previouspostslink">«</a>';
			$html = $html . $line;
		}
		
		for ($i = $beginindex; $i <= $endindex; $i++) {
			$url = $burl . '&amp;page=' . $i;
			if ($i == $pageindex) {
				$line = '<span class="current">' . $pageindex . '</span>';
			}
			else if ($i < $pageindex) {
				$line = '<a href="' . $url . '" class="page smaller">' . $i . '</a>';
			}
			else {
				$line = '<a href="' . $url . '" class="page larger">' . $i . '</a>';
			}
			$html = $html . $line;
		}
		
		if ($pageindex < $count) {
			$url = $burl . '&amp;page=' . ($pageindex + 1);
			$line = '<a href="' . $url . '" class="nextpostslink">»</a>';
			$html = $html . $line;
		}
		
		if ($sizeindex < $lastsizeindex) {
			$url = $burl . '&amp;page=' . $count;
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