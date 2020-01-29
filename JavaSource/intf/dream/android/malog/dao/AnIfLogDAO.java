package intf.dream.android.malog.dao;

import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnIfLogDAO extends BaseJdbcDaoSupportIntf
{
	public int saveLoginLog(Map map) throws Exception;
}