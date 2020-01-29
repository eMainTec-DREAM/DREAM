package common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import common.bean.MwareConfig;
import common.file.FileUploadUtil;
import dream.doc.img.dao.MaDocImgDetailDAO;

/**
 * mware ���� ���� Util
 * @author  javaworker
 * @version $Id: FileUtil.java,v 1.3 2013/12/10 08:12:30 javaworker Exp $
 * @since   1.0
 */
public class FileUtil
{
    /**
     * ������ ������ String ���� �����Ѵ�.
     * @author  javaworker
     * @version $Id: FileUtil.java,v 1.3 2013/12/10 08:12:30 javaworker Exp $
     * @since   1.0
     * 
     * @param fileName
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static String readFile(String fileName) throws IOException, FileNotFoundException
    {
        // �ִ� ���� ũ��� 100,000[byte]�� �Ѵ�.
        StringBuffer sFileContent = new StringBuffer(100000);
        
        InputStreamReader ir = new InputStreamReader(new FileInputStream(fileName), "UTF8");

//        FileReader fileReader = new FileReader(fileName);
        if (ir != null) 
        {
            BufferedReader brReader = new BufferedReader(ir);
            if (brReader != null) 
            {
                while(brReader.ready()) 
                {
                    String sLine = brReader.readLine();
                    if ( sLine == null )
                    {
                        break;
                    }
                    sFileContent.append( sLine ).append( '\n' );
                }
                brReader.close();
            }
            ir.close();
        }
    
        return sFileContent.toString();
    }
    
    public static File makeFile(String fileName, String content) throws IOException
    {
        File file = new File(fileName);
        BufferedWriter ot = null;
        if(file.exists()) file.delete();
        
        try {
            file.createNewFile();
            ot = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getPath()), "UTF8"));
        
            ot.write(content);
            ot.close();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if(ot != null) ot.close();
        }
        
//        System.out.println("!!!!!"+file.getPath());
        return file;
    }
    
    /**
     * ���Ͽ� ������ ����.
     * @author  javaworker
     * @version $Id: FileUtil.java,v 1.3 2013/12/10 08:12:30 javaworker Exp $
     * @since   1.0
     * 
     * @param fileName
     * @param content
     * @throws IOException
     */
    public static void writeFile(String fileName, String content) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(fileName);
        for ( int i = 0; i < content.length(); i++ ) 
        {
            fos.write(content.charAt(i));
        }
        fos.close();
    }
    
    /**
     * file Name���� ���� Ȯ���ڸ� �����Ͽ� ����
     * @author  javaworker
     * @version $Id: FileUtil.java,v 1.3 2013/12/10 08:12:30 javaworker Exp $
     * @since   1.0
     * 
     * @param fileName
     * @return
     */
    public static String getFileType(String fileName) 
    {
        // fileName�� . ���� ��ư�� �����ϸ� ���� �ڿ� ��ư�� Ȯ���ڰ� �ȴ�. 
        
        StringTokenizer stFileName = new StringTokenizer(fileName, ".");
        
        String fileType = "";
        
        while (stFileName.hasMoreTokens())
        {
            fileType = stFileName.nextToken();
        }
        
        
        return fileType;
    }
    
    /**
     * xls �������� Data ����
     * @author  javaworker
     * @version $Id: FileUtil.java,v 1.3 2013/12/10 08:12:30 javaworker Exp $
     * @since   1.0
     * 
     * @param mgImpCommonDTO
     * @return
     */
    public static String[][] getXSSFdata(String excelFileDir, String excelFileName)
    {
        //excel���� �о� �� data
        String[][] excelData = null;
        FileInputStream fis = null;
        try
        {
            String filePath = excelFileDir + excelFileName;
            File file = new File(filePath);
            fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            Sheet sheet = wb.getSheetAt(0);
            
            excelData = FileUtil.sheetTOStringArray(sheet);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (fis != null) fis.close();
            }
            catch(IOException ioe){}
        }
        
        return excelData;
    }

    /**
     * Excel �� data�� String �迭�� ���� 
     * @author  javaworker
     * @version $Id: FileUtil.java,v 1.3 2013/12/10 08:12:30 javaworker Exp $
     * @since   1.0
     * 
     * @param excelFileDir : Excel ���� directory
     * @param excelFileName : Excel �����̸�
     * @return
     */
    public static String[][] getExcelData(String excelFileDir, String excelFileName)
    {
        String [][] excelData = null;
        
        String fileType = FileUtil.getFileType(excelFileName);
        
        // HSSF : xls
        if ("xls".equals(fileType.toLowerCase()))
        {
            excelData = FileUtil.getHSSFdata(excelFileDir, excelFileName);
        }
        // XSSF : excel 2007 �̻�
        else if ("xlsx".equals(fileType.toLowerCase()))
        {
            excelData = FileUtil.getXSSFdata(excelFileDir, excelFileName);
        }
        
        return excelData;
    }
    
    /**
     * xlsx �������� Data ����
     * @author  javaworker
     * @version $Id: FileUtil.java,v 1.3 2013/12/10 08:12:30 javaworker Exp $
     * @since   1.0
     * 
     * @param mgImpCommonDTO
     * @return
     */
    public static String[][] getHSSFdata(String excelFileDir, String excelFileName)
    {
        //excel���� �о� �� data
        String[][] excelData = null;
        
        FileInputStream fis = null;
        try
        {
            String filePath = excelFileDir + excelFileName;
            
            fis = new FileInputStream(filePath);
            HSSFWorkbook wb = new HSSFWorkbook(fis);

            Sheet sheet = wb.getSheetAt(wb.getActiveSheetIndex());
            
            excelData = FileUtil.sheetTOStringArray(sheet);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (fis != null) fis.close();
            }
            catch(IOException ioe){}
        }
        
        return excelData;
    }
    
    /**
     * Excel Sheet(Apache POI)�� String[][] �� ��ȯ
     * @author  javaworker
     * @version $Id: FileUtil.java,v 1.3 2013/12/10 08:12:30 javaworker Exp $
     * @since   1.0
     * 
     * @param sheet
     * @return
     */
    public static String[][] sheetTOStringArray(Sheet sheet)
    {
        int rowNumber = sheet.getLastRowNum();
        int cellNumber = sheet.getRow(0).getLastCellNum();
        
        String [][] excelData = new String[rowNumber+1][cellNumber];
        
        for (int i=0; i<=rowNumber; i++)
        {
            Row row = sheet.getRow(i);
            if (row == null) continue;
            
            for (int j=0; j<cellNumber; j++)
            {
                Cell cell = row.getCell(j);
                
                if (cell == null)
                {
                    excelData[i][j] = "";
                    continue;
                }
                switch(cell.getCellType()) 
                {
                    /*
                    case Cell.CELL_TYPE_STRING:
                        excelData[i][j] = cell.getRichStringCellValue().getString();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if(DateUtil.isCellDateFormatted(cell)) 
                        {
                            excelData[i][j] = cell.getDateCellValue().toString();
                        } 
                        else 
                        {
                            excelData[i][j] = Double.toString(cell.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        
                        break;
                    */
                    case Cell.CELL_TYPE_FORMULA:
                        
                        try
                        {
                            // ���� ���� ���� String
                            excelData[i][j] = cell.getRichStringCellValue().getString();
                        }
                        catch(Exception ex)
                        {
                            try
                            {
                                // ���� ���� ���� ����
                                excelData[i][j] = Double.toString(cell.getNumericCellValue());
                            }
                            catch(Exception ex2)
                            {
                                // String ���ڿ��� �״�� ��Ÿ���� ��
                                excelData[i][j] = cell.toString();
                            }
                        }
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell))
                        {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                            excelData[i][j] = sdf.format(cell.getDateCellValue());
                        }
                        else
                        {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            excelData[i][j] = cell.toString().replaceAll(",", "");
                        }
                        break;
                    default:
                        excelData[i][j] = cell.toString();
                }
            }
        }
        
        return excelData;
    }
    /**
     * file Name����
     * @author  javaworker
     * @version $Id: FileUtil.java,v 1.1 2015/09/03 07:58:34 brpark Exp $
     * @since   1.0
     * 
     * @param fileName
     * @return
     */
    public static String getFileName(String filePath) 
    {
        String[] fileToken = filePath.split("\\\\");
        String fileName = fileToken[fileToken.length-1];
        return fileName.split("\\.")[0];
    }
    
    public static boolean fileExists(String fileName){
        
       // System.out.println("!!!!!"+getWebRootPath()+fileName);
        File f = new File(getWebRootPath()+fileName);
     //   System.out.println(f.exists());
        return f.exists();
    }

    private static String getWebRootPath() {
        return FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath().split("WEB-INF/")[0];
    }
    
    public static CloudBlobClient getCloudBlobClient() throws StorageException, FileNotFoundException, InvalidKeyException, URISyntaxException 
    {
       String storageConnectionString =
               "DefaultEndpointsProtocol=https;"
                  + "AccountName=dreamdiag;"
                  + "AccountKey=DefaultEndpointsProtocol=https;AccountName=dreamdiag;AccountKey=K+IzkSMaAqFpPLSyLyVbLbOj+uOEaKLF2fZcSV7B0hLrQgAG0enzonhc+DY2xrj4FV3WmvtoTwsYt3xOTy6YhQ==;EndpointSuffix=core.windows.net";

      CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);
      CloudBlobClient serviceClient = account.createCloudBlobClient();

      return serviceClient;
    }
    
    public static List makeSlideImg(String objectId, String objectType) throws InvalidKeyException, URISyntaxException, StorageException
    {
        return makeSlideImg(objectId, objectType, "");
    }
    
    public static List makeSlideImg(String objectId, String objectType, String param) throws InvalidKeyException, URISyntaxException, StorageException
    {
        MaDocImgDetailDAO maDocImgDetailDAO = (MaDocImgDetailDAO) CommonUtil.getBean("maDocImgDetailDAO");
       List imgList = maDocImgDetailDAO.getImgFileList(objectId, objectType, param);
       List rtnList = new ArrayList();
       
       for(int i = 0; imgList.size() > i ; i++)
       {
           Map map = (Map)imgList.get(i);

           String fileNo = String.valueOf(map.get("IMGDATA_ID"));
           String fileName = String.valueOf(map.get("FILE_NAME"));
           String filePath = String.valueOf(map.get("NFFILEPATH"));
           
           String originFile = "";
           String targetFile = "";
           
           if(MwareConfig.osName.indexOf("LINUX") >=0){
               originFile = MwareConfig.getFileDir() + filePath + "/" + fileNo;
               targetFile = MwareConfig.getWebAppPath()+"dream/imgSlide/" + fileName;
           }else{
               originFile = MwareConfig.getFileDir() + filePath + "\\" + fileNo;
               targetFile = MwareConfig.getWebAppPath()+"dream\\imgSlide\\" + fileName;
           }
           
           try
           {
               if(MwareConfig.osName.indexOf("LINUX") >=0){
                   fileName = FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getWebAppPath()+"dream/imgSlide/");
               }else{
                   fileName = FileUploadUtil.fileCopy(originFile, targetFile, MwareConfig.getWebAppPath()+"dream\\imgSlide\\");
               }
               
           }catch (IOException e){}
           
           map.put("FILE_NAME", fileName);
           rtnList.add(map);
       }


        // file ����
        deleteTempResultFiles();
        
        return rtnList;
    }
    
    public static void deleteTempResultFiles()
    {
        //=======================================================
        // �ӽ� ���� dir �� ��� ���� �� �����Ѵ�.
        File dirFile = null;
        if(MwareConfig.osName.indexOf("LINUX") >=0){
            dirFile = new File(MwareConfig.getWebAppPath()+"dream/imgSlide/");
        }else{
            dirFile = new File(MwareConfig.getWebAppPath()+"dream\\imgSlide\\");
        }
        File [] files = dirFile.listFiles();
        
        if(files == null) return;
        
        for (int i=0; i<files.length; i++)
        {
            // ���ڰ� ���ó�¥ ������ �����Ȱ͵��� ��� �����Ѵ�.
            long fileDateLong = files[i].lastModified();
            Date fileDate = new Date(fileDateLong);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            
            // ���� ��������
            String madeDate = dateFormat.format(fileDate);
            
            // ��������
            String currentDate = common.util.DateUtil.getDateTime("yyyyMMdd");
            
            try
            {
                // ���ϻ������� < �������� ��� �����Ѵ�.
                if (common.util.DateUtil.compareDate(madeDate, null, "<", currentDate, null))
                {
                    files[i].delete();
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();                
            }
        }
    }
}
