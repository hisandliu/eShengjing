<?php
require_once 'config.php';

function get_homepage() {
	$contextpath = CONTEXT_PATH;
	$server_port = $_SERVER['SERVER_PORT'];
	if ($server_port == '80' || $server_port == '') {
		$server_port = '';
	}
	else {
		$server_port = ':' . $server_port;
	}
	$r = 'http://' . $_SERVER['SERVER_NAME'] . $server_port . $contextpath . '/';
	return $r;
}

function get_rooturl() {
	$contextpath = CONTEXT_PATH;
	$server_port = $_SERVER['SERVER_PORT'];
	if ($server_port == '80' || $server_port == '') {
		$server_port = '';
	}
	else {
		$server_port = ':' . $server_port;
	}
	$r = 'http://' . $_SERVER['SERVER_NAME'] . $server_port . $contextpath . '/';
	return $r;
}

function get_searchurl() {
	$contextpath = CONTEXT_PATH;
	$server_port = $_SERVER['SERVER_PORT'];
	if ($server_port == '80' || $server_port == '') {
		$server_port = '';
	}
	else {
		$server_port = ':' . $server_port;
	}
	$r = 'http://' . $_SERVER['SERVER_NAME'] . $server_port . $contextpath . '/' . 'search.php';
	return $r;
}