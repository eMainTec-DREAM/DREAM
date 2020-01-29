package common.util.service.spring;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import common.bean.MwareConfig;
import common.util.dao.InterfaceHandlerDAO;
import common.util.service.InterfaceHandlerService;

/**
 * Excel Download 클릭 (Excel 파일 저장, T2FILE/T2INTERFACE 입력, T2PURCHASE_REQ 에 Last Excel Download Date 입력)
 * @author  hiimkkm
 * @version $Id: InterfaceHandlerServiceImpl.java,v 1.11 2013/12/30 04:16:27 hiimkkm Exp $
 * @since   1.0
 *
 * @spring.bean id="interfaceHandlerServiceTarget"
 * @spring.txbn id="interfaceHandlerService"
 * 
 * @spring.property name="interfaceHandlerDAO" ref="interfaceHandlerDAO"
 */
public class InterfaceHandlerServiceImpl implements InterfaceHandlerService
{
    /** interface DAO */
	private InterfaceHandlerDAO interfaceHandlerDAO = null;
	 
	public InterfaceHandlerDAO getInterfaceHandlerDAO()
    {
        return interfaceHandlerDAO;
    }

    public void setInterfaceHandlerDAO(InterfaceHandlerDAO interfaceHandlerDAO)
    {
        this.interfaceHandlerDAO = interfaceHandlerDAO;
    }

    /**
     * 엑셀 파일 저장
     * @author  hiimkkm
     * @version $Id: InterfaceHandlerServiceImpl.java,v 1.11 2013/12/30 04:16:27 hiimkkm Exp $
     * @since   1.0
     * 
     * @param docType
     * @param newFileNo
     * @param data
     * @throws IOException
     */
    private void saveXLSfromMaps(String docType, String newFileNo, List data)
            throws IOException
    {
        Map record = (Map)data.get(0);
        
        Set set = record.keySet();
        Iterator setIter = set.iterator();
        String[] headsName = new String[set.size()];
        for(int i=0;setIter.hasNext();i++)
        {
            headsName[i] = (String)setIter.next();
        }
        
        // 파일 저장
        saveXLSfile(docType, newFileNo, data, headsName);  
    }
    
    /**
     * 엑셀 파일 저장
     * @author  hiimkkm
     * @version $Id: InterfaceHandlerServiceImpl.java,v 1.11 2013/12/30 04:16:27 hiimkkm Exp $
     * @since   1.0
     * 
     * @param docType
     * @param newFileNo
     * @param data
     * @param headsName
     * @throws IOException
     */
	private void saveXLSfile(String docType, String newFileNo, List data, String[] headsName) throws IOException
	{
	    HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet");
        // 엑셀 편집시 비밀번호 필요
        sheet.protectSheet("emaintec");
        
        //스타일 객체 생성
        HSSFCellStyle style = wb.createCellStyle(); 
        //셀 스타일
//        style.setFillBackgroundColor(HSSFColor.WHITE.index);
//        style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        //보더 스타일
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBottomBorderColor(HSSFColor.BLACK.index);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setLeftBorderColor(HSSFColor.BLACK.index);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setRightBorderColor(HSSFColor.BLACK.index);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setTopBorderColor(HSSFColor.BLACK.index);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        //폰트 객체 생성
//        HSSFFont font = wb.createFont(); 
//        
//        font.setFontHeightInPoints((short)10); //폰트 크기
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //폰트 굵게
//
//        style.setFont(font); 

        // Header Set
        HSSFRow headerRow = sheet.createRow(0);
        
        //셀 높이 설정
        headerRow.setHeight((short)512);

        for (int i=0; i<headsName.length; i++)
        {
            HSSFCell cell = headerRow.createCell(i);
            
            cell.setCellStyle(style);
            cell.setCellValue(headsName[i]);
            
            sheet.autoSizeColumn((short)i); //셀 사이즈(width) 자동 조정
        }

        for (int i=0,len=data.size();i<len;i++)
        {
            HSSFRow dataRow = sheet.createRow(i+1);
            
            Map record=(Map)data.get(i);
            
            Set keySet = record.keySet();
            Iterator keyIter = keySet.iterator();
            
            for (int j=0; keyIter.hasNext(); j++)
            {
                    
                HSSFCell cell = dataRow.createCell(j);  
                String key = (String)keyIter.next();
      
                if(String.valueOf(record.get(key)).equals("null"))
                {
                    cell.setCellValue("");
                }
                else
                {
                    cell.setCellValue(String.valueOf(record.get(key)));
                    
                    if( String.valueOf(record.get(key)).length() > 35 )
                    {
                        sheet.setColumnWidth(j, 6000);
                    }
                }
            }
            
        }
        
        FileOutputStream fs = null;
        try
        {
            fs = new FileOutputStream(MwareConfig.getFileDir() + docType + "\\" + newFileNo);
            wb.write(fs);
        }
        finally 
        {
            fs.close();
        }
	}
	
	/**
	 * T2FILE 에 파일 정보 입력
	 * @author  hiimkkm
	 * @version $Id: InterfaceHandlerServiceImpl.java,v 1.11 2013/12/30 04:16:27 hiimkkm Exp $
	 * @since   1.0
	 * 
	 * @param docType
	 * @param newFileNo
	 * @param objectNo
	 * @param enterBy
	 * @param fileName
	 */
    private void insertFileInfo(String docType, String newFileNo, String objectNo, String enterBy, String fileName)
    {
        interfaceHandlerDAO.insertFileInfo(docType, newFileNo, objectNo, enterBy, fileName);
    }

    /**
     * T2INTERFACE에 입력
     * @author  hiimkkm
     * @version $Id: InterfaceHandlerServiceImpl.java,v 1.11 2013/12/30 04:16:27 hiimkkm Exp $
     * @since   1.0
     * 
     * @param docType
     * @param newFileNo
     * @param enterBy
     */
    private void insertInterface(String docType, String newFileNo, String enterBy)
    {
        interfaceHandlerDAO.insertInterface(docType, newFileNo, enterBy);
    }

    public String excelDownload(String interType, List resultList, String fileName, String enterBy) throws IOException
    {
        String objectNo = interfaceHandlerDAO.getNextSequence("SQ2INTERFACE_NO");
        String newFileNo = interfaceHandlerDAO.getNextSequence("SQ2FILE_NO");
        
        // INTERFACE폴더에 파일 저장
        this.saveXLSfromMaps("INTERFACE", newFileNo, resultList);
        // T2FILE에 파일정보 입력
        this.insertFileInfo("INTERFACE", newFileNo, objectNo, enterBy, fileName);
        // T2INTERFACE에 Interface 정보 입력
        this.insertInterface(interType, newFileNo, enterBy);
        
        return newFileNo;
    }
}
