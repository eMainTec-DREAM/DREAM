package dream.work.let.categ.dto;

import common.bean.BaseDTO;

/**
 * �����۾��㰡�� ǥ�������׸� Lov DTO
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 */
public class LovWorkLetCategPointListDTO extends BaseDTO
{
    /** �����۾� ǥ�������׸� ID */
	private String woLetCtgPointId 		=	"";
	
    /** �����۾����� ID */
	private String woLetCtgId 			=	"";
	
	/** ���� */                       	
	private String stepNum 				= 	"";
	/** ���˺��� */                     	
	private String checkPosition 		= 	"";
	/** �����׸� */                     	
	private String checkPoint 			= 	"";
	/** ���˹�� */                     	
	private String checkMethod 			= 	"";
	/** �������� */                 		
	private String fitBasis 			= 	"";
	/** ��ġ�������� id */            		
	private String checkTypeId 			= 	"";
	/** ��ġ�������� */                   	
	private String checkTypeDesc 		= 	"";
	/** ���Ѱ� */                      	
	private String checkMin 			= 	"";
	/** ���˱��ذ� */                		
	private String checkBasisVal		= 	"";
	/** ���Ѱ� */                      	
	private String checkMax 			= 	"";
	/** ���� */                       	  	
	private String uom 					= 	"";
	/** ��뿩�� */	                    	  	
	private String isUse 				=	"";
	/** ��� */                       	  	
	private String remark 				= 	"";

    /** extCode1 */
    private String extCode1 			= 	"";
    /** extCode2 */
    private String extCode2 			= 	"";
    
    /** Multy Select Y */
    private String multiSelect    		= 	"";
    

	public String getWoLetCtgPointId() {
		return woLetCtgPointId;
	}
	public void setWoLetCtgPointId(String woLetCtgPointId) {
		this.woLetCtgPointId = woLetCtgPointId;
		super.setAuditKey(woLetCtgPointId);
	}
	public String getWoLetCtgId() {
		return woLetCtgId;
	}
	public void setWoLetCtgId(String woLetCtgId) {
		this.woLetCtgId = woLetCtgId;
	}
	public String getStepNum() {
		return stepNum;
	}
	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}
	public String getCheckPosition() {
		return checkPosition;
	}
	public void setCheckPosition(String checkPosition) {
		this.checkPosition = checkPosition;
	}
	public String getCheckPoint() {
		return checkPoint;
	}
	public void setCheckPoint(String checkPoint) {
		this.checkPoint = checkPoint;
	}
	public String getCheckMethod() {
		return checkMethod;
	}
	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}
	public String getFitBasis() {
		return fitBasis;
	}
	public void setFitBasis(String fitBasis) {
		this.fitBasis = fitBasis;
	}
	public String getCheckTypeId() {
		return checkTypeId;
	}
	public void setCheckTypeId(String checkTypeId) {
		this.checkTypeId = checkTypeId;
	}
	public String getCheckTypeDesc() {
		return checkTypeDesc;
	}
	public void setCheckTypeDesc(String checkTypeDesc) {
		this.checkTypeDesc = checkTypeDesc;
	}
	public String getCheckMin() {
		return checkMin;
	}
	public void setCheckMin(String checkMin) {
		this.checkMin = checkMin;
	}
	public String getCheckBasisVal() {
		return checkBasisVal;
	}
	public void setCheckBasisVal(String checkBasisVal) {
		this.checkBasisVal = checkBasisVal;
	}
	public String getCheckMax() {
		return checkMax;
	}
	public void setCheckMax(String checkMax) {
		this.checkMax = checkMax;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getMultiSelect() {
		return multiSelect;
	}
	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}
}
