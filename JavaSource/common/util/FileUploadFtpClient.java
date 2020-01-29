package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * �ۼ��� : �������
 * ���� : ftp�� �̿��� ��������.
 */
public class FileUploadFtpClient {
 FTPClient ftpClient = null;
 
        //�����ڸ� ���� ftp ������ �Ѵ�.
 public FileUploadFtpClient(String url, int port, String id, String pw){
  this.ftpClient = new FTPClient();
  
  try {
   ftpClient.connect(url, port);
         ftpClient.login(id, pw);
//         System.out.println("!!!!"+ftpClient.isConnected());
         ftpClient.enterLocalPassiveMode();
         ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//         ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
         ftpClient.setControlEncoding("euc-kr");

  }catch (IOException ex) {
            //System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
 }
 

        //���, ���ϸ�, ������ �޾� �ش� ��ο� ������ ���ϸ����� �����Ѵ�.

 public boolean put(File file){
  
  boolean bReault = false;
  InputStream inputStream = null;
  try {
                        //��θ� / ����
   /*String path_arr[] = path.split("/");
   for(int i = 0 ; i < path_arr.length ; i++){
    String directory = path_arr[i];
    if(directory != null && directory.length() > 0){
                                        //��θ� ã�� ��� ����.
                                        //��ΰ� ���� ��� ��� ����
     if(!ftpClient.changeWorkingDirectory(directory)){
      ftpClient.makeDirectory(directory);
      ftpClient.changeWorkingDirectory(directory);
     }else{
      ftpClient.changeWorkingDirectory(directory);
     }
    }
   }*/

          inputStream = new FileInputStream(file);
          //�����ϰ� ��� ���Ϲ���.
          String fname = new String(file.getName().getBytes("euc-kr"), "iso_8859_1");
          bReault = ftpClient.storeFile(fname, inputStream);
          System.out.println("bReault" + " : " + bReault);
  }catch (IOException ex) {
   bReault = false;
            //System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }finally{
         if(inputStream != null){
    try {
     inputStream.close();
    } catch (IOException e) {
     e.printStackTrace();
    }
   }
         disconnect();
        }
  return bReault;
 }
 
        //ftp ������ �����Ѵ�.
 public void disconnect(){
   try {
             if (ftpClient.isConnected()) {
                 ftpClient.logout();
                 ftpClient.disconnect();
             }
         } catch (IOException ex) {
             ex.printStackTrace();
         }
 }
 
}