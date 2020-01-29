package dream.rcm.ffail.service.spring;

import dream.rcm.ffail.dao.RcmFfailItemDetailDAO;
import dream.rcm.ffail.dto.RcmFfailItemDetailDTO;
import dream.rcm.ffail.dto.RcmFfailItemListDTO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;
import dream.rcm.ffail.service.RcmFfailItemDetailService;

/**
 * 답변 - 수신자
 * @author kim2107
 * @version $Id: RcmFfailItemDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmFfailItemDetailServiceTarget"
 * @spring.txbn id="rcmFfailItemDetailService"
 * @spring.property name="rcmFfailItemDetailDAO" ref="rcmFfailItemDetailDAO"
 */
public class RcmFfailItemDetailServiceImpl implements RcmFfailItemDetailService
{
    private RcmFfailItemDetailDAO rcmFfailItemDetailDAO = null;
    
    public RcmFfailItemDetailDAO getRcmFfailItemDetailDAO() {
		return rcmFfailItemDetailDAO;
	}

	public void setRcmFfailItemDetailDAO(RcmFfailItemDetailDAO rcmFfailItemDetailDAO) {
		this.rcmFfailItemDetailDAO = rcmFfailItemDetailDAO;
	}

	public RcmFfailItemDetailDTO findDetail(RcmFfailItemListDTO rcmFfailItemListDTO, RcmFfailCommonDTO rcmFfailCommonDTO)throws Exception
    {
        return rcmFfailItemDetailDAO.findDetail(rcmFfailItemListDTO, rcmFfailCommonDTO);
    }
    
	public int updateDetail(RcmFfailItemDetailDTO rcmFfailItemDetailDTO, RcmFfailCommonDTO rcmFfailCommonDTO) throws Exception
    {        
        return rcmFfailItemDetailDAO.updateDetail(rcmFfailItemDetailDTO, rcmFfailCommonDTO);
    }
	public int insertDetail(RcmFfailItemDetailDTO rcmFfailItemDetailDTO, RcmFfailCommonDTO rcmFfailCommonDTO) throws Exception
    {        
        return rcmFfailItemDetailDAO.insertDetail( rcmFfailItemDetailDTO, rcmFfailCommonDTO);
    }
}
