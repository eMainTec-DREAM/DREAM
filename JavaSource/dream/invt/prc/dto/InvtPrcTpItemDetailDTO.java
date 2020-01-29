package dream.invt.prc.dto;

import common.bean.BaseDTO;


/**
 * 구매절차 Item 상세  DTO
 * @author  hyosung
 * @version $Id: InvtPrcTpItemDetailDTO.java,v 1.1 2015/12/04 09:10:45 hyosung Exp $
 * @since   1.0
 */
public class InvtPrcTpItemDetailDTO extends BaseDTO
{
	
	/** 구매절차단계 ID */
	private String invtPrcPhId     ="";
	
	/** 조회순서 */
	private String ordNo           ="";
	/** 사용여부 */
	private String isUse           ="";
	private String isUseDesc       ="";
	/** 대분류NO */
	private String invt_LTypeNo    ="";
	/** 대분류명 */
	private String invt_LTypeDesc  ="";
	/** 소분류NO */
	private String invt_STypeNo    ="";
	/** 소분류명 */
	private String invt_STypeDesc  ="";
	/** 유관부서명 */
	private String invtRefDept     ="";
	/** 관련문서명 */
	private String invtRefDoc      ="";
	/** 비고 */
	private String remark          ="";
	/** 번호생성 Prefix */
    private String docPrefix       = "";

    
    public String getDocPrefix()
    {
        return docPrefix;
    }

    public void setDocPrefix(String docPrefix)
    {
        this.docPrefix = docPrefix;
    }

    public String getInvtPrcPhId()
    {
        return invtPrcPhId;
    }

    public void setInvtPrcPhId(String invtPrcPhId)
    {
        this.invtPrcPhId = invtPrcPhId;
        super.setAuditKey(invtPrcPhId);
    }

    public String getOrdNo()
    {
        return ordNo;
    }

    public void setOrdNo(String ordNo)
    {
        this.ordNo = ordNo;
    }

    public String getIsUse()
    {
        return isUse;
    }

    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }

    public String getIsUseDesc()
    {
        return isUseDesc;
    }

    public void setIsUseDesc(String isUseDesc)
    {
        this.isUseDesc = isUseDesc;
    }

    public String getInvt_LTypeNo()
    {
        return invt_LTypeNo;
    }

    public void setInvt_LTypeNo(String invt_LTypeNo)
    {
        this.invt_LTypeNo = invt_LTypeNo;
    }

    public String getInvt_LTypeDesc()
    {
        return invt_LTypeDesc;
    }

    public void setInvt_LTypeDesc(String invt_LTypeDesc)
    {
        this.invt_LTypeDesc = invt_LTypeDesc;
    }

    public String getInvt_STypeNo()
    {
        return invt_STypeNo;
    }

    public void setInvt_STypeNo(String invt_STypeNo)
    {
        this.invt_STypeNo = invt_STypeNo;
    }

    public String getInvt_STypeDesc()
    {
        return invt_STypeDesc;
    }

    public void setInvt_STypeDesc(String invt_STypeDesc)
    {
        this.invt_STypeDesc = invt_STypeDesc;
    }

    public String getInvtRefDept()
    {
        return invtRefDept;
    }

    public void setInvtRefDept(String invtRefDept)
    {
        this.invtRefDept = invtRefDept;
    }

    public String getInvtRefDoc()
    {
        return invtRefDoc;
    }

    public void setInvtRefDoc(String invtRefDoc)
    {
        this.invtRefDoc = invtRefDoc;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
	
	
}