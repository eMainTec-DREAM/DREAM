package dream.asset.rpt.nyearpo.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.asset.rpt.nyearpo.dao.AssetRptNYearPODetailDAO;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPOCommonDTO;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPODetailDTO;

/**
 * N Year Spare Part 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptNYearPODetailDAOTarget"
 * @spring.txbn id="assetRptNYearPODetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptNYearPODetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetRptNYearPODetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptNYearPODetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO,AssetRptNYearPODetailDTO assetRptNYearPODetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                     			");
        query.append("   ''                                         AS SEQNO            	");
        query.append("   , a.item_no                                AS ITEMNO            	");
        query.append("   , a.description                            AS EQUIPDESC			");
        query.append("   , a.tag_no                                 AS EQUIPTAGNO        	");
        query.append("   , d.part_no                                AS PARTNO          		");
        query.append("   , d.description                            AS PARTDESC				");
        query.append("   , d.pt_size                                AS PARTSIZE          	");
        query.append("   , d.MODEL                                  AS PARTMODEL			");
        query.append("   , b.consist_nbr                            AS CONSISTNBR			");
        query.append("   , b.dwg_no                                 AS DWGNO				");
        query.append("   , b.dwg_section_no                         AS DWGSECTIONNO			");
        query.append("   , b.consist_qty                            AS CONSISTQTY			");
        query.append("   , d.uom                              		AS UOM					");
        query.append("   , d.maker                            		AS PARTMAKER			");
        query.append("   , d.vendor_code                      		AS VENDORCODE			");
        query.append("   , E.op_qty                           		AS OPQTY				");
        query.append("   , d.lead_time                        		AS LEADTIME				");
        query.append("   , d.last_price                       		AS LEADPRICE			");
        query.append("   , convert(nvarchar, d.lead_time) * d.last_price  	AS TOTAMT				");
        query.append("FROM TAEQUIPMENT a   INNER JOIN TAEQPART b  ON a.comp_no = b.comp_no AND a.equip_id = b.equip_id		");
        query.append("    INNER JOIN TAPARTS d ON b.comp_no =d.comp_no AND b.part_id = d.part_id  							");
        query.append("    INNER JOIN TAEQPARTOPQTY E ON d.comp_no = E.comp_no AND b.part_id = E.part_id AND b.eqpart_id = E.eqpart_id		");
        query.append("  --INNER JOIN TAEQPARTOPQTY E ON d.comp_no = E.comp_no AND b.part_id = E.part_id AND b.eqpart_id = E.eqpart_id       ");
        query.append("WHERE 1 = 1    														");
        query.append(this.getWhere(assetRptNYearPOCommonDTO, assetRptNYearPODetailDTO,loginUser));
        query.append("ORDER BY a.equip_id, b.eqpart_id										");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO,AssetRptNYearPODetailDTO assetRptNYearPODetailDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("e.ven_po_no", assetRptNYearPODetailDTO.getVenPoNo());
        
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("a.is_last_version", "Y");
        query.getAndQuery("b.is_use", "Y");
        query.getAndQuery("d.is_deleted", "N");
        query.getAndQuery("d.is_use", "Y");
        
        return query.toString();
    }
    
    public String findCount(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO,AssetRptNYearPODetailDTO assetRptNYearPODetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                          														");
        query.append("    COUNT(1)                                    														");
        query.append("FROM TAEQUIPMENT a   INNER JOIN TAEQPART b  ON a.comp_no = b.comp_no AND a.equip_id = b.equip_id		");
        query.append("    INNER JOIN TAPARTS d ON b.comp_no =d.comp_no AND b.part_id = d.part_id  							");
        query.append("    INNER JOIN TAEQPARTOPQTY E ON d.comp_no = E.comp_no AND b.part_id = E.part_id AND b.eqpart_id = E.eqpart_id		");
        query.append("  --INNER JOIN TAEQPARTOPQTY E ON d.comp_no = E.comp_no AND b.part_id = E.part_id AND b.eqpart_id = E.eqpart_id       ");
        query.append("WHERE 1 = 1    														");
        query.append(this.getWhere(assetRptNYearPOCommonDTO, assetRptNYearPODetailDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
}