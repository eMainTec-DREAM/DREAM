package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.bean.MwareConfig;

/**
 * ���񸶽��� - �ǹ� �� DTO
 * @author  kim21017
 * @version $Id: MaEqBuildMstrDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaEqBuildMstrDetailDTO extends BaseDTO
{
	/** ����ID */
	private String equipId 					= "";
	/** ������  */
	private String eqBuildAddr 				= MwareConfig.getEmptyFieldValue();
	/** ����  */
	private String eqBuildArea 				= MwareConfig.getEmptyFieldValue();
	/** ���� */
	private String eqBuildStruct				= MwareConfig.getEmptyFieldValue();
	/** �� */
	private String eqBuildFloor 				= MwareConfig.getEmptyFieldValue();
	/** �� */
	private String eqBuildSection 			= MwareConfig.getEmptyFieldValue();
	/** �۾��׷� Id */
	private String wkctrId					= "";
	/** �۾��׷� Desc */
	private String wkctrDesc				= "";
	
	
	public String getWkctrId() {
		return wkctrId;
	}
	public void setWkctrId(String wkctrId) {
		this.wkctrId = wkctrId;
	}
	public String getWkctrDesc() {
		return wkctrDesc;
	}
	public void setWkctrDesc(String wkctrDesc) {
		this.wkctrDesc = wkctrDesc;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEqBuildAddr() {
		return eqBuildAddr;
	}
	public void setEqBuildAddr(String eqBuildAddr) {
		this.eqBuildAddr = eqBuildAddr;
	}
	public String getEqBuildArea() {
		return eqBuildArea;
	}
	public void setEqBuildArea(String eqBuildArea) {
		this.eqBuildArea = eqBuildArea;
	}
	public String getEqBuildStruct() {
		return eqBuildStruct;
	}
	public void setEqBuildStruct(String eqBuildStruct) {
		this.eqBuildStruct = eqBuildStruct;
	}
	public String getEqBuildFloor() {
		return eqBuildFloor;
	}
	public void setEqBuildFloor(String eqBuildFloor) {
		this.eqBuildFloor = eqBuildFloor;
	}
	public String getEqBuildSection() {
		return eqBuildSection;
	}
	public void setEqBuildSection(String eqBuildSection) {
		this.eqBuildSection = eqBuildSection;
	}
}
