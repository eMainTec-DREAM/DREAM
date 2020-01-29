package common.file.service;

import org.apache.struts.upload.FormFile;

import common.file.MwareFile;
import common.file.dto.FileUploadDTO;

/**
 * 문서 관리 - 상세
 * 
 * @author javaworker
 * @version $Id: FileUploadService.java,v 1.2 2013/10/11 01:12:47 pochul2423 Exp $
 * @since 1.0
 */
public interface FileUploadService
{
    /**
     * 문서 정보를 저장한다.
     * File 도 또한 저장한다.(물리적 파일 이름은 image no 시퀀스를 이용한다.)
     * @author  javaworker
     * @version $Id: FileUploadService.java,v 1.2 2013/10/11 01:12:47 pochul2423 Exp $
     * @since   1.0
     * 
     * @param fileUploadDTO
     * @throws Exception
     */
    int saveDocNew(FileUploadDTO fileUploadDTO, FormFile [] fileList) throws Exception;

    /**
     * 파일 정보 상세 내역을 조회한다.
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
     * 물리적 파일이름으로 파일정보를 조회한다.
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
     * SGDATA 파일 저장
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
