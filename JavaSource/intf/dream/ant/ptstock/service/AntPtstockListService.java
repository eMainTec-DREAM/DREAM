package intf.dream.ant.ptstock.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AntPtstockListService
{     
    public List findPartsList(Map map) throws Exception;
    public List findStockList(Map map) throws Exception;
    public int savePtstock(List list) throws Exception;
}
