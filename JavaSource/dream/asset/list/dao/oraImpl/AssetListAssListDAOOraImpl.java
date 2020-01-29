package dream.asset.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.list.dao.AssetListAssListDAO;
import dream.asset.list.dto.AssetListAssListDTO;

/**
 * AssetListAss Page - List DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetListAssListDAOTarget"
 * @spring.txbn id="assetListAssListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetListAssListDAOOraImpl  extends BaseJdbcDaoSupportOra implements AssetListAssListDAO
{

    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetListAssListDTO
     * @return List
     */
    public List findList(AssetListAssListDTO assetListAssListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("      ''                                  AS seqNo                        ");
        query.append("      ,''                                 AS isDelCheck                   ");
        query.append("      , x.eqasslist_id                    AS eqasslistId                  ");
        query.append("      , x.eqasslist_no                    AS eqasslistNo                  ");
        query.append("      , y.eqloc_id                        AS eqlocId                      ");
        query.append("      , (SELECT a.full_desc                                               ");
        query.append("         FROM TAEQLOC a                                                   ");
        query.append("         WHERE a.eqloc_id = y.eqloc_id)   AS eqLocDesc                    ");
        query.append("      , x.equip_id                        AS equipId                      ");
        query.append("      , y.item_no                         AS ITEMNO                       ");
        query.append("      , y.description                     AS equipDesc                    ");
        query.append("     , y.p_equip_id                     	AS parEquipId				    ");
        query.append("     , (SELECT b.description FROM TAEQUIPMENT b 						    ");
        query.append("        WHERE b.comp_no = y.comp_no 									    ");
        query.append("          AND b.equip_id = y.p_equip_id)  AS parEquipDesc				    ");
        query.append("     , (SELECT a.full_desc                                                ");
        query.append("        FROM TAEQLOC a                                                    ");
        query.append("        WHERE a.eqloc_id = (SELECT b.eqloc_id FROM TAEQUIPMENT b 		    ");
        query.append("                             WHERE b.comp_no = a.comp_no 				    ");
        query.append("                               AND b.equip_id = y.p_equip_id)    )    AS parEqLocDesc		");
        query.append("      , x.eq_grade                        AS eqGradeId                    ");
        query.append("      , SFACODE_TO_DESC(x.eq_grade, 'EQ_GRADE', 'SYS', x.comp_no, '"+user.getLangId()+"') AS eqGradeDesc         ");
        query.append("      , (SELECT SUM(a.eval_value)                                         ");
        query.append("         FROM TAEQASSPVAL a                                               ");
        query.append("         WHERE a.comp_no = x.comp_no                                      ");
        query.append("             AND a.eqasslist_id = X.eqasslist_id                          ");
        query.append("         GROUP BY a.comp_no, a.eqasslist_id ) AS evalValue                ");
        query.append("      , x.ass_date                        AS assDate                      ");
        query.append("      , x.emp_id                          AS empId                        ");
        query.append("      , (SELECT a.emp_name                                                ");
        query.append("         FROM TAEMP a                                                     ");
        query.append("         WHERE x.emp_id = a.emp_id)       AS empName                      ");
        query.append("      , x.remark                          AS remark                       ");
        query.append("      , x.eqasslist_status                AS eqasslistStatusId            "); 
        query.append("      , SFACODE_TO_DESC(x.eqasslist_status, 'EQASSLIST_STATUS', 'SYS', x.comp_no, '"+user.getLangId()+"') AS eqasslistStatusDesc         "); 
        query.append("      , x.pmi_action_type                 AS PMIACTIONTYPE                ");
        query.append("      , (SELECT a.description FROM TAASSBASELIST a 						");
        query.append("          WHERE a.comp_no = x.comp_no 									");
        query.append("            AND a.assbaselist_id = x.assbaselist_id)    AS ASSGRADEBASETABLE		");
        query.append("      , (SELECT SFACODE_TO_DESC( x.eqasslist_type, 'EQASSLIST_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"') 		");
        query.append("           FROM dual)    					AS ASSTYPEDESC					");
        query.append("FROM TAEQASSLIST x JOIN TAEQUIPMENT y                                     ");
        query.append("ON X.COMP_NO = Y.COMP_NO                                                  ");
        query.append("    AND X.EQUIP_ID = Y.EQUIP_ID                                           ");
        query.append("WHERE  1=1                                                                ");
        query.append(this.getWhere(assetListAssListDTO, user));
        query.getOrderByQuery("x.eqasslist_no DESC", assetListAssListDTO.getOrderBy(), assetListAssListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetListAssListDTO.getIsLoadMaxCount(), assetListAssListDTO.getFirstRow()));
    }
    
    private String getWhere(AssetListAssListDTO assetListAssListDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        
        // 평가구분이 최종평가인것만 조회
        query.getAndQuery("x.eqasslist_type", "LT");
        
        if (!"".equals(assetListAssListDTO.getEqasslistId()))
        {
            query.getAndQuery("x.eqasslist_id", assetListAssListDTO.getEqasslistId());
            return query.toString();
        }
        
        query.getAndQuery("x.equip_id", assetListAssListDTO.getEquipId());
        
        return query.toString();
    }
    
    public int chkDelRow(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append(" SELECT count(1)              ");
        query.append("   FROM TAEQASSLIST           ");
        query.append("  WHERE comp_no          = ?  ");
        query.append("    AND eqasslist_id     = ?  ");
        query.append("    AND eqasslist_status = ?  ");

        Object[] objects = new Object[] {   
                 user.getCompNo()
               , id
               , "C"
        };
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    
    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAEQASSLIST   ");
        query.append("WHERE eqasslist_id = ?    ");
        query.append("  AND comp_no = ?         ");
        
        Object[] objects = new Object[] {   
                id
                ,user.getCompNo()
        };
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public String findTotalCount(
            AssetListAssListDTO assetListAssListDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAEQASSLIST  x JOIN TAEQUIPMENT y                  ");
        query.append("ON X.COMP_NO = Y.COMP_NO                                  ");
        query.append("  AND X.EQUIP_ID = Y.EQUIP_ID                             ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(assetListAssListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
