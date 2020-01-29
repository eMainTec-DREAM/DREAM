package intf.dream.cricket.woreq.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface CricketWoReqListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findWoReqList(Map map) throws Exception;
    public int deleteWoReq(Map map) throws Exception;
    public int insertWoReq(Map map) throws Exception;
    public int updateWoReq(Map map) throws Exception;
    public int updateWoReqStatus(Map map) throws Exception;
    
    public List findWoResList(Map map) throws Exception;
    public int updateWoRes(Map map) throws Exception;
    public int deleteWoRes(Map map) throws Exception;

    public List findWoReqResList(Map map) throws Exception;
    public int insertWoReqRes(Map map) throws Exception;
    public int updateWoReqRes(Map map) throws Exception;
    public int deleteWoReqRes(Map map) throws Exception;

    public List findPhotoList(Map map) throws Exception;
    public String findDoc(Map map) throws Exception;
}