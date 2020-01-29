package dream.part.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrDetailDTO;

/**
 * 자재마스터 - 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maPtMstrDetailForm"
 */
public class MaPtMstrDetailForm extends BaseForm
{
    //========================================================================
    /** 자재마스터 공통 */ 
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
    //========================================================================
    /** 자재마스터 상세 */
    private MaPtMstrDetailDTO maPtMstrDetailDTO = new MaPtMstrDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }

    public MaPtMstrCommonDTO getMaPtMstrCommonDTO() {
		return maPtMstrCommonDTO;
	}

	public void setMaPtMstrCommonDTO(MaPtMstrCommonDTO maPtMstrCommonDTO) 
	{
		this.maPtMstrCommonDTO = maPtMstrCommonDTO;
	}

	public MaPtMstrDetailDTO getMaPtMstrDetailDTO() {
		return maPtMstrDetailDTO;
	}

	public void setMaPtMstrDetailDTO(MaPtMstrDetailDTO maPtMstrDetailDTO) 
	{
		this.maPtMstrDetailDTO = maPtMstrDetailDTO;
	}
}
