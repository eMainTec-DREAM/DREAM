package dream.mgr.manst.dto;

import common.bean.BaseDTO;

/**
 * 무정지대표라인 - 상세
 * @author  kim21017
 * @version $Id: MaNstGrpDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaNstGrpDetailDTO extends BaseDTO
{
	/** 공장No */
	private String popPlantNo 			= "";
	/** 과No */
	private String popDeptNo 			= "";
	/** 라인No */
	private String popLineNo 			= "";
	/** 대표라인No */
	private String mainLineNo			= "";
	/** 공장 목표율*/
	private String plantRate 			= "";
	/** 과 목표율 */
	private String gwRate	 			= "";
	/** 라인목표율 */
	private String lineRate 			= "";
	/** 대표라인목표율 */
	private String mainLineRate			= "";
	/** 년도 */
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
