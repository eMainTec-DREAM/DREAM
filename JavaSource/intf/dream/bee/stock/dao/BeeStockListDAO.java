package intf.dream.bee.stock.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.bee.stock.dto.BeeStockCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeStockListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findStockList(BeeStockCommonDTO beeStockCommonDTO, Map map) throws Exception;
    public List findStockCount(BeeStockCommonDTO beeStockCommonDTO, Map map) throws Exception;
    public int updateStock(Map map) throws Exception;
    public int insertStockLog(Map map) throws Exception;
}