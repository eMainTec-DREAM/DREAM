package dream.part.rpt.stockkpi.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����ǥ dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class StockKpiCommonDTO extends BaseDTO
{
    /** ���(����) */
    private String filterStartYyyymm    = "";
    /** ���(��) */
    private String filterEndYyyymm      = "";
    /** â�� ID */
    private String filterWCodeId        = "";
    /** â�� desc */
    private String filterWName          = "";
    
    
    public String getFilterStartYyyymm()
    {
        return filterStartYyyymm;
    }
    public void setFilterStartYyyymm(String filterStartYyyymm)
    {
        this.filterStartYyyymm = CommonUtil.convertDate(filterStartYyyymm);
    }
    public String getFilterEndYyyymm()
    {
        return filterEndYyyymm;
    }
    public void setFilterEndYyyymm(String filterEndYyyymm)
    {
        this.filterEndYyyymm = CommonUtil.convertDate(filterEndYyyymm);
    }
    public String getFilterWCodeId()
    {
        return filterWCodeId;
    }
    public void setFilterWCodeId(String filterWCodeId)
    {
        this.filterWCodeId = filterWCodeId;
    }
    public String getFilterWName()
    {
        return filterWName;
    }
    public void setFilterWName(String filterWName)
    {
        this.filterWName = filterWName;
    }
}