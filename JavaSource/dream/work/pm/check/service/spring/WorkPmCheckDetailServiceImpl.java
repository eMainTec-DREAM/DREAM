package dream.work.pm.check.service.spring;

import common.bean.User;
import dream.work.pm.check.dao.WorkPmCheckDetailDAO;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckDetailDTO;
import dream.work.pm.check.service.WorkPmCheckDetailService;

/**
 * WorkPmCheck Page - Detail Service implements
 * @author youngjoo38
 * @version $Id: WorkPmCheckDetailServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmCheckDetailServiceTarget"
 * @spring.txbn id="workPmCheckDetailService"
 * @spring.property name="workPmCheckDetailDAO" ref="workPmCheckDetailDAO"
 */
public class WorkPmCheckDetailServiceImpl implements WorkPmCheckDetailService
{
    private WorkPmCheckDetailDAO workPmCheckDetailDAO = null;
    
    public WorkPmCheckDetailDTO findDetail(WorkPmCheckCommonDTO workPmCheckCommonDTO, User user) throws Exception
    {
        return workPmCheckDetailDAO.findDetail(workPmCheckCommonDTO, user);
    }
    
    public int insertDetail(WorkPmCheckDetailDTO workPmCheckDetailDTO, User user) throws Exception
    {
         return workPmCheckDetailDAO.insertDetail(workPmCheckDetailDTO, user);
    }
    
    public int updateDetail(WorkPmCheckDetailDTO workPmCheckDetailDTO, User user) throws Exception
    {
         return workPmCheckDetailDAO.updateDetail(workPmCheckDetailDTO, user);
    }

    public WorkPmCheckDetailDAO getWorkPmCheckDetailDAO() {
        return workPmCheckDetailDAO;
    }

    public void setWorkPmCheckDetailDAO(WorkPmCheckDetailDAO workPmCheckDetailDAO) {
        this.workPmCheckDetailDAO = workPmCheckDetailDAO;
    }
}
