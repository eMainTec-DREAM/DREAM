package common.file.dto;

import common.bean.BaseDTO;

/**
 * 파일첨부 - DTO
 * @author  javaworker
 * @version $Id: FileUploadDTO.java,v 1.2 2014/01/07 08:16:30 hiimkkm Exp $
 * @since   1.0
 */
public class FileUploadDTO extends BaseDTO
{
    /** object No */
    private String objectNo = "";
    /** Object 구분 */
    private String docType  = "";
    /** 첨부파일 표시되는 Tag ID */
    private String fileAttachTagId  = "";
    
    private String fileName = "";
    
    private String fileNo   = "";
    
    /** 조회할때 화면에 보이는 파일 최대 10개(파일이름, 파일사이즈, imageNo) */
    private String [][] fileInfoArray = null;
    
    /** 삭제시 처리 fileNo 최대 10개 */
    private String [] fileNoArray = new String[10];

    public String getFileNo()
    {
        return fileNo;
    }

    public void setFileNo(String fileNo)
    {
        this.fileNo = fileNo;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getDocType()
    {
        return docType;
    }

    public void setDocType(String docType)
    {
        this.docType = docType;
    }

    public String getObjectNo()
    {
        return objectNo;
    }

    public void setObjectNo(String objectNo)
    {
        this.objectNo = objectNo;
    }

    public String[][] getFileInfoArray()
    {
        return fileInfoArray;
    }

    public void setFileInfoArray(String[][] fileInfoArray)
    {
        this.fileInfoArray = fileInfoArray;
    }

    public String[] getFileNoArray()
    {
        return fileNoArray;
    }

    public void setFileNoArray(String[] fileNoArray)
    {
        this.fileNoArray = fileNoArray;
    }

    public String getFileAttachTagId()
    {
        return fileAttachTagId;
    }

    public void setFileAttachTagId(String fileAttachTagId)
    {
        this.fileAttachTagId = fileAttachTagId;
    }
}
