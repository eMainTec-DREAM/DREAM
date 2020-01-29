package dream.work.fmea.list.service.spring;

import common.bean.User;
import dream.work.fmea.list.dao.WorkFmeaReqDetailDAO;
import dream.work.fmea.list.dto.WorkFmeaReqCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaReqDetailDTO;
import dream.work.fmea.list.service.WorkFmeaReqDetailService;

/**
 * 고장영향성평가- Detail Service implements
 * @author kim21017
 * @version $Id: WorkFmeaReqDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workFmeaReqDetailServiceTarget"
 * @spring.txbn id="workFmeaReqDetailService"
 * @spring.property name="workFmeaReqDetailDAO" ref="workFmeaReqDetailDAO"
 */
public class WorkFmeaReqDetailServiceImpl implements WorkFmeaReqDetailService
{
    private WorkFmeaReqDetailDAO workFmeaReqDetailDAO = null;
    
    public WorkFmeaReqDetailDTO findDetail(WorkFmeaReqCommonDTO workFmeaReqCommonDTO, User user) throws Exception
    {
    	return workFmeaReqDetailDAO.findDetail(workFmeaReqCommonDTO, user);
    }
    
    public int insertDetail(WorkFmeaReqDetailDTO workFmeaReqDetailDTO, User user) throws Exception
    {
    	return workFmeaReqDetailDAO.insertDetail(workFmeaReqDetailDTO, user);
    }
    
    public int updateDetail(WorkFmeaReqDetailDTO workFmeaReqDetailDTO, User user) throws Exception
    {
    	 return workFmeaReqDetailDAO.updateDetail(workFmeaReqDetailDTO, user);
    }
    public int updateStatus(WorkFmeaReqDetailDTO workFmeaReqDetailDTO, User user) throws Exception
    {
    	 return workFmeaReqDetailDAO.updateStatus(workFmeaReqDetailDTO, user);
    }

	public WorkFmeaReqDetailDAO getWorkFmeaReqDetailDAO() {
		return workFmeaReqDetailDAO;
	}

	public void setWorkFmeaReqDetailDAO(WorkFmeaReqDetailDAO workFmeaReqDetailDAO) {
		this.workFmeaReqDetailDAO = workFmeaReqDetailDAO;
	}
}
