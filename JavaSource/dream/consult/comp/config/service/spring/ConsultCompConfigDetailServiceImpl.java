package dream.consult.comp.config.service.spring;

import common.bean.User;
import common.config.service.ConfigService;
import dream.consult.comp.config.dao.ConsultCompConfigDetailDAO;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;
import dream.consult.comp.config.dto.ConsultCompConfigDetailDTO;
import dream.consult.comp.config.service.ConsultCompConfigDetailService;

/**
 * 시스템 환경변수 - 상세 serviceimpl 
 * @author  syyang
 * @version $Id: ConsultCompConfigDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 syyang Exp $
 * @since   1.0
 * @spring.bean id="consultCompConfigDetailServiceTarget"
 * @spring.txbn id="consultCompConfigDetailService"
 * @spring.property name="consultCompConfigDetailDAO" ref="consultCompConfigDetailDAO"
 * @spring.property name="configService" ref="configService"
 */
public class ConsultCompConfigDetailServiceImpl implements ConsultCompConfigDetailService
{
    private ConsultCompConfigDetailDAO consultCompConfigDetailDAO = null;
    private ConfigService configService =null;
    
    public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	public ConsultCompConfigDetailDAO getConsultCompConfigDetailDAO() {
		return consultCompConfigDetailDAO;
	}

	public void setConsultCompConfigDetailDAO(ConsultCompConfigDetailDAO consultCompConfigDetailDAO) {
		this.consultCompConfigDetailDAO = consultCompConfigDetailDAO;
	}

	public ConsultCompConfigDetailDTO findDetail(ConsultCompConfigCommonDTO consultCompConfigCommonDTO, User user)throws Exception
    {
        return consultCompConfigDetailDAO.findDetail(consultCompConfigCommonDTO, user);
    }
    
	public int updateDetail(ConsultCompConfigDetailDTO consultCompConfigDetailDTO, User user) throws Exception
    {   
		int rtnValue = consultCompConfigDetailDAO.updateDetail(consultCompConfigDetailDTO, user);
         
//         configService.loadConfig();
         return rtnValue;
        
    }
	public int insertDetail(ConsultCompConfigDetailDTO consultCompConfigDetailDTO, User user) throws Exception
    {        
		int rtnValue = consultCompConfigDetailDAO.insertDetail(consultCompConfigDetailDTO, user);
         
//         configService.loadConfig();
         return rtnValue;
    }
}
