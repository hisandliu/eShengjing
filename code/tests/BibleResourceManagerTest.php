<?php

require_once 'org/hisand/bible/BibleResourceManager.php';

class tests_BibleResourceManagerTest {
	
	public function getItemByCode() {
		$m = new org_hisand_bible_BibleResourceManager();
		$r = $m->getItemByCode("Job");
		foreach ($r as $key => $value) {
			echo $key . ':' . $value . "\n";
		}
	}

	public function getParentItemById() {
		$m = new org_hisand_bible_BibleResourceManager();
		$r = $m->getParentItemById(13325);
		foreach ($r as $key => $value) {
			echo $key . ':' . $value . "\n";
		}
	}
	public function getIdByCode() {
		$m = new org_hisand_bible_BibleResourceManager();
		$r = $m->getIdByCode("Job");
		echo $r;
	}

	public function getChildsById() {
		$m = new org_hisand_bible_BibleResourceManager();
		$list = $m->getChildsById(13325);
		foreach ($list as $item) {
			$line = '';
			foreach ($item as $key => $value) {
				$line = $line . $key . ':' . $value . ";";
			}
			echo $line . "\n";
		}
	}

	public function getChildsByCode() {
		$m = new org_hisand_bible_BibleResourceManager();
		$list = $m->getChildsByCode('Job');
		foreach ($list as $item) {
			$line = '';
			foreach ($item as $key => $value) {
				$line = $line . $key . ':' . $value . ";";
			}
			echo $line . "\n";
		}
	}
}