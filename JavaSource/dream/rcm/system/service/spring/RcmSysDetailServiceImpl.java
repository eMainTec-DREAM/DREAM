package dream.rcm.system.service.spring;

import common.bean.User;
import dream.rcm.system.dao.RcmSysDetailDAO;
import dream.rcm.system.dto.RcmSysDetailDTO;
import dream.rcm.system.service.RcmSysDetailService;

/**
 * System분석 - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="rcmSysDetailServiceTarget"
 * @spring.txbn id="rcmSysDetailService"
 * @spring.property name="rcmSysDetailDAO" ref="rcmSysDetailDAO"
 */
public class RcmSysDetailServiceImpl implements RcmSysDetailService
{
    private RcmSysDetailDAO rcmSysDetailDAO = null;
    
    public RcmSysDetailDAO getRcmSysDetailDAO() 
    {
		return rcmSysDetailDAO;
	}

	public void setRcmSysDetailDAO(RcmSysDetailDAO rcmSysDetailDAO) 
	{
		this.rcmSysDetailDAO = rcmSysDetailDAO;
	}

	public RcmSysDetailDTO findDetail(User user, String id)throws Exception
    {
        return rcmSysDetailDAO.findDetail(user, id);
    }
    
	public int insertDetail(User user, RcmSysDetailDTO rcmSysDetailDTO) throws Exception
    {        
        return rcmSysDetailDAO.insertDetail(user, rcmSysDetailDTO);
    }
	
	public int updateDetail(User user, RcmSysDetailDTO rcmSysDetailDTO) throws Exception
    {        
        return rcmSysDetailDAO.updateDetail(user, rcmSysDetailDTO);
    }
}
