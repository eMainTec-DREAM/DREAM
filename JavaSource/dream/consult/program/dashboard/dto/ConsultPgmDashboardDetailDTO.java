package dream.consult.program.dashboard.dto;

import common.bean.BaseDTO;

/**
 * 대시보드 Contents - 상세 DTO
 * @author  kim21017
 * @version $Id: ConsultPgmDashboardDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class ConsultPgmDashboardDetailDTO extends BaseDTO
{

	/** ID */
	private String dbContentsId 			= "";
	
	/** Contents 구분Id */
	private String dbContentsTypeId 		= "";
	/** Contents 구분Desc */
	private String dbContentsTypeDesc 		= "";
	/** Contents Desc */
	private String dbContentsDesc			= "";
	/** 화면표시 keyType */
	private String keyType					= "";
	/** 화면표시 keyNo */
	private String keyNo					= "";
	/** 화면표시 keyName */
	private String keyName					= "";
	/** jsp pageId */
	private String pageId					= "";
	/** jsp pageDesc */
	private String pageDesc					= "";
	/** jsp fileName */
	private String fileName					= "";
	/** 너비 ID */
	private String dbContentsWidthId		= "";
	/** 너비 DESC */
	private String dbContentsWidthDesc		= "";
	/** 이미지 파일 */
	private String imageFile				= "";
	/** 사용여부 ID */
	private String isUseId					= "";
	/** 사용여부 DESC */
	private String isUseDesc				= "";
	/** 비고  */
	private String remark					= "";
	public String getDbContentsId() {
		return dbContentsId;
	}
	public void setDbContentsId(String dbContentsId) {
		this.dbContentsId = dbContentsId;
	}
	public String getDbContentsTypeId() {
		return dbContentsTypeId;
	}
	public void setDbContentsTypeId(String dbContentsTypeId) {
		this.dbContentsTypeId = dbContentsTypeId;
	}
	public String getDbContentsTypeDesc() {
		return dbContentsTypeDesc;
	}
	public void setDbContentsTypeDesc(String dbContentsTypeDesc) {
		this.dbContentsTypeDesc = dbContentsTypeDesc;
	}
	public String getDbContentsDesc() {
		return dbContentsDesc;
	}
	public void setDbContentsDesc(String dbContentsDesc) {
		this.dbContentsDesc = dbContentsDesc;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getPageDesc() {
		return pageDesc;
	}
	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDbContentsWidthId() {
		return dbContentsWidthId;
	}
	public void setDbContentsWidthId(String dbContentsWidthId) {
		this.dbContentsWidthId = dbContentsWidthId;
	}
	public String getDbContentsWidthDesc() {
		return dbContentsWidthDesc;
	}
	public void setDbContentsWidthDesc(String dbContentsWidthDesc) {
		this.dbContentsWidthDesc = dbContentsWidthDesc;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getIsUseId() {
		return isUseId;
	}
	public void setIsUseId(String isUseId) {
		this.isUseId = isUseId;
	}
	public String getIsUseDesc() {
		return isUseDesc;
	}
	public void setIsUseDesc(String isUseDesc) {
		this.isUseDesc = isUseDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
