package dream.consult.program.guide.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.guide.dao.ConsultPgmGuideListDAO;
import dream.consult.program.guide.dto.ConsultPgmGuideCommonDTO;
import dream.consult.program.guide.service.ConsultPgmGuideListService;

/**
 * Guide Page - List Service implements
 * @author kim21017
 * @version $Id: ConsultPgmGuideListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="consultPgmGuideListServiceTarget"
 * @spring.txbn id="consultPgmGuideListService"
 * @spring.property name="consultPgmGuideListDAO" ref="consultPgmGuideListDAO"
 */
public class ConsultPgmGuideListServiceImpl implements ConsultPgmGuideListService
{
	private ConsultPgmGuideListDAO consultPgmGuideListDAO = null;

	public List findPgmGuideList(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO, User user) throws Exception
    {      
        return consultPgmGuideListDAO.findPgmGuideList(consultPgmGuideCommonDTO,user);
    }

	public int deletePgmGuideList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultPgmGuideListDAO.deletePgmGuideList(id, user);
            }
        return result;
    }

	public ConsultPgmGuideListDAO getConsultPgmGuideListDAO() {
		return consultPgmGuideListDAO;
	}

	public void setConsultPgmGuideListDAO(ConsultPgmGuideListDAO consultPgmGuideListDAO) {
		this.consultPgmGuideListDAO = consultPgmGuideListDAO;
	}
	public String findTotalCount(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO,User user) throws Exception
    {
        return consultPgmGuideListDAO.findTotalCount(consultPgmGuideCommonDTO, user);
    }
}

