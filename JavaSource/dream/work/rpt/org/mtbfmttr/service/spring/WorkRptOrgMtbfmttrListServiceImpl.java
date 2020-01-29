package dream.work.rpt.org.mtbfmttr.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.org.mtbfmttr.dao.WorkRptOrgMtbfmttrListDAO;
import dream.work.rpt.org.mtbfmttr.dto.WorkRptOrgMtbfmttrCommonDTO;
import dream.work.rpt.org.mtbfmttr.service.WorkRptOrgMtbfmttrListService;

/**
 * 조직별MTBF,MTTR 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptOrgMtbfmttrListServiceTarget"
 * @spring.txbn id="workRptOrgMtbfmttrListService"
 * @spring.property name="workRptOrgMtbfmttrListDAO" ref="workRptOrgMtbfmttrListDAO"
 */
public class WorkRptOrgMtbfmttrListServiceImpl implements WorkRptOrgMtbfmttrListService
{
    private WorkRptOrgMtbfmttrListDAO workRptOrgMtbfmttrListDAO = null;
    
	public WorkRptOrgMtbfmttrListDAO getWorkRptOrgMtbfmttrListDAO()
    {
        return workRptOrgMtbfmttrListDAO;
    }
	
    public void setWorkRptOrgMtbfmttrListDAO(
            WorkRptOrgMtbfmttrListDAO workRptOrgMtbfmttrListDAO)
    {
        this.workRptOrgMtbfmttrListDAO = workRptOrgMtbfmttrListDAO;
    }
    
    public List findList(WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO, User loginUser)
    {
        return workRptOrgMtbfmttrListDAO.findList(workRptOrgMtbfmttrCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO, User user)
    {
        return workRptOrgMtbfmttrListDAO.findTotalCount(workRptOrgMtbfmttrCommonDTO, user);
    }
	
}

