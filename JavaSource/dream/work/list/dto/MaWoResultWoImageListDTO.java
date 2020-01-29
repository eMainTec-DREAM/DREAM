package dream.work.list.dto;

import java.util.List;

import common.bean.BaseDTO;

/**
 * �۾���� �۾����� DTO
 * @author  kim21017
 * @version $Id: MaWoResultWoImageListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultWoImageListDTO extends BaseDTO
{
	/** �۾���� �۾����� ��/�� ������ */
	private String subImgType 					= "";
	
	/** �۾���� �۾� �� ���� ����Ʈ */
	private List slideBeforeFileList		= null;
	/** �۾���� �۾� �� ���� ����Ʈ */
	private List slideAfterFileList			= null;
	
	
	public String getSubImgType() {
		return subImgType;
	}

	public void setSubImgType(String subImgType) {
		this.subImgType = subImgType;
	}

	public List getSlideBeforeFileList() {
		return slideBeforeFileList;
	}

	public void setSlideBeforeFileList(List slideBeforeFileList) {
		this.slideBeforeFileList = slideBeforeFileList;
	}

	public List getSlideAfterFileList() {
		return slideAfterFileList;
	}

	public void setSlideAfterFileList(List slideAfterFileList) {
		this.slideAfterFileList = slideAfterFileList;
	}

}