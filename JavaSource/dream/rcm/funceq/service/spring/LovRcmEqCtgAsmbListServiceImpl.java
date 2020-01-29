package dream.rcm.funceq.service.spring;

import java.util.List;

import common.bean.User;

import dream.rcm.funceq.dao.LovRcmEqCtgAsmbListDAO;
import dream.rcm.funceq.dto.LovRcmEqCtgAsmbListDTO;
import dream.rcm.funceq.service.LovRcmEqCtgAsmbListService;

/**
 * 설비종류작업부위팝업 ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovRcmEqCtgAsmbListServiceTarget"
 * @spring.txbn id="lovRcmEqCtgAsmbListService"
 * @spring.property name="lovRcmEqCtgAsmbListDAO" ref="lovRcmEqCtgAsmbListDAO"
 */
public class LovRcmEqCtgAsmbListServiceImpl implements LovRcmEqCtgAsmbListService
{
    /** 설비종류작업부위팝업 DAO */
    private LovRcmEqCtgAsmbListDAO lovRcmEqCtgAsmbListDAO = null;

    public LovRcmEqCtgAsmbListDAO getLovRcmEqCtgAsmbListDAO() 
    {
		return lovRcmEqCtgAsmbListDAO;
	}

	public void setLovRcmEqCtgAsmbListDAO(LovRcmEqCtgAsmbListDAO lovRcmEqCtgAsmbListDAO) 
	{
		this.lovRcmEqCtgAsmbListDAO = lovRcmEqCtgAsmbListDAO;
	}

	public List findEqCtgAsmbList(LovRcmEqCtgAsmbListDTO lovRcmEqCtgAsmbListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovRcmEqCtgAsmbListDAO.findEqCtgAsmbList(lovRcmEqCtgAsmbListDTO,loginUser);
        
        return resultList;
    }
}