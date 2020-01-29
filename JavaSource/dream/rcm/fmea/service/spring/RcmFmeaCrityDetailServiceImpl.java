package dream.rcm.fmea.service.spring;

import dream.rcm.fmea.dao.RcmFmeaCrityDetailDAO;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityDetailDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityListDTO;
import dream.rcm.fmea.service.RcmFmeaCrityDetailService;

/**
 * ¼ö½ÅÀÚ
 * @author kim2107
 * @version $Id: RcmFmeaCrityDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmFmeaCrityDetailServiceTarget"
 * @spring.txbn id="rcmFmeaCrityDetailService"
 * @spring.property name="rcmFmeaCrityDetailDAO" ref="rcmFmeaCrityDetailDAO"
 */
public class RcmFmeaCrityDetailServiceImpl implements RcmFmeaCrityDetailService
{
    private RcmFmeaCrityDetailDAO rcmFmeaCrityDetailDAO = null;
    
    public RcmFmeaCrityDetailDAO getRcmFmeaCrityDetailDAO() {
		return rcmFmeaCrityDetailDAO;
	}

	public void setRcmFmeaCrityDetailDAO(RcmFmeaCrityDetailDAO rcmFmeaCrityDetailDAO) {
		this.rcmFmeaCrityDetailDAO = rcmFmeaCrityDetailDAO;
	}

	public RcmFmeaCrityDetailDTO findDetail(RcmFmeaCrityListDTO rcmFmeaCrityListDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO)throws Exception
    {
        return rcmFmeaCrityDetailDAO.findDetail(rcmFmeaCrityListDTO, rcmFmeaCommonDTO);
    }
    
	public int updateDetail(RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO) throws Exception
    {        
        return rcmFmeaCrityDetailDAO.updateDetail(rcmFmeaCrityDetailDTO, rcmFmeaCommonDTO);
    }
	public int insertDetail(RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO) throws Exception
    {        
        return rcmFmeaCrityDetailDAO.insertDetail( rcmFmeaCrityDetailDTO, rcmFmeaCommonDTO);
    }

	@Override
	public int findVal(RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO) {
		// TODO Auto-generated method stub
		return rcmFmeaCrityDetailDAO.findVal( rcmFmeaCrityDetailDTO, rcmFmeaCommonDTO);
	}

}
