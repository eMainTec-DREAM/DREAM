package dream.rcm.funceq.service.spring;

import dream.rcm.funceq.dao.RcmFuncEqDetailDAO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.dto.RcmFuncEqDetailDTO;
import dream.rcm.funceq.service.RcmFuncEqDetailService;

/**
 * 질의 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: RcmFuncEqDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmFuncEqDetailServiceTarget"
 * @spring.txbn id="rcmFuncEqDetailService"
 * @spring.property name="rcmFuncEqDetailDAO" ref="rcmFuncEqDetailDAO"
 */
public class RcmFuncEqDetailServiceImpl implements RcmFuncEqDetailService
{
    private RcmFuncEqDetailDAO rcmFuncEqDetailDAO = null;
    
    public RcmFuncEqDetailDAO getRcmFuncEqDetailDAO() {
		return rcmFuncEqDetailDAO;
	}

	public void setRcmFuncEqDetailDAO(RcmFuncEqDetailDAO rcmFuncEqDetailDAO) {
		this.rcmFuncEqDetailDAO = rcmFuncEqDetailDAO;
	}

	public RcmFuncEqDetailDTO findDetail(RcmFuncEqCommonDTO rcmFuncEqCommonDTO)throws Exception
    {
        return rcmFuncEqDetailDAO.findDetail(rcmFuncEqCommonDTO);
    }
	
	public int updateDetail(RcmFuncEqDetailDTO rcmFuncEqDetailDTO) throws Exception
    {        
	    rcmFuncEqDetailDAO.updateDetail(rcmFuncEqDetailDTO);
		
        return rcmFuncEqDetailDAO.updateRcmFunc(rcmFuncEqDetailDTO);
    }
	public int insertDetail(RcmFuncEqDetailDTO rcmFuncEqDetailDTO) throws Exception
    {   
		rcmFuncEqDetailDTO.setFuncId(rcmFuncEqDetailDAO.getNextSequence("SQARCMFUNC_ID"));
		
//		rcmFuncEqDetailDTO.setRcmFfailId(rcmFuncEqDetailDAO.getNextSequence("SQARCMFFAIL_ID"));
		
		rcmFuncEqDetailDAO.insertRcmFunc(rcmFuncEqDetailDTO);
		
        return rcmFuncEqDetailDAO.insertDetail(rcmFuncEqDetailDTO);
    }
}
