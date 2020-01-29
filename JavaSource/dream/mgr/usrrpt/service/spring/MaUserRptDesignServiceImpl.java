package dream.mgr.usrrpt.service.spring;

import common.bean.User;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrrpt.dao.MaUserRptDesignDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.service.MaUserRptDesignService;



/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id: MaUserRptListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maUserRptDesignServiceTarget"
 * @spring.txbn id="maUserRptDesignService"
 * @spring.property name="maUserRptDesignDAO" ref="maUserRptDesignDAO"
 */
public class MaUserRptDesignServiceImpl implements MaUserRptDesignService
{
    private MaUserRptDesignDAO maUserRptDesignDAO = null;

    public MaUserRptDesignDAO getMaUserRptDesignDAO() {
		return maUserRptDesignDAO;
	}

	public void setMaUserRptDesignDAO(MaUserRptDesignDAO maUserRptDesignDAO) {
		this.maUserRptDesignDAO = maUserRptDesignDAO;
	}

	public int updateScript(MaUserRptCommonDTO mcDataSelectCommonDTO, User loginUser)
    {      
        return maUserRptDesignDAO.updateScript(mcDataSelectCommonDTO, loginUser);
    }

	public MaUserRptCommonDTO findScript(MaUserRptCommonDTO maUserRptCommonDTO) {
		return maUserRptDesignDAO.findScript(maUserRptCommonDTO);
	}
}

