package dream.ass.asset.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.ass.asset.dao.AssAssetDetailDAO;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetDetailDTO;

/**
 * AssAsset Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id: AssAssetDetailDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assAssetDetailDAOTarget"
 * @spring.txbn id="assAssetDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssAssetDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssAssetDetailDAO
{

    public AssAssetDetailDTO findDetail(AssAssetCommonDTO assAssetCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                ");
        query.append("       x.eqasslist_id                     AS eqasslistId              ");
        query.append("     , x.eqasslist_no                     AS eqasslistNo              ");
        query.append("     , x.eqasslist_status                 AS eqasslistStatusId        ");
        query.append("     , SFACODE_TO_DESC(x.eqasslist_status, 'EQASSLIST_STATUS', 'SYS', x.comp_no, ?) AS eqasslistStatusDesc          ");
        query.append("     , y.eqloc_id                         AS eqLocId                  ");
        query.append("     , (SELECT a.full_desc                                            ");
        query.append("        FROM TAEQLOC a                                                ");
        query.append("        WHERE a.eqloc_id = y.eqloc_id)    AS eqLocDesc                ");
        query.append("     , x.equip_id                         AS equipId                  ");
        query.append("     , y.description                      AS equipDesc                ");
        query.append("     , y.item_no                          AS equipNo                  ");
        query.append("     , y.p_equip_id                     	AS parEquipId				");
        query.append("     , (SELECT b.description FROM TAEQUIPMENT b 						");
        query.append("        WHERE b.comp_no = y.comp_no 									");
        query.append("          AND b.equip_id = y.p_equip_id)  AS parEquipDesc				");
        query.append("     , (SELECT a.full_desc                                            ");
        query.append("        FROM TAEQLOC a                                                ");
        query.append("        WHERE a.eqloc_id = (SELECT b.eqloc_id FROM TAEQUIPMENT b 		");
        query.append("                             WHERE b.comp_no = a.comp_no 				");
        query.append("                               AND b.equip_id = y.p_equip_id)    )    AS parEqLocDesc		");
        query.append("     , x.assbaselist_id                   AS assBaseListId            ");
        query.append("     , (SELECT a.description                                          ");
        query.append("        FROM TAASSBASELIST a                                          ");
        query.append("        WHERE a.comp_no = x.comp_no                                   ");
        query.append("          AND a.assbaselist_id = x.assbaselist_id) AS assBaseListDesc ");
        query.append("     , x.emp_id                           AS empId                    ");
        query.append("     , (SELECT a.emp_name                                             ");
        query.append("        FROM TAEMP a                                                  ");
        query.append("        WHERE x.emp_id = a.emp_id)        AS empName                  ");
        query.append("     , x.eq_grade                         AS eqGradeId                ");
        query.append("     , SFACODE_TO_DESC(x.eq_grade, 'EQ_GRADE', 'SYS', x.comp_no, ?) AS eqGradeDesc         ");
        query.append("     , (SELECT SUM(a.eval_value)                                      ");
        query.append("        FROM TAEQASSPVAL a                                            ");
        query.append("        WHERE a.comp_no = x.comp_no                                   ");
        query.append("            AND a.eqasslist_id = X.eqasslist_id                       ");
        query.append("            GROUP BY a.comp_no, a.eqasslist_id ) AS evalValue         ");
        query.append("     , x.ass_date                         AS assDate                  ");
        query.append("     , x.REMARK AS remark                                             ");
        query.append("     , x.pmi_action_type                      pmiActionType        	");
        query.append("     , SFACODE_TO_DESC(x.pmi_action_type,'PMI_ACTION_TYPE','SYS','',?)	pmiActionTypeDesc        ");
        query.append("	   , x.plant 								plantId					");
        query.append("     , (SELECT a.description 											");
        query.append("		 FROM TAPLANT a 												");
        query.append("		 WHERE a.comp_no = x.comp_no 									");
        query.append("		 AND a.plant = x.plant ) 				plantDesc				");
        query.append("     , x.eqasslist_type						assTypeId	        	");
        query.append("     , SFACODE_TO_DESC(x.eqasslist_type,'EQASSLIST_TYPE','SYS','',?)  	assTypeDesc        ");
        query.append("FROM TAEQASSLIST x JOIN TAEQUIPMENT y                                 ");
        query.append("ON x.comp_no = y.comp_no                                              ");
        query.append("AND x.equip_id = y.equip_id                                           ");

        query.append("WHERE  1=1                                                            ");
        query.append("AND    x.comp_no    = ?                                               ");
        query.append("AND    x.eqasslist_id    = ?                                          ");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getLangId()
                ,user.getLangId()
                ,user.getCompNo()
                ,assAssetCommonDTO.getEqasslistId()
        };
    
        AssAssetDetailDTO assAssetDetailDTO = 
                (AssAssetDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new AssAssetDetailDTO()));
        
        return assAssetDetailDTO;
        
    }
    

    public int insertDetail(AssAssetDetailDTO assAssetDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("INSERT INTO TAEQASSLIST(  ");
        query.append("  comp_no                 ");
        query.append(", eqasslist_id            ");
        query.append(", eqasslist_no            ");
        query.append(", equip_id                ");
        query.append(", eqasslist_status        ");
        query.append(", assbaselist_id          ");
        query.append(", ass_date                ");
        query.append(", dept_id                 ");
        query.append(", emp_id                  ");
        query.append(", eq_grade                ");
        query.append(", remark                  ");
        query.append(", pmi_action_type         ");
        query.append(", plant			        ");
        query.append(", eqasslist_type	        ");
        query.append(")VALUES(                  ");
        query.append("   ?                      ");
        query.append(" , ?                      ");
        query.append(" , ?                      ");
        query.append(" , ?                      ");
        query.append(" , ?                      ");
        query.append(" , ?                      ");
        query.append(" , ?                      ");
        query.append(" , ?                      ");
        query.append(" , ?                      ");
        query.append(" , ?                      ");
        query.append(" , ?                      ");
        query.append(" , ?                      ");
        query.append(" , ?                      ");
        query.append(" , NVL(?,'LT')            ");
        query.append(" )                        ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,assAssetDetailDTO.getEqasslistId()
                ,assAssetDetailDTO.getEqasslistNo()
                ,assAssetDetailDTO.getEquipId()
                ,assAssetDetailDTO.getEqasslistStatusId()
                ,assAssetDetailDTO.getAssBaseListId()
                ,assAssetDetailDTO.getAssDate()
                ,user.getDeptId()
                ,assAssetDetailDTO.getEmpId()
                ,assAssetDetailDTO.getEqGradeId()
                ,assAssetDetailDTO.getRemark()
                ,assAssetDetailDTO.getPmiActionType()
                ,assAssetDetailDTO.getPlantId()
                ,assAssetDetailDTO.getAssTypeId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    public int insertPoint(AssAssetDetailDTO assAssetDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("INSERT INTO TAEQASSPVAL (     ");
        query.append("    eqasslist_id              ");
        query.append("  , comp_no                   ");
        query.append("  , eqasspval_id              ");
        query.append("  , assbasepoint_id           ");
        query.append("  , ass_point_type            ");
        query.append("  , ass_point                 ");
        query.append("  ) SELECT                    ");
        query.append("  ?                           ");
        query.append(", ?                           ");
        query.append(", SQAEQASSPVAL_ID.NEXTVAL     ");
        query.append(", assbasepoint_id             ");
        query.append(", ass_point_type              ");
        query.append(", ass_point                   ");
        query.append("FROM TAASSBASEPOINT           ");
        query.append("WHERE assbaselist_id = ?      ");
        query.append("    and comp_no =  ?          ");

        Object[] objects = new Object[] {
                  assAssetDetailDTO.getEqasslistId()
                , user.getCompNo()
                , assAssetDetailDTO.getAssBaseListId()
                , user.getCompNo()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public int deleteEvalPointList(AssAssetDetailDTO assAssetDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("DELETE FROM TAEQASSPVAL x 	");
    	query.append("WHERE x.comp_no    = ?		");
    	query.append("AND x.eqasslist_id = ?		");
    	query.append("AND x.assbasepoint_id 		");
    	query.append("		NOT IN (SELECT a.assbasepoint_id 		");
    	query.append("                FROM TAASSBASEPOINT a 		");
    	query.append("               WHERE a.comp_no = ? 			");
    	query.append("                 AND a.assbaselist_id = ?  )	");

    	Object[] objects = new Object[] {
    			user.getCompNo()
    			, assAssetDetailDTO.getEqasslistId()
    			, user.getCompNo()
    			, assAssetDetailDTO.getAssBaseListId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(AssAssetDetailDTO assAssetDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;

        query.append("UPDATE TAEQASSLIST SET                ");
        query.append("  comp_no                 = ?         ");
        query.append("  ,eqasslist_id           = ?         ");
        query.append("  ,eqasslist_no           = ?         ");
        query.append("  ,equip_id               = ?         ");
        query.append("  ,eqasslist_status       = ?         ");
        query.append("  ,assbaselist_id         = ?         ");
        query.append("  ,ass_date               = ?         ");
        query.append("  ,dept_id                = ?         ");
        query.append("  ,emp_id                 = ?         ");
        query.append("  ,eq_grade               = ?         ");
        query.append("  ,REMARK                 = ?         ");
        query.append("  ,pmi_action_type        = ?         ");
        query.append("  ,plant			        = ?         ");
        query.append("  ,eqasslist_type       	= NVL(?,'LT')");
        query.append("WHERE  comp_no            = ?         ");
        query.append("  AND  eqasslist_id       = ?         ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,assAssetDetailDTO.getEqasslistId()
                ,assAssetDetailDTO.getEqasslistNo()
                ,assAssetDetailDTO.getEquipId()
                ,assAssetDetailDTO.getEqasslistStatusId()
                ,assAssetDetailDTO.getAssBaseListId()
                ,assAssetDetailDTO.getAssDate()
                ,user.getDeptId()
                ,assAssetDetailDTO.getEmpId()
                ,assAssetDetailDTO.getEqGradeId()
                ,assAssetDetailDTO.getRemark()
                ,assAssetDetailDTO.getPmiActionType()
                ,assAssetDetailDTO.getPlantId()
                ,assAssetDetailDTO.getAssTypeId()
                ,user.getCompNo()
                ,assAssetDetailDTO.getEqasslistId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public int insertEvalPointList(AssAssetDetailDTO assAssetDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;
    	
    	query.setClear();
    	query.append(" INSERT INTO TAEQASSPVAL (                                    ");
    	query.append("      comp_no                                                 ");
    	query.append("    , eqasspval_id                                             ");
    	query.append("    , eqasslist_id                                            ");
    	query.append("    , assbasepoint_id                                         ");
    	query.append("    , ass_point_type                                          ");
    	query.append("    , ass_point                                               ");
    	query.append(")                                                             ");
    	query.append(" SELECT                                                       ");
    	query.append("    a.comp_no                                                 ");
    	query.append("   ,SQAEQASSPVAL_ID.nextval                                   ");
    	query.append("   ,?                                                         ");
    	query.append("   , a.assbasepoint_id                                        ");
    	query.append("   , a.ass_point_type                                         ");
    	query.append("   , a.ass_point                                              ");
    	query.append(" from TAASSBASEPOINT a left outer join TAEQASSPVAL b          ");
    	query.append("            on a.comp_no = b.comp_no                          ");
    	query.append("                and a.assbasepoint_id = b.assbasepoint_id     ");
    	query.append("                and b.comp_no = ?                             ");
    	query.append("                and b.eqasslist_id = ?                        ");
    	query.append(" where 1=1                                                    ");
    	query.append("     and a.is_use = 'Y'                                       ");
    	query.append("     and b.comp_no is null                                    ");
    	query.append("     and a.assbaselist_id = ?                                 ");
    	
    	Object[] objects = new Object[] {
    			assAssetDetailDTO.getEqasslistId()
    			,user.getCompNo()
    			,assAssetDetailDTO.getEqasslistId()
    			,assAssetDetailDTO.getAssBaseListId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int asscompletedDetail(AssAssetDetailDTO assAssetDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;
        
        query.append("UPDATE TAEQASSLIST SET                                                ");
        query.append("   eqasslist_status    = 'C'                                          ");
        query.append("  , eq_grade = (                                                      ");
        query.append("                 SELECT a.eq_grade                                    ");
        query.append("                 FROM TAASSBASEGRADE a                                ");
        query.append("                 INNER JOIN                                           ");
        query.append("                   (  SELECT SUM(a.eval_value) eval_value             ");
        query.append("                           , MAX(a.comp_no) comp_no                   ");
        query.append("                           , MAX(b.assbaselist_id) assbaselist_id     ");
        query.append("                      FROM TAEQASSPVAL a, TAEQASSLIST b               ");
        query.append("                      WHERE a.comp_no = b.comp_no                     ");
        query.append("                          AND a.eqasslist_id = b.eqasslist_id         ");
        query.append("                          AND a.eqasslist_id = ?                      ");
        query.append("                          AND b.assbaselist_id = ?                    ");
        query.append("                          AND a.comp_no = ?                           ");
        query.append("                    ) b                                               ");
        query.append("                  ON a.comp_no = b.comp_no                            ");
        query.append("                      AND a.assbaselist_id = b.assbaselist_id         ");
        query.append("                  WHERE a.assbaselist_id =  ?                         ");
        query.append("                      AND b.eval_value >= a.grade_from                ");
        query.append("                      AND b.eval_value <= a.grade_to                  ");
        query.append("                      AND ROWNUM = 1)                                 ");
        query.append("     ,PMI_ACTION_TYPE = (SELECT aa.PMI_ACTION_TYPE                    ");
        query.append("                    FROM TAEQASSLIST aa                               ");
        query.append("                    WHERE aa.comp_no = ?                              ");
        query.append("                    AND aa.eqasslist_id = ?)                          ");        
        query.append("WHERE comp_no = ?                                                     ");
        query.append("AND eqasslist_id = ?                                                  ");
        
        Object[] objects = new Object[] {
                assAssetDetailDTO.getEqasslistId()
              , assAssetDetailDTO.getAssBaseListId()
              , user.getCompNo()
              , assAssetDetailDTO.getAssBaseListId()
              , user.getCompNo()
              , assAssetDetailDTO.getEqasslistId()
              , user.getCompNo()
              , assAssetDetailDTO.getEqasslistId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(),objects);
        
        return rtnValue;
    }
    
    public int updateEqGrade(AssAssetDetailDTO assAssetDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;
        
        query.append("UPDATE TAEQUIPMENT a SET a.eq_grade = (SELECT aa.eq_grade                      ");
        query.append("                                       FROM TAEQASSLIST aa                     ");
        query.append("                                       WHERE aa.comp_no = ?                    ");
        query.append("                                       AND aa.eqasslist_id = ?)                ");
        query.append("                        ,a.PMI_ACTION_TYPE = (SELECT aa.PMI_ACTION_TYPE        ");
        query.append("                                       FROM TAEQASSLIST aa                     ");
        query.append("                                       WHERE aa.comp_no = ?                    ");
        query.append("                                       AND aa.eqasslist_id = ?)                ");
        query.append("WHERE a.comp_no = ?                                                            ");
        query.append("AND a.equip_id = ?                                                             ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
              , assAssetDetailDTO.getEqasslistId()
              , user.getCompNo()
              , assAssetDetailDTO.getEqasslistId()
              , user.getCompNo()
              , assAssetDetailDTO.getEquipId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public String checkDetail(AssAssetDetailDTO assAssetDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT CASE                                       ");
        query.append("       WHEN (                                     ");
        query.append("              (SELECT COUNT(*)                    ");
        query.append("               FROM TAEQASSPVAL                   ");
        query.append("               WHERE eqasslist_id = ?      )      ");
        query.append("             =                                    ");
        query.append("               (SELECT COUNT(*)                   ");
        query.append("               FROM TAEQASSPVAL                   ");
        query.append("               WHERE eqasslist_id = ?             ");
        query.append("                   AND ass_eval IS NOT NULL )     ");
        query.append("             )                                    ");
        query.append("        THEN 1                                    ");
        query.append("        ELSE 0                                    ");
        query.append("        END           AS isComplete               ");
        query.append("FROM dual                                         ");
        
        Object[] objects = new Object[] {
                assAssetDetailDTO.getEqasslistId()
              , assAssetDetailDTO.getEqasslistId()
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
    
    
    public String updateGrade(AssAssetDetailDTO assAssetDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT a.eq_grade                                    ");
        query.append("FROM TAASSBASEGRADE a                                ");
        query.append("INNER JOIN                                           ");
        query.append("  (  SELECT SUM(a.eval_value) eval_value             ");
        query.append("          , MAX(a.comp_no) comp_no                   ");
        query.append("          , MAX(b.assbaselist_id) assbaselist_id     ");
        query.append("     FROM TAEQASSPVAL a, TAEQASSLIST b               ");
        query.append("     WHERE a.comp_no = b.comp_no                     ");
        query.append("         AND a.eqasslist_id = b.eqasslist_id         ");
        query.append("         AND a.eqasslist_id = ?                      ");
        query.append("         AND b.assbaselist_id = ?                    ");
        query.append("         AND a.comp_no = ?                           ");
        query.append("   ) b                                               ");
        query.append(" ON a.comp_no = b.comp_no                            ");
        query.append("     AND a.assbaselist_id = b.assbaselist_id         ");
        query.append(" WHERE a.assbaselist_id =  ?                         ");
        query.append("     AND b.eval_value >= a.grade_from                ");
        query.append("     AND b.eval_value <= a.grade_to                  ");
        query.append("     AND ROWNUM = 1                                  ");
        
        Object[] objects = new Object[] {
                assAssetDetailDTO.getEqasslistId()
              , assAssetDetailDTO.getAssBaseListId()
              , user.getCompNo()
              , assAssetDetailDTO.getAssBaseListId()
        };

        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    
    
    public String updateScore(AssAssetDetailDTO assAssetDetailDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                           ");
        query.append("       (SELECT SUM(a.eval_value)                                 ");
        query.append("        FROM TAEQASSPVAL a                                       ");
        query.append("        WHERE a.comp_no = x.comp_no                              ");
        query.append("            AND a.eqasslist_id = X.eqasslist_id                  ");
        query.append("            GROUP BY a.comp_no, a.eqasslist_id ) AS evalValue    ");
        query.append("FROM TAEQASSLIST x JOIN TAEQUIPMENT y                            ");
        query.append("ON x.comp_no = y.comp_no                                         ");
        query.append("AND x.equip_id = y.equip_id                                      ");
        query.append("WHERE  1=1                                                       ");
        query.append("AND    x.comp_no    = ?                                          ");
        query.append("AND    x.eqasslist_id    = ?                                     ");

        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,assAssetDetailDTO.getEqasslistId()
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }


    @Override
    public String checkEquip(AssAssetDetailDTO assAssetDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                    ");
        query.append("       eqctg_type         ");
        query.append("FROM   TAEQUIPMENT        ");
        query.append("WHERE  equip_id   = ?     ");
        query.append("  AND  comp_no    = ?     ");

        
        Object[] objects = new Object[] {
                assAssetDetailDTO.getEquipId()
                ,user.getCompNo()
        };

        List resultList = getJdbcTemplate().queryForList(query.toString(), objects);
        
        return QueryBuffer.listToString(resultList);
    }
    
    public String findPmitypePeriod(AssAssetDetailDTO assAssetDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                    ");
        query.append("     (select a.pmc_type from taeqtool a where a.comp_no = x.comp_no and a.equip_id = x.equip_id) || '+' ||            ");
        query.append("    ( SELECT                              ");
        query.append("         a.CYCLE || '+' || a.period_type  ");
        query.append("        FROM TAASSBASEGRADE a             ");
        query.append("       WHERE a.comp_no = x.comp_no        ");
        query.append("         AND a.assbaselist_id         = ? ");
        query.append("         AND a.eq_grade = x.eq_grade )    ");
        query.append("       || '+' ||                          ");
        query.append("     (SELECT MAX(a.wrkcallist_id) FROM TAWRKCALLIST a WHERE a.comp_no = x.comp_no AND a.is_use = 'Y' )        ");
        query.append("FROM TAEQUIPMENT x                        ");
        query.append("WHERE 1=1                                 ");
        query.append("AND x.comp_no                         = ? ");
        query.append("AND x.equip_id                        = ? ");
        
        Object[] objects = new Object[] {
                assAssetDetailDTO.getAssBaseListId()
              , user.getCompNo()
              , assAssetDetailDTO.getEquipId()
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }


    @Override
    public String isExistPm(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception {

        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                ");
        query.append("    x.pm_id                           ");
        query.append("FROM TAPMLST x INNER JOIN TAPMEQUIP y ");
        query.append("ON x.comp_no = y.comp_no              ");
        query.append("AND x.pm_id = y.pm_id                 ");
        query.append("WHERE x.comp_no  = ?                  ");
        query.append("AND x.wo_type    = ?                  ");
        query.append("AND y.equip_id   = ?                  ");
        query.append("AND x.is_deleted = ?                  ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                , "PMC"
                , assAssetDetailDTO.getEquipId()
                , "N"
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
        
    }


    @Override
    public String getLastVersionEquipId(String equipId, String compNo)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT b.equip_id                             ");
        query.append("FROM   TAEQUIPMENT b                          ");
        query.append("WHERE  1=1                                    ");
        query.append("  AND  b.comp_no = ?                          ");
        query.append("  AND  b.item_no=(SELECT c.item_no            ");
        query.append("                  FROM   TAEQUIPMENT c        ");
        query.append("                  WHERE  1=1                  ");
        query.append("                    AND  c.comp_no = ?        ");
        query.append("                    AND  c.equip_id = ?       ");
        query.append("                 )                            ");
        query.append("  AND  (b.is_last_version = 'Y' OR revision_status ='W')    ");

        Object[] objects = new Object[] {
                compNo,
                compNo,
                equipId
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
    
}

