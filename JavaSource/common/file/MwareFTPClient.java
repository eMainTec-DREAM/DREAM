package common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

public class MwareFTPClient
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(MwareFTPClient.class);

    /**
     * WAS 에 있는 해당하는 경로의 값을 
     * FTP 에 업로드한다.
     * @author  javaworker
     * @version $Id: MwareFTPClient.java,v 1.1 2013/08/30 09:12:34 javaworker Exp $
     * @since   1.0
     * 
     * @param wasFilePath
     * @param ftpFilePath
     * @return
     */
    static public boolean fileUpload(String wasFilePath, String ftpFileName, String fileDir)
    {
        logger.debug("fileUpload method call");
        
        FTPClient ftpClient = new FTPClient();
        FileInputStream in = null;
        
        try
        {
            //======================================================================
            //ftpClient.connect(BaseForm.getFtpServerIp());
            //ftpClient.login(BaseForm.getFtpUserId(), BaseForm.getFtpPassWord());
            ftpClient.connect("");
            ftpClient.login("", "");
            //======================================================================
            
            // FTP.ASCII_FILE_TYPE
            // FTP.IMAGE_FILE_TYPE
            ftpClient.setFileType(FTP.IMAGE_FILE_TYPE);
            
            in = new FileInputStream(wasFilePath);
    
            // 만약 ftp의 특정 directory로 이동한다면 
            // fileDir 디렉토리로 이동
            boolean flag = ftpClient.changeWorkingDirectory(fileDir);
            
            if(flag == false)
            {
                StringTokenizer st = new StringTokenizer(fileDir, "/");
                
                // FTP 안에서 해당 디렉토리로 이동
                while(st.hasMoreTokens())
                {
                    String currnetPath = st.nextToken();
                    ftpClient.makeDirectory(currnetPath);
                    ftpClient.changeWorkingDirectory(currnetPath);
                }
            }
            
            logger.debug("===================FTP Upload PreInfomation====================");
            logger.debug("WAS FILE PATH : [" + wasFilePath + "]");
            logger.debug("FTP File Name : [" + ftpFileName + "]");
            logger.debug("FTP File DIR  : [" + fileDir + "]");
            logger.debug("===============================================================");
            
            // ftp 에 저장한다.
            flag = ftpClient.storeFile(ftpFileName, in);
            
            ftpClient.logout();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                if (in != null) in.close();
            }
            catch(IOException ioe){};
            
            if (ftpClient.isConnected()) 
            {
                try 
                {
                    ftpClient.disconnect();
                } 
                catch(IOException ioe) {}
            }
        }
        
        return true;
    }
    
    /**
     * FTP 에서 WAS 로 파일 download 시 사용한다.
     * @author  javaworker
     * @version $Id: MwareFTPClient.java,v 1.1 2013/08/30 09:12:34 javaworker Exp $
     * @since   1.0
     * 
     * @param wasFilePath
     * @param ftpFileName
     * @param fileDir
     * @return
     * @throws IOException
     */
    static public boolean fileDownload(String wasFilePath, String ftpFileName, String fileDir) throws IOException
    {
        logger.debug("==================FTP Download====================");
        logger.debug("WAS FILE PATH : [" + wasFilePath + "]");
        logger.debug("FTP File Name : [" + ftpFileName + "]");
        logger.debug("FTP File DIR  : [" + fileDir + "]");
        logger.debug("==================================================");
        
        FTPClient ftpClient = new FTPClient();
        FileOutputStream ftpOut = null;
        
        try
        {
            //======================================================================
            //ftpClient.connect(BaseForm.getFtpServerIp());
            //ftpClient.login(BaseForm.getFtpUserId(), BaseForm.getFtpPassWord());
            ftpClient.connect("202.133.21.156");
            ftpClient.login("mware_ftp", "mware_ftp");
            //======================================================================
            
            ftpFileName = new String(ftpFileName.getBytes("EUC-KR"), "8859_1");
            fileDir = new String(fileDir.getBytes("EUC-KR"), "8859_1");
            
            // FTP.ASCII_FILE_TYPE
            // FTP.IMAGE_FILE_TYPE
            ftpClient.setFileType(FTP.IMAGE_FILE_TYPE);
            ftpOut = new FileOutputStream(wasFilePath);
            
            // 만약 ftp의 특정 directory로 이동한다면 
            // fileDir 디렉토리로 이동
            boolean flag = ftpClient.changeWorkingDirectory(fileDir);
            flag = ftpClient.retrieveFile(ftpFileName, ftpOut);
            if (!flag)
            {
                throw new Exception("retrieveFile fail");
            }
            
            ftpClient.logout();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally
        {
            if (ftpOut != null)
            {
                ftpOut.close();
            }
            
            if (ftpClient.isConnected()) 
            {
                try 
                {
                    ftpClient.disconnect();
                }
                catch(IOException ioe) {}
            }
        }
        
        return true;
    }

    /**
     * Local[WAS] 에 있는 파일을 삭제 한다.
     * @author  javaworker
     * @version $Id: MwareFTPClient.java,v 1.1 2013/08/30 09:12:34 javaworker Exp $
     * @since   1.0
     * 
     * @param wasFilePath
     * @return
     */
    public static boolean fileLocalDelete(String wasFilePath)
    {
        // FTP로 업로드된 파일을 WAS 에서 삭제한다.
        File file = new File(wasFilePath);
        if (file.delete())
        {
            logger.debug("============== WAS FILE =============");
            logger.debug(wasFilePath+":DELETE!!!");
            logger.debug("=====================================");
            return true;
        }
        
        logger.debug("============== WAS FILE =============");
        logger.debug(wasFilePath+":DELETE ERROR");
        logger.debug("=====================================");
        return false;
    }
}
