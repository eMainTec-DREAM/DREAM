package dream.work.pm.list.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.MwareConfig;
import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.MaPmMstrListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;

/**
 * 설비종류 - 목록 dao
 * @author  jung7126
 * @version $Id: MaPmMstrListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maPmMstrListDAOTarget"
 * @spring.txbn id="maPmMstrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmMstrListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPmMstrListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaPmMstrListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @return List
     */
    public List findList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                                                                                                         ");
        query.append("SELECT                                                                                                                                                    ");
        query.append("       ''                                                                                                                         AS seqNo                ");
        query.append("     , ''                                                                                                                         AS isDelCheck           ");
        query.append("     , x.pm_no                                                                                                                    AS pmNo                 ");
        query.append("     , x.description                                                                                                              AS description          ");
        query.append("     , y.equipNo                                                                                                                  AS equipNo              ");
        query.append("     , y.equipDesc                                                                                                                AS equipDesc            ");
        query.append("     , y.equipNo                                                                                                                  AS itemNo               ");
        query.append("     , y.usageDeptDesc                                                                                                            AS usageDeptDesc        ");
        query.append("     , y.pequipNo                                                                                                                 AS pequipNo             ");
        query.append("     , y.pequipDesc                                                                                                               AS pequipDesc           ");
        query.append("     , y.pequipUsaDeptDesc                                                                                                        AS pequipUsaDeptDesc    ");
        query.append("     , dbo.SFAIDTODESC(x.dept_id, '', 'DEPT' , '"+ user.getCompNo() +"')                                                          AS deptDesc             ");
        query.append("     , ( SELECT a.description                                                                                                                             ");
        query.append("           FROM TAWKCTR a                                                                                                                                 ");
        query.append("          WHERE a.comp_no  = x.comp_no                                                                                                                    ");
        query.append("            AND a.wkctr_id = x.wkctr_id )                                                                                         AS wkCtrDesc            ");
        query.append("     , ( SELECT a.emp_name                                                                                                                                ");
        query.append("            FROM TAEMP a                                                                                                                                  ");
        query.append("          WHERE a.comp_no = x.comp_no                                                                                                                     ");
        query.append("               AND a.emp_id = x.emp_id )                                                                                          AS empName              ");
        query.append("     , dbo.SFACODE_TO_DESC(x.pm_type, x.wo_type + '_TYPE', 'SYS', '"+ user.getCompNo() +"','"+ user.getLangId() +"')              AS pmTypeDesc           ");
        query.append("     , dbo.SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+ user.getCompNo() +"','"+ user.getLangId() +"')            AS scheduleType         ");
        query.append("     , CASE WHEN x.cycle IS NULL AND wo_type ='PMC'                                                                                                       ");
        query.append("            THEN ( SELECT a.key_name                                                                                                                      ");
        query.append("                     FROM TALANG a                                                                                                                        ");
        query.append("                    WHERE a.key_no   = 'noCal'                                                                                                            ");
        query.append("                      AND a.key_type = 'LABEL'                                                                                                            ");
        query.append("                      AND lang =  '"+ user.getCompNo() +"')                                                                                               ");
        query.append("            ELSE CONVERT(NVARCHAR,x.CYCLE) + dbo.SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', x.comp_no, '"+ user.getCompNo() +"')                ");
        query.append("             END                                                                                                                  AS cycleDesc            ");
        query.append("     , x.USAGE                                                                                                                                            ");
        query.append("     , CASE WHEN x.is_active != 'Y' THEN x.is_active ELSE isnull(y.is_active,'N') END                                             AS isActive             ");
        query.append("     , x.pm_id                                                                                                                    AS pmId                 ");
        query.append("     , x.pm_type                                                                                                                  AS pmType               ");
        query.append("     , ( SELECT param2                                                                                                                                    ");
        query.append("           FROM TACDSYSD                                                                                                                                  ");
        query.append("          WHERE cdsysd_no = x.pm_type                                                                                                                     ");
        query.append("            AND list_type = x.wo_type +'_TYPE' )                                                                                  AS param                ");
        query.append("     , x.revision_status                             	AS revisionStatusId	");
        query.append("     , dbo.SFACODE_TO_DESC(x.revision_status, 'REVISION_STATUS', 'SYS', '', '"+ user.getLangId() +"')                             AS revisionStatus       ");
        query.append("     , x.is_last_version                                                                                                          AS isLastVersion        ");
        query.append("     , dbo.SFACODE_TO_DESC(x.is_last_version, 'IS_USE', 'SYS', x.comp_no, '"+ user.getLangId() +"')                               AS isLastVersionDesc    ");
        query.append("     , y.eqGradeDesc                                                                                                              AS eqGrade              ");
        query.append("     , y.modelNo                                                                                                                  AS modelNo              ");
        query.append("     , y.maker                                                                                                                    AS maker                ");
        query.append("     , y.eqlocNo                                                                                                                  AS eqlocNo              ");
        query.append("     , y.eqlocDesc                                                                                                                AS eqlocDesc            ");
        query.append("     , y.initWrkDate                                                                                                              AS initWrkDate          ");
        query.append("     , y.lastSchDate                                                                                                              AS lastSchDate          ");
        query.append("		, (SELECT description												");
        query.append("      	FROM TAPLANT													");
        query.append("      	WHERE comp_no = x.comp_no										");
        query.append("      	AND plant = x.plant)                   			plantDesc  		");
        query.append("      , (SELECT description                                               ");
        query.append("        	FROM TAWRKCALLIST                                               ");
        query.append("        	WHERE comp_no = x.comp_no                                       ");
        query.append("          AND WRKCALLIST_ID = x.WRKCALLIST_ID	)           wrkcallistDesc  ");
        query.append("      , x.init_wrk_date                                   initWrkDate     ");
        query.append("      , x.wo_res_bef                                      woResBef        ");
        query.append("      , x.WORK_NUMBER                                     workNumber      ");
        query.append("      , (SELECT dept_no                                                   ");
        query.append("        	FROM TADEPT                                                     ");
        query.append("        	WHERE comp_no = x.comp_no                                       ");
        query.append("          AND dept_id = x.dept_id	)                       deptNo          ");
        query.append("      , (SELECT description                                               ");
        query.append("        	FROM tadept                                                     ");
        query.append("        	WHERE comp_no = x.comp_no                                       ");
        query.append("          AND dept_id = x.dept_id	)                       deptDesc        ");
        query.append("      , (SELECT wkctr_no                                                  ");
        query.append("        	FROM TAWKCTR                                                    ");
        query.append("        	WHERE comp_no = x.comp_no                                       ");
        query.append("          AND WKCTR_ID = x.WKCTR_ID	)                   wrctrNo         ");
        query.append("      , (SELECT description                                               ");
        query.append("        	FROM TAWKCTR                                                    ");
        query.append("        	WHERE comp_no = x.comp_no                                       ");
        query.append("          AND WKCTR_ID = x.WKCTR_ID	)                   wrctrDesc       ");
        query.append("      , (SELECT emp_no                                                    ");
        query.append("        	FROM TAEMP                                                      ");
        query.append("        	WHERE comp_no = x.comp_no                                       ");
        query.append("          AND emp_id = x.emp_id	)                       empNo           ");
        query.append("      , x.remark                                          remark  		");
        query.append("      , x.tolerance 										tolerance		");
        query.append("      , x.revisionhist_id                             	revisionhistId	");
        query.append("  FROM TAPMLST x LEFT OUTER JOIN ( SELECT                                                                                                                 ");
        query.append("                                          CASE a.eqctg_type WHEN 'MD' THEN '('+ a.old_eq_no +')'+ a.item_no ELSE a.item_no END    AS equipNo              ");
        query.append("                                        , a.description                                                                           AS equipDesc            ");
        query.append("                                        , CASE a.eqctg_type WHEN 'MD' THEN '('+ a.old_eq_no +')'+ a.item_no ELSE a.item_no END    AS itemNo               ");
        query.append("                                        , f.description                                                                           AS usageDeptDesc        ");
        query.append("                                        , d.pequipNo                                                                              AS pequipNo             ");
        query.append("                                        , d.pequipId                                                                              AS pequipId             ");
        query.append("                                        , d.pequipDesc                                                                            AS pequipDesc           ");
        query.append("                                        , d.usageDeptId                                                                           AS pequipUsaDeptId      ");
        query.append("                                        , d.pequipUsaDeptDesc                                                                     AS pequipUsaDeptDesc    ");
        query.append("                                        , dbo.SFACODE_TO_DESC(a.eq_grade, 'EQ_GRADE', 'SYS', '', '"+ user.getLangId() +"')        AS eqGradeDesc          ");
        query.append("                                        , a.model_no                                                                              AS modelNo              ");
        query.append("                                        , a.maker                                                                                 AS maker                ");
        query.append("                                        , ( SELECT c.eqloc_no                                                                                             ");
        query.append("                                              FROM TAEQLOC c                                                                                              ");
        query.append("                                             WHERE c.comp_no=a.comp_no                                                                                    ");
        query.append("                                               AND c.eqloc_id       = a.eqloc_id                                                                          ");
        query.append("                                               AND c.eqloc_lvl_type = 'L4' )                                                      AS eqlocNo              ");
        query.append("                                        , ( SELECT c.description                                                                                          ");
        query.append("                                              FROM TAEQLOC c                                                                                              ");
        query.append("                                             WHERE c.comp_no  = a.comp_no                                                                                 ");
        query.append("                                               AND c.eqloc_id = A.eqloc_id )                                                      AS eqlocDesc            ");
        query.append("                                        , a.comp_no                                                                                                       ");
        query.append("                                        , b.pm_id                                                                                                         ");
        query.append("                                        , b.init_wrk_date                                                                         AS initWrkDate          ");
        query.append("                                        , b.last_sch_date                                                                         AS lastSchDate          ");
        query.append("                                        , a.plf_type                                                                                                      ");
        query.append("                                        , a.equip_id                                                                                                      ");
        query.append("                                        , a.eqctg_type                                                                                                    ");
        query.append("                                        , a.main_mng_id                                                                                                   ");
        query.append("                                        , a.sub_mng_id                                                                                                    ");
        query.append("                                        , a.eqloc_id                                                                                                      ");
        query.append("                                        , a.eqctg_id                                                                                                      ");
        query.append("                                        , a.is_law_eq                                                                                                     ");
        query.append("                                        , a.item_no                                                                                                       ");
        query.append("                                        , a.usage_dept                                                                                                    ");
        query.append("                                        , a.eq_grade                                                                                                      ");
        query.append("                                        , a.description                                                                                                   ");
        query.append("                                        , b.is_active                                                                                                     ");
        query.append("                                     FROM TAEQUIPMENT A INNER JOIN TAPMEQUIP b                                                                            ");
        query.append("                                                                ON A.equip_id = b.equip_id                                                                ");
        query.append("                                                               AND A.comp_no  = b.comp_no                                                                 ");
        query.append("                                                   LEFT OUTER JOIN TADEPT f                                                                               ");
        query.append("                                                                ON A.usage_dept = f.dept_id                                                               ");
        query.append("                                                               AND A.comp_no    = f.comp_no                                                               ");
        query.append("                                                   LEFT OUTER JOIN ( SELECT                                                                               ");
        query.append("                                                                            y.description     AS pequipDesc                                               ");
        query.append("                                                                          , y.item_no         AS pequipNo                                                 ");
        query.append("                                                                          , y.equip_id        AS pequipId                                                 ");
        query.append("                                                                          , c.description     AS pequipUsaDeptDesc                                        ");
        query.append("                                                                          , c.dept_id         AS usageDeptId                                              ");
        query.append("                                                                          , x.equip_id                                                                    ");
        query.append("                                                                          , y.comp_no                                                                     ");
        query.append("                                                                       FROM TAEQUIPMENT x JOIN TAEQUIPMENT y                                              ");
        query.append("                                                                                            ON x.p_equip_id = y.equip_id                                  ");
        query.append("                                                                               LEFT OUTER JOIN TADEPT c                                                   ");
        query.append("                                                                                            ON y.usage_dept = c.dept_id                                   ");
        query.append("                                                                                           AND y.comp_no    = c.comp_no                                   ");
        query.append("                                                                   ) d                                                                                    ");
        query.append("                                                                ON d.equip_id = A.equip_id                                                                ");
        query.append("                                                               AND A.comp_no  = d.comp_no                                                                 ");
        query.append("                                    WHERE 1 = 1                                                                                                           ");
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("b.is_deleted", "N");
        query.append("                                 ) y                                                                                                                      ");
        query.append("                              ON x.pm_id   = y.pm_id                                                                                                      ");
        query.append("                             AND x.comp_no = y.comp_no                                                                                                    ");
        query.append(" WHERE 1 = 1                                                                                                                                              ");
        query.append(this.getWhere(maPmMstrCommonDTO, user));
        
        query.getOrderByQuery("x.pm_id", "x.pm_id DESC", maPmMstrCommonDTO.getOrderBy(), maPmMstrCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(maPmMstrCommonDTO.getIsLoadMaxCount(), maPmMstrCommonDTO.getFirstRow()));
    }
    
    public List findWorkList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                                                                                    ");
        query.append("       ''                                                                                         AS seqNo                ");
        query.append("     , ''                                                                                         AS isDelCheck           ");
        query.append("     , x.pm_no                                                                                    AS pmNo                 ");
        query.append("     , x.description                                                                              AS description          ");
        query.append("     , y.equipNo                                                                                  AS equipNo              ");
        query.append("     , y.equipDesc                                                                                AS equipDesc            ");
        query.append("     , dbo.SFAIDTODESC(x.dept_id, '', 'DEPT', '"+ user.getCompNo() +"')                           AS deptDesc             ");
        query.append("     , (SELECT description                                                                                                ");
        query.append("          FROM TAWKCTR                                                                                                    ");
        query.append("         WHERE comp_no  = x.comp_no                                                                                       ");
        query.append("           AND wkctr_id = x.wkctr_id)                                                             AS wkCtrDesc            ");
        query.append("     , (SELECT a.emp_name                                                                                                 ");
        query.append("          FROM TAEMP a                                                                                                    ");
        query.append("         WHERE comp_no  = x.comp_no                                                                                       ");
        query.append("           AND a.emp_id = x.emp_id)                                                               AS empName              ");
        query.append("     , dbo.SFACODE_TO_DESC(x.pm_type, x.wo_type + '_TYPE', 'SYS', '"+ user.getCompNo() +"','"+ user.getLangId() +"')       AS pmTypeDesc   ");
        query.append("     , dbo.SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+ user.getCompNo() +"','"+ user.getLangId() +"')    AS scheduleType ");
        query.append("     , CONVERT(NVARCHAR, x.cycle) + dbo.SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+ user.getCompNo() +"','"+user.getLangId() +"')  AS cycleDesc    ");
        query.append("     , y.lastSchDate                                                                              AS lastSchDate          ");
        query.append("     , x.USAGE                                                                                    AS usage                ");
        query.append("     , CASE WHEN x.is_active != 'Y' THEN x.is_active ELSE isnull(y.is_active,'N') END             AS isActive             ");
        query.append("     , x.pm_id                                                                                    AS pmId                 ");
        query.append("     , x.pm_type                                                                                  AS pmType               ");
        query.append("     , (SELECT param2 FROM TACDSYSD WHERE cdsysd_no=x.pm_type AND list_type= x.wo_type + '_TYPE')  AS param                ");
        query.append("     , dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+ user.getLangId() +"') AS revisionStatus       ");
        query.append("     , x.is_last_version                                                                          AS isLastVersion        ");
        query.append("     , dbo.SFACODE_TO_DESC(x.is_last_version,'IS_USE','SYS',x.comp_no,'"+ user.getLangId() +"')   AS isLastVersionDesc    ");
        query.append("        ,x.tolerance 																				AS tolerance			");
        query.append("  FROM TAPMLST x LEFT OUTER JOIN ( SELECT                                                                                                                 ");
        query.append("                                          CASE a.eqctg_type WHEN 'MD' THEN '('+ a.old_eq_no +')'+ a.item_no ELSE a.item_no END    AS equipNo              ");
        query.append("                                        , a.description                                                                           AS equipDesc            ");
        query.append("                                        , CASE a.eqctg_type WHEN 'MD' THEN '('+ a.old_eq_no +')'+ a.item_no ELSE a.item_no END    AS itemNo               ");
        query.append("                                        , f.description                                                                           AS usageDeptDesc        ");
        query.append("                                        , d.pequipNo                                                                              AS pequipNo             ");
        query.append("                                        , d.pequipId                                                                              AS pequipId             ");
        query.append("                                        , d.pequipDesc                                                                            AS pequipDesc           ");
        query.append("                                        , d.usageDeptId                                                                           AS pequipUsaDeptId      ");
        query.append("                                        , d.pequipUsaDeptDesc                                                                     AS pequipUsaDeptDesc    ");
        query.append("                                        , dbo.SFACODE_TO_DESC(a.eq_grade, 'EQ_GRADE', 'SYS', '', '"+ user.getLangId() +"')        AS eqGradeDesc          ");
        query.append("                                        , a.model_no                                                                              AS modelNo              ");
        query.append("                                        , a.maker                                                                                 AS maker                ");
        query.append("                                        , ( SELECT c.eqloc_no                                                                                             ");
        query.append("                                              FROM TAEQLOC c                                                                                              ");
        query.append("                                             WHERE c.comp_no=a.comp_no                                                                                    ");
        query.append("                                               AND c.eqloc_id       = a.eqloc_id                                                                          ");
        query.append("                                               AND c.eqloc_lvl_type = 'L4' )                                                      AS eqlocNo              ");
        query.append("                                        , ( SELECT c.description                                                                                          ");
        query.append("                                              FROM TAEQLOC c                                                                                              ");
        query.append("                                             WHERE c.comp_no  = a.comp_no                                                                                 ");
        query.append("                                               AND c.eqloc_id = A.eqloc_id )                                                      AS eqlocDesc            ");
        query.append("                                        , a.comp_no                                                                                                       ");
        query.append("                                        , b.pm_id                                                                                                         ");
        query.append("                                        , b.init_wrk_date                                                                         AS initWrkDate          ");
        query.append("                                        , b.last_sch_date                                                                         AS lastSchDate          ");
        query.append("                                        , a.plf_type                                                                                                      ");
        query.append("                                        , a.equip_id                                                                                                      ");
        query.append("                                        , a.eqctg_type                                                                                                    ");
        query.append("                                        , a.main_mng_id                                                                                                   ");
        query.append("                                        , a.sub_mng_id                                                                                                    ");
        query.append("                                        , a.eqloc_id                                                                                                      ");
        query.append("                                        , a.eqctg_id                                                                                                      ");
        query.append("                                        , a.is_law_eq                                                                                                     ");
        query.append("                                        , a.item_no                                                                                                       ");
        query.append("                                        , a.usage_dept                                                                                                    ");
        query.append("                                        , a.eq_grade                                                                                                      ");
        query.append("                                        , a.description                                                                                                   ");
        query.append("                                        , b.is_active                                                                                                     ");
        query.append("                                     FROM TAEQUIPMENT A INNER JOIN TAPMEQUIP b                                                                            ");
        query.append("                                                                ON A.equip_id = b.equip_id                                                                ");
        query.append("                                                               AND A.comp_no  = b.comp_no                                                                 ");
        query.append("                                                   LEFT OUTER JOIN TADEPT f                                                                               ");
        query.append("                                                                ON A.usage_dept = f.dept_id                                                               ");
        query.append("                                                               AND A.comp_no    = f.comp_no                                                               ");
        query.append("                                                   LEFT OUTER JOIN ( SELECT                                                                               ");
        query.append("                                                                            y.description     AS pequipDesc                                               ");
        query.append("                                                                          , y.item_no         AS pequipNo                                                 ");
        query.append("                                                                          , y.equip_id        AS pequipId                                                 ");
        query.append("                                                                          , c.description     AS pequipUsaDeptDesc                                        ");
        query.append("                                                                          , c.dept_id         AS usageDeptId                                              ");
        query.append("                                                                          , x.equip_id                                                                    ");
        query.append("                                                                          , y.comp_no                                                                     ");
        query.append("                                                                       FROM TAEQUIPMENT x JOIN TAEQUIPMENT y                                              ");
        query.append("                                                                                            ON x.p_equip_id = y.equip_id                                  ");
        query.append("                                                                               LEFT OUTER JOIN TADEPT c                                                   ");
        query.append("                                                                                            ON y.usage_dept = c.dept_id                                   ");
        query.append("                                                                                           AND y.comp_no    = c.comp_no                                   ");
        query.append("                                                                   ) d                                                                                    ");
        query.append("                                                                ON d.equip_id = A.equip_id                                                                ");
        query.append("                                                               AND A.comp_no  = d.comp_no                                                                 ");
        query.append("                                    WHERE 1 = 1                                                                                                           ");
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("b.is_deleted", "N");
        query.append("                                 ) y                                                                                                                      ");
        query.append("                              ON x.pm_id   = y.pm_id                                                                                                      ");
        query.append("                             AND x.comp_no = y.comp_no                                                                                                    ");
        query.append(" WHERE 1 = 1                                                                                                                                              ");
        query.append(this.getWhere(maPmMstrCommonDTO, user));
        
        
        query.getOrderByQuery("x.pm_id", "x.pm_id DESC", maPmMstrCommonDTO.getOrderBy(), maPmMstrCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(maPmMstrCommonDTO.getIsLoadMaxCount(), maPmMstrCommonDTO.getFirstRow()));
    }
    public List findInsList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                                                                                                                     ");
        query.append("SELECT                                                                                                                                                                ");
        query.append("          ''                                                                                                                                      AS seqNo            ");
        query.append("        , ''                                                                                                                                      AS isDelCheck       ");
        query.append("        , x.pm_no                                                                                                                                 AS pmNo             ");
        query.append("        , x.description                                                                                                                           AS description      ");
        query.append("        , y.equipNo                                                                                                                               AS equipNo          ");
        query.append("        , y.equipDesc                                                                                                                             AS equipDesc        ");
        query.append("        , dbo.SFAIDTODESC(x.dept_id, '', 'DEPT' , '"+ user.getCompNo() +"')                                                                       AS deptDesc         ");
        query.append("        , (SELECT description FROM TAWKCTR WHERE comp_no=x.comp_no AND wkctr_id=x.wkctr_id)                                                       AS wkCtrDesc        ");
        query.append("        , (SELECT a.emp_name FROM TAEMP a WHERE comp_no=x.comp_no AND a.emp_id=x.emp_id)                                                          AS empName          ");
        query.append("        , dbo.SFACODE_TO_DESC(x.pm_type, x.wo_type + '_TYPE', 'SYS', '"+ user.getCompNo() +"','"+user.getLangId()+"')                             AS pmTypeDesc       ");
        query.append("        , dbo.SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+ user.getCompNo() +"','"+user.getLangId()+"')                           AS scheduleType     ");
        query.append("        , CONVERT(NVARCHAR, x.cycle) + dbo.SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+ user.getCompNo() +"','"+user.getLangId() +"') AS cycleDesc        ");
        query.append("        , x.USAGE                                                                                                                                 AS usage            ");
        query.append("        , y.lastSchDate                                                                                                                           AS lastSchDate      ");
        query.append("        , CASE WHEN x.is_active != 'Y' THEN x.is_active ELSE isnull(y.is_active,'N') END                                                          AS isActive         ");
        query.append("        , x.pm_id                                                                                                                                 AS pmId             ");
        query.append("        , x.pm_type                                                                                                                               AS pmType           ");
        query.append("        , (SELECT param2 FROM TACDSYSD WHERE cdsysd_no=x.pm_type AND list_type= x.wo_type + '_TYPE')                                              AS param            ");
        query.append("        , dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+user.getLangId()+"')                                                AS revisionStatus   ");
        query.append("        , x.is_last_version                                                                                                                       AS isLastVersion    ");
        query.append("        , dbo.SFACODE_TO_DESC(x.is_last_version,'IS_USE','SYS',x.comp_no,'"+user.getLangId()+"')                                                  AS isLastVersionDesc");
        query.append("  FROM TAPMLST x LEFT OUTER JOIN (SELECT                                                                                                              ");
        query.append("                                         CASE a.eqctg_type WHEN 'MD' THEN '('+ a.old_eq_no +')'+ a.item_no ELSE a.item_no END AS equipNo              ");
        query.append("                                       , a.description                                                                        AS equipDesc            ");
        query.append("                                       , CASE a.eqctg_type WHEN 'MD' THEN '('+ a.old_eq_no +')'+ a.item_no ELSE a.item_no END AS itemNo               ");
        query.append("                                       , f.description                                                                        AS usageDeptDesc        ");
        query.append("                                       , d.pequipNo                                                                           AS pequipNo             ");
        query.append("                                       , d.pequipDesc                                                                         AS pequipDesc           ");
        query.append("                                       , d.pequipUsaDeptDesc                                                                  AS pequipUsaDeptDesc    ");
        query.append("                                       , SFACODE_TO_DESC(a.eq_grade,'EQ_GRADE','SYS','','"+ user.getLangId() +"')             AS eqGradeDesc          ");
        query.append("                                       , a.model_no                                                                           AS modelNo              ");
        query.append("                                       , a.maker                                                                              AS maker                ");
        query.append("                                       , (SELECT c.eqloc_no                                                                                           ");
        query.append("                                            FROM TAEQLOC c                                                                                            ");
        query.append("                                           WHERE c.comp_no  = a.comp_no                                                                               ");
        query.append("                                             AND c.eqloc_id = a.eqloc_id                                                                              ");
        query.append("                                             AND c.eqloc_lvl_type='L4')                                                       AS eqlocNo              ");
        query.append("                                       , (SELECT c.description                                                                                        ");
        query.append("                                            FROM TAEQLOC c                                                                                            ");
        query.append("                                           WHERE c.comp_no  = a.comp_no                                                                               ");
        query.append("                                             AND c.eqloc_id = A.eqloc_id)                                                     AS eqlocDesc            ");
        query.append("                                       , a.comp_no                                                                                                    ");
        query.append("                                       , b.pm_id                                                                                                      ");
        query.append("                                       , b.init_wrk_date                                                                      AS initWrkDate          ");
        query.append("                                       , b.last_sch_date                                                                      AS lastSchDate          ");
        query.append("                                       , a.plf_type                                                                                                   ");
        query.append("                                       , a.equip_id                                                                                                   ");
        query.append("                                       , a.eqctg_type                                                                                                 ");
        query.append("                                       , a.main_mng_id                                                                                                ");
        query.append("                                       , a.sub_mng_id                                                                                                 ");
        query.append("                                       , a.eqloc_id                                                                                                   ");
        query.append("                                       , a.eqctg_id                                                                                                   ");
        query.append("                                       , a.is_law_eq                                                                                                  ");
        query.append("                                       , a.item_no                                                                                                    ");
        query.append("                                       , a.usage_dept                                                                                                 ");
        query.append("                                       , a.eq_grade                                                                                                   ");
        query.append("                                       , a.description                                                                                                ");
        query.append("                                       , b.is_active                                                                                                  ");
        query.append("                                    FROM TAEQUIPMENT A INNER JOIN TAPMEQUIP b                                                                         ");
        query.append("                                                               ON A.equip_id = b.equip_id                                                             ");
        query.append("                                                              AND A.comp_no = b.comp_no                                                               ");
        query.append("                                                  LEFT OUTER JOIN TADEPT f                                                                            ");
        query.append("                                                               ON A.usage_dept = f.dept_id                                                            ");
        query.append("                                                              AND A.comp_no = f.comp_no                                                               ");
        query.append("                                                   LEFT OUTER JOIN ( SELECT                                                                           ");
        query.append("                                                                            y.description     AS pequipDesc                                           ");
        query.append("                                                                          , y.item_no         AS pequipNo                                             ");
        query.append("                                                                          , y.equip_id        AS pequipId                                             ");
        query.append("                                                                          , c.description     AS pequipUsaDeptDesc                                    ");
        query.append("                                                                          , c.dept_id         AS usageDeptId                                          ");
        query.append("                                                                          , x.equip_id                                                                ");
        query.append("                                                                          , y.comp_no                                                                 ");
        query.append("                                                                       FROM TAEQUIPMENT x JOIN TAEQUIPMENT y                                          ");
        query.append("                                                                                            ON x.p_equip_id = y.equip_id                              ");
        query.append("                                                                               LEFT OUTER JOIN TADEPT c                                               ");
        query.append("                                                                                            ON y.usage_dept = c.dept_id                               ");
        query.append("                                                                                           AND y.comp_no    = c.comp_no                               ");
        query.append("                                                                   ) d                                                                                ");
        query.append("                                                               ON d.equip_id = A.equip_id                                                             ");
        query.append("                                                              AND A.comp_no  = d.comp_no                                                              ");
        query.append("                                   WHERE 1 = 1                                                                                                        ");
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("b.is_deleted", "N");
        query.append("                                 ) y                                                                                                                  ");
        query.append("                              ON x.pm_id = y.pm_id                                                                                                    ");
        query.append("                             AND x.comp_no = y.comp_no                                                                                                ");
        query.append(" WHERE 1 = 1                                                                                                                                          ");
        query.append(this.getWhere(maPmMstrCommonDTO,user));
        
        query.getOrderByQuery("x.pm_id", "x.pm_id DESC", maPmMstrCommonDTO.getOrderBy(), maPmMstrCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(maPmMstrCommonDTO.getIsLoadMaxCount(), maPmMstrCommonDTO.getFirstRow()));
    }
    public List findTrList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                                                                                         ");
        query.append("SELECT                                                                                                                                    ");
        query.append("       ''                                                                                                 AS seqNo                        ");
        query.append("     , ''                                                                                                 AS isDelCheck                   ");
        query.append("     , x.pm_no                                                                                            AS pmNo                         ");
        query.append("     , x.description                                                                                      AS description                  ");
        query.append("     , y.equipNo                                                                                          AS equipNo                      ");
        query.append("     , y.equipDesc                                                                                        AS equipDesc                    ");
        query.append("     , dbo.SFAIDTODESC(x.dept_id, '', 'DEPT' , '"+ user.getCompNo() +"')                                  AS deptDesc                     ");
        query.append("     , (SELECT description FROM TAWKCTR WHERE comp_no = x.comp_no AND wkctr_id = x.wkctr_id)              AS wkCtrDesc                    ");
        query.append("     , (SELECT a.emp_name FROM TAEMP a WHERE comp_no = x.comp_no AND a.emp_id = x.emp_id)                 AS empName                      ");
        query.append("     , dbo.SFACODE_TO_DESC(x.pm_type, x.wo_type + '_TYPE', 'SYS', '"+ user.getCompNo() +"','"+user.getLangId()+"')       AS pmTypeDesc     ");
        query.append("     , dbo.SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+ user.getCompNo() +"','"+user.getLangId()+"')    AS scheduleType   ");
        query.append("     , CONVERT(NVARCHAR, x.cycle) + dbo.SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+ user.getCompNo() +"','"+user.getLangId() +"')  AS cycleDesc    ");
        query.append("     , x.USAGE                                                                                            AS usage                        ");
        query.append("     , y.lastSchDate                                                                                      AS lastSchDate                  ");
        query.append("     , CASE WHEN x.is_active != 'Y' THEN x.is_active ELSE isnull(y.is_active,'N') END                     AS isActive                     ");
        query.append("     , x.pm_id                                                                                            AS pmId                         ");
        query.append("     , x.pm_type                                                                                          AS pmType                       ");
        query.append("     , (SELECT param2 FROM TACDSYSD WHERE cdsysd_no=x.pm_type AND list_type= x.wo_type + '_TYPE')         AS param                        ");
        query.append("     , dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+user.getLangId()+"')           AS revisionStatus               ");
        query.append("     , x.is_last_version                                                                                  AS isLastVersion                ");
        query.append("     , dbo.SFACODE_TO_DESC(x.is_last_version,'IS_USE','SYS',x.comp_no,'"+user.getLangId()+"')             AS isLastVersionDesc            ");
        query.append("  FROM TAPMLST x LEFT OUTER JOIN ( SELECT                                                                                                                 ");
        query.append("                                          CASE a.eqctg_type WHEN 'MD' THEN '('+ a.old_eq_no +')'+ a.item_no ELSE a.item_no END    AS equipNo              ");
        query.append("                                        , a.description                                                                           AS equipDesc            ");
        query.append("                                        , CASE a.eqctg_type WHEN 'MD' THEN '('+ a.old_eq_no +')'+ a.item_no ELSE a.item_no END    AS itemNo               ");
        query.append("                                        , f.description                                                                           AS usageDeptDesc        ");
        query.append("                                        , d.pequipNo                                                                              AS pequipNo             ");
        query.append("                                        , d.pequipId                                                                              AS pequipId             ");
        query.append("                                        , d.pequipDesc                                                                            AS pequipDesc           ");
        query.append("                                        , d.usageDeptId                                                                           AS pequipUsaDeptId      ");
        query.append("                                        , d.pequipUsaDeptDesc                                                                     AS pequipUsaDeptDesc    ");
        query.append("                                        , dbo.SFACODE_TO_DESC(a.eq_grade, 'EQ_GRADE', 'SYS', '', '"+ user.getLangId() +"')        AS eqGradeDesc          ");
        query.append("                                        , a.model_no                                                                              AS modelNo              ");
        query.append("                                        , a.maker                                                                                 AS maker                ");
        query.append("                                        , ( SELECT c.eqloc_no                                                                                             ");
        query.append("                                              FROM TAEQLOC c                                                                                              ");
        query.append("                                             WHERE c.comp_no=a.comp_no                                                                                    ");
        query.append("                                               AND c.eqloc_id       = a.eqloc_id                                                                          ");
        query.append("                                               AND c.eqloc_lvl_type = 'L4' )                                                      AS eqlocNo              ");
        query.append("                                        , ( SELECT c.description                                                                                          ");
        query.append("                                              FROM TAEQLOC c                                                                                              ");
        query.append("                                             WHERE c.comp_no  = a.comp_no                                                                                 ");
        query.append("                                               AND c.eqloc_id = A.eqloc_id )                                                      AS eqlocDesc            ");
        query.append("                                        , a.comp_no                                                                                                       ");
        query.append("                                        , b.pm_id                                                                                                         ");
        query.append("                                        , b.init_wrk_date                                                                         AS initWrkDate          ");
        query.append("                                        , b.last_sch_date                                                                         AS lastSchDate          ");
        query.append("                                        , a.plf_type                                                                                                      ");
        query.append("                                        , a.equip_id                                                                                                      ");
        query.append("                                        , a.eqctg_type                                                                                                    ");
        query.append("                                        , a.main_mng_id                                                                                                   ");
        query.append("                                        , a.sub_mng_id                                                                                                    ");
        query.append("                                        , a.eqloc_id                                                                                                      ");
        query.append("                                        , a.eqctg_id                                                                                                      ");
        query.append("                                        , a.is_law_eq                                                                                                     ");
        query.append("                                        , a.item_no                                                                                                       ");
        query.append("                                        , a.usage_dept                                                                                                    ");
        query.append("                                        , a.eq_grade                                                                                                      ");
        query.append("                                        , a.description                                                                                                   ");
        query.append("                                        , b.is_active                                                                                                     ");
        query.append("                                     FROM TAEQUIPMENT A INNER JOIN TAPMEQUIP b                                                                            ");
        query.append("                                                                ON A.equip_id = b.equip_id                                                                ");
        query.append("                                                               AND A.comp_no  = b.comp_no                                                                 ");
        query.append("                                                   LEFT OUTER JOIN TADEPT f                                                                               ");
        query.append("                                                                ON A.usage_dept = f.dept_id                                                               ");
        query.append("                                                               AND A.comp_no    = f.comp_no                                                               ");
        query.append("                                                   LEFT OUTER JOIN ( SELECT                                                                               ");
        query.append("                                                                            y.description     AS pequipDesc                                               ");
        query.append("                                                                          , y.item_no         AS pequipNo                                                 ");
        query.append("                                                                          , y.equip_id        AS pequipId                                                 ");
        query.append("                                                                          , c.description     AS pequipUsaDeptDesc                                        ");
        query.append("                                                                          , c.dept_id         AS usageDeptId                                              ");
        query.append("                                                                          , x.equip_id                                                                    ");
        query.append("                                                                          , y.comp_no                                                                     ");
        query.append("                                                                       FROM TAEQUIPMENT x JOIN TAEQUIPMENT y                                              ");
        query.append("                                                                                            ON x.p_equip_id = y.equip_id                                  ");
        query.append("                                                                               LEFT OUTER JOIN TADEPT c                                                   ");
        query.append("                                                                                            ON y.usage_dept = c.dept_id                                   ");
        query.append("                                                                                           AND y.comp_no    = c.comp_no                                   ");
        query.append("                                                                   ) d                                                                                    ");
        query.append("                                                                ON d.equip_id = A.equip_id                                                                ");
        query.append("                                                               AND A.comp_no  = d.comp_no                                                                 ");
        query.append("                                    WHERE 1 = 1                                                                                                           ");
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("b.is_deleted", "N");
        query.append("                                 ) y                                                                                                                      ");
        query.append("                              ON x.pm_id   = y.pm_id                                                                                                      ");
        query.append("                             AND x.comp_no = y.comp_no                                                                                                    ");
        query.append(" WHERE 1 = 1                                                                                                                                              ");
        query.append(this.getWhere(maPmMstrCommonDTO, user));
        
        query.getOrderByQuery("x.pm_id", "x.pm_id DESC", maPmMstrCommonDTO.getOrderBy(), maPmMstrCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(maPmMstrCommonDTO.getIsLoadMaxCount(), maPmMstrCommonDTO.getFirstRow()));
    }
    public List findCalList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maPmMstrCommonDTO.getCompNo();

        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                                                                                     ");
        query.append("SELECT                                                                                                                                ");
        query.append("       ''                                                                                                     AS seqNo                ");
        query.append("     , ''                                                                                                     AS isDelCheck           ");
        query.append("     , x.pm_no                                                                                                AS pmNo                 ");
        query.append("     , x.description                                                                                          AS description          ");
        query.append("     , y.equipNo                                                                                              AS equipNo              ");
        query.append("     , y.equipDesc                                                                                            AS equipDesc            ");
        query.append("     , dbo.SFAIDTODESC(x.dept_id, '', 'DEPT' , '"+compNo+"')                                                  AS deptDesc             ");
        query.append("     , (SELECT description FROM TAWKCTR WHERE comp_no=x.comp_no AND wkctr_id=x.wkctr_id)                      AS wkCtrDesc            ");
        query.append("     , (SELECT a.emp_name FROM TAEMP a WHERE comp_no=x.comp_no AND a.emp_id=x.emp_id)                         AS empName              ");
        query.append("     , dbo.SFACODE_TO_DESC(x.pm_type, x.wo_type + '_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"')        AS pmTypeDesc           ");
        query.append("     , dbo.SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+compNo+"','"+user.getLangId()+"')      AS scheduleType         ");
        query.append("     , CONVERT(NVARCHAR, x.cycle) + dbo.SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+ user.getCompNo() +"','"+user.getLangId() +"')  AS cycleDesc    ");
        query.append("     , x.USAGE                                                                                                AS usage                ");
        query.append("     , y.lastSchDate                                                                                          AS lastSchDate          ");
        query.append("     , CASE WHEN x.is_active != 'Y' THEN x.is_active ELSE isnull(y.is_active,'N') END                         AS isActive             ");
        query.append("     , x.pm_id                                                                                                AS pmId                 ");
        query.append("     , x.pm_type                                                                                              AS pmType               ");
        query.append("     , (SELECT param2 FROM TACDSYSD WHERE cdsysd_no=x.pm_type AND list_type= x.wo_type + '_TYPE')             AS param                ");
        query.append("     , dbo.SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+user.getLangId()+"')               AS revisionStatus       ");
        query.append("     , x.is_last_version                                                                                      AS isLastVersion        ");
        query.append("     , dbo.SFACODE_TO_DESC(x.is_last_version,'IS_USE','SYS',x.comp_no,'"+user.getLangId()+"')                 AS isLastVersionDesc    ");
        query.append("  FROM TAPMLST x LEFT OUTER JOIN ( SELECT                                                                                                                 ");
        query.append("                                          CASE a.eqctg_type WHEN 'MD' THEN '('+ a.old_eq_no +')'+ a.item_no ELSE a.item_no END    AS equipNo              ");
        query.append("                                        , a.description                                                                           AS equipDesc            ");
        query.append("                                        , CASE a.eqctg_type WHEN 'MD' THEN '('+ a.old_eq_no +')'+ a.item_no ELSE a.item_no END    AS itemNo               ");
        query.append("                                        , f.description                                                                           AS usageDeptDesc        ");
        query.append("                                        , d.pequipNo                                                                              AS pequipNo             ");
        query.append("                                        , d.pequipId                                                                              AS pequipId             ");
        query.append("                                        , d.pequipDesc                                                                            AS pequipDesc           ");
        query.append("                                        , d.usageDeptId                                                                           AS pequipUsaDeptId      ");
        query.append("                                        , d.pequipUsaDeptDesc                                                                     AS pequipUsaDeptDesc    ");
        query.append("                                        , dbo.SFACODE_TO_DESC(a.eq_grade, 'EQ_GRADE', 'SYS', '', '"+ user.getLangId() +"')        AS eqGradeDesc          ");
        query.append("                                        , a.model_no                                                                              AS modelNo              ");
        query.append("                                        , a.maker                                                                                 AS maker                ");
        query.append("                                        , ( SELECT c.eqloc_no                                                                                             ");
        query.append("                                              FROM TAEQLOC c                                                                                              ");
        query.append("                                             WHERE c.comp_no=a.comp_no                                                                                    ");
        query.append("                                               AND c.eqloc_id       = a.eqloc_id                                                                          ");
        query.append("                                               AND c.eqloc_lvl_type = 'L4' )                                                      AS eqlocNo              ");
        query.append("                                        , ( SELECT c.description                                                                                          ");
        query.append("                                              FROM TAEQLOC c                                                                                              ");
        query.append("                                             WHERE c.comp_no  = a.comp_no                                                                                 ");
        query.append("                                               AND c.eqloc_id = A.eqloc_id )                                                      AS eqlocDesc            ");
        query.append("                                        , a.comp_no                                                                                                       ");
        query.append("                                        , b.pm_id                                                                                                         ");
        query.append("                                        , b.init_wrk_date                                                                         AS initWrkDate          ");
        query.append("                                        , b.last_sch_date                                                                         AS lastSchDate          ");
        query.append("                                        , a.plf_type                                                                                                      ");
        query.append("                                        , a.equip_id                                                                                                      ");
        query.append("                                        , a.eqctg_type                                                                                                    ");
        query.append("                                        , a.main_mng_id                                                                                                   ");
        query.append("                                        , a.sub_mng_id                                                                                                    ");
        query.append("                                        , a.eqloc_id                                                                                                      ");
        query.append("                                        , a.eqctg_id                                                                                                      ");
        query.append("                                        , a.is_law_eq                                                                                                     ");
        query.append("                                        , a.item_no                                                                                                       ");
        query.append("                                        , a.usage_dept                                                                                                    ");
        query.append("                                        , a.eq_grade                                                                                                      ");
        query.append("                                        , a.description                                                                                                   ");
        query.append("                                        , b.is_active                                                                                                     ");
        query.append("                                     FROM TAEQUIPMENT A INNER JOIN TAPMEQUIP b                                                                            ");
        query.append("                                                                ON A.equip_id = b.equip_id                                                                ");
        query.append("                                                               AND A.comp_no  = b.comp_no                                                                 ");
        query.append("                                                   LEFT OUTER JOIN TADEPT f                                                                               ");
        query.append("                                                                ON A.usage_dept = f.dept_id                                                               ");
        query.append("                                                               AND A.comp_no    = f.comp_no                                                               ");
        query.append("                                                   LEFT OUTER JOIN ( SELECT                                                                               ");
        query.append("                                                                            y.description     AS pequipDesc                                               ");
        query.append("                                                                          , y.item_no         AS pequipNo                                                 ");
        query.append("                                                                          , y.equip_id        AS pequipId                                                 ");
        query.append("                                                                          , c.description     AS pequipUsaDeptDesc                                        ");
        query.append("                                                                          , c.dept_id         AS usageDeptId                                              ");
        query.append("                                                                          , x.equip_id                                                                    ");
        query.append("                                                                          , y.comp_no                                                                     ");
        query.append("                                                                       FROM TAEQUIPMENT x JOIN TAEQUIPMENT y                                              ");
        query.append("                                                                                            ON x.p_equip_id = y.equip_id                                  ");
        query.append("                                                                               LEFT OUTER JOIN TADEPT c                                                   ");
        query.append("                                                                                            ON y.usage_dept = c.dept_id                                   ");
        query.append("                                                                                           AND y.comp_no    = c.comp_no                                   ");
        query.append("                                                                   ) d                                                                                    ");
        query.append("                                                                ON d.equip_id = A.equip_id                                                                ");
        query.append("                                                               AND A.comp_no  = d.comp_no                                                                 ");
        query.append("                                    WHERE 1 = 1                                                                                                           ");
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("b.is_deleted", "N");
        query.append("                                 ) y                                                                                                                      ");
        query.append("                              ON x.pm_id   = y.pm_id                                                                                                      ");
        query.append("                             AND x.comp_no = y.comp_no                                                                                                    ");
        query.append(" WHERE 1 = 1                                                                                                                                              ");
        query.append(this.getWhere(maPmMstrCommonDTO, user));
        
        query.getOrderByQuery("x.pm_id", "x.pm_id DESC", maPmMstrCommonDTO.getOrderBy(), maPmMstrCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(maPmMstrCommonDTO.getIsLoadMaxCount(), maPmMstrCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaPmMstrListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] updateListDeleteTag(final List<MaPmMstrDetailDTO> list, final User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	final String deleteTime = DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss");

        query.append("UPDATE TAPMLST  SET                   ");
        query.append("         IS_DELETED = 'Y'             ");
        query.append("        ,IS_ACTIVE = 'N'              ");
        query.append("        ,DELETE_BY  = ?               ");
        query.append("        ,DELETE_TIME = ?              ");
        query.append("WHERE  pm_id   = ?                    ");
        query.append("  AND  comp_no     = ?                ");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {

            @Override
            public int getBatchSize()
            {
                return list.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                MaPmMstrDetailDTO maPmMstrDetailDTO = list.get(i);

                Object[] objects = getObject(new Object[] {  
                        user.getEmpId()
                        ,deleteTime
                        ,maPmMstrDetailDTO.getPmId()
                        ,user.getCompNo()
                });
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }
    
    /**
     * Filter 조건
     * @author  jung7126
     * @version $Id: MaPmMstrListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = user.getCompNo();
        String usageDept   = maPmMstrCommonDTO.getFilterUsageDeptId();
        String usageDeptDesc   = maPmMstrCommonDTO.getFilterUsageDeptDesc();
        
        query.getStringEqualQuery("x.comp_no", compNo);
        query.getStringEqualQuery("x.IS_DELETED", "N");
        if (!"".equals(maPmMstrCommonDTO.getPmId()))
        {
            query.getAndQuery("x.pm_id", maPmMstrCommonDTO.getPmId());
            return query.toString();
        }
        query.getLikeQuery("x.pm_no", maPmMstrCommonDTO.getPmNo());
        query.getAndQuery("x.cycle", maPmMstrCommonDTO.getCycle());
        query.getAndQuery("x.period_type", maPmMstrCommonDTO.getPeriodType());
        query.getLikeQuery("x.description", maPmMstrCommonDTO.getPmDesc());
        query.getDeptLevelQuery("x.dept_id", maPmMstrCommonDTO.getDeptId(), maPmMstrCommonDTO.getDeptDesc(), compNo);
        //작업종류
    	query.getSysCdQuery("x.wo_type", maPmMstrCommonDTO.getWoType(), maPmMstrCommonDTO.getWoTypeDesc(), "WO_TYPE", compNo,user.getLangId());
    	//작업형태
    	query.getSysCdQuery("x.pm_type", maPmMstrCommonDTO.getPmType(), maPmMstrCommonDTO.getPmTypeDesc(), "x.wo_type+'_TYPE'", compNo,user.getLangId());
    	//일정생성종류
        query.getSysCdQuery("x.schedule_type", maPmMstrCommonDTO.getScheduleType(), maPmMstrCommonDTO.getScheduleTypeDesc(), "SCHEDULE_TYPE", compNo,user.getLangId());
       
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.emp_id)", maPmMstrCommonDTO.getEmpId(), maPmMstrCommonDTO.getEmpName());
        
        query.getSysCdQuery("y.plf_type", maPmMstrCommonDTO.getPlfType(), maPmMstrCommonDTO.getPlfTypeDesc(), "PLF_TYPE", compNo,user.getLangId());
        query.getSysCdQuery("y.eqctg_type", maPmMstrCommonDTO.getEqCtgTypeId(), maPmMstrCommonDTO.getEqCtgTypeDesc(), "EQCTG_TYPE", compNo,user.getLangId());
        query.getCodeLikeQuery("y.equip_id", "y.description+y.item_no",   maPmMstrCommonDTO.getEquipId(), maPmMstrCommonDTO.getEquipDesc());
        query.getCodeLikeQuery("y.main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = y.main_mng_id AND a.comp_no='"+compNo+"')", 
                maPmMstrCommonDTO.getMainMngId(), maPmMstrCommonDTO.getMainMngName());
        query.getCodeLikeQuery("y.sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = y.sub_mng_id AND a.comp_no='"+compNo+"')", 
                maPmMstrCommonDTO.getSubMngId(), maPmMstrCommonDTO.getSubMngName());
        query.getEqLocLevelQuery("y.eqloc_id", maPmMstrCommonDTO.getEqLocId(), maPmMstrCommonDTO.getEqLocDesc(), compNo);
        query.getEqCtgLevelQuery("y.eqctg_id", maPmMstrCommonDTO.getEqCtgId(), maPmMstrCommonDTO.getEqCtgDesc(), compNo);
        query.getLikeQuery("y.is_law_eq", maPmMstrCommonDTO.getIsLawEq());
        query.getLikeQuery("y.item_no", maPmMstrCommonDTO.getFilterEquipNo());
        query.getDeptLevelQuery("y.usage_dept", maPmMstrCommonDTO.getFilterEqUsaDeptId(), maPmMstrCommonDTO.getFilterEqUsaDeptDesc(), compNo);
        query.getSysCdQuery("y.eq_grade", maPmMstrCommonDTO.getFilterEqGrade(), maPmMstrCommonDTO.getFilterEqGradeDesc(), "EQ_GRADE", compNo,user.getLangId());
        query.getLikeQuery("y.maker", maPmMstrCommonDTO.getFilterMaker());
        query.getLikeQuery("y.model", maPmMstrCommonDTO.getFilterModel());
        query.getCodeLikeQuery("y.pequipId", "y.pequipDesc", maPmMstrCommonDTO.getFilterPEquipId(), maPmMstrCommonDTO.getFilterPEquipDesc());
        query.getLikeQuery("y.pequipNo", maPmMstrCommonDTO.getFilterPEquipNo());
        query.getDeptLevelQuery("y.pequipUsaDeptId", maPmMstrCommonDTO.getFilterPEqUsaDeptId(), maPmMstrCommonDTO.getFilterPEqUsaDeptDesc(), compNo);
                
        query.getWkCtrLevelQuery("x.wkctr_id", maPmMstrCommonDTO.getWkCtrId(), maPmMstrCommonDTO.getWkCtrDesc(), compNo);
        
        query.getAndQuery("x.revision_status", maPmMstrCommonDTO.getRevisionStatus());
        query.getSysCdQuery("x.is_last_version", maPmMstrCommonDTO.getIsLastVersion(), maPmMstrCommonDTO.getIsLastVersionDesc(), "IS_USE", compNo,user.getLangId());

        if(!"".equals(maPmMstrCommonDTO.getRevNo())){
            query.append("AND x.revisionhist_id IN (SELECT  a.revisionhist_id                               ");
            query.append("                          FROM    TAREVISIONHIST a                                ");
            query.append("                          WHERE   a.comp_no = x.comp_no                           ");
            query.append("                          AND     a.revision_object_type = 'PMSTD'                ");
            query.append("                          AND     a.rev_no = '"+maPmMstrCommonDTO.getRevNo()+"'   ");
            query.append("                          )                                                       ");
        }
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+compNo+"' AND a.plant = x.plant )", 
        		maPmMstrCommonDTO.getFilterPlantId(), maPmMstrCommonDTO.getFilterPlantDesc());
        
        // 생성일자        
        if(!"".equals(maPmMstrCommonDTO.getFilterFromCreDate()) || !"".equals(maPmMstrCommonDTO.getFilterToCreDate())){
        	String fromCreDate = maPmMstrCommonDTO.getFilterFromCreDate();
        	String toCreDate = maPmMstrCommonDTO.getFilterToCreDate();
        	query.getAndDateQuery("x.cre_date", fromCreDate, toCreDate);
        }

        //상위설비명
        /*if(!"".equals(maPmMstrCommonDTO.getFilterPEquipId()) || !"".equals(maPmMstrCommonDTO.getFilterPEquipDesc()))
        {
        	query.append(" AND x.pm_id IN (SELECT a.pm_id                          						");
        	query.append("                    FROM TAPMEQUIP a, TAEQUIPMENT b        					");
        	query.append("                    WHERE a.comp_no = b.comp_no            					");
        	query.append("                      AND a.equip_id = b.equip_id        						");
        	query.append("                    AND a.comp_no = x.comp_no									");
        	query.append("                    AND b.p_equip_id IN (SELECT c.equip_id FROM TAEQUIPMENT c ");
        	query.append("                                        WHERE c.comp_no = b.comp_no			");
        	query.getCodeLikeQuery("c.equip_id", "c.description", maPmMstrCommonDTO.getFilterPEquipId(), maPmMstrCommonDTO.getFilterPEquipDesc());
        	query.getDeptLevelQuery("c.usage_dept", maPmMstrCommonDTO.getFilterPEqUsaDeptId(), maPmMstrCommonDTO.getFilterPEqUsaDeptDesc(), compNo);
        	query.getLikeQuery("c.item_no", maPmMstrCommonDTO.getFilterPEquipNo());
        	query.append("                                        )										");
        	query.append("                 )                    										");
        }*/
        
        //상위설비사용부서
//        if(!"".equals(maPmMstrCommonDTO.getFilterPEqUsaDeptId()) || !"".equals(maPmMstrCommonDTO.getFilterPEqUsaDeptDesc()))
//        {
//        	query.append(" AND x.pm_id IN (SELECT a.pm_id                                                  		");
//        	query.append("                   FROM TAPMEQUIP a, TAEQUIPMENT b                            		");
//        	query.append("                  WHERE a.comp_no = b.comp_no                                			");
//        	query.append("                    AND a.equip_id = b.equip_id                                		");
//        	query.append("                    AND a.comp_no = x.comp_no                                    		");
//        	query.append("                    AND b.p_equip_id IN (SELECT c.equip_id FROM TAEQUIPMENT c 		");
//        	query.append("                                          WHERE c.comp_no = b.comp_no            		");
//        	query.append("                                            AND c.usage_dept IN ( SELECT d.dept_id FROM TADEPT d WHERE d.comp_no = c.comp_no  		");
//        	query.getDeptLevelQuery("d.dept_id", maPmMstrCommonDTO.getFilterPEqUsaDeptId(), maPmMstrCommonDTO.getFilterPEqUsaDeptDesc(), compNo);
//        	query.append("                                                                 )            		");
//        	query.append("                                         )                                    		");
//        	query.append("                 )																	");
//        }
        
        return query.toString();
    }
    
    public int insertCopyDetail(String newId, String oldId, String revStat,	String revhistId, User user, boolean revFlag)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPMLST                      ");
    	query.append("(                                        ");
    	query.append("    pm_id,           comp_no,            ");
    	query.append("    pm_no,						       ");
    	query.append("    description,        				   ");
    	query.append("    dept_id,                   		   ");
    	query.append("    pm_type,         schedule_type,      ");
    	query.append("    cycle,           period_type,        ");
    	query.append("    USAGE,           wo_res_bef,         ");
    	query.append("    init_wrk_date,  				 	   ");
    	query.append("    wo_type,         pm_categ,           ");
    	query.append("    emp_id,          eqloc_id,           ");
    	query.append("    is_active,       remark,             ");
    	query.append("    wkctr_id,		   wrkcallist_id, 	   ");
    	query.append("    lnwrklist_id,    rsum_usage,         ");
    	query.append("    revisionhist_id, revision_status,    ");
    	query.append("    is_last_version, plant,     		   ");
    	query.append("    cre_date, 	   cre_by     	       ");
    	query.append("    ,tolerance     , work_number		   ");
    	query.append(") SELECT                                 ");
    	query.append("    ?,               x.comp_no,          ");
    	
    	if(revFlag)
    	{
    		query.append("    '"+newId+"',     					");
    		query.append("    '['+(SELECT a.key_name FROM TALANG a WHERE a.key_type='LABEL' AND a.key_no='copied' AND a.lang = '"+user.getLangId()+"')+']'+x.description,      ");
    	}
    	else 
    	{
    		query.append("    x.pm_no,               			");
    		query.append("    x.description,      				");
    	}
    	
    	query.append("    x.dept_id,       				       	");
    	query.append("    x.pm_type,       x.schedule_type,    	");
    	query.append("    x.cycle,         x.period_type,      	");
    	query.append("    x.USAGE,         x.wo_res_bef,       	");
    	query.append("    CONVERT(VARCHAR, getDate()-1, 112),  	");
    	query.append("    x.wo_type,       x.pm_categ,         	");
    	query.append("    x.emp_id,        x.eqloc_id,         	");
    	query.append("    x.is_active, 	   x.remark,           	");
    	query.append("    x.wkctr_id,	   x.wrkcallist_id,    	");
    	query.append("    x.lnwrklist_id,  x.rsum_usage,       	");
    	query.append("    ?,	   		   ?,		           	");
    	query.append("    ?,			   x.plant,				");
    	query.append("    ?,               ?					");
    	query.append("    ,x.tolerance     , work_number		");
    	query.append("	FROM TAPMLST x							");
    	query.append("	WHERE 1=1								");
    	query.append("	AND comp_no = ?							");
    	query.append("	AND pm_id   = ?							");

    	Object[] objects = new Object[] {
    			newId
    			,revhistId
    			,revStat
    			,"N".equals(MwareConfig.getIsUsePmRevision())?"Y":"N"
            	,DateUtil.getDateTime("yyyyMMddHHmmss")
            	,user.getUserId()
    			,user.getCompNo()
    			,oldId
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
    	return rtnValue;
    }
    public int insertCopyEquip(String newId, String oldId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	query.append("INSERT INTO TAPMEQUIP(                                   ");
        query.append("      comp_no,    pmequip_id,     pm_id,                  ");
        query.append("      equip_id,   init_wrk_date,  last_sch_date           ");
        query.append("      ,is_active  ,eqasmb_id  )                           ");
        query.append("SELECT                                                    ");
        query.append("      x.comp_no,  NEXT VALUE FOR dbo.SQAPMEQUIP_ID,  ?,   ");
        query.append("      x.equip_id, ISNULL(x.last_cplt_date, x.init_wrk_date),        x.last_sch_date ");
        query.append("      ,x.is_active,x.eqasmb_id                            ");
        query.append("  FROM TAPMEQUIP x                                        ");
        query.append("  WHERE 1=1                                               ");
        query.append("  AND comp_no = ?                                         ");
        query.append("  AND pm_id   = ?                                         ");
        query.append("  AND is_deleted  = ?                                     ");
        Object[] objects = new Object[] {
                newId,
                user.getCompNo(),
                oldId,
                "N"
        };
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
    	return rtnValue;
    }
    public int insertCopyPoint(String newId, String oldId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	query.append("INSERT INTO TAPMPOINT(                                    ");
    	query.append("            COMP_NO, PM_POINT_ID, PM_ID,                  ");
    	query.append("            STEP_NUM, EQASMB_ID, CHECK_POINT,             ");
    	query.append("            CHECK_METHOD, FIT_BASIS, CHECK_TYPE,          ");
    	query.append("            CHECK_MIN, CHECK_BASIS_VAL, CHECK_MAX,        ");
    	query.append("            UOM, IS_ACTIVE, REMARK,                       ");
    	query.append("            STWRK_POINT_ID, STD_CHECK_POINT_ID, FIT_RATE,	");
    	query.append("            IS_RUN_VALUE                )                 ");
    	query.append("SELECT     COMP_NO, NEXT VALUE FOR SQAPM_POINT_ID, ?,     ");
    	query.append("            STEP_NUM, EQASMB_ID, CHECK_POINT,             ");
    	query.append("            CHECK_METHOD, FIT_BASIS, CHECK_TYPE,          ");
    	query.append("            CHECK_MIN, CHECK_BASIS_VAL, CHECK_MAX,        ");
    	query.append("            UOM, IS_ACTIVE, REMARK,                       ");
    	query.append("            STWRK_POINT_ID, STD_CHECK_POINT_ID, FIT_RATE,	");
    	query.append("            IS_RUN_VALUE                					");
    	query.append("	FROM TAPMPOINT x										");
    	query.append("	WHERE 1=1												");
    	query.append("	AND comp_no = ?											");
    	query.append("	AND pm_id   = ?											");
    	query.append("  AND is_deleted  = ?                                     ");
    	
    	Object[] objects = new Object[] {
    			newId,
    			user.getCompNo(),
    			oldId,
    			"N"
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
    	return rtnValue;
    }
    
    public int insertCopySched(String newId, String oldId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DECLARE @plusdate VARCHAR(8) 											");
        query.append("SET @plusdate = LEFT(CONVERT(VARCHAR, dateadd(MONTH, +12, getdate()), 112),4)+'1231'	");
        query.append("EXEC dbo.SP_PM_MAKE_SCHEDULE_BYONE                             		");
        query.append("                  '"+user.getCompNo()+"'                       		");
        query.append("                 ,'"+newId+"'                                  		");
        query.append("                 ,'"+user.getUserId()+"'                       		");
        query.append("                 ,@plusdate											");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
    public int deleteQrCode(User loginUser, String fileName) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TAREPORTPARAM   ");
        query.append("WHERE  comp_no   = ?        ");
        query.append("AND    user_id   = ?        ");
        query.append("AND    file_name = ?        ");

        Object[] objects = new Object[]{
                loginUser.getCompNo()
                ,loginUser.getUserId()
                ,fileName
        };

        int result = this.getJdbcTemplate().update(query.toString(),getObject(objects));

        return result;
    }
    
    public int insertQrCode(String id, String fileName, User loginUser) {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query = new QuerySqlBuffer();
    	
		query.append("INSERT INTO TAREPORTPARAM (   ");
        query.append("        comp_no               ");
        query.append("        ,user_id              ");
        query.append("        ,skey_id              ");
        query.append("        ,file_name            ");
        query.append(")                             ");
        query.append("VALUES (                      ");
        query.append("        ?                     ");
        query.append("        ,?                    ");
        query.append("        ,?                    ");
        query.append("        ,?                    ");
        query.append(")                             ");
        
        Object[] objects = new Object[]{
                loginUser.getCompNo(),
                loginUser.getUserId(),
                id,
                fileName
        };
	    
	    int result = this.getJdbcTemplate().update(query.toString(),getObject(objects));
	    
	    return result;
	}
    public int insertListQrCode(MaPmMstrCommonDTO maPmMstrCommonDTO, String fileName, User loginUser) {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAREPORTPARAM (      ");
        query.append("        comp_no                  ");
        query.append("        ,user_id                 ");
        query.append("        ,skey_id                 ");
        query.append("        ,file_name               ");
        query.append(")                                ");
        query.append("SELECT x.comp_no, ?, x.pm_id, ?  ");
        query.append("FROM    TAPMLST x                ");
        query.append("WHERE   1=1                      ");
        query.append(this.getWhere(maPmMstrCommonDTO,loginUser));
        
        Object[] objects = new Object[]{
                loginUser.getUserId()
                ,fileName
        };
    	
    	int result = this.getJdbcTemplate().update(query.toString(),getObject(objects));
    	
    	return result;
    }
	@Override
	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();
		
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;              ");
        query.append(" SELECT                                                        ");
        query.append("        COUNT(1)                                               ");
		/*query.append("   FROM TAPMLST x                                              ");
		query.append("  WHERE 1=1	                                				 ");*/
        query.append("  FROM TAPMLST x LEFT OUTER JOIN ( SELECT                                                                                                                 ");
        query.append("                                          CASE a.eqctg_type WHEN 'MD' THEN '('+ a.old_eq_no +')'+ a.item_no ELSE a.item_no END    AS equipNo              ");
        query.append("                                        , a.description                                                                           AS equipDesc            ");
        query.append("                                        , CASE a.eqctg_type WHEN 'MD' THEN '('+ a.old_eq_no +')'+ a.item_no ELSE a.item_no END    AS itemNo               ");
        query.append("                                        , f.description                                                                           AS usageDeptDesc        ");
        query.append("                                        , d.pequipId                                                                              AS pequipId             ");
        query.append("                                        , d.pequipNo                                                                              AS pequipNo             ");
        query.append("                                        , d.pequipDesc                                                                            AS pequipDesc           ");
        query.append("                                        , d.pequipUsaDeptId                                                                       AS pequipUsaDeptId      ");
        query.append("                                        , d.pequipUsaDeptDesc                                                                     AS pequipUsaDeptDesc    ");
        query.append("                                        , dbo.SFACODE_TO_DESC(a.eq_grade, 'EQ_GRADE', 'SYS', '', '"+ user.getLangId() +"')        AS eqGradeDesc          ");
        query.append("                                        , a.model_no                                                                              AS modelNo              ");
        query.append("                                        , a.maker                                                                                 AS maker                ");
        query.append("                                        , ( SELECT c.eqloc_no                                                                                             ");
        query.append("                                              FROM TAEQLOC c                                                                                              ");
        query.append("                                             WHERE c.comp_no=a.comp_no                                                                                    ");
        query.append("                                               AND c.eqloc_id       = a.eqloc_id                                                                          ");
        query.append("                                               AND c.eqloc_lvl_type = 'L4' )                                                      AS eqlocNo              ");
        query.append("                                        , ( SELECT c.description                                                                                          ");
        query.append("                                              FROM TAEQLOC c                                                                                              ");
        query.append("                                             WHERE c.comp_no  = a.comp_no                                                                                 ");
        query.append("                                               AND c.eqloc_id = A.eqloc_id )                                                      AS eqlocDesc            ");
        query.append("                                        , a.comp_no                                                                                                       ");
        query.append("                                        , b.pm_id                                                                                                         ");
        query.append("                                        , b.init_wrk_date                                                                         AS initWrkDate          ");
        query.append("                                        , a.plf_type                                                                                                      ");
        query.append("                                        , a.equip_id                                                                                                      ");
        query.append("                                        , a.eqctg_type                                                                                                    ");
        query.append("                                        , a.main_mng_id                                                                                                   ");
        query.append("                                        , a.sub_mng_id                                                                                                    ");
        query.append("                                        , a.eqloc_id                                                                                                      ");
        query.append("                                        , a.eqctg_id                                                                                                      ");
        query.append("                                        , a.is_law_eq                                                                                                     ");
        query.append("                                        , a.item_no                                                                                                       ");
        query.append("                                        , a.usage_dept                                                                                                    ");
        query.append("                                        , a.eq_grade                                                                                                      ");
        query.append("                                        , a.description                                                                                                   ");
        query.append("                                     FROM TAEQUIPMENT A INNER JOIN TAPMEQUIP b                                                                            ");
        query.append("                                                                ON A.equip_id = b.equip_id                                                                ");
        query.append("                                                               AND A.comp_no  = b.comp_no                                                                 ");
        query.append("                                                   LEFT OUTER JOIN TADEPT f                                                                               ");
        query.append("                                                                ON A.usage_dept = f.dept_id                                                               ");
        query.append("                                                               AND A.comp_no    = f.comp_no                                                               ");
        query.append("                                                   LEFT OUTER JOIN ( SELECT                                                                               ");
        query.append("                                                                            y.description     AS pequipDesc                                               ");
        query.append("                                                                          , y.equip_id        AS pequipId                                                 ");
        query.append("                                                                          , y.item_no         AS pequipNo                                                 ");
        query.append("                                                                          , c.description     AS pequipUsaDeptDesc                                        ");
        query.append("                                                                          , c.dept_id         AS pequipUsaDeptId                                          ");
        query.append("                                                                          , x.equip_id                                                                    ");
        query.append("                                                                          , y.comp_no                                                                     ");
        query.append("                                                                       FROM TAEQUIPMENT x JOIN TAEQUIPMENT y                                              ");
        query.append("                                                                                            ON x.p_equip_id = y.equip_id                                  ");
        query.append("                                                                               LEFT OUTER JOIN TADEPT c                                                   ");
        query.append("                                                                                            ON y.usage_dept = c.dept_id                                   ");
        query.append("                                                                                           AND y.comp_no    = c.comp_no                                   ");
        query.append("                                                                   ) d                                                                                    ");
        query.append("                                                                ON d.equip_id = A.equip_id                                                                ");
        query.append("                                                               AND A.comp_no  = d.comp_no                                                                 ");
        query.append("                                    WHERE 1 = 1                                                                                                           ");
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("b.is_deleted", "N");
        query.append("                                 ) y                                                                                                                      ");
        query.append("                              ON x.pm_id   = y.pm_id                                                                                                      ");
        query.append("                             AND x.comp_no = y.comp_no                                                                                                    ");
        query.append(" WHERE 1 = 1                                                                                                                                              ");
        query.append(this.getWhere(maPmMstrCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
    @Override
    public String[] selectPmEquipMaps(String id, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                ");
        query.append("  pmequip_id          ");
        query.append("FROM TAPMEQUIP        ");
        query.append("WHERE comp_no = ?     ");
        query.append("AND pm_id = ?         ");
        query.append("AND is_deleted = ?    ");
        
        Object[] objects = new Object[]{
                loginUser.getCompNo()
                ,id
                ,"N"
        };
        
        return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(), getObject(objects)));
    }
    @Override
    public int insertCopyOneEquip(String newPmId, String newPmEquipId, String oldPmEquipId, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        query.append("INSERT INTO TAPMEQUIP(                                    ");
        query.append("      comp_no,    pmequip_id,     pm_id,                  ");
        query.append("      equip_id,   init_wrk_date,  last_sch_date           ");
        query.append("      ,is_active  ,eqasmb_id  )                           ");
        query.append("SELECT                                                    ");
        query.append("      x.comp_no,  ?,                      ?,              ");
        query.append("      x.equip_id, x.init_wrk_date,        x.last_sch_date ");
        query.append("      ,x.is_active,x.eqasmb_id                            ");
        query.append("  FROM TAPMEQUIP x                                        ");
        query.append("  WHERE 1=1                                               ");
        query.append("  AND comp_no = ?                                         ");
        query.append("  AND pmequip_id   = ?                                    ");
        query.append("  AND is_deleted  = ?                                     ");
        Object[] objects = new Object[] {
                newPmEquipId,
                newPmId,
                loginUser.getCompNo(),
                oldPmEquipId,
                "N"
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
        return rtnValue;
    }
    @Override
    public int insertCopyPart(String newId, String oldId, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        query.append("INSERT INTO TAPMPART(                                     ");
        query.append("      comp_no     ,pm_part_id             ,pm_id          ");
        query.append("      ,part_id    ,use_qty                ,pmequip_id     ");
        query.append("      )                                                   ");
        query.append("SELECT                                                    ");
        query.append("      x.comp_no   ,NEXT VALUE FOR SQAPM_PART_ID  ,?       ");
        query.append("      ,x.part_id  ,x.use_qty              ,x.pmequip_id   ");
        query.append("  FROM TAPMPART x                                         ");
        query.append("  WHERE 1=1                                               ");
        query.append("  AND comp_no = ?                                         ");
        query.append("  AND pm_id   = ?                                         ");
        query.append("  AND is_deleted  = ?                                     ");
        Object[] objects = new Object[] {
                newId,
                loginUser.getCompNo(),
                oldId,
                "N"
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
        return rtnValue;
    }
    @Override
    public int updatePmEquipId(String newPmId, String newPmEquipId, String oldPmEquipId, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        query.append("UPDATE TAPMPART       ");
        query.append("  SET pmequip_id = ?  ");
        query.append("WHERE comp_no = ?     ");
        query.append("AND pm_id = ?         ");
        query.append("AND pmequip_id = ?    ");

        Object[] objects = new Object[] {
                newPmEquipId,
                loginUser.getCompNo(),
                newPmId,
                oldPmEquipId
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
        return rtnValue;
    }
	@Override
	public String getData(User user, MaPmMstrCommonDTO maPmMstrCommonDTO) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 										");
		query.append("    CASE WHEN convert(nvarchar,MIN(x.exceltab_id)) IS NOT NULL 	");
		query.append("             THEN convert(nvarchar,MIN(x.exceltab_id))+ ',' + min(x.description) + ',' + min(x.table_name) 	");
		query.append("             ELSE '0' 						");
		query.append("             END           					");
		query.append("FROM TAEXCELTAB x								");
		query.append("WHERE x.exceltab_no = ?						");
		
        Object[] objects = new Object[] {
        		maPmMstrCommonDTO.getExceltabNo()
        };

		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), getObject(objects)));
	}
	
    public int insertCopyMsTime(String newId, String oldId, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPMMEASURETIME(                       				");
    	query.append("		comp_no			, pmmeasuretime_id		, pm_id				");
    	query.append("	  , measure_time	, remark									");
    	query.append(") SELECT                                       					");
    	query.append("		x.comp_no		, NEXT VALUE FOR SQAPMMEASURETIME_ID	, ?	");
    	query.append("	  , x.measure_time	, x.remark									");
    	query.append("	FROM TAPMMEASURETIME x											");
    	query.append(" WHERE x.comp_no = ?												");
    	query.append("	 AND x.pm_id   = ?												");

        Object[] objects = new Object[] {
    		     newId
        	   , loginUser.getCompNo()
    		   , oldId
        };
        
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}