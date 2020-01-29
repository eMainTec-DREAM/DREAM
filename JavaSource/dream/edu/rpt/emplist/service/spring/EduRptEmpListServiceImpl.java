package dream.edu.rpt.emplist.service.spring;

import java.util.List;

import common.bean.User;

import dream.edu.rpt.emplist.dao.EduRptEmpListDAO;
import dream.edu.rpt.emplist.dto.EduRptEmpCommonDTO;
import dream.edu.rpt.emplist.service.EduRptEmpListService;

/**
 * 자격증분류 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 *
 * @spring.bean id="eduRptEmpListServiceTarget"
 * @spring.txbn id="eduRptEmpListService"
 * @spring.property name="eduRptEmpListDAO" ref="eduRptEmpListDAO"
 */
public class EduRptEmpListServiceImpl implements EduRptEmpListService
{
    private EduRptEmpListDAO eduRptEmpListDAO = null;

    public EduRptEmpListDAO getEduRptEmpListDAO() {
		return eduRptEmpListDAO;
	}

	public void setEduRptEmpListDAO(EduRptEmpListDAO eduRptEmpListDAO) {
		this.eduRptEmpListDAO = eduRptEmpListDAO;
	}


	public List findList(EduRptEmpCommonDTO eduRptEmpCommonDTO    ,User user)
    {
        return eduRptEmpListDAO.findList(eduRptEmpCommonDTO,user);
    }

	@Override
	public String findTotalCount(EduRptEmpCommonDTO eduRptEmpCommonDTO, User user)
	{
		return eduRptEmpListDAO.findTotalCount(eduRptEmpCommonDTO, user);
	}



}