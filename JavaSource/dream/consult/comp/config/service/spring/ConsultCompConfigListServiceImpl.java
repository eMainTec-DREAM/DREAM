package dream.consult.comp.config.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.config.dao.ConsultCompConfigListDAO;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;
import dream.consult.comp.config.service.ConsultCompConfigListService;

/**
 * 시스템 환경변수 - 목록 serviceimpl
 * @author syyang
 * @version $Id: ConsultCompConfigListServiceImpl.java,v 1.0 2015/12/02 09:12:51 syyang Exp $
 * @since 1.0
 * 
 * @spring.bean id="consultCompConfigListServiceTarget"
 * @spring.txbn id="consultCompConfigListService"
 * @spring.property name="consultCompConfigListDAO" ref="consultCompConfigListDAO"
 */
public class ConsultCompConfigListServiceImpl implements ConsultCompConfigListService
{
    private ConsultCompConfigListDAO consultCompConfigListDAO = null;

    public ConsultCompConfigListDAO getConsultCompConfigListDAO() {
		return consultCompConfigListDAO;
	}

	public void setConsultCompConfigListDAO(ConsultCompConfigListDAO consultCompConfigListDAO) {
		this.consultCompConfigListDAO = consultCompConfigListDAO;
	}

	public List findConfigList(ConsultCompConfigCommonDTO consultCompConfigCommonDTO, User user)
    {      
        return consultCompConfigListDAO.findConfigList(consultCompConfigCommonDTO, user);
    }
	
	public int deleteConfig(String[] configIdRows, String[] compNoRows, User user) throws Exception{
        int result = 0;
        
        if(!configIdRows.equals(null))
            for(int i=0; i<configIdRows.length; i++)
            {
                result = result + consultCompConfigListDAO.deleteConfig(configIdRows[i], compNoRows[i], user);
            }
        
        return result;
    }

	public String findTotalCount(ConsultCompConfigCommonDTO consultCompConfigCommonDTO, User user) {
		return consultCompConfigListDAO.findTotalCount(consultCompConfigCommonDTO, user);
	}
}

