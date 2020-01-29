package dream.work.pm.list.service.spring;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmListMsTimeDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeListDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeDetailDTO;
import dream.work.pm.list.service.WorkPmListMsTimeDetailService;

/**
 * 작업시간
 * @author js.lee
 * @version $Id: Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmListMsTimeDetailServiceTarget"
 * @spring.txbn id="workPmListMsTimeDetailService"
 * @spring.property name="workPmListMsTimeDetailDAO" ref="workPmListMsTimeDetailDAO"
 */
public class WorkPmListMsTimeDetailServiceImpl implements WorkPmListMsTimeDetailService
{
    private WorkPmListMsTimeDetailDAO workPmListMsTimeDetailDAO = null;

	public WorkPmListMsTimeDetailDAO getWorkPmListMsTimeDetailDAO() {
		return workPmListMsTimeDetailDAO;
	}

	public void setWorkPmListMsTimeDetailDAO(WorkPmListMsTimeDetailDAO workPmListMsTimeDetailDAO) {
		this.workPmListMsTimeDetailDAO = workPmListMsTimeDetailDAO;
	}

	public WorkPmListMsTimeDetailDTO findDetail(WorkPmListMsTimeListDTO workPmListMsTimeListDTO, User user)throws Exception
    {
        return workPmListMsTimeDetailDAO.findDetail(workPmListMsTimeListDTO, user);
    }
    
	public int insertDetail(WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception
    {        
        
        return workPmListMsTimeDetailDAO.insertDetail( workPmListMsTimeDetailDTO, maPmMstrCommonDTO, user);
    }

	public int updateDetail(WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO, User user) throws Exception {
		return workPmListMsTimeDetailDAO.updateDetail(workPmListMsTimeDetailDTO, user);
	}

	@Override
	public String validTime(WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) {
		return workPmListMsTimeDetailDAO.validTime(workPmListMsTimeDetailDTO, maPmMstrCommonDTO, user);
	}
}
