package dream.work.pm.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dao.WorkPmPointUInsListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.service.WorkPmPointUInsListService;

/**
 * 사용량 항목 - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workPmPointUInsListServiceTarget"
 * @spring.txbn id="workPmPointUInsListService"
 * @spring.property name="workPmPointUInsListDAO" ref="workPmPointUInsListDAO"
 */
public class WorkPmPointUInsListServiceImpl implements WorkPmPointUInsListService
{
	private WorkPmPointUInsListDAO workPmPointUInsListDAO = null;

	public WorkPmPointUInsListDAO getWorkPmPointUInsListDAO() {
		return workPmPointUInsListDAO;
	}

	public void setWorkPmPointUInsListDAO(WorkPmPointUInsListDAO workPmPointUInsListDAO) {
		this.workPmPointUInsListDAO = workPmPointUInsListDAO;
	}
	
	public List findList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception
    {      
        return workPmPointUInsListDAO.findList(maPmMstrCommonDTO,user);
    }

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workPmPointUInsListDAO.deleteList(id, user);
            }
        return result;
    }

	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO,User user) throws Exception
    {
        return workPmPointUInsListDAO.findTotalCount(maPmMstrCommonDTO, user);
    }
}

