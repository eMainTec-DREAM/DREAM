package intf.dream.bee.inspection.service;

import java.util.List;
import java.util.Map;

import intf.dream.bee.inspection.dto.BeeInspectionCommonDTO;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeeInspectionListService
{     
    public List findInspectionList(BeeInspectionCommonDTO beeInspectionCommonDTO, Map map) throws Exception;
    public List findInspectionCount(BeeInspectionCommonDTO beeInspectionCommonDTO, Map map) throws Exception;
    public List findPointList(Map map) throws Exception;
    public List findPointHistList(Map map) throws Exception;
    public List findWoPointCount(Map map) throws Exception;
    public List findStatus(Map map) throws Exception;

    public int insertPoint(List list) throws Exception;
    public int updatePoint(List list) throws Exception;
}
