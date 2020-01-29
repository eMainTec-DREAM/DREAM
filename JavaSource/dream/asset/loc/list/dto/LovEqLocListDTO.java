package dream.asset.loc.list.dto;

import common.bean.BaseDTO;

/**
 * 위치분류검색 팝업 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovEqLocListDTO extends BaseDTO
{
    /** 구분 */
    private String eqLocLvlType		= "";
    /** 구분명 */
    private String eqLocLvlTypeDesc	= "";
    /** 위치명 */
    private String fullDesc			= "";
    /** 위치코드 */
    private String eqLocNo          = "";
    /** extCode1 */
    private String extCode1 		= "";
    /** extCode2 */
    private String extCode2 		= "";
    /** code type */
    private String codeType 		= "";
    /** 공장  ID */
    private String plant            = "";
    /** 공장  DESC */
    private String plantDesc        = "";
    
	public String getPlant()
    {
        return plant;
    }
    public void setPlant(String plant)
    {
        this.plant = plant;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getEqLocNo()
    {
        return eqLocNo;
    }
    public void setEqLocNo(String eqLocNo)
    {
        this.eqLocNo = eqLocNo;
    }
    public String getEqLocLvlTypeDesc() {
		return eqLocLvlTypeDesc;
	}
	public void setEqLocLvlTypeDesc(String eqLocLvlTypeDesc) {
		this.eqLocLvlTypeDesc = eqLocLvlTypeDesc;
	}
	public String getEqLocLvlType() {
		return eqLocLvlType;
	}
	public void setEqLocLvlType(String eqLocLvlType) {
		this.eqLocLvlType = eqLocLvlType;
	}
	public String getFullDesc() {
		return fullDesc;
	}
	public void setFullDesc(String fullDesc) {
		this.fullDesc = fullDesc;
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
