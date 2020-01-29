package dream.consult.program.wrkimp.service.spring;

import common.bean.User;
import dream.consult.program.wrkimp.dao.MaWrkimpDetailDAO;
import dream.consult.program.wrkimp.dto.MaWrkimpCommonDTO;
import dream.consult.program.wrkimp.dto.MaWrkimpDetailDTO;
import dream.consult.program.wrkimp.service.MaWrkimpDetailService;

/**
 * »ó¼¼ serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maWrkimpDetailServiceTarget"
 * @spring.txbn id="maWrkimpDetailService"
 * @spring.property name="maWrkimpDetailDAO" ref="maWrkimpDetailDAO"
 */
public class MaWrkimpDetailServiceImpl implements MaWrkimpDetailService
{
    private MaWrkimpDetailDAO maWrkimpDetailDAO = null;
    
    public MaWrkimpDetailDAO getMaWrkimpDetailDAO() 
    {
		return maWrkimpDetailDAO;
	}

	public void setMaWrkimpDetailDAO(MaWrkimpDetailDAO maWrkimpDetailDAO) 
	{
		this.maWrkimpDetailDAO = maWrkimpDetailDAO;
	}

	public MaWrkimpDetailDTO findDetail(MaWrkimpCommonDTO maWrkimpCommonDTO, User user)throws Exception
    {
        return maWrkimpDetailDAO.findDetail(maWrkimpCommonDTO, user);
    }
    
	public int insertDetail(MaWrkimpDetailDTO maWrkimpDetailDTO, User user) throws Exception
    {        
        return maWrkimpDetailDAO.insertDetail(maWrkimpDetailDTO, user);
    }
	
	public int updateDetail(MaWrkimpDetailDTO maWrkimpDetailDTO, User user) throws Exception
    {        
		maWrkimpDetailDAO.updateHelpDetail(maWrkimpDetailDTO, user);
		
        return maWrkimpDetailDAO.updateDetail(maWrkimpDetailDTO, user);
    }
}
