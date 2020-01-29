package dream.tool.stk.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자재재고 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPttStckCommonDTO extends BaseDTO
{
	/** ID */
	private String wcodeId             = "";
	private String partId              = "";
	private String partGrade           = "";
    /** 구매신청 생성 시 시퀀스  */
    private String reqIdx = "";
    
	public String getReqIdx() {
		return reqIdx;
	}

	public void setReqIdx(String reqIdx) {
		this.reqIdx = reqIdx;
	}
	
	/** 필터-창고Id */
	private String filterWcodeId       = "";
	/** 필터-창고명 */
	private String filterWname         = "";
	/** 필터-부품번호 */
	private String filterPartNo        = "";
	/** 필터-부품번명 */
	private String filterPartDesc      = "";
	/** 필터-부품번명 */
	private String filterPtSize        = "";
	/** 필터-제작사 */
	private String filterMaker         = "";
	/** 필터-모델 */
	private String filterModel         = "";
	/** 필터-안전재고미만 */
	private String filterSaftyYN       = "";
	/** 필터-총재고 이상수량 */
	private String filterQtyCnt        = "";
	
    public String getFilterQtyCnt() {
		return filterQtyCnt;
	}

	public void setFilterQtyCnt(String filterQtyCnt) {
		this.filterQtyCnt = CommonUtil.convertMoney(filterQtyCnt);
	}

	public String getFilterSaftyYN() {
		return filterSaftyYN;
	}
	public void setFilterSaftyYN(String filterSaftyYN) {
		this.filterSaftyYN = filterSaftyYN;
	}
	public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
        super.setAuditKey(partId);
    }
    public String getPartGrade()
    {
        return partGrade;
    }
    public void setPartGrade(String partGrade)
    {
        this.partGrade = partGrade;
    }
    public String getFilterWcodeId()
    {
        return filterWcodeId;
    }
    public void setFilterWcodeId(String filterWcodeId)
    {
        this.filterWcodeId = filterWcodeId;
    }

    public String getFilterWname()
    {
        return filterWname;
    }
    public void setFilterWname(String filterWname)
    {
        this.filterWname = filterWname;
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
    public String getFilterPtSize()
    {
        return filterPtSize;
    }
    public void setFilterPtSize(String filterPtSize)
    {
        this.filterPtSize = filterPtSize;
    }
    public String getFilterMaker()
    {
        return filterMaker;
    }
    public void setFilterMaker(String filterMaker)
    {
        this.filterMaker = filterMaker;
    }
    public String getFilterModel()
    {
        return filterModel;
    }
    public void setFilterModel(String filterModel)
    {
        this.filterModel = filterModel;
    }

}
