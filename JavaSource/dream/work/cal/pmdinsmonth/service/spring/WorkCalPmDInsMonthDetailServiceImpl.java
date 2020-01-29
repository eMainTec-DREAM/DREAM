package dream.work.cal.pmdinsmonth.service.spring;

import java.util.ArrayList;
import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.cal.pmdinsmonth.dao.WorkCalPmDInsMonthDetailDAO;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthCommonDTO;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthDetailDTO;
import dream.work.cal.pmdinsmonth.service.WorkCalPmDInsMonthDetailService;
import dream.work.cal.pmdinsmonth.service.WorkCalPmDInsMonthListService;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsDetailDTO;
import dream.work.pm.list.service.WorkPmiDInsDetailService;

/**
 * 월간예방일정 - 상세 serviceimpl
 * @author  kim21017
 * @version $Id: WorkCalPmDInsMonthDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workCalPmDInsMonthDetailServiceTarget"
 * @spring.txbn id="workCalPmDInsMonthDetailService"
 * @spring.property name="workCalPmDInsMonthDetailDAO" ref="workCalPmDInsMonthDetailDAO"
 */
public class WorkCalPmDInsMonthDetailServiceImpl implements WorkCalPmDInsMonthDetailService
{
    private WorkCalPmDInsMonthDetailDAO workCalPmDInsMonthDetailDAO = null;

	public WorkCalPmDInsMonthDetailDAO getWorkCalPmDInsMonthDetailDAO() {
		return workCalPmDInsMonthDetailDAO;
	}

	public void setWorkCalPmDInsMonthDetailDAO(
			WorkCalPmDInsMonthDetailDAO workCalPmDInsMonthDetailDAO) {
		this.workCalPmDInsMonthDetailDAO = workCalPmDInsMonthDetailDAO;
	}

	public WorkCalPmDInsMonthDetailDTO findDetail(String pmInsDSchedId, User user)throws Exception
    {
	    WorkCalPmDInsMonthListService workCalPmDInsMonthListService = (WorkCalPmDInsMonthListService) CommonUtil.getBean("workCalPmDInsMonthListService", user);
	    WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO = new WorkCalPmDInsMonthCommonDTO();
	    workCalPmDInsMonthCommonDTO.setPmInsDSchedId(pmInsDSchedId);
	    return (WorkCalPmDInsMonthDetailDTO) CommonUtil.makeDetailFromList(workCalPmDInsMonthListService.findSchedList(workCalPmDInsMonthCommonDTO, user), new WorkCalPmDInsMonthDetailDTO());
    }

	public int updateDetail(WorkCalPmDInsMonthDetailDTO workCalPmDInsMonthDetailDTO, User user) throws Exception
    {
	    List list = new ArrayList();
	    list.add(workCalPmDInsMonthDetailDTO);
        return this.updateList(list, user)[0];
    }

    @Override
    public int[] updateList(List<WorkCalPmDInsMonthDetailDTO> list, User user) throws Exception
    {
        WorkPmiDInsCommonDTO workPmiDInsCommonDTO;
        WorkPmiDInsDetailDTO workPmiDInsDetailDTO;
        for(WorkCalPmDInsMonthDetailDTO workCalPmDInsMonthDetailDTO:list){
            if("S".equals(workCalPmDInsMonthDetailDTO.getPmSchedStatus())){
                WorkPmiDInsDetailService workPmiDInsDetailService = (WorkPmiDInsDetailService) CommonUtil.getBean("workPmiDInsDetailService", user);
                
                workPmiDInsCommonDTO = new WorkPmiDInsCommonDTO();
                workPmiDInsCommonDTO.setPmInsDListId(workCalPmDInsMonthDetailDTO.getPmInsDListId());
                workPmiDInsDetailDTO = workPmiDInsDetailService.findDetail(workPmiDInsCommonDTO, user);
                
                workPmiDInsDetailDTO.setWkorDate(workCalPmDInsMonthDetailDTO.getSchedDate());
                workPmiDInsDetailDTO.setStartDate(workCalPmDInsMonthDetailDTO.getSchedDate());
                workPmiDInsDetailDTO.setEndDate(workCalPmDInsMonthDetailDTO.getSchedDate());
                workPmiDInsDetailService.updateDetail(workPmiDInsDetailDTO, user);
            }
        }
        
        return workCalPmDInsMonthDetailDAO.update(list, user);
    }
}
