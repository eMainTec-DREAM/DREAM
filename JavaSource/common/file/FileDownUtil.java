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
     *  ������ �ӽ������� �ٿ�ε�� ����Ѵ�.
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
        
        //��Ʈ��, ä�� ����
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        FileChannel fcin = null;
        FileChannel fcout = null;
        
        /**
         * fileDir���� tempFilePath������ ���� ����
         * fileDir ������ ���ϸ��� ������ ���� ��� 
         * tempFilePath ����� ���ϸ��� ������ ������
         */
        
        try 
        {
             //��Ʈ�� ����
             inputStream = new FileInputStream(fileDir);
             outputStream = new FileOutputStream(tempFilePath);
             //ä�� ����
             fcin = inputStream.getChannel();
             fcout = outputStream.getChannel();
             
             //ä���� ���� ��Ʈ�� ����
             long size = fcin.size();
             fcin.transferTo(0, size, fcout);

        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally 
        {
             //�ڿ� ����
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
