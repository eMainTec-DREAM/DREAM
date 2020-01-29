package dream.mgr.exldata.form;

import common.file.FileForm;
import dream.mgr.exldata.dto.MgrExldataUploadDetailDTO;

/**
 * 엑셀업로드- 상세 Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="mgrExldataUploadDetailForm"
 */
public class MgrExldataUploadDetailForm extends FileForm
{
    //========================================================================
    /** 메뉴 상세 */
    private MgrExldataUploadDetailDTO mgrExldataUploadDetailDTO = new MgrExldataUploadDetailDTO();
    /** 파일명 */
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
