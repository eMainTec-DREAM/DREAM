package intf.dream.bee.pmwork.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.bee.pmwork.dto.BeePmworkCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeePmworkListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findPmworkList(BeePmworkCommonDTO beePmworkCommonDTO, Map map) throws Exception;
    public List findPmworkCount(BeePmworkCommonDTO beePmworkCommonDTO, Map map) throws Exception;
    public int deletePmwork(Map map) throws Exception;
    public int insertPmwork(Map map) throws Exception;
    public int insertWoequip(Map map) throws Exception;
    public int updateWoequip(Map map) throws Exception;
    public int deleteWoequip(Map map) throws Exception;
    public int updatePmwork(Map map) throws Exception;
    
    public List findWoCraftList(Map map) throws Exception;
    public int insertWoCraft(Map map) throws Exception;
    public int updateWoCraft(Map map) throws Exception;
    public int deleteWoCraft(Map map) throws Exception;

    public List findWoPartsList(Map map) throws Exception;
    public int insertWoParts(Map map) throws Exception;
    public int updateWoParts(Map map) throws Exception;
    public int deleteWoParts(Map map) throws Exception;
    public List findStockQty(Map map) throws Exception;

    public List findPhotoList(Map map) throws Exception;
    public String findDoc(Map map) throws Exception;
}