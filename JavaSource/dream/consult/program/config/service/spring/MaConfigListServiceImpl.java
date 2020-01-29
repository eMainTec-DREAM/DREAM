package dream.consult.program.config.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.config.dao.MaConfigListDAO;
import dream.consult.program.config.dto.MaConfigCommonDTO;
import dream.consult.program.config.service.MaConfigListService;

/**
 * 시스템 환경변수 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaConfigListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maConfigListServiceTarget"
 * @spring.txbn id="maConfigListService"
 * @spring.property name="maConfigListDAO" ref="maConfigListDAO"
 */
public class MaConfigListServiceImpl implements MaConfigListService
{
    private MaConfigListDAO maConfigListDAO = null;

    public MaConfigListDAO getMaConfigListDAO() {
		return maConfigListDAO;
	}

	public void setMaConfigListDAO(MaConfigListDAO maConfigListDAO) {
		this.maConfigListDAO = maConfigListDAO;
	}

	public List findConfigList(MaConfigCommonDTO maConfigCommonDTO, User user)
    {      
        return maConfigListDAO.findConfigList(maConfigCommonDTO, user);
    }
	
	public int deleteConfig(String[] configIdRows, User user) throws Exception{
        int result = 0;
        
        if(!configIdRows.equals(null))
            for(int i=0; i<configIdRows.length; i++)
            {
                result = result + maConfigListDAO.deleteConfig(configIdRows[i], user);
            }
        
        return result;
    }

	public String findTotalCount(MaConfigCommonDTO maConfigCommonDTO, User user) {
		return maConfigListDAO.findTotalCount(maConfigCommonDTO, user);
	}
}

