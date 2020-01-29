package dream.work.list.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dao.WorkListPointListDAO;
import dream.work.list.dto.WorkListPointDetailDTO;
import dream.work.list.dto.WorkListPointListDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.service.WorkListPointDetailService;
import dream.work.list.service.WorkListPointListService;

/**
 * WorkListPoint Page - List Service implements
 * @author youngjoo38
 * @version $Id: WorkListPointListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workListPointListServiceTarget"
 * @spring.txbn id="workListPointListService"
 * @spring.property name="workListPointListDAO" ref="workListPointListDAO"
 * @spring.property name="workListPointDetailService" ref="workListPointDetailService"
 */
public class WorkListPointListServiceImpl implements WorkListPointListService
{
    private WorkListPointListDAO workListPointListDAO = null;
    
    private WorkListPointDetailService workListPointDetailService = null;

    public WorkListPointListDAO getWorkListPointListDAO() {
    	return workListPointListDAO;
    }
    
    public void setWorkListPointListDAO(WorkListPointListDAO workListPointListDAO) {
    	this.workListPointListDAO = workListPointListDAO;
    }    
    
    public WorkListPointDetailService getWorkListPointDetailService() {
		return workListPointDetailService;
	}

	public void setWorkListPointDetailService(WorkListPointDetailService workListPointDetailService) {
		this.workListPointDetailService = workListPointDetailService;
	}

	public List findList(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, WorkListPointListDTO workListPointListDTO, User user) throws Exception
    {      
        return workListPointListDAO.findList(workListPtrlResultCommonDTO,workListPointListDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null) && deleteRows.length>0) {
            List list = new ArrayList();
            WorkListPointDetailDTO workListPointDetailDTO = new WorkListPointDetailDTO();
            for(String id : deleteRows)
            {
                workListPointDetailDTO.setPmPtrlRsltPointId(id);
                list.add(BeanUtils.cloneBean(workListPointDetailDTO));
            }
            result = workListPointListDAO.deleteList(list, user).length;
        }
        
        return result;
    }

    
    public String findTotalCount(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, WorkListPointListDTO workListPointListDTO, User user)  throws Exception
    {
        return workListPointListDAO.findTotalCount(workListPtrlResultCommonDTO, workListPointListDTO, user);
    }

	@Override
	public void saveList(List<Map> gridList, User user) throws Exception
	{
		WorkListPointDetailDTO  workListPointDetailDTO = null;
        WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO = null; 
    	
        for(Map map : gridList)
        {
        	workListPointDetailDTO = (WorkListPointDetailDTO)CommonUtil.makeDTO(map, WorkListPointDetailDTO.class);
        	workListPtrlResultCommonDTO = (WorkListPtrlResultCommonDTO)CommonUtil.makeDTO(map, WorkListPtrlResultCommonDTO.class);
        	
        	switch(workListPointDetailDTO.getNativeeditor())
        	{
        		case "inserted":
        			break;
        		case "updated" : workListPointDetailService.updateDetail(workListPtrlResultCommonDTO, workListPointDetailDTO, user);
        			break;
        		case "deleted":
        			break;
        	}
        	
        }
	}
}
