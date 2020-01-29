package dream.rcm.system.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.system.dao.LovRcmListDAO;
import dream.rcm.system.dto.LovRcmListDTO;
import dream.rcm.system.service.LovRcmListService;

/**
 * ÀÚ»êÆË¾÷ ServiceImpl
 * @author  kim21017
 * @version $Id: LovRcmListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovRcmListServiceTarget"
 * @spring.txbn id="lovRcmListService"
 * @spring.property name="lovRcmListDAO" ref="lovRcmListDAO"
 */
public class LovRcmListServiceImpl implements LovRcmListService
{
    /** ÀÚ»êÆË¾÷ DAO */
    private LovRcmListDAO lovRcmListDAO = null;

    public LovRcmListDAO getLovRcmListDAO() {
		return lovRcmListDAO;
	}

	public void setLovRcmListDAO(LovRcmListDAO lovRcmListDAO) {
		this.lovRcmListDAO = lovRcmListDAO;
	}

	public List findRcmList(LovRcmListDTO lovRcmListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovRcmListDAO.findRcmList(lovRcmListDTO, loginUser);
        
        return resultList;
    }
}