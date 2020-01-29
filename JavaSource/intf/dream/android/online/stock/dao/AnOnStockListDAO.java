package intf.dream.android.online.stock.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnStockListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findStockList(Map map) throws Exception;
    public int updateStock(Map map) throws Exception;
    public int insertStockLog(Map map) throws Exception;
}