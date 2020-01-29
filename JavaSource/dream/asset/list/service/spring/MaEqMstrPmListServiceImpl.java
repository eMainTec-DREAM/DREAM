package dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrPmListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmListDTO;
import dream.asset.list.service.MaEqMstrPmListService;

/**
 * 설비 점검 목록
 * @author kim21017
 * @version $Id: MaEqMstrPmListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrPmListServiceTarget"
 * @spring.txbn id="maEqMstrPmListService"
 * @spring.property name="maEqMstrPmListDAO" ref="maEqMstrPmListDAO"
 */
public class MaEqMstrPmListServiceImpl implements MaEqMstrPmListService
{
    private MaEqMstrPmListDAO maEqMstrPmListDAO = null;
    
	public MaEqMstrPmListDAO getMaEqMstrPmListDAO() {
		return maEqMstrPmListDAO;
	}

	public void setMaEqMstrPmListDAO(MaEqMstrPmListDAO maEqMstrPmListDAO) {
		this.maEqMstrPmListDAO = maEqMstrPmListDAO;
	}


	public List findInsList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser)
    {      
        return maEqMstrPmListDAO.findInsList(maEqMstrCommonDTO, maEqMstrPmListDTO, loginUser);
    }
	public List findRplList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser)
    {      
        return maEqMstrPmListDAO.findRplList(maEqMstrCommonDTO, maEqMstrPmListDTO, loginUser);
    }
	public String findInsTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser)
    {      
        return maEqMstrPmListDAO.findInsTotalCount(maEqMstrCommonDTO, maEqMstrPmListDTO, loginUser);
    }
	public String findRplTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmListDTO maEqMstrPmListDTO, User loginUser)
    {      
        return maEqMstrPmListDAO.findRplTotalCount(maEqMstrCommonDTO, maEqMstrPmListDTO, loginUser);
    }

}