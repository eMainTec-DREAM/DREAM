package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.PartListBinListDTO;

/**
 * 부품창고 보관위치 - List Form
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts.form name="partListBinListForm"
 * */
public class PartListBinListForm extends BaseForm{
	
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
	private PartListBinListDTO partListBinListDTO = new PartListBinListDTO();
	
	
	public MaPtMstrCommonDTO getMaPtMstrCommonDTO() {
		return maPtMstrCommonDTO;
	}
	public void setMaPtMstrCommonDTO(MaPtMstrCommonDTO maPtMstrCommonDTO) {
		this.maPtMstrCommonDTO = maPtMstrCommonDTO;
	}
	public PartListBinListDTO getPartListBinListDTO() {
		return partListBinListDTO;
	}
	public void setPartListBinListDTO(PartListBinListDTO partListBinListDTO) {
		this.partListBinListDTO = partListBinListDTO;
	}
	
}
