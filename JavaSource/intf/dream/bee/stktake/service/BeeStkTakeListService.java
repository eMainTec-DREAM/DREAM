package intf.dream.bee.stktake.service;

import java.util.List;
import java.util.Map;

import intf.dream.bee.stktake.dto.BeeStkTakeCommonDTO;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeeStkTakeListService
{     
    public List findStkTakeList(BeeStkTakeCommonDTO beeStkTakeCommonDTO, Map map) throws Exception;
    public List findStkTakeCount(BeeStkTakeCommonDTO beeStkTakeCommonDTO, Map map) throws Exception;
    public int deleteStkTake(List list) throws Exception;

    public List findStkTakeItemList(Map map) throws Exception;
    public int deleteStkTakeItem(List list) throws Exception;
    public int insertStkTakeItem(List list) throws Exception;
    public int updateStkTakeItem(List list) throws Exception;
}
