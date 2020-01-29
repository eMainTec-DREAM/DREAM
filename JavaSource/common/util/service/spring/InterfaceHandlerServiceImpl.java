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
 * Excel Download Ŭ�� (Excel ���� ����, T2FILE/T2INTERFACE �Է�, T2PURCHASE_REQ �� Last Excel Download Date �Է�)
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
     * ���� ���� ����
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
        
        // ���� ����
        saveXLSfile(docType, newFileNo, data, headsName);  
    }
    
    /**
     * ���� ���� ����
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
        // ���� ������ ��й�ȣ �ʿ�
        sheet.protectSheet("emaintec");
        
        //��Ÿ�� ��ü ����
        HSSFCellStyle style = wb.createCellStyle(); 
        //�� ��Ÿ��
//        style.setFillBackgroundColor(HSSFColor.WHITE.index);
//        style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        //���� ��Ÿ��
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
//        //��Ʈ ��ü ����
//        HSSFFont font = wb.createFont(); 
//        
//        font.setFontHeightInPoints((short)10); //��Ʈ ũ��
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //��Ʈ ����
//
//        style.setFont(font); 

        // Header Set
        HSSFRow headerRow = sheet.createRow(0);
        
        //�� ���� ����
        headerRow.setHeight((short)512);

        for (int i=0; i<headsName.length; i++)
        {
            HSSFCell cell = headerRow.createCell(i);
            
            cell.setCellStyle(style);
            cell.setCellValue(headsName[i]);
            
            sheet.autoSizeColumn((short)i); //�� ������(width) �ڵ� ����
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
	 * T2FILE �� ���� ���� �Է�
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
     * T2INTERFACE�� �Է�
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
        
        // INTERFACE������ ���� ����
        this.saveXLSfromMaps("INTERFACE", newFileNo, resultList);
        // T2FILE�� �������� �Է�
        this.insertFileInfo("INTERFACE", newFileNo, objectNo, enterBy, fileName);
        // T2INTERFACE�� Interface ���� �Է�
        this.insertInterface(interType, newFileNo, enterBy);
        
        return newFileNo;
    }
}
