<?php
require_once 'org/hisand/db/ConnectionHelper.php';

/**
 * 数据库帮助类
 * @author Administrator
 *
 */
class org_hisand_db_DataSource {

	public function __construct() {
   	}

    static private $instances = array();

    /**
     * 数据库连接字符串(Database Source Name)
     * @var string
     */
    private $dsn = '';

    /**
     * 用户名
     * @var string
     */
    private $username = '';

    /**
     * 密码
     * @var string
     */
    private $password = '';

    /**
     * 是否开启持久连接
     * @var bool
     */
    private $isPersisten = false;

    /**
     * 是否开启仿真预编译
     * @var bool
     */
    private $isEmulate = false;

    
	/**
	 * 
	 * Enter description here ...
     * @param string $dsn       连接字符串
     * @param string $usr       数据库用户名
     * @param string $pwd       数据库密码
     * @param bool   $emulate   仿真预编译查询
     * @param bool   $persisten 是否持久连接
     * @return PDO
     */
    public function getPDO($dsn, $usr, $pwd, $emulate = false, $persisten = false) {
		$this->dsn = $dsn;
		$this->username = $usr;
        $this->password = $pwd;
        $this->isPersisten = (bool)$persisten;
        $this->isEmulate = (bool)$emulate;
  
        $pdo = new PDO($this->dsn, $this->username, $this->password);
			// 错误模式为抛出 PDOException 异常
		$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            // 开启查询缓存
        $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, $this->isEmulate);
            // 开启持久连接
        $pdo->setAttribute(PDO::ATTR_PERSISTENT, $this->isPersisten);
        
        return $pdo;
    }

}
   	