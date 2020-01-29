package dream.rcm.system.service.spring;

import dream.rcm.system.dao.RcmSysEmpDetailDAO;
import dream.rcm.system.dto.RcmSysEmpDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.service.RcmSysEmpDetailService;

/**
 * ÀÚºÐ¼®
 * @author kim21017
 * @version $Id: RcmSysEmpDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmSysEmpDetailServiceTarget"
 * @spring.txbn id="rcmSysEmpDetailService"
 * @spring.property name="rcmSysEmpDetailDAO" ref="rcmSysEmpDetailDAO"
 */
public class RcmSysEmpDetailServiceImpl implements RcmSysEmpDetailService
{
    private RcmSysEmpDetailDAO rcmSysEmpDetailDAO = null;
    
    public RcmSysEmpDetailDAO getRcmSysEmpDetailDAO() {
		return rcmSysEmpDetailDAO;
	}

	public void setRcmSysEmpDetailDAO(RcmSysEmpDetailDAO rcmSysEmpDetailDAO) {
		this.rcmSysEmpDetailDAO = rcmSysEmpDetailDAO;
	}

	public RcmSysEmpDetailDTO findDetail(String rcmListId, String rcmEmpId, String compNo)throws Exception
    {
        return rcmSysEmpDetailDAO.findDetail(rcmListId, rcmEmpId, compNo);
    }
    
	public int updateDetail(RcmSysEmpDetailDTO rcmSysEmpDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO) throws Exception
    {        
        return rcmSysEmpDetailDAO.updateDetail(rcmSysEmpDetailDTO, maPmMstrCommonDTO);
    }
	public int insertDetail(RcmSysEmpDetailDTO rcmSysEmpDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO) throws Exception
    {        
        return rcmSysEmpDetailDAO.insertDetail( rcmSysEmpDetailDTO, maPmMstrCommonDTO);
    }
}
