/**
 * 
 */
package dream.part.iss.emg.req.service;

import java.util.List;

import common.bean.User;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
/**
 * 부품출고 - List Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface PartIssEmgReqListService {
	/**
	 * FIND PROGRAM GUIDE LIST
	 * @param partIssEmgReqCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findIssReqList(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user) throws Exception;
	/**
	 * DELETE PROGRAM GUIDE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteIssReqList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND PROGRAM GUIDE LIST COUNT
	 * @param partIssEmgReqCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user) throws Exception;
}
