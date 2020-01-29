package dream.work.rpt.pm.ratio.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pm.ratio.dao.WorkRptPmRatioDetailDAO;
import dream.work.rpt.pm.ratio.dto.WorkRptPmRatioDetailDTO;
import dream.work.rpt.pm.ratio.service.WorkRptPmRatioDetailService;

/**
 * ��ȹ������(��ġ) �� ���
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPmRatioDetailServiceTarget"
 * @spring.txbn id="workRptPmRatioDetailService"
 * @spring.property name="workRptPmRatioDetailDAO" ref="workRptPmRatioDetailDAO"
 */
public class WorkRptPmRatioDetailServiceImpl implements WorkRptPmRatioDetailService
{
    private WorkRptPmRatioDetailDAO workRptPmRatioDetailDAO = null;
    
    public WorkRptPmRatioDetailDAO getWorkRptPmRatioDetailDAO()
    {
        return workRptPmRatioDetailDAO;
    }
    
    public void setWorkRptPmRatioDetailDAO(
            WorkRptPmRatioDetailDAO workRptPmRatioDetailDAO)
    {
        this.workRptPmRatioDetailDAO = workRptPmRatioDetailDAO;
    }
    
    public List findDetail(WorkRptPmRatioDetailDTO workRptPmRatioDetailDTO, User loginUser)
    {
        return workRptPmRatioDetailDAO.findDetail(workRptPmRatioDetailDTO, loginUser);
        
    }
	
}

