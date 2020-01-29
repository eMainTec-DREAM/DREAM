package dream.edu.list.service.spring;

import java.util.List;

import common.bean.User;

import dream.edu.list.dao.EduEmpListDAO;
import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduEmpListDTO;
import dream.edu.list.service.EduEmpListService;

/**
 * 작업결과 작업자 목록
 * @author kim21017
 * @version $Id: EduEmpListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="eduEmpListServiceTarget"
 * @spring.txbn id="eduEmpListService"
 * @spring.property name="eduEmpListDAO" ref="eduEmpListDAO"
 */
public class EduEmpListServiceImpl implements EduEmpListService
{
    private EduEmpListDAO eduEmpListDAO = null;

	

	public EduEmpListDAO getEduEmpListDAO() {
		return eduEmpListDAO;
	}

	public void setEduEmpListDAO(EduEmpListDAO eduEmpListDAO) {
		this.eduEmpListDAO = eduEmpListDAO;
	}

	public List findEmpList(EduCommonDTO eduCommonDTO, EduEmpListDTO eduEmpListDTO, User loginUser)
    {
        return eduEmpListDAO.findEmpList(eduCommonDTO, eduEmpListDTO, loginUser);
    }

	public int deleteEmpList(String[] deleteRows, User loginUser) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + eduEmpListDAO.deleteEmpList(id, loginUser);
            }

        return result;
    }

	@Override
	public String findTotalCount(EduCommonDTO eduCommonDTO, EduEmpListDTO eduEmpListDTO, User user) 
	{
		return eduEmpListDAO.findTotalCount(eduCommonDTO, eduEmpListDTO, user);
	}
}

