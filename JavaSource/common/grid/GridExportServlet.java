package common.grid;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.bean.User;
import common.file.service.ExcelExportService;
import common.struts.BaseAction;
import common.util.CommonUtil;

/**
 * Grid Export Servlet
 * @author  javaworker
 * @version $Id: GridExportServlet.java,v 1.1 2013/08/30 09:12:56 javaworker Exp $
 * @since   1.0
 */
public class GridExportServlet extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException
	{
	    if("Y".equals(request.getAttribute("isMultiSheet"))){
	        List sheetList = (List)request.getAttribute("sheetList");
            String fileName = (String)request.getAttribute("fileName");
            String currentPageId = (String)request.getAttribute("currentPageId");
            String isProtected = (String)request.getAttribute("isProtected");
            String isTree = (String)request.getAttribute("isTree");
            User user = (User) request.getAttribute("user");
            String compNo = user.getCompNo();
            
            ExcelExportService excelExportService = (ExcelExportService)CommonUtil.getBean("excelExportService", compNo);
            excelExportService.exportXLSXfromSheetList(sheetList, fileName, request, response, currentPageId, isProtected, isTree, user);
	    }
	    else{
	        List data = (List)request.getAttribute(BaseAction.FIND_XML_ATTRIBUTE);
	        List headData = (List)request.getAttribute("headList");
	        String fileName = (String)request.getAttribute("fileName");
	        String currentPageId = (String)request.getAttribute("currentPageId");
	        String isProtected = (String)request.getAttribute("isProtected");
	        String isTree = (String)request.getAttribute("isTree");
	        User user = (User) request.getAttribute("user");
	        String compNo = user.getCompNo();
	        
	        ExcelExportService excelExportService = (ExcelExportService)CommonUtil.getBean("excelExportService", compNo);
	        excelExportService.exportXLSXfromMaps(headData, data, fileName, request, response, currentPageId, isProtected, isTree, user);
	    }

	}
}