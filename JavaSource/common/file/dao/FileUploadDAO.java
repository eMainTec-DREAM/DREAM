package common.file.dao;

import common.file.MwareFile;
import common.file.dto.FileUploadDTO;
import common.spring.BaseJdbcDaoSupportIntf;

/**
 * 파일첨부
 * @author javaworker
 * @version $Id: FileUploadDAO.java,v 1.2 2014/05/20 07:26:19 pochul2423 Exp $
 * @since 1.0
 */
public interface FileUploadDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * 파일정보를 저장한다.
     * @author  javaworker
     * @version $Id: FileUploadDAO.java,v 1.2 2014/05/20 07:26:19 pochul2423 Exp $
     * @since   1.0
     * 
     * @param mwareFile
     * @param fileUploadDTO
     * @return
     * @throws Exception
     */
    public int insertFileInfo(MwareFile mwareFile, FileUploadDTO fileUploadDTO);

            /**
     * 이미지 해더 번호로 첨부파일을 조회한다.
     * @author  javaworker
     * @version $Id: FileUploadDAO.java,v 1.2 2014/05/20 07:26:19 pochul2423 Exp $
     * @since   1.0
     * 
     * @param imageHdrNo
     * @return
     * @throws Exception
     */
    public MwareFile getFileInfo(String fileNo);

    /**
     * DB 첨부파일 내역을 삭제한다.
     * @author  javaworker
     * @version $Id: FileUploadDAO.java,v 1.2 2014/05/20 07:26:19 pochul2423 Exp $
     * @since   1.0
     * 
     * @param imageNo
     * @return
     * @throws Exception
     */
    public int deleteImage(String fileNo);
    
    /**
     * 이미지 해더 번호로 첨부파일을 조회한다.
     * @author  javaworker
     * @version $Id: FileUploadDAO.java,v 1.2 2014/05/20 07:26:19 pochul2423 Exp $
     * @since   1.0
     * 
     * @param imageHdrNo
     * @return
     * @throws Exception
     */
    public String[][] getFileInfoArray(FileUploadDTO fileUploadDTO);

    public MwareFile getImgFileInfo(String fileNo);
}
