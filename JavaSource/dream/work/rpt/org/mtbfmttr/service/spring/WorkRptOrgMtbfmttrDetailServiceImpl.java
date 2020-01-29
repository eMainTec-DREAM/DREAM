package dream.work.rpt.org.mtbfmttr.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;
import dream.work.rpt.org.mtbfmttr.dao.WorkRptOrgMtbfmttrDetailDAO;
import dream.work.rpt.org.mtbfmttr.dto.WorkRptOrgMtbfmttrDetailDTO;
import dream.work.rpt.org.mtbfmttr.service.WorkRptOrgMtbfmttrDetailService;

/**
 * 조직별MTBF,MTTR 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptOrgMtbfmttrDetailServiceTarget"
 * @spring.txbn id="workRptOrgMtbfmttrDetailService"
 * @spring.property name="workRptOrgMtbfmttrDetailDAO" ref="workRptOrgMtbfmttrDetailDAO"
 */
public class WorkRptOrgMtbfmttrDetailServiceImpl implements WorkRptOrgMtbfmttrDetailService
{
    private WorkRptOrgMtbfmttrDetailDAO workRptOrgMtbfmttrDetailDAO = null;
    
    public WorkRptOrgMtbfmttrDetailDAO getWorkRptOrgMtbfmttrDetailDAO()
    {
        return workRptOrgMtbfmttrDetailDAO;
    }
    
    public void setWorkRptOrgMtbfmttrDetailDAO(
            WorkRptOrgMtbfmttrDetailDAO workRptOrgMtbfmttrDetailDAO)
    {
        this.workRptOrgMtbfmttrDetailDAO = workRptOrgMtbfmttrDetailDAO;
    }
    
    public List findDetail(WorkRptOrgMtbfmttrDetailDTO workRptOrgMtbfmttrDetailDTO, User loginUser)
    {
        return workRptOrgMtbfmttrDetailDAO.findDetail(workRptOrgMtbfmttrDetailDTO, loginUser);
        
    }
    @Override
    public String findTotalCount(WorkRptOrgMtbfmttrDetailDTO workRptOrgMtbfmttrDetailDTO, User user)
    {
        return workRptOrgMtbfmttrDetailDAO.findTotalCount(workRptOrgMtbfmttrDetailDTO, user);
    }
}

