package intf.dream.ant.cal.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AntCalListDAO extends BaseJdbcDaoSupportIntf
{
    public List findHdrList(Map map) throws Exception;
    public List findWocraftList(Map map) throws Exception;
    public List findWocalibList(Map map) throws Exception;
    public List findWocalibValueList(Map map) throws Exception;
    public List findWoequipList(Map map) throws Exception;
    public List findWocalibStdEq(Map map) throws Exception;
    public List findWocalibGnlValue(Map map) throws Exception;
    public List findWocalibSclValue(Map map) throws Exception;

    public int updateWorkOrder(Map map) throws Exception;
    public int insertWorkOrder(Map map, String wkorId) throws Exception;
    public int insertWorkOrderLog(Map map, String wkorId) throws Exception;
    public int changeFileSeq(Map map, String wkorId) throws Exception;
    
	public int saveWoequip(Map map, String wkorId);
	public int saveWocraft(Map map, String wkorId, String woCraftId);
	public int saveWocalib(Map map, String wkorId);
	public int saveWocalibValue(Map map, String wkorId);
	public int saveWocalibStdEq(Map map, String wkorId, String sqawocalibstdeq_id);
	public int updateCalStdEqNextDateList(Map map, String wkorId, String sqawocalibstdeq_id);
	public int saveWocalibGnlValue(Map map, String wkorId);
	public int saveWocalibSclValue(Map map, String wkorId);
	
	public String getWoStatusOfWorkOrder(Map map);
}