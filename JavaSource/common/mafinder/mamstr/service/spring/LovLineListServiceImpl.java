package common.mafinder.mamstr.service.spring;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dao.LovLineListDAO;
import common.mafinder.mamstr.dto.LovLineListDTO;
import common.mafinder.mamstr.service.LovLineListService;

/**
 * 무정지라인팝업 ServiceImpl
 * @author  kim21017
 * @version $Id: LovLineListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovLineListServiceTarget"
 * @spring.txbn id="lovLineListService"
 * @spring.property name="lovLineListDAO" ref="lovLineListDAO"
 */
public class LovLineListServiceImpl implements LovLineListService
{
    /** 무정지라인팝업 DAO */
    private LovLineListDAO lovLineListDAO = null;

    public LovLineListDAO getLovLineListDAO() {
		return lovLineListDAO;
	}

	public void setLovLineListDAO(LovLineListDAO lovLineListDAO) {
		this.lovLineListDAO = lovLineListDAO;
	}

	public List findLineList(LovLineListDTO lovLineListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovLineListDAO.findLineList(lovLineListDTO, loginUser);
        
        return resultList;
    }
}