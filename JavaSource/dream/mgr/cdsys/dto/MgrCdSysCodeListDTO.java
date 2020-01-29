package dream.mgr.cdsys.dto;

import common.bean.BaseDTO;

/**
 * 시스템코드 - 분류  DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class MgrCdSysCodeListDTO extends BaseDTO
{
	/** 시스템코드유형상세ID */
	private String cdSysDId 	= "";
	
	private String listType 	= "";
	
	private String cdSysDNo 	= "";

	public String getCdSysDNo()
    {
        return cdSysDNo;
    }

    public void setCdSysDNo(String cdSysDNo)
    {
        this.cdSysDNo = cdSysDNo;
    }

    public String getListType()
    {
        return listType;
    }

    public void setListType(String listType)
    {
        this.listType = listType;
    }

    public String getCdSysDId() {
		return cdSysDId;
	}

	public void setCdSysDId(String cdSysDId) {
		this.cdSysDId = cdSysDId;
	}
}