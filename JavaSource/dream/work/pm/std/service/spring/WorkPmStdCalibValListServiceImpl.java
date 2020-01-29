package dream.work.pm.std.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dao.WorkPmStdCalibValListDAO;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValListDTO;
import dream.work.pm.std.service.WorkPmStdCalibValListService;

/**
 * 표준교정값 - List Service implements
 * @author kim21017
 * @version $Id: WorkPmStdCalibValListServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workPmStdCalibValListServiceTarget"
 * @spring.txbn id="workPmStdCalibValListService"
 * @spring.property name="workPmStdCalibValListDAO" ref="workPmStdCalibValListDAO"
 */
public class WorkPmStdCalibValListServiceImpl implements WorkPmStdCalibValListService
{
	private WorkPmStdCalibValListDAO workPmStdCalibValListDAO = null;

	public List findList(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO,WorkPmStdCalibValListDTO workPmStdCalibValListDTO, User user) throws Exception
    {      
        return workPmStdCalibValListDAO.findList(workPmStdCalibCommonDTO,workPmStdCalibValListDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workPmStdCalibValListDAO.deleteList(id, user);
            }
        return result;
    }
	public String findTotalCount(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO,WorkPmStdCalibValListDTO workPmStdCalibValListDTO, User user) throws Exception
    {      
        return workPmStdCalibValListDAO.findTotalCount(workPmStdCalibCommonDTO,workPmStdCalibValListDTO,user);
    }
	public WorkPmStdCalibValListDAO getWorkPmStdCalibValListDAO() {
		return workPmStdCalibValListDAO;
	}

	public void setWorkPmStdCalibValListDAO(WorkPmStdCalibValListDAO workPmStdCalibValListDAO) {
		this.workPmStdCalibValListDAO = workPmStdCalibValListDAO;
	}

}

