package dream.ass.base.service.spring;

import common.bean.User;
import dream.ass.base.dao.AssBaseDetailDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseDetailDTO;
import dream.ass.base.service.AssBaseDetailService;

/**
 * 설비등급 평가기준 - Detail Service implements
 * @author kim21017
 * @version $Id: AssBaseDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="assBaseDetailServiceTarget"
 * @spring.txbn id="assBaseDetailService"
 * @spring.property name="assBaseDetailDAO" ref="assBaseDetailDAO"
 */
public class AssBaseDetailServiceImpl implements AssBaseDetailService
{
    private AssBaseDetailDAO assBaseDetailDAO = null;
    
    public AssBaseDetailDTO findDetail(AssBaseCommonDTO assBaseCommonDTO, User user) throws Exception
    {
    	return assBaseDetailDAO.findDetail(assBaseCommonDTO, user);
    }
    
    public int insertDetail(AssBaseDetailDTO assBaseDetailDTO, User user) throws Exception
    {
    	return assBaseDetailDAO.insertDetail(assBaseDetailDTO, user);
    }
    
    public int updateDetail(AssBaseDetailDTO assBaseDetailDTO, User user) throws Exception
    {
    	 return assBaseDetailDAO.updateDetail(assBaseDetailDTO, user);
    }

	public AssBaseDetailDAO getAssBaseDetailDAO() {
		return assBaseDetailDAO;
	}

	public void setAssBaseDetailDAO(AssBaseDetailDAO assBaseDetailDAO) {
		this.assBaseDetailDAO = assBaseDetailDAO;
	}
}
