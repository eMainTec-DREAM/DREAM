package dream.part.stk.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������� ���� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtStckCommonDTO extends BaseDTO
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
	/** ����-���ۻ� */
	private String filterMaker         = "";
	/** ����-�� */
	private String filterModel         = "";
	/** ����-�������̸� */
	private String filterSaftyYN       = "";
	/** ����-����� �̻���� */
	private String filterQtyCnt        = "";
    /** ����׷� */
    private String filterPartGroup     = "";
    /** ����׷�� */
    private String filterPartGroupDesc = "";
    /** vendor code */
    private String filterVendorPtCode  = "";
    /** �����߿䵵 */
    private String filterPtAbcClass    = "";
    /** �����߿䵵 */
    private String filterPtAbcClassDesc= "";
    /** ���� ��뼳�� Id */
    private String filterEquipId                 = "";
    /** ���� ��뼳�� �� */
    private String filterEquipDesc              = "";
    /** ��࿩��  */
    private String isCont               = "";
    /** ����-���� ID */
    private String filterPlantId         	= "";
    /** ����-���� DESC */
    private String filterPlantDesc         	= "";
    
    

    
	public String getFilterPlantId() {
		return filterPlantId;
	}

	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}

	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}

	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}

	public String getIsCont()
    {
        return isCont;
    }

    public void setIsCont(String isCont)
    {
        this.isCont = isCont;
    }

    public String getFilterEquipId()
    {
        return filterEquipId;
    }

    public void setFilterEquipId(String filterEquipId)
    {
        this.filterEquipId = filterEquipId;
    }

    public String getFilterEquipDesc()
    {
        return filterEquipDesc;
    }

    public void setFilterEquipDesc(String filterEquipDesc)
    {
        this.filterEquipDesc = filterEquipDesc;
    }

    public String getFilterPtAbcClass() {
		return filterPtAbcClass;
	}

	public void setFilterPtAbcClass(String filterPtAbcClass) {
		this.filterPtAbcClass = filterPtAbcClass;
	}

	public String getFilterPtAbcClassDesc() {
		return filterPtAbcClassDesc;
	}

	public void setFilterPtAbcClassDesc(String filterPtAbcClassDesc) {
		this.filterPtAbcClassDesc = filterPtAbcClassDesc;
	}

	public String getFilterVendorPtCode() {
		return filterVendorPtCode;
	}

	public void setFilterVendorPtCode(String filterVendorPtCode) {
		this.filterVendorPtCode = filterVendorPtCode;
	}

	public String getFilterPartGroup() {
		return filterPartGroup;
	}

	public void setFilterPartGroup(String filterPartGroup) {
		this.filterPartGroup = filterPartGroup;
	}

	public String getFilterPartGroupDesc() {
		return filterPartGroupDesc;
	}

	public void setFilterPartGroupDesc(String filterPartGroupDesc) {
		this.filterPartGroupDesc = filterPartGroupDesc;
	}

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
        super.setAuditKey(wcodeId);
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
