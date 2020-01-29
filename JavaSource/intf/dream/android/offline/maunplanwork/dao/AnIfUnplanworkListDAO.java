package intf.dream.android.offline.maunplanwork.dao;

import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnIfUnplanworkListDAO extends BaseJdbcDaoSupportIntf
{
    public int updateWorkOrder(Map map) throws Exception;
    public int insertWorkOrder(Map map, String wkorId) throws Exception;
    public int insertWorkOrderLog(Map map, String wkorId) throws Exception;
    public int changeFileSeq(Map map, String wkorId) throws Exception;
    public int changeWoReqResWkorId(Map map, String wkorId) throws Exception;
    public int changeWoReqStatus(Map map, String wkorId) throws Exception;
    
	public int saveWoequip(Map map, String wkorId);
	public int saveWofail(Map map, String wkorId);
	public int updateWorkOrderEqAsmgId(Map map, String wkorId);
	public int saveWocraft(Map map, String wkorId, String woCraftId);
	public int saveWoparts(Map map, String wkorId, String woPartId);

	public String getWoStatusOfWorkOrder(Map map);
}