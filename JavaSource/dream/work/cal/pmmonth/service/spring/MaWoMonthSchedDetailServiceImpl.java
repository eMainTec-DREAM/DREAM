package dream.work.cal.pmmonth.service.spring;

import common.bean.User;
import dream.work.cal.pmmonth.dao.MaWoMonthSchedDetailDAO;
import dream.work.cal.pmmonth.dto.MaWoMonthSchedDetailDTO;
import dream.work.cal.pmmonth.service.MaWoMonthSchedDetailService;

/**
 * 월간예방일정 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaWoMonthSchedDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoMonthSchedDetailServiceTarget"
 * @spring.txbn id="maWoMonthSchedDetailService"
 * @spring.property name="maWoMonthSchedDetailDAO" ref="maWoMonthSchedDetailDAO"
 */
public class MaWoMonthSchedDetailServiceImpl implements MaWoMonthSchedDetailService
{
    private MaWoMonthSchedDetailDAO maWoMonthSchedDetailDAO = null;
    
    public MaWoMonthSchedDetailDAO getMaWoMonthSchedDetailDAO() {
		return maWoMonthSchedDetailDAO;
	}

	public void setMaWoMonthSchedDetailDAO(MaWoMonthSchedDetailDAO maWoMonthSchedDetailDAO) {
		this.maWoMonthSchedDetailDAO = maWoMonthSchedDetailDAO;
	}

	public MaWoMonthSchedDetailDTO findDetail(String pmSchedId, User user)throws Exception
    {
        return maWoMonthSchedDetailDAO.findDetail(pmSchedId, user);
    }
	
	public int updateDetail(MaWoMonthSchedDetailDTO maWoMonthSchedDetailDTO) throws Exception
    {        
        return maWoMonthSchedDetailDAO.updateDetail(maWoMonthSchedDetailDTO);
    }
}
