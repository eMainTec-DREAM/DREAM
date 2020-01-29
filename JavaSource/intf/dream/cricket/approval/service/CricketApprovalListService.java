package intf.dream.cricket.approval.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface CricketApprovalListService
{     
    public List findApprovalList(Map map) throws Exception;
    public int reqApproval(List list) throws Exception;
    
    public List findApprovalUserList(Map map) throws Exception;
    public List findApprovalUserNextNo(Map map) throws Exception;
    
    public int insertApproval(List list) throws Exception;
    public int updateApproval(List list) throws Exception;
    public int insertApprovalUser(List list) throws Exception;
    public int updateApprovalUser(List list) throws Exception;
    public int deleteApprovalUser(List list) throws Exception;
    
    public List findApprovalLineList(Map map) throws Exception;
    public List findApprovalLineUserList(Map map) throws Exception;
    public int addApprovalLine(List list) throws Exception;
    
    public List findApprovalReadyList(Map map) throws Exception;

    public int actionApproval(List list) throws Exception;
    public int referenceApproval(List list) throws Exception;
    public int rejectApproval(List list) throws Exception;
}
