package dream.part.stk.dto;

import common.bean.BaseDTO;

/**
 * 재고검색 팝업 DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class LovPtStckListDTO extends BaseDTO
{
    /** extCode1 */
    private String extCode1 	      = "";
    /** extCode2 */
    private String extCode2 	      = "";
    /** code type */
    private String codeType 	      = "";
    /** 창고 ID */
    private String filterWId 	      = "";
    /** 창고 DESC */
    private String filterWDesc 	      = "";
    /** 부품 ID */
    private String filterPartId       = "";
    /** 부품 NO */
    private String filterPartNo       = "";
    /** 부품 DESC */
    private String filterPartDesc     = "";
    /** 업체품번 */
    private String filterVendorPtCode = "";
	/** 모델 */
	private String filterModel        = "";
	/** 필터-총재고 이상수량 */
	private String filterQtyCnt       = "";
    
    public String getExtCode1()
    {
        return extCode1;
    }
    public void setExtCode1(String extCode1)
    {
        this.extCode1 = extCode1;
    }
    public String getExtCode2()
    {
        return extCode2;
    }
    public void setExtCode2(String extCode2)
    {
        this.extCode2 = extCode2;
    }
    public String getCodeType()
    {
        return codeType;
    }
    public void setCodeType(String codeType)
    {
        this.codeType = codeType;
    }
    public String getFilterWId()
    {
        return filterWId;
    }
    public void setFilterWId(String filterWId)
    {
        this.filterWId = filterWId;
    }
    public String getFilterWDesc()
    {
        return filterWDesc;
    }
    public void setFilterWDesc(String filterWDesc)
    {
        this.filterWDesc = filterWDesc;
    }
    public String getFilterPartId()
    {
        return filterPartId;
    }
    public void setFilterPartId(String filterPartId)
    {
        this.filterPartId = filterPartId;
    }
    public String getFilterPartNo()
    {
        return filterPartNo;
    }
    public void setFilterPartNo(String filterPartNo)
    {
        this.filterPartNo = filterPartNo;
    }
    public String getFilterPartDesc()
    {
        return filterPartDesc;
    }
    public void setFilterPartDesc(String filterPartDesc)
    {
        this.filterPartDesc = filterPartDesc;
    }
    public String getFilterVendorPtCode()
    {
        return filterVendorPtCode;
    }
    public void setFilterVendorPtCode(String filterVendorPtCode)
    {
        this.filterVendorPtCode = filterVendorPtCode;
    }
	public String getFilterModel() {
		return filterModel;
	}
	public void setFilterModel(String filterModel) {
		this.filterModel = filterModel;
	}
	public String getFilterQtyCnt() {
		return filterQtyCnt;
	}
	public void setFilterQtyCnt(String filterQtyCnt) {
		this.filterQtyCnt = filterQtyCnt;
	}
    
}
