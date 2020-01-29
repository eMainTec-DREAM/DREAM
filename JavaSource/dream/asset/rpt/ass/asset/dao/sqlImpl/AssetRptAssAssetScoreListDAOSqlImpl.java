package dream.asset.rpt.ass.asset.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.rpt.ass.asset.dao.AssetRptAssAssetScoreListDAO;
import dream.asset.rpt.ass.asset.dto.AssetRptAssAssetScoreCommonDTO;

/**
 * AssetRptAssAssetScoreList Page - List DAO implements
 * @author nhkim8548
 * @version $Id: AssetRptAssAssetScoreListDAOSqlImpl.java,v 1.0 2018/08/24 17:21:40 nhkim8548 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assetRptAssAssetScoreListDAOTarget"
 * @spring.txbn id="assetRptAssAssetScoreListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptAssAssetScoreListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements AssetRptAssAssetScoreListDAO
{
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id: assetRptAssAssetScoreListDAO.java,v 1.0 2018/08/05 17:17:12 nhkim8548 Exp $
     * @since   1.0
     * 
     * @param assetRptAssAssetScoreListDTO
     * @return List
     */
    public List findList(AssetRptAssAssetScoreCommonDTO assetRptAssAssetScoreCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append(" SELECT                                                                                                                                    ");
        query.append("         ''                                                                                                             AS seqNo           ");
        query.append("       , ( SELECT a.item_no                                                                                                                  ");
        query.append("             FROM taequipment a                                                                                                              ");
        query.append("            WHERE a.equip_id = x.equip_id )                                                                               AS equipNo         ");
        query.append("       , ( SELECT b.description                                                                                                              ");
        query.append("             FROM taequipment b                                                                                                             ");
        query.append("            WHERE b.equip_id = x.equip_id )                                                                               AS equipName,       ");
        query.getDate("x.ass_date", "assDate");
        query.append("       , dbo.SFACODE_TO_DESC(y.ass_point_type, 'ASS_POINT_TYPE', 'SYS', '"+ user.getCompNo() +"', '"+ user.getLangId() +"') AS assCategoryType ");
        query.append("       , y.ass_point                                                                                                    AS assCategory     ");
        query.append("       , y.ass_eval                                                                                                     AS assResult       ");
        query.append("       , y.eval_value                                                                                                   AS assScore        ");
        query.append("       , ( SELECT c.emp_name                                                                                                                 ");
        query.append("             FROM taemp c                                                                                                                   ");
        query.append("            WHERE c.comp_no = x.comp_no and c.emp_id = x.emp_id ) + ' / ' +                                                                                                    ");
        query.append("         ( SELECT d.description                                                                                                              ");
        query.append("             FROM tadept d                                                                                                                  ");
        query.append("            WHERE d.comp_no = x.comp_no and d.dept_id = x.dept_id )                                                                                 AS assessor        ");
        query.append("   FROM TAEQASSLIST x                                                                                                                      ");
        query.append("  INNER JOIN TAEQASSPVAL y                                                                                                                 ");
        query.append("          ON x.eqasslist_id = y.eqasslist_id                                                                                               ");
        query.append("  WHERE 1=1                                                                                                                                ");
        query.append(this.getWhere(assetRptAssAssetScoreCommonDTO, user));
        query.getOrderByQuery("y.eqasspval_id", "x.equip_id, y.eqasspval_id", assetRptAssAssetScoreCommonDTO.getOrderBy(), assetRptAssAssetScoreCommonDTO.getDirection());                
        
        return getJdbcTemplate().queryForList(query.toString(assetRptAssAssetScoreCommonDTO.getIsLoadMaxCount(), assetRptAssAssetScoreCommonDTO.getFirstRow()));
    }
    
    private String getWhere(AssetRptAssAssetScoreCommonDTO assetRptAssAssetScoreCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
     // 완료
        query.getAndQuery("x.eqasslist_status", "C");
        // 회사번호
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        // 설비번호
        query.getLikeQuery("( SELECT e.item_no FROM taequipment e WHERE e.comp_no = x.comp_no AND e.equip_id = x.equip_id )", assetRptAssAssetScoreCommonDTO.getFilterEquipNo());
        // 설비명
        query.getLikeQuery("( SELECT f.description FROM taequipment f WHERE f.comp_no = x.comp_no AND f.equip_id = x.equip_id )", assetRptAssAssetScoreCommonDTO.getFilterEquipDesc());
        // 평가항목구분
        query.getSysCdQuery("y.ass_point_type", assetRptAssAssetScoreCommonDTO.getFilterAssCategoryTypeId(), assetRptAssAssetScoreCommonDTO.getFilterAssCategoryTypeDesc(), "ASS_POINT_TYPE", user.getCompNo(), user.getLangId());
        // 공장명        
        query.getCodeLikeQuery("(SELECT g.plant FROM taequipment g WHERE g.equip_id = x.equip_id AND g.comp_no = '"+ user.getCompNo() +"' )", "(SELECT h.description FROM  TAPLANT h WHERE comp_no = '"+user.getCompNo()+"'  AND h.plant = (SELECT i.plant FROM taequipment i WHERE i.comp_no = '"+user.getCompNo()+"' AND i.equip_id = x.equip_id))", assetRptAssAssetScoreCommonDTO.getFilterPlantId(), assetRptAssAssetScoreCommonDTO.getFilterPlantDesc());
        // 평가항목
        query.getLikeQuery("y.ass_point", assetRptAssAssetScoreCommonDTO.getFilterAssCategoryDesc());
        // 평가일자
        query.getAndDateQuery("x.ass_date", assetRptAssAssetScoreCommonDTO.getFilterAssStartDate(), assetRptAssAssetScoreCommonDTO.getFilterAssEndDate());
        
        return query.toString();
    }
    public String findTotalCount(AssetRptAssAssetScoreCommonDTO assetRptAssAssetScoreCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(" SELECT                                      ");
        query.append("        COUNT(1)                             ");
        query.append("   FROM TAEQASSLIST x                        ");
        query.append("  INNER JOIN TAEQASSPVAL y                   ");
        query.append("          ON x.eqasslist_id = y.eqasslist_id ");
        query.append("  WHERE 1=1                                  ");
        query.append(this.getWhere(assetRptAssAssetScoreCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
