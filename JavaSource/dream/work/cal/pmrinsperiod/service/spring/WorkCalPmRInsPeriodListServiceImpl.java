package dream.work.cal.pmrinsperiod.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.fins.gt.util.StringUtils;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.work.cal.pmrinsperiod.dao.WorkCalPmRInsPeriodListDAO;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodCommonDTO;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodDetailDTO;
import dream.work.cal.pmrinsperiod.service.WorkCalPmRInsPeriodDetailService;
import dream.work.cal.pmrinsperiod.service.WorkCalPmRInsPeriodListService;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.service.WorkPmiListService;

/**
 * 예방작업일정(기간) - 목록 serviceimpl
 * @author kim21017
 * @version $Id: WorkCalPmRInsPeriodListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="workCalPmRInsPeriodListServiceTarget"
 * @spring.txbn id="workCalPmRInsPeriodListService"
 * @spring.property name="workCalPmRInsPeriodListDAO" ref="workCalPmRInsPeriodListDAO"
 */
public class WorkCalPmRInsPeriodListServiceImpl implements WorkCalPmRInsPeriodListService
{
    private WorkCalPmRInsPeriodListDAO workCalPmRInsPeriodListDAO = null;

	public WorkCalPmRInsPeriodListDAO getWorkCalPmRInsPeriodListDAO() {
		return workCalPmRInsPeriodListDAO;
	}

	public void setWorkCalPmRInsPeriodListDAO(
			WorkCalPmRInsPeriodListDAO workCalPmRInsPeriodListDAO) {
		this.workCalPmRInsPeriodListDAO = workCalPmRInsPeriodListDAO;
	}
	
	public List findSchedList(WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO, User user)
    {
        return workCalPmRInsPeriodListDAO.findSchedList(workCalPmRInsPeriodCommonDTO, user);
    }
	
	public int deleteSched(String[] deleteRows, User user) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null) && deleteRows.length>0){
            String pmInsSchedId = StringUtils.join(deleteRows, "+");
            
            //DELETE TAPMINSLIST
            WorkPmiListService workPmiListService = (WorkPmiListService) CommonUtil.getBean("workPmiListService", user);
            WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();
            workPmiCommonDTO.setPminsschedId(pmInsSchedId);
            String[] pmInsListIds = StringUtil.toSingleArray(workPmiListService.findList(workPmiCommonDTO, user), "PMINSLISTID");
            workPmiListService.deleteList(pmInsListIds, user);
            
            //DELETE TAPMINSSCHED
            List list = new ArrayList();
            WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO = new WorkCalPmRInsPeriodDetailDTO();
            for(String id:deleteRows){
                workCalPmRInsPeriodDetailDTO.setPmInsSchedId(id);
                list.add(BeanUtils.cloneBean(workCalPmRInsPeriodDetailDTO));
            }
            result = workCalPmRInsPeriodListDAO.updateDeleteTag(list, user).length;
        }
        return result;
    }
	
    public void updateSchedule(List<Map> gridList, User user) throws Exception
    {
        WorkCalPmRInsPeriodDetailService workCalPmRInsPeriodDetailService = (WorkCalPmRInsPeriodDetailService) CommonUtil.getBean("workCalPmRInsPeriodDetailService", user);
        WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO;
        List listDto = new ArrayList();
        for(Map mapDto : gridList)
        {
            workCalPmRInsPeriodDetailDTO = (WorkCalPmRInsPeriodDetailDTO) CommonUtil.makeDTO(mapDto, WorkCalPmRInsPeriodDetailDTO.class);
            if("".equals(workCalPmRInsPeriodDetailDTO.getSchedDate())) continue;
            listDto.add(BeanUtils.cloneBean(workCalPmRInsPeriodDetailDTO));
        }
        workCalPmRInsPeriodDetailService.updateList(listDto, user);
    }

	@Override
	public String findTotalCount(WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO, User user)
			throws Exception {
		return workCalPmRInsPeriodListDAO.findTotalCount(workCalPmRInsPeriodCommonDTO, user);
	}
}

