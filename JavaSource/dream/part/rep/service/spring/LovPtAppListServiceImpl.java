package dream.part.rep.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.rep.dao.LovPtAppListDAO;
import dream.part.rep.dto.LovPtAppListDTO;
import dream.part.rep.service.LovPtAppListService;

/**
 * 수리기안품의서 팝업 ServiceImpl
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 *
 * @spring.bean id="lovPtAppListServiceTarget"
 * @spring.txbn id="lovPtAppListService"
 * @spring.property name="lovPtAppListDAO" ref="lovPtAppListDAO"
 */
public class LovPtAppListServiceImpl implements LovPtAppListService
{
    /** 수리기안품의서 팝업 DAO */
    private LovPtAppListDAO lovPtAppListDAO = null;

    public LovPtAppListDAO getLovPtAppListDAO() 
    {
		return lovPtAppListDAO;
	}

	public void setLovPtAppListDAO(LovPtAppListDAO lovPtAppListDAO) 
	{
		this.lovPtAppListDAO = lovPtAppListDAO;
	}

	public List findPtAppList(LovPtAppListDTO lovPtAppListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovPtAppListDAO.findPtAppList(lovPtAppListDTO, loginUser);
        
        
        return resultList;
    }
}