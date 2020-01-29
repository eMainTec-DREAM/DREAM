package dream.asset.loc.goal.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaLnGoalCommonDTO extends BaseDTO
{
    
	/** ȸ���ڵ� */
	private String compNo          = "";
	/** �Ϻ����ΰ��� ID */
	private String mtLnPointId     = "";
	/** ��� ���� */
	private String startYyyymm     = "";
	/** ��� �� */
	private String endYyyymm       = "";
	/** ���屸�� */
	private String plant           = "";
	/** ���屸�и� */
	private String plantDesc       = "";
	/** �����ڵ� */
	private String eqlocId         = "";
	/** �����ڵ�� */
	private String eqlocIdDesc     = "";
	
	private String goalItem        = "";
	
	private String goalItemDesc    = "";
	
	public String getGoalItem() {
		return goalItem;
	}
	public void setGoalItem(String goalItem) {
		this.goalItem = goalItem;
	}
	public String getGoalItemDesc() {
		return goalItemDesc;
	}
	public void setGoalItemDesc(String goalItemDesc) {
		this.goalItemDesc = goalItemDesc;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getMtLnPointId() {
		return mtLnPointId;
	}
	public void setMtLnPointId(String mtLnPointId) {
		this.mtLnPointId = mtLnPointId;
		super.setAuditKey(mtLnPointId);
	}
	public String getStartYyyymm() {
		return startYyyymm;
	}
	public void setStartYyyymm(String startYyyymm) {
		this.startYyyymm = CommonUtil.convertDate(startYyyymm);
	}
	public String getEndYyyymm() {
		return endYyyymm;
	}
	public void setEndYyyymm(String endYyyymm) {
		this.endYyyymm = CommonUtil.convertDate(endYyyymm);
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
	public String getEqlocId() {
		return eqlocId;
	}
	public void setEqlocId(String eqlocId) {
		this.eqlocId = eqlocId;
	}
	public String getEqlocIdDesc() {
		return eqlocIdDesc;
	}
	public void setEqlocIdDesc(String eqlocIdDesc) {
		this.eqlocIdDesc = eqlocIdDesc;
	}
	
	
	
	
	


}
