package dream.mgr.ptwh.form;

import common.struts.BaseForm;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.dto.MgrPtWhBinDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhBinListDTO;

/**
 * 부품창고 보관위치 - Detail Form
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrPtWhBinDetailForm"
 */
public class MgrPtWhBinDetailForm extends BaseForm
{
    private MgrPtWhCommonDTO mgrPtWhCommonDTO = new MgrPtWhCommonDTO();
	private MgrPtWhBinListDTO mgrPtWhBinListDTO = new MgrPtWhBinListDTO();
	private MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO = new MgrPtWhBinDetailDTO();
	
	public MgrPtWhCommonDTO getMgrPtWhCommonDTO()
    {
        return mgrPtWhCommonDTO;
    }
    public void setMgrPtWhCommonDTO(MgrPtWhCommonDTO mgrPtWhCommonDTO)
    {
        this.mgrPtWhCommonDTO = mgrPtWhCommonDTO;
    }
    public MgrPtWhBinListDTO getMgrPtWhBinListDTO() {
		return mgrPtWhBinListDTO;
	}
	public void setMgrPtWhBinListDTO(MgrPtWhBinListDTO mgrPtWhBinListDTO) {
		this.mgrPtWhBinListDTO = mgrPtWhBinListDTO;
	}
	public MgrPtWhBinDetailDTO getMgrPtWhBinDetailDTO() {
		return mgrPtWhBinDetailDTO;
	}
	public void setMgrPtWhBinDetailDTO(MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO) {
		this.mgrPtWhBinDetailDTO = mgrPtWhBinDetailDTO;
	}
}
