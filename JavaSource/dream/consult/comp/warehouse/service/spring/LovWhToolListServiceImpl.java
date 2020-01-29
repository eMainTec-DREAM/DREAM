package dream.consult.comp.warehouse.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.warehouse.dao.LovWhToolListDAO;
import dream.consult.comp.warehouse.dto.LovWhToolListDTO;
import dream.consult.comp.warehouse.service.LovWhToolListService;

/**
 * 사용창고 팝업 ServiceImpl
 * @author  kim21017
 * @version $Id: LovWhToolListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovWhToolListServiceTarget"
 * @spring.txbn id="lovWhToolListService"
 * @spring.property name="lovWhToolListDAO" ref="lovWhToolListDAO"
 */
public class LovWhToolListServiceImpl implements LovWhToolListService
{
    /** 사용창고 팝업 DAO */
    private LovWhToolListDAO lovWhToolListDAO = null;

    public LovWhToolListDAO getLovWhToolListDAO() {
		return lovWhToolListDAO;
	}

	public void setLovWhToolListDAO(LovWhToolListDAO lovWhToolListDAO) {
		this.lovWhToolListDAO = lovWhToolListDAO;
	}

	public List findWhList(LovWhToolListDTO lovWhToolListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovWhToolListDAO.findWhList(lovWhToolListDTO, loginUser);
        
        
        return resultList;
    }
}