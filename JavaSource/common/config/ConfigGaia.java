package common.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import common.bean.GaiaConfig;
import common.config.service.ConfigGaiaService;
import common.config.service.ConfigService;
import common.message.DataBaseMessageResources;
import common.struts.BaseAction;

/**
 * M.WARE config loader
 * @author  javaworker
 * @version $Id: ConfigLoaderServlet.java,v 1.1 2013/08/30 09:13:51 javaworker Exp $
 * @since   1.0
 */
public class ConfigGaia extends HttpServlet {
    
	/**
	 * Initialize the root web application context.
	 */
	public void init() throws ServletException {

        getGaiaConfig();
        
//        DataBaseMessageResources dataBaseMessageResources = 
//            (DataBaseMessageResources)getServletContext().getAttribute(org.apache.struts.Globals.MESSAGES_KEY);
//        
//        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
//                
        //dataBaseMessageResources.loadLocale(null);
        //dataBaseMessageResources.loadLocale(configService.findLanguage());
        
        }

    /**
	 * System ���� �ε�
	 * @author  javaworker
	 * @version $Id: ConfigLoaderServlet.java,v 1.1 2013/08/30 09:13:51 javaworker Exp $
	 * @since   1.0
	 *
	 */
	private void getGaiaConfig()
	{
		if (BaseAction.ctx == null)
        {
            BaseAction.ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); 
        }
		
        ConfigGaiaService configGaiaService = (ConfigGaiaService) BaseAction.ctx.getBean("configGaiaService");

        // DB �� �ִ� �ش� page�� button�� �ε��Ѵ�.
        if (GaiaConfig.getButtonTable() == null)
        {
            GaiaConfig.setButtonTable(configGaiaService.findPageButtons());
        }
                                                                
        //==================================================================
        // ��� Tab Page ����� Session�� �����Ѵ�.
        if (GaiaConfig.getTabPagesTable() == null)
        {
            GaiaConfig.setTabPagesTable(configGaiaService.findTabPages());
        }
        
        //==================================================================
        // �ش� User Group�� �ش��ϴ� ��� Page ����� Session�� �����Ѵ�.
        if (GaiaConfig.getPagesTable() == null)
        {
            GaiaConfig.setPagesTable(configGaiaService.findPages());
        }
        
        //===========================================================================
        // �ش� User Group�� �ش��ϴ� ��� Menu ����� Session�� �����Ѵ�.
        if (GaiaConfig.getMenuPathTable() == null)
        {
            GaiaConfig.setMenuPathTable(configGaiaService.findMenuPathTable());
        }
        //===========================================================================
	}
}
