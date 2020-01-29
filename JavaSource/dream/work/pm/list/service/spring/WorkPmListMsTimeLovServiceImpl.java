package dream.work.pm.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmListMsTimeLovDAO;
import dream.work.pm.list.dto.WorkPmListMsTimeLovDTO;
import dream.work.pm.list.service.WorkPmListMsTimeLovService;

/**
 * ÀÛ¾÷¿äÃ»À¯Çü ÆË¾÷ ServiceImpl
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="workPmListMsTimeLovServiceTarget"
 * @spring.txbn id="workPmListMsTimeLovService"
 * @spring.property name="workPmListMsTimeLovDAO" ref="workPmListMsTimeLovDAO"
 */
public class WorkPmListMsTimeLovServiceImpl implements WorkPmListMsTimeLovService
{
    /** ÆË¾÷ DAO */
    private WorkPmListMsTimeLovDAO workPmListMsTimeLovDAO = null;
    
    public WorkPmListMsTimeLovDAO getWorkPmListMsTimeLovDAO() {
		return workPmListMsTimeLovDAO;
	}

	public void setWorkPmListMsTimeLovDAO(WorkPmListMsTimeLovDAO workPmListMsTimeLovDAO) {
		this.workPmListMsTimeLovDAO = workPmListMsTimeLovDAO;
	}

	public List findList(User loginUser, WorkPmListMsTimeLovDTO workPmListMsTimeLovDTO)
    {
        List resultList = null;
        resultList = workPmListMsTimeLovDAO.findList(loginUser,workPmListMsTimeLovDTO);
        
        return resultList;
    }
}