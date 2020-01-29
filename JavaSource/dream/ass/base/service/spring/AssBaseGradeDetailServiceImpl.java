package dream.ass.base.service.spring;

import common.bean.User;
import dream.ass.base.dao.AssBaseGradeDetailDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseGradeDetailDTO;
import dream.ass.base.dto.AssBaseGradeListDTO;
import dream.ass.base.service.AssBaseGradeDetailService;

/**
 * 등급기준 - Detail Service implements
 * @author kim21017
 * @version $Id: AssBaseGradeDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="assBaseGradeDetailServiceTarget"
 * @spring.txbn id="assBaseGradeDetailService"
 * @spring.property name="assBaseGradeDetailDAO" ref="assBaseGradeDetailDAO"
 */
public class AssBaseGradeDetailServiceImpl implements AssBaseGradeDetailService
{
    private AssBaseGradeDetailDAO assBaseGradeDetailDAO = null;
    
    public AssBaseGradeDetailDTO findDetail(AssBaseCommonDTO assBaseCommonDTO, AssBaseGradeListDTO assBaseGradeListDTO, User user) throws Exception
    {
    	return assBaseGradeDetailDAO.findDetail(assBaseCommonDTO,assBaseGradeListDTO, user);
    }
    
    public int insertDetail(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeDetailDTO assBaseGradeDetailDTO, User user) throws Exception
    {
     	return assBaseGradeDetailDAO.insertDetail(assBaseCommonDTO,assBaseGradeDetailDTO, user);
    }
    
    public int updateDetail(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeDetailDTO assBaseGradeDetailDTO, User user) throws Exception
    {
     	return assBaseGradeDetailDAO.updateDetail(assBaseCommonDTO,assBaseGradeDetailDTO, user);
    }
    public String validGrade(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeDetailDTO assBaseGradeDetailDTO, User user) throws Exception
    {
     	return assBaseGradeDetailDAO.validGrade(assBaseCommonDTO,assBaseGradeDetailDTO, user);
    }
    public String validFromTo(AssBaseCommonDTO assBaseCommonDTO,AssBaseGradeDetailDTO assBaseGradeDetailDTO, User user) throws Exception
    {
     	return assBaseGradeDetailDAO.validFromTo(assBaseCommonDTO,assBaseGradeDetailDTO, user);
    }

	public AssBaseGradeDetailDAO getAssBaseGradeDetailDAO() {
		return assBaseGradeDetailDAO;
	}

	public void setAssBaseGradeDetailDAO(AssBaseGradeDetailDAO assBaseGradeDetailDAO) {
		this.assBaseGradeDetailDAO = assBaseGradeDetailDAO;
	}
    

}
