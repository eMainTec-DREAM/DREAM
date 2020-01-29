package dream.ass.asset.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.ass.asset.dao.AssAssetScoreCopyLovDAO;
import dream.ass.asset.dto.AssAssetScoreCopyLovDTO;

/**
 * 평가결과복사 LOV
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 * 
 * @spring.bean id="assAssetScoreCopyLovDAOTarget"
 * @spring.txbn id="assAssetScoreCopyLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssAssetScoreCopyLovDAOOraImpl extends BaseJdbcDaoSupportOra implements AssAssetScoreCopyLovDAO
{
	@Override
    public List findAssList(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
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
        query.append("      , SFACODE_TO_DESC(x.EQASSLIST_TYPE, 'EQASSLIST_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"') 	AS assType         			   "); 
        query.append("FROM TAEQASSLIST x JOIN TAEQUIPMENT y                                     ");
        query.append("ON X.COMP_NO = Y.COMP_NO                                                  ");
        query.append("    AND X.EQUIP_ID = Y.EQUIP_ID                                           ");
        query.append("WHERE  1=1                                                                ");
        query.append(this.getWhere(assAssetScoreCopyLovDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("x.eqasslist_no", assAssetScoreCopyLovDTO.getOrderBy(), assAssetScoreCopyLovDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assAssetScoreCopyLovDTO.getIsLoadMaxCount(), assAssetScoreCopyLovDTO.getFirstRow()));

    }
    private String getWhere(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
    	QueryBuffer query = new QueryBuffer();
        
        // 평가일자
        query.getAndDateQuery("x.ass_date", assAssetScoreCopyLovDTO.getFilterAssStartDate(), assAssetScoreCopyLovDTO.getFilterAssEndDate());

        
        // 설비 (ID/DESC)
        if(!"".equals(assAssetScoreCopyLovDTO.getFilterEquipDesc()))
        {
            query.append("AND x.equip_id IN (SELECT a.equip_id       ");
            query.append("                      FROM TAEQUIPMENT a   ");
            query.append("                      WHERE 1=1            ");
            query.getLikeQuery("a.description", assAssetScoreCopyLovDTO.getFilterEquipDesc());
            query.append("                   )                       ");
        }
        else if(!"".equals(assAssetScoreCopyLovDTO.getFilterEquipDesc()) && !"".equals(assAssetScoreCopyLovDTO.getFilterEquipId()))
        {
            query.getAndQuery("x.equip_id", assAssetScoreCopyLovDTO.getFilterEquipId());
        }
        
        // 설비번호
        query.getLikeQuery("y.item_no", assAssetScoreCopyLovDTO.getFilterEquipNo());
        
        // 평가등급기준표
        if(!"".equals(conditionMap.get("eqasslist_id")))
        {
            query.append("AND x.assbaselist_id = (SELECT a.assbaselist_id       ");
            query.append("                      FROM TAEQASSLIST a   ");
            query.append("                      WHERE 1=1            ");
            query.getAndQuery("a.eqasslist_id", conditionMap.get("eqasslist_id"));
            query.getAndQuery("a.comp_no", user.getCompNo());
            query.append("                   )                       ");
        }
        
        // 공장
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		assAssetScoreCopyLovDTO.getFilterPlantId(), assAssetScoreCopyLovDTO.getFilterPlantDesc());
        
        // 평가구분
        query.getSysCdQuery("x.eqasslist_type", assAssetScoreCopyLovDTO.getFilterAssTypeId(), assAssetScoreCopyLovDTO.getFilterAssTypeDesc(), "EQASSLIST_TYPE", user.getCompNo(),user.getLangId());
        
        
        return query.toString();
    }
    
	@Override
	public String findTotalCount(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO, User user,
			Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception
	{
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("       COUNT(1)                               ");
        query.append("FROM TAEQASSLIST x JOIN TAEQUIPMENT y         ");
        query.append("ON X.COMP_NO = Y.COMP_NO                      ");
        query.append("    AND X.EQUIP_ID = Y.EQUIP_ID               ");
        query.append("WHERE  1=1                                    ");
        query.append(this.getWhere(assAssetScoreCopyLovDTO, user, columnMap, conditionMap));
        query.append("ORDER BY 1                                    ");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
		return QueryBuffer.listToString(resultList);
	}
	@Override
	public List findAssScoreList(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                            ");
        query.append("       ''                                 AS seqNo                ");
        query.append("       ,''                                AS isDelCheck           ");
        query.append("       , x.eqasspval_id                   AS eqAssPValId          ");
        query.append("       , SFACODE_TO_DESC(x.ASS_POINT_TYPE, 'ASS_POINT_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"') AS assPointTypeDesc      ");
        query.append("       , x.ass_point_type                 AS assPointTypeId       ");
        query.append("       , x.assbasepoint_id                AS assBasePointId       ");
        query.append("       , x.ass_point                      AS assBasePointDesc     ");
        query.append("       , x.assbasepval_id                 AS assEvalId            ");
        query.append("       , x.ass_eval                       AS assEvalDesc          ");
        query.append("       , x.eval_value                     AS eqAssPValDesc        ");
        query.append("       , x.REMARK                         AS remark               ");
        query.append("       , x.eqasslist_id                   AS EQASSLISTID          ");
        query.append("       , (SELECT a.assbaselist_id                                 ");
        query.append("          FROM TAASSBASEPVAL a                                    ");
        query.append("          WHERE a.comp_no = x.comp_no                             ");
        query.append("              AND a.assbasepval_id = x.assbasepval_id) AS assBaseListId       ");
        query.append("FROM TAEQASSPVAL x                                                ");
        query.append("WHERE  1=1                                                        ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getAndNumKeyQuery("x.eqasslist_id", assAssetScoreCopyLovDTO.getEqasslistId());
        
        query.getOrderByQuery("x.eqasspval_id", assAssetScoreCopyLovDTO.getOrderBy(), assAssetScoreCopyLovDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assAssetScoreCopyLovDTO.getIsLoadMaxCount(), assAssetScoreCopyLovDTO.getFirstRow()));
    }
	
}