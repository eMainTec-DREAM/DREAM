package dream.mgr.manst.dto;

import common.bean.BaseDTO;

/**
 * ��������ǥ���� - ��
 * @author  kim21017
 * @version $Id: MaNstGrpDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaNstGrpDetailDTO extends BaseDTO
{
	/** ����No */
	private String popPlantNo 			= "";
	/** ��No */
	private String popDeptNo 			= "";
	/** ����No */
	private String popLineNo 			= "";
	/** ��ǥ����No */
	private String mainLineNo			= "";
	/** ���� ��ǥ��*/
	private String plantRate 			= "";
	/** �� ��ǥ�� */
	private String gwRate	 			= "";
	/** ���θ�ǥ�� */
	private String lineRate 			= "";
	/** ��ǥ���θ�ǥ�� */
	private String mainLineRate			= "";
	/** �⵵ */
	private String yyyy	 				= "";
	/**  */
	private String popDeptDesc          = "";
	/**  */
	private String popPlantDesc         = "";

	public String getPopDeptDesc()
    {
        return popDeptDesc;
    }
    public void setPopDeptDesc(String popDeptDesc)
    {
        this.popDeptDesc = popDeptDesc;
    }
    public String getPopPlantDesc()
    {
        return popPlantDesc;
    }
    public void setPopPlantDesc(String popPlantDesc)
    {
        this.popPlantDesc = popPlantDesc;
    }
    public String getPopPlantNo() {
		return popPlantNo;
	}
	public void setPopPlantNo(String popPlantNo) {
		this.popPlantNo = popPlantNo;
	}
	public String getPopDeptNo() {
		return popDeptNo;
	}
	public void setPopDeptNo(String popDeptNo) {
		this.popDeptNo = popDeptNo;
	}
	public String getPopLineNo() {
		return popLineNo;
	}
	public void setPopLineNo(String popLineNo) {
		this.popLineNo = popLineNo;
	}
	public String getMainLineNo() {
		return mainLineNo;
	}
	public void setMainLineNo(String mainLineNo) {
		this.mainLineNo = mainLineNo;
	}
	public String getPlantRate() {
		return plantRate;
	}
	public void setPlantRate(String plantRate) {
		this.plantRate = plantRate;
	}
	public String getGwRate() {
		return gwRate;
	}
	public void setGwRate(String gwRate) {
		this.gwRate = gwRate;
	}
	public String getLineRate() {
		return lineRate;
	}
	public void setLineRate(String lineRate) {
		this.lineRate = lineRate;
	}
	public String getMainLineRate() {
		return mainLineRate;
	}
	public void setMainLineRate(String mainLineRate) {
		this.mainLineRate = mainLineRate;
	}
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}
	
}
