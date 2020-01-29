package dream.work.list.service.spring;

import common.bean.User;
import dream.work.list.dao.WorkListPointDetailDAO;
import dream.work.list.dto.WorkListPointDetailDTO;
import dream.work.list.dto.WorkListPointListDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.service.WorkListPointDetailService;

/**
 * 점검내용 - Detail Service implements
 * @author youngjoo38
 * @version $Id: WorkListPointDetailServiceImpl.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workListPointDetailServiceTarget"
 * @spring.txbn id="workListPointDetailService"
 * @spring.property name="workListPointDetailDAO" ref="workListPointDetailDAO"
 */
public class WorkListPointDetailServiceImpl implements WorkListPointDetailService
{
    private WorkListPointDetailDAO workListPointDetailDAO = null;
    
    public WorkListPointDetailDTO findDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, WorkListPointListDTO workListPointListDTO, User user) throws Exception
    {
    	return workListPointDetailDAO.findDetail(workListPtrlResultCommonDTO,workListPointListDTO, user);
    }
    
    public int insertDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,WorkListPointDetailDTO workListPointDetailDTO, User user) throws Exception
    {
     	return workListPointDetailDAO.insertDetail(workListPtrlResultCommonDTO,workListPointDetailDTO, user);
    }
    
    public int updateDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,WorkListPointDetailDTO workListPointDetailDTO, User user) throws Exception
    {
    	int rtnValue = 0;
    	
    	rtnValue+= workListPointDetailDAO.updateDetail(workListPtrlResultCommonDTO,workListPointDetailDTO, user);
    	
     	return rtnValue;
    }

	public WorkListPointDetailDAO getWorkListPointDetailDAO() {
		return workListPointDetailDAO;
	}

	public void setWorkListPointDetailDAO(WorkListPointDetailDAO workListPointDetailDAO) {
		this.workListPointDetailDAO = workListPointDetailDAO;
	}
    

}
