package dream.work.rpt.mabdpoint.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointDetailDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoRsltListDTO;

/**
 * �̻�������ġ - �۾���� ��� form
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maBdPointWoRsltListForm"
 */
public class MaBdPointWoRsltListForm extends BaseForm
{    
    //========================================================================
    /** �̻�������ġ ���� DTO */ 
    private MaBdPointCommonDTO maBdPointCommonDTO = new MaBdPointCommonDTO();
    //========================================================================
    /** �̻�������ġ �� */
    private MaBdPointDetailDTO maBdPointDetailDTO = new MaBdPointDetailDTO();
    
    /** �̻�������ġ - �۾���� ���  DTO */
    private MaBdPointWoRsltListDTO maBdPointWoRsltListDTO = new MaBdPointWoRsltListDTO();
    
    /** �۾� ���� */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /** �۾���� �� */
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
