package intf.dream.android.offline.maptstktake.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnIfPtstktakeListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findStktakeList(Map map) throws Exception;
    public List findStktakeItem(Map map) throws Exception;
	public int savePtstktakeItem(Map map) throws Exception;
}