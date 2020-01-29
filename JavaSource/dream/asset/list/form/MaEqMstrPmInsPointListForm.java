package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointListDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 설비의 정기점검-항목 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsPointListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPmInsPointListForm"
 */
public class MaEqMstrPmInsPointListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  점검마스터 상세  */
    private MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO = new MaEqMstrPmInsDetailDTO();
    /**  점검항목 목록  */
    private MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO = new MaEqMstrPmInsPointListDTO();
    /**  점검 공통 */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
    
	public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrCommonDTO;
	}
	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO) {
		this.maPmMstrCommonDTO = maPmMstrCommonDTO;
	}
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	public MaEqMstrPmInsPointListDTO getMaEqMstrPmInsPointListDTO() {
		return maEqMstrPmInsPointListDTO;
	}
	public void setMaEqMstrPmInsPointListDTO(MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO) {
		this.maEqMstrPmInsPointListDTO = maEqMstrPmInsPointListDTO;
	}
	public MaEqMstrPmInsDetailDTO getMaEqMstrPmInsDetailDTO() {
		return maEqMstrPmInsDetailDTO;
	}
	public void setMaEqMstrPmInsDetailDTO(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO) {
		this.maEqMstrPmInsDetailDTO = maEqMstrPmInsDetailDTO;
	}
	
}
