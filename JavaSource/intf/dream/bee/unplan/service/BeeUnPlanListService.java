package intf.dream.bee.unplan.service;

import java.util.List;
import java.util.Map;

import intf.dream.bee.unplan.dto.BeeUnPlanCommonDTO;

/**
 *  service
 * @author
 * @version $Id:  $
 * @since   1.0
 */
public interface BeeUnPlanListService
{     
    public List findUnPlanList(BeeUnPlanCommonDTO beeUnPlanCommonDTO, Map map) throws Exception;
    public List findUnPlanCount(BeeUnPlanCommonDTO beeUnPlanCommonDTO, Map map) throws Exception;
    public int deleteUnPlan(List list) throws Exception;
    public int insertUnPlan(List list) throws Exception;
    public int updateUnPlan(List list) throws Exception;
    
    public List findWoFailList(Map map) throws Exception;
    public int deleteWoFail(List list) throws Exception;
    public int insertWoFailList(List list) throws Exception;
    public int updateWoFailList(List list) throws Exception;
}
