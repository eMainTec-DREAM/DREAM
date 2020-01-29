package dream.ass.base.service.spring;

import common.bean.User;
import dream.ass.base.dao.AssBasePointValDetailDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.dto.AssBasePointValDetailDTO;
import dream.ass.base.dto.AssBasePointValListDTO;
import dream.ass.base.service.AssBasePointValDetailService;

/**
 * 평가기준 - Detail Service implements
 * @author kim21017
 * @version $Id: AssBasePointValDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="assBasePointValDetailServiceTarget"
 * @spring.txbn id="assBasePointValDetailService"
 * @spring.property name="assBasePointValDetailDAO" ref="assBasePointValDetailDAO"
 */
public class AssBasePointValDetailServiceImpl implements AssBasePointValDetailService
{
    private AssBasePointValDetailDAO assBasePointValDetailDAO = null;
    
    public AssBasePointValDetailDTO findDetail(AssBaseCommonDTO assBaseCommonDTO, AssBasePointListDTO assBasePointListDTO, AssBasePointValListDTO assBasePointValListDTO, User user) throws Exception
    {
    	return assBasePointValDetailDAO.findDetail(assBaseCommonDTO,assBasePointListDTO, assBasePointValListDTO, user);
    }
    
    public int insertDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO, AssBasePointValListDTO assBasePointValListDTO,AssBasePointValDetailDTO assBasePointValDetailDTO, User user) throws Exception
    {
     	return assBasePointValDetailDAO.insertDetail(assBaseCommonDTO,assBasePointListDTO, assBasePointValListDTO, assBasePointValDetailDTO, user);
    }
    
    public int updateDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO, AssBasePointValListDTO assBasePointValListDTO,AssBasePointValDetailDTO assBasePointValDetailDTO, User user) throws Exception
    {
     	return assBasePointValDetailDAO.updateDetail(assBaseCommonDTO,assBasePointListDTO, assBasePointValListDTO, assBasePointValDetailDTO, user);
    }

	public AssBasePointValDetailDAO getAssBasePointValDetailDAO() {
		return assBasePointValDetailDAO;
	}

	public void setAssBasePointValDetailDAO(AssBasePointValDetailDAO assBasePointValDetailDAO) {
		this.assBasePointValDetailDAO = assBasePointValDetailDAO;
	}
    

}
