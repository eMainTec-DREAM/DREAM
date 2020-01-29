package dream.work.let.categ.dto;

import common.bean.BaseDTO;

/**
 * 안전작업유형 Lov 팝업 DTO
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 */
public class LovWorkLetCategListDTO extends BaseDTO
{
    /** 안전작업유형 ID */
	private String woLetCtgId 		=	"";
	
	/** 안전작업유형 */
	private String woLetCtgType		=	"";
	/** 안전작업유형 */
	private String woLetCtgTypeDesc =	"";
	/** 안전작업 유형번호 */
	private String woLetCtgNo 		=	"";
	/** 제목 */
	private String description 		=	"";
	/** 사용여부 */
	private String isUse	 		=	"";

    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    
    /** Multy Select Y */
    private String multiSelect    	= 	"";
    
    
	public String getWoLetCtgId() {
		return woLetCtgId;
	}
	public void setWoLetCtgId(String woLetCtgId) {
		this.woLetCtgId = woLetCtgId;
		super.setAuditKey(woLetCtgId);
	}
	public String getWoLetCtgType() {
		return woLetCtgType;
	}
	public void setWoLetCtgType(String woLetCtgType) {
		this.woLetCtgType = woLetCtgType;
	}
	public String getWoLetCtgTypeDesc() {
		return woLetCtgTypeDesc;
	}
	public void setWoLetCtgTypeDesc(String woLetCtgTypeDesc) {
		this.woLetCtgTypeDesc = woLetCtgTypeDesc;
	}
	public String getWoLetCtgNo() {
		return woLetCtgNo;
	}
	public void setWoLetCtgNo(String woLetCtgNo) {
		this.woLetCtgNo = woLetCtgNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getMultiSelect() {
		return multiSelect;
	}
	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}
	public String getExtCode1() {
		return extCode1;
	}
	public void setExtCode1(String extCode1) {
		this.extCode1 = extCode1;
	}
	public String getExtCode2() {
		return extCode2;
	}
	public void setExtCode2(String extCode2) {
		this.extCode2 = extCode2;
	}
	
    
}
