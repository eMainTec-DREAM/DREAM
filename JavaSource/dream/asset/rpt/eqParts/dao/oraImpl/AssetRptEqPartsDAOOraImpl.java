package dream.asset.rpt.eqParts.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.rpt.eqParts.dao.AssetRptEqPartsDAO;
import dream.asset.rpt.eqParts.dto.AssetRptEqPartsDTO;

/**
 * 汲厚备己何前  - DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptEqPartsDAOTarget"
 * @spring.txbn id="assetRptEqPartsDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptEqPartsDAOOraImpl  extends BaseJdbcDaoSupportOra implements AssetRptEqPartsDAO
{
    public List findList(AssetRptEqPartsDTO assetRptEqPartsDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                        ");
        query.append("    ''                                    AS SEQNO            ");
        query.append("    ,a.item_no                            AS ITEMNO           ");
        query.append("    ,a.description                        AS EQUIPDESC        ");
        query.append("    ,(SELECT full_desc FROM TAEQLOC                           ");
        query.append("      WHERE comp_no = a.comp_no                               ");
        query.append("      AND eqloc_id = a.eqloc_id)          AS EQLOCDESC        ");
        query.append("    ,(SELECT full_desc FROM TAEQCTG                           ");
        query.append("      WHERE comp_no = a.comp_no                               ");
        query.append("      AND eqctg_id = a.eqctg_id)          AS EQCTGDESC        ");
        query.append("    ,(SELECT description FROM TAPLANT                         ");
        query.append("      WHERE comp_no = a.comp_no                               ");
        query.append("      AND plant = a.plant)                AS PLANTDESC        ");
        query.append("    ,c.eqasmb_no                          AS EQASMBNO         ");
        query.append("    ,NVL(c.full_desc, c.description)      AS EQASMBDESC       ");
        query.append("    ,d.part_no                            AS PARTNO           ");
        query.append("    ,d.description                        AS PARTDESC         ");
        query.append("    ,d.pt_size                            AS PARTSIZE         ");
        query.append("    ,d.MODEL                              AS PARTMODEL        ");
        query.append("    ,d.maker                              AS PARTMAKER        ");
        query.append("    ,b.consist_qty                        AS CONSISTQTY       ");
        query.append("    ,b.use_qty                            AS USEQTY           ");
        query.append("    ,b.issue_time                         AS ISSUETIME        ");
        query.append("    ,b.issue_first_date                   AS ISSUEFIRSTDATE   ");
        query.append("    ,b.issue_last_date                    AS ISSUELASTDATE    ");
        query.append("    ,a.equip_id                           AS EQUIPID          ");
        query.append("    ,b.part_id                            AS PARTID           ");
        query.append("    ,b.eqasmb_id                          AS EQASMBID         ");
        query.append("FROM TAEQUIPMENT a INNER JOIN TAEQPART b                      ");
        query.append("ON a.comp_no = b.comp_no AND a.equip_id = b.equip_id          ");
        query.append("LEFT OUTER JOIN TAEQASMB c                                    ");
        query.append("ON b.comp_no = c.comp_no AND b.eqasmb_id = c.eqasmb_id AND c.is_deleted = 'N' AND c.is_use = 'Y'  ");
        query.append("INNER JOIN TAPARTS d                                          ");
        query.append("ON b.comp_no =d.comp_no AND b.part_id = d.part_id             ");
        query.append("WHERE 1 = 1                                                   ");
        query.append(this.getWhere(assetRptEqPartsDTO, user));
        query.getOrderByQuery("a.equip_id, b.eqpart_id", assetRptEqPartsDTO.getOrderBy(), assetRptEqPartsDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptEqPartsDTO.getIsLoadMaxCount(), assetRptEqPartsDTO.getFirstRow()));
    }

    private String getWhere(AssetRptEqPartsDTO assetRptEqPartsDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.append("  AND a.is_deleted = 'N'          ");
        query.append("  AND a.is_last_version = 'Y'     ");
        query.append("  AND b.is_use = 'Y'              ");
        query.append("  AND d.is_deleted = 'N'          ");
        query.append("  AND d.is_use = 'Y'              ");
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        
        query.getAndQuery("a.item_no", assetRptEqPartsDTO.getItemNo());
        
        query.getLikeQuery("a.description", assetRptEqPartsDTO.getEquipDesc());
        
        query.getAndQuery("d.part_no", assetRptEqPartsDTO.getPartNo());
        
        query.getLikeQuery("d.description", assetRptEqPartsDTO.getPartDesc());
        
        query.getEqCtgLevelQuery("a.eqctg_id", assetRptEqPartsDTO.getEqCtgId(), assetRptEqPartsDTO.getEqCtgDesc(), user.getCompNo());
        
        query.getPlantCdQuery("a.plant", assetRptEqPartsDTO.getPlant(), assetRptEqPartsDTO.getPlantDesc(), user.getCompNo());
        
        return query.toString();
    }

    public String findTotalCount(AssetRptEqPartsDTO assetRptEqPartsDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                        ");
        query.append("    COUNT(1)                                                  ");
        query.append("FROM TAEQUIPMENT a INNER JOIN TAEQPART b                      ");
        query.append("ON a.comp_no = b.comp_no AND a.equip_id = b.equip_id          ");
        query.append("LEFT OUTER JOIN TAEQASMB c                                    ");
        query.append("ON b.comp_no = c.comp_no AND b.eqasmb_id = c.eqasmb_id AND c.is_deleted = 'N' AND c.is_use = 'Y'  ");
        query.append("INNER JOIN TAPARTS d                                          ");
        query.append("ON b.comp_no =d.comp_no AND b.part_id = d.part_id             ");
        query.append("WHERE 1 = 1                                                   ");
        query.append(this.getWhere(assetRptEqPartsDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
