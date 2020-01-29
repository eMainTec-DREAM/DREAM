package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsListDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;

/**
 * 설비 정기점검 상세
 * @author  kim2107
 * @version $Id: MaEqMstrPmInsDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPmInsDetailForm"
 */
public class MaEqMstrPmInsDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** 설비 예방점검 목록 DTO  */
    private MaEqMstrPmInsListDTO maEqMstrPmInsListDTO = new MaEqMstrPmInsListDTO();
	/** 설비 예방점검 상세 DTO  */
    private MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO = new MaEqMstrPmInsDetailDTO();
    /**  점검 공통 */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
    private MaPmMstrDetailDTO maPmMstrDetailDTO = new MaPmMstrDetailDTO();
    
    
	public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrCommonDTO;
	}
	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO) {
		this.maPmMstrCommonDTO = maPmMstrCommonDTO;
	}
	public MaPmMstrDetailDTO getMaPmMstrDetailDTO() {
		return maPmMstrDetailDTO;
	}
	public void setMaPmMstrDetailDTO(MaPmMstrDetailDTO maPmMstrDetailDTO) {
		this.maPmMstrDetailDTO = maPmMstrDetailDTO;
	}
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	public MaEqMstrPmInsListDTO getMaEqMstrPmInsListDTO() {
		return maEqMstrPmInsListDTO;
	}
	public void setMaEqMstrPmInsListDTO(MaEqMstrPmInsListDTO maEqMstrPmInsListDTO) {
		this.maEqMstrPmInsListDTO = maEqMstrPmInsListDTO;
	}
	public MaEqMstrPmInsDetailDTO getMaEqMstrPmInsDetailDTO() {
		return maEqMstrPmInsDetailDTO;
	}
	public void setMaEqMstrPmInsDetailDTO(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO) {
		this.maEqMstrPmInsDetailDTO = maEqMstrPmInsDetailDTO;
	}
}
