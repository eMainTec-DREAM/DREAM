package intf.dream.android.online.unplan.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.bee.asset.dto.BeeAssetCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnUnPlanListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findUnPlanList(Map map) throws Exception;
    public int deleteUnPlan(Map map) throws Exception;
    public int insertUnPlan(Map map) throws Exception;
    public int updateUnPlan(Map map) throws Exception;
    
    public List findWoFailList(Map map) throws Exception;
    public int deleteWoFail(Map map) throws Exception;
    public int insertWoFailList(Map map) throws Exception;
    public int updateWorkOrderEqAsmbId(Map map) throws Exception;
    public int updateWoFailList(Map map) throws Exception;
    public String findWoFailCount(Map map) throws Exception;
}