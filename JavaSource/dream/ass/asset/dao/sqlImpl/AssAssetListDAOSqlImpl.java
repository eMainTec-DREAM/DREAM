package dream.ass.asset.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.ass.asset.dao.AssAssetListDAO;
import dream.ass.asset.dto.AssAssetCommonDTO;

/**
 * AssAsset Page - List DAO implements
 * @author youngjoo38
 * @version $Id: AssAssetListDAOSqlImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assAssetListDAOTarget"
 * @spring.txbn id="assAssetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssAssetListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements AssAssetListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: assAssetListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param assAssetCommonDTO
     * @return List
     */
    public List findList(AssAssetCommonDTO assAssetCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
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
        query.append("     , y.p_equip_id                       AS parEquipId                   ");
        query.append("     , (SELECT b.description FROM TAEQUIPMENT b                           ");
        query.append("        WHERE b.comp_no = y.comp_no                                       ");
        query.append("          AND b.equip_id = y.p_equip_id)  AS parEquipDesc                 ");
        query.append("     , (SELECT a.full_desc                                                ");
        query.append("        FROM TAEQLOC a                                                    ");
        query.append("        WHERE a.eqloc_id = (SELECT b.eqloc_id FROM TAEQUIPMENT b          ");
        query.append("                             WHERE b.comp_no = a.comp_no                  ");
        query.append("                               AND b.equip_id = y.p_equip_id)    )    AS parEqLocDesc     ");
        query.append("      , x.eq_grade                        AS eqGradeId                    ");
        query.append("      , dbo.SFACODE_TO_DESC(x.eq_grade, 'EQ_GRADE', 'SYS', x.comp_no, '"+user.getLangId()+"') AS eqGradeDesc         ");
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
        query.append("      , dbo.SFACODE_TO_DESC(x.eqasslist_status, 'EQASSLIST_STATUS', 'SYS', x.comp_no, '"+user.getLangId()+"') AS eqasslistStatusDesc         ");       
        query.append("      , x.pmi_action_type                 AS PMIACTIONTYPE                "); 
        query.append("      , dbo.SFACODE_TO_DESC(x.EQASSLIST_TYPE, 'EQASSLIST_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"') 	AS assType         			   "); 
        query.append("FROM TAEQASSLIST x JOIN TAEQUIPMENT y                                     ");
        query.append("ON X.COMP_NO = Y.COMP_NO                                                  ");
        query.append("    AND X.EQUIP_ID = Y.EQUIP_ID                                           ");
        query.append("    AND y.is_deleted = 'N'                                                ");
        query.append("WHERE  1=1                                                                ");

        query.append(this.getWhere(assAssetCommonDTO, user));
        query.getOrderByQuery("x.eqasslist_id", "x.eqasslist_no", assAssetCommonDTO.getOrderBy(), assAssetCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assAssetCommonDTO.getIsLoadMaxCount(), assAssetCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param assAssetCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AssAssetCommonDTO assAssetCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        
        if(!"".equals(assAssetCommonDTO.getEqasslistId())){
            query.getAndQuery("x.eqasslist_id", assAssetCommonDTO.getEqasslistId());
            return query.toString();
        }

        query.getAndQuery("y.eqctg_type", assAssetCommonDTO.getEqCtgType());
        
        // 평가일자
        query.getAndDateQuery("x.ass_date", assAssetCommonDTO.getFilterAssStartDate(), assAssetCommonDTO.getFilterAssEndDate());

        // 상태
        query.getSysCdQuery("x.eqasslist_status", assAssetCommonDTO.getFilterEqasslistStatusId(), assAssetCommonDTO.getFilterEqasslistStatusDesc(), "EQASSLIST_STATUS", user.getCompNo(),user.getLangId());
        
        // 설비 (ID/DESC)
        if(!"".equals(assAssetCommonDTO.getFilterEquipDesc()))
        {
            query.append("AND x.equip_id IN (SELECT a.equip_id       ");
            query.append("                      FROM TAEQUIPMENT a   ");
            query.append("                      WHERE 1=1            ");
            query.getLikeQuery("a.description", assAssetCommonDTO.getFilterEquipDesc());
            query.append("                   )                       ");
        }
        else if(!"".equals(assAssetCommonDTO.getFilterEquipDesc()) && !"".equals(assAssetCommonDTO.getFilterEquipId()))
        {
            query.getAndQuery("x.equip_id", assAssetCommonDTO.getFilterEquipId());
        }
        
        // 상위설비 (ID/DESC)
        query.getCodeLikeQuery("y.p_equip_id", "(SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = y.comp_no and a.equip_id = y.p_equip_id)"
                , assAssetCommonDTO.getFilterParEquipId(), assAssetCommonDTO.getFilterParEquipDesc());

        // 설비위치
        query.getEqLocLevelQuery("y.eqloc_id", assAssetCommonDTO.getFilterEqLocId(), assAssetCommonDTO.getFilterEqLocDesc(), user.getCompNo());
        
        // 상위설비위치
        query.getEqLocLevelQuery("(SELECT a.eqloc_id FROM TAEQUIPMENT a WHERE a.comp_no = Y.comp_no AND a.equip_id = Y.p_equip_id)", assAssetCommonDTO.getFilterParEqLocId(), assAssetCommonDTO.getFilterParEqLocDesc(), user.getCompNo());
        
        // 평가등급
        query.getSysCdQuery("x.eq_grade", assAssetCommonDTO.getFilterEqGradeId(), assAssetCommonDTO.getFilterEqGradeDesc(), "EQ_GRADE", user.getCompNo(),user.getLangId());
        
        // 담당자 (ID/DESC)
        if(!"".equals(assAssetCommonDTO.getFilterEmpId()))
        {
            query.getAndQuery("x.emp_id", assAssetCommonDTO.getFilterEmpId());
        }
        else if(!"".equals(assAssetCommonDTO.getFilterEmpName()))
        {
            query.append("AND x.emp_id IN (SELECT a.emp_id           ");
            query.append("                      FROM TAEMP a         ");
            query.append("                      WHERE 1=1            ");
            query.getLikeQuery("a.emp_name", assAssetCommonDTO.getFilterEmpName());
            query.append("                   )                       ");
        }

        // 설비번호
        query.getLikeQuery("y.item_no", assAssetCommonDTO.getFilterEquipNo());
        
        //설비사용부서
        query.getDeptLevelQuery("(SELECT a.usage_dept FROM TAEQUIPMENT a WHERE a.equip_id =  y.p_equip_id)", assAssetCommonDTO.getFilterParUsageDeptId(), assAssetCommonDTO.getFilterParUsageDeptDesc(), user.getCompNo());
        
        //공장코드
        query.getCodeLikeQuery("y.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = y.plant )", 
                assAssetCommonDTO.getFilterPlantId(), assAssetCommonDTO.getFilterPlantDesc());

        // 평가구분
        query.getSysCdQuery("x.eqasslist_type", assAssetCommonDTO.getFilterAssTypeId(), assAssetCommonDTO.getFilterAssTypeDesc(), "EQASSLIST_TYPE", user.getCompNo(),user.getLangId());
        
        return query.toString();
    }
    
    /**
     * Filter 조건
     * @author  nhkim8548
     * @version $Id: assAssetListDAO.java,v 1.0 2018/10/23 10:00:00 nhkim8548 Exp $
     * @since   1.0
     * 
     * @param   assAssetCommonDTO
     * @return
     * @throws  Exception
     */
    public String chkDelList(String id, User user) throws Exception
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
        
        List resultList=  getJdbcTemplate().queryForList(query.toString(), objects);
        return QuerySqlBuffer.listToString(resultList);
    }
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: assAssetListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param assAssetCommonDTO
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

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
            AssAssetCommonDTO assAssetCommonDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAEQASSLIST  x JOIN TAEQUIPMENT y                  ");
        query.append("ON X.COMP_NO = Y.COMP_NO                                  ");
        query.append("  AND X.EQUIP_ID = Y.EQUIP_ID                             ");
        query.append("  AND y.is_deleted = 'N'                                  ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(assAssetCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

    @Override
    public String findDefaultPrctpId(User user) throws Exception 
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                        ");
        query.append("       MAX(a.invtprctp_id)    ");
        query.append("FROM TAINVTPRCTP a            ");
        query.append("WHERE  1=1                    ");
        query.getAndQuery("a.comp_no", user.getCompNo());
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

    @Override
    public String findEquipDesc(String id, User user) throws Exception {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                        ");
        query.append("       a.description+'+'+a.eqloc_id+'+'+a.eqctg_id            ");
        query.append("FROM TAEQUIPMENT a            ");
        query.append("WHERE  1=1                    ");
        query.getAndQuery("a.comp_no", user.getCompNo());
        query.getAndQuery("a.equip_id", id);
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

    }    
}