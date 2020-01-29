package common.file.form;

import common.file.FileForm;
import common.file.dto.FileUploadDTO;

/**
 * 파일첨부
 * @author  javaworker
 * @version $Id: FileUploadForm.java,v 1.1 2013/08/30 09:13:08 javaworker Exp $
 * @since   1.0
 * @struts.form name="fileUploadForm"
 */
public class FileUploadForm extends FileForm
{
    /** File DTO */
    private FileUploadDTO fileUploadDTO = new FileUploadDTO();
    
    /** 수정가능여부 */
    private String updating = "";
    
    public synchronized FileUploadDTO getFileUploadDTO()
    {
        return fileUploadDTO;
    }

    public synchronized void setFileUploadDTO(FileUploadDTO fileUploadDTO)
    {
        this.fileUploadDTO = fileUploadDTO;
    }

    public String getUpdating()
    {
        return updating;
    }

    public void setUpdating(String updating)
    {
        this.updating = updating;
    }
}