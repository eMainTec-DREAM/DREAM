package dream.work.pm.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmMsTimeUInsListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsDetailDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsListDTO;
import dream.work.pm.list.service.WorkPmMsTimeUInsDetailService;
import dream.work.pm.list.service.WorkPmMsTimeUInsListService;

/**
 * 예방설비 목록
 * @author kim21017
 * @version $Id: WorkPmMsTimeUInsListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmMsTimeUInsListServiceTarget"
 * @spring.txbn id="workPmMsTimeUInsListService"
 * @spring.property name="workPmMsTimeUInsListDAO" ref="workPmMsTimeUInsListDAO"
 * @spring.property name="workPmMsTimeUInsDetailService" ref="workPmMsTimeUInsDetailService"
 */
public class WorkPmMsTimeUInsListServiceImpl implements WorkPmMsTimeUInsListService
{
    private WorkPmMsTimeUInsListDAO workPmMsTimeUInsListDAO = null;
    private WorkPmMsTimeUInsDetailService workPmMsTimeUInsDetailService = null;

	public WorkPmMsTimeUInsDetailService getWorkPmMsTimeUInsDetailService() {
		return workPmMsTimeUInsDetailService;
	}

	public void setWorkPmMsTimeUInsDetailService(WorkPmMsTimeUInsDetailService workPmMsTimeUInsDetailService) {
		this.workPmMsTimeUInsDetailService = workPmMsTimeUInsDetailService;
	}

	public List findMsTimeList(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO, User loginUser)
    {      
        return workPmMsTimeUInsListDAO.findMsTimeList(maPmMstrCommonDTO, workPmMsTimeUInsListDTO, loginUser);
    }

	public WorkPmMsTimeUInsListDAO getWorkPmMsTimeUInsListDAO() {
		return workPmMsTimeUInsListDAO;
	}

	public void setWorkPmMsTimeUInsListDAO(WorkPmMsTimeUInsListDAO workPmMsTimeUInsListDAO) {
		this.workPmMsTimeUInsListDAO = workPmMsTimeUInsListDAO;
	}
	
	public int deleteMsTimeList(String[] deleteRows, User loginUser) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
            	result = result + workPmMsTimeUInsListDAO.deleteMsTimeList(id, loginUser);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO, User user) throws Exception {
		return workPmMsTimeUInsListDAO.findTotalCount(maPmMstrCommonDTO,workPmMsTimeUInsListDTO, user);
	}

	@Override
	public int inputMsTimeList(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception {

        int result = 0;
        
        String[] multiKey = workPmMsTimeUInsDetailDTO.getMultiMsTimeKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
        	workPmMsTimeUInsDetailDTO.setMeasureTimeId(multiKey[i]);
        	String cnt = workPmMsTimeUInsDetailService.validTime(workPmMsTimeUInsDetailDTO, maPmMstrCommonDTO, user);
        	if ("0".equals(cnt)) {
        		workPmMsTimeUInsDetailDTO.setPmMsTimeId(workPmMsTimeUInsListDAO.getNextSequence("SQAPMMEASURETIME_ID"));
        		result = result + workPmMsTimeUInsDetailService.insertDetail(workPmMsTimeUInsDetailDTO, maPmMstrCommonDTO, user);
			}
        }
        
        return result;
		
	}
}

