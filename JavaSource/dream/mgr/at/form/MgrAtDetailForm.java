package dream.mgr.at.form;

import common.struts.BaseForm;
import dream.mgr.at.dto.MgrAtCommonDTO;
import dream.mgr.at.dto.MgrAtDetailDTO;
import dream.mgr.at.dto.MgrAtHistListDTO;

/**
 * 상세 Form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrAtDetailForm"
 */
public class MgrAtDetailForm extends BaseForm
{
    //========================================================================
    /** 공통 */ 
    private MgrAtCommonDTO mgrAtCommonDTO = new MgrAtCommonDTO();
    //========================================================================
    /** 상세 DTO */ 
    private MgrAtDetailDTO mgrAtDetailDTO = new MgrAtDetailDTO();
    private MgrAtHistListDTO mgrAtHistListDTO = new MgrAtHistListDTO();
    
	public MgrAtCommonDTO getMgrAtCommonDTO() {
		return mgrAtCommonDTO;
	}
	public MgrAtHistListDTO getMgrAtHistListDTO() {
		return mgrAtHistListDTO;
	}
	public void setMgrAtHistListDTO(MgrAtHistListDTO mgrAtHistListDTO) {
		this.mgrAtHistListDTO = mgrAtHistListDTO;
	}
	public void setMgrAtCommonDTO(MgrAtCommonDTO mgrAtCommonDTO) {
		this.mgrAtCommonDTO = mgrAtCommonDTO;
	}
	public MgrAtDetailDTO getMgrAtDetailDTO() {
		return mgrAtDetailDTO;
	}
	public void setMgrAtDetailDTO(MgrAtDetailDTO mgrAtDetailDTO) {
		this.mgrAtDetailDTO = mgrAtDetailDTO;
	}
}
