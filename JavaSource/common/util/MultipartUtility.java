package common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

 
public class MultipartUtility {
    private static final String DEFAULT_ENCODING = "UTF-8";
     
    private String url;
    private MultipartEntityBuilder params;

    public MultipartUtility(String url){
        this.url = url;
         
        params = MultipartEntityBuilder.create();
        
    }

    public MultipartUtility addParam(Map<String, Object> param){
        return addParam(param, DEFAULT_ENCODING);
    }

    public MultipartUtility addParam(Map<String, Object> param, String encoding){
        for( Map.Entry<String, Object> e : param.entrySet() ){
            if (e.getValue() instanceof File) {
                addParam(e.getKey(), (File)e.getValue(), encoding);
            }else{
                addParam(e.getKey(), (String)e.getValue(), encoding);
            }
        }
        return this;
    }
     

    public MultipartUtility addParam(String name, String value){
        return addParam(name, value, DEFAULT_ENCODING);
    }
     
    public MultipartUtility addParam(String name, String value, String encoding){
        params.addPart(name, new StringBody(value, ContentType.create("text/plain", encoding)));
        return this;
    }
     
 
    public MultipartUtility addParam(String name, File file){
        return addParam(name, file, DEFAULT_ENCODING);
    }
     
    public MultipartUtility addParam(String name, File file, String encoding){
        if( file.exists() ){
            try{
                params.addPart(
                        name, 
                        new FileBody(file, ContentType.create("application/octet-stream"),
                        URLEncoder.encode(file.getName(), encoding)));
            }catch( Exception ex ){ ex.printStackTrace(); }
             
        }
         
        return this;
    }
 
  
    public String submit() throws Exception{
    	
//    	int timeout = 300; //Timeout 
//    	RequestConfig.Builder requestBuilder = RequestConfig.custom();
//    	requestBuilder = requestBuilder.setConnectTimeout(timeout*1000);
//    	requestBuilder = requestBuilder.setConnectionRequestTimeout(timeout*1000);
    	
    	HttpClientBuilder  http = HttpClients.custom();
    	
//    	http.setDefaultRequestConfig(requestBuilder.build());

        StringBuffer result = new StringBuffer();
        
        try{
            HttpPost post = new HttpPost(url);
            post.setEntity(params.build());
 
            long stime = System.currentTimeMillis(); 
        	SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        	String str = dayTime.format(new Date(stime));

            CloseableHttpClient client = http.build(); // execute(post);
            CloseableHttpResponse response = client.execute(post);
            
            
            try{
                HttpEntity res = response.getEntity();
                BufferedReader br = new BufferedReader(
                                    new InputStreamReader(res.getContent(), Charset.forName("UTF-8")));
                 
                String buffer = null;
                
                String etr = dayTime.format(new Date(System.currentTimeMillis() ));

                while( (buffer=br.readLine())!=null ){
                    result.append(buffer).append("\r\n");
                }
                
                String etr2 = dayTime.format(new Date(System.currentTimeMillis() ));
            	
            }finally{
                response.close();
            }
        }finally{
//            http. close();
        }
 
        return result.toString();
    }
     
    public static int upload(String localFilePath, String remoteFilePath, String fileName, HttpServletRequest request) throws Exception { 
        
        FTPClient ftp = null; // FTP Client 
        FileInputStream fis = null; // File Input Stream 
        File uploadfile = new File(localFilePath); // File 
        String url = "10.3.7.98"; 
        String id = "tmms"; 
        String pwd = "t1m2m3s4!@#$"; 
        String port = "21"; 
        
        int result = -1; try{ ftp = new FTPClient(); // FTP Client 
        ftp.setControlEncoding("UTF-8"); // 
        ftp.connect(url, Integer.parseInt(port)); // 
        ftp.login(id, pwd); // FTP  
        ftp.enterLocalPassiveMode(); // Passive Mode 
        ftp.changeWorkingDirectory(remoteFilePath); 
        ftp.setFileType(FTP.BINARY_FILE_TYPE); //  
        try{ 
            fis = new FileInputStream(uploadfile); // 
            boolean isSuccess = ftp.storeFile(fileName, fis); // File 
            if (isSuccess){ 
                result = 1; // 
                } else{  } 
            } catch(IOException ex){ 
                System.out.println("IO Exception : " + ex.getMessage()); 
                }finally{ if (fis != null){ try{ fis.close(); // Stream 
                return result; } catch(IOException ex){ 
                    System.out.println("IO Exception : " + ex.getMessage()); 
                    } } } 
        ftp.logout(); // FTP Log Out 
        }catch(IOException e){ System.out.println("IO:"+e.getMessage()); }
        finally{ if (ftp != null && ftp.isConnected()){ try{ ftp.disconnect(); //
        return result; 
        } catch (IOException e){ 
            System.out.println("IO Exception : " + e.getMessage()); 
            } }
        } return result; 
        
    }


    public static void main(String[] args) throws Exception {

        byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
        String secretKey   = "12345678901234567890123456789012"; //32bit
        
        byte[] textBytes = Base64.decodeBase64("NMvjHN5HM71FXXFYYqhnVQ==");
        System.out.println(DateUtil.getDayInterval("20180528",DateUtil.getDate()));
        //byte[] textBytes = str.getBytes("UTF-8");
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
        System.out.println(new String(cipher.doFinal(textBytes), "UTF-8"));
        
        System.out.println(CommonUtil.aesEncodeString("2160061"));
        
//        System.out.println(AES256Cipher.getInstance("12345678912345678912345678912345").AES_Encode("test"));
//        
//        System.out.println(AES256Cipher.getInstance("12345678912345678912345678912345").AES_Decode("xGUqqDYVhKlbTN9NOyA3Zw=="));

    }
}
