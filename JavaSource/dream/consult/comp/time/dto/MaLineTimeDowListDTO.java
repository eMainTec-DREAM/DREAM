package dream.consult.comp.time.dto;

import common.bean.BaseDTO;

/**
 * 요일별 설정 목록 dto
 * @author  kim21017
 * @version $Id: MaLineTimeDowListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaLineTimeDowListDTO extends BaseDTO
{
    /** 회사No */
    private String compNo               = "";
	/** 요일별 설정 id */
	private String eqLocDowRunId 	= "";

	public String getCompNo()
    {
        return compNo;
    }

    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }

    public String getEqLocDowRunId() {
		return eqLocDowRunId;
	}

	public void setEqLocDowRunId(String eqLocDowRunId) {
		this.eqLocDowRunId = eqLocDowRunId;
	}
}