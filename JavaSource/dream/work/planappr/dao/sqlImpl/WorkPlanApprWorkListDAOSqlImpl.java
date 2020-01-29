package dream.work.planappr.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.planappr.dao.WorkPlanApprWorkListDAO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;

/**
 * 작업계획승인-점검작업 - 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workPlanApprWorkListDAOTarget"
 * @spring.txbn id="workPlanApprWorkListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPlanApprWorkListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPlanApprWorkListDAO
{
    public List findList(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  			");
        query.append("SELECT                                                                                ");
        query.append("       x.comp_no compNo,                                                              ");
        query.append("       woplanapprlist_id woplanapprlistId,                                            ");
        query.append("       woplanappr_id woplanapprId,                                                    ");
        query.append("       c.wkor_id wkorId,                                                              ");
        query.append("       x.pmsched_id pmschedId,                                                        ");
        query.append("       x.sched_date startDate,                                                        ");
        query.append("       x.wkor_date wkorDate,                                                          ");
        query.append("       z.description wkorDesc,                                                        ");
        query.append("       f.description equipDesc,                                                       ");
        query.append("       (SELECT description                                                            ");
        query.append("           FROM  TAEQUIPMENT a                                                        ");
        query.append("          WHERE  a.comp_no = f.comp_no                                                ");
        query.append("            AND  a.equip_id = f.p_equip_id )  pequipDesc,                             ");
        query.append("       (SELECT   a.item_no                                                            ");
        query.append("           FROM  TAEQUIPMENT a                                                        ");
        query.append("          WHERE  a.comp_no = f.comp_no                                                ");
        query.append("            AND  a.equip_id = f.p_equip_id )  pitemNo,                                ");
        query.append("       f.item_no                              equipNo                                 ");
        query.append("      ,f.usage_dept                           usageDeptId                             ");
        query.append("      ,(SELECT a.description                                                          ");
        query.append("         FROM TADEPT a                                                                ");
        query.append("        WHERE a.comp_no = f.comp_no                                                   ");
        query.append("          AND a.dept_id = f.usage_dept)       usageDeptDesc                           ");
        query.append("       ,z.CYCLE + dbo.SFACODE_TO_DESC(z.period_type,'PERIOD_TYPE','SYS','','"+user.getLangId()+"') CYCLE ");
        query.append("       ,y.sched_date   planCalDate                                					");
        query.append("       ,CASE z.period_type WHEN 'D' THEN dateadd(DAY,- 1,dateadd(DAY,z.CYCLE,y.sched_date))		");
        query.append("                           WHEN 'M' THEN dateadd(DAY,- 1,dateadd(MONTH,z.CYCLE, y.sched_date))	");
        query.append("                           WHEN 'W' THEN dateadd(DAY,- 1,dateadd(DAY,(z.CYCLE*7),y.sched_date))	");
        query.append("                           WHEN 'Y' THEN dateadd(DAY,- 1,dateadd(YEAR,z.CYCLE,y.sched_date))      ");
        query.append("                           END                nextPlanCalDate                            			");
        query.append("FROM   TAWOPLANAPPRLIST x LEFT OUTER JOIN TAPMSCHED y ON x.pmsched_id = y.pmsched_id  ");
        query.append("   LEFT OUTER JOIN TAPMLST z ON y.pm_id = z.pm_id                                     ");
        query.append("   LEFT OUTER JOIN TAWORKORDER c ON y.wkor_id = c.wkor_id                             ");
        query.append("   LEFT OUTER JOIN TAEQUIPMENT f ON x.equip_id = f.equip_id                           ");
        query.append("WHERE 1 = 1                                                                           ");
        query.append(this.getWhere(workPlanApprCommonDTO,workPlanApprDetailDTO,user));
        query.getOrderByQuery("x.woplanapprlist_id", "x.sched_date", workPlanApprCommonDTO.getOrderBy(), workPlanApprCommonDTO.getDirection());
        
        //return getJdbcTemplate().queryForList(query.toString());
        return getJdbcTemplate().queryForList(query.toString(workPlanApprCommonDTO.getIsLoadMaxCount(), workPlanApprCommonDTO.getFirstRow()));

    }
    
    private String getWhere(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getAndNumKeyQuery("x.woplanappr_id", workPlanApprCommonDTO.getWoPlanApprId());
        
        return query.toString();
    }
    
    public String findTotalCount(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT                ");
        query.append("       COUNT(1)       ");
        query.append("FROM TAWOPLANAPPRLIST x      ");
        query.append("WHERE  1=1            ");
        query.append(this.getWhere(workPlanApprCommonDTO,workPlanApprDetailDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

    @Override
    public void deleteWoPlanApprList(
            WorkPlanApprCommonDTO workPlanApprCommonDTO,
            WorkPlanApprDetailDTO workPlanApprDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAWOPLANAPPRLIST           ");
        query.append("WHERE  comp_no            = ?     ");
        query.append("AND    woplanappr_id      = ?     ");
        Object[] objects = new Object[]{
                user.getCompNo()
                ,workPlanApprDetailDTO.getWoPlanApprId()
        };

        int result = this.getJdbcTemplate().update(query.toString(),getObject(objects));
    }

    @Override
    public void insertWoPlanApprList(
            WorkPlanApprCommonDTO workPlanApprCommonDTO,
            WorkPlanApprDetailDTO workPlanApprDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAWOPLANAPPRLIST (comp_no, woplanapprlist_id, woplanappr_id, pmsched_id, sched_date, equip_id, cycle, period_type)  ");
        query.append("SELECT x.comp_no                                                                                  ");
        query.append("       ,sqawoplanapprlist_id.NEXTVAL                                                              ");
        query.append("       ,?                                                                                         ");
        query.append("       ,x.pmsched_id                                                                              ");
        query.append("       ,x.sched_date                                                                              ");
        query.append("       ,yy.equip_id                                                                               ");
        query.append("       ,y.cycle                                                                                   ");
        query.append("       ,y.period_type                                                                             ");
        query.append("FROM TAPMSCHED x INNER JOIN TAPMLST y                                                             ");
        query.append("  ON x.comp_no  = y.comp_no                                                                       ");
        query.append(" AND x.pm_id    = y.pm_id                                                                         ");
        query.append("INNER JOIN TAPMEQUIP yy                                                                           ");
        query.append("  ON yy.pm_id = y.pm_id                                                                           ");
        query.append(" AND yy.comp_no = y.comp_no                                                                       ");
        query.append(" AND yy.pmequip_id = x.pmequip_id                                                                 ");
        query.append("WHERE 1 = 1                                                                                       ");
        query.append("  AND  x.sched_date  >= ?                                                                         ");
        query.append("  AND x.sched_date <= ?                                                                           ");
        query.append("  AND  x.comp_no= ?                                                                               ");
        query.append("  AND  x.IS_DELETED='N'                                                                           ");
        query.append("  AND  y.IS_DELETED='N'                                                                           ");
        query.append("  AND  yy.IS_DELETED='N'                                                                          ");
        query.append("  AND y.wo_type = ?                                                                               ");
        query.append("  AND y.plant = ?                                                                                 ");
        query.append("  AND NOT EXISTS (SELECT A.equip_id                                                               ");
        query.append("                  FROM   TAEQUIPMENT A                                                            ");
        query.append("                  WHERE  1=1                                                                      ");
        query.append("                    AND  A.equip_id = x.equip_id                                                  ");
        query.append("                    AND  A.comp_no= ?                                                             ");
        query.append("                    AND  A.is_last_version = 'N'                                                  ");
        query.append("                 )                                                                                ");

        
        Object[] objects = new Object[] {
                workPlanApprDetailDTO.getWoPlanApprId(),
                workPlanApprDetailDTO.getStartDate(),
                workPlanApprDetailDTO.getEndDate(),
                user.getCompNo(),
                workPlanApprCommonDTO.getWoType(),
                workPlanApprDetailDTO.getPlantId(),
                user.getCompNo(),
                
        };
        
        this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}