package dream.work.rpt.maeqbm.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.maeqbm.dao.MaEqBmListDAO;
import dream.work.rpt.maeqbm.dto.MaEqBmListDTO;
import dream.work.rpt.maeqbm.service.MaEqBmListService;

/**
 * 설비고장내역serviceimpl
 * @author kim21017
 * @version $Id: MaEqBmListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqBmListServiceTarget"
 * @spring.txbn id="maEqBmListService"
 * @spring.property name="maEqBmListDAO" ref="maEqBmListDAO"
 */
public class MaEqBmListServiceImpl implements MaEqBmListService
{
    private MaEqBmListDAO maEqBmListDAO = null;

    public MaEqBmListDAO getMaEqBmListDAO() {
		return maEqBmListDAO;
	}

	public void setMaEqBmListDAO(MaEqBmListDAO maEqBmListDAO) {
		this.maEqBmListDAO = maEqBmListDAO;
	}
	
	public List findEqBmList(MaEqBmListDTO maEqBmListDTO, User user)
    {      
        return maEqBmListDAO.findEqBmList(maEqBmListDTO, user);
    }

	@Override
	public String findTotalCount(MaEqBmListDTO maEqBmListDTO, User user)
	{
		return maEqBmListDAO.findTotalCount(maEqBmListDTO, user);
	}
}