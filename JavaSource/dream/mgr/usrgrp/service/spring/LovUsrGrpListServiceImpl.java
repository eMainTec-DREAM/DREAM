package dream.mgr.usrgrp.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.usrgrp.dao.LovUsrGrpListDAO;
import dream.mgr.usrgrp.dto.LovUsrGrpListDTO;
import dream.mgr.usrgrp.service.LovUsrGrpListService;

/**
 * ±ÇÇÑÆË¾÷ ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovUsrGrpListServiceTarget"
 * @spring.txbn id="lovUsrGrpListService"
 * @spring.property name="lovUsrGrpListDAO" ref="lovUsrGrpListDAO"
 */
public class LovUsrGrpListServiceImpl implements LovUsrGrpListService
{
    /** ±ÇÇÑ ÆË¾÷ DAO */
    private LovUsrGrpListDAO lovUsrGrpListDAO = null;
    public LovUsrGrpListDAO getLovUsrGrpListDAO() 
    {
		return lovUsrGrpListDAO;
	}

	public void setLovUsrGrpListDAO(LovUsrGrpListDAO lovUsrGrpListDAO) 
	{
		this.lovUsrGrpListDAO = lovUsrGrpListDAO;
	}

	public List findUsrGrpList(LovUsrGrpListDTO lovUsrGrpListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovUsrGrpListDAO.findUsrGrpList(lovUsrGrpListDTO,loginUser);
        
        return resultList;
    }
}