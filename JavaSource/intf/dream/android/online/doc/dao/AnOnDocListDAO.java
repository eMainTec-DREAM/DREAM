package intf.dream.android.online.doc.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;

/**
 * dao
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 */
public interface AnOnDocListDAO extends BaseJdbcDaoSupportIntf {
	public List findDocList(Map map) throws Exception;
}