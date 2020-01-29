package intf.dream.ant.woreq.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AntWoReqListDAO extends BaseJdbcDaoSupportIntf
{
    public List findWoReqList(Map map) throws Exception;
    public List findWoReqFileList(Map map) throws Exception;
    public List findWoReqResList(Map map) throws Exception;

    public int insertWoReqList(Map map, String woReqId) throws Exception;
    public int changeFileSeq(Map map, String woReqId) throws Exception;
    public int updateWoResList(Map map) throws Exception;
    public int insertWoReqResList(Map map) throws Exception;
    public int updateWoReqStatus(Map map) throws Exception;
    
    public String getWoReqStatus(Map map) throws Exception;
}