package dream.note.daily.dto;

import java.util.List;

import common.bean.BaseDTO;

/**
 * 일일작업승인 사진 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class MaWoDailyImageListDTO extends BaseDTO
{
	/** 일일작업승인 사진 구분자 */
	private String subImgType 				= "";
	
	/** 작업결과 작업 후 사진 리스트 */
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