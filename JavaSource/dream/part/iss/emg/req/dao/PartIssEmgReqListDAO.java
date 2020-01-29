package dream.part.iss.emg.req.dao;

import java.util.List;

import common.bean.User;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;

/**
 * 부품출고 - List DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface PartIssEmgReqListDAO
{
	/**
	 * FIND LIST
	 * @param partIssEmgReqCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findIssReqList(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteIssReqList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param partIssEmgReqCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user) throws Exception;

    public int deleteIssReqPartsList(String id, User user) throws Exception;
    
}