package dream.pers.priv.db.set.service.spring;

import java.util.List;

import common.bean.User;
import dream.pers.priv.db.set.dao.LovDbConAcListDAO;
import dream.pers.priv.db.set.dto.LovDbConAcListDTO;
import dream.pers.priv.db.set.service.LovDbConAcListService;

/**
 * ÀÛ¾÷¿äÃ»À¯Çü ÆË¾÷ ServiceImpl
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovDbConAcListServiceTarget"
 * @spring.txbn id="lovDbConAcListService"
 * @spring.property name="lovDbConAcListDAO" ref="lovDbConAcListDAO"
 */
public class LovDbConAcListServiceImpl implements LovDbConAcListService
{
    /** ÆË¾÷ DAO */
    private LovDbConAcListDAO lovDbConAcListDAO = null;
    
    public LovDbConAcListDAO getLovDbConAcListDAO() {
		return lovDbConAcListDAO;
	}

	public void setLovDbConAcListDAO(LovDbConAcListDAO lovDbConAcListDAO) {
		this.lovDbConAcListDAO = lovDbConAcListDAO;
	}
 
	@Override
    public List findList(LovDbConAcListDTO lovDbConAcListDTO, User user)
    {
        List resultList = null;
        resultList = lovDbConAcListDAO.findList(lovDbConAcListDTO, user);
        return resultList;
    }
}