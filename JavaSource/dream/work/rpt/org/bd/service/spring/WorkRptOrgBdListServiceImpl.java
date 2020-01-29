package dream.work.rpt.org.bd.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.org.bd.dao.WorkRptOrgBdListDAO;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;
import dream.work.rpt.org.bd.service.WorkRptOrgBdListService;

/**
 * 조직별 고장분석 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptOrgBdListServiceTarget"
 * @spring.txbn id="workRptOrgBdListService"
 * @spring.property name="workRptOrgBdListDAO" ref="workRptOrgBdListDAO"
 */
public class WorkRptOrgBdListServiceImpl implements WorkRptOrgBdListService
{
    private WorkRptOrgBdListDAO workRptOrgBdListDAO = null;
    
	public WorkRptOrgBdListDAO getWorkRptOrgBdListDAO()
    {
        return workRptOrgBdListDAO;
    }
	
    public void setWorkRptOrgBdListDAO(
            WorkRptOrgBdListDAO workRptOrgBdListDAO)
    {
        this.workRptOrgBdListDAO = workRptOrgBdListDAO;
    }
    
    public List findList(WorkRptOrgBdCommonDTO workRptOrgBdCommonDTO, User loginUser)
    {
        return workRptOrgBdListDAO.findList(workRptOrgBdCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(WorkRptOrgBdCommonDTO workRptOrgBdCommonDTO, User user)
    {
        return workRptOrgBdListDAO.findTotalCount(workRptOrgBdCommonDTO, user);
    }
	
}

