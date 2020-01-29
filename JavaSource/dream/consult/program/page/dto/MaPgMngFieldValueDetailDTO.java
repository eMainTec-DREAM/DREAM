package dream.consult.program.page.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 화면별 필드 기본 값 상세 dto
 * @author  kim21017
 * @version $Id: MaPgMngFieldValueDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPgMngFieldValueDetailDTO extends BaseDTO
{
	/** 필드 ID */
	private String pgFieldId			= "";
	/** 필드 값 ID */
	private String pgFieldValueId		= "";
	/** 회사 ID */
	private String compNoId				= "";
	/** 회사 명 */
	private String compNoDesc			= "";
	/** 파일명 */
	private String fileName				= "";
	/** 필드 name */
	private String fieldId				= "";
	/** 기본 값 ID */
	private String defaultObjectId		= "";
	/** 기본 값 ID */
	private String defaultValueId		= "";
	/** 기본 값 명*/
	private String defaultValueDesc		= "";
	/** 일 간격 */
	private String dateInterval			= "";
	
	public String getPgFieldId() {
		return pgFieldId;
	}
	public void setPgFieldId(String pgFieldId) {
		this.pgFieldId = pgFieldId;
	}
	public String getPgFieldValueId() {
		return pgFieldValueId;
	}
	public void setPgFieldValueId(String pgFieldValueId) {
		this.pgFieldValueId = pgFieldValueId;
	}
	public String getCompNoId() {
		return compNoId;
	}
	public void setCompNoId(String compNoId) {
		this.compNoId = compNoId;
	}
	public String getCompNoDesc() {
		return compNoDesc;
	}
	public void setCompNoDesc(String compNoDesc) {
		this.compNoDesc = compNoDesc;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getDefaultObjectId() {
		return defaultObjectId;
	}
	public void setDefaultObjectId(String defaultObjectId) {
		this.defaultObjectId = defaultObjectId;
	}
	public String getDateInterval() {
		return dateInterval;
	}
	public void setDateInterval(String dateInterval) {
		this.dateInterval = CommonUtil.convertMoney(dateInterval);
	}
	public String getDefaultValueId() {
		return defaultValueId;
	}
	public void setDefaultValueId(String defaultValueId) {
		this.defaultValueId = defaultValueId;
	}
	public String getDefaultValueDesc() {
		return defaultValueDesc;
	}
	public void setDefaultValueDesc(String defaultValueDesc) {
		this.defaultValueDesc = defaultValueDesc;
	}
	
}