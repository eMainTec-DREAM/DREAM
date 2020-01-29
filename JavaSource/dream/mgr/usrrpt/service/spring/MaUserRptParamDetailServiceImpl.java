package dream.mgr.usrrpt.service.spring;

import common.bean.User;
import dream.mgr.usrrpt.dao.MaUserRptParamDetailDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptParamDetailDTO;
import dream.mgr.usrrpt.service.MaUserRptParamDetailService;

/**
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maUserRptParamDetailServiceTarget"
 * @spring.txbn id="maUserRptParamDetailService"
 * @spring.property name="maUserRptParamDetailDAO" ref="maUserRptParamDetailDAO"
 */
public class MaUserRptParamDetailServiceImpl implements MaUserRptParamDetailService
{
    private MaUserRptParamDetailDAO maUserRptParamDetailDAO = null;
    
    public MaUserRptParamDetailDAO getMaUserRptParamDetailDAO() 
    {
		return maUserRptParamDetailDAO;
	}

	public void setMaUserRptParamDetailDAO(MaUserRptParamDetailDAO maUserRptParamDetailDAO) 
	{
		this.maUserRptParamDetailDAO = maUserRptParamDetailDAO;
	}

	public MaUserRptParamDetailDTO findDetail(MaUserRptCommonDTO msPtMstrCommonDTO, User loginUser)throws Exception
    {
        return maUserRptParamDetailDAO.findDetail(msPtMstrCommonDTO, loginUser);
    }
    
	public int updateDetail(MaUserRptParamDetailDTO maUserRptParamDetailDTO, User loginUser) throws Exception
    {        
        return maUserRptParamDetailDAO.updateDetail(maUserRptParamDetailDTO, loginUser);
    }
	
	public int insertDetail(MaUserRptParamDetailDTO maUserRptParamDetailDTO, User loginUser) throws Exception
    {   
        return maUserRptParamDetailDAO.insertDetail( maUserRptParamDetailDTO, loginUser);
    }

	public int checkNextNum(String usrrptlistId) {
		return maUserRptParamDetailDAO.checkNextNum(usrrptlistId);
	}
}
