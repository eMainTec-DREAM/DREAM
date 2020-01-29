package dream.work.rpt.org.bd.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.org.bd.dao.WorkRptOrgBdDetailDAO;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdDetailDTO;
import dream.work.rpt.org.bd.service.WorkRptOrgBdDetailService;

/**
 * 조직별 고장분석 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptOrgBdDetailServiceTarget"
 * @spring.txbn id="workRptOrgBdDetailService"
 * @spring.property name="workRptOrgBdDetailDAO" ref="workRptOrgBdDetailDAO"
 */
public class WorkRptOrgBdDetailServiceImpl implements WorkRptOrgBdDetailService
{
    private WorkRptOrgBdDetailDAO workRptOrgBdDetailDAO = null;
    
    public WorkRptOrgBdDetailDAO getWorkRptOrgBdDetailDAO()
    {
        return workRptOrgBdDetailDAO;
    }
    
    public void setWorkRptOrgBdDetailDAO(
            WorkRptOrgBdDetailDAO workRptOrgBdDetailDAO)
    {
        this.workRptOrgBdDetailDAO = workRptOrgBdDetailDAO;
    }
    
    public List findDetail(WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO, User loginUser)
    {
        return workRptOrgBdDetailDAO.findDetail(workRptOrgBdDetailDTO, loginUser);
        
    }
    @Override
    public String findTotalCount(WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO, User user)
    {
        return workRptOrgBdDetailDAO.findTotalCount(workRptOrgBdDetailDTO, user);
    }
}

