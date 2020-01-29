package dream.work.cal.pmyear.service.spring;

import common.bean.User;
import dream.work.cal.pmyear.dao.MaWoYearSchedDetailDAO;
import dream.work.cal.pmyear.dto.MaWoYearSchedDetailDTO;
import dream.work.cal.pmyear.service.MaWoYearSchedDetailService;

/**
 * 연간작업일정 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaWoYearSchedDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoYearSchedDetailServiceTarget"
 * @spring.txbn id="maWoYearSchedDetailService"
 * @spring.property name="maWoYearSchedDetailDAO" ref="maWoYearSchedDetailDAO"
 */
public class MaWoYearSchedDetailServiceImpl implements MaWoYearSchedDetailService
{
    private MaWoYearSchedDetailDAO maWoYearSchedDetailDAO = null;
    
    public MaWoYearSchedDetailDAO getMaWoYearSchedDetailDAO() {
		return maWoYearSchedDetailDAO;
	}

	public void setMaWoYearSchedDetailDAO(MaWoYearSchedDetailDAO maWoYearSchedDetailDAO) {
		this.maWoYearSchedDetailDAO = maWoYearSchedDetailDAO;
	}

	public MaWoYearSchedDetailDTO findDetail(String pmSchedId, User user)throws Exception
    {
        return maWoYearSchedDetailDAO.findDetail(pmSchedId, user);
    }
	
	public int updateDetail(MaWoYearSchedDetailDTO maWoYearSchedDetailDTO) throws Exception
    {        
        return maWoYearSchedDetailDAO.updateDetail(maWoYearSchedDetailDTO);
    }
}
