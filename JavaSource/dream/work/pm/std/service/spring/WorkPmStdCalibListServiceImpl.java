package dream.work.pm.std.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dao.WorkPmStdCalibListDAO;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.service.WorkPmStdCalibListService;

/**
 * 교정표준값 타입  - List Service implements
 * @author kim21017
 * @version $Id: WorkPmStdCalibListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workPmStdCalibListServiceTarget"
 * @spring.txbn id="workPmStdCalibListService"
 * @spring.property name="workPmStdCalibListDAO" ref="workPmStdCalibListDAO"
 */
public class WorkPmStdCalibListServiceImpl implements WorkPmStdCalibListService
{
	private WorkPmStdCalibListDAO workPmStdCalibListDAO = null;

	public List findList(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, User user) throws Exception
    {      
        return workPmStdCalibListDAO.findList(workPmStdCalibCommonDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workPmStdCalibListDAO.deleteValList(id, user);
                result = result + workPmStdCalibListDAO.deleteList(id, user);
            }
        return result;
    }
	public String findTotalCount(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, User user) throws Exception
    {      
        return workPmStdCalibListDAO.findTotalCount(workPmStdCalibCommonDTO,user);
    }
	public WorkPmStdCalibListDAO getWorkPmStdCalibListDAO() {
		return workPmStdCalibListDAO;
	}

	public void setWorkPmStdCalibListDAO(WorkPmStdCalibListDAO workPmStdCalibListDAO) {
		this.workPmStdCalibListDAO = workPmStdCalibListDAO;
	}
}

