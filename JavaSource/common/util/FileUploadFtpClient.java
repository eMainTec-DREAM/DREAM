package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * 작성자 : 어셈블링블링
 * 설명 : ftp를 이용한 파일전송.
 */
public class FileUploadFtpClient {
 FTPClient ftpClient = null;
 
        //생성자를 통해 ftp 설정을 한다.
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
 

        //경로, 파일명, 파일을 받아 해당 경로에 파일을 파일명으로 저장한다.

 public boolean put(File file){
  
  boolean bReault = false;
  InputStream inputStream = null;
  try {
                        //경로를 / 구분
   /*String path_arr[] = path.split("/");
   for(int i = 0 ; i < path_arr.length ; i++){
    String directory = path_arr[i];
    if(directory != null && directory.length() > 0){
                                        //경로를 찾아 들어 간다.
                                        //경로가 없는 경우 경로 생성
     if(!ftpClient.changeWorkingDirectory(directory)){
      ftpClient.makeDirectory(directory);
      ftpClient.changeWorkingDirectory(directory);
     }else{
      ftpClient.changeWorkingDirectory(directory);
     }
    }
   }*/

          inputStream = new FileInputStream(file);
          //저장하고 경과 리턴받음.
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
 
        //ftp 연결을 해제한다.
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