package dream.work.rpt.pmicmptrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmicmptrate.dao.WorkRptPmiCmptDetailDAO;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptDetailDTO;
import dream.work.rpt.pmicmptrate.service.WorkRptPmiCmptDetailService;

/**
 * 고장TOP(위치) 상세 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPmiCmptDetailServiceTarget"
 * @spring.txbn id="workRptPmiCmptDetailService"
 * @spring.property name="workRptPmiCmptDetailDAO" ref="workRptPmiCmptDetailDAO"
 */
public class WorkRptPmiCmptDetailServiceImpl implements WorkRptPmiCmptDetailService
{
    private WorkRptPmiCmptDetailDAO workRptPmiCmptDetailDAO = null;
    
    public WorkRptPmiCmptDetailDAO getWorkRptPmiCmptDetailDAO()
    {
        return workRptPmiCmptDetailDAO;
    }
    
    public void setWorkRptPmiCmptDetailDAO(
            WorkRptPmiCmptDetailDAO workRptPmiCmptDetailDAO)
    {
        this.workRptPmiCmptDetailDAO = workRptPmiCmptDetailDAO;
    }
    
    public List findDetail(WorkRptPmiCmptDetailDTO workRptPmiCmptDetailDTO, User loginUser) throws Exception
    {
        return workRptPmiCmptDetailDAO.findDetail(workRptPmiCmptDetailDTO, loginUser);
        
    }

	@Override
	public String findTotalCount(WorkRptPmiCmptDetailDTO workRptPmiCmptDetailDTO, User user) throws Exception
	{
		return workRptPmiCmptDetailDAO.findTotalCount(workRptPmiCmptDetailDTO, user);
	}
	
}

