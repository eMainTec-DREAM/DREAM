package common.config;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import common.bean.MwareConfig;
import common.config.service.ConfigService;
import common.message.DataBaseMessageResources;
import common.struts.BaseAction;
import dream.consult.comp.list.service.MaCompMngDetailService;

/**
 * M.WARE config loader
 * @author  javaworker
 * @version $Id: ConfigLoaderServlet.java,v 1.1 2013/08/30 09:13:51 javaworker Exp $
 * @since   1.0
 */
public class ConfigLoaderServlet extends HttpServlet {

	private ContextLoader contextLoader;

	/**
	 * Initialize the root web application context.
	 */
	public void init() throws ServletException {
		this.contextLoader = createContextLoader();
		this.contextLoader.initWebApplicationContext(getServletContext());
		
        try {
			getMwareConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        DataBaseMessageResources dataBaseMessageResources = 
            (DataBaseMessageResources)getServletContext().getAttribute(org.apache.struts.Globals.MESSAGES_KEY);
        
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
                
        //dataBaseMessageResources.loadLocale(null);
        dataBaseMessageResources.loadLocale(configService.findLanguage());
        
        }

    /**
	 * System 설정 로딩
	 * @author  javaworker
	 * @version $Id: ConfigLoaderServlet.java,v 1.1 2013/08/30 09:13:51 javaworker Exp $
     * @throws Exception 
     * @since   1.0
	 *
	 */
	private void getMwareConfig() throws Exception
	{
		if (BaseAction.ctx == null)
        {
            BaseAction.ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); 
        }
		
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
    	
        //==================================================================
        // T4SECURITY 를 조회 저장한다.
        if (MwareConfig.getSecurityTable() == null)
        {
            configService.loadSecurityTable();
        }
        
        //==================================================================
        // DB 에 있는 해당 page별 button을 로딩한다.
        if (MwareConfig.getButtonTable() == null)
        {
            configService.loadPageButtons();
        }
                                                                
        //==================================================================
        // 해당 User Group에 해당하는 모든 Tab Page 목록을 Session에 셋팅한다.
        if (MwareConfig.getTabPagesTable() == null)
        {
            configService.loadTabPages();
        }
        
        //==================================================================
        // 해당 User Group에 해당하는 모든 Page 목록을 Session에 셋팅한다.
        if (MwareConfig.getPagesTable() == null)
        {
            configService.loadPages();
        }
        
        //===========================================================================
        // 해당 User Group에 해당하는 모든 Menu 목록을 Session에 셋팅한다.
        if (MwareConfig.getMenuPathTable() == null)
        {
            configService.loadMenuPathTable();
        }
        
       //==================================================================
        // DB 에 있는 해당 page별 Link를 로딩한다.
        if (MwareConfig.getLinkedTable() == null)
        {
            configService.loadPageLinkes();
        }
        
        //===========================================================================
        
        //===========================================================================
        // 각 Page Field 목록을 Session에 셋팅한다.
        if (MwareConfig.getSysCodeJson() == null)
        {
            configService.loadSysCodeJson();
        }

        //===========================================================================

        // WEB APP 설치 경로 설정
        MwareConfig.setWebAppPath(getServletContext().getRealPath("/"));
        
        // page size가 처리되지 않았다면 호출하여 셋팅하여 준다.
        configService.loadConfig();

        //Image Log Setting 
        //get compNo from tacomp whth init_ct_path_yn (Y) , 1ea
        setLoginPage();
        //Then, find image Infomation and copy image File to their proper folder. (LOGINTITLE, LOGINSUBTITLE, MAINTITLE)
        

	}
	
	
	public void setLoginPage() throws Exception
	{
	    if (BaseAction.ctx == null)
        {
            BaseAction.ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); 
        }
	    
	    MaCompMngDetailService service = (MaCompMngDetailService) BaseAction.ctx.getBean("maCompMngDetailService");
	    
	    service.loadLoginImg();
	    
	}
	
	/**
	 * Create the ContextLoader to use. Can be overridden in subclasses.
	 * @return the new ContextLoader
	 */
	protected ContextLoader createContextLoader() {
		return new ContextLoader();
	}

	/**
	 * Return the ContextLoader used by this servlet. 
	 */
	public ContextLoader getContextLoader() {
		return contextLoader;
	}


	/**
	 * Close the root web application context.
	 */
	public void destroy() {
		if (this.contextLoader != null) {
			this.contextLoader.closeWebApplicationContext(getServletContext());
		}
	}

	/**
	 * This should never even be called since no mapping to this servlet should
	 * ever be created in web.xml. That's why a correctly invoked Servlet 2.3
	 * listener is much more appropriate for initialization work ;-)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		getServletContext().log("This servlet is nerver called just for loading data.");
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}


	public String getServletInfo() {
		return "ConfigLoaderServlet for Servlet API 2.2/2.3 " +
		    "(deprecated in favor of ConfigLoaderServlet for Servlet API 2.4)";
	}

}
