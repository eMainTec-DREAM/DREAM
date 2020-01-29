package dream.part.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.list.dao.LovReqPartsListDAO;
import dream.part.list.dto.LovReqPartsListDTO;
import dream.part.list.service.LovReqPartsListService;

/**
 * 濠營で機 ServiceImpl
 * @author  kim21017
 * @version $Id: LovReqPartsListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovReqPartsListServiceTarget"
 * @spring.txbn id="lovReqPartsListService"
 * @spring.property name="lovReqPartsListDAO" ref="lovReqPartsListDAO"
 */
public class LovReqPartsListServiceImpl implements LovReqPartsListService
{
    /** 濠營で機 DAO */
    private LovReqPartsListDAO lovReqPartsListDAO = null;

    public LovReqPartsListDAO getLovReqPartsListDAO() {
		return lovReqPartsListDAO;
	}

	public void setLovReqPartsListDAO(LovReqPartsListDAO lovReqPartsListDAO) {
		this.lovReqPartsListDAO = lovReqPartsListDAO;
	}

	public List findPartsList(LovReqPartsListDTO lovReqPartsListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovReqPartsListDAO.findPartsList(lovReqPartsListDTO, loginUser);
        
        
        return resultList;
    }
}