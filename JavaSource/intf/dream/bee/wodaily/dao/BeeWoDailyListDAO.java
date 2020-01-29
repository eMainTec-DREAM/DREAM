package intf.dream.bee.wodaily.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.bee.wodaily.dto.BeeWoDailyCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeWoDailyListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findWoDailyList(BeeWoDailyCommonDTO beeWoDailyCommonDTO, Map map) throws Exception;
    public List findWoDailyCount(BeeWoDailyCommonDTO beeWoDailyCommonDTO, Map map) throws Exception;
    public int deleteWoDaily(Map map) throws Exception;
    public int insertWoDaily(Map map) throws Exception;
    public int updateWoDaily(Map map) throws Exception;
    public int updateStatus(Map map) throws Exception;
    
    public List findDailyUnPlanList(Map map) throws Exception;
    public List findDailyRoutineList(Map map) throws Exception;
    
}