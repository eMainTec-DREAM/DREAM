package intf.dream.android.online.maptpurreq.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnMaPtPurReqListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findMaPtPurReqList(Map map) throws Exception;
    public int deleteMaPtPurReq(Map map) throws Exception;
    //public int insertMaPtPurReqHdr(Map map) throws Exception;
    public int insertMaPtPurReq(Map map) throws Exception;
    public int updateMaPtPurReq(Map map) throws Exception;
    public int confirmMaPtPurReq(Map map) throws Exception;
}