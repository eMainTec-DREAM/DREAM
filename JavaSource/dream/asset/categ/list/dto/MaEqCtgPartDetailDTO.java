package dream.asset.categ.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���������� ��ǰ �� dto
 * @author  kim21017
 * @version $Id: MaEqCtgPartDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqCtgPartDetailDTO extends BaseDTO
{
	/** ���������� ��ǰ id */
	private String eqCtgPartId		= "";
	/** ���� id*/
	private String partId			= "";
	/** �����ڵ�*/
	private String partNo			= "";
	/** ����� �԰�*/
	private String partNameSize		= "";
	/** �����*/
	private String partDesc			= "";
	/** �԰�*/
	private String ptSize			= "";
	/** ��*/
	private String model			= "";
	/** ������*/
	private String useQty			= "";
	/** �۾�����id*/
	private String eqCtgAsmbId		= "";
	/** �۾�������*/
	private String eqCtgAsmbDesc	= "";
	/** ��ȸ���� */
	private String ordNo			= "";
	/** ��뿩�� */
	private String isUse			= "";
	/** �������-��,��,��, �� */
    private String periodType 		= "";
    private String periodTypeDesc   = "";
    
    /** ������� Ȯ�ι�� ID */
    private String scheduleTypeId	= "";
    /** ������� Ȯ�ι�� */
    private String scheduleTypeDesc	= "";
    /** �������-��뷮,���귮,�����ð� */
    private String usage	 		= "";
    /** �������-�Ⱓ */
    private String cycle 			= "";
	
	/** (������)����������� */
    private String isEqTypePart 	= "";
    
    /** OLD ���������� ��ǰ id */
    private String oldEqCtgPartId	= "";
    /** �������� id */
    private String eqCtgId      	= "";
	
    
	public String getEqCtgId()
    {
        return eqCtgId;
    }
    public void setEqCtgId(String eqCtgId)
    {
        this.eqCtgId = eqCtgId;
    }
    public String getPartNameSize()
    {
        return partNameSize;
    }
    public String getScheduleTypeId() {
		return scheduleTypeId;
	}
	public void setScheduleTypeId(String scheduleTypeId) {
		this.scheduleTypeId = scheduleTypeId;
	}
	public String getScheduleTypeDesc() {
		return scheduleTypeDesc;
	}
	public void setScheduleTypeDesc(String scheduleTypeDesc) {
		this.scheduleTypeDesc = scheduleTypeDesc;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = CommonUtil.convertMoney(usage);
	}
	public void setPartNameSize(String partNameSize)
    {
        this.partNameSize = partNameSize;
    }
    public String getPtSize()
    {
        return ptSize;
    }
    public void setPtSize(String ptSize)
    {
        this.ptSize = ptSize;
    }
    public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
    }
    public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public String getPeriodTypeDesc() {
		return periodTypeDesc;
	}
	public void setPeriodTypeDesc(String periodTypeDesc) {
		this.periodTypeDesc = periodTypeDesc;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getIsEqTypePart()
    {
        return isEqTypePart;
    }
    public String getOldEqCtgPartId() {
		return oldEqCtgPartId;
	}
	public void setOldEqCtgPartId(String oldEqCtgPartId) {
		this.oldEqCtgPartId = oldEqCtgPartId;
	}
	public void setIsEqTypePart(String isEqTypePart)
    {
        this.isEqTypePart = isEqTypePart;
    }
    public String getEqCtgPartId() {
		return eqCtgPartId;
	}
	public void setEqCtgPartId(String eqCtgPartId) {
		this.eqCtgPartId = eqCtgPartId;
		super.setAuditKey(eqCtgPartId);
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getUseQty() {
		return useQty;
	}
	public void setUseQty(String useQty) {
		this.useQty = CommonUtil.convertMoney(useQty);
	}
	public String getEqCtgAsmbId() {
		return eqCtgAsmbId;
	}
	public void setEqCtgAsmbId(String eqCtgAsmbId) {
		this.eqCtgAsmbId = eqCtgAsmbId;
	}
	public String getEqCtgAsmbDesc() {
		return eqCtgAsmbDesc;
	}
	public void setEqCtgAsmbDesc(String eqCtgAsmbDesc) {
		this.eqCtgAsmbDesc = eqCtgAsmbDesc;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	
}