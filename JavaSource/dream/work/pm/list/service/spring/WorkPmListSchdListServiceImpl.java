package dream.work.pm.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmListSchdListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.service.WorkPmListSchdListService;

/**
 * 예방작업 일자 목록
 * @author kim21017
 * @version $Id: WorkPmListSchdListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmListSchdListServiceTarget"
 * @spring.txbn id="workPmListSchdListService"
 * @spring.property name="workPmListSchdListDAO" ref="workPmListSchdListDAO"
 */
public class WorkPmListSchdListServiceImpl implements WorkPmListSchdListService
{
    private WorkPmListSchdListDAO workPmListSchdListDAO = null;


	public List findSchList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {      
        return workPmListSchdListDAO.findSchList(maPmMstrCommonDTO, loginUser);
    }

	public WorkPmListSchdListDAO getWorkPmListSchdListDAO() {
		return workPmListSchdListDAO;
	}

	public void setWorkPmListSchdListDAO(WorkPmListSchdListDAO workPmListSchdListDAO) {
		this.workPmListSchdListDAO = workPmListSchdListDAO;
	}
	
	public int deleteSchList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workPmListSchdListDAO.deleteSchList(id, compNo);
            }
        
        return result;
    }

	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser) throws Exception 
	{
		return workPmListSchdListDAO.findTotalCount(maPmMstrCommonDTO, loginUser);
	}
}

