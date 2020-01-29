package dream.ass.base.service.spring;

import common.bean.User;
import dream.ass.base.dao.AssBasePointDetailDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointDetailDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.service.AssBasePointDetailService;

/**
 * 평가항목 - Detail Service implements
 * @author kim21017
 * @version $Id: AssBasePointDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="assBasePointDetailServiceTarget"
 * @spring.txbn id="assBasePointDetailService"
 * @spring.property name="assBasePointDetailDAO" ref="assBasePointDetailDAO"
 */
public class AssBasePointDetailServiceImpl implements AssBasePointDetailService
{
    private AssBasePointDetailDAO assBasePointDetailDAO = null;
    
    public AssBasePointDetailDTO findDetail(AssBaseCommonDTO assBaseCommonDTO, AssBasePointListDTO assBasePointListDTO, User user) throws Exception
    {
    	return assBasePointDetailDAO.findDetail(assBaseCommonDTO,assBasePointListDTO, user);
    }
    
    public int insertDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointDetailDTO assBasePointDetailDTO, User user) throws Exception
    {
     	return assBasePointDetailDAO.insertDetail(assBaseCommonDTO,assBasePointDetailDTO, user);
    }
    
    public int updateDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointDetailDTO assBasePointDetailDTO, User user) throws Exception
    {
     	return assBasePointDetailDAO.updateDetail(assBaseCommonDTO,assBasePointDetailDTO, user);
    }

	public AssBasePointDetailDAO getAssBasePointDetailDAO() {
		return assBasePointDetailDAO;
	}

	public void setAssBasePointDetailDAO(AssBasePointDetailDAO assBasePointDetailDAO) {
		this.assBasePointDetailDAO = assBasePointDetailDAO;
	}
    

}
