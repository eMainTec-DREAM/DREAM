package dream.work.pm.list.service.spring;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmMsTimeUInsDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsDetailDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsListDTO;
import dream.work.pm.list.service.WorkPmMsTimeUInsDetailService;

/**
 * 작업시간
 * @author js.lee
 * @version $Id: Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmMsTimeUInsDetailServiceTarget"
 * @spring.txbn id="workPmMsTimeUInsDetailService"
 * @spring.property name="workPmMsTimeUInsDetailDAO" ref="workPmMsTimeUInsDetailDAO"
 */
public class WorkPmMsTimeUInsDetailServiceImpl implements WorkPmMsTimeUInsDetailService
{
    private WorkPmMsTimeUInsDetailDAO workPmMsTimeUInsDetailDAO = null;

	public WorkPmMsTimeUInsDetailDAO getWorkPmMsTimeUInsDetailDAO() {
		return workPmMsTimeUInsDetailDAO;
	}

	public void setWorkPmMsTimeUInsDetailDAO(WorkPmMsTimeUInsDetailDAO workPmMsTimeUInsDetailDAO) {
		this.workPmMsTimeUInsDetailDAO = workPmMsTimeUInsDetailDAO;
	}

	public WorkPmMsTimeUInsDetailDTO findDetail(WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO, User user)throws Exception
    {
        return workPmMsTimeUInsDetailDAO.findDetail(workPmMsTimeUInsListDTO, user);
    }
    
	public int insertDetail(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception
    {        
        
        return workPmMsTimeUInsDetailDAO.insertDetail( workPmMsTimeUInsDetailDTO, maPmMstrCommonDTO, user);
    }

	public int updateDetail(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, User user) throws Exception {
		return workPmMsTimeUInsDetailDAO.updateDetail(workPmMsTimeUInsDetailDTO, user);
	}

	@Override
	public String validTime(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) {
		return workPmMsTimeUInsDetailDAO.validTime(workPmMsTimeUInsDetailDTO, maPmMstrCommonDTO, user);
	}
}
