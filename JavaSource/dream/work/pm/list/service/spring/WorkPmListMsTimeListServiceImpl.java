package dream.work.pm.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmListMsTimeListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeDetailDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeListDTO;
import dream.work.pm.list.service.WorkPmListMsTimeDetailService;
import dream.work.pm.list.service.WorkPmListMsTimeListService;

/**
 * 예방설비 목록
 * @author kim21017
 * @version $Id: WorkPmListMsTimeListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmListMsTimeListServiceTarget"
 * @spring.txbn id="workPmListMsTimeListService"
 * @spring.property name="workPmListMsTimeListDAO" ref="workPmListMsTimeListDAO"
 * @spring.property name="workPmListMsTimeDetailService" ref="workPmListMsTimeDetailService"
 */
public class WorkPmListMsTimeListServiceImpl implements WorkPmListMsTimeListService
{
    private WorkPmListMsTimeListDAO workPmListMsTimeListDAO = null;
    private WorkPmListMsTimeDetailService workPmListMsTimeDetailService = null;

	public WorkPmListMsTimeDetailService getWorkPmListMsTimeDetailService() {
		return workPmListMsTimeDetailService;
	}

	public void setWorkPmListMsTimeDetailService(WorkPmListMsTimeDetailService workPmListMsTimeDetailService) {
		this.workPmListMsTimeDetailService = workPmListMsTimeDetailService;
	}

	public List findMsTimeList(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmListMsTimeListDTO workPmListMsTimeListDTO, User loginUser)
    {      
        return workPmListMsTimeListDAO.findMsTimeList(maPmMstrCommonDTO, workPmListMsTimeListDTO, loginUser);
    }

	public WorkPmListMsTimeListDAO getWorkPmListMsTimeListDAO() {
		return workPmListMsTimeListDAO;
	}

	public void setWorkPmListMsTimeListDAO(WorkPmListMsTimeListDAO workPmListMsTimeListDAO) {
		this.workPmListMsTimeListDAO = workPmListMsTimeListDAO;
	}
	
	public int deleteMsTimeList(String[] deleteRows, User loginUser) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
            	result = result + workPmListMsTimeListDAO.deleteMsTimeList(id, loginUser);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmListMsTimeListDTO workPmListMsTimeListDTO, User user) throws Exception {
		return workPmListMsTimeListDAO.findTotalCount(maPmMstrCommonDTO,workPmListMsTimeListDTO, user);
	}

	@Override
	public int inputMsTimeList(WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception {

        int result = 0;
        
        String[] multiKey = workPmListMsTimeDetailDTO.getMultiMsTimeKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
        	workPmListMsTimeDetailDTO.setMeasureTimeId(multiKey[i]);
        	String cnt = workPmListMsTimeDetailService.validTime(workPmListMsTimeDetailDTO, maPmMstrCommonDTO, user);
        	if ("0".equals(cnt)) {
        		workPmListMsTimeDetailDTO.setPmMsTimeId(workPmListMsTimeListDAO.getNextSequence("SQAPMMEASURETIME_ID"));
        		result = result + workPmListMsTimeDetailService.insertDetail(workPmListMsTimeDetailDTO, maPmMstrCommonDTO, user);
			}
        }
        
        return result;
		
	}
}

