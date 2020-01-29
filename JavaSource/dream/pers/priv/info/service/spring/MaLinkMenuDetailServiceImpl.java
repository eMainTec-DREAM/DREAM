package dream.pers.priv.info.service.spring;

import common.bean.User;
import dream.pers.priv.info.dao.MaLinkMenuDetailDAO;
import dream.pers.priv.info.dto.MaLinkMenuDetailDTO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.service.MaLinkMenuDetailService;

/**
 * »ó¼¼
 * @author jung7126
 * @version $Id: MaLinkMenuDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maLinkMenuDetailServiceTarget"
 * @spring.txbn id="maLinkMenuDetailService"
 * @spring.property name="maLinkMenuDetailDAO" ref="maLinkMenuDetailDAO"
 */
public class MaLinkMenuDetailServiceImpl implements MaLinkMenuDetailService
{
    private MaLinkMenuDetailDAO maLinkMenuDetailDAO = null;
    
    public MaLinkMenuDetailDAO getMaLinkMenuDetailDAO() {
		return maLinkMenuDetailDAO;
	}

	public void setMaLinkMenuDetailDAO(MaLinkMenuDetailDAO maLinkMenuDetailDAO) {
		this.maLinkMenuDetailDAO = maLinkMenuDetailDAO;
	}

	public MaLinkMenuDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)throws Exception
    {
        return maLinkMenuDetailDAO.findDetail(maMyInfoCommonDTO, user);
    }
    
	public int updateDetail(MaLinkMenuDetailDTO maLinkMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO) throws Exception
    {        
        return maLinkMenuDetailDAO.updateDetail(maLinkMenuDetailDTO, maMyInfoCommonDTO);
    }
	public int insertDetail(MaLinkMenuDetailDTO maLinkMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO) throws Exception
    {        
        return maLinkMenuDetailDAO.insertDetail( maLinkMenuDetailDTO, maMyInfoCommonDTO);
    }
}
