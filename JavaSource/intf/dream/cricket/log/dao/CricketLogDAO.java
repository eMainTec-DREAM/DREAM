package intf.dream.cricket.log.dao;

import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface CricketLogDAO extends BaseJdbcDaoSupportIntf
{
	public int saveLoginLog(Map map) throws Exception;
}