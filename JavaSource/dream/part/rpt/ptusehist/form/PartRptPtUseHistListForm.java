package dream.part.rpt.ptusehist.form;

import common.struts.BaseForm;
import dream.part.rpt.ptusehist.dto.PartRptPtUseHistCommonDTO;
import dream.part.rpt.ptusehist.dto.PartRptPtUseHistDetailDTO;
/**
 * 부품사용분석 - List Form
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * @struts.form name="partRptPtUseHistListForm"
 * */

public class PartRptPtUseHistListForm extends BaseForm
{
	private PartRptPtUseHistCommonDTO partRptPtUseHistCommonDTO = new PartRptPtUseHistCommonDTO();
	private PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO = new PartRptPtUseHistDetailDTO();
    
	public PartRptPtUseHistCommonDTO getPartRptPtUseHistCommonDTO() 
	{
		return partRptPtUseHistCommonDTO;
	}
	public void setPartRptPtUseHistCommonDTO(PartRptPtUseHistCommonDTO partRptPtUseHistCommonDTO) 
	{
		this.partRptPtUseHistCommonDTO = partRptPtUseHistCommonDTO;
	}
	public PartRptPtUseHistDetailDTO getPartRptPtUseHistDetailDTO() 
	{
		return partRptPtUseHistDetailDTO;
	}
	public void setPartRptPtUseHistDetailDTO(PartRptPtUseHistDetailDTO partRptPtUseHistDetailDTO) 
	{
		this.partRptPtUseHistDetailDTO = partRptPtUseHistDetailDTO;
	}
	
}
