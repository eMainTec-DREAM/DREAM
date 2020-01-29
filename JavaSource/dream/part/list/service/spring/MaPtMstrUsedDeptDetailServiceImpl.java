package dream.part.list.service.spring;

import common.bean.User;
import dream.part.list.dao.MaPtMstrUsedDeptDetailDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrUsedDeptDetailDTO;
import dream.part.list.service.MaPtMstrUsedDeptDetailService;

/**
 * 부품사용부서
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtMstrUsedDeptDetailServiceTarget"
 * @spring.txbn id="maPtMstrUsedDeptDetailService"
 * @spring.property name="maPtMstrUsedDeptDetailDAO" ref="maPtMstrUsedDeptDetailDAO"
 */
public class MaPtMstrUsedDeptDetailServiceImpl implements MaPtMstrUsedDeptDetailService
{
    private MaPtMstrUsedDeptDetailDAO maPtMstrUsedDeptDetailDAO = null;
    
    public MaPtMstrUsedDeptDetailDAO getMaPtMstrUsedDeptDetailDAO() 
    {
		return maPtMstrUsedDeptDetailDAO;
	}

	public void setMaPtMstrUsedDeptDetailDAO(MaPtMstrUsedDeptDetailDAO maPtMstrUsedDeptDetailDAO) 
	{
		this.maPtMstrUsedDeptDetailDAO = maPtMstrUsedDeptDetailDAO;
	}

	public MaPtMstrUsedDeptDetailDTO findDetail(MaPtMstrCommonDTO msPtMstrCommonDTO, User loginUser)throws Exception
    {
        return maPtMstrUsedDeptDetailDAO.findDetail(msPtMstrCommonDTO, loginUser);
    }
    
	public int updateDetail(MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO, User loginUser) throws Exception
    {        
        return maPtMstrUsedDeptDetailDAO.updateDetail(maPtMstrUsedDeptDetailDTO, loginUser);
    }
	
	public int insertDetail(MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO, User loginUser) throws Exception
    {        
        return maPtMstrUsedDeptDetailDAO.insertDetail( maPtMstrUsedDeptDetailDTO, loginUser);
    }
}
