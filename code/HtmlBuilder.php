<?php
require_once 'org/hisand/bible/BibleResourceManager.php';

class HtmlBuilder {

	public $homepage = '/';
	public $rooturl = '/';
	public $code;
	public $chapter_url_template = '?p=[CHAPTER_CODE]';
	public $codelink = '-';

	private $ot_list;
	public function getOtBookList() {
		if ($this->ot_list == null) {
			$m = new org_hisand_bible_BibleResourceManager();
			$this->ot_list = $m->getChildsByCode("Old_Testament");
		}
		return $this->ot_list;
	}

	private $nt_list;
	public function getNtBookList() {
		if ($this->nt_list == null) {
			$m = new org_hisand_bible_BibleResourceManager();
			$this->nt_list = $m->getChildsByCode("New_Testament");
		}
		return $this->nt_list;
	}

	public function buildBookCenterHtml($isnew, $column) {
		$burl = $this->rooturl;
		$html = '';
		$list = null;
		if ($isnew) {
			$list = $this->getNtBookList();
		}
		else {
			$list = $this->getOtBookList();
		}
		for ($i = 0; $i < count($list); $i++) {
			if ($i % 3 == $column) {
				$item = $list[$i];
				$code = $item['code'];
				$enname = $item['enname'];
				$cnname = $item['cnname'];
				$subcode = $code . $this->codelink . '1';
				$url = $burl . $this->buildUrl($subcode);
				$name = $cnname . " - " . $enname;
				$line = "<li><a href=\"" . $url . "\">" . $name . "</a></li>";
				$html = $html . $line . "\n";
			}
		}
		return $html;
	}
	
	public function buildBookSideHtml($isnew) {
		$burl = $this->rooturl;
		$html = '';
		$list = null;
		if ($isnew) {
			$list = $this->getNtBookList();
		}
		else {
			$list = $this->getOtBookList();
		}
		for ($i = 0; $i < count($list); $i++) {
			$item = $list[$i];
			$code = $item['code'];
			$enname = $item['enname'];
			$cnname = $item['cnname'];
			$subcode = $code . $this->codelink . '1';
			$url = $burl . $this->buildUrl($subcode);
			$name = $cnname . " - " . $enname;
			$line = "<li><a href=\"" . $url . "\">" . $name . "</a></li>";
			$html = $html . $line . "\n";
		}
		return $html;
	}
	
	public function buildUrl($code) {
		$url = $this->chapter_url_template;
		$url = str_replace('[CHAPTER_CODE]', $code, $url);
		return $url;
	}

}