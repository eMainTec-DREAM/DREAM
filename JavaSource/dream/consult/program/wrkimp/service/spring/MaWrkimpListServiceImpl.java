package dream.consult.program.wrkimp.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.wrkimp.dao.MaWrkimpListDAO;
import dream.consult.program.wrkimp.dto.MaWrkimpCommonDTO;
import dream.consult.program.wrkimp.service.MaWrkimpListService;


/**
 * ¸ñ·Ï serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maWrkimpListServiceTarget"
 * @spring.txbn id="maWrkimpListService"
 * @spring.property name="maWrkimpListDAO" ref="maWrkimpListDAO"
 */
public class MaWrkimpListServiceImpl implements MaWrkimpListService
{
    private MaWrkimpListDAO maWrkimpListDAO = null;

    public MaWrkimpListDAO getMaWrkimpListDAO() 
    {
		return maWrkimpListDAO;
	}

	public void setMaWrkimpListDAO(MaWrkimpListDAO maWrkimpListDAO) 
	{
		this.maWrkimpListDAO = maWrkimpListDAO;
	}

	public List findWrkimpList(MaWrkimpCommonDTO maWrkimpCommonDTO, User user)
    {      
        return maWrkimpListDAO.findWrkimpList(maWrkimpCommonDTO,user);
    }

    public int deleteList(String[] deleteRows, User user) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maWrkimpListDAO.deleteWrkimp(id, user);
            }
        
        return result;
    }

	public String findTotalCount(MaWrkimpCommonDTO maWrkimpCommonDTO, User user) {
		return maWrkimpListDAO.findTotalCount(maWrkimpCommonDTO, user);
	}
}

