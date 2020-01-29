package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.PartListImgLinkUrlDTO;

/**
 * ºÎÇ°Image Link Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="partListImgLinkUrlForm"
 * */
public class PartListImgLinkUrlForm extends BaseForm{
	
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
	private PartListImgLinkUrlDTO partListImgLinkUrlDTO = new PartListImgLinkUrlDTO();
	
	
	public MaPtMstrCommonDTO getMaPtMstrCommonDTO() {
		return maPtMstrCommonDTO;
	}
	public void setMaPtMstrCommonDTO(MaPtMstrCommonDTO maPtMstrCommonDTO) {
		this.maPtMstrCommonDTO = maPtMstrCommonDTO;
	}
	public PartListImgLinkUrlDTO getPartListImgLinkUrlDTO() {
		return partListImgLinkUrlDTO;
	}
	public void setPartListImgLinkUrlDTO(PartListImgLinkUrlDTO partListImgLinkUrlDTO) {
		this.partListImgLinkUrlDTO = partListImgLinkUrlDTO;
	}
	
}
