package dream.work.cal.pmrinsperiod.service.spring;

import java.util.ArrayList;
import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.cal.pmrinsperiod.dao.WorkCalPmRInsPeriodDetailDAO;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodCommonDTO;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodDetailDTO;
import dream.work.cal.pmrinsperiod.service.WorkCalPmRInsPeriodDetailService;
import dream.work.cal.pmrinsperiod.service.WorkCalPmRInsPeriodListService;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiDetailDTO;
import dream.work.pmi.list.service.WorkPmiDetailService;

/**
 * 예방작업일정(기간) - 상세 serviceimpl
 * @author  kim21017
 * @version $Id: WorkCalPmRInsPeriodDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workCalPmRInsPeriodDetailServiceTarget"
 * @spring.txbn id="workCalPmRInsPeriodDetailService"
 * @spring.property name="workCalPmRInsPeriodDetailDAO" ref="workCalPmRInsPeriodDetailDAO"
 */
public class WorkCalPmRInsPeriodDetailServiceImpl implements WorkCalPmRInsPeriodDetailService
{
    private WorkCalPmRInsPeriodDetailDAO workCalPmRInsPeriodDetailDAO = null;

    

	public WorkCalPmRInsPeriodDetailDAO getWorkCalPmRInsPeriodDetailDAO() {
		return workCalPmRInsPeriodDetailDAO;
	}

	public void setWorkCalPmRInsPeriodDetailDAO(
			WorkCalPmRInsPeriodDetailDAO workCalPmRInsPeriodDetailDAO) {
		this.workCalPmRInsPeriodDetailDAO = workCalPmRInsPeriodDetailDAO;
	}

	public WorkCalPmRInsPeriodDetailDTO findDetail(String pmSchedId, User user)throws Exception
    {
	    WorkCalPmRInsPeriodListService workCalPmRInsPeriodListService = (WorkCalPmRInsPeriodListService) CommonUtil.getBean("workCalPmRInsPeriodListService", user);
	    WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO = new WorkCalPmRInsPeriodCommonDTO();
	    workCalPmRInsPeriodCommonDTO.setPmInsSchedId(pmSchedId);
        return (WorkCalPmRInsPeriodDetailDTO)CommonUtil.makeDetailFromList(workCalPmRInsPeriodListService.findSchedList(workCalPmRInsPeriodCommonDTO, user), new WorkCalPmRInsPeriodDetailDTO());
    }

	@Override
    public int[] updateList(List<WorkCalPmRInsPeriodDetailDTO> list, User user) throws Exception
    {
	    WorkPmiCommonDTO workPmiCommonDTO;
	    WorkPmiDetailDTO workPmiDetailDTO;
	    for(WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO:list){
	        if("S".equals(workCalPmRInsPeriodDetailDTO.getPmSchedStatus())){
	            WorkPmiDetailService workPmiDetailService = (WorkPmiDetailService) CommonUtil.getBean("workPmiDetailService", user);
	            
	            workPmiCommonDTO = new WorkPmiCommonDTO();
	            workPmiCommonDTO.setPminslistId(workCalPmRInsPeriodDetailDTO.getPmInsListId());
	            workPmiDetailDTO = workPmiDetailService.findDetail(workPmiCommonDTO, user);
	            
	            workPmiDetailDTO.setWkorDate(workCalPmRInsPeriodDetailDTO.getSchedDate());
	            workPmiDetailDTO.setStartDate(workCalPmRInsPeriodDetailDTO.getSchedDate());
	            workPmiDetailDTO.setEndDate(workCalPmRInsPeriodDetailDTO.getSchedDate());
	            workPmiDetailService.updateDetail(workPmiDetailDTO, user);
	        }
	    }
	    
        return workCalPmRInsPeriodDetailDAO.update(list, user);
    }
	
	public int updateDetail(WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO, User user) throws Exception
    {
	    List<WorkCalPmRInsPeriodDetailDTO> workCalPmRInsPeriodDetailDTOList = new ArrayList<WorkCalPmRInsPeriodDetailDTO>();
	    workCalPmRInsPeriodDetailDTOList.add(workCalPmRInsPeriodDetailDTO);
        return this.updateList(workCalPmRInsPeriodDetailDTOList, user)[0];
    }
	
	public int inputDetail(WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO, User user) throws Exception
    {
        return workCalPmRInsPeriodDetailDAO.inputDetail(workCalPmRInsPeriodDetailDTO, user);
    }
}
