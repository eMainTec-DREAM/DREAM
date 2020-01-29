package dream.tool.stk.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������� ���� DTO
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
    /** ���Ž�û ���� �� ������  */
    private String reqIdx = "";
    
	public String getReqIdx() {
		return reqIdx;
	}

	public void setReqIdx(String reqIdx) {
		this.reqIdx = reqIdx;
	}
	
	/** ����-â��Id */
	private String filterWcodeId       = "";
	/** ����-â��� */
	private String filterWname         = "";
	/** ����-��ǰ��ȣ */
	private String filterPartNo        = "";
	/** ����-��ǰ���� */
	private String filterPartDesc      = "";
	/** ����-��ǰ���� */
	private String filterPtSize        = "";
	/** ����-���ۻ� */
	private String filterMaker         = "";
	/** ����-�� */
	private String filterModel         = "";
	/** ����-�������̸� */
	private String filterSaftyYN       = "";
	/** ����-����� �̻���� */
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
