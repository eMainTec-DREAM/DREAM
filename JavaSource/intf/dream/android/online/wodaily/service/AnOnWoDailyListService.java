package intf.dream.android.online.wodaily.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AnOnWoDailyListService
{     
    public List findWoDailyList(Map map) throws Exception;

    public int deleteWoDaily(List list) throws Exception;
    public int insertWoDaily(List list) throws Exception;
    public int updateWoDaily(List list) throws Exception;

    
    public List findDailyUnPlanList(Map map) throws Exception;
    public List findDailyRoutineList(Map map) throws Exception;
}
