package dream.mgr.usrrpt.service.spring;

import common.bean.User;
import dream.mgr.usrrpt.dao.MaUserRptColDetailDAO;
import dream.mgr.usrrpt.dto.MaUserRptColDetailDTO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.service.MaUserRptColDetailService;

/**
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maUserRptColDetailServiceTarget"
 * @spring.txbn id="maUserRptColDetailService"
 * @spring.property name="maUserRptColDetailDAO" ref="maUserRptColDetailDAO"
 */
public class MaUserRptColDetailServiceImpl implements MaUserRptColDetailService
{
    private MaUserRptColDetailDAO maUserRptColDetailDAO = null;
    
    public MaUserRptColDetailDAO getMaUserRptColDetailDAO() 
    {
		return maUserRptColDetailDAO;
	}

	public void setMaUserRptColDetailDAO(MaUserRptColDetailDAO maUserRptColDetailDAO) 
	{
		this.maUserRptColDetailDAO = maUserRptColDetailDAO;
	}

	public MaUserRptColDetailDTO findDetail(MaUserRptCommonDTO msPtMstrCommonDTO, User loginUser)throws Exception
    {
        return maUserRptColDetailDAO.findDetail(msPtMstrCommonDTO, loginUser);
    }
    
	public int updateDetail(MaUserRptColDetailDTO maUserRptColDetailDTO, User loginUser) throws Exception
    {        
        return maUserRptColDetailDAO.updateDetail(maUserRptColDetailDTO, loginUser);
    }
	
	public int insertDetail(MaUserRptColDetailDTO maUserRptColDetailDTO, User loginUser) throws Exception
    {   
        return maUserRptColDetailDAO.insertDetail( maUserRptColDetailDTO, loginUser);
    }

	public int checkNextNum(String usrrptlistId) {
		return maUserRptColDetailDAO.checkNextNum(usrrptlistId);
	}
}
