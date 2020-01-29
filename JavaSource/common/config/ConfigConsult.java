package common.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import common.bean.ConsultConfig;
import common.bean.MwareConfig;
import common.config.service.ConfigConsultService;
import common.config.service.ConfigService;
import common.message.DataBaseMessageResources;
import common.struts.BaseAction;

/**
 * M.WARE config loader
 * @author  javaworker
 * @version $Id: ConfigLoaderServlet.java,v 1.1 2013/08/30 09:13:51 javaworker Exp $
 * @since   1.0
 */
public class ConfigConsult extends HttpServlet {
    
	/**
	 * Initialize the root web application context.
	 */
	public void init() throws ServletException {

        getConsultConfig();
        
//        DataBaseMessageResources dataBaseMessageResources = 
//            (DataBaseMessageResources)getServletContext().getAttribute(org.apache.struts.Globals.MESSAGES_KEY);
//        
//        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
//                
        //dataBaseMessageResources.loadLocale(null);
        //dataBaseMessageResources.loadLocale(configService.findLanguage());
        
        }

    /**
	 * System 설정 로딩
	 * @author  javaworker
	 * @version $Id: ConfigLoaderServlet.java,v 1.1 2013/08/30 09:13:51 javaworker Exp $
	 * @since   1.0
	 *
	 */
	private void getConsultConfig()
	{
		if (BaseAction.ctx == null)
        {
            BaseAction.ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); 
        }
		
        ConfigConsultService configConsultService = (ConfigConsultService) BaseAction.ctx.getBean("configConsultService");

        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
        
        
        // DB 에 있는 해당 page별 button을 로딩한다.
        if (ConsultConfig.getButtonTable() == null)
        {
            ConsultConfig.setButtonTable(configService.findPageButtons());
        }
                                                                
        //==================================================================
        // 모든 Tab Page 목록을 Session에 셋팅한다.
        if (ConsultConfig.getTabPagesTable() == null)
        {
            ConsultConfig.setTabPagesTable(configService.findTabPages());
        }
        
        //==================================================================
        // 해당 User Group에 해당하는 모든 Page 목록을 Session에 셋팅한다.
        if (ConsultConfig.getPagesTable() == null)
        {
            ConsultConfig.setPagesTable(configService.findPages());
        }
        
        //===========================================================================
        // 해당 User Group에 해당하는 모든 Menu 목록을 Session에 셋팅한다.
        if (ConsultConfig.getMenuPathTable() == null)
        {
            ConsultConfig.setMenuPathTable(configConsultService.findMenuPathTable());
        }
        //===========================================================================
	}
}
