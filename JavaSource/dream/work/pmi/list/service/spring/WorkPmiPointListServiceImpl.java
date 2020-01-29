package dream.work.pmi.list.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.pmi.list.dao.WorkPmiPointListDAO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointDetailDTO;
import dream.work.pmi.list.dto.WorkPmiPointListDTO;
import dream.work.pmi.list.service.WorkPmiPointDetailService;
import dream.work.pmi.list.service.WorkPmiPointListService;

/**
 * 점검작업 점검 목록
 * @author kim21017
 * @version $Id: WorkPmiPointListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmiPointListServiceTarget"
 * @spring.txbn id="workPmiPointListService"
 * @spring.property name="workPmiPointListDAO" ref="workPmiPointListDAO"
 * @spring.property name="workPmiPointDetailService" ref="workPmiPointDetailService"
 * 
 */
public class WorkPmiPointListServiceImpl implements WorkPmiPointListService
{
    private WorkPmiPointListDAO workPmiPointListDAO = null;

    private WorkPmiPointDetailService workPmiPointDetailService = null;


	public WorkPmiPointDetailService getWorkPmiPointDetailService() {
		return workPmiPointDetailService;
	}

	public void setWorkPmiPointDetailService(WorkPmiPointDetailService workPmiPointDetailService) {
		this.workPmiPointDetailService = workPmiPointDetailService;
	}

	public List findPointList(WorkPmiCommonDTO workPmiCommonDTO, WorkPmiPointListDTO workPmiPointListDTO, User loginUser) throws Exception
    {      
	    List result = null;
	    if("C".equals(workPmiCommonDTO.getPmschedStatusId())) {
	        result = workPmiPointListDAO.findPointList(workPmiCommonDTO,workPmiPointListDTO, loginUser, true);
	    }
	    else {
	        result = workPmiPointListDAO.findPointList(workPmiCommonDTO,workPmiPointListDTO, loginUser, false);
	    }
        return result;
    }

	public WorkPmiPointListDAO getWorkPmiPointListDAO() {
		return workPmiPointListDAO;
	}

	public void setWorkPmiPointListDAO(WorkPmiPointListDAO workPmiPointListDAO) {
		this.workPmiPointListDAO = workPmiPointListDAO;
	}
	
	public int deletePointList(String[] deleteRows, User user) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null) && deleteRows.length>0){
            List list = new ArrayList();
            WorkPmiPointDetailDTO workPmiPointDetailDTO = new WorkPmiPointDetailDTO();
            for(String id : deleteRows)
            {
                workPmiPointDetailDTO.setPmInsPointId(id);
                list.add(BeanUtils.cloneBean(workPmiPointDetailDTO));
            }
            result = workPmiPointListDAO.deletePointList(list, user).length;
        }
        
        return result;
    }

	@Override
	public void savePointList(List<Map> gridList, User user) throws Exception 
	{
		WorkPmiPointDetailDTO workPmiPointDetailDTO = null;
		WorkPmiCommonDTO workPmiCommonDTO = null;
		
        for(Map map : gridList)
        {
        	workPmiPointDetailDTO = (WorkPmiPointDetailDTO)CommonUtil.makeDTO(map, WorkPmiPointDetailDTO.class);
        	workPmiCommonDTO = (WorkPmiCommonDTO)CommonUtil.makeDTO(map, WorkPmiCommonDTO.class);
        	
        	switch(workPmiPointDetailDTO.getNativeeditor())
        	{
        		case "inserted":
        			break;
        		case "updated" : workPmiPointDetailService.updateDetail(workPmiPointDetailDTO, workPmiCommonDTO, user);
        			break;
        		case "deleted": String[] deleteRows = {workPmiPointDetailDTO.getPmInsPointId()};
        		    this.deletePointList(deleteRows, user);
        			break;
        	}
        	
        }
	}

	public String findTotalCount(WorkPmiCommonDTO workPmiCommonDTO, WorkPmiPointListDTO workPmiPointListDTO, User loginUser) throws Exception 
	{
	    String cnt = "";
	    if("C".equals(workPmiCommonDTO.getPmschedStatusId())) {
            cnt = workPmiPointListDAO.findTotalCount(workPmiCommonDTO, workPmiPointListDTO, loginUser, true);
        }
        else {
            cnt = workPmiPointListDAO.findTotalCount(workPmiCommonDTO, workPmiPointListDTO, loginUser, false);
        }
		return cnt;
	}
}

