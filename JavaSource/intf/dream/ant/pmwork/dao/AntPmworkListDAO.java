package intf.dream.ant.pmwork.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AntPmworkListDAO extends BaseJdbcDaoSupportIntf
{
    public List findHdrList(Map map) throws Exception;
    public List findWocraftList(Map map) throws Exception;
    public List findWopartsList(Map map) throws Exception;
    public List findWoequipList(Map map) throws Exception;

    public int updateWorkOrder(Map map) throws Exception;
    public int insertWorkOrder(Map map, String wkorId) throws Exception;
    public int insertWorkOrderLog(Map map, String wkorId) throws Exception;
    public int changeFileSeq(Map map, String wkorId) throws Exception;
    
	public int saveWoequip(Map map, String wkorId);
	public int saveWocraft(Map map, String wkorId, String woCraftId);
	public int saveWoparts(Map map, String wkorId, String woPartId);
	
	public String getWoStatusOfWorkOrder(Map map);
}