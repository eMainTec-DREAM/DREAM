package intf.dream.bee.stock.service;

import java.util.List;
import java.util.Map;

import intf.dream.bee.stock.dto.BeeStockCommonDTO;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeeStockListService
{     
    public List findStockList(BeeStockCommonDTO beeStockCommonDTO, Map map) throws Exception;
    public List findStockCount(BeeStockCommonDTO beeStockCommonDTO, Map map) throws Exception;
    public int updateStock(List list) throws Exception;
}
