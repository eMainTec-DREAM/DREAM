package common.export;

import java.io.IOException;
import java.net.URLDecoder;
import javax.servlet.http.*;

import common.bean.MwareConfig;
import common.export.com.dhtmlx.xml2excel.CSVWriter;

public class CSVGenerator extends HttpServlet {

		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			req.setCharacterEncoding("UTF-8");
			String xml = req.getParameter("grid_xml");
			xml = URLDecoder.decode(xml, "UTF-8");
			CSVWriter writer = new CSVWriter();
			String fileName = "";
			String filePath = MwareConfig.getFileDir();
			writer.generate(xml, filePath, fileName , resp);
		}
}
