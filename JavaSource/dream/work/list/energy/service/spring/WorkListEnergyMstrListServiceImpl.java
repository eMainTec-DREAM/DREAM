package dream.work.list.energy.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.energy.dao.WorkListEnergyMstrListDAO;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointDetailDTO;
import dream.work.list.energy.service.WorkListEnergyMstrDetailService;
import dream.work.list.energy.service.WorkListEnergyMstrListService;
import dream.work.list.energy.service.WorkListEnergyPointDetailService;

/**
 * 에너지관리 - 에너지값 등록 목록 ServiceImpl
 * @author sy.yang
 * @version $Id: WorkListEnergyMstrListServiceImpl.java,v 1.0 2015/12/02 09:12:51 sy.yang Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListEnergyMstrListServiceTarget"
 * @spring.txbn id="workListEnergyMstrListService"
 * @spring.property name="workListEnergyMstrListDAO" ref="workListEnergyMstrListDAO"
 * @spring.property name="workListEnergyMstrDetailService" ref="workListEnergyMstrDetailService"
 * @spring.property name="workListEnergyPointDetailService" ref="workListEnergyPointDetailService"
 */
public class WorkListEnergyMstrListServiceImpl implements WorkListEnergyMstrListService
{
    private WorkListEnergyMstrListDAO workListEnergyMstrListDAO = null;
    private WorkListEnergyMstrDetailService workListEnergyMstrDetailService = null;
    private WorkListEnergyPointDetailService workListEnergyPointDetailService = null;

    
    public WorkListEnergyMstrListDAO getWorkListEnergyMstrListDAO() {
		return workListEnergyMstrListDAO;
	}
	public void setWorkListEnergyMstrListDAO(WorkListEnergyMstrListDAO workListEnergyMstrListDAO) {
		this.workListEnergyMstrListDAO = workListEnergyMstrListDAO;
	}
	public WorkListEnergyMstrDetailService getWorkListEnergyMstrDetailService() {
		return workListEnergyMstrDetailService;
	}
	public void setWorkListEnergyMstrDetailService(WorkListEnergyMstrDetailService workListEnergyMstrDetailService) {
		this.workListEnergyMstrDetailService = workListEnergyMstrDetailService;
	}
	public WorkListEnergyPointDetailService getWorkListEnergyPointDetailService() {
		return workListEnergyPointDetailService;
	}
	public void setWorkListEnergyPointDetailService(WorkListEnergyPointDetailService workListEnergyPointDetailService) {
		this.workListEnergyPointDetailService = workListEnergyPointDetailService;
	}

	public List findList(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, User user)
    {      
        return workListEnergyMstrListDAO.findList(workListEnergyMstrListCommonDTO,user);
    }
	public int deleteList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
            	//TAPMINSSCHED 삭제
                result = result + workListEnergyMstrListDAO.deleteSchedList(id,compNo);
            	//TAPMINSLIST 점검값 삭제
                result = result + workListEnergyMstrListDAO.deleteList(id,compNo);
            	//TAPMINSPOINT에 측정항목 데이터 삭제
                result = result + workListEnergyMstrListDAO.deletePoint(id,compNo);
            }
        return result;
    }
	
    public String findTotalCount( WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, User user)
    {
        return workListEnergyMstrListDAO.findTotalCount(workListEnergyMstrListCommonDTO,user);
    }
    
	public int insertList(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User loginUser) throws Exception 
	{
        int result = 0;
        
        String[] multiKey = workListEnergyMstrDetailDTO.getMultiKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
        	workListEnergyMstrDetailDTO.setPmId(multiKey[i]);
        	workListEnergyMstrDetailDTO.setPmschedStatusId("P");
        	
        	workListEnergyMstrDetailDTO.setPmInsSchedId(workListEnergyMstrListDAO.getNextSequence("SQAPMINSSCHED_ID"));
        	workListEnergyMstrDetailDTO.setPminslistId(workListEnergyMstrListDAO.getNextSequence("SQAPMINSLIST_ID"));
        	//TAPMINSSCHED 점검일정 생성
        	workListEnergyMstrDetailService.insertEnergyPmInsSched(workListEnergyMstrListCommonDTO, workListEnergyMstrDetailDTO, loginUser);
        	        	
        	//TAPMINSLIST 점검값 저장
        	result = result + workListEnergyMstrDetailService.insertWoEnergyMstrDetail(workListEnergyMstrListCommonDTO, workListEnergyMstrDetailDTO, loginUser);
            
        	//TAPMINSPOINT에 측정항목 데이터 저장
        	WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO = null;
        	workListEnergyPointDetailService.insertEnergyPmPoint(workListEnergyPointDetailDTO, workListEnergyMstrDetailDTO, loginUser);
        }
        
        return result;
	}
}

