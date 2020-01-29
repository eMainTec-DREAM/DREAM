package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.PartListSafQtyDetailDTO;
import dream.part.list.dto.PartListSafQtyListDTO;

/**
 * 부품창고 보관위치 - Detail Form
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts.form name="partListSafQtyDetailForm"
 */
public class PartListSafQtyDetailForm extends BaseForm
{
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
	private PartListSafQtyListDTO partListSafQtyListDTO = new PartListSafQtyListDTO();
	private PartListSafQtyDetailDTO partListSafQtyDetailDTO = new PartListSafQtyDetailDTO();
	
	public MaPtMstrCommonDTO getMaPtMstrCommonDTO()
    {
        return maPtMstrCommonDTO;
    }
    public void setMaPtMstrCommonDTO(MaPtMstrCommonDTO maPtMstrCommonDTO)
    {
        this.maPtMstrCommonDTO = maPtMstrCommonDTO;
    }
    public PartListSafQtyListDTO getPartListSafQtyListDTO() {
		return partListSafQtyListDTO;
	}
	public void setPartListSafQtyListDTO(PartListSafQtyListDTO partListSafQtyListDTO) {
		this.partListSafQtyListDTO = partListSafQtyListDTO;
	}
	public PartListSafQtyDetailDTO getPartListSafQtyDetailDTO() {
		return partListSafQtyDetailDTO;
	}
	public void setPartListSafQtyDetailDTO(PartListSafQtyDetailDTO partListSafQtyDetailDTO) {
		this.partListSafQtyDetailDTO = partListSafQtyDetailDTO;
	}
}
