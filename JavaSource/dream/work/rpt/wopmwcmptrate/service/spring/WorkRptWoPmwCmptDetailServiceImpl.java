package dream.work.rpt.wopmwcmptrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.wopmwcmptrate.dao.WorkRptWoPmwCmptDetailDAO;
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptDetailDTO;
import dream.work.rpt.wopmwcmptrate.service.WorkRptWoPmwCmptDetailService;

/**
 * 예방작업 계획대비 실행 비율 상세 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptWoPmwCmptDetailServiceTarget"
 * @spring.txbn id="workRptWoPmwCmptDetailService"
 * @spring.property name="workRptWoPmwCmptDetailDAO" ref="workRptWoPmwCmptDetailDAO"
 */
public class WorkRptWoPmwCmptDetailServiceImpl implements WorkRptWoPmwCmptDetailService
{
    private WorkRptWoPmwCmptDetailDAO workRptWoPmwCmptDetailDAO = null;
    
    public WorkRptWoPmwCmptDetailDAO getWorkRptWoPmwCmptDetailDAO()
    {
        return workRptWoPmwCmptDetailDAO;
    }
    
    public void setWorkRptWoPmwCmptDetailDAO(
            WorkRptWoPmwCmptDetailDAO workRptWoPmwCmptDetailDAO)
    {
        this.workRptWoPmwCmptDetailDAO = workRptWoPmwCmptDetailDAO;
    }
    
    public List findDetail(WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO, User loginUser) throws Exception
    {
        return workRptWoPmwCmptDetailDAO.findDetail(workRptWoPmwCmptDetailDTO, loginUser);
        
    }
	
    public String findTotalCount(WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO, User loginUser)  throws Exception
    {
        return workRptWoPmwCmptDetailDAO.findTotalCount(workRptWoPmwCmptDetailDTO, loginUser);
    }
}

