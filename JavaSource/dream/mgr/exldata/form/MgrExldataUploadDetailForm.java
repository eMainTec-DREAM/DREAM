package dream.mgr.exldata.form;

import common.file.FileForm;
import dream.mgr.exldata.dto.MgrExldataUploadDetailDTO;

/**
 * �������ε�- �� Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="mgrExldataUploadDetailForm"
 */
public class MgrExldataUploadDetailForm extends FileForm
{
    //========================================================================
    /** �޴� �� */
    private MgrExldataUploadDetailDTO mgrExldataUploadDetailDTO = new MgrExldataUploadDetailDTO();
    /** ���ϸ� */
    private String[] fileNames = null;
    
    
    public String[] getFileNames()
    {
        return fileNames;
    }

    public void setFileNames(String[] fileNames)
    {
        this.fileNames = fileNames;
    }
    
	public MgrExldataUploadDetailDTO getMgrExldataUploadDetailDTO() {
		return mgrExldataUploadDetailDTO;
	}

	public void setMgrExldataUploadDetailDTO(MgrExldataUploadDetailDTO mgrExldataUploadDetailDTO) {
		this.mgrExldataUploadDetailDTO = mgrExldataUploadDetailDTO;
	}

}
