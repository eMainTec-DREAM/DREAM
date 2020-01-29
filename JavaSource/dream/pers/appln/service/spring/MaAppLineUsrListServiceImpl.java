package dream.pers.appln.service.spring;

import java.util.List;

import common.bean.User;
import dream.pers.appln.dao.MaAppLineUsrListDAO;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineUsrListDTO;
import dream.pers.appln.service.MaAppLineUsrListService;


/**
 * ¸ñ·Ï serviceimpl
 * @author kim21017
 * @version $Id: MaAppLineUsrListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maAppLineUsrListServiceTarget"
 * @spring.txbn id="maAppLineUsrListService"
 * @spring.property name="maAppLineUsrListDAO" ref="maAppLineUsrListDAO"
 */
public class MaAppLineUsrListServiceImpl implements MaAppLineUsrListService
{
    private MaAppLineUsrListDAO maAppLineUsrListDAO = null;

    public MaAppLineUsrListDAO getMaAppLineUsrListDAO() {
		return maAppLineUsrListDAO;
	}
	public void setMaAppLineUsrListDAO(MaAppLineUsrListDAO maAppLineUsrListDAO) {
		this.maAppLineUsrListDAO = maAppLineUsrListDAO;
	}
	
	public List findAnsList(MaAppLineCommonDTO maAppLineCommonDTO, MaAppLineUsrListDTO maAppLineUsrListDTO, User user)
    {      
        return maAppLineUsrListDAO.findAnsList(maAppLineCommonDTO, maAppLineUsrListDTO, user);
    }
	
	public int deleteAnsList(String[] deleteRows , String[] deleteRowsExt) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + maAppLineUsrListDAO.deleteAnsList(deleteRows[i]);
        }
        
        return result;
    }
	@Override
	public String findTotalCount(MaAppLineCommonDTO maAppLineCommonDTO, MaAppLineUsrListDTO maAppLineUsrListDTO,
			User user) throws Exception {
		return maAppLineUsrListDAO.findTotalCount(maAppLineCommonDTO, maAppLineUsrListDTO, user);
	}
}

