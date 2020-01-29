package dream.ass.base.form;

import java.util.ArrayList;
import java.util.List;

import common.struts.BaseForm;
import dream.ass.base.dto.AssBaseCommonDTO;
/**
 * 설비등급 평가기준 - List Form
 * @author kim21017
 * @version $Id: AssBaseListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="assBaseListForm"
 * */

public class AssBaseListForm extends BaseForm{
	
	private AssBaseCommonDTO assBaseCommonDTO = new AssBaseCommonDTO();
	
	private List<AssBaseCommonDTO> assBaseCommonDTOList = new ArrayList<AssBaseCommonDTO>();

	public AssBaseCommonDTO getAssBaseCommonDTO() {
		return assBaseCommonDTO;
	}

	public void setAssBaseCommonDTO(AssBaseCommonDTO assBaseCommonDTO) {
		this.assBaseCommonDTO = assBaseCommonDTO;
	}

    public List<AssBaseCommonDTO> getAssBaseCommonDTOList()
    {
        return assBaseCommonDTOList;
    }

    public void setAssBaseCommonDTOList(List<AssBaseCommonDTO> assBaseCommonDTOList)
    {
        this.assBaseCommonDTOList = assBaseCommonDTOList;
    }
	
}
