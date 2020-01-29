package dream.pers.priv.info.service.spring;

import java.util.List;

import common.bean.User;
import dream.pers.priv.info.dao.MaMyMenuListDAO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.service.MaMyMenuListService;

/**
 * ¸ñ·Ï
 * @author jung7126
 * @version $Id: MaMyMenuListServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maMyMenuListServiceTarget"
 * @spring.txbn id="maMyMenuListService"
 * @spring.property name="maMyMenuListDAO" ref="maMyMenuListDAO"
 */
public class MaMyMenuListServiceImpl implements MaMyMenuListService
{
    private MaMyMenuListDAO maMyMenuListDAO = null;


	public List findList(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {      
        return maMyMenuListDAO.findList(maMyInfoCommonDTO, user);
    }

	public MaMyMenuListDAO getMaMyMenuListDAO() {
		return maMyMenuListDAO;
	}

	public void setMaMyMenuListDAO(MaMyMenuListDAO maMyMenuListDAO) {
		this.maMyMenuListDAO = maMyMenuListDAO;
	}
	
	public int deleteList(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maMyMenuListDAO.deleteList(id);
            }
        
        return result;
    }
}

