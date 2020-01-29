package dream.work.pm.std.service.spring;

import common.bean.User;
import dream.work.pm.std.dao.WorkPmStdCalibValDetailDAO;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValDetailDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValListDTO;
import dream.work.pm.std.service.WorkPmStdCalibValDetailService;

/**
 * 표준교정값 - Detail Service implements
 * @author kim21017
 * @version $Id: WorkPmStdCalibValDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workPmStdCalibValDetailServiceTarget"
 * @spring.txbn id="workPmStdCalibValDetailService"
 * @spring.property name="workPmStdCalibValDetailDAO" ref="workPmStdCalibValDetailDAO"
 */
public class WorkPmStdCalibValDetailServiceImpl implements WorkPmStdCalibValDetailService
{
    private WorkPmStdCalibValDetailDAO workPmStdCalibValDetailDAO = null;
    
    public WorkPmStdCalibValDetailDTO findDetail(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, WorkPmStdCalibValListDTO workPmStdCalibValListDTO, User user) throws Exception
    {
    	return workPmStdCalibValDetailDAO.findDetail(workPmStdCalibCommonDTO,workPmStdCalibValListDTO, user);
    }
    
    public int insertDetail(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO,WorkPmStdCalibValDetailDTO workPmStdCalibValDetailDTO, User user) throws Exception
    {
     	return workPmStdCalibValDetailDAO.insertDetail(workPmStdCalibCommonDTO,workPmStdCalibValDetailDTO, user);
    }
    
    public int updateDetail(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO,WorkPmStdCalibValDetailDTO workPmStdCalibValDetailDTO, User user) throws Exception
    {
     	return workPmStdCalibValDetailDAO.updateDetail(workPmStdCalibCommonDTO,workPmStdCalibValDetailDTO, user);
    }

	public WorkPmStdCalibValDetailDAO getWorkPmStdCalibValDetailDAO() {
		return workPmStdCalibValDetailDAO;
	}

	public void setWorkPmStdCalibValDetailDAO(WorkPmStdCalibValDetailDAO workPmStdCalibValDetailDAO) {
		this.workPmStdCalibValDetailDAO = workPmStdCalibValDetailDAO;
	}
    

}
