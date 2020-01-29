package intf.dream.android.offline.mainspection.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnIfInspectionListDAO extends BaseJdbcDaoSupportIntf
{
    public List findSchedList(Map map);
    public List findPmlstList(Map map);
    public List findPmequipList(Map map);
    public List findPmpointList(Map map);

    public int saveWopoint(Map<String,String> map, String wopointId);
    public String getWopoint(Map<String,String> map);

	public int mergeAbnormalRslt(Map map, String wopointId);
	public void deleteAbnormalRslt(Map map);
	
	public void updatePmSched(Map map);
    public int updateWorkOrder(Map map);
	public int updateWoCraft(Map map);
	
	public String getWoStatusOfWorkOrder(Map map);
}