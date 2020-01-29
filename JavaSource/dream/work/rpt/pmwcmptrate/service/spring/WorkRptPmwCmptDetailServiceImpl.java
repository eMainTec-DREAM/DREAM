package dream.work.rpt.pmwcmptrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmwcmptrate.dao.WorkRptPmwCmptDetailDAO;
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptDetailDTO;
import dream.work.rpt.pmwcmptrate.service.WorkRptPmwCmptDetailService;

/**
 * 주기정비 계획대비 실행 비율 상세 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPmwCmptDetailServiceTarget"
 * @spring.txbn id="workRptPmwCmptDetailService"
 * @spring.property name="workRptPmwCmptDetailDAO" ref="workRptPmwCmptDetailDAO"
 */
public class WorkRptPmwCmptDetailServiceImpl implements WorkRptPmwCmptDetailService
{
    private WorkRptPmwCmptDetailDAO workRptPmwCmptDetailDAO = null;
    
    public WorkRptPmwCmptDetailDAO getWorkRptPmwCmptDetailDAO()
    {
        return workRptPmwCmptDetailDAO;
    }
    
    public void setWorkRptPmwCmptDetailDAO(
            WorkRptPmwCmptDetailDAO workRptPmwCmptDetailDAO)
    {
        this.workRptPmwCmptDetailDAO = workRptPmwCmptDetailDAO;
    }
    
    public List findDetail(WorkRptPmwCmptDetailDTO workRptPmwCmptDetailDTO, User loginUser) throws Exception
    {
        return workRptPmwCmptDetailDAO.findDetail(workRptPmwCmptDetailDTO, loginUser);
        
    }
    public String findTotalCount(WorkRptPmwCmptDetailDTO workRptPmwCmptDetailDTO, User loginUser)  throws Exception
    {
        return workRptPmwCmptDetailDAO.findTotalCount(workRptPmwCmptDetailDTO, loginUser);
    }
	
}

