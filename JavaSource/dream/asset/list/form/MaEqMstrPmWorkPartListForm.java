package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartListDTO;

/**
 * 설비의 정기작업-부품 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkPartListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPmWorkPartListForm"
 */
public class MaEqMstrPmWorkPartListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  점검마스터 상세  */
    private MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO = new MaEqMstrPmWorkDetailDTO();
    /**  부품 목록  */
    private MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO = new MaEqMstrPmWorkPartListDTO();
    
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	public MaEqMstrPmWorkPartListDTO getMaEqMstrPmWorkPartListDTO() {
		return maEqMstrPmWorkPartListDTO;
	}
	public void setMaEqMstrPmWorkPartListDTO(MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO) {
		this.maEqMstrPmWorkPartListDTO = maEqMstrPmWorkPartListDTO;
	}
	public MaEqMstrPmWorkDetailDTO getMaEqMstrPmWorkDetailDTO() {
		return maEqMstrPmWorkDetailDTO;
	}
	public void setMaEqMstrPmWorkDetailDTO(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO) {
		this.maEqMstrPmWorkDetailDTO = maEqMstrPmWorkDetailDTO;
	}
	
}
