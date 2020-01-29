package common.file;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.jpeg.JpegDirectory;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import common.bean.MwareConfig;
import common.util.FileUtil;

/**
 * 첨부파일 upLoad
 * @author  javaworker
 * @version $Id: FileUploadUtil.java,v 1.5 2014/02/07 05:05:26 javaworker Exp $
 * @since   1.0
 *
 */
public class FileUploadUtil 
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FileUploadUtil.class);
    
    public static final String KOR_CHARSET = "MS949"; //EUC-KR
    public static final String ENG_CHARSET = "ISO-8859-1";
    public static String K2E(String korean) 
    {
         String english = null;
         
         if (korean == null ) {
             return null;
         }
         
         try { 
             english = new String(korean.getBytes(KOR_CHARSET), ENG_CHARSET);
         } catch (UnsupportedEncodingException e) {
             english = new String(korean);
         }

         return english;
     }
    
    public static Map<String,String> checkFile(List<FileItem> fileList, String fileType)
    {
    	int sysFileSize 		= Integer.parseInt(MwareConfig.getMaxFileSize()) * 1000000;
    	String sysFileExt		= MwareConfig.getFileType();
    	String sysImgFileExt 	= sysFileExt+MwareConfig.getImgFileType();
    	
    	Map<String,String> resultMap = new HashMap<String,String>();
    	resultMap.put("state","OK");
    	resultMap.put("info","");
    	
    	
    	if(fileList != null)
	    	for (FileItem item : fileList)
	        {
	    		
	    		if(item == null) continue;
	    		
	    		String filename = FilenameUtils.getName(item.getName());
	    		
	    		if (filename == null || "".equals(filename))
	            {
	                continue;
	            }

	    		if(item.getSize() > sysFileSize)
	    		{
	    			resultMap.put("state", "ERROR");
	    			resultMap.put("info", "Exceed the File Size limitation.");
	    			
	    			break;
	    		} 
	    		
	    		if((sysFileExt.toUpperCase()).indexOf(FilenameUtils.getExtension(item.getName()).toUpperCase()) > 0)
	    		{
	    			resultMap.put("state", "ERROR");
	    			resultMap.put("info", FilenameUtils.getExtension(item.getName())+", File Extension is not allowed.");
	    			
	    			break;
	    		}
	    		
	    		if("IMAGE".equals(fileType) && (sysImgFileExt.toUpperCase()).indexOf(FilenameUtils.getExtension(item.getName()).toUpperCase()) == -1)
	    		{
	    			resultMap.put("state", "ERROR");
	    			resultMap.put("info", FilenameUtils.getExtension(item.getName())+", File Extension is not allowed.");
	    			
	    			break;
	    		}
	    		
	        }
    	
    	return resultMap;
    }
    
    public static MwareFile doFileUpload(FormFile formFile, 
            String physicalName, String folerPath) throws FileNotFoundException, IOException 
    {   
        logger.debug("doFileUpload(FormFile) start!");
        
        if (formFile == null)
        {
            return null;
        }

        logger.debug("===================File Upload====================");
        logger.debug("File Name   : [" + K2E(formFile.getFileName()) + "]");
        logger.debug("ContentType : [" + formFile.getContentType() + "]");
        logger.debug("File Size   : [" + formFile.getFileSize() + "]");
        logger.debug("Image Type  : [" + folerPath + "]");
        logger.debug("==================================================");
        
        InputStream stream = formFile.getInputStream();
        
        // 첨부파일이 중복되지 않게 하기 위해
        
        String newFileName = "";
        if (physicalName == null || "".equals(physicalName))
        {
            return null;
        }

        newFileName = physicalName;
        // Long.toString(System.currentTimeMillis());
        
        //========================================
        // File 저장 경로
        String fileDir = MwareConfig.getFileDir();
        //========================================
        
        //fileDir = "C:\\GMMS_Service_Dev\\File_data\\";
        //File dir = new File(fileDir + folerPath + "\\");
        /*
         *Linux (In Linux, it make folder like this 'SAFETY\'
         */
        File dir = new File(fileDir + folerPath);
        
        if(!dir.exists())
        {
           dir.mkdirs();
        }
        
        //=============================================
        //folerPath을 사용할 경우 경로를 fileDir뒤에 folerPath을
        //=============================================
        //OutputStream bos = new FileOutputStream(fileDir + folerPath + "\\" + newFileName);
        /*
         * Linux
         */
        OutputStream bos = new FileOutputStream(fileDir + folerPath + "/" + newFileName);
        
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) 
        {
            bos.write(buffer, 0, bytesRead);
        }
        
        bos.close();
        stream.close();

        MwareFile mwareFile = new MwareFile();
        mwareFile.setFileName(K2E(formFile.getFileName()));
        mwareFile.setFileType(getFileType(mwareFile.getFileName()));
        mwareFile.setPhysicalFileName(newFileName);
        mwareFile.setFileSize(formFile.getFileSize());

        if (logger.isDebugEnabled())
        {
            logger.debug("doFileUpload(FormFile) end!");
        }
        
        return mwareFile;
    }
    
    /**
     * file Name에서 뒤의 확장자를 추출하여 
     * 리턴한다.
     * @author  javaworker
     * @version $Id: FileUploadUtil.java,v 1.5 2014/02/07 05:05:26 javaworker Exp $
     * @since   1.0
     * 
     * @param fileName
     * @return
     */
    private static String getFileType(String fileName) 
    {
        // fileName을 . 으로 토튼을 설정하면 가장 뒤에 토튼이 확장자가 된다. 
        
        StringTokenizer stFileName = new StringTokenizer(fileName, ".");
        
        String fileType = "";
        
        while (stFileName.hasMoreTokens())
        {
            fileType = stFileName.nextToken();
        }
        
        
        return fileType;
    }
    
    /**
     * WAS로 파일을 업로드 한다.
     * @author  javaworker
     * @version $Id: FileUploadUtil.java,v 1.5 2014/02/07 05:05:26 javaworker Exp $
     * @since   1.0
     * 
     * @param formFile
     * @param physicalName
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static MwareFile doFileUploadToWas(FormFile formFile, 
            String physicalName, String pFileDir) throws FileNotFoundException, IOException 
    {      
        if (logger.isDebugEnabled())
        {
            logger.debug("doFileUpload(FormFile) start!");
        }
        
        if (formFile == null || physicalName == null || "".equals(physicalName))
        {
            return null;
        }
        
        // 파일 이름 셋팅한다.
        String newFileName = physicalName;
        
        logger.debug("===================File Upload====================");
        logger.debug("File Name      : [" + formFile.getFileName() + "]");
        logger.debug("ContentType    : [" + formFile.getContentType() + "]");
        logger.debug("File Size      : [" + formFile.getFileSize() + "]");
        logger.debug("File Directory : [" + pFileDir + "]");
        logger.debug("==================================================");
        
        InputStream stream = formFile.getInputStream();

        //===========================================
        // File 저장 경로
        String fileDir = pFileDir;
        //===========================================
        
        OutputStream bos = new FileOutputStream(fileDir + newFileName);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) 
        {
            bos.write(buffer, 0, bytesRead);
        }
        
        bos.close();
        stream.close();

        MwareFile mwareFile = new MwareFile();
        mwareFile.setFileName(formFile.getFileName());
        mwareFile.setFileType(getFileType(mwareFile.getFileName()));
        mwareFile.setPhysicalFileName(newFileName);
        mwareFile.setFileSize(formFile.getFileSize());
        
        if (logger.isDebugEnabled())
        {
            logger.debug("doFileUpload(FormFile) end!");
        }
        
        return mwareFile;
    }

    /**
     * excelFileDir 의 uploadFileId 의 file 을
     * file 서버의 작업 dir 의  input_excel 의 디렉토리로 이동한다. 
     * @author  javaworker
     * @version $Id: FileUploadUtil.java,v 1.5 2014/02/07 05:05:26 javaworker Exp $
     * @since   1.0
     * 
     * @param excelFileDir
     * @param uploadFileId
     * @param input_excel
     */
    public static void doFileMoveToFileServer(String excelFileDir, 
            String uploadFileId, String inputExcel) throws Exception
    {
        
        // FTP로 업로드된 파일을 WAS 에서 삭제한다.
        File file = new File(excelFileDir + uploadFileId);
        
        // 이동될 파일의 경로 + 이름
        //String newFilePath = BaseForm.getFileDir() + inputExcel  + uploadFileId;
        String newFilePath = MwareConfig.getFileDir() + uploadFileId;
        
        // 파일이름
        file.renameTo(new File(newFilePath));
    }

    /**
     * 파일 복사시 이용
     * @author  javaworker
     * @version $Id: FileUploadUtil.java,v 1.5 2014/02/07 05:05:26 javaworker Exp $
     * @since   1.0
     * 
     * @param in
     * @param out
     * @throws IOException
     */
    public static void fileCopy(String originFilePath, String destFilePath) throws IOException 
    {
        logger.debug("FILE_PATH  : [" + originFilePath + "]");
        logger.debug("WAS_PATH   : [" + destFilePath + "]");
        
        File file = new File(originFilePath);
        
        if(!file.exists()) return;
        
        FileInputStream in   = null;
        FileOutputStream out = null;  
        
        BufferedInputStream bin  = null;
        BufferedOutputStream bos = null;
        
        try 
        {
            in = new FileInputStream(originFilePath);  
            out = new FileOutputStream(destFilePath);  
            
            bin = new BufferedInputStream(in);
            bos = new BufferedOutputStream(out);
        
            byte[] buf = new byte[2048]; //buffer size 2K.
            int read = 0;
            while ((read = bin.read(buf)) != -1) 
            {
                bos.write(buf,0,read);
            }
            
            bos.flush();
        }
        finally 
        {
            bos.close();
            bin.close();
            in.close();
            out.close();
        }        
    }
    
    /**
     * 파일 복사
     * @author  hiimkkm
     * @version $Id: FileUploadUtil.java,v 1.5 2014/02/07 05:05:26 javaworker Exp $
     * @since   1.0
     * 
     * @param originFilePath
     * @param destFilePath
     * @param destFolder
     * @throws IOException
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    public static String fileCopy(String originFilePath, String destFilePath, String destFolder) throws IOException, URISyntaxException, StorageException, InvalidKeyException 
    {
        logger.debug("FILE_PATH  : [" + originFilePath + "]");
        logger.debug("WAS_PATH   : [" + destFilePath + "]");

        String rtnVal= "";
        if("AZURE".equals(MwareConfig.getFileDir()))
        {
            CloudBlobClient serviceClient = FileUtil.getCloudBlobClient();
            CloudBlobContainer container = serviceClient.getContainerReference("dream");

            originFilePath= originFilePath.replace(MwareConfig.getFileDir(), "");
            destFilePath= destFilePath.replace( MwareConfig.getWebAppPath(), "");
            
            CloudBlockBlob blob = container.getBlockBlobReference(originFilePath);
            CloudBlockBlob destBlob = container.getBlockBlobReference(destFilePath);
           
            if (!blob.exists()) return rtnVal;
           
            destBlob.startCopy(blob);
            rtnVal = destBlob.getUri().toString();
        }
        else
        {         
            File originFile = new File(originFilePath);
            
            rtnVal = originFile.getName();
            if(!originFile.exists()) return rtnVal;

            File file = new File(destFolder);
            // 일치하는 폴더가 없으면 생성
            if(!file.exists())
            {
                file.mkdirs();
            }
            
            FileInputStream in   = null;
            FileOutputStream out = null;  
            
            BufferedInputStream bin  = null;
            BufferedOutputStream bos = null;
            
            try 
            {
                in = new FileInputStream(originFilePath);  
                out = new FileOutputStream(destFilePath);  
                
                bin = new BufferedInputStream(in);
                bos = new BufferedOutputStream(out);
                
                byte[] buf = new byte[2048]; //buffer size 2K.
                int read = 0;
                while ((read = bin.read(buf)) != -1) 
                {
                    bos.write(buf,0,read);
                }
                
                bos.flush();
            }
            finally 
            {
                bos.close();
                bin.close();
                in.close();
                out.close();
            }        
        }
        return rtnVal;
        
    }
    
    /**
     * image type 에 해당하는 파일을 WAS에서 삭제한다. 
     * @author  javaworker
     * @version $Id: FileUploadUtil.java,v 1.5 2014/02/07 05:05:26 javaworker Exp $
     * @since   1.0
     * 
     * @param imageType
     * @param imageNo
     * @return
     */
    public static boolean deleteFile(String imageType, String imageNo)
    {
    	String wasFilePath = MwareConfig.getFileDir() + imageType + File.separator + imageNo;

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

    /**
     * Dhtmlx Vault File Upload
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param item
     * @param physicalName
     * @param nfFilePath
     * @return
     * @throws IOException
     * @throws URISyntaxException 
     * @throws StorageException 
     * @throws InvalidKeyException 
     */
    public static MwareFile doFileUpload(FileItem item, String physicalName, String nfFilePath, String compNo) throws IOException, InvalidKeyException, StorageException, URISyntaxException
    {
        // Process form file field (input type="file").
        String fieldname = item.getFieldName();
        String filename = FilenameUtils.getName(item.getName());
        String validFileType = MwareConfig.getImgFileType();
        String fileExtension = FilenameUtils.getExtension(filename).toLowerCase();
        
//        MwareConfig.etCompanies()

        //int isValid = validFileType.indexOf(FilenameUtils.getExtension(filename));

        //모의해킹 테스트 보안처리 로직. jsp파일 우회업로드시 서버에서 validation 처리한다.
        if(fileExtension.equals("jsp")||fileExtension.equals("java"))
        {
        	//사용불가능 한 파일임.
        	throw new IOException();
        }
        else
        {
        	int filesize = 0;
            
            //========================================
            // File 저장 경로
            String fileDir = MwareConfig.getFileDir();
            
/*            File newFile = new File("test.txt");
            FileWriter writer = new FileWriter(newFile);
            writer.write("@2222222!!!!!!!!!!!!");
            writer.flush();
            
            System.out.println(newFile.getAbsolutePath());
            
            FileReader filereader = new FileReader(newFile);
            int singleCh = 0;
            while((singleCh = filereader.read()) != -1){
                System.out.print((char)singleCh);
            }
            filereader.close();*/

            if("AZURE".equals(fileDir))
            {
                  CloudBlobClient serviceClient = FileUtil.getCloudBlobClient();
                  CloudBlobContainer container = serviceClient.getContainerReference("dream");
                  container.createIfNotExists();
                  // Upload an image file.

                  CloudBlockBlob blob = container.getBlockBlobReference(nfFilePath + File.separator + physicalName);
//                  File sourceFile = new File("c:\\image1.jpg");
                  InputStream filecontent = item.getInputStream();

                  filesize = Integer.parseInt(item.getSize()+"");
                  blob.upload(filecontent, item.getSize());
                
            }
            else
            {
                //========================================
                //fileDir = "C:\\GMMS_Service_Dev\\File_data\\";
                //File dir = new File(fileDir + folerPath + "\\");
                /*
                 *Linux (In Linux, it make folder like this 'SAFETY\'
                 */
                File dir = new File(fileDir + nfFilePath);
                
                if(!dir.exists())
                {
                    dir.mkdirs();
                }
                
                InputStream filecontent = item.getInputStream();
                
                // Write to file
                File f=new File(fileDir + nfFilePath + "/" + physicalName);
                FileOutputStream fout=new FileOutputStream(f);
                byte buf[]=new byte[1024];
                int len;
                
                // 이미지타입인 경우 회전 검사
                if(MwareConfig.getImgFileType().toLowerCase().contains(fileExtension) && !"png".equals(fileExtension))
                {
                    try {
                        AffineTransform affineTransform = getExifTransformation(readImageInformation(filecontent));
                        BufferedImage bfi = transformImage(item.getInputStream(), affineTransform);
                        
                        ImageIO.write(bfi, fileExtension, fout);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
                while((len=filecontent.read(buf))>0) {
                    fout.write(buf,0,len);
                    filesize+=len;
                }
                fout.close(); 
            }
            
            MwareFile mwareFile = new MwareFile();
            mwareFile.setFileName(filename);
            mwareFile.setFileType(fileExtension);
            mwareFile.setPhysicalFileName(physicalName);
            mwareFile.setFileSize(filesize);
            
            return mwareFile;
        }
        
        
    }
    
    /**
     * URL File Upload
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param url
     * @param filename
     * @param physicalName
     * @param nfFilePath
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static MwareFile doUrlFileUpload(URL url, String filename, String physicalName, String nfFilePath) throws FileNotFoundException, IOException
    {
		MwareFile mwareFile = new MwareFile();
		int filesize = 0;
		
		//========================================
		// File 저장 경로
		String fileDir = MwareConfig.getFileDir();
		//========================================
		
		File dir = new File(fileDir + nfFilePath);		
		if(!dir.exists())
		{
			dir.mkdirs();
		}
		
		URLConnection uc = null;
		InputStream filecontent = null;		
		try {
		    uc = url.openConnection();
		    uc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		    uc.connect();
			filecontent = uc.getInputStream();
			// Write to file
			File f = new File(fileDir + nfFilePath + "/" + physicalName);
			FileOutputStream fout = new FileOutputStream(f);    		
			byte buf[] = new byte[1024];
			int len;
			while((len=filecontent.read(buf))>0)
			{
				fout.write(buf,0,len);
				filesize+=len;
			}
			fout.close(); 

			// 파일 검사
			Map<String,String> rstMap = FileUploadUtil.checkUrlFile(url, filename, "DOCUMENT", filesize);
			if(rstMap.containsValue("ERROR"))
	        {
		       	// 파일삭제
	        	f.delete();
	        	
	        	mwareFile = null;

	        	throw new Exception(rstMap.get("info").toString());	        	
	        }
			else{
	    		mwareFile.setFileName(filename);
	    		mwareFile.setFileType(FilenameUtils.getExtension(filename));
	    		mwareFile.setPhysicalFileName(physicalName);
	    		mwareFile.setFileSize(filesize);
			}
		
		} catch(FileNotFoundException fe){
			fe.printStackTrace();
        	mwareFile = null;
        	throw new FileNotFoundException("FILE NOT FOUND");
		}catch (IOException e) {
			e.printStackTrace();
        	mwareFile = null;
			throw new IOException("OTHER ERROR");
		}catch (Exception e) {
			e.printStackTrace();
        	mwareFile = null;
			throw new IOException(e.getMessage());
		}
		
		return mwareFile;
    	
    }

    /**
     * URL File Size, 확장자 확인 
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param fileUrl
     * @param filename
     * @param fileType
     * @param filesize
     * @return
     */
    public static Map<String,String> checkUrlFile(URL fileUrl, String filename, String fileType, int filesize)
    {
    	int sysFileSize 		= Integer.parseInt(MwareConfig.getMaxFileSize()) * 1000000;
    	String sysFileExt		= MwareConfig.getFileType();
    	String sysImgFileExt 	= sysFileExt+MwareConfig.getImgFileType();
    	
    	Map<String,String> resultMap = new HashMap<String,String>();
    	resultMap.put("state","OK");
    	resultMap.put("info","");
    			
		if(filesize > sysFileSize)
		{
			resultMap.put("state", "ERROR");
			resultMap.put("info", "Exceed the File Size limitation.");
		} 
		//모의해킹 테스트 보안처리 로직. jsp파일 우회업로드시 서버에서 validation 처리한다.
		if(FilenameUtils.getExtension(filename).equals("jsp")||FilenameUtils.getExtension(filename).equals("java"))
		{
			resultMap.put("state", "ERROR");
			resultMap.put("info", FilenameUtils.getExtension(filename)+", File Extension is not allowed.");
		}
		if((sysFileExt.toUpperCase()).indexOf(FilenameUtils.getExtension(filename).toUpperCase()) > 0)
		{
			resultMap.put("state", "ERROR");
			resultMap.put("info", FilenameUtils.getExtension(filename)+", File Extension is not allowed.");
		}		
		if("IMAGE".equals(fileType) && (sysImgFileExt.toUpperCase()).indexOf(FilenameUtils.getExtension(filename).toUpperCase()) == -1)
		{
			resultMap.put("state", "ERROR");
			resultMap.put("info", FilenameUtils.getExtension(filename)+", File Extension is not allowed.");
		}
		
    	return resultMap;
    }
    
    public static class ImageInformation {
        public final int orientation;
        public final int width;
        public final int height;

        public ImageInformation(int orientation, int width, int height) {
            this.orientation = orientation;
            this.width = width;
            this.height = height;
        }

        public String toString() {
            return String.format("%dx%d,%d", this.width, this.height, this.orientation);
        }
    }
    
    public static ImageInformation readImageInformation(InputStream filecontent)  throws IOException, MetadataException, ImageProcessingException {
    	Metadata metadata = ImageMetadataReader.readMetadata(filecontent);
    	Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
    	JpegDirectory jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);
    	
    	int orientation = 1;
    	try {
    	    
    	    if(directory != null) orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
    	    
    	} catch (MetadataException me) {
    		logger.warn("Could not get orientation");
    	}
    	int width = jpegDirectory.getImageWidth();
    	int height = jpegDirectory.getImageHeight();
    	
    	return new ImageInformation(orientation, width, height);
    }
    
    
    public static AffineTransform getExifTransformation(ImageInformation info) {

        AffineTransform t = new AffineTransform();

        switch (info.orientation) {
        case 1:
            break;
        case 2: // Flip X
            t.scale(-1.0, 1.0);
            t.translate(-info.width, 0);
            break;
        case 3: // PI rotation 
            t.translate(info.width, info.height);
            t.rotate(Math.PI);
            break;
        case 4: // Flip Y
            t.scale(1.0, -1.0);
            t.translate(0, -info.height);
            break;
        case 5: // - PI/2 and Flip X
            t.rotate(-Math.PI / 2);
            t.scale(-1.0, 1.0);
            break;
        case 6: // -PI/2 and -width
            t.translate(info.height, 0);
            t.rotate(Math.PI / 2);
            break;
        case 7: // PI/2 and Flip
            t.scale(-1.0, 1.0);
            t.translate(-info.height, 0);
            t.translate(0, info.width);
            t.rotate(  3 * Math.PI / 2);
            break;
        case 8: // PI / 2
            t.translate(0, info.width);
            t.rotate(  3 * Math.PI / 2);
            break;
        }
        return t;
    }
    
    public static BufferedImage transformImage(InputStream transformImage, AffineTransform transform) throws Exception {

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage image = ImageIO.read(transformImage);
        BufferedImage destinationImage = op.createCompatibleDestImage(image, (image.getType() == BufferedImage.TYPE_BYTE_GRAY) ? null : null );
        Graphics2D g = destinationImage.createGraphics();
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, destinationImage.getWidth(), destinationImage.getHeight());
        destinationImage = op.filter(image, destinationImage);
        
        return destinationImage;
    }
}