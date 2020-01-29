package dream.work.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.dao.MaWoResultSelectDAO;
import dream.work.list.dto.MaWoResultSelectDTO;
import dream.work.list.service.MaWoResultSelectService;

/**
 * 작업종류/형태 팝업 ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="maWoResultSelectServiceTarget"
 * @spring.txbn id="maWoResultSelectService"
 * @spring.property name="maWoResultSelectDAO" ref="maWoResultSelectDAO"
 */
public class MaWoResultSelectServiceImpl implements MaWoResultSelectService
{
    /** 메뉴 팝업 DAO */
    private MaWoResultSelectDAO maWoResultSelectDAO = null;
    public MaWoResultSelectDAO getMaWoResultSelectDAO() 
    {
		return maWoResultSelectDAO;
	}

	public void setMaWoResultSelectDAO(MaWoResultSelectDAO maWoResultSelectDAO) 
	{
		this.maWoResultSelectDAO = maWoResultSelectDAO;
	}

	public List findWoTypeList(User loginUser, MaWoResultSelectDTO maWoResultSelectDTO)
    {
        List resultList = null;
        resultList = maWoResultSelectDAO.findWoTypeList(loginUser,maWoResultSelectDTO);
        
        return resultList;
    }
	public List findTypeList(User loginUser, MaWoResultSelectDTO maWoResultSelectDTO)
    {
        List resultList = null;
        resultList = maWoResultSelectDAO.findTypeList(loginUser,maWoResultSelectDTO);
        
        return resultList;
    }
}