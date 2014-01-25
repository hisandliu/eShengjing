<?php
include_once 'org/hisand/db/BasicDAO.php';

class org_hisand_bible_BibleResourceManager {
	private $fieldLine = 'id,parent_code,code,child_count,sort_order,enname,cnname,encontent,cncontent';
	private $tablename = 'resource';
	private $dao = null;

	function __construct() {
		$this->dao = new org_hisand_db_BasicDAO();
	}

	private function buildBasicSql() {
		$sql = 'select ' . $this->fieldLine . ' from ' . $this->tablename;
		return $sql;
	}
	
	public function getItem($id) {
		$sql = $this->buildBasicSql();
		$sql = $sql . ' where $id=?';
		$values = array($id);
		return $this->dao->queryItem($sql, $values);
	}
	
	public function getItemByCode($code) {
		$sql = $this->buildBasicSql();
		$sql = $sql . ' where code=?';
		$values = array($code);
		return $this->dao->queryItem($sql, $values);
	}

	public function getParentItemById($id) {
		$sql = $this->buildBasicSql();
		$sql = $sql . ' where id in (select parent_id from ' . $this->tablename . ' where id=?)';
		$values = array($id);
		return $this->dao->queryItem($sql, $values);
	}

	public function getParentItemByCode($code) {
		$id = $this->getIdByCode($code);
		return $this->getParentItemById($id);
	}

	public function getChildsById($id) {
		$sql = $this->buildBasicSql();
		$sql = $sql . ' where parent_id=? order by sort_order';
		$values = array($id);
		return $this->dao->queryList($sql, $values);
	}

	public function getChildsByCode($code) {
		$id = $this->getIdByCode($code);
		return $this->getChildsById($id);
	}

	public function getIdByCode($code) {
		$sql = 'select id from ' . $this->tablename . ' where code=?';
		$values = array($code);
		$object =  $this->dao->scalar($sql, $values);
		if ($object == null) return -1;
		return $object;
	}
	
	public function getListByContent($content, $pageindex, $pagesize) {
		$value = '%' . $content . '%';
		$start = ($pageindex - 1) * $pagesize;
		$sql = 'select * from ' . $this->tablename . 
			' where cncontent like  ? or encontent like ? and depth=4 limit ?, ?';
		$values = array($value, $value, $start, $pagesize);
		$list =  $this->dao->queryList($sql, $values);
		return $list;
	}
	
	public function getCountByContent($content) {
		$value = '%' . $content . '%';
		$sql = 'select count(id) from ' . $this->tablename . 
			' where cncontent like ? or encontent like ? and depth=4';
		$values = array($value, $value);
		$object =  $this->dao->scalar($sql, $values);
		if ($object == null) return 0;
		return $object;
	}
}