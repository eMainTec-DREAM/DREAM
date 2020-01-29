package dream.consult.program.page.service.spring;

import java.util.List;

import common.bean.User;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.consult.program.page.dao.MaGrdMngColListDAO;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;
import dream.consult.program.page.service.MaGrdMngColListService;

/**
 * Ä®·³ ¸ñ·Ï
 * @author jung7126
 * @version $Id: MaGrdMngColListServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maGrdMngColListServiceTarget"
 * @spring.txbn id="maGrdMngColListService"
 * @spring.property name="maGrdMngColListDAO" ref="maGrdMngColListDAO"
 */
public class MaGrdMngColListServiceImpl implements MaGrdMngColListService
{
    private MaGrdMngColListDAO maGrdMngColListDAO = null;
    
	public MaGrdMngColListDAO getMaGrdMngColListDAO() {
		return maGrdMngColListDAO;
	}

	public void setMaGrdMngColListDAO(MaGrdMngColListDAO maGrdMngColListDAO) {
		this.maGrdMngColListDAO = maGrdMngColListDAO;
	}
	
	public List findColList(MaGrdMngCommonDTO maGrdMngCommonDTO, User user)
	{      
	    return maGrdMngColListDAO.findColList(maGrdMngCommonDTO, user);
	}
	
	public String findTotalCount(MaGrdMngCommonDTO maGrdMngCommonDTO, User user) throws Exception
    {      
        return maGrdMngColListDAO.findTotalCount(maGrdMngCommonDTO,user);
    }
	
	public int deleteColList(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maGrdMngColListDAO.deleteColList(id);
            }
        
        return result;
    }
	
	public int sysYColList(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maGrdMngColListDAO.sysYColList(id);
            }
        
        return result;
    }
	
	public int sysNColList(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maGrdMngColListDAO.sysNColList(id);
            }
        
        return result;
    }

}

