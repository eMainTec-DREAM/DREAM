package common.export;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.bean.User;
import common.file.service.ExcelExportService;
import common.util.CommonUtil;


@SuppressWarnings("serial")
public class ExcelGenerator extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User user = new User();
		user.setUserNo(req.getParameter("userNo"));
		user.setCompNo(req.getParameter("compNo"));
		
        ExcelExportService excelExportService = (ExcelExportService)CommonUtil.getBean("excelExportService", user);
        excelExportService.exportGridExl(req, resp, user);
        
	}

}