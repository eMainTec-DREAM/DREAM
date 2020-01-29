package dream.work.cal.pmperiod.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.cal.pmperiod.dao.MaWoSchedDetailDAO;
import dream.work.cal.pmperiod.dto.MaWoSchedDetailDTO;
import dream.work.cal.pmperiod.service.MaWoSchedDetailService;
import dream.work.cal.pmyear.dao.MaWoYearSchedListDAO;

/**
 * 예방작업일정(기간) - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaWoSchedDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoSchedDetailServiceTarget"
 * @spring.txbn id="maWoSchedDetailService"
 * @spring.property name="maWoSchedDetailDAO" ref="maWoSchedDetailDAO"
 * @spring.property name="maWoYearSchedListDAO" ref="maWoYearSchedListDAO"
 */
public class MaWoSchedDetailServiceImpl implements MaWoSchedDetailService
{
    private MaWoSchedDetailDAO maWoSchedDetailDAO = null;
    
    private MaWoYearSchedListDAO maWoYearSchedListDAO = null;
    

    public MaWoYearSchedListDAO getMaWoYearSchedListDAO() {
		return maWoYearSchedListDAO;
	}

	public void setMaWoYearSchedListDAO(MaWoYearSchedListDAO maWoYearSchedListDAO) {
		this.maWoYearSchedListDAO = maWoYearSchedListDAO;
	}
	
    
    public MaWoSchedDetailDAO getMaWoSchedDetailDAO() {
		return maWoSchedDetailDAO;
	}

	public void setMaWoSchedDetailDAO(MaWoSchedDetailDAO maWoSchedDetailDAO) {
		this.maWoSchedDetailDAO = maWoSchedDetailDAO;
	}

	public MaWoSchedDetailDTO findDetail(String pmSchedId, User user)throws Exception
    {
        return maWoSchedDetailDAO.findDetail(pmSchedId, user);
    }
	
	public int updateDetail(MaWoSchedDetailDTO maWoSchedDetailDTO, User user) throws Exception
    {        
        
		String pSchedStatus = "";
		String scheDate     = maWoSchedDetailDTO.getSchedDate();
		String pmSchedId    = maWoSchedDetailDTO.getPmSchedId();
		String remark       = maWoSchedDetailDTO.getRemark();
		if(scheDate.indexOf("-") == -1)scheDate = CommonUtil.convertDate(scheDate);
        scheDate = scheDate.replaceAll("-", "");
        
        pSchedStatus =  maWoYearSchedListDAO.checkSched(pmSchedId, user);
        
    	if("S".equals(pSchedStatus)){
    		maWoYearSchedListDAO.updateScheduleDate(pmSchedId, scheDate, remark, user);
    		maWoYearSchedListDAO.updateResultSchedDetail(pmSchedId, scheDate, user);
    	}else if("P".equals(pSchedStatus)){
    		maWoYearSchedListDAO.updateScheduleDate(pmSchedId, scheDate, remark, user);
    	}
    	return 0;
        
    }
	public int inputDetail(MaWoSchedDetailDTO maWoSchedDetailDTO) throws Exception
    {        
        return maWoSchedDetailDAO.inputDetail(maWoSchedDetailDTO);
    }
}
