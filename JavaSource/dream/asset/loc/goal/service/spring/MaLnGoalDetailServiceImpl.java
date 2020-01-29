package dream.asset.loc.goal.service.spring;

import common.bean.User;
import dream.asset.loc.goal.dao.MaLnGoalDetailDAO;
import dream.asset.loc.goal.dto.MaLnGoalCommonDTO;
import dream.asset.loc.goal.dto.MaLnGoalDetailDTO;
import dream.asset.loc.goal.service.MaLnGoalDetailService;


/**
 * »ó¼¼ serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maLnGoalDetailServiceTarget"
 * @spring.txbn id="maLnGoalDetailService"
 * @spring.property name="maLnGoalDetailDAO" ref="maLnGoalDetailDAO"
 */
public class MaLnGoalDetailServiceImpl implements MaLnGoalDetailService
{
    private MaLnGoalDetailDAO maLnGoalDetailDAO = null;


    public MaLnGoalDetailDAO getMaLnGoalDetailDAO() 
    {
		return maLnGoalDetailDAO;
	}

	public void setMaLnGoalDetailDAO(MaLnGoalDetailDAO maLnGoalDetailDAO) 
	{
		this.maLnGoalDetailDAO = maLnGoalDetailDAO;
	}

	public MaLnGoalDetailDTO findDetail(MaLnGoalCommonDTO maLnGoalCommonDTO, User loginUser)
    {	    
        return maLnGoalDetailDAO.findDetail(maLnGoalCommonDTO, loginUser);
    }

	public int insertDetail(MaLnGoalDetailDTO maLnGoalDetailDTO, User loginUser) throws Exception
    {        
        return maLnGoalDetailDAO.insertDetail(maLnGoalDetailDTO, loginUser);
    }
	
	public int updateDetail(MaLnGoalDetailDTO maLnGoalDetailDTO, User loginUser) throws Exception
    {        
        return maLnGoalDetailDAO.updateDetail(maLnGoalDetailDTO, loginUser);
    }
}
