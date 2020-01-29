package dream.work.fmea.list.service.spring;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.fmea.list.dao.WorkFmeaDetailDAO;
import dream.work.fmea.list.dto.WorkFmeaCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaDetailDTO;
import dream.work.fmea.list.service.WorkFmeaDetailService;

/**
 * 고장영향성평가- Detail Service implements
 * @author kim21017
 * @version $Id: WorkFmeaDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="workFmeaDetailServiceTarget"
 * @spring.txbn id="workFmeaDetailService"
 * @spring.property name="workFmeaDetailDAO" ref="workFmeaDetailDAO"
 */
public class WorkFmeaDetailServiceImpl implements WorkFmeaDetailService
{
    private WorkFmeaDetailDAO workFmeaDetailDAO = null;
    
    public WorkFmeaDetailDTO findDetail(WorkFmeaCommonDTO workFmeaCommonDTO, User user) throws Exception
    {
    	return workFmeaDetailDAO.findDetail(workFmeaCommonDTO, user);
    }
    
    public int insertDetail(WorkFmeaDetailDTO workFmeaDetailDTO, User user) throws Exception
    {
    	return workFmeaDetailDAO.insertDetail(workFmeaDetailDTO, user);
    }
    
    public int updateDetail(WorkFmeaDetailDTO workFmeaDetailDTO, User user) throws Exception
    {
    	 return workFmeaDetailDAO.updateDetail(workFmeaDetailDTO, user);
    }
    public int confirmDetail(WorkFmeaDetailDTO workFmeaDetailDTO, User user) throws Exception
    {
    	 return workFmeaDetailDAO.confirmDetail(workFmeaDetailDTO, user);
    }

	public WorkFmeaDetailDAO getWorkFmeaDetailDAO() {
		return workFmeaDetailDAO;
	}

	public void setWorkFmeaDetailDAO(WorkFmeaDetailDAO workFmeaDetailDAO) {
		this.workFmeaDetailDAO = workFmeaDetailDAO;
	}

    @Override
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user)
    {
        workFmeaDetailDAO.setStatus(appReqDetailDTO, user);
    }

    @Override
    public String getStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        return workFmeaDetailDAO.getStatus(appReqDetailDTO, user);
    }
}
