package common.mafinder.mamstr.service.spring;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dao.LovEqAppListDAO;
import common.mafinder.mamstr.dto.LovEqAppListDTO;
import common.mafinder.mamstr.service.LovEqAppListService;

/**
 * 설비기안품의서 팝업 ServiceImpl
 * @author  kim21017
 * @version $Id: LovEqAppListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovEqAppListServiceTarget"
 * @spring.txbn id="lovEqAppListService"
 * @spring.property name="lovEqAppListDAO" ref="lovEqAppListDAO"
 */
public class LovEqAppListServiceImpl implements LovEqAppListService
{
    /** 설비기안품의서 팝업 DAO */
    private LovEqAppListDAO lovEqAppListDAO = null;

    public LovEqAppListDAO getLovEqAppListDAO() {
		return lovEqAppListDAO;
	}

	public void setLovEqAppListDAO(LovEqAppListDAO lovEqAppListDAO) {
		this.lovEqAppListDAO = lovEqAppListDAO;
	}

	public List findEqAppList(LovEqAppListDTO lovEqAppListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovEqAppListDAO.findEqAppList(lovEqAppListDTO,loginUser);
        
        
        return resultList;
    }
}