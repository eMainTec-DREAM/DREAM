package dream.work.fmea.list.service.spring;

import common.bean.User;
import dream.work.fmea.list.dao.WorkFmeaResDetailDAO;
import dream.work.fmea.list.dto.WorkFmeaResCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaResDetailDTO;
import dream.work.fmea.list.service.WorkFmeaResDetailService;

/**
 * 고장영향성평가- Detail Service implements
 * @author kim21017
 * @version $Id: WorkFmeaResDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workFmeaResDetailServiceTarget"
 * @spring.txbn id="workFmeaResDetailService"
 * @spring.property name="workFmeaResDetailDAO" ref="workFmeaResDetailDAO"
 */
public class WorkFmeaResDetailServiceImpl implements WorkFmeaResDetailService
{
    private WorkFmeaResDetailDAO workFmeaResDetailDAO = null;
    
    public WorkFmeaResDetailDTO findDetail(WorkFmeaResCommonDTO workFmeaResCommonDTO, User user) throws Exception
    {
    	return workFmeaResDetailDAO.findDetail(workFmeaResCommonDTO, user);
    }
    
    public int insertDetail(WorkFmeaResDetailDTO workFmeaResDetailDTO, User user) throws Exception
    {
    	return workFmeaResDetailDAO.insertDetail(workFmeaResDetailDTO, user);
    }
    
    public int updateDetail(WorkFmeaResDetailDTO workFmeaResDetailDTO, User user) throws Exception
    {
    	 return workFmeaResDetailDAO.updateDetail(workFmeaResDetailDTO, user);
    }
    public int completedDetail(WorkFmeaResCommonDTO workFmeaResCommonDTO, WorkFmeaResDetailDTO workFmeaResDetailDTO, User user) throws Exception
    {
         return workFmeaResDetailDAO.completedDetail(workFmeaResDetailDTO, user);
    }
	public WorkFmeaResDetailDAO getWorkFmeaResDetailDAO() {
		return workFmeaResDetailDAO;
	}

	public void setWorkFmeaResDetailDAO(WorkFmeaResDetailDAO workFmeaResDetailDAO) {
		this.workFmeaResDetailDAO = workFmeaResDetailDAO;
	}
}
