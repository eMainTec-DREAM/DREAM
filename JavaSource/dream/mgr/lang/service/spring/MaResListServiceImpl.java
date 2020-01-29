package dream.mgr.lang.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.lang.dao.MaResListDAO;
import dream.mgr.lang.dto.MaResCommonDTO;
import dream.mgr.lang.service.MaResListService;

/**
 * 언어  - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maResListServiceTarget"
 * @spring.txbn id="maResListService"
 * @spring.property name="maResListDAO" ref="maResListDAO"
 */
public class MaResListServiceImpl implements MaResListService
{
    private MaResListDAO maResListDAO = null;

    public MaResListDAO getMaResListDAO() 
    {
		return maResListDAO;
	}

	public void setMaResListDAO(MaResListDAO maResListDAO) 
	{
		this.maResListDAO = maResListDAO;
	}

	public List findResList(MaResCommonDTO maResCommonDTO, User user)
    {      
        return maResListDAO.findResList(maResCommonDTO, user);
    }

    @Override
    public String findTotalCount(MaResCommonDTO maResCommonDTO, User user)
    {
        return maResListDAO.findTotalCount(maResCommonDTO, user);
    }

}

