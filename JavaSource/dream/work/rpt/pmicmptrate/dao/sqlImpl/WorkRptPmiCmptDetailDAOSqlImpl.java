package dream.work.rpt.pmicmptrate.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.pmicmptrate.dao.WorkRptPmiCmptDetailDAO;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptDetailDTO;

/**
 * 고장TOP(위치) 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptPmiCmptDetailDAOTarget"
 * @spring.txbn id="workRptPmiCmptDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmiCmptDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptPmiCmptDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiCmptDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptPmiCmptDetailDTO workRptPmiCmptDetailDTO, User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     						");
        query.append("SELECT        																");
        query.append("    ''       											AS SEQNO				");
        query.append("    ,a.pminslist_id									AS pminslistNo			");
        query.append("    ,a.description        							AS description			");
        query.append("    ,b.sched_date     								AS schedDate			");
        query.append("    ,(CASE WHEN a.pmsched_status IN ('C', 'PRC') THEN a.end_date ELSE '' END)									AS endDate		");
        query.append("    ,(SELECT aa.item_no FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id) 		AS itemNo		");
        query.append("    ,(SELECT aa.description FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id)	AS itemDesc		");
        query.append("    ,(SELECT aa.description FROM TADEPT aa WHERE a.comp_no = aa.comp_no AND a.dept_id = aa.dept_id) 			AS deptDesc		");
        query.append("    ,(SELECT aa.emp_name FROM TAEMP aa WHERE a.comp_no = aa.comp_no AND a.emp_id = aa.emp_id) 				AS empName		");
        query.append("    ,d.eqasmb_desc    								AS eqAsmbDesc			");
        query.append("    ,d.check_point    								AS 'checkPoint'			");
        query.append("    ,d.fit_basis      								AS fitBasis				");
        query.append("    ,c.pm_point_rslt_status       					AS pmPointRsltStatus	");
        query.append("    ,dbo.SFACODE_TO_DESC(c.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS','','"+loginUser.getLangId()+"')	AS pmPointRsltStatusDesc	");
        query.append("    ,c.result_value       							AS resultValue			");
        query.append("    ,c.remark     									AS remark				");
        query.append("    ,a.equip_id										AS equipId 				");
        query.append("    ,(SELECT aa.eqctg_type FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id) 	AS eqCtgType	");
        query.append("FROM TAPMINSLIST a INNER JOIN TAPMINSSCHED b 									");
        query.append("ON a.comp_no = b.comp_no AND a.pminssched_id = b.pminssched_id       			");
        query.append("	INNER JOIN TAPMINSPOINT c        											");
        query.append("	ON b.comp_no = c.comp_no AND b.pminslist_id = c.pminslist_id       			");
        query.append("		INNER JOIN tapmpoint d        											");
        query.append("		ON c.comp_no = d.comp_no AND c.pm_point_id = d.pm_point_id        		");
        query.append("WHERE 1=1     																");
        query.append(this.getWhere(workRptPmiCmptDetailDTO,loginUser));
        query.getOrderByQuery("a.comp_no", "b.sched_date ASC", workRptPmiCmptDetailDTO.getOrderBy(), workRptPmiCmptDetailDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPmiCmptDetailDTO.getIsLoadMaxCount(), workRptPmiCmptDetailDTO.getFirstRow()));
    }
    
    public String getWhere(WorkRptPmiCmptDetailDTO workRptPmiCmptDetailDTO,User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getStringEqualQuery("a.comp_no", loginUser.getCompNo());
        
        query.getStringEqualQuery("a.is_deleted", "N");
                
        query.getAndQuery("a.plant", workRptPmiCmptDetailDTO.getPlantId());
        
        // 월
        String yyyymm = workRptPmiCmptDetailDTO.getYyyymm();
        
        if (yyyymm != null && !"".equals(yyyymm))
        {
            // toMonth가 오늘이후면 오늘까지만 조회
            if(DateUtil.compareDate(DateUtil.getDate(), DateUtil.plusLastDayOfMonth(yyyymm)))
            {
                query.getAndDateQuery("b.sched_date", yyyymm +"01", DateUtil.getDate());
            }
            else{
                query.getAndDateQuery("b.sched_date", yyyymm +"01", DateUtil.plusLastDayOfMonth(yyyymm));
            }
        }
        
        // 예방작업설비 삭제여부
        query.append("AND EXISTS (SELECT aa.equip_id                    ");
        query.append("            FROM TAPMEQUIP aa INNER JOIN TAEQUIPMENT bb   ");
        query.append("            ON aa.comp_no = bb.comp_no            ");
        query.append("            AND aa.equip_id = bb.equip_id         ");
        query.append("            WHERE 1=1                             ");
        query.append("             AND aa.comp_no = b.comp_no           ");
        query.append("              AND aa.pmequip_id = b.pmequip_id    ");
        query.getAndQuery("aa.is_deleted", "N");
        query.getAndQuery("bb.is_deleted", "N");
        query.append("           )                                      ");
        
        
        return query.toString();
    }
    
    public String findTotalCount(WorkRptPmiCmptDetailDTO workRptPmiCmptDetailDTO, User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;      			");
        query.append("SELECT                                           					");
        query.append("    COUNT(1)                                     					");
        query.append("FROM TAPMINSLIST a INNER JOIN TAPMINSSCHED b  					");
        query.append("ON a.comp_no = b.comp_no AND a.pminssched_id = b.pminssched_id   	");
        query.append("	INNER JOIN TAPMINSPOINT c         								");
        query.append("	ON b.comp_no = c.comp_no AND b.pminslist_id = c.pminslist_id  	");
        query.append("		INNER JOIN tapmpoint d        								");
        query.append("		ON c.comp_no = d.comp_no AND c.pm_point_id = d.pm_point_id	");
        query.append("WHERE 1=1      								 					");
        query.append(this.getWhere(workRptPmiCmptDetailDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

}