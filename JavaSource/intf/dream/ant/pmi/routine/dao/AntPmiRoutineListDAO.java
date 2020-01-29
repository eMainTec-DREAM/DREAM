package intf.dream.ant.pmi.routine.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AntPmiRoutineListDAO extends BaseJdbcDaoSupportIntf
{
    public List findSchedList(Map map);
    public List findPmlstList(Map map);
    public List findPmequipList(Map map);
    public List findPmpointList(Map map);

    public int saveInspoint(Map<String,String> map, String wopointId);
    public String getInspoint(Map<String,String> map);

	public int mergeAbnormalRslt(Map map, String pminspointId);
	public void deleteAbnormalRslt(Map map);
	
	public void updatePmSched(Map map);
	public void updatePmPointSchedStatus(Map map);
	public int executeSP_PM_UPDATE_LASTCPLT_DATE(Map map);
	public int updatePminsList(Map map);

	public String getWoStatusOfInspection(Map map);
    
}