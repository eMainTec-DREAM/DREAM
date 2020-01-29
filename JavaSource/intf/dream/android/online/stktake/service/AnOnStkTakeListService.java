package intf.dream.android.online.stktake.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AnOnStkTakeListService
{     
    public List findStkTakeList(Map map) throws Exception;
    public int deleteStkTake(List list) throws Exception;

    public List findStkTakeItemList(Map map) throws Exception;
    public int deleteStkTakeItem(List list) throws Exception;
    public int insertStkTakeItem(List list) throws Exception;
    public int updateStkTakeItem(List list) throws Exception;
}
