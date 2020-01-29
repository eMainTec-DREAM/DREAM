package dream.consult.comp.warehouse.dto;

import common.bean.BaseDTO;

/**
 * ���â��˻� �˾� DTO
 * @author  kim21017
 * @version $Id: LovWhListDTO.java,v 1.1 2016/01/18 09:12:12 kim21017 Exp $
 * @since   1.0
 */
public class LovWhListDTO extends BaseDTO
{
	/** �����ڵ� */
    private String plant		= "";
    /** ����� */
    private String plantDesc	= "";
	/** â���ڵ� */
    private String wcode 		= "";
    /** â��� */
    private String wname 		= "";
    /** ��뿩�� */
    private String isUse 		= "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    
    
    
    
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getWcode() {
		return wcode;
	}
	public void setWcode(String wcode) {
		this.wcode = wcode;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
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
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
    
}
