package intf.dream.bee.woreq.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.bee.woreq.dto.BeeWoReqCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeWoReqListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findWoReqList(BeeWoReqCommonDTO beeWoReqCommonDTO, Map map) throws Exception;
    public List findWoReqCount(BeeWoReqCommonDTO beeWoReqCommonDTO, Map map) throws Exception;
    public int deleteWoReq(Map map) throws Exception;
    public int insertWoReq(Map map) throws Exception;
    public int updateWoReq(Map map) throws Exception;
    public int updateWoReqStatus(Map map) throws Exception;
    
    public List findWoResList(BeeWoReqCommonDTO beeWoReqCommonDTO, Map map) throws Exception;
    public List findWoResCount(BeeWoReqCommonDTO beeWoReqCommonDTO, Map map) throws Exception;
    public int updateWoRes(Map map) throws Exception;
    public int deleteWoRes(Map map) throws Exception;

    public List findWoReqResList(Map map) throws Exception;
    public int insertWoReqRes(Map map) throws Exception;
    public int updateWoReqRes(Map map) throws Exception;
    public int deleteWoReqRes(Map map) throws Exception;

    public List findPhotoList(Map map) throws Exception;
    public String findDoc(Map map) throws Exception;
}