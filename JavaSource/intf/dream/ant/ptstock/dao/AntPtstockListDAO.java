package intf.dream.ant.ptstock.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AntPtstockListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findPartsList(Map map) throws Exception;
    public List findStockList(Map map) throws Exception;
	public int savePtstock(Map map) throws Exception;
	public int savePtstockLog(Map map, String ptStockAdjId) throws Exception;
}