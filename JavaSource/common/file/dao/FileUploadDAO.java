package common.file.dao;

import common.file.MwareFile;
import common.file.dto.FileUploadDTO;
import common.spring.BaseJdbcDaoSupportIntf;

/**
 * ����÷��
 * @author javaworker
 * @version $Id: FileUploadDAO.java,v 1.2 2014/05/20 07:26:19 pochul2423 Exp $
 * @since 1.0
 */
public interface FileUploadDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * ���������� �����Ѵ�.
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
     * �̹��� �ش� ��ȣ�� ÷�������� ��ȸ�Ѵ�.
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
     * DB ÷������ ������ �����Ѵ�.
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
     * �̹��� �ش� ��ȣ�� ÷�������� ��ȸ�Ѵ�.
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
