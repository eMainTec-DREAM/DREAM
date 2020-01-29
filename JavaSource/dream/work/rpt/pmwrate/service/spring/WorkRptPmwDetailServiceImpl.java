package dream.work.rpt.pmwrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmwrate.dao.WorkRptPmwDetailDAO;
import dream.work.rpt.pmwrate.dto.WorkRptPmwDetailDTO;
import dream.work.rpt.pmwrate.service.WorkRptPmwDetailService;

/**
 * 주기정비 실행 비율 상세 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPmwDetailServiceTarget"
 * @spring.txbn id="workRptPmwDetailService"
 * @spring.property name="workRptPmwDetailDAO" ref="workRptPmwDetailDAO"
 */
public class WorkRptPmwDetailServiceImpl implements WorkRptPmwDetailService
{
    private WorkRptPmwDetailDAO workRptPmwDetailDAO = null;
    
    public WorkRptPmwDetailDAO getWorkRptPmwDetailDAO()
    {
        return workRptPmwDetailDAO;
    }
    
    public void setWorkRptPmwDetailDAO(
            WorkRptPmwDetailDAO workRptPmwDetailDAO)
    {
        this.workRptPmwDetailDAO = workRptPmwDetailDAO;
    }
    
    public List findDetail(WorkRptPmwDetailDTO workRptPmwDetailDTO, User loginUser) throws Exception
    {
        return workRptPmwDetailDAO.findDetail(workRptPmwDetailDTO, loginUser);
        
    }
	
    public String findTotalCount(WorkRptPmwDetailDTO workRptPmwDetailDTO, User loginUser)  throws Exception
    {
        return workRptPmwDetailDAO.findTotalCount(workRptPmwDetailDTO, loginUser);
    }
}

