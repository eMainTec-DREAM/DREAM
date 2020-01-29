package dream.mgr.cal.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �޹��� ���� - �� DTO
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDetailDTO.java,v 1.0 2015/12/02 08:44:16 euna0207 Exp $
 * @since   1.0
 *
 */
public class MgrCalCompWkrcalDaysetDetailDTO extends BaseDTO
{
	/** �޹�����ID */
	private String wrkcalDaysetId 		= "";
	
	/** ���� */
	private String calDate 					= "";
	/** ���ϸ� */
	private String isRepeat 				= "";
	/** �޹����� */
	private String isOff 				= "";
	/** �޹����� */
	private String remark 				= "";
	public String getWrkcalDaysetId() {
		return wrkcalDaysetId;
	}
	public void setWrkcalDaysetId(String wrkcalDaysetId) {
		this.wrkcalDaysetId = wrkcalDaysetId;
	}
	public String getCalDate() {
		return calDate;
	}
	public void setCalDate(String calDate) {
		this.calDate = CommonUtil.convertDate(calDate);
	}
	public String getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}
	public String getIsOff() {
		return isOff;
	}
	public void setIsOff(String isOff) {
		this.isOff = isOff;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
