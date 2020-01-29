package dream.pers.appln.service.spring;

import common.bean.User;

import dream.pers.appln.dao.MaAppLineDetailDAO;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineDetailDTO;
import dream.pers.appln.service.MaAppLineDetailService;

/**
 * »ó¼¼ serviceimpl 
 * @author  kim21017
 * @version $Id: MaAppLineDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maAppLineDetailServiceTarget"
 * @spring.txbn id="maAppLineDetailService"
 * @spring.property name="maAppLineDetailDAO" ref="maAppLineDetailDAO"
 */
public class MaAppLineDetailServiceImpl implements MaAppLineDetailService
{
    private MaAppLineDetailDAO maAppLineDetailDAO = null;
    
    public MaAppLineDetailDAO getMaAppLineDetailDAO() {
		return maAppLineDetailDAO;
	}

	public void setMaAppLineDetailDAO(MaAppLineDetailDAO maAppLineDetailDAO) {
		this.maAppLineDetailDAO = maAppLineDetailDAO;
	}

	public MaAppLineDetailDTO findDetail(MaAppLineCommonDTO maAppLineCommonDTO, User user)throws Exception
    {
        return maAppLineDetailDAO.findDetail(maAppLineCommonDTO, user);
    }
	
	public int updateDetail(MaAppLineDetailDTO maAppLineDetailDTO, User user) throws Exception
    {        
        return maAppLineDetailDAO.updateDetail(maAppLineDetailDTO, user);
    }
	public int confirmDetail(MaAppLineDetailDTO maAppLineDetailDTO, User user) throws Exception
    {        
        return maAppLineDetailDAO.confirmDetail(maAppLineDetailDTO, user);
    }
	public int insertDetail(MaAppLineDetailDTO maAppLineDetailDTO, User user) throws Exception
    {        
        return maAppLineDetailDAO.insertDetail(maAppLineDetailDTO, user);
    }
}
