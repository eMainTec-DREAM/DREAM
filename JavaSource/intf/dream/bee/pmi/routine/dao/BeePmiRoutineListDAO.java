package intf.dream.bee.pmi.routine.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.bee.pmi.routine.dto.BeePmiRoutineCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeePmiRoutineListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findPmiRoutineList(BeePmiRoutineCommonDTO beePmiRoutineCommonDTO, Map map) throws Exception;
    public List findPmiRoutineCount(BeePmiRoutineCommonDTO beePmiRoutineCommonDTO, Map map) throws Exception;
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

	public void updatePmSched(Map map);
	public void updatePmPointSchedStatus(Map map);
	public int executeSP_PM_UPDATE_LASTCPLT_DATE(Map map);
	public int updatePminsList(Map map);
	public String getInspoint(Map map);
}