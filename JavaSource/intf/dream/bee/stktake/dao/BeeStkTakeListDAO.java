package intf.dream.bee.stktake.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.bee.stktake.dto.BeeStkTakeCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeStkTakeListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findStkTakeList(BeeStkTakeCommonDTO beeStkTakeCommonDTO, Map map) throws Exception;
    public List findStkTakeCount(BeeStkTakeCommonDTO beeStkTakeCommonDTO, Map map) throws Exception;
    public int deleteStkTake(Map map) throws Exception;

    public List findStkTakeItemList(Map map) throws Exception;
    public int deleteStkTakeItem(Map map) throws Exception;
    public int insertStkTakeItem(Map map) throws Exception;
    public int updateStkTakeItem(Map map) throws Exception;
}