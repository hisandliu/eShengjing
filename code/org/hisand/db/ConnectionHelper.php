<?php
require_once 'config.php';
require_once 'org/hisand/db/DataSource.php';

class org_hisand_db_ConnectionHelper {

	private static $instance;
	
	function __construct() {
   	}
   	
   	/**
   	 * 
   	 * Enter description here ...
   	 * @return org_hisand_db_ConnectionHelper
   	 */
   	private static function getInstance() {
   		if (org_hisand_db_ConnectionHelper::$instance == null) {
			org_hisand_db_ConnectionHelper::$instance = new org_hisand_db_ConnectionHelper();
		}
		return org_hisand_db_ConnectionHelper::$instance;
   	}
	
   
   	/**
   	 * 取得  PDO 连接
   	 * @param $connectionId 连接的 Id
	 * @return PDO
	 */
	public static function getConnection($connectionId = 'default') {
		try {
			$dsn = DB_DSN;
			$username = DB_USERNAME;
			$password = DB_PASSWORD;
			$ds = new org_hisand_db_DataSource();
			$conn = $ds->getPDO($dsn, $username, $password);
			return $conn;
		}
		catch (PDOException $e) {
			//echo $e->getMessage();
			$ex = new Exception('Get Connection failed!', '9999');
			throw $ex;
		}
	}

	/**
	 * 
	 * @param PDOStatement $stmt
	 */
	public static function closeStmt($stmt = null) {
		try {
			if ($stmt != null) {
				$stmt->closeCursor();
			}
		}
		catch (Exception $e) {
		}
	}
	
	/**
	 * 
	 * @param PDO $conn
	 * @param PDOStatement $stmt
	 */
	public static function closeConnection($conn, $stmt = null) {
		try {
			if ($stmt != null) {
				$stmt->closeCursor();
			}
			if ($conn != null) {
				$conn = null;
			}
		}
		catch (Exception $e) {
		}
	}

}
