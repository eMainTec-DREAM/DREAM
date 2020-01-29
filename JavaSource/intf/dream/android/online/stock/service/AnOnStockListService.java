package intf.dream.android.online.stock.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AnOnStockListService
{     
    public List findStockList(Map map) throws Exception;
    public int updateStock(List list) throws Exception;
}
