package dream.pers.appln.service.spring;

import java.util.List;

import common.bean.User;
import dream.pers.appln.dao.MaAppLineListDAO;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.service.MaAppLineListService;

/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id: MaAppLineListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maAppLineListServiceTarget"
 * @spring.txbn id="maAppLineListService"
 * @spring.property name="maAppLineListDAO" ref="maAppLineListDAO"
 */
public class MaAppLineListServiceImpl implements MaAppLineListService
{
    private MaAppLineListDAO maAppLineListDAO = null;

    public MaAppLineListDAO getMaAppLineListDAO() {
		return maAppLineListDAO;
	}

	public void setMaAppLineListDAO(MaAppLineListDAO maAppLineListDAO) {
		this.maAppLineListDAO = maAppLineListDAO;
	}

	public List findQnaList(MaAppLineCommonDTO maAppLineCommonDTO, User user)
    {      
        return maAppLineListDAO.findQnaList(maAppLineCommonDTO, user);
    }
	
	public int deleteQna(String[] deleteRows, User user) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maAppLineListDAO.deleteQna(id,user);
            }
        
        return result;
    }

    public int insertLine(String[] deleteRows, User user, MaAppLineCommonDTO maAppLineCommonDTO)
    {
        int result = 0;
        if(!deleteRows.equals(null)){
        	maAppLineListDAO.deleteLine(maAppLineCommonDTO, user);
        	maAppLineListDAO.mergeAppList(maAppLineCommonDTO, user);
        }
        
        for(String apprlineId : deleteRows)
        {
            maAppLineCommonDTO.setApprlineId(apprlineId);
            result = result + maAppLineListDAO.insertLine(maAppLineCommonDTO, user);
        }
        
        return result;
    }
    
    @Override
    public String findTotalCount(MaAppLineCommonDTO maAppLineCommonDTO, User user)
    {
    	return maAppLineListDAO.findTotalCount(maAppLineCommonDTO, user);
    }
}

