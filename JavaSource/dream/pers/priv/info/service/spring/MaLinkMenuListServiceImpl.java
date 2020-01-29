package dream.pers.priv.info.service.spring;

import java.util.List;

import common.bean.User;
import dream.pers.priv.info.dao.MaLinkMenuListDAO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.service.MaLinkMenuListService;

/**
 * ¸ñ·Ï
 * @author jung7126
 * @version $Id: MaLinkMenuListServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maLinkMenuListServiceTarget"
 * @spring.txbn id="maLinkMenuListService"
 * @spring.property name="maLinkMenuListDAO" ref="maLinkMenuListDAO"
 */
public class MaLinkMenuListServiceImpl implements MaLinkMenuListService
{
    private MaLinkMenuListDAO maLinkMenuListDAO = null;


	public List findList(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {      
        return maLinkMenuListDAO.findList(maMyInfoCommonDTO, user);
    }

	public MaLinkMenuListDAO getMaLinkMenuListDAO() {
		return maLinkMenuListDAO;
	}

	public void setMaLinkMenuListDAO(MaLinkMenuListDAO maLinkMenuListDAO) {
		this.maLinkMenuListDAO = maLinkMenuListDAO;
	}
	
	public int deleteList(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maLinkMenuListDAO.deleteList(id);
            }
        
        return result;
    }
}

