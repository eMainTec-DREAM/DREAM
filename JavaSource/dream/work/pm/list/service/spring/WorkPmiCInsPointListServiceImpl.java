package dream.work.pm.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.pm.list.dao.WorkPmiCInsPointListDAO;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsPointDetailDTO;
import dream.work.pm.list.service.WorkPmiCInsPointDetailService;
import dream.work.pm.list.service.WorkPmiCInsPointListService;

/**
 * WorkPmiCInsPoint Page - List Service implements
 * @author youngjoo38
 * @version $Id: WorkPmiCInsPointListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmiCInsPointListServiceTarget"
 * @spring.txbn id="workPmiCInsPointListService"
 * @spring.property name="workPmiCInsPointListDAO" ref="workPmiCInsPointListDAO"
 * @spring.property name="workPmiCInsPointDetailService" ref="workPmiCInsPointDetailService"
 */
public class WorkPmiCInsPointListServiceImpl implements WorkPmiCInsPointListService
{
    private WorkPmiCInsPointListDAO workPmiCInsPointListDAO = null;
    
    private WorkPmiCInsPointDetailService workPmiCInsPointDetailService = null;

    public WorkPmiCInsPointListDAO getWorkPmiCInsPointListDAO() {
    	return workPmiCInsPointListDAO;
    }
    
    public void setWorkPmiCInsPointListDAO(WorkPmiCInsPointListDAO workPmiCInsPointListDAO) {
    	this.workPmiCInsPointListDAO = workPmiCInsPointListDAO;
    }    
    
    public WorkPmiCInsPointDetailService getWorkPmiCInsPointDetailService() {
		return workPmiCInsPointDetailService;
	}

	public void setWorkPmiCInsPointDetailService(WorkPmiCInsPointDetailService workPmiCInsPointDetailService) {
		this.workPmiCInsPointDetailService = workPmiCInsPointDetailService;
	}

	
	public List findList(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) throws Exception
    {      
        return workPmiCInsPointListDAO.findList(workPmiCInsCommonDTO, user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workPmiCInsPointListDAO.deleteList(id, user);
            }
        return result;
    }

    public String findTotalCount(WorkPmiCInsCommonDTO workPmiCInsCommonDTO,User user)  throws Exception
    {
        return workPmiCInsPointListDAO.findTotalCount(workPmiCInsCommonDTO, user);
    }

	@Override
	public void saveList(List<Map> gridList, User user) throws Exception 
	{
		WorkPmiCInsPointDetailDTO  workPmiCInsPointDetailDTO = null;

		for(Map map : gridList)
        {
			workPmiCInsPointDetailDTO = (WorkPmiCInsPointDetailDTO)CommonUtil.makeDTO(map, WorkPmiCInsPointDetailDTO.class);
        	
        	switch(workPmiCInsPointDetailDTO.getNativeeditor())
        	{
        		case "inserted":
        			break;
        		case "updated" : workPmiCInsPointDetailService.updateDetail(workPmiCInsPointDetailDTO, user);
        			break;
        		case "deleted":
        			break;
        	}
        	
        }
	}
}
