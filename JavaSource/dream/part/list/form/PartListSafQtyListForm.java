package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.PartListSafQtyListDTO;

/**
 * 부품창고 보관위치 - List Form
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts.form name="partListSafQtyListForm"
 * */
public class PartListSafQtyListForm extends BaseForm{
	
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
	private PartListSafQtyListDTO partListSafQtyListDTO = new PartListSafQtyListDTO();
	
	
	public MaPtMstrCommonDTO getMaPtMstrCommonDTO() {
		return maPtMstrCommonDTO;
	}
	public void setMaPtMstrCommonDTO(MaPtMstrCommonDTO maPtMstrCommonDTO) {
		this.maPtMstrCommonDTO = maPtMstrCommonDTO;
	}
	public PartListSafQtyListDTO getPartListSafQtyListDTO() {
		return partListSafQtyListDTO;
	}
	public void setPartListSafQtyListDTO(PartListSafQtyListDTO partListSafQtyListDTO) {
		this.partListSafQtyListDTO = partListSafQtyListDTO;
	}
	
}
