package dream.part.rpt.stockkpi.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 재고지표 dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class StockKpiCommonDTO extends BaseDTO
{
    /** 년월(시작) */
    private String filterStartYyyymm    = "";
    /** 년월(끝) */
    private String filterEndYyyymm      = "";
    /** 창고 ID */
    private String filterWCodeId        = "";
    /** 창고 desc */
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