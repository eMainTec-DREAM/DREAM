package dream.asset.rpt.nyearpo.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.rpt.nyearpo.dao.AssetRptNYearPOListDAO;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPOCommonDTO;

/**
 * N Year Spare Part 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptNYearPOListDAOTarget"
 * @spring.txbn id="assetRptNYearPOListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptNYearPOListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetRptNYearPOListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptNYearPOCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 													");
        query.append("    '' 					seqNo							");
        query.append("       ,a.ven_po_no     	venPono							");
        query.append("       ,a.op_period       opPeriod						");
        query.append("       ,b.vendor_no     	vendorNo						");
        query.append("       ,b.description   	vendorDesc						");
        query.append("       ,b.address       	addr							");
        query.append("       ,b.office        	office							");
        query.append("FROM TAEQPARTOPQTY a LEFT OUTER JOIN TAVENDOR b 			");
        query.append("ON a.comp_no = b.comp_no AND a.vendor_id = b.vendor_id	");
        query.append("WHERE 1=1													");
        query.append(this.getWhere(assetRptNYearPOCommonDTO,loginUser));
        query.append("GROUP BY a.ven_po_no, a.op_period, b.vendor_no, b.description, b.address, b.office		");
        query.getOrderByQuery("a.eqpartopqty_id", "a.ven_po_no", assetRptNYearPOCommonDTO.getOrderBy(), assetRptNYearPOCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptNYearPOCommonDTO.getIsLoadMaxCount(), assetRptNYearPOCommonDTO.getFirstRow()));
    }
    
    public String getWhere(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());

        //거래처
        query.getCodeLikeQuery("b.vendor_id", "b.description", 
        		assetRptNYearPOCommonDTO.getFilterVendorId() , assetRptNYearPOCommonDTO.getFilterVendorDesc());

        //PO#
        query.getLikeQuery("a.ven_po_no", assetRptNYearPOCommonDTO.getFilterPoNumber());
        
        //운영기간(년)
        query.getLikeQuery("a.op_period", assetRptNYearPOCommonDTO.getFilterOpPeriod());
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 													");
        query.append("    COUNT(1)												");
        query.append("FROM														");
        query.append("(															");
        query.append("SELECT                                         			");
    	query.append("       COUNT(1)                                			");
        query.append("FROM TAEQPARTOPQTY a LEFT OUTER JOIN TAVENDOR b 			");
        query.append("ON a.comp_no = b.comp_no AND a.vendor_id = b.vendor_id	");
        query.append("WHERE 1=1													");
        query.append(this.getWhere(assetRptNYearPOCommonDTO,user));
        query.append("GROUP BY a.ven_po_no, a.op_period, b.vendor_no, b.description, b.address, b.office		");
        query.append(") a														");
                
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}