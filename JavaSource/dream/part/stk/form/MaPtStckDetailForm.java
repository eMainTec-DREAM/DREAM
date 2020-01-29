package dream.part.stk.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.part.list.dto.MaPtMstrDetailDTO;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.dto.MaPtStckDetailDTO;

/**
 * 자재재고- 상세 Form
 * @author  ssong
 * @version $Id: MaPtStckDetailForm.java,v 1.0 2015/12/02 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="maPtStckDetailForm"
 */
public class MaPtStckDetailForm extends BaseForm
{
    //========================================================================
    /** 자재재고 공통 */ 
    private MaPtStckCommonDTO maPtStckCommonDTO = new MaPtStckCommonDTO();
    //========================================================================
    /** 자재재고 상세 */
    private MaPtStckDetailDTO maPtStckDetailDTO = new MaPtStckDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

    /** 자재마스터 상세 */
    private MaPtMstrDetailDTO maPtMstrDetailDTO = new MaPtMstrDetailDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public MaPtMstrDetailDTO getMaPtMstrDetailDTO() {
		return maPtMstrDetailDTO;
	}

	public void setMaPtMstrDetailDTO(MaPtMstrDetailDTO maPtMstrDetailDTO) {
		this.maPtMstrDetailDTO = maPtMstrDetailDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }

    public MaPtStckCommonDTO getMaPtStckCommonDTO() {
		return maPtStckCommonDTO;
	}

    public void setMaPtStckCommonDTO(MaPtStckCommonDTO maPtStckCommonDTO) {
		this.maPtStckCommonDTO = maPtStckCommonDTO;
	}

	public MaPtStckDetailDTO getMaPtStckDetailDTO() {
		return maPtStckDetailDTO;
	}

	public void setMaPtStckDetailDTO(MaPtStckDetailDTO maPtStckDetailDTO) {
		this.maPtStckDetailDTO = maPtStckDetailDTO;
	}
}
