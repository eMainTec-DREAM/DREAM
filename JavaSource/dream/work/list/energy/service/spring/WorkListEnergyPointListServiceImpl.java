package dream.work.list.energy.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.energy.dao.WorkListEnergyPointListDAO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointListDTO;
import dream.work.list.energy.service.WorkListEnergyPointDetailService;
import dream.work.list.energy.service.WorkListEnergyPointListService;

/**
 * 에너지 값 측정항목 목록 ServiceImpl
 * @author sy.yang
 * @version $Id: WorkListEnergyPointListServiceImpl.java,v 1.0 2015/12/02 09:12:51 sy.yang Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListEnergyPointListServiceTarget"
 * @spring.txbn id="workListEnergyPointListService"
 * @spring.property name="workListEnergyPointListDAO" ref="workListEnergyPointListDAO"
 * @spring.property name="workListEnergyPointDetailService" ref="workListEnergyPointDetailService"
 * 
 */
public class WorkListEnergyPointListServiceImpl implements WorkListEnergyPointListService
{
    private WorkListEnergyPointListDAO workListEnergyPointListDAO = null;

    private WorkListEnergyPointDetailService workListEnergyPointDetailService = null;


	public WorkListEnergyPointDetailService getWorkListEnergyPointDetailService() {
		return workListEnergyPointDetailService;
	}

	public void setWorkListEnergyPointDetailService(WorkListEnergyPointDetailService workListEnergyPointDetailService) {
		this.workListEnergyPointDetailService = workListEnergyPointDetailService;
	}

	public List findPointList(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyPointListDTO workListEnergyPointListDTO, User loginUser) throws Exception
    {      
	    List result = workListEnergyPointListDAO.findPointList(workListEnergyMstrListCommonDTO,workListEnergyPointListDTO, loginUser);

        return result;
    }

	public WorkListEnergyPointListDAO getWorkListEnergyPointListDAO() {
		return workListEnergyPointListDAO;
	}

	public void setWorkListEnergyPointListDAO(WorkListEnergyPointListDAO workListEnergyPointListDAO) {
		this.workListEnergyPointListDAO = workListEnergyPointListDAO;
	}

	public String findTotalCount(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyPointListDTO workListEnergyPointListDTO, User loginUser) throws Exception 
	{
	    String cnt = "";
	    if("C".equals(workListEnergyMstrListCommonDTO.getPmschedStatusId())) {
            cnt = workListEnergyPointListDAO.findTotalCount(workListEnergyMstrListCommonDTO, workListEnergyPointListDTO, loginUser, true);
        }
        else {
            cnt = workListEnergyPointListDAO.findTotalCount(workListEnergyMstrListCommonDTO, workListEnergyPointListDTO, loginUser, false);
        }
		return cnt;
	}
}

