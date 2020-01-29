package common.file.service.spring;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.bean.MwareConfig;
import common.bean.User;
import common.export.com.dhtmlx.xml2excel.ExcelWriter;
import common.file.service.ExcelExportService;
import common.util.CommonUtil;
import common.util.DateUtil;

/**
 * 颇老梅何
 * @author  javaworker
 * @version $Id: FileUploadServiceImpl.java,v 1.4 2014/01/07 08:22:29 hiimkkm Exp $
 * @since   1.0
 * @spring.bean id="excelExportServiceTarget"
 * @spring.txbn id="excelExportService"
 */
public class ExcelExportServiceImpl implements ExcelExportService
{
    @Override
    public void exportXLSXfromMaps(List headData, List data, String fileName, HttpServletRequest request, HttpServletResponse response, String currentPageId, String isProtected, String isTree, User user) throws IOException
    {
    	
    	String filePath = MwareConfig.getFileDir();
    	fileName =  fileName +user.getUserNo()+DateUtil.getDateTime() + ".xlsx";
    	
        // Excel File 积己
    	CommonUtil.makeExlFile(headData, data, filePath, fileName, isProtected, isTree);
        
        // Excel download
    	CommonUtil.exportExlFile(filePath, fileName, request, response, currentPageId);
        
    	// Excel File 昏力
    	CommonUtil.deleteExlFile(filePath, fileName);
    	
    }
    
    @Override
    public void exportXLSXfromSheetList(List sheetList, String fileName, HttpServletRequest request, HttpServletResponse response, String currentPageId, String isProtected, String isTree, User user) throws IOException
    {
        String filePath = MwareConfig.getFileDir();
        fileName =  fileName +user.getUserNo()+DateUtil.getDateTime() + ".xlsx";
        
        // Excel File 积己
        CommonUtil.makeExlFile(sheetList, filePath, fileName, isProtected, isTree);
        
        // Excel download
        CommonUtil.exportExlFile(filePath, fileName, request, response, currentPageId);
        
        // Excel File 昏力
        CommonUtil.deleteExlFile(filePath, fileName);
    } 

	@Override
	public void exportGridExl(HttpServletRequest request, HttpServletResponse response, User user) throws IOException
	{
		String xml = request.getParameter("grid_xml");
		String fileName = request.getParameter("fileName");
		xml = URLDecoder.decode(xml, "UTF-8");
		fileName = URLDecoder.decode(fileName, "UTF-8");
		
    	String filePath = MwareConfig.getFileDir();
    	fileName = fileName+user.getUserNo()+DateUtil.getDateTime()+".xls";
		
		// Grid Excel File 积己
    	ExcelWriter ew = new ExcelWriter();
		ew.generate(xml, filePath, fileName, response);
		
        // Excel download
		CommonUtil.exportExlFile(filePath, fileName, request, response, null);
		
    	// Excel File 昏力
    	CommonUtil.deleteExlFile(filePath, fileName);
		
	}
}
