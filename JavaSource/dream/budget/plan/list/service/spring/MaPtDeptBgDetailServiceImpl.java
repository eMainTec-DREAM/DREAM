package dream.budget.plan.list.service.spring;

import common.bean.User;
import dream.budget.plan.list.dao.MaPtDeptBgDetailDAO;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;
import dream.budget.plan.list.dto.MaPtDeptBgDetailDTO;
import dream.budget.plan.list.service.MaPtDeptBgDetailService;

/**
 * 부서별 예산금액
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtDeptBgDetailServiceTarget"
 * @spring.txbn id="maPtDeptBgDetailService"
 * @spring.property name="maPtDeptBgDetailDAO" ref="maPtDeptBgDetailDAO"
 */
public class MaPtDeptBgDetailServiceImpl implements MaPtDeptBgDetailService
{
    private MaPtDeptBgDetailDAO maPtDeptBgDetailDAO = null;
    
    public MaPtDeptBgDetailDAO getMaPtDeptBgDetailDAO() 
    {
		return maPtDeptBgDetailDAO;
	}

	public void setMaPtDeptBgDetailDAO(MaPtDeptBgDetailDAO maPtDeptBgDetailDAO) 
	{
		this.maPtDeptBgDetailDAO = maPtDeptBgDetailDAO;
	}

	public MaPtDeptBgDetailDTO findDetail(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser)throws Exception
    {
		MaPtDeptBgDetailDTO maPtDeptBgDetailDTO;
		if(maPtBudgetCommonDTO.getBgtDeptPlanId() == "")
			maPtDeptBgDetailDTO =  maPtDeptBgDetailDAO.findNewDetail(maPtBudgetCommonDTO, loginUser);
		else
			maPtDeptBgDetailDTO = maPtDeptBgDetailDAO.findDetail(maPtBudgetCommonDTO, loginUser);
		
		return maPtDeptBgDetailDTO;
    }
    
	public int updateDetail(MaPtDeptBgDetailDTO maPtDeptBgDetailDTO, User loginUser) throws Exception
    {        
        return maPtDeptBgDetailDAO.updateVendor(maPtDeptBgDetailDTO, loginUser);
    }
	
	public int insertDetail(MaPtDeptBgDetailDTO maPtDeptBgDetailDTO, User loginUser) throws Exception
    {        
		int result = maPtDeptBgDetailDAO.updateVendor(maPtDeptBgDetailDTO, loginUser);
		if(result == 0)
			maPtDeptBgDetailDAO.insertPtVendor( maPtDeptBgDetailDTO, loginUser);
	    
        return result;
    }
}
