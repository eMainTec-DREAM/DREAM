package dream.mgr.ptwh.form;

import common.struts.BaseForm;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;

/**
 * 부품창고 담당자 - List Form
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrPtWhEmpListForm"
 * */
public class MgrPtWhEmpListForm extends BaseForm{
	
    private MgrPtWhCommonDTO mgrPtWhCommonDTO = new MgrPtWhCommonDTO();
	private MgrPtWhEmpListDTO mgrPtWhEmpListDTO = new MgrPtWhEmpListDTO();
	
	
	public MgrPtWhCommonDTO getMgrPtWhCommonDTO() {
		return mgrPtWhCommonDTO;
	}
	public void setMgrPtWhCommonDTO(MgrPtWhCommonDTO mgrPtWhCommonDTO) {
		this.mgrPtWhCommonDTO = mgrPtWhCommonDTO;
	}
	public MgrPtWhEmpListDTO getMgrPtWhEmpListDTO() {
		return mgrPtWhEmpListDTO;
	}
	public void setMgrPtWhEmpListDTO(MgrPtWhEmpListDTO mgrPtWhEmpListDTO) {
		this.mgrPtWhEmpListDTO = mgrPtWhEmpListDTO;
	}
	
}
