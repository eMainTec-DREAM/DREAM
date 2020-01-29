package dream.mgr.usrrpt.service.spring;

import common.bean.User;
import dream.mgr.usrrpt.dao.MaUserRptOrdDetailDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptOrdDetailDTO;
import dream.mgr.usrrpt.service.MaUserRptOrdDetailService;

/**
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maUserRptOrdDetailServiceTarget"
 * @spring.txbn id="maUserRptOrdDetailService"
 * @spring.property name="maUserRptOrdDetailDAO" ref="maUserRptOrdDetailDAO"
 */
public class MaUserRptOrdDetailServiceImpl implements MaUserRptOrdDetailService
{
    private MaUserRptOrdDetailDAO maUserRptOrdDetailDAO = null;
    
    public MaUserRptOrdDetailDAO getMaUserRptOrdDetailDAO() 
    {
		return maUserRptOrdDetailDAO;
	}

	public void setMaUserRptOrdDetailDAO(MaUserRptOrdDetailDAO maUserRptOrdDetailDAO) 
	{
		this.maUserRptOrdDetailDAO = maUserRptOrdDetailDAO;
	}

	public MaUserRptOrdDetailDTO findDetail(MaUserRptCommonDTO msPtMstrCommonDTO, User loginUser)throws Exception
    {
        return maUserRptOrdDetailDAO.findDetail(msPtMstrCommonDTO, loginUser);
    }
    
	public int updateDetail(MaUserRptOrdDetailDTO maUserRptOrdDetailDTO, User loginUser) throws Exception
    {        
        return maUserRptOrdDetailDAO.updateDetail(maUserRptOrdDetailDTO, loginUser);
    }
	
	public int insertDetail(MaUserRptOrdDetailDTO maUserRptOrdDetailDTO, User loginUser) throws Exception
    {   
        return maUserRptOrdDetailDAO.insertDetail( maUserRptOrdDetailDTO, loginUser);
    }

	public int checkNextNum(String usrrptlistId) {
		return maUserRptOrdDetailDAO.checkNextNum(usrrptlistId);
	}
}
