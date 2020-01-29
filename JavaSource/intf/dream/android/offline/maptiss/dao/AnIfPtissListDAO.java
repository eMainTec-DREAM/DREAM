package intf.dream.android.offline.maptiss.dao;

import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnIfPtissListDAO extends BaseJdbcDaoSupportIntf
{
	public int savePtissList(Map map, String ptIssListId, String ptIssueId) throws Exception;
	public int savePtissue(Map map, String ptIssueId) throws Exception;
}