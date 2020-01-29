package common.file.service.spring;

import org.apache.struts.upload.FormFile;

import common.bean.MwareConfig;
import common.file.FileManager;
import common.file.FileUploadUtil;
import common.file.MwareFile;
import common.file.dao.FileUploadDAO;
import common.file.dto.FileUploadDTO;
import common.file.service.FileUploadService;

/**
 * ����÷��
 * @author  javaworker
 * @version $Id: FileUploadServiceImpl.java,v 1.4 2014/01/07 08:22:29 hiimkkm Exp $
 * @since   1.0
 * @spring.bean id="fileUploadServiceTarget"
 * @spring.txbn id="fileUploadService"
 * @spring.property name="fileUploadDAO" ref="fileUploadDAO"
 */
public class FileUploadServiceImpl implements FileUploadService
{
    private FileUploadDAO fileUploadDAO = null;

    public FileUploadDAO getFileUploadDAO()
    {
        return fileUploadDAO;
    }

    public void setFileUploadDAO(FileUploadDAO fileUploadDAO)
    {
        this.fileUploadDAO = fileUploadDAO;
    }

    public int saveDocNew(FileUploadDTO fileUploadDTO, FormFile [] fileList) throws Exception
    {
        // ������ ������û�� ���ϵ��� DB ���� �����Ѵ�.
        String [] fileNoArray = fileUploadDTO.getFileNoArray();
        for (int i=0; i<fileNoArray.length; i++)
        {
            String fileNo = fileNoArray[i];
            
            if (fileNo != null && !"".equals(fileNo))
            {
                // ÷������ ���� ���� ����
                fileUploadDAO.deleteImage(fileNo);
                
                // WAS�� ���ε�� ���� ����
                FileUploadUtil.deleteFile(fileUploadDTO.getDocType(), fileNo);
            }
        }
        
        // ÷������ ������ �ش� ������ �� ������ DB�� �����Ѵ�.
        return fileUpload(fileList, fileUploadDTO);
    }

    /**
     * ������ ���ε� �Ѵ�.
     * @author  javaworker
     * @version $Id: FileUploadServiceImpl.java,v 1.4 2014/01/07 08:22:29 hiimkkm Exp $
     * @since   1.0
     * 
     * @param sdPdsNewDTO
     * @return
     * @throws Exception
     */
    private int fileUpload(FormFile [] fileList, FileUploadDTO fileUploadDTO) throws Exception
    {        
        int result = 0;
        
        if (fileList == null) return 0;
        
        for (int i=0; i < fileList.length; i++)
        {
            if (fileList[i] == null || "".equals(fileList[i].getFileName()))
            {
                continue;
            }
            
            //================================================================================
            // ÷�������� upload �Ѵ�.
            String fileNo = fileUploadDAO.getNextSequence("SQ2FILE_NO")+"";
            MwareFile mwareFile = FileUploadUtil.doFileUpload(fileList[i], 
                    fileNo, fileUploadDTO.getDocType());
            //================================================================================
            
            fileUploadDTO.setFileNo(fileNo);
            fileUploadDTO.setFileName(fileList[i].getFileName());
            
            // ����� ���������� DB �� �����Ѵ�.
            result += fileUploadDAO.insertFileInfo(mwareFile, fileUploadDTO);
        }
        
        return result;
    }
    
    public void findDocMngNew(FileUploadDTO fileUploadDTO)
    {
        // equip_no, image_type, object_no�� ��ȸ�Ѵ�.
        fileUploadDTO.setFileInfoArray(fileUploadDAO.getFileInfoArray(fileUploadDTO));
    }

    public MwareFile getFileInfo(String fileNo, String fileType)
    {
        MwareFile mwareFile = null;
        if("IMAGE".equals(fileType))
        {
            mwareFile = fileUploadDAO.getImgFileInfo(fileNo);
        }
        else if("DOCUMENT".equals(fileType))
        {
            mwareFile = fileUploadDAO.getFileInfo(fileNo);
        }
        
        return mwareFile;
    }

    public int fileUploadForUser(FormFile[] fileList, FileUploadDTO fileUploadDTO) throws Exception
    {
        int result = 0;
        
        if (fileList == null) return 0;
        
        for (int i=0; i < fileList.length; i++)
        {
            if (fileList[i] == null || "".equals(fileList[i].getFileName()))
            {
                continue;
            }
            
            String path = MwareConfig.getWebAppPath()+"/common/upload"; 
            
            FileManager fileManager = new FileManager(path);
            //================================================================================
            // ÷�������� upload �Ѵ�.FormFile [] fileList
            String pathName = fileManager.save(fileList[i],fileUploadDTO.getObjectNo());
            //pathName�� ��� �Է�.
            //================================================================================

            fileUploadDTO.setFileName(fileList[i].getFileName());
            fileUploadDTO.setFileNo(pathName);
        }
        
        return result;
    }
}
