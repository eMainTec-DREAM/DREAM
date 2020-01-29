package dream.mgr.ptwh.form;

import common.struts.BaseForm;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.dto.MgrPtWhEmpDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;

/**
 * 부품창고 담당자 - Detail Form
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrPtWhEmpDetailForm"
 */
public class MgrPtWhEmpDetailForm extends BaseForm
{
    private MgrPtWhCommonDTO mgrPtWhCommonDTO = new MgrPtWhCommonDTO();
	private MgrPtWhEmpListDTO mgrPtWhEmpListDTO = new MgrPtWhEmpListDTO();
	private MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO = new MgrPtWhEmpDetailDTO();
	
	public MgrPtWhCommonDTO getMgrPtWhCommonDTO()
    {
        return mgrPtWhCommonDTO;
    }
    public void setMgrPtWhCommonDTO(MgrPtWhCommonDTO mgrPtWhCommonDTO)
    {
        this.mgrPtWhCommonDTO = mgrPtWhCommonDTO;
    }
    public MgrPtWhEmpListDTO getMgrPtWhEmpListDTO() {
		return mgrPtWhEmpListDTO;
	}
	public void setMgrPtWhEmpListDTO(MgrPtWhEmpListDTO mgrPtWhEmpListDTO) {
		this.mgrPtWhEmpListDTO = mgrPtWhEmpListDTO;
	}
	public MgrPtWhEmpDetailDTO getMgrPtWhEmpDetailDTO() {
		return mgrPtWhEmpDetailDTO;
	}
	public void setMgrPtWhEmpDetailDTO(MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO) {
		this.mgrPtWhEmpDetailDTO = mgrPtWhEmpDetailDTO;
	}
}
