package dream.pers.priv.info.service.spring;

import common.bean.User;
import dream.pers.priv.info.dao.MaMyMenuDetailDAO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.MaMyMenuDetailDTO;
import dream.pers.priv.info.service.MaMyMenuDetailService;

/**
 * »ó¼¼
 * @author jung7126
 * @version $Id: MaMyMenuDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maMyMenuDetailServiceTarget"
 * @spring.txbn id="maMyMenuDetailService"
 * @spring.property name="maMyMenuDetailDAO" ref="maMyMenuDetailDAO"
 */
public class MaMyMenuDetailServiceImpl implements MaMyMenuDetailService
{
    private MaMyMenuDetailDAO maMyMenuDetailDAO = null;
    
    public MaMyMenuDetailDAO getMaMyMenuDetailDAO() {
		return maMyMenuDetailDAO;
	}

	public void setMaMyMenuDetailDAO(MaMyMenuDetailDAO maMyMenuDetailDAO) {
		this.maMyMenuDetailDAO = maMyMenuDetailDAO;
	}

	public MaMyMenuDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)throws Exception
    {
        return maMyMenuDetailDAO.findDetail(maMyInfoCommonDTO, user);
    }
    
	public int updateDetail(MaMyMenuDetailDTO maMyMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO) throws Exception
    {        
        return maMyMenuDetailDAO.updateDetail(maMyMenuDetailDTO, maMyInfoCommonDTO);
    }
	public int insertDetail(MaMyMenuDetailDTO maMyMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO) throws Exception
    {        
        return maMyMenuDetailDAO.insertDetail( maMyMenuDetailDTO, maMyInfoCommonDTO);
    }
}
