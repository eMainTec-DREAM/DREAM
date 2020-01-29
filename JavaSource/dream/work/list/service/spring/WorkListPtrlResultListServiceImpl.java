package dream.work.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.work.list.dao.WorkListPtrlResultListDAO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.service.WorkListPtrlResultListService;

/**
 * 순회점검 작업결과 목록
 * @author youngjoo38
 * @version $Id: WorkListPtrlResultListServiceImpl.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListPtrlResultListServiceTarget"
 * @spring.txbn id="workListPtrlResultListService"
 * @spring.property name="workListPtrlResultListDAO" ref="workListPtrlResultListDAO"
 */
public class WorkListPtrlResultListServiceImpl implements WorkListPtrlResultListService
{
    private WorkListPtrlResultListDAO workListPtrlResultListDAO = null;

    public WorkListPtrlResultListDAO getWorkListPtrlResultListDAO() {
		return workListPtrlResultListDAO;
	}

	public void setWorkListPtrlResultListDAO(WorkListPtrlResultListDAO workListPtrlResultListDAO) {
		this.workListPtrlResultListDAO = workListPtrlResultListDAO;
	}

	public List findList(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,User loginUser) throws Exception
    {      
        return workListPtrlResultListDAO.findList(workListPtrlResultCommonDTO, loginUser);
    }
    
	public String findTotalCount(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,User user)  throws Exception
    {
        return workListPtrlResultListDAO.findTotalCount(workListPtrlResultCommonDTO, user);
    }
    
	@Override
	public int deleteList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workListPtrlResultListDAO.deleteList(id, compNo);
            }
        
        return result;
    }
}

