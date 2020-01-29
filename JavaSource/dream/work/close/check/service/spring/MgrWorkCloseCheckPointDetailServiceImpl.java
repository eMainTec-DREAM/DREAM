package dream.work.close.check.service.spring;

import common.bean.User;
import dream.work.close.check.dao.MgrWorkCloseCheckPointDetailDAO;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointDetailDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;
import dream.work.close.check.service.MgrWorkCloseCheckPointDetailService;

/**
 * 표준항목 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="mgrWorkCloseCheckPointDetailServiceTarget"
 * @spring.txbn id="mgrWorkCloseCheckPointDetailService"
 * @spring.property name="mgrWorkCloseCheckPointDetailDAO" ref="mgrWorkCloseCheckPointDetailDAO"
 */
public class MgrWorkCloseCheckPointDetailServiceImpl implements MgrWorkCloseCheckPointDetailService
{
    private MgrWorkCloseCheckPointDetailDAO mgrWorkCloseCheckPointDetailDAO = null;
    
    public MgrWorkCloseCheckPointDetailDAO getMgrWorkCloseCheckPointDetailDAO() 
    {
		return mgrWorkCloseCheckPointDetailDAO;
	}

	public void setMgrWorkCloseCheckPointDetailDAO(MgrWorkCloseCheckPointDetailDAO mgrWorkCloseCheckPointDetailDAO) 
	{
		this.mgrWorkCloseCheckPointDetailDAO = mgrWorkCloseCheckPointDetailDAO;
	}

	public MgrWorkCloseCheckPointDetailDTO findDetail(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO,MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO, User loginUser)throws Exception
    {
        return mgrWorkCloseCheckPointDetailDAO.findDetail(mgrWorkCloseCheckCommonDTO, mgrWorkCloseCheckPointListDTO, loginUser);
    }
    
	public int insertDetail(MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO, MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User loginUser) throws Exception
    {        
        return mgrWorkCloseCheckPointDetailDAO.insertDetail(mgrWorkCloseCheckPointDetailDTO, mgrWorkCloseCheckCommonDTO, loginUser);
    }
	
	public int updateDetail(MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO, MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User loginUser) throws Exception
    {        
        return mgrWorkCloseCheckPointDetailDAO.updateDetail(mgrWorkCloseCheckPointDetailDTO, mgrWorkCloseCheckCommonDTO, loginUser);
    }
	
}
