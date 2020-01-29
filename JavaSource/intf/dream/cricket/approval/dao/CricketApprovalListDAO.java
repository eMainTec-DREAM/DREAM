package intf.dream.cricket.approval.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface CricketApprovalListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findApprovalList(Map map) throws Exception;
    
    public List findApprovalUserList(Map map) throws Exception;
    public List findApprovalUserNextNo(Map map) throws Exception;
    
    public int insertApproval(Map map) throws Exception;
    public int updateApproval(Map map) throws Exception;
    public int insertApprovalUser(Map map) throws Exception;
    public int updateApprovalUser(Map map) throws Exception;
    public int deleteApprovalUser(Map map) throws Exception;
    
    public List findApprovalLineList(Map map) throws Exception;
    public List findApprovalLineUserList(Map map) throws Exception;
    public int addApprovalLine(Map map) throws Exception;

    public int addReqUser(Map map) throws Exception;
    public int updateReqStatus(Map map) throws Exception;
    public int updateNextUsrStatus(Map map) throws Exception;
    public int initApprUser(Map map) throws Exception;
    
    public List findApprovalReadyList(Map map) throws Exception;
}