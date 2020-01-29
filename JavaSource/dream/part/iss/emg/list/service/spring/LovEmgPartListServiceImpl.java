package dream.part.iss.emg.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.iss.emg.list.dao.LovEmgPartListDAO;
import dream.part.iss.emg.list.dto.LovEmgPartListDTO;
import dream.part.iss.emg.list.service.LovEmgPartListService;

/**
 * 긴급출고 ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovEmgPartListServiceTarget"
 * @spring.txbn id="lovEmgPartListService"
 * @spring.property name="lovEmgPartListDAO" ref="lovEmgPartListDAO"
 */
public class LovEmgPartListServiceImpl implements LovEmgPartListService
{
    /**  DAO */
    private LovEmgPartListDAO lovEmgPartListDAO = null;

    public LovEmgPartListDAO getLovEmgPartListDAO() 
    {
		return lovEmgPartListDAO;
	}

	public void setLovEmgPartListDAO(LovEmgPartListDAO lovEmgPartListDAO) 
	{
		this.lovEmgPartListDAO = lovEmgPartListDAO;
	}

	public List findEmgPartList(LovEmgPartListDTO lovEmgPartListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovEmgPartListDAO.findEmgPartList(lovEmgPartListDTO,loginUser);
        
        return resultList;
    }
	
	@Override
	public String findTotalCount(LovEmgPartListDTO lovEmgPartListDTO, User user) throws Exception {
		return lovEmgPartListDAO.findTotalCount(lovEmgPartListDTO, user);
	}
}