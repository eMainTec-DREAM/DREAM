package dream.part.iss.wo.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �������Ȯ�� - �� DTO
 * @author  hyosung
 * @version $Id:$
 * @since   1.0
 *
 */
public class PartIssWoItemListDTO extends BaseDTO
{
    /** ȸ��*/
    private String compNo  ="";
    /** ���ø��� ID  */
    private String ptisslistSerialId=  "";
    /** ����� ID */
    private String ptisslistId          = "";
    /** �ø��� NO*/
    private String serialNo             ="";
    /** ������ */
    private String issueQty             = "";
    /** ��ǰ */
    private String partId               = "";
    /** ��ǰ NO*/
    private String partNo               = "";
    /** ��ǰ�� */
    private String partDesc             = "";
	/** ����ID */
    private String equipId          ="";
    /** ���   */
    private String remark       ="";
   
    
 
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
   
    public String getPtisslistSerialId()
    {
        return ptisslistSerialId;
    }
    public void setPtisslistSerialId(String ptisslistSerialId)
    {
        this.ptisslistSerialId = ptisslistSerialId;
    }
    public String getPtisslistId()
    {
        return ptisslistId;
    }
    public void setPtisslistId(String ptisslistId)
    {
        this.ptisslistId = ptisslistId;
    }
    public String getSerialNo()
    {
        return serialNo;
    }
    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }
    public String getIssueQty()
    {
        return issueQty;
    }
    public void setIssueQty(String issueQty)
    {
        this.issueQty = issueQty;
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getPartDesc()
    {
        return partDesc;
    }
    public void setPartDesc(String partDesc)
    {
        this.partDesc = partDesc;
    }
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    
    
    
    
}
