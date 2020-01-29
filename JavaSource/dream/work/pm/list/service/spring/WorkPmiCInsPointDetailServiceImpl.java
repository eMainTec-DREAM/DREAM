package dream.work.pm.list.service.spring;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmiCInsPointDetailDAO;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsPointDetailDTO;
import dream.work.pm.list.service.WorkPmiCInsPointDetailService;

/**
 * WorkPmiCInsPoint Page - Detail Service implements
 * @author youngjoo38
 * @version $Id: WorkPmiCInsPointDetailServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmiCInsPointDetailServiceTarget"
 * @spring.txbn id="workPmiCInsPointDetailService"
 * @spring.property name="workPmiCInsPointDetailDAO" ref="workPmiCInsPointDetailDAO"
 */
public class WorkPmiCInsPointDetailServiceImpl implements WorkPmiCInsPointDetailService
{
    private WorkPmiCInsPointDetailDAO workPmiCInsPointDetailDAO = null;
    
    public WorkPmiCInsPointDetailDTO findDetail(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) throws Exception
    {
        return workPmiCInsPointDetailDAO.findDetail(workPmiCInsCommonDTO, user);
    }
    
    public int insertDetail(WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO, User user) throws Exception
    {
         return workPmiCInsPointDetailDAO.insertDetail(workPmiCInsPointDetailDTO, user);
    }
    
    public int updateDetail(WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO, User user) throws Exception
    {
    	int rtnValue = 0;
    	
        String id = "";
        
        id = workPmiCInsPointDetailDAO.getId(workPmiCInsPointDetailDTO, user);
        
        workPmiCInsPointDetailDTO.setEqAsmbId(id);
        
        rtnValue+=workPmiCInsPointDetailDAO.updateDetail(workPmiCInsPointDetailDTO, user);
        
        return rtnValue;
    }

    public WorkPmiCInsPointDetailDAO getWorkPmiCInsPointDetailDAO() {
        return workPmiCInsPointDetailDAO;
    }

    public void setWorkPmiCInsPointDetailDAO(WorkPmiCInsPointDetailDAO workPmiCInsPointDetailDAO) {
        this.workPmiCInsPointDetailDAO = workPmiCInsPointDetailDAO;
    }
    
    public String getId(WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO, User user) throws Exception
    {
        return workPmiCInsPointDetailDAO.getId(workPmiCInsPointDetailDTO, user);
    }
}
