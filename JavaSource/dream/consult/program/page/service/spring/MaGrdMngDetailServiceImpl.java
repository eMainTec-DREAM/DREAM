package dream.consult.program.page.service.spring;

import common.bean.User;
import dream.consult.program.page.dao.MaGrdMngDetailDAO;
import dream.consult.program.page.dto.MaGrdMngDetailDTO;
import dream.consult.program.page.service.MaGrdMngDetailService;

/**
 * »ó¼¼ serviceimpl 
 * @author  jung7126
 * @version $Id: MaGrdMngDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maGrdMngDetailServiceTarget"
 * @spring.txbn id="maGrdMngDetailService"
 * @spring.property name="maGrdMngDetailDAO" ref="maGrdMngDetailDAO"
 */
public class MaGrdMngDetailServiceImpl implements MaGrdMngDetailService
{
    private MaGrdMngDetailDAO maGrdMngDetailDAO = null;
    
    public MaGrdMngDetailDAO getMaGrdMngDetailDAO() {
		return maGrdMngDetailDAO;
	}

	public void setMaGrdMngDetailDAO(MaGrdMngDetailDAO maGrdMngDetailDAO) {
		this.maGrdMngDetailDAO = maGrdMngDetailDAO;
	}

	public MaGrdMngDetailDTO findDetail(String pageId, User user)throws Exception
    {
        return maGrdMngDetailDAO.findDetail(pageId, user);
    }
	
	public int insertDetail(MaGrdMngDetailDTO maGrdMngDetailDTO) throws Exception
    {        
        return maGrdMngDetailDAO.insertDetail(maGrdMngDetailDTO);
    }
	
	public int updateDetail(MaGrdMngDetailDTO maGrdMngDetailDTO) throws Exception
    {        
        return maGrdMngDetailDAO.updateDetail(maGrdMngDetailDTO);
    }
}
