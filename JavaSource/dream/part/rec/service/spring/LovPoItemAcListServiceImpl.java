package dream.part.rec.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.rec.dao.LovPoItemAcListDAO;
import dream.part.rec.dto.LovPoItemAcListDTO;
import dream.part.rec.service.LovPoItemAcListService;

/**
 * 발주 선택 Lov ServiceImpl
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovPoItemAcListServiceTarget"
 * @spring.txbn id="lovPoItemAcListService"
 * @spring.property name="lovPoItemAcListDAO" ref="lovPoItemAcListDAO"
 */
public class LovPoItemAcListServiceImpl implements LovPoItemAcListService
{
    /** 팝업 DAO */
    private LovPoItemAcListDAO lovPoItemAcListDAO = null;
    
    public LovPoItemAcListDAO getLovPoItemAcListDAO() {
		return lovPoItemAcListDAO;
	}

	public void setLovPoItemAcListDAO(LovPoItemAcListDAO lovPoItemAcListDAO) {
		this.lovPoItemAcListDAO = lovPoItemAcListDAO;
	}
 
	@Override
    public List findList(LovPoItemAcListDTO lovPoItemAcListDTO, User user)
    {
        List resultList = lovPoItemAcListDAO.findList(lovPoItemAcListDTO, user);
        return resultList;
    }
}