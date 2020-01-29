package intf.dream.bee.inspection.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.bee.inspection.dto.BeeInspectionCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeInspectionListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findInspectionList(BeeInspectionCommonDTO beeInspectionCommonDTO, Map map) throws Exception;
    public List findInspectionCount(BeeInspectionCommonDTO beeInspectionCommonDTO, Map map) throws Exception;
    public List findPointList(Map map) throws Exception;
    public List findPointHistList(Map map) throws Exception;
    public List findWoPointCount(Map map) throws Exception;
    public List findStatus(Map map) throws Exception;

    public int insertPoint(Map map) throws Exception;
    public int updatePoint(Map map) throws Exception;
    public int mergeAbnormalRslt(Map map) throws Exception;

    public int updateStartDate(Map map) throws Exception;
    public int updateEndDate(Map map) throws Exception;
    
    
    public String getWopointCount(Map map) throws Exception;
    public String getPmpointCount(Map map) throws Exception;
    
    public String getWopoint(Map map);
}