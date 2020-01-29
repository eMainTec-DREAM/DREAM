package dream.consult.program.guide.service.spring;

import common.bean.User;
import dream.consult.program.guide.dao.ConsultPgmGuideDetailDAO;
import dream.consult.program.guide.dto.ConsultPgmGuideCommonDTO;
import dream.consult.program.guide.dto.ConsultPgmGuideDetailDTO;
import dream.consult.program.guide.service.ConsultPgmGuideDetailService;

/**
 * Guide Page - Detail Service implements
 * @author kim21017
 * @version $Id: ConsultPgmGuideDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="consultPgmGuideDetailServiceTarget"
 * @spring.txbn id="consultPgmGuideDetailService"
 * @spring.property name="consultPgmGuideDetailDAO" ref="consultPgmGuideDetailDAO"
 */
public class ConsultPgmGuideDetailServiceImpl implements ConsultPgmGuideDetailService
{
    private ConsultPgmGuideDetailDAO consultPgmGuideDetailDAO = null;
    
    public ConsultPgmGuideDetailDTO findPgmGuideDetail(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO, User user) throws Exception
    {
    	return consultPgmGuideDetailDAO.findPgmGuideDetail(consultPgmGuideCommonDTO, user);
    }
    
    public int insertPgmGuideDetail(ConsultPgmGuideDetailDTO consultPgmGuideDetailDTO, User user) throws Exception
    {
    	return consultPgmGuideDetailDAO.insertPgmGuideDetail(consultPgmGuideDetailDTO, user);
    }
    
    public int updatePgmGuideDetail(ConsultPgmGuideDetailDTO consultPgmGuideDetailDTO, User user) throws Exception
    {
    	 return consultPgmGuideDetailDAO.updatePgmGuideDetail(consultPgmGuideDetailDTO, user);
    }

	public ConsultPgmGuideDetailDAO getConsultPgmGuideDetailDAO() {
		return consultPgmGuideDetailDAO;
	}

	public void setConsultPgmGuideDetailDAO(ConsultPgmGuideDetailDAO consultPgmGuideDetailDAO) {
		this.consultPgmGuideDetailDAO = consultPgmGuideDetailDAO;
	}
}
