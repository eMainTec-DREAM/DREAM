package dream.part.rpt.mawopthist.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��ǰ����̷� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaWoPtHistCommonDTO extends BaseDTO
{
   
    /** Filter-ȸ���ڵ� */
    private String filterCompNo             = "";
    /** ���� */
    private String filterStartDate          = "";
    private String filterEndDate            = "";
    /** �μ�Id */
    private String filterDeptId             = "";
    /** �μ��� */
    private String filterDeptDesc           = "";
    /** ����Id */
    private String filterEquipId            = "";
    /** ����� */
    private String filterEquipDesc          = "";
    /** ��ǰId */
    private String filterPartId             = "";
    private String filterPartNo             = "";
    /** ��ǰ�� */
    private String filterPartDesc           = "";
    /** �ڻ�Id */
    private String filterAssetId            = "";
    /** �ڻ�� */
    private String filterAssetDesc          = "";
	/** ��ġid - ���� */
	private String filterEqLocId			= "";
	/** ��ġ�� - ���� */
	private String filterEqLocDesc			= "";
	/** ����id - ���� */
	private String filterEqCtgId			= "";
	/** ������ - ���� */
	private String filterEqCtgDesc			= "";
	/** ����-������(��)id */
	private String filterMainMngId			= "";
	/** ����-������(��)�� */
	private String filterMainMngName		= "";
	/** ����-������(��)id */
	private String filterSubMngId			= "";
	/** ����-������(��)�� */
	private String filterSubMngName			= "";
	/** ����-�������񿩺� */
	private String filterIsLawEq			= "";
	/** Filter-��������Id */
	private String filterEqCtgTypeId 	   = "";
	/** Filter-�������� */
	private String filterEqCtgTypeDesc 	   = "";
	/** Filter-���� ID */
    private String filterPlantId            = "";
    /** Filter-���� DESC */
    private String filterPlantDesc          = "";
    /** ���Ž�û ���� �� ������  */
    private String reqIdx = "";
    
    
	public String getFilterPlantId()
    {
        return filterPlantId;
    }

    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }

    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }

    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }

    public String getFilterEqCtgTypeId() {
		return filterEqCtgTypeId;
	}

	public void setFilterEqCtgTypeId(String filterEqCtgTypeId) {
		this.filterEqCtgTypeId = filterEqCtgTypeId;
	}

	public String getFilterEqCtgTypeDesc() {
		return filterEqCtgTypeDesc;
	}

	public void setFilterEqCtgTypeDesc(String filterEqCtgTypeDesc) {
		this.filterEqCtgTypeDesc = filterEqCtgTypeDesc;
	}

	public String getReqIdx() {
		return reqIdx;
	}

	public void setReqIdx(String reqIdx) {
		this.reqIdx = reqIdx;
	}
    
    public String getFilterEqLocId() {
		return filterEqLocId;
	}
	public void setFilterEqLocId(String filterEqLocId) {
		this.filterEqLocId = filterEqLocId;
	}
	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}
	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
	}
	public String getFilterEqCtgId() {
		return filterEqCtgId;
	}
	public void setFilterEqCtgId(String filterEqCtgId) {
		this.filterEqCtgId = filterEqCtgId;
	}
	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}
	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
	}
	public String getFilterMainMngId() {
		return filterMainMngId;
	}
	public void setFilterMainMngId(String filterMainMngId) {
		this.filterMainMngId = filterMainMngId;
	}
	public String getFilterMainMngName() {
		return filterMainMngName;
	}
	public void setFilterMainMngName(String filterMainMngName) {
		this.filterMainMngName = filterMainMngName;
	}
	public String getFilterSubMngId() {
		return filterSubMngId;
	}
	public void setFilterSubMngId(String filterSubMngId) {
		this.filterSubMngId = filterSubMngId;
	}
	public String getFilterSubMngName() {
		return filterSubMngName;
	}
	public void setFilterSubMngName(String filterSubMngName) {
		this.filterSubMngName = filterSubMngName;
	}
	public String getFilterIsLawEq() {
		return filterIsLawEq;
	}
	public void setFilterIsLawEq(String filterIsLawEq) {
		this.filterIsLawEq = filterIsLawEq;
	}
	public String getFilterPartNo()
    {
        return filterPartNo;
    }
    public void setFilterPartNo(String filterPartNo)
    {
        this.filterPartNo = filterPartNo;
    }
    public String getFilterCompNo()
    {
        return filterCompNo;
    }
    public void setFilterCompNo(String filterCompNo)
    {
        this.filterCompNo = filterCompNo;
    }
    public String getFilterStartDate()
    {
        return filterStartDate;
    }
    public void setFilterStartDate(String filterStartDate)
    {
        this.filterStartDate = CommonUtil.convertDate(filterStartDate);
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = CommonUtil.convertDate(filterEndDate);
    }
    public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
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
    public String getFilterPartId()
    {
        return filterPartId;
    }
    public void setFilterPartId(String filterPartId)
    {
        this.filterPartId = filterPartId;
    }
    public String getFilterPartDesc()
    {
        return filterPartDesc;
    }
    public void setFilterPartDesc(String filterPartDesc)
    {
        this.filterPartDesc = filterPartDesc;
    }
    public String getFilterAssetId()
    {
        return filterAssetId;
    }
    public void setFilterAssetId(String filterAssetId)
    {
        this.filterAssetId = filterAssetId;
    }
    public String getFilterAssetDesc()
    {
        return filterAssetDesc;
    }
    public void setFilterAssetDesc(String filterAssetDesc)
    {
        this.filterAssetDesc = filterAssetDesc;
    }
}
