package dream.work.rpt.mabdpoint.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointDetailDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoRsltListDTO;

/**
 * 이상점검조치 - 작업결과 목록 form
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maBdPointWoRsltListForm"
 */
public class MaBdPointWoRsltListForm extends BaseForm
{    
    //========================================================================
    /** 이상점검조치 공통 DTO */ 
    private MaBdPointCommonDTO maBdPointCommonDTO = new MaBdPointCommonDTO();
    //========================================================================
    /** 이상점검조치 상세 */
    private MaBdPointDetailDTO maBdPointDetailDTO = new MaBdPointDetailDTO();
    
    /** 이상점검조치 - 작업결과 목록  DTO */
    private MaBdPointWoRsltListDTO maBdPointWoRsltListDTO = new MaBdPointWoRsltListDTO();
    
    /** 작업 공통 */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /** 작업결과 상세 */
    private MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = new MaWoResultMstrDetailDTO();
    
    
	public MaBdPointCommonDTO getMaBdPointCommonDTO() {
		return maBdPointCommonDTO;
	}
	public void setMaBdPointCommonDTO(MaBdPointCommonDTO maBdPointCommonDTO) {
		this.maBdPointCommonDTO = maBdPointCommonDTO;
	}
	public MaBdPointDetailDTO getMaBdPointDetailDTO() {
		return maBdPointDetailDTO;
	}
	public void setMaBdPointDetailDTO(MaBdPointDetailDTO maBdPointDetailDTO) {
		this.maBdPointDetailDTO = maBdPointDetailDTO;
	}
	public MaBdPointWoRsltListDTO getMaBdPointWoRsltListDTO() {
		return maBdPointWoRsltListDTO;
	}
	public void setMaBdPointWoRsltListDTO(MaBdPointWoRsltListDTO maBdPointWoRsltListDTO) {
		this.maBdPointWoRsltListDTO = maBdPointWoRsltListDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
	public MaWoResultMstrDetailDTO getMaWoResultMstrDetailDTO() {
		return maWoResultMstrDetailDTO;
	}
	public void setMaWoResultMstrDetailDTO(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {
		this.maWoResultMstrDetailDTO = maWoResultMstrDetailDTO;
	}
}
