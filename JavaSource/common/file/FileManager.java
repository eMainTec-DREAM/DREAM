package common.file;

import java.io.*;
import java.util.Random;
import org.apache.struts.upload.FormFile;
 
public class FileManager
{
    private String path;
    private String filePath;
    private String fileName;
    private FormFile file;
    private String userId;

    public FileManager(String path)
    {
        this.path = path;
    }

    public  String getFileName()
    {
        return fileName;
    }

    public  String getFilePath()
    {
        return filePath;
    }

    public  void setFile(FormFile file)
    {
        this.file = file;
    }
    
    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    private  String getSaveFileName()
    {
        String fileseparator = "/";
        File currentPath = new File(path);
        if(!currentPath.exists())
            currentPath.mkdirs();
        String sFile = null;
        String list[] = null;
//        FileNameFIlter searchKey = new FileNameFIlter();
        Random random = new Random(System.currentTimeMillis());
//        do
//        {
            sFile = String.valueOf(System.currentTimeMillis()) + String.valueOf(random.nextLong()) + ".upl";
//            searchKey.setFileName(sFile);
//            list = currentPath.list(searchKey);
//        } while(list.length > 0);
        return sFile;
    }

    public  String save(FormFile file, String objectNo)
    {
        String fileseparator = "/";
        if(file != null && file.getFileSize() > 0)
        {
            setFile(file);
//            setUserId(objectNo+".jpg");
            save();
            return getFilePath() + "/" + getFileName();
        } else
        {
            return "";
        }
    }

    public  void save()
    {
        if(null == file)
            return;
        String fileseparator = "/";
        fileName = file.getFileName();
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream stream = file.getInputStream();
            filePath = getSaveFileName();
            OutputStream bos = new FileOutputStream(path + fileseparator + fileName);

            int bytesRead = 0;
            byte buffer[] = new byte[1024];
            while((bytesRead = stream.read(buffer, 0, 1024)) != -1) 
                bos.write(buffer, 0, bytesRead);
            bos.close();
            stream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
