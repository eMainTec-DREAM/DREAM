package common.file.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.bean.User;

/**
 * ���� ���� - ��
 * 
 * @author javaworker
 * @version $Id: FileUploadService.java,v 1.2 2013/10/11 01:12:47 pochul2423 Exp $
 * @since 1.0
 */
public interface ExcelExportService
{
	// Server Excel �ٿ�ε�
    void exportXLSXfromMaps(List headData, List data, String fileName,  HttpServletRequest request, HttpServletResponse response,  String currentPageId, String isProtected, String isTree, User user) throws IOException;
    void exportXLSXfromSheetList(List sheetList, String fileName, HttpServletRequest request, HttpServletResponse response, String currentPageId, String isProtected, String isTree, User user) throws IOException;
    
    // Grid Excel �ٿ�ε�
    void exportGridExl(HttpServletRequest request, HttpServletResponse response, User user) throws IOException;
    
}
