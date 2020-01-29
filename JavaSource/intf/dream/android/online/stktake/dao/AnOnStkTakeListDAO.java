package intf.dream.android.online.stktake.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnStkTakeListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findStkTakeList(Map map) throws Exception;
    public int deleteStkTake(Map map) throws Exception;

    public List findStkTakeItemList(Map map) throws Exception;
    public int deleteStkTakeItem(Map map) throws Exception;
    public int insertStkTakeItem(Map map) throws Exception;
    public int updateStkTakeItem(Map map) throws Exception;
}