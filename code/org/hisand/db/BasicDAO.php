<?php

require_once 'org/hisand/db/ConnectionHelper.php';

/**
 * 数据库帮助类
 * @author Administrator
 *
 */
class org_hisand_db_BasicDAO {
	public function __construct() {
   	}
  	
   	/**
	 * 
	 * @var PDO
	 */
	public $connection;
	
   	/**
   	 * @return PDO
   	 */
   	private function getConnection() {
   		return org_hisand_db_ConnectionHelper::getConnection();
   	}
   	
   	/**
   	 * 
   	 * @param string $sql 要执行的SQL语句
	 * @param array $valuesArr $valuesArr 的第一个元素是 表示要查询的值的数据类型 ，
	 * <p>即所有值对应的数据类型,其它元素是要查询的值</p>
	 * @return array
	 */
	public function queryList($sql, array $valuesArr = null) {
		$conn = $this->connection;
		$stmt = null;
		$isself = false;
		try {
			if ($conn == null) {
				$conn = $this->getConnection();
				$isself = true;
			}

			$conn->query("SET NAMES utf8");
			$stmt = $conn->prepare($sql);
			if (!$stmt) {
				throw new Exception('Prepare Error!', 111);
			}
		
			$this->bindNumArrValue($stmt, $valuesArr);
			//$stmt->execute($valuesArr);
			$stmt->execute();
		
			$list = $stmt->fetchAll(PDO::FETCH_ASSOC);
	     
			if ($isself) {
	        	org_hisand_db_ConnectionHelper::closeConnection($conn, $stmt);
	        }
	        else {
	        	org_hisand_db_ConnectionHelper::closeStmt($stmt);
	        }
			
			return $list;
		}
		catch (Exception $e) {
			if ($isself) {
	        	org_hisand_db_ConnectionHelper::closeConnection($conn, $stmt);
	        }
	        else {
	        	org_hisand_db_ConnectionHelper::closeStmt($stmt);
	        }
			throw $e;
		}
	}
	
	function bindNumArrValue($stmt, $array) {
	    if (is_object($stmt) && ($stmt instanceof PDOStatement)) {
	        for ($i = 0; $i < count($array); $i++) {
	            $value = $array[$i];
                if (is_int($value))
                    $param = PDO::PARAM_INT;
                elseif (is_bool($value))
                    $param = PDO::PARAM_BOOL;
                elseif (is_null($value))
                    $param = PDO::PARAM_NULL;
                elseif (is_string($value))
                    $param = PDO::PARAM_STR;
               	else
	                $param = FALSE;
                $stmt->bindValue($i + 1, $value, $param);
	        }
	    }
	}
	
	function bindArrayValue($req, $array, $typeArray = false) {
	    if (is_object($req) && ($req instanceof PDOStatement)) {
	        foreach($array as $key => $value) {
	            if($typeArray)
	                $req->bindValue(":$key", $value, $typeArray[$key]);
	            else {
	                if(is_int($value))
	                    $param = PDO::PARAM_INT;
	                elseif(is_bool($value))
	                    $param = PDO::PARAM_BOOL;
	                elseif(is_null($value))
	                    $param = PDO::PARAM_NULL;
	                elseif(is_string($value))
	                    $param = PDO::PARAM_STR;
	                else
	                    $param = FALSE;
	                    
	                if($param)
	                    $req->bindValue(":$key",$value,$param);
	            }
	        }
	    }
	}
	
	
   	/**
   	 * 
   	 * @param string $sql 要执行的SQL语句
	 * @param array $valuesArr $valuesArr 的第一个元素是 表示要查询的值的数据类型 ，
	 * <p>即所有值对应的数据类型,其它元素是要查询的值</p>
	 * @return array
	 */
	public function queryList2222222222($sql, $valuesArr = null) {
		$conn = $this->connection;
		$stmt = null;
		$isself = false;
		try {
			if ($conn == null) {
				$conn = $this->getConnection();
				$isself = true;
			}

			$conn->query("SET NAMES utf8");
			$stmt = $conn->prepare($sql);
			if (!$stmt) {
				throw new Exception('Prepare Error!', 111);
			}
			
			//$stmt->bind_param($typeLine, $usercode);
			if ($valuesArr !=null && count($valuesArr) > 1) {
				$ret = call_user_func_array(array($stmt, 'bind_param'), $valuesArr);  
			}
			
			$stmt->execute();
			//$stmt->bind_param();
			$result = $stmt->result_metadata();
	        $fields = array(); 
	        while ($field = $result->fetch_field()) { 
	            $name = $field->name; 
	            $fields[$name] = &$$name; 
	        } 
	        call_user_func_array(array($stmt, 'bind_result'), $fields);

	        $results = array(); 
	        while ($stmt->fetch()) { 
	            $temp = array(); 
	            foreach ($fields as $key => $val) { 
	            	$temp[$key] = $val; 
	            } 
	            array_push($results, $temp); 
	        }
        
			if ($isself) {
	        	org_hisand_db_ConnectionHelper::closeConnection($conn, $stmt);
	        }
	        else {
	        	org_hisand_db_ConnectionHelper::closeStmt($stmt);
	        }
			
			return $results;
		}
		catch (Exception $e) {
			if ($isself) {
	        	org_hisand_db_ConnectionHelper::closeConnection($conn, $stmt);
	        }
	        else {
	        	org_hisand_db_ConnectionHelper::closeStmt($stmt);
	        }
			throw $e;
		}
	}
	
	public function queryItem($sql, $valuesArr = null) {
		$list = $this->queryList($sql, $valuesArr);
		if (count($list) <= 0) return null;
		return $list[0];
	}
	
	public function scalar($sql, $valuesArr = null) {
		$item = $this->queryItem($sql, $valuesArr);
		if ($item == null) return null;
		$keys = array_keys($item);
		$r = $item[$keys[0]];
		return $r;
	}
	
	
	/**
   	 * 
   	 * @param string $sql 要执行的SQL语句
	 * @param array $valuesArr $valuesArr 的第一个元素是 表示要查询的值的数据类型 ，
	 * <p>即所有值对应的数据类型,其它元素是要查询的值</p>
	 * @return int 返回自增的ID，若是小于0，表示失败
	 */
	public function createItem($sql, $valuesArr = null) {
		$conn = $this->connection;
		$stmt = null;
		$isself = false;
		try {
			if ($conn == null) {
				$conn = $this->getConnection();
				$isself = true;
			}
			
			$conn->query("SET NAMES utf8");
			//$sql = $sql . ';select last_insert_id()';
			$stmt = $conn->prepare($sql);
			if ($valuesArr !=null && count($valuesArr) > 1) {
				$ret = call_user_func_array(array($stmt, 'bind_param'), $valuesArr);  
			}

			$result = $stmt->execute();
			
			if (!$result) {
				if ($isself) {
		        	org_hisand_db_ConnectionHelper::closeConnection($conn, $stmt);
		        }
		        else {
		        	org_hisand_db_ConnectionHelper::closeStmt($stmt);
		        }
		        return -1;
			}
	        
			$stmt = $conn->prepare('select last_insert_id()');
			$stmt->execute();
			$stmt->bind_result($id);
			$stmt->fetch();
       		
			if (!$id) {
				$id = 9999;
			}
			
	        if ($isself) {
	        	org_hisand_db_ConnectionHelper::closeConnection($conn, $stmt);
	        }
	        else {
	        	org_hisand_db_ConnectionHelper::closeStmt($stmt);
	        }

			return $id;
		}
		catch (Exception $e) {
			if ($isself) {
	        	org_hisand_db_ConnectionHelper::closeConnection($conn, $stmt);
	        }
			else {
	        	org_hisand_db_ConnectionHelper::closeStmt($stmt);
	        }
			throw $e;
		}
	}
	
	/**
   	 * 
   	 * @param string $sql 要执行的SQL语句
	 * @param array $valuesArr $valuesArr 的第一个元素是 表示要查询的值的数据类型 ，
	 * <p>即所有值对应的数据类型,其它元素是要查询的值</p>
	 * @return int 返回影响的行数，若是小于0，表示失败
	 */
	public function updateItem($sql, $valuesArr = null) {
		$conn = $this->connection;
		$stmt = null;
		$isself = false;
		try {
			if ($conn == null) {
				$conn = $this->getConnection();
				$isself = true;
			}
			
			$conn->query("SET NAMES utf8");
			$stmt = $conn->prepare($sql);
			if ($valuesArr !=null && count($valuesArr) > 1) {
				$ret = call_user_func_array(array($stmt, 'bind_param'), $valuesArr);  
			}

			$result = $stmt->execute();
			
			if (!$result) {
				if ($isself) {
		        	org_hisand_db_ConnectionHelper::closeConnection($conn, $stmt);
		        }
		        else {
		        	org_hisand_db_ConnectionHelper::closeStmt($stmt);
		        }
		        return -1;
			}
	        
			$rows = $conn->affected_rows;
	
			if (!$rows) {
				$rows = 9999;
			}

	        if ($isself) {
	        	org_hisand_db_ConnectionHelper::closeConnection($conn, $stmt);
	        }
	        else {
	        	org_hisand_db_ConnectionHelper::closeStmt($stmt);
	        }

			return $rows;
		}
		catch (Exception $e) {
			if ($isself) {
	        	org_hisand_db_ConnectionHelper::closeConnection($conn, $stmt);
	        }
			else {
	        	org_hisand_db_ConnectionHelper::closeStmt($stmt);
	        }
			throw $e;
		}
	}
	
	/**
   	 * 
   	 * @param string $sql 要执行的SQL语句
	 * @param array $valuesArr $valuesArr 的第一个元素是 表示要查询的值的数据类型 ，
	 * <p>即所有值对应的数据类型,其它元素是要查询的值</p>
	 * @return int 返回删除成功的行数，失败返回-1
	 */
	public function deleteItem($sql, $valuesArr = null) {
		$conn = $this->connection;
		$stmt = null;
		$isself = false;
		try {
			if ($conn == null) {
				$conn = $this->getConnection();
				$isself = true;
			}
			
			$conn->query("SET NAMES utf8");
			//$sql = $sql . ';select last_insert_id()';
			$stmt = $conn->prepare($sql);
			if ($valuesArr !=null && count($valuesArr) > 1) {
				$ret = call_user_func_array(array($stmt, 'bind_param'), $valuesArr);  
			}

			$result = $stmt->execute();
			
			if (!$result) {
				if ($isself) {
		        	org_hisand_db_ConnectionHelper::closeConnection($conn, $stmt);
		        }
		        else {
		        	org_hisand_db_ConnectionHelper::closeStmt($stmt);
		        }
		        return -1;
			}

			$rows = $conn->affected_rows;
	
			if (!$rows) {
				$rows = 9999;
			}
			
	        if ($isself) {
	        	org_hisand_db_ConnectionHelper::closeConnection($conn, $stmt);
	        }
	        else {
	        	org_hisand_db_ConnectionHelper::closeStmt($stmt);
	        }

			return $rows;
		}
		catch (Exception $e) {
			if ($isself) {
	        	org_hisand_db_ConnectionHelper::closeConnection($conn, $stmt);
	        }
			else {
	        	org_hisand_db_ConnectionHelper::closeStmt($stmt);
	        }
			throw $e;
		}
	}

}

?>