package dream.work.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.dao.WorkListGnlCaEqListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.service.WorkListGnlCaEqListService;

/**
 * 작업상세  - 검교정 - 표준기 목록
 * @author kim21017
 * @version $Id: WorkListGnlCaEqListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListGnlCaEqListServiceTarget"
 * @spring.txbn id="workListGnlCaEqListService"
 * @spring.property name="workListGnlCaEqListDAO" ref="workListGnlCaEqListDAO"
 */
public class WorkListGnlCaEqListServiceImpl implements WorkListGnlCaEqListService
{
    private WorkListGnlCaEqListDAO workListGnlCaEqListDAO = null;

    public WorkListGnlCaEqListDAO getWorkListGnlCaEqListDAO() {
		return workListGnlCaEqListDAO;
	}

	public void setWorkListGnlCaEqListDAO(WorkListGnlCaEqListDAO workListGnlCaEqListDAO) {
		this.workListGnlCaEqListDAO = workListGnlCaEqListDAO;
	}

	public List findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User loginUser)
    {      
        return workListGnlCaEqListDAO.findCavalList(maWoResultMstrCommonDTO, loginUser);
    }
	
	public int deleteCavalList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workListGnlCaEqListDAO.deleteCavalList(id, compNo);
            }
        
        return result;
    }

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser) throws Exception 
	{
		return workListGnlCaEqListDAO.findTotalCount(maWoResultMstrCommonDTO, loginUser);
	}
}

