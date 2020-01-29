package dream.budget.plan.list.service.spring;

import common.bean.User;
import dream.budget.plan.list.dao.MaPtBudgetDetailDAO;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;
import dream.budget.plan.list.dto.MaPtBudgetDetailDTO;
import dream.budget.plan.list.service.MaPtBudgetDetailService;

/**
 * 예산관리 - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maPtBudgetDetailServiceTarget"
 * @spring.txbn id="maPtBudgetDetailService"
 * @spring.property name="maPtBudgetDetailDAO" ref="maPtBudgetDetailDAO"
 */
public class MaPtBudgetDetailServiceImpl implements MaPtBudgetDetailService
{
    private MaPtBudgetDetailDAO maPtBudgetDetailDAO = null;


    public MaPtBudgetDetailDAO getMaPtBudgetDetailDAO() 
    {
		return maPtBudgetDetailDAO;
	}

	public void setMaPtBudgetDetailDAO(MaPtBudgetDetailDAO maPtBudgetDetailDAO) 
	{
		this.maPtBudgetDetailDAO = maPtBudgetDetailDAO;
	}

	public MaPtBudgetDetailDTO findDetail(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser)
    {	    
        return maPtBudgetDetailDAO.findDetail(maPtBudgetCommonDTO, loginUser);
    }

	public int insertDetail(MaPtBudgetDetailDTO maPtBudgetDetailDTO, User loginUser) throws Exception
    {        
        return maPtBudgetDetailDAO.insertDetail(maPtBudgetDetailDTO, loginUser);
    }
	
	public int updateDetail(MaPtBudgetDetailDTO maPtBudgetDetailDTO, User loginUser) throws Exception
    {        
        return maPtBudgetDetailDAO.updateDetail(maPtBudgetDetailDTO, loginUser);
    }
}
