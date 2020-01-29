package dream.part.iss.emg.req.service.spring;

import common.bean.User;

import dream.part.iss.emg.req.dao.PartIssEmgReqPartsDetailDAO;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqPartsDetailDTO;
import dream.part.iss.emg.req.service.PartIssEmgReqPartsDetailService;

/**
 * 긴급출고 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="partIssEmgReqPartsDetailServiceTarget"
 * @spring.txbn id="partIssEmgReqPartsDetailService"
 * @spring.property name="partIssEmgReqPartsDetailDAO" ref="partIssEmgReqPartsDetailDAO"
 */
public class PartIssEmgReqPartsDetailServiceImpl implements PartIssEmgReqPartsDetailService
{
    private PartIssEmgReqPartsDetailDAO partIssEmgReqPartsDetailDAO = null;
    
	public PartIssEmgReqPartsDetailDAO getPartIssEmgReqPartsDetailDAO() {
		return partIssEmgReqPartsDetailDAO;
	}

	public void setPartIssEmgReqPartsDetailDAO(
			PartIssEmgReqPartsDetailDAO partIssEmgReqPartsDetailDAO) {
		this.partIssEmgReqPartsDetailDAO = partIssEmgReqPartsDetailDAO;
	}

	public PartIssEmgReqPartsDetailDTO findDetail(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user)throws Exception
    {
		PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO = partIssEmgReqPartsDetailDAO.findDetail(partIssEmgReqCommonDTO, user);
        
        return partIssEmgReqPartsDetailDTO;
    }
	
	public int insertDetail(PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO, User user) throws Exception
    {   
	    int result = 0;
	    //TAPTISSLIST 입력
	    result = partIssEmgReqPartsDetailDAO.insertPtIssEmgList(partIssEmgReqPartsDetailDTO, user);
	    
        return result;
    }
	
	public int updateDetail(PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO, User user) throws Exception
    {   
	    int result = 0;
	    //TAPTISSLIST 수정
	    result = partIssEmgReqPartsDetailDAO.updatePtIssEmgList(partIssEmgReqPartsDetailDTO, user);
	    
	    return result;
    }

}
