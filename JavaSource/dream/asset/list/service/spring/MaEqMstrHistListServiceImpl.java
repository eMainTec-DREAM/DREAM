package dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.list.dao.MaEqMstrHistListDAO;
import dream.asset.list.dto.MaEqMstrHistListDTO;
import dream.asset.list.service.MaEqMstrHistListService;

/**
 * 설비변경이력 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaEqMstrHistListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrHistListServiceTarget"
 * @spring.txbn id="maEqMstrHistListService"
 * @spring.property name="maEqMstrHistListDAO" ref="maEqMstrHistListDAO"
 */
public class MaEqMstrHistListServiceImpl implements MaEqMstrHistListService
{
    private MaEqMstrHistListDAO maEqMstrHistListDAO = null;

    public MaEqMstrHistListDAO getMaEqMstrHistListDAO() {
		return maEqMstrHistListDAO;
	}

	public void setMaEqMstrHistListDAO(MaEqMstrHistListDAO maEqMstrHistListDAO) {
		this.maEqMstrHistListDAO = maEqMstrHistListDAO;
	}

	public List findEqMstrHistList(MaEqMstrHistListDTO maEqMstrHistListDTO, User user)
    {      
        return maEqMstrHistListDAO.findEqMstrHistList(maEqMstrHistListDTO,user);
    }
}

