package common.file.service;

import org.apache.struts.upload.FormFile;

import common.file.MwareFile;
import common.file.dto.FileUploadDTO;

/**
 * ���� ���� - ��
 * 
 * @author javaworker
 * @version $Id: FileUploadService.java,v 1.2 2013/10/11 01:12:47 pochul2423 Exp $
 * @since 1.0
 */
public interface FileUploadService
{
    /**
     * ���� ������ �����Ѵ�.
     * File �� ���� �����Ѵ�.(������ ���� �̸��� image no �������� �̿��Ѵ�.)
     * @author  javaworker
     * @version $Id: FileUploadService.java,v 1.2 2013/10/11 01:12:47 pochul2423 Exp $
     * @since   1.0
     * 
     * @param fileUploadDTO
     * @throws Exception
     */
    int saveDocNew(FileUploadDTO fileUploadDTO, FormFile [] fileList) throws Exception;

    /**
     * ���� ���� �� ������ ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: FileUploadService.java,v 1.2 2013/10/11 01:12:47 pochul2423 Exp $
     * @since   1.0
     * 
     * @param sdDocMngCommonDTO
     * @return
     * @throws Exception
     */
    void findDocMngNew(FileUploadDTO fileUploadDTO);

    /**
     * ������ �����̸����� ���������� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: FileUploadService.java,v 1.2 2013/10/11 01:12:47 pochul2423 Exp $
     * @since   1.0
     * 
     * @param realName
     * @param fileType 
     * @return
     * @throws Exception
     */
    MwareFile getFileInfo(String realName, String fileType);
    
    /**
     * SGDATA ���� ����
     * @author  jung7126
     * @version $Id: FileUploadService.java,v 1.2 2013/10/11 01:12:47 pochul2423 Exp $
     * @since   1.0
     * 
     * @param fileList
     * @param fileUploadDTO
     * @return
     * @throws Exception
     */
    int fileUploadForUser(FormFile [] fileList, FileUploadDTO fileUploadDTO) throws Exception;
}
