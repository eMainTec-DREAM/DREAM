package dream.work.let.permit.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.let.permit.dao.WorkLetPermitPointListDAO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointListDTO;
import dream.work.let.permit.service.WorkLetPermitPointDetailService;
import dream.work.let.permit.service.WorkLetPermitPointListService;

/**
 * 안전작업허가서 작업유형  - 점검항목 목록 Service implements
 * @author syyang
 * @version $Id: WorkLetPermitPointListServiceImpl.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 * @spring.bean id="workLetPermitPointListServiceTarget"
 * @spring.txbn id="workLetPermitPointListService"
 * @spring.property name="workLetPermitPointListDAO" ref="workLetPermitPointListDAO"
 * @spring.property name="workLetPermitPointDetailService" ref="workLetPermitPointDetailService"
 */
public class WorkLetPermitPointListServiceImpl implements WorkLetPermitPointListService
{
	private WorkLetPermitPointListDAO workLetPermitPointListDAO = null;
	private WorkLetPermitPointDetailService workLetPermitPointDetailService = null;

	public WorkLetPermitPointListDAO getWorkLetPermitPointListDAO() {
		return workLetPermitPointListDAO;
	}
	public void setWorkLetPermitPointListDAO(WorkLetPermitPointListDAO workLetPermitPointListDAO) {
		this.workLetPermitPointListDAO = workLetPermitPointListDAO;
	}
	public WorkLetPermitPointDetailService getWorkLetPermitPointDetailService() {
		return workLetPermitPointDetailService;
	}
	public void setWorkLetPermitPointDetailService(WorkLetPermitPointDetailService workLetPermitPointDetailService) {
		this.workLetPermitPointDetailService = workLetPermitPointDetailService;
	}
	
	public List findList(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointListDTO workLetPermitPointListDTO, User user) throws Exception
    {      
        return workLetPermitPointListDAO.findList(workLetPermitDetailDTO, workLetPermitPointListDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workLetPermitPointListDAO.deleteList(id, user);
            }
        return result;
    }

	public String findTotalCount(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointListDTO workLetPermitPointListDTO,User user) throws Exception
    {
        return workLetPermitPointListDAO.findTotalCount(workLetPermitDetailDTO, workLetPermitPointListDTO, user);
    }
    
    @Override
    public int insertStdPointList(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO, User user) throws Exception
    {
    	int result = 0;
    	
    	String[] multiKey = workLetPermitPointDetailDTO.getMultiKey().split("\\+");
    	
    	for(int i=0; multiKey.length > i; i++)
    	{
    		workLetPermitPointDetailDTO = workLetPermitPointListDAO.getWoLetCategPointDetail(multiKey[i], user);
    		workLetPermitPointDetailDTO.setWoLetListPointId(workLetPermitPointListDAO.getNextSequence("SQAWOLETLISTPOINT_ID"));
    		
    		result = result + workLetPermitPointDetailService.insertDetail(workLetPermitDetailDTO, workLetPermitPointDetailDTO, user);
    	}
    	
    	return result;
    }
    
}

