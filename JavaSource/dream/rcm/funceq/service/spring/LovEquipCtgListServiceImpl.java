package dream.rcm.funceq.service.spring;

import java.util.List;

import common.bean.User;
import dream.rcm.funceq.dao.LovEquipCtgListDAO;
import dream.rcm.funceq.dto.LovEquipCtgListDTO;
import dream.rcm.funceq.service.LovEquipCtgListService;

/**
 * ¼³ºñÆË¾÷ ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovEquipCtgListServiceTarget"
 * @spring.txbn id="lovEquipCtgListService"
 * @spring.property name="lovEquipCtgListDAO" ref="lovEquipCtgListDAO"
 */
public class LovEquipCtgListServiceImpl implements LovEquipCtgListService
{
    /** ¼³ºñÆË¾÷ DAO */
    private LovEquipCtgListDAO lovEquipCtgListDAO = null;

    public LovEquipCtgListDAO getLovEquipCtgListDAO() 
    {
		return lovEquipCtgListDAO;
	}

	public void setLovEquipCtgListDAO(LovEquipCtgListDAO lovEquipCtgListDAO) 
	{
		this.lovEquipCtgListDAO = lovEquipCtgListDAO;
	}

	public List findEquipList(LovEquipCtgListDTO lovEquipCtgListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovEquipCtgListDAO.findEquipList(lovEquipCtgListDTO, loginUser);
        
        return resultList;
    }
}