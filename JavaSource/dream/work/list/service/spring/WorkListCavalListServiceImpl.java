package dream.work.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.dao.WorkListCavalListDAO;
import dream.work.list.dto.WorkListCavalListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.service.WorkListCavalListService;

/**
 * 작업상세  - 검교정 - 측정값목록
 * @author kim21017
 * @version $Id: WorkListCavalListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListCavalListServiceTarget"
 * @spring.txbn id="workListCavalListService"
 * @spring.property name="workListCavalListDAO" ref="workListCavalListDAO"
 */
public class WorkListCavalListServiceImpl implements WorkListCavalListService
{
    private WorkListCavalListDAO workListCavalListDAO = null;

    public WorkListCavalListDAO getWorkListCavalListDAO() {
		return workListCavalListDAO;
	}

	public void setWorkListCavalListDAO(WorkListCavalListDAO workListCavalListDAO) {
		this.workListCavalListDAO = workListCavalListDAO;
	}

	public List findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListCavalListDTO workListCavalListDTO, User loginUser)
    {      
        return workListCavalListDAO.findCavalList(maWoResultMstrCommonDTO, workListCavalListDTO, loginUser);
    }
	
	public int deleteCavalList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workListCavalListDAO.deleteCavalList(id, compNo);
            }
        
        return result;
    }

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListCavalListDTO workListCavalListDTO, User loginUser) throws Exception 
	{
		return workListCavalListDAO.findTotalCount(maWoResultMstrCommonDTO, workListCavalListDTO, loginUser);
	}
}

