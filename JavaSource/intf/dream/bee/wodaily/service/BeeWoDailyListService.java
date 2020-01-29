package intf.dream.bee.wodaily.service;

import java.util.List;
import java.util.Map;

import intf.dream.bee.wodaily.dto.BeeWoDailyCommonDTO;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeeWoDailyListService
{     
    public List findWoDailyList(BeeWoDailyCommonDTO beeWoDailyCommonDTO, Map map) throws Exception;
    public List findWoDailyCount(BeeWoDailyCommonDTO beeWoDailyCommonDTO, Map map) throws Exception;

    public int deleteWoDaily(List list) throws Exception;
    public int insertWoDaily(List list) throws Exception;
    public int updateWoDaily(List list) throws Exception;

    
    public List findDailyUnPlanList(Map map) throws Exception;
    public List findDailyRoutineList(Map map) throws Exception;
}
