package common.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.apache.log4j.Logger;

public class FileDownUtil
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FileDownUtil.class);

    /**
     *  파일을 임시폴더로 다운로드시 사용한다.
     * @author  sem0202
     * @version $Id: FileDownUtil.java,v 1.1 2013/08/30 09:12:34 javaworker Exp $
     * @since   1.0
     * 
     * @param tempFilePath
     * @param fileDir
     * @return
     * @throws IOException
     */
    static public boolean fileDownload(String tempFilePath, String fileDir) throws IOException
    {
        logger.debug("==================File tempFolder Download====================");
        logger.debug("WAS FILE PATH : [" + tempFilePath + "]");
        logger.debug("FTP File DIR  : [" + fileDir + "]");
        logger.debug("==============================================================");
        
        //스트림, 채널 선언
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        FileChannel fcin = null;
        FileChannel fcout = null;
        
        /**
         * fileDir에서 tempFilePath으로의 파일 복사
         * fileDir 복사할 파일명을 포함한 절대 경로 
         * tempFilePath 복사될 파일명을 포함한 절대경로
         */
        
        try 
        {
             //스트림 생성
             inputStream = new FileInputStream(fileDir);
             outputStream = new FileOutputStream(tempFilePath);
             //채널 생성
             fcin = inputStream.getChannel();
             fcout = outputStream.getChannel();
             
             //채널을 통한 스트림 전송
             long size = fcin.size();
             fcin.transferTo(0, size, fcout);

        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally 
        {
             //자원 해제
             try
             {
              fcout.close();
             }
             catch(IOException ioe){}
             try
             {
              fcin.close();
             }
             catch(IOException ioe){}
             try
             {
                 outputStream.close();
             }
             catch(IOException ioe){}
             try
             {
                 inputStream.close();
             }
             catch(IOException ioe){}
        }
        
        return true;
    }
    
}
