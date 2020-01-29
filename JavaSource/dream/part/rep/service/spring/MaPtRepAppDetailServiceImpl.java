package dream.part.rep.service.spring;

import common.bean.User;
import dream.part.rep.dao.MaPtRepAppDetailDAO;
import dream.part.rep.dto.MaPtRepAppDetailDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.service.MaPtRepAppDetailService;

/**
 * 수리기안 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtRepAppDetailServiceTarget"
 * @spring.txbn id="maPtRepAppDetailService"
 * @spring.property name="maPtRepAppDetailDAO" ref="maPtRepAppDetailDAO"
 */
public class MaPtRepAppDetailServiceImpl implements MaPtRepAppDetailService
{
    private MaPtRepAppDetailDAO maPtRepAppDetailDAO = null;
    
    public MaPtRepAppDetailDAO getMaPtRepAppDetailDAO() 
    {
		return maPtRepAppDetailDAO;
	}

	public void setMaPtRepAppDetailDAO(MaPtRepAppDetailDAO maPtRepAppDetailDAO) 
	{
		this.maPtRepAppDetailDAO = maPtRepAppDetailDAO;
	}

	public MaPtRepAppDetailDTO findDetail(MaPtRepCommonDTO maPtRepCommonDTO, User loginUser)throws Exception
    {
        return maPtRepAppDetailDAO.findDetail(maPtRepCommonDTO, loginUser);
    }
	
	public int insertDetail(MaPtRepAppDetailDTO maPtRepAppDetailDTO, User loginUser) throws Exception
    {   
        return maPtRepAppDetailDAO.insertDetail(maPtRepAppDetailDTO, loginUser);
    }
	
	public int updateDetail(MaPtRepAppDetailDTO maPtRepAppDetailDTO, User loginUser) throws Exception
    {        
        return maPtRepAppDetailDAO.updateDetail(maPtRepAppDetailDTO, loginUser);
    }
    
}
