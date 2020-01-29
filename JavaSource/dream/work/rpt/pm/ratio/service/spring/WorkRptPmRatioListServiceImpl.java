package dream.work.rpt.pm.ratio.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pm.ratio.dao.WorkRptPmRatioListDAO;
import dream.work.rpt.pm.ratio.dto.WorkRptPmRatioCommonDTO;
import dream.work.rpt.pm.ratio.service.WorkRptPmRatioListService;

/**
 * 계획보전율(위치) 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPmRatioListServiceTarget"
 * @spring.txbn id="workRptPmRatioListService"
 * @spring.property name="workRptPmRatioListDAO" ref="workRptPmRatioListDAO"
 */
public class WorkRptPmRatioListServiceImpl implements WorkRptPmRatioListService
{
    private WorkRptPmRatioListDAO workRptPmRatioListDAO = null;
    
	public WorkRptPmRatioListDAO getWorkRptPmRatioListDAO()
    {
        return workRptPmRatioListDAO;
    }
	
    public void setWorkRptPmRatioListDAO(
            WorkRptPmRatioListDAO workRptPmRatioListDAO)
    {
        this.workRptPmRatioListDAO = workRptPmRatioListDAO;
    }
    
    public List findList(WorkRptPmRatioCommonDTO workRptPmRatioCommonDTO, User loginUser)
    {
        return workRptPmRatioListDAO.findList(workRptPmRatioCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(WorkRptPmRatioCommonDTO workRptPmRatioCommonDTO, User user)
    {
        return workRptPmRatioListDAO.findTotalCount(workRptPmRatioCommonDTO, user);
    }
	
}

