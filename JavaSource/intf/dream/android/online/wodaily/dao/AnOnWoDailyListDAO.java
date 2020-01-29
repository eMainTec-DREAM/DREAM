package intf.dream.android.online.wodaily.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnWoDailyListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findWoDailyList(Map map) throws Exception;
    public int deleteWoDaily(Map map) throws Exception;
    public int insertWoDaily(Map map) throws Exception;
    public int updateWoDaily(Map map) throws Exception;
    public int updateStatus(Map map) throws Exception;
    
    public List findDailyUnPlanList(Map map) throws Exception;
    public List findDailyRoutineList(Map map) throws Exception;
    
}