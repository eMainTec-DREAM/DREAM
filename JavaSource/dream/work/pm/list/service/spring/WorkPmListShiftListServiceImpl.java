package dream.work.pm.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmListShiftListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.service.WorkPmListShiftListService;

/**
 * 교대조 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workPmListShiftListServiceTarget"
 * @spring.txbn id="workPmListShiftListService"
 * @spring.property name="workPmListShiftListDAO" ref="workPmListShiftListDAO"
 */
public class WorkPmListShiftListServiceImpl implements WorkPmListShiftListService
{
    private WorkPmListShiftListDAO workPmListShiftListDAO = null;


	public List findShiftList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {      
        return workPmListShiftListDAO.findShiftList(maPmMstrCommonDTO, loginUser);
    }

	public WorkPmListShiftListDAO getWorkPmListShiftListDAO() {
		return workPmListShiftListDAO;
	}

	public void setWorkPmListShiftListDAO(WorkPmListShiftListDAO workPmListShiftListDAO) {
		this.workPmListShiftListDAO = workPmListShiftListDAO;
	}
	
	public int deleteShiftList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workPmListShiftListDAO.deleteShiftList(id, compNo);
            }
        
        return result;
    }
	@Override
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {
        return workPmListShiftListDAO.findTotalCount(maPmMstrCommonDTO, user);
    }
}

