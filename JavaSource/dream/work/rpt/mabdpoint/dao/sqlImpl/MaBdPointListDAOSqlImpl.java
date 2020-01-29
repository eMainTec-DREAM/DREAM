package dream.work.rpt.mabdpoint.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.mabdpoint.dao.MaBdPointListDAO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;

/**
 * 이상점검조치 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maBdPointListDAOTarget"
 * @spring.txbn id="maBdPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBdPointListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaBdPointListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointCommonDTO
     * @return List
     */
    public List findList(MaBdPointCommonDTO maBdPointCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT * FROM  (			                                                                            ");
        
        query.append("SELECT     ''                                                             AS SEQNO                    ");
        query.append("         , ''                                                             AS ISDELCHECK               ");
        query.append("         , x.wongpoint_id                                                   AS WONGPOINTID              ");
        query.append("         , x.pm_rep_method_type                                           AS PMREPMETHODTYPE          ");
        query.append("         , x.actual_date                                                  AS ACTUALDATE               ");
        query.append("         , x.pm_wkor_id                                                                               ");
        query.append("         , y.pm_type                                                                                  ");
        query.append("         , dbo.SFACODE_TO_DESC(y.pm_type,'PMI_TYPE','SYS',x.comp_no,'"+user.getLangId()+"')           AS PMTYPEDESC               ");
        query.append("         , (SELECT b.equip_id                                                                         ");
        query.append("            FROM TAWOEQUIP a, TAEQUIPMENT b                                                           ");
        query.append("            WHERE a.comp_no = b.comp_no                                                               ");
        query.append("              AND a.equip_id = b.equip_id                                                             ");
        query.append("              AND a.wkor_id = y.wkor_id                                                               ");
        query.append("              AND a.comp_no = y.comp_no                                                               ");
        query.append("            GROUP BY a.comp_no, a.wkor_id, b.equip_id    )                AS EQUIPID                  ");
        query.append("         , (SELECT (SELECT c.full_desc FROM TAEQLOC c WHERE c.eqloc_id = b.eqloc_id)                  ");
        query.append("            FROM TAWOEQUIP a, TAEQUIPMENT b                                                           ");
        query.append("            WHERE a.comp_no = b.comp_no                                                               ");
        query.append("              AND a.equip_id = b.equip_id                                                             ");
        query.append("              AND a.wkor_id = y.wkor_id                                                               ");
        query.append("              AND a.comp_no = y.comp_no                                                               ");
        query.append("            GROUP BY a.comp_no, a.wkor_id, b.eqloc_id    )                AS EQLOCDESC                ");
        query.append("		   ,(SELECT b.eqloc_id                                                                  ");
        query.append("			FROM TAWOEQUIP a, TAEQUIPMENT b                                                    	");
        query.append("			WHERE a.comp_no = b.comp_no                                                        	");
        query.append("			AND a.equip_id = b.equip_id                                                      	");
        query.append("			AND a.wkor_id = y.wkor_id                                                        	");
        query.append("			AND a.comp_no = y.comp_no                                                        	");
        query.append("			GROUP BY a.comp_no, a.wkor_id, b.eqloc_id    )                	AS eqlocId   		");
        query.append("         ,c.description                            						AS EQUIPDESC                ");
        query.append("         ,c.item_no                                                       AS EQUIPNO                  ");
        query.append("         , (SELECT a.emp_name                                                                         ");
        query.append("            FROM TAEMP a                                                                              ");
        query.append("            WHERE a.comp_no = y.comp_no                                                               ");
        query.append("              AND a.emp_id = y.emp_id)                                    AS WOCRAFT                  ");
        query.append("         , ISNULL((SELECT a.description                                                                      ");
        query.append("            FROM TAEQASMB a                                                                           ");
        query.append("            WHERE a.comp_no = z.comp_no                                                               ");
        query.append("              AND a.eqasmb_id = z.eqasmb_id),'')+'/'+ z.check_point           AS ASMBINSPECT              ");
        query.append("         , dbo.SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+user.getLangId()+"')               ");
        query.append("                                                                          AS PMPOINTRSLTSTATUS        ");
        query.append("         , x.result_value                                                 AS RESULTVALUE              ");
        query.append("         ,(SELECT wk.wo_no FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)          AS INSPECTWONO                ");
        query.append("         ,(SELECT wk.description FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS INSPECTWODESC            ");
        query.append("         ,dbo.SFACODE_TO_DESC(x.pm_point_rep_status,'PM_POINT_REP_STATUS','SYS',x.comp_no,'"+user.getLangId()+"')                     ");
        query.append("                                                                          AS PMPOINTREPSTATUS         ");
        query.append("         , x.repair_desc                                                  AS REPAIRDESC               ");
        query.append("         , x.repair_date                                                  AS REPAIRDATE               ");
        query.append("		   ,(SELECT a.description FROM TADEPT a 														");
        query.append("		     WHERE a.comp_no = x.comp_no AND a.dept_id													");
        query.append("		     				 = (SELECT b.dept_id FROM TAEMP b 											");
        query.append("		     				 	WHERE b.comp_no = x.comp_no 											");
        query.append("								AND b.emp_id = x.repair_by)) 				AS repairDept				");
        query.append("         , (SELECT a.emp_name                                                                         ");
        query.append("            FROM TAEMP a                                                                              ");
        query.append("            WHERE a.comp_no = x.comp_no                                                               ");
        query.append("              AND a.emp_id = x.repair_by)                                 AS REPAIRBY                 ");
        query.append("         , x.pmi_type                                                     AS PMITYPE                  ");
        query.append("         , x.REMARK                                                       AS INSPECTREMARK            ");
        query.append(",(SELECT a.param1 FROM TACDSYSD a                                                     		");
        query.append("             WHERE a.list_type = 'BM_TYPE'                                                    ");
        query.append("             AND a.key_no = 'BM_TYPE.'+(SELECT b.pm_type FROM TAWORKORDER b                  ");
        query.append("                              WHERE b.wongpoint_id = x.wongpoint_id))     AS param1        	");
        query.append("          , x.woplan_wkor_id           									AS woplanId			");
        query.append("          , x.woreq_id                     								AS woreqId			");
		query.append("   ,(SELECT a.description																				");
		query.append("	   		FROM TADEPT	a																				");
		query.append("	   		WHERE a.comp_no = y.comp_no																	");
		query.append("	   		AND a.dept_id = y.dept_id) 										AS deptDesc		         	");
		query.append("   ,(SELECT a.description																				");
		query.append("	   		FROM TAPLANT	a																			");
		query.append("	   		WHERE a.comp_no = y.comp_no																	");
		query.append("	   		AND a.plant = y.plant) 										    AS plantDesc		        ");
		query.append("FROM TAWONGPOINT x INNER JOIN TAWORKORDER y                                                           ");
        query.append("        ON x.comp_no = y.comp_no AND x.wkor_id = y.wkor_id                                            ");
        query.append("INNER JOIN TAPMPOINT z                                                                                ");
        query.append("        ON x.comp_no = z.comp_no AND x.pm_point_id = z.pm_point_id                                    ");
        query.append("INNER JOIN TAWOEQUIP b                                                                                ");
        query.append("        ON y.wkor_id = b.wkor_id AND y.comp_no = b.comp_no                                            ");
        query.append("INNER JOIN TAEQUIPMENT c                                                                              ");
        query.append("        ON b.equip_id = c.equip_id AND b.comp_no = c.comp_no                                          ");
        query.append("WHERE 1 = 1                                                                                           ");
        query.append("AND x.pmi_type = 'INS'                                                                                ");
if("N".equals(maBdPointCommonDTO.getReloadRow())){
//        query.append("AND y.wo_status = 'C'                                                                                 ");
        query.append("AND x.pm_point_rslt_status = 'BD'                                                                     ");
        query.append(this.getWhereIns(maBdPointCommonDTO, user));
}
        query.append("UNION ALL");
        
        query.append("SELECT     ''                                                             AS SEQNO                    ");
        query.append("         , ''                                                             AS ISDELCHECK               ");
        query.append("         , x.wongpoint_id                                                   AS WONGPOINTID              ");
        query.append("         , x.pm_rep_method_type                                           AS PMREPMETHODTYPE          ");
        query.append("         , x.actual_date                                                  AS ACTUALDATE               ");
        query.append("         , x.pm_wkor_id                                                                               ");
        query.append("         , y.pm_type                                                                                  ");
        query.append("         , dbo.SFACODE_TO_DESC(y.pm_type,'PMI_TYPE','SYS',x.comp_no,'"+user.getLangId()+"')           AS PMTYPEDESC               ");
        query.append("         , y.equip_id          											AS equipId			");
        query.append("         , (SELECT a.full_desc                                                                        ");
        query.append("            FROM TAEQLOC a                                                                            ");
        query.append("            WHERE a.comp_no = x.comp_no                                                               ");
        query.append("              AND a.eqloc_id = (SELECT d.eqloc_id FROM TAEQUIPMENT d WHERE d.equip_id = y.equip_id))  AS   EQLOCDESC           ");
        query.append("			, (SELECT eqloc_id                                                                  ");
        query.append("			FROM TAEQLOC                                                                       	");
        query.append("			WHERE comp_no = x.comp_no                                                         	");
        query.append("			AND eqloc_id = (SELECT d.eqloc_id FROM TAEQUIPMENT d WHERE d.equip_id = y.equip_id))                eqLocId      		");
        query.append("          , (SELECT a.description                                                                     ");
        query.append("             FROM TAEQUIPMENT a                                                                       ");
        query.append("             WHERE a.comp_no = y.comp_no                                                              ");
        query.append("               AND a.equip_id = y.equip_id    )                           AS EQUIPDESC                ");
        query.append("          , (SELECT a.item_no                                                                         ");
        query.append("             FROM TAEQUIPMENT a                                                                       ");
        query.append("             WHERE a.comp_no = y.comp_no                                                              ");
        query.append("               AND a.equip_id = y.equip_id    )                           AS EQUIPNO                  ");
        query.append("          , (SELECT a.emp_name                                                                        ");
        query.append("             FROM TAEMP a                                                                             ");
        query.append("             WHERE a.comp_no = y.comp_no                                                              ");
        query.append("               AND a.emp_id = y.emp_id)                                   AS WOCRAFT                  ");
        query.append("          , ISNULL((SELECT description                                                                       ");
        query.append("             FROM TAEQASMB a                                                                          ");
        query.append("             WHERE a.comp_no = z.comp_no                                                              ");
        query.append("               AND a.eqasmb_id = z.eqasmb_id),'')+'/'+ z.check_point          AS ASMBINSPECT              ");
        query.append("          , dbo.SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+user.getLangId()+"')               ");
        query.append("                                                                          AS PMPOINTRSLTSTATUS        ");
        query.append("          , x.result_value                                                AS RESULTVALUE              ");
        query.append("          , (SELECT wk.wo_no FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)          AS INSPECTWONO                ");
        query.append("          , (SELECT wk.description FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS INSPECTWODESC            ");
        query.append("          , dbo.SFACODE_TO_DESC(x.pm_point_rep_status,'PM_POINT_REP_STATUS','SYS',x.comp_no,'"+user.getLangId()+"')                     ");
        query.append("                                                                          AS PMPOINTREPSTATUS         ");
        query.append("          , x.repair_desc                                                 AS REPAIRDESC               ");
        query.append("          , x.repair_date                                                 AS REPAIRDATE               ");
        query.append("		   ,(SELECT a.description FROM TADEPT a 														");
        query.append("		     WHERE a.comp_no = x.comp_no AND a.dept_id													");
        query.append("		     				 = (SELECT b.dept_id FROM TAEMP b 											");
        query.append("		     				 	WHERE b.comp_no = x.comp_no 											");
        query.append("								AND b.emp_id = x.repair_by)) 				AS repairDept				");
        query.append("          , (SELECT a.emp_name                                                                        ");
        query.append("             FROM TAEMP a                                                                             ");
        query.append("             WHERE a.comp_no = x.comp_no                                                              ");
        query.append("               AND a.emp_id = x.repair_by)                                AS REPAIRBY                 ");
        query.append("          , x.pmi_type                                                    AS PMITYPE                  ");
        query.append("          , x.REMARK                                                      AS INSPECTREMARK            ");
        query.append(",(SELECT a.param1 FROM TACDSYSD a                                                     		");
        query.append("             WHERE a.list_type = 'BM_TYPE'                                                    ");
        query.append("             AND a.key_no = 'BM_TYPE.'+(SELECT b.pm_type FROM TAWORKORDER b                  ");
        query.append("                              WHERE b.wongpoint_id = x.wongpoint_id))     AS param1        	");
        query.append("          , x.woplan_wkor_id           									AS woplanId			");
        query.append("          , x.woreq_id                     								AS woreqId			");
		query.append("   ,(SELECT a.description																				");
		query.append("	   		FROM TADEPT	a																				");
		query.append("	   		WHERE a.comp_no = y.comp_no																	");
		query.append("	   		AND a.dept_id = y.dept_id) 										AS deptDesc         		");
		query.append("   ,(SELECT a.description                                                                             ");
        query.append("          FROM TAPLANT    a                                                                           ");
        query.append("          WHERE a.comp_no = y.comp_no                                                                 ");
        query.append("          AND a.plant = y.plant)                                          AS plantDesc                ");
        query.append("FROM TAWONGPOINT x INNER JOIN TAPMINSLIST y                                                           ");
        query.append("        ON x.comp_no = y.comp_no AND x.pminslist_id = y.pminslist_id                                  ");
        query.append("INNER JOIN TAPMPOINT z                                                                                ");
        query.append("        ON x.comp_no = z.comp_no AND x.pm_point_id = z.pm_point_id                                    ");
        query.append("INNER JOIN TAEQUIPMENT c                                                                              ");
        query.append("        ON y.equip_id = c.equip_id AND y.comp_no = c.comp_no                                          ");
        query.append("WHERE 1 = 1                                                                                           ");
        query.append("AND x.pmi_type = 'RINS'                                                                               ");
if("N".equals(maBdPointCommonDTO.getReloadRow())){
//        query.append("AND y.pmsched_status = 'C'                                                                            ");
        query.append("AND x.pm_point_rslt_status = 'BD'                                                                     ");
        query.append(this.getWhereRIns(maBdPointCommonDTO, user));
}
        query.append("UNION ALL");

        query.append("SELECT     ''                                                             AS SEQNO                    ");
        query.append("         , ''                                                             AS ISDELCHECK               ");
        query.append("         ,  x.wongpoint_id                                                AS WONGPOINTID              ");
        query.append("         , x.pm_rep_method_type                                           AS PMREPMETHODTYPE          ");
        query.append("         , x.actual_date                                                  AS ACTUALDATE               ");
        query.append("         , x.pm_wkor_id                                                                               ");
        query.append("         , y.pm_type                                                                                  ");
        query.append("         , dbo.SFACODE_TO_DESC(y.pm_type,'PMI_TYPE','SYS',x.comp_no,'"+user.getLangId()+"')           AS PMTYPEDESC               ");
        query.append("         , y.equip_id          											AS equipId			");
        query.append("         , (SELECT a.full_desc                                                                        ");
        query.append("            FROM TAEQLOC a                                                                            ");
        query.append("            WHERE a.comp_no = x.comp_no                                                               ");
        query.append("              AND a.eqloc_id = (SELECT d.eqloc_id FROM TAEQUIPMENT d WHERE d.equip_id = y.equip_id))  AS EQLOCDESC           ");
        query.append("			, (SELECT eqloc_id                                                                  ");
        query.append("			FROM TAEQLOC                                                                       	");
        query.append("			WHERE comp_no = x.comp_no                                                         	");
        query.append("			AND eqloc_id = (SELECT d.eqloc_id FROM TAEQUIPMENT d WHERE d.equip_id = y.equip_id))                eqLocId      		");
        query.append("         , (SELECT a.description                                                                      ");
        query.append("            FROM TAEQUIPMENT a                                                                        ");
        query.append("            WHERE a.comp_no = y.comp_no                                                               ");
        query.append("                AND a.equip_id = y.equip_id    )                          AS EQUIPDESC                ");
        query.append("         , (SELECT a.item_no                                                                          ");
        query.append("            FROM TAEQUIPMENT a                                                                        ");
        query.append("            WHERE a.comp_no = y.comp_no                                                               ");
        query.append("                AND a.equip_id = y.equip_id    )                          AS EQUIPNO                  ");
        query.append("         , (SELECT a.emp_name                                                                         ");
        query.append("            FROM TAEMP a                                                                              ");
        query.append("            WHERE a.comp_no = y.comp_no                                                               ");
        query.append("              AND a.emp_id = y.emp_id)                                    AS WOCRAFT                  ");
        query.append("         , ISNULL((SELECT a.description                                                                      ");
        query.append("            FROM TAEQASMB a                                                                           ");
        query.append("            WHERE a.comp_no = z.comp_no                                                               ");
        query.append("            AND a.eqasmb_id = z.eqasmb_id),'')+'/'+  z.check_point            AS ASMBINSPECT              ");
        query.append("         , dbo.SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+user.getLangId()+"')               ");
        query.append("                                                                          AS PMPOINTRSLTSTATUS        ");
        query.append("         , x.result_value                                                 AS RESULTVALUE              ");
        query.append("         , (SELECT wk.wo_no FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS INSPECTWONO              ");
        query.append("         , (SELECT wk.description FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS INSPECTWODESC          ");
        query.append("         , dbo.SFACODE_TO_DESC(x.pm_point_rep_status,'PM_POINT_REP_STATUS','SYS',x.comp_no,'"+user.getLangId()+"')                     ");
        query.append("                                                                          AS PMPOINTREPSTATUS         ");
        query.append("         , x.repair_desc                                                  AS REPAIRDESC               ");
        query.append("         , x.repair_date                                                  AS REPAIRDATE               ");
        query.append("		   ,(SELECT a.description FROM TADEPT a 														");
        query.append("		     WHERE a.comp_no = x.comp_no AND a.dept_id													");
        query.append("		     				 = (SELECT b.dept_id FROM TAEMP b 											");
        query.append("		     				 	WHERE b.comp_no = x.comp_no 											");
        query.append("								AND b.emp_id = x.repair_by)) 				AS repairDept				");
        query.append("         , (SELECT a.emp_name                                                                         ");
        query.append("            FROM TAEMP a                                                                              ");
        query.append("            WHERE a.comp_no = x.comp_no                                                               ");
        query.append("              AND a.emp_id = x.repair_by)                                 AS REPAIRBY                 ");
        query.append("         , x.pmi_type                                                     AS PMITYPE                  ");
        query.append("         , x.REMARK                                                       AS INSPECTREMARK            ");
        query.append(",(SELECT a.param1 FROM TACDSYSD a                                                     		");
        query.append("             WHERE a.list_type = 'BM_TYPE'                                                    ");
        query.append("             AND a.key_no = 'BM_TYPE.'+(SELECT b.pm_type FROM TAWORKORDER b                  ");
        query.append("                              WHERE b.wongpoint_id = x.wongpoint_id))     AS param1        	");
        query.append("          , x.woplan_wkor_id           									AS woplanId			");
        query.append("          , x.woreq_id                     								AS woreqId			");
		query.append("   ,(SELECT a.description																				");
		query.append("	   		FROM TADEPT	a																				");
		query.append("	   		WHERE a.comp_no = y.comp_no																	");
		query.append("	   		AND a.dept_id = y.dept_id) 										AS deptDesc         		");
		query.append("   ,(SELECT a.description                                                                             ");
        query.append("          FROM TAPLANT    a                                                                           ");
        query.append("          WHERE a.comp_no = y.comp_no                                                                 ");
        query.append("          AND a.plant = y.plant)                                          AS plantDesc                ");
        query.append("FROM TAWONGPOINT x INNER JOIN TAPMINSDLIST y                                                          ");
        query.append("        ON x.comp_no = y.comp_no AND x.pminsdlist_id = y.pminsdlist_id                                ");
        query.append("INNER JOIN TAPMPOINT z                                                                                ");
        query.append("        ON x.comp_no = z.comp_no AND x.pm_point_id = z.pm_point_id                                    ");
        query.append("INNER JOIN TAEQUIPMENT c                                                                              ");
        query.append("        ON y.equip_id = c.equip_id AND y.comp_no = c.comp_no                                          ");
        query.append("WHERE 1 = 1                                                                                           ");
        query.append("AND x.pmi_type = 'DINS'                                                                               ");
if("N".equals(maBdPointCommonDTO.getReloadRow())){
//        query.append("AND y.pmsched_status = 'C'                                                                            ");
        query.append("AND x.pm_point_rslt_status = 'BD'                                                                     ");
        query.append(this.getWhereRIns(maBdPointCommonDTO, user));
}
        query.append("UNION ALL");
        
        query.append("SELECT     ''                                                             AS SEQNO                    ");
        query.append("         , ''                                                             AS ISDELCHECK               ");
        query.append("         ,  x.wongpoint_id                                                AS WONGPOINTID              ");
        query.append("         , x.pm_rep_method_type                                           AS PMREPMETHODTYPE          ");
        query.append("         , x.actual_date                                                  AS ACTUALDATE               ");
        query.append("         , x.pm_wkor_id                                                                               ");
        query.append("         , y.pm_type                                                                                  ");
        query.append("         , dbo.SFACODE_TO_DESC(y.pm_type,'PMI_TYPE','SYS',x.comp_no,'"+user.getLangId()+"')           AS PMTYPEDESC               ");
        query.append("         , y.equip_id          											AS equipId			");
        query.append("         , (SELECT a.full_desc                                                                        ");
        query.append("            FROM TAEQLOC a                                                                            ");
        query.append("            WHERE a.comp_no = x.comp_no                                                               ");
        query.append("              AND a.eqloc_id = (SELECT d.eqloc_id FROM TAEQUIPMENT d WHERE d.equip_id = y.equip_id))  AS EQLOCDESC           ");
        query.append("			, (SELECT eqloc_id                                                                  ");
        query.append("			FROM TAEQLOC                                                                       	");
        query.append("			WHERE comp_no = x.comp_no                                                         	");
        query.append("			AND eqloc_id = (SELECT d.eqloc_id FROM TAEQUIPMENT d WHERE d.equip_id = y.equip_id))                eqLocId      		");
        query.append("         , (SELECT a.description                                                                      ");
        query.append("            FROM TAEQUIPMENT a                                                                        ");
        query.append("            WHERE a.comp_no = y.comp_no                                                               ");
        query.append("                AND a.equip_id = y.equip_id    )                          AS EQUIPDESC                ");
        query.append("         , (SELECT a.item_no                                                                          ");
        query.append("            FROM TAEQUIPMENT a                                                                        ");
        query.append("            WHERE a.comp_no = y.comp_no                                                               ");
        query.append("                AND a.equip_id = y.equip_id    )                          AS EQUIPNO                  ");
        query.append("         , (SELECT a.emp_name                                                                         ");
        query.append("            FROM TAEMP a                                                                              ");
        query.append("            WHERE a.comp_no = y.comp_no                                                               ");
        query.append("              AND a.emp_id = y.emp_id)                                    AS WOCRAFT                  ");
        query.append("         , ISNULL((SELECT a.description                                                                      ");
        query.append("            FROM TAEQASMB a                                                                           ");
        query.append("            WHERE a.comp_no = z.comp_no                                                               ");
        query.append("            AND a.eqasmb_id = z.eqasmb_id),'')+'/'+  z.check_point            AS ASMBINSPECT              ");
        query.append("         , dbo.SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+user.getLangId()+"')               ");
        query.append("                                                                          AS PMPOINTRSLTSTATUS        ");
        query.append("         , x.result_value                                                 AS RESULTVALUE              ");
        query.append("         , (SELECT wk.wo_no FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS INSPECTWONO              ");
        query.append("         , (SELECT wk.description FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS INSPECTWODESC          ");
        query.append("         , dbo.SFACODE_TO_DESC(x.pm_point_rep_status,'PM_POINT_REP_STATUS','SYS',x.comp_no,'"+user.getLangId()+"')                     ");
        query.append("                                                                          AS PMPOINTREPSTATUS         ");
        query.append("         , x.repair_desc                                                  AS REPAIRDESC               ");
        query.append("         , x.repair_date                                                  AS REPAIRDATE               ");
        query.append("		   ,(SELECT a.description FROM TADEPT a 														");
        query.append("		     WHERE a.comp_no = x.comp_no AND a.dept_id													");
        query.append("		     				 = (SELECT b.dept_id FROM TAEMP b 											");
        query.append("		     				 	WHERE b.comp_no = x.comp_no 											");
        query.append("								AND b.emp_id = x.repair_by)) 				AS repairDept				");
        query.append("         , (SELECT a.emp_name                                                                         ");
        query.append("            FROM TAEMP a                                                                              ");
        query.append("            WHERE a.comp_no = x.comp_no                                                               ");
        query.append("              AND a.emp_id = x.repair_by)                                 AS REPAIRBY                 ");
        query.append("         , x.pmi_type                                                     AS PMITYPE                  ");
        query.append("         , x.REMARK                                                       AS INSPECTREMARK            ");
        query.append(",(SELECT a.param1 FROM TACDSYSD a                                                     		");
        query.append("             WHERE a.list_type = 'BM_TYPE'                                                    ");
        query.append("             AND a.key_no = 'BM_TYPE.'+(SELECT b.pm_type FROM TAWORKORDER b                  ");
        query.append("                              WHERE b.wongpoint_id = x.wongpoint_id))     AS param1        	");
        query.append("          , x.woplan_wkor_id           									AS woplanId			");
        query.append("          , x.woreq_id                     								AS woreqId			");
        query.append("   ,(SELECT a.description																				");
        query.append("	   		FROM TADEPT	a																				");
        query.append("	   		WHERE a.comp_no = y.comp_no																	");
        query.append("	   		AND a.dept_id = y.dept_id) 										AS deptDesc         		");
        query.append("   ,(SELECT a.description                                                                             ");
        query.append("          FROM TAPLANT    a                                                                           ");
        query.append("          WHERE a.comp_no = y.comp_no                                                                 ");
        query.append("          AND a.plant = y.plant)                                          AS plantDesc                ");
        query.append("FROM TAWONGPOINT x INNER JOIN TAPMINSDLIST y                                                          ");
        query.append("        ON x.comp_no = y.comp_no AND x.pminsdlist_id = y.pminsdlist_id                                ");
        query.append("INNER JOIN TAPMPOINT z                                                                                ");
        query.append("        ON x.comp_no = z.comp_no AND x.pm_point_id = z.pm_point_id                                    ");
        query.append("INNER JOIN TAEQUIPMENT c                                                                              ");
        query.append("        ON y.equip_id = c.equip_id AND y.comp_no = c.comp_no                                          ");
        query.append("WHERE 1 = 1                                                                                           ");
        query.append("AND x.pmi_type = 'CINS'                                                                               ");
        if("N".equals(maBdPointCommonDTO.getReloadRow())){
//        	query.append("AND y.pmsched_status = 'C'                                                                            ");
        	query.append("AND x.pm_point_rslt_status = 'BD'                                                                     ");
        	query.append(this.getWhereRIns(maBdPointCommonDTO, user));
        }
        query.append("UNION ALL");
        
        query.append("SELECT     ''                                                             AS SEQNO                    ");
        query.append("         , ''                                                             AS ISDELCHECK               ");
        query.append("         , x.wongpoint_id                                                 AS WONGPOINTID              ");
        query.append("         , x.pm_rep_method_type                                           AS PMREPMETHODTYPE          ");
        query.append("         , x.actual_date                                                  AS ACTUALDATE               ");
        query.append("         , x.pm_wkor_id                                                                               ");
        query.append("         , y.pm_type                                                                                  ");
        query.append("         , dbo.SFACODE_TO_DESC(y.pm_type,'PMI_TYPE','SYS',x.comp_no,'"+user.getLangId()+"')     AS PMTYPEDESC               ");
        query.append("         , y.equip_id          											AS equipId			");
        query.append("         , (SELECT a.full_desc                                                                        ");
        query.append("            FROM TAEQLOC a                                                                            ");
        query.append("            WHERE a.comp_no = x.comp_no                                                               ");
        query.append("              AND a.eqloc_id = (SELECT d.eqloc_id FROM TAEQUIPMENT d WHERE d.equip_id = y.equip_id))  AS EQLOCDESC           ");
        query.append("			, (SELECT eqloc_id                                                                  ");
        query.append("			FROM TAEQLOC                                                                       	");
        query.append("			WHERE comp_no = x.comp_no                                                         	");
        query.append("			AND eqloc_id = (SELECT d.eqloc_id FROM TAEQUIPMENT d WHERE d.equip_id = y.equip_id))                eqLocId      		");        
        query.append("         , (SELECT a.description                                                                      ");
        query.append("            FROM TAEQUIPMENT a                                                                        ");
        query.append("            WHERE a.comp_no = y.comp_no                                                               ");
        query.append("              AND a.equip_id = y.equip_id    )                            AS EQUIPDESC                ");
        query.append("         , (SELECT a.item_no                                                                          ");
        query.append("            FROM TAEQUIPMENT a                                                                        ");
        query.append("            WHERE a.comp_no = y.comp_no                                                               ");
        query.append("              AND a.equip_id = y.equip_id    )                            AS EQUIPNO                  ");
        query.append("         , (SELECT a.emp_name                                                                         ");
        query.append("            FROM TAEMP a                                                                              ");
        query.append("            WHERE a.comp_no = y.comp_no                                                               ");
        query.append("              AND a.emp_id = y.emp_id)                                    AS WOCRAFT                  ");
        query.append("         , ISNULL((SELECT a.description                                                                      ");
        query.append("            FROM TAEQASMB a                                                                           ");
        query.append("            WHERE a.comp_no = z.comp_no                                                               ");
        query.append("              AND a.eqasmb_id = z.eqasmb_id),'')+'/'+ z.check_point           AS ASMBINSPECT              ");
        query.append("         , dbo.SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+user.getLangId()+"')                          ");
        query.append("                                                                          AS PMPOINTRSLTSTATUS        ");
        query.append("         , x.result_value                                                 AS RESULTVALUE              ");
        query.append("         , (SELECT wk.wo_no FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)          AS INSPECTWONO      ");
        query.append("         , (SELECT wk.description FROM TAWORKORDER wk WHERE wk.comp_no=x.comp_no AND wk.wongpoint_id = x.wongpoint_id)    AS INSPECTWODESC    ");
        query.append("         , dbo.SFACODE_TO_DESC(x.pm_point_rep_status,'PM_POINT_REP_STATUS','SYS',x.comp_no,'"+user.getLangId()+"')                            ");
        query.append("                                                                          AS PMPOINTREPSTATUS         ");
        query.append("         , x.repair_desc                                                  AS REPAIRDESC               ");
        query.append("         , x.repair_date                                                  AS REPAIRDATE               ");
        query.append("		   ,(SELECT a.description FROM TADEPT a 														");
        query.append("		     WHERE a.comp_no = x.comp_no AND a.dept_id													");
        query.append("		     				 = (SELECT b.dept_id FROM TAEMP b 											");
        query.append("		     				 	WHERE b.comp_no = x.comp_no 											");
        query.append("								AND b.emp_id = x.repair_by)) 				AS repairDept				");
        query.append("         , (SELECT a.emp_name                                                                         ");
        query.append("            FROM TAEMP a                                                                              ");
        query.append("            WHERE a.comp_no = x.comp_no                                                               ");
        query.append("              AND a.emp_id = x.repair_by)                                 AS REPAIRBY                 ");
        query.append("         , x.pmi_type                                                     AS PMITYPE                  ");
        query.append("         , x.REMARK                                                       AS INSPECTREMARK            ");
        query.append(",(SELECT a.param1 FROM TACDSYSD a                                                     		");
        query.append("             WHERE a.list_type = 'BM_TYPE'                                                    ");
        query.append("             AND a.key_no = 'BM_TYPE.'+(SELECT b.pm_type FROM TAWORKORDER b                  ");
        query.append("                              WHERE b.wongpoint_id = x.wongpoint_id))     AS param1        	");
        query.append("          , x.woplan_wkor_id           									AS woplanId			");
        query.append("          , x.woreq_id                     								AS woreqId			");
		query.append("   ,(SELECT a.description																				");
		query.append("	   		FROM TADEPT	a																				");
		query.append("	   		WHERE a.comp_no = y.comp_no																	");
		query.append("	   		AND a.dept_id = y.dept_id) 										AS deptDesc         		");
		query.append("   ,(SELECT a.description                                                                             ");
        query.append("          FROM TAPLANT    a                                                                           ");
        query.append("          WHERE a.comp_no = y.comp_no                                                                 ");
        query.append("          AND a.plant = y.plant)                                          AS plantDesc                ");
        query.append("FROM TAWONGPOINT x INNER JOIN TAPMPTRLRSLTLIST y                                                      ");
        query.append("        ON x.comp_no = y.comp_no AND x.pmptrlrsltlist_id = y.pmptrlrsltlist_id                        ");
        query.append("INNER JOIN TAPMPOINT z                                                                                ");
        query.append("        ON x.comp_no = z.comp_no AND x.pm_point_id = z.pm_point_id                                    ");
        query.append("INNER JOIN TAEQUIPMENT c                                                                              ");
        query.append("        ON y.equip_id = c.equip_id AND y.comp_no = c.comp_no                                          ");
        query.append("WHERE 1 = 1                                                                                           ");
        query.append("AND x.pmi_type = 'PINS'                                                                               ");
if("N".equals(maBdPointCommonDTO.getReloadRow())){
//        query.append("AND y.pmsched_status = 'C'                                                                            ");
        query.append("AND x.pm_point_rslt_status = 'BD'                                                                     ");
        query.append(this.getWhereRIns(maBdPointCommonDTO, user));
}
        
        //query.append("ORDER BY actualDate desc                  ");

        query.append(")	w					");
        query.append("WHERE 1=1						");
        query.append(this.getWhere(maBdPointCommonDTO, user));

        query.getOrderByQuery("w.WONGPOINTID", "w.ACTUALDATE", maBdPointCommonDTO.getOrderBy(), maBdPointCommonDTO.getDirection());
        
//        return getJdbcTemplate().queryForList(query.toString());
        return getJdbcTemplate().queryForList(query.toString(maBdPointCommonDTO.getIsLoadMaxCount(), maBdPointCommonDTO.getFirstRow()));

    }

    private String getWhere(MaBdPointCommonDTO maBdPointCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();

        if (!"".equals(maBdPointCommonDTO.getWoNgPointId()))
        {
            query.getAndQuery("w.WONGPOINTID", maBdPointCommonDTO.getWoNgPointId());
        }

        query.getSysCdQuery("w.pm_type", maBdPointCommonDTO.getFilterPmTypeId(), maBdPointCommonDTO.getFilterPmTypeDesc(), "PMI_TYPE", maBdPointCommonDTO.getCompNo(), user.getLangId());
        
        return query.toString();
    }
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointCommonDTO
     * @return
     * @throws Exception
     */

    //Ins 조건
    private String getWhereIns(MaBdPointCommonDTO maBdPointCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        String usageDept   = maBdPointCommonDTO.getFilterUsageDeptId();
        String usageDeptDesc   = maBdPointCommonDTO.getFilterUsageDeptDesc();
        
        query.getAndQuery("x.comp_no", maBdPointCommonDTO.getCompNo());
        
        query.getAndQuery("y.is_deleted", "N");
        query.getAndQuery("z.is_deleted", "N");
        query.getAndQuery("c.is_deleted", "N");
        
        //사용부서
        query.getDeptLevelQuery("c.usage_dept", usageDept, usageDeptDesc, user.getCompNo());
        
        //점검일자
        query.getAndDateQuery("x.actual_date", maBdPointCommonDTO.getFilterStartDate(), maBdPointCommonDTO.getFilterEndDate());
        //조치결과
        query.getCodeLikeQuery("x.pm_point_rep_status", "dbo.SFACODE_TO_DESC(x.pm_point_rep_status,'PM_POINT_REP_STATUS','SYS',x.comp_no,'"+user.getLangId()+"')", 
        		maBdPointCommonDTO.getFilterRepStatus(), maBdPointCommonDTO.getFilterRepStatusDesc());
        // 부서
        query.getDeptLevelQuery("y.dept_id", maBdPointCommonDTO.getFilterDeptId(), maBdPointCommonDTO.getFilterDeptDesc(), maBdPointCommonDTO.getCompNo());
        //작업자
        query.getCodeLikeQuery("y.emp_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = y.comp_no AND a.emp_id = y.emp_id)", 
        		maBdPointCommonDTO.getFilterEmpId(), maBdPointCommonDTO.getFilterEmpName());
        //위치
        if(!"".equals(maBdPointCommonDTO.getFilterEqLocId())||!"".equals(maBdPointCommonDTO.getFilterEqLocDesc())){
        	query.append("AND y.wkor_id IN (SELECT a.wkor_id FROM TAWOEQUIP a	");
        	query.append("				WHERE a.equip_id IN (SELECT b.equip_id	");
        	query.append("									FROM TAEQUIPMENT b	");
        	query.append("									WHERE 1=1			");
        	query.getEqLocLevelQuery("b.eqloc_id", maBdPointCommonDTO.getFilterEqLocId(), maBdPointCommonDTO.getFilterEqLocDesc(), maBdPointCommonDTO.getCompNo());
        	query.append("									))					");
        }
        //종류
        if(!"".equals(maBdPointCommonDTO.getFilterEqCtgId())||!"".equals(maBdPointCommonDTO.getFilterEqCtgDesc())){
        	query.append("AND y.wkor_id IN (SELECT a.wkor_id FROM TAWOEQUIP a			");
        	query.append("				WHERE a.equip_id IN (SELECT b.equip_id		");
        	query.append("									FROM TAEQUIPMENT b	");
        	query.append("									WHERE 1=1			");
        	query.getEqLocLevelQuery("b.eqctg_id", maBdPointCommonDTO.getFilterEqCtgId(), maBdPointCommonDTO.getFilterEqCtgDesc(), maBdPointCommonDTO.getCompNo());
        	query.append("									))					");
        }
        //설비유형
        if(!"".equals(maBdPointCommonDTO.getFilterEqCtgTypeId())||!"".equals(maBdPointCommonDTO.getFilterEqCtgTypeDesc())){
        	query.append("AND y.wkor_id IN (SELECT a.wkor_id FROM TAWOEQUIP a		");
        	query.append("				WHERE a.equip_id IN (SELECT b.equip_id		");
        	query.append("									FROM TAEQUIPMENT b	");
        	query.append("									WHERE 1=1			");
        	query.getSysCdQuery("b.eqctg_type", maBdPointCommonDTO.getFilterEqCtgTypeId(), maBdPointCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", maBdPointCommonDTO.getCompNo(), user.getLangId());
            query.append("									))					");
        }
        query.getWkCtrLevelQuery("y.wkctr_id", maBdPointCommonDTO.getFilterWkCtrId(), maBdPointCommonDTO.getFilterWkCtrDesc(), user.getCompNo());

        //공장코드
        query.getCodeLikeQuery("y.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = y.plant )", 
        		maBdPointCommonDTO.getFilterPlantId(), maBdPointCommonDTO.getFilterPlantDesc());
        //설비번호
        query.getLikeQuery("c.item_no", maBdPointCommonDTO.getFilterItemNo());
        
        return query.toString();
    }

    //RIns 조건
    private String getWhereRIns(MaBdPointCommonDTO maBdPointCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String usageDept       = maBdPointCommonDTO.getFilterUsageDeptId();
        String usageDeptDesc   = maBdPointCommonDTO.getFilterUsageDeptDesc();
      
        query.getAndQuery("x.comp_no", maBdPointCommonDTO.getCompNo());
        
        query.getAndQuery("y.is_deleted", "N");
        query.getAndQuery("z.is_deleted", "N");
        query.getAndQuery("c.is_deleted", "N");
        
        //사용부서
        query.getDeptLevelQuery("c.usage_dept", usageDept, usageDeptDesc, user.getCompNo());
        
        //점검일자
        query.getAndDateQuery("x.actual_date", maBdPointCommonDTO.getFilterStartDate(), maBdPointCommonDTO.getFilterEndDate());
        //조치결과
        query.getCodeLikeQuery("x.pm_point_rep_status", "dbo.SFACODE_TO_DESC(x.pm_point_rep_status,'PM_POINT_REP_STATUS','SYS',x.comp_no,'"+user.getLangId()+"')", 
        		maBdPointCommonDTO.getFilterRepStatus(), maBdPointCommonDTO.getFilterRepStatusDesc());
        // 부서
        query.getDeptLevelQuery("y.dept_id", maBdPointCommonDTO.getFilterDeptId(), maBdPointCommonDTO.getFilterDeptDesc(), maBdPointCommonDTO.getCompNo());
        //작업자
        query.getCodeLikeQuery("y.emp_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = y.comp_no AND a.emp_id = y.emp_id)", 
        		maBdPointCommonDTO.getFilterEmpId(), maBdPointCommonDTO.getFilterEmpName());
        //위치
        if(!"".equals(maBdPointCommonDTO.getFilterEqLocId())||!"".equals(maBdPointCommonDTO.getFilterEqLocDesc())){
        	query.append("AND  y.equip_id IN (SELECT a.equip_id	");
        	query.append("					  FROM TAEQUIPMENT a");
        	query.append("					  WHERE 1=1			");
        	query.getEqLocLevelQuery("a.eqloc_id", maBdPointCommonDTO.getFilterEqLocId(), maBdPointCommonDTO.getFilterEqLocDesc(), maBdPointCommonDTO.getCompNo());
        	query.append("									)	");
        }
        //종류
        if(!"".equals(maBdPointCommonDTO.getFilterEqCtgId())||!"".equals(maBdPointCommonDTO.getFilterEqCtgDesc())){
        	query.append("AND y.equip_id IN (SELECT a.equip_id	");
        	query.append("					 FROM TAEQUIPMENT a	");
        	query.append("					 WHERE 1=1			");
        	query.getEqLocLevelQuery("a.eqctg_id", maBdPointCommonDTO.getFilterEqCtgId(), maBdPointCommonDTO.getFilterEqCtgDesc(), maBdPointCommonDTO.getCompNo());
        	query.append("									)	");
        }
        //설비유형
        if(!"".equals(maBdPointCommonDTO.getFilterEqCtgTypeId())||!"".equals(maBdPointCommonDTO.getFilterEqCtgTypeDesc())){
        	query.append("AND y.equip_id IN (SELECT a.equip_id	");
        	query.append("					 FROM TAEQUIPMENT a	");
        	query.append("					 WHERE 1=1			");
        	query.getSysCdQuery("a.eqctg_type", maBdPointCommonDTO.getFilterEqCtgTypeId(), maBdPointCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", maBdPointCommonDTO.getCompNo(), user.getLangId());
            query.append("									)	");
        }
        query.getWkCtrLevelQuery("y.wkctr_id", maBdPointCommonDTO.getFilterWkCtrId(), maBdPointCommonDTO.getFilterWkCtrDesc(), user.getCompNo());
        
        //공장코드
        query.getCodeLikeQuery("y.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = y.plant )", 
        		maBdPointCommonDTO.getFilterPlantId(), maBdPointCommonDTO.getFilterPlantDesc());
        //설비번호
        query.getLikeQuery("c.item_no", maBdPointCommonDTO.getFilterItemNo());
        
        return query.toString();
    }
    

	@Override
	public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT SUM(SEQNO) FROM  (			                                                                            ");
        
        query.append("SELECT     count(1)                                                             AS SEQNO                    ");
        query.append("FROM TAWONGPOINT x INNER JOIN TAWORKORDER y                                                           ");
        query.append("        ON x.comp_no = y.comp_no AND x.wkor_id = y.wkor_id                                            ");
        query.append("INNER JOIN TAPMPOINT z                                                                                ");
        query.append("        ON x.comp_no = z.comp_no AND x.pm_point_id = z.pm_point_id                                    ");
        query.append("INNER JOIN TAWOEQUIP b                                                                                ");
        query.append("        ON y.wkor_id = b.wkor_id AND y.comp_no = b.comp_no                                            ");
        query.append("INNER JOIN TAEQUIPMENT c                                                                              ");
        query.append("        ON b.equip_id = c.equip_id AND b.comp_no = c.comp_no                                          ");
        query.append("WHERE 1 = 1                                                                                           ");
        query.append("AND x.pmi_type = 'INS'                                                                                ");
if("N".equals(maBdPointCommonDTO.getReloadRow())){
//        query.append("AND y.wo_status = 'C'                                                                                 ");
        query.append("AND x.pm_point_rslt_status = 'BD'                                                                     ");
        query.append(this.getWhereIns(maBdPointCommonDTO, user));
}
        query.append("UNION ALL");
        
        query.append("SELECT     count(1)                                                             AS SEQNO                    ");
        query.append("FROM TAWONGPOINT x INNER JOIN TAPMINSLIST y                                                           ");
        query.append("        ON x.comp_no = y.comp_no AND x.pminslist_id = y.pminslist_id                                  ");
        query.append("INNER JOIN TAPMPOINT z                                                                                ");
        query.append("        ON x.comp_no = z.comp_no AND x.pm_point_id = z.pm_point_id                                    ");
        query.append("INNER JOIN TAEQUIPMENT c                                                                              ");
        query.append("        ON y.equip_id = c.equip_id AND y.comp_no = c.comp_no                                          ");
        query.append("WHERE 1 = 1                                                                                           ");
        query.append("AND x.pmi_type = 'RINS'                                                                               ");
if("N".equals(maBdPointCommonDTO.getReloadRow())){
//        query.append("AND y.pmsched_status = 'C'                                                                            ");
        query.append("AND x.pm_point_rslt_status = 'BD'                                                                     ");
        query.append(this.getWhereRIns(maBdPointCommonDTO, user));
}
        query.append("UNION ALL");

        query.append("SELECT     count(1)                                                             AS SEQNO                    ");
        query.append("FROM TAWONGPOINT x INNER JOIN TAPMINSDLIST y                                                          ");
        query.append("        ON x.comp_no = y.comp_no AND x.pminsdlist_id = y.pminsdlist_id                                ");
        query.append("INNER JOIN TAPMPOINT z                                                                                ");
        query.append("        ON x.comp_no = z.comp_no AND x.pm_point_id = z.pm_point_id                                    ");
        query.append("INNER JOIN TAEQUIPMENT c                                                                              ");
        query.append("        ON y.equip_id = c.equip_id AND y.comp_no = c.comp_no                                          ");
        query.append("WHERE 1 = 1                                                                                           ");
        query.append("AND x.pmi_type = 'DINS'                                                                               ");
if("N".equals(maBdPointCommonDTO.getReloadRow())){
//        query.append("AND y.pmsched_status = 'C'                                                                            ");
        query.append("AND x.pm_point_rslt_status = 'BD'                                                                     ");
        query.append(this.getWhereRIns(maBdPointCommonDTO, user));
}
        query.append("UNION ALL");
        
        query.append("SELECT     count(1)                                                             AS SEQNO                    ");
        query.append("FROM TAWONGPOINT x INNER JOIN TAPMINSDLIST y                                                          ");
        query.append("        ON x.comp_no = y.comp_no AND x.pminsdlist_id = y.pminsdlist_id                                ");
        query.append("INNER JOIN TAPMPOINT z                                                                                ");
        query.append("        ON x.comp_no = z.comp_no AND x.pm_point_id = z.pm_point_id                                    ");
        query.append("INNER JOIN TAEQUIPMENT c                                                                              ");
        query.append("        ON y.equip_id = c.equip_id AND y.comp_no = c.comp_no                                          ");
        query.append("WHERE 1 = 1                                                                                           ");
        query.append("AND x.pmi_type = 'CINS'                                                                               ");
        if("N".equals(maBdPointCommonDTO.getReloadRow())){
//        	query.append("AND y.pmsched_status = 'C'                                                                            ");
        	query.append("AND x.pm_point_rslt_status = 'BD'                                                                     ");
        	query.append(this.getWhereRIns(maBdPointCommonDTO, user));
        }
        query.append("UNION ALL");
        
        query.append("SELECT     count(1)                                                             AS SEQNO                    ");
        query.append("FROM TAWONGPOINT x INNER JOIN TAPMPTRLRSLTLIST y                                                      ");
        query.append("        ON x.comp_no = y.comp_no AND x.pmptrlrsltlist_id = y.pmptrlrsltlist_id                        ");
        query.append("INNER JOIN TAPMPOINT z                                                                                ");
        query.append("        ON x.comp_no = z.comp_no AND x.pm_point_id = z.pm_point_id                                    ");
        query.append("INNER JOIN TAEQUIPMENT c                                                                              ");
        query.append("        ON y.equip_id = c.equip_id AND y.comp_no = c.comp_no                                          ");
        query.append("WHERE 1 = 1                                                                                           ");
        query.append("AND x.pmi_type = 'PINS'                                                                               ");
if("N".equals(maBdPointCommonDTO.getReloadRow())){
//        query.append("AND y.pmsched_status = 'C'                                                                            ");
        query.append("AND x.pm_point_rslt_status = 'BD'                                                                     ");
        query.append(this.getWhereRIns(maBdPointCommonDTO, user));
}

        query.append(")	w					");
        query.append("WHERE 1=1						");
        //query.append(this.getWhere(maBdPointCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
    
}