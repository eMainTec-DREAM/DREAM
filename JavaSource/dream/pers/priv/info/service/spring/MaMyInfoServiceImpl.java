package dream.pers.priv.info.service.spring;

import dream.pers.priv.info.dao.MaMyInfoDAO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.service.MaMyInfoService;

/**
 * ³»Á¤º¸ serviceimpl
 * @author kim21017
 * @version $Id: MaMyInfoServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maMyInfoServiceTarget"
 * @spring.txbn id="maMyInfoService"
 * @spring.property name="maMyInfoDAO" ref="maMyInfoDAO"
 */
public class MaMyInfoServiceImpl implements MaMyInfoService
{
    private MaMyInfoDAO maMyInfoDAO = null;

    public MaMyInfoDAO getMaMyInfoDAO() {
		return maMyInfoDAO;
	}

	public void setMaMyInfoDAO(MaMyInfoDAO maMyInfoDAO) {
		this.maMyInfoDAO = maMyInfoDAO;
	}

	public MaMyInfoCommonDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO)
    {      
        return maMyInfoDAO.findDetail(maMyInfoCommonDTO);
    }
	
	public int updateDetail(MaMyInfoCommonDTO maMyInfoCommonDTO) throws Exception
    {        
        return maMyInfoDAO.updateDetail(maMyInfoCommonDTO);
    }
	
}

