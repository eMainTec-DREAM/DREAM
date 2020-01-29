package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartListDTO;

/**
 * 설비 정기작업-부품 상세
 * @author  kim2107
 * @version $Id: MaEqMstrPmWorkPartDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPmWorkPartDetailForm"
 */
public class MaEqMstrPmWorkPartDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** 설비 예방작업 상세 DTO  */
    private MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO = new MaEqMstrPmWorkDetailDTO();
	/** 설비 예방작업 부품 항목 목록 DTO  */
    private MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO = new MaEqMstrPmWorkPartListDTO();
	/** 설비 예방작업 부품 항목 상세 DTO  */
    private MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO = new MaEqMstrPmWorkPartDetailDTO();
    
    
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	public MaEqMstrPmWorkDetailDTO getMaEqMstrPmWorkDetailDTO() {
		return maEqMstrPmWorkDetailDTO;
	}
	public void setMaEqMstrPmWorkDetailDTO(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO) {
		this.maEqMstrPmWorkDetailDTO = maEqMstrPmWorkDetailDTO;
	}
	public MaEqMstrPmWorkPartListDTO getMaEqMstrPmWorkPartListDTO() {
		return maEqMstrPmWorkPartListDTO;
	}
	public void setMaEqMstrPmWorkPartListDTO(MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO) {
		this.maEqMstrPmWorkPartListDTO = maEqMstrPmWorkPartListDTO;
	}
	public MaEqMstrPmWorkPartDetailDTO getMaEqMstrPmWorkPartDetailDTO() {
		return maEqMstrPmWorkPartDetailDTO;
	}
	public void setMaEqMstrPmWorkPartDetailDTO(MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO) {
		this.maEqMstrPmWorkPartDetailDTO = maEqMstrPmWorkPartDetailDTO;
	}
}
