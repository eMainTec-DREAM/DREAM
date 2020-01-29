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
    /** �ִ� ���� ���� ��ŭ �迭�� �����Ѵ�. */
    private FormFile [] fileList = new FormFile[500];
    
    private FormFile file1;

    public FormFile [] getFileList()
    {
        return fileList;
    }

    /**
     * struts formFile ����<br>
     * - struts 1.2.7������ setFileList(fileList) �̿� ���� �����ϸ� <br>
     * fileList �迭�� �����Ͽ� �ش�. ������ 1.3.10������ �Ʒ��� ���� index �Ķ����<br>
     * �߰��Ǿ ���� �޾ƾ� �Ѵ�.
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
