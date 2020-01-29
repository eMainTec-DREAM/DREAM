package dream.work.pm.list.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.pm.list.dao.WorkPmiDInsPointListDAO;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsPointDetailDTO;
import dream.work.pm.list.service.WorkPmiDInsPointDetailService;
import dream.work.pm.list.service.WorkPmiDInsPointListService;

/**
 * WorkPmiDInsPoint Page - List Service implements
 * @author youngjoo38
 * @version $Id: WorkPmiDInsPointListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmiDInsPointListServiceTarget"
 * @spring.txbn id="workPmiDInsPointListService"
 * @spring.property name="workPmiDInsPointListDAO" ref="workPmiDInsPointListDAO"
 * @spring.property name="workPmiDInsPointDetailService" ref="workPmiDInsPointDetailService"
 */
public class WorkPmiDInsPointListServiceImpl implements WorkPmiDInsPointListService
{
    private WorkPmiDInsPointListDAO workPmiDInsPointListDAO = null;
    
    private WorkPmiDInsPointDetailService workPmiDInsPointDetailService = null;
    
    
    public WorkPmiDInsPointListDAO getWorkPmiDInsPointListDAO() {
    	return workPmiDInsPointListDAO;
    }
	    public void setWorkPmiDInsPointListDAO(WorkPmiDInsPointListDAO workPmiDInsPointListDAO) {
    	this.workPmiDInsPointListDAO = workPmiDInsPointListDAO;
    }    

    public WorkPmiDInsPointDetailService getWorkPmiDInsPointDetailService() {
		return workPmiDInsPointDetailService;
	}
	public void setWorkPmiDInsPointDetailService(WorkPmiDInsPointDetailService workPmiDInsPointDetailService) {
		this.workPmiDInsPointDetailService = workPmiDInsPointDetailService;
	}

	
	public List findList(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) throws Exception
    {      
        return workPmiDInsPointListDAO.findList(workPmiDInsCommonDTO, user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null) && deleteRows.length>0){
            List list = new ArrayList();
            WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO = new WorkPmiDInsPointDetailDTO();
            for(String id : deleteRows)
            {
                workPmiDInsPointDetailDTO.setPmInsDPointId(id);
                list.add(BeanUtils.cloneBean(workPmiDInsPointDetailDTO));
            }
            result = workPmiDInsPointListDAO.deleteList(list, user).length;
        }
        
        return result;
    }
    
    public String findTotalCount(WorkPmiDInsCommonDTO workPmiDInsCommonDTO,User user)  throws Exception
    {
        return workPmiDInsPointListDAO.findTotalCount(workPmiDInsCommonDTO, user);
    }

	@Override
	public void saveList(List<Map> gridList, User user) throws Exception
	{
        WorkPmiDInsPointDetailDTO  workPmiDInsPointDetailDTO = null;
        
        for(Map map : gridList)
        {
        	workPmiDInsPointDetailDTO = (WorkPmiDInsPointDetailDTO)CommonUtil.makeDTO(map, WorkPmiDInsPointDetailDTO.class);
        	
        	switch(workPmiDInsPointDetailDTO.getNativeeditor())
        	{
        		case "inserted":
        			break;
        		case "updated" : workPmiDInsPointDetailService.updateDetail(workPmiDInsPointDetailDTO, user);
        			break;
        		case "deleted":
        			break;
        	}
        	
        }
	}
}
