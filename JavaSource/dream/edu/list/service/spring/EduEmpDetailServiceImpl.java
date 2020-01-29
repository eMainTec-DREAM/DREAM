package dream.edu.list.service.spring;

import common.bean.User;

import dream.edu.list.dao.EduEmpDetailDAO;
import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduEmpDetailDTO;
import dream.edu.list.service.EduEmpDetailService;

/**
 * 작업결과 작업자
 * @author kim2107
 * @version $Id: EduEmpDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 *
 * @spring.bean id="eduEmpDetailServiceTarget"
 * @spring.txbn id="eduEmpDetailService"
 * @spring.property name="eduEmpDetailDAO" ref="eduEmpDetailDAO"
 */
public class EduEmpDetailServiceImpl implements EduEmpDetailService
{
    
	private EduEmpDetailDAO eduEmpDetailDAO = null;


	public EduEmpDetailDAO getEduEmpDetailDAO() {
		return eduEmpDetailDAO;
	}

	public void setEduEmpDetailDAO(EduEmpDetailDAO eduEmpDetailDAO) {
		this.eduEmpDetailDAO = eduEmpDetailDAO;
	}

	public EduEmpDetailDTO findDetail(String eduListId, User loginUser)throws Exception
    {
        return eduEmpDetailDAO.findDetail(eduListId, loginUser);
    }

	public int updateDetail(EduEmpDetailDTO eduEmpDetailDTO, EduCommonDTO eduCommonDTO, User loginUser) throws Exception
    {
        return eduEmpDetailDAO.updateDetail(eduEmpDetailDTO, eduCommonDTO, loginUser);
    }
	
	public int insertDetail(EduEmpDetailDTO eduEmpDetailDTO, EduCommonDTO eduCommonDTO, User loginUser) throws Exception
    {
        return eduEmpDetailDAO.insertDetail( eduEmpDetailDTO, eduCommonDTO, loginUser);
    }
	
	public String validEmp(EduEmpDetailDTO eduEmpDetailDTO, EduCommonDTO eduCommonDTO, User loginUser){
		return eduEmpDetailDAO.validEmp(eduEmpDetailDTO, eduCommonDTO, loginUser);
	}
	
}
