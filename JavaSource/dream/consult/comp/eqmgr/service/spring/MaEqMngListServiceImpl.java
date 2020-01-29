package dream.consult.comp.eqmgr.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.eqmgr.dao.MaEqMngListDAO;
import dream.consult.comp.eqmgr.dto.MaEqMngCommonDTO;
import dream.consult.comp.eqmgr.service.MaEqMngListService;

/**
 * 설비담당자변경 - 목록 serviceimpl
 * @author jung7126
 * @version $Id: MaEqMngListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMngListServiceTarget"
 * @spring.txbn id="maEqMngListService"
 * @spring.property name="maEqMngListDAO" ref="maEqMngListDAO"
 */
public class MaEqMngListServiceImpl implements MaEqMngListService
{
    private MaEqMngListDAO maEqMngListDAO = null;

    public MaEqMngListDAO getMaEqMngListDAO() {
		return maEqMngListDAO;
	}

	public void setMaEqMngListDAO(MaEqMngListDAO maEqMngListDAO) {
		this.maEqMngListDAO = maEqMngListDAO;
	}

	public List findEqMngList(MaEqMngCommonDTO maEqMngCommonDTO, User user)
    {      
        return maEqMngListDAO.findEqMngList(maEqMngCommonDTO, user);
    }

	@Override
	public String findTotalCount(MaEqMngCommonDTO maEqMngCommonDTO, User user)
	{
		return maEqMngListDAO.findTotalCount(maEqMngCommonDTO, user);
	}
}

