package intf.dream.android.offline.maptstock.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnIfPtstockListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findPartsList(Map map) throws Exception;
    public List findStockList(Map map) throws Exception;
	public int savePtstock(Map map) throws Exception;
	public int savePtstockLog(Map map, String ptStockAdjId) throws Exception;
}