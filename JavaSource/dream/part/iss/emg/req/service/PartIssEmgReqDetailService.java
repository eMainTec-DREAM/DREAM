package dream.part.iss.emg.req.service;

import common.bean.User;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;
/**
 * 부품출고 - Detail Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface PartIssEmgReqDetailService
{    
	/**
	 * FIND PROGRAM GUIDE DETAIL
	 * @param partIssEmgReqCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public PartIssEmgReqDetailDTO findIssReqDetail(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user) throws Exception;
	/**
	 * INSERT PROGRAM GUIDE
	 * @param partIssEmgReqDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertIssReqDetail(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception;
    /**
     * UPDATE PROGRAM GUIDE
     * @param partIssEmgReqDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateIssReqDetail(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception;
    public int updateStatus(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception;
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;
    public String[] issueParts(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception;
    public String[] issueCancelParts(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception;
}
