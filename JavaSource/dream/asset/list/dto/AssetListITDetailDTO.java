package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.bean.MwareConfig;

/**
 * IT장비목록 - Detail DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class AssetListITDetailDTO extends BaseDTO
{
	/** IT장비 ID */
	private String equipId 				= "";
	/** IT장비 NO */
	private String itemNo 		    	= "";
	/** IT장비 DESC */                     
	private String equipDesc 		    = "";
	/** OS */                   
	private String osName 	    		= MwareConfig.getEmptyFieldValue();
	/** hostName */                   
	private String hostName 	    	= MwareConfig.getEmptyFieldValue();
	/** IP Address */                   
	private String ipAddr 	    		= MwareConfig.getEmptyFieldValue();
	/** Mac Address */                   
	private String macAddr 	    		= MwareConfig.getEmptyFieldValue();

	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getMacAddr() {
		return macAddr;
	}
	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}
}
