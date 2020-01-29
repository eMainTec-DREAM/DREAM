package dream.budget.plan.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.budget.plan.list.dao.MaPtBudgetListDAO;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;
import dream.budget.plan.list.service.MaPtBudgetListService;

/**
 * 예산관리 - 목록 serviceimpl
 * @author ssong
 * @version
 * @since 1.0
 * 
 * @spring.bean id="maPtBudgetListServiceTarget"
 * @spring.txbn id="maPtBudgetListService"
 * @spring.property name="maPtBudgetListDAO" ref="maPtBudgetListDAO"
 */
public class MaPtBudgetListServiceImpl implements MaPtBudgetListService
{
    private MaPtBudgetListDAO maPtBudgetListDAO = null;

    public MaPtBudgetListDAO getMaPtBudgetListDAO() 
    {
		return maPtBudgetListDAO;
	}

	public void setMaPtBudgetListDAO(MaPtBudgetListDAO maPtBudgetListDAO) 
	{
		this.maPtBudgetListDAO = maPtBudgetListDAO;
	}

	public List findList(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser)
    {      
        return maPtBudgetListDAO.findList(maPtBudgetCommonDTO, loginUser);
    }

	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
        {
            for(String id : deleteRows)
            {
                
                result = result + maPtBudgetListDAO.deleteHeader(id, loginUser); 
                result = result + maPtBudgetListDAO.deleteDetail(id, loginUser);
            }
        }
        return result;
    }

	@Override
	public String findTotalCount(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User user)
	{
	    return maPtBudgetListDAO.findTotalCount(maPtBudgetCommonDTO, user);
	}
}

