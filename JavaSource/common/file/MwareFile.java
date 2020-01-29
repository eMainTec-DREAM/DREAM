package common.file;

/**
 * 첨부 파일의 정보를 가지고 있다.
 * @author  javaworker
 * @version $Id: MwareFile.java,v 1.1 2013/08/30 09:12:33 javaworker Exp $
 * @since   1.0
 *
 */
public class MwareFile 
{
    /** 보여지는 파일이름 */
	private String fileName = "";
    /** File Type */
    private String fileType = "";
    /** 물리적 파일 이름 : 중복불가 */
	private String physicalFileName = "";
    /** File Size */
    private int fileSize = 0;
    /** image_type */
    private String imageType = "";
    
    private String docType  = "";
    /** A:첨부, I:이미지, D:도면 */
    private String fileGubun = "";
    
    private String nfFilePath   = "";
    
    
	public String getNfFilePath()
    {
        return nfFilePath;
    }

    public void setNfFilePath(String nfFilePath)
    {
        this.nfFilePath = nfFilePath;
    }

    public String getDocType()
    {
        return docType;
    }

    public void setDocType(String docType)
    {
        this.docType = docType;
    }

    public String getFileName() 
    {
		return fileName;
	}

	public void setFileName(String fileName) 
    {
		this.fileName = fileName;
	}

    public String getPhysicalFileName()
    {
        return physicalFileName;
    }

    public void setPhysicalFileName(String physicalFileName)
    {
        this.physicalFileName = physicalFileName;
    }

    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public int getFileSize()
    {
        return fileSize;
    }

    public void setFileSize(int fileSize)
    {
        this.fileSize = fileSize;
    }

    public String getImageType()
    {
        return imageType;
    }

    public void setImageType(String imageType)
    {
        this.imageType = imageType;
    }

    public String getFileGubun()
    {
        return fileGubun;
    }

    public void setFileGubun(String fileGubun)
    {
        this.fileGubun = fileGubun;
    }
}
