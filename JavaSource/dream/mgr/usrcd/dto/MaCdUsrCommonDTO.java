package dream.mgr.usrcd.dto;

import common.bean.BaseDTO;

/**
 * ������ڵ� ���� DTO
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaCdUsrCommonDTO extends BaseDTO
{
	/** Filter-ȸ���ڵ� */
	private String filterCompNo 		= "";
	/** Filter-�ڵ����� */
	private String filterDirType 		= "";
	/** Filter-�ڵ������� */
	private String filterDescription 	= "";
	
	/** ȸ���ڵ� */
	private String compNo 				= "";
	/** �ڵ����� Mst Id */
	private String cdUsrmId				= "";
	/** �ڵ����� Dtl Id */
	private String cdUsrdId				= "";
	
	/** �ڵ����� */
	private String dirType 				= "";
	/** �ڵ������� */
	private String dirTypeDesc			= "";
	/** ������ �����ڵ� */
	private String code 				= "";
	
	public String getCdUsrmId()
    {
        return cdUsrmId;
    }
    public void setCdUsrmId(String cdUsrmId)
    {
        this.cdUsrmId = cdUsrmId;
        super.setAuditKey(cdUsrmId);
    }
    public String getCdUsrdId()
    {
        return cdUsrdId;
    }
    public void setCdUsrdId(String cdUsrdId)
    {
        this.cdUsrdId = cdUsrdId;
        super.setAuditKey(cdUsrdId);
    }
    public String getDirTypeDesc() 
	{
		return dirTypeDesc;
	}
	public void setDirTypeDesc(String dirTypeDesc) 
	{
		this.dirTypeDesc = dirTypeDesc;
	}
	public String getCode() 
	{
		return code;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}
	public String getFilterCompNo() 
	{
		return filterCompNo;
	}
	public void setFilterCompNo(String filterCompNo) 
	{
		this.filterCompNo = filterCompNo;
	}
	public String getFilterDirType() 
	{
		return filterDirType;
	}
	public void setFilterDirType(String filterDirType) 
	{
		this.filterDirType = filterDirType;
	}
	public String getFilterDescription() 
	{
		return filterDescription;
	}
	public void setFilterDescription(String filterDescription) 
	{
		this.filterDescription = filterDescription;
	}
	public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}
	public String getDirType() 
	{
		return dirType;
	}
	public void setDirType(String dirType) 
	{
		this.dirType = dirType;
	}

}
