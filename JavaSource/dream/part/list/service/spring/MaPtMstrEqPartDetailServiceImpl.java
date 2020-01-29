package dream.part.list.service.spring;

import common.bean.User;
import dream.part.list.dao.MaPtMstrEqPartDetailDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrEqPartDetailDTO;
import dream.part.list.service.MaPtMstrEqPartDetailService;

/**
 * 부품사용설비
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtMstrEqPartDetailServiceTarget"
 * @spring.txbn id="maPtMstrEqPartDetailService"
 * @spring.property name="maPtMstrEqPartDetailDAO" ref="maPtMstrEqPartDetailDAO"
 */
public class MaPtMstrEqPartDetailServiceImpl implements MaPtMstrEqPartDetailService
{
    private MaPtMstrEqPartDetailDAO maPtMstrEqPartDetailDAO = null;
    
    public MaPtMstrEqPartDetailDAO getMaPtMstrEqPartDetailDAO() 
    {
		return maPtMstrEqPartDetailDAO;
	}

	public void setMaPtMstrEqPartDetailDAO(MaPtMstrEqPartDetailDAO maPtMstrEqPartDetailDAO) 
	{
		this.maPtMstrEqPartDetailDAO = maPtMstrEqPartDetailDAO;
	}

	public MaPtMstrEqPartDetailDTO findDetail(MaPtMstrCommonDTO msPtMstrCommonDTO, User loginUser)throws Exception
    {
        return maPtMstrEqPartDetailDAO.findDetail(msPtMstrCommonDTO, loginUser);
    }
    
	public int updateDetail(MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO, User loginUser) throws Exception
    {        
        return maPtMstrEqPartDetailDAO.updateDetail(maPtMstrEqPartDetailDTO, loginUser);
    }
	
	public int insertDetail(MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO, User loginUser) throws Exception
    {        
        return maPtMstrEqPartDetailDAO.insertDetail( maPtMstrEqPartDetailDTO, loginUser);
    }
}
