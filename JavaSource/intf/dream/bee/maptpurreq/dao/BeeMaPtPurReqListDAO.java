package intf.dream.bee.maptpurreq.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.bee.maptpurreq.dto.BeeMaPtPurReqCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeMaPtPurReqListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findMaPtPurReqList(BeeMaPtPurReqCommonDTO beeMaPtPurReqCommonDTO, Map map) throws Exception;
    public List findMaPtPurReqCount(BeeMaPtPurReqCommonDTO beeMaPtPurReqCommonDTO, Map map) throws Exception;
    public int deleteMaPtPurReq(Map map) throws Exception;
    //public int insertMaPtPurReqHdr(Map map) throws Exception;
    public int insertMaPtPurReq(Map map) throws Exception;
    public int updateMaPtPurReq(Map map) throws Exception;
    public int confirmMaPtPurReq(Map map) throws Exception;
}