package dream.note.daily.dto;

import java.util.List;

import common.bean.BaseDTO;

/**
 * �����۾����� ���� DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class MaWoDailyImageListDTO extends BaseDTO
{
	/** �����۾����� ���� ������ */
	private String subImgType 				= "";
	
	/** �۾���� �۾� �� ���� ����Ʈ */
	private List slideAfterFileList			= null;
	
	
	public String getSubImgType() {
		return subImgType;
	}

	public void setSubImgType(String subImgType) {
		this.subImgType = subImgType;
	}

	public List getSlideAfterFileList() {
		return slideAfterFileList;
	}

	public void setSlideAfterFileList(List slideAfterFileList) {
		this.slideAfterFileList = slideAfterFileList;
	}
}