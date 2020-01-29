package dream.consult.program.btn.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.btn.dao.LovButtonListDAO;
import dream.consult.program.btn.dto.LovButtonListDTO;
import dream.consult.program.btn.service.LovButtonListService;

/**
 * ¹öÆ°ÆË¾÷ ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovButtonListServiceTarget"
 * @spring.txbn id="lovButtonListService"
 * @spring.property name="lovButtonListDAO" ref="lovButtonListDAO"
 */
public class LovButtonListServiceImpl implements LovButtonListService
{
    /** ¹öÆ° ÆË¾÷ DAO */
    private LovButtonListDAO lovButtonListDAO = null;
    public LovButtonListDAO getLovButtonListDAO() 
    {
		return lovButtonListDAO;
	}

	public void setLovButtonListDAO(LovButtonListDAO lovButtonListDAO) 
	{
		this.lovButtonListDAO = lovButtonListDAO;
	}

	public List findButtonList(LovButtonListDTO lovButtonListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovButtonListDAO.findButtonList(lovButtonListDTO,loginUser);
        
        return resultList;
    }
}