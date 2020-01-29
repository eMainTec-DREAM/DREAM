package common.file;

import org.apache.struts.upload.FormFile;

import common.struts.BaseForm;

/**
 * 
 * @author  javaworker
 * @version $Id: FileForm.java,v 1.1 2013/08/30 09:12:34 javaworker Exp $
 * @since   1.0
 *
 */
public class FileForm extends BaseForm
{
    /** 최대 파일 갯수 만큼 배열을 셋팅한다. */
    private FormFile [] fileList = new FormFile[500];
    
    private FormFile file1;

    public FormFile [] getFileList()
    {
        return fileList;
    }

    /**
     * struts formFile 셋팅<br>
     * - struts 1.2.7에서는 setFileList(fileList) 이와 같이 선언하면 <br>
     * fileList 배열로 셋팅하여 준다. 하지만 1.3.10에서는 아래와 같이 index 파라메터<br>
     * 추가되어서 같이 받아야 한다.
     * @author  javaworker
     * @version $Id: FileForm.java,v 1.1 2013/08/30 09:12:34 javaworker Exp $
     * @since   1.0
     * 
     * @param index
     * @param formFile
     */
    public void setFileList(int index, FormFile formFile)
    {
        this.fileList[index] = formFile;
    }

    public FormFile getFile1()
    {
        return file1;
    }

    public void setFile1(FormFile file1)
    {
        this.file1 = file1;
    }
}
