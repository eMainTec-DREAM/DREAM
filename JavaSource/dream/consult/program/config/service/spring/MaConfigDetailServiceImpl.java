package dream.consult.program.config.service.spring;

import common.bean.User;
import common.config.service.ConfigService;
import dream.consult.program.config.dao.MaConfigDetailDAO;
import dream.consult.program.config.dto.MaConfigCommonDTO;
import dream.consult.program.config.dto.MaConfigDetailDTO;
import dream.consult.program.config.service.MaConfigDetailService;

/**
 * 시스템 환경변수 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaConfigDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maConfigDetailServiceTarget"
 * @spring.txbn id="maConfigDetailService"
 * @spring.property name="maConfigDetailDAO" ref="maConfigDetailDAO"
 * @spring.property name="configService" ref="configService"
 */
public class MaConfigDetailServiceImpl implements MaConfigDetailService
{
    private MaConfigDetailDAO maConfigDetailDAO = null;
    private ConfigService configService =null;
    
    public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	public MaConfigDetailDAO getMaConfigDetailDAO() {
		return maConfigDetailDAO;
	}

	public void setMaConfigDetailDAO(MaConfigDetailDAO maConfigDetailDAO) {
		this.maConfigDetailDAO = maConfigDetailDAO;
	}

	public MaConfigDetailDTO findDetail(MaConfigCommonDTO maConfigCommonDTO, User user)throws Exception
    {
        return maConfigDetailDAO.findDetail(maConfigCommonDTO, user);
    }
    
	public int updateDetail(MaConfigDetailDTO maConfigDetailDTO, User user) throws Exception
    {   
		int rtnValue = maConfigDetailDAO.updateDetail(maConfigDetailDTO, user);
         
//         configService.loadConfig();
         return rtnValue;
        
    }
	public int insertDetail(MaConfigDetailDTO maConfigDetailDTO, User user) throws Exception
    {        
		int rtnValue = maConfigDetailDAO.insertDetail(maConfigDetailDTO, user);
         
//         configService.loadConfig();
         return rtnValue;
    }
}
