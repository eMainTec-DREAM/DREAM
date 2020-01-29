package dream.work.cal.pminsappr.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.cal.pminsappr.dao.WorkCalPmInsApprSchedListDAO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;

/**
 * 예방점검계획승인-점검결과 - 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workCalPmInsApprSchedListDAOTarget"
 * @spring.txbn id="workCalPmInsApprSchedListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalPmInsApprSchedListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkCalPmInsApprSchedListDAO
{
    public List findList(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                            ");
        query.append("       ''                                         AS seqNo                                        ");
        query.append("     , x.comp_no                                  AS compNo                                       ");
        query.append("     , pminsschedapprlist_id                      AS woplanapprlistId                             ");
        query.append("     , pminsschedappr_id                          AS woplanapprId                                 ");
        query.append("     , c.pminslist_id                             AS pminslistId                                  ");
        query.append("     , x.pminssched_id                            AS pminsschedId                                 ");
        query.append("     , x.sched_date                               AS schedDate                                    ");
        query.append("     , x.wkor_date                                AS wkorDate                                     ");
        query.append("     , z.description                              AS pmDesc                                       ");
        query.append("     , f.item_no                             		AS equipNo			                            ");
        query.append("     , f.description 								AS equipDesc									");
        query.append("     , x.cycle || (SELECT SFACODE_TO_DESC(x.period_type,'PERIOD_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL) AS cycle ");
        query.append("     , (SELECT  description                                                                       ");
        query.append("          FROM  TAEQUIPMENT A                                                                     ");
        query.append("         WHERE  A.comp_no  = f.comp_no                                                            ");
        query.append("           AND  A.equip_id = f.p_equip_id )       AS pequipDesc                                   ");
        query.append("     , (SELECT  A.item_no                                                                         ");
        query.append("          FROM  TAEQUIPMENT A                                                                     ");
        query.append("         WHERE  A.comp_no  = f.comp_no                                                            ");
        query.append("           AND  A.equip_id = f.p_equip_id )       AS pitemNo                                      ");
        query.append("FROM TAPMINSSCHEDAPPRLIST x INNER JOIN TAPMINSSCHED y ON x.comp_no = y.comp_no AND x.pminssched_id = y.pminssched_id        ");
        query.append("   INNER JOIN TAPMLST z ON y.comp_no = z.comp_no AND y.pm_id = z.pm_id                                                      ");
        query.append("   LEFT OUTER JOIN TAPMINSLIST c ON y.comp_no = c.comp_no AND y.pminslist_id = c.pminslist_id                               ");
        query.append("   LEFT OUTER JOIN TAEQUIPMENT f ON y.comp_no = f.comp_no AND y.equip_id = f.equip_id                                       ");
        query.append("WHERE 1 = 1                                                                                       ");

        query.append(this.getWhere(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO,user));
        query.getOrderByQuery("x.sched_date", workCalPmInsApprCommonDTO.getOrderBy(), workCalPmInsApprCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workCalPmInsApprCommonDTO.getIsLoadMaxCount(), workCalPmInsApprCommonDTO.getFirstRow()));
    }
    
    private String getWhere(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user){
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.pminsschedappr_id", workCalPmInsApprCommonDTO.getPmInsSchedApprId());
    	query.getAndQuery("y.is_deleted", "N");

    	if(workCalPmInsApprDetailDTO.getPminsschedapprType().equals("ACT")){
    		query.append(" AND y.pmsched_status IN ('C')				");    		
    	}else if(workCalPmInsApprDetailDTO.getPminsschedapprType().equals("NOT")){
    		query.append(" AND y.pmsched_status NOT IN ('C')			");    		
    	} 
    	
        return query.toString();
    }
    
    public String findTotalCount(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT                ");
        query.append("       COUNT(1)       ");
        query.append("FROM TAPMINSSCHEDAPPRLIST x INNER JOIN TAPMINSSCHED y ON x.comp_no = y.comp_no AND x.pminssched_id = y.pminssched_id        ");
        query.append("   INNER JOIN TAPMLST z ON y.comp_no = z.comp_no AND y.pm_id = z.pm_id                                                      ");
        query.append("   LEFT OUTER JOIN TAPMINSLIST c ON y.comp_no = c.comp_no AND y.pminslist_id = c.pminslist_id                               ");
        query.append("   LEFT OUTER JOIN TAEQUIPMENT f ON y.comp_no = f.comp_no AND y.equip_id = f.equip_id                                       ");
        query.append("WHERE 1 = 1                                                                                       ");

        query.append(this.getWhere(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

    @Override
    public void deletePmInsSchedApprList( WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAPMINSSCHEDAPPRLIST            ");
        query.append("WHERE  comp_no                 = ?     ");
        query.append("AND    pminsschedappr_id       = ?     ");
        Object[] objects = new Object[]{
                user.getCompNo()
                ,workCalPmInsApprDetailDTO.getPmInsSchedApprId()
        };

        int result = this.getJdbcTemplate().update(query.toString(),objects);
    }

    @Override
    public void insertPmInsSchedApprList(
            WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,
            WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAPMINSSCHEDAPPRLIST(comp_no, pminsschedapprlist_id, pminsschedappr_id, pminssched_id, sched_date, equip_id, cycle, period_type, pm_id)     ");
        query.append("SELECT y.comp_no,                                             ");
        query.append("       sqapminsschedapprlist_id.NEXTVAL,                      ");
        query.append("       ?,                                                     ");
        query.append("       x.pminssched_id,                                       ");
        query.append("       x.sched_date                                           ");
        query.append(" 		 ,z.equip_id											");
        query.append(" 		 ,y.cycle												");
        query.append(" 		 ,y.period_type											");
        query.append(" 		 ,x.pm_id												");
        query.append("FROM TAPMINSSCHED x INNER JOIN TAPMLST y ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id       ");
        query.append("INNER JOIN TAPMEQUIP z                                                                        ");
        query.append("ON z.pmequip_id = x.pmequip_id AND z.comp_no = x.comp_no                                      ");
        query.append("INNER JOIN TAEQUIPMENT d ON z.equip_id = d.equip_id and z.comp_no = d.comp_no                 ");
        query.append("WHERE 1=1                                                                                     ");
        query.append("  AND  x.comp_no= ?                                           ");
        query.append("  AND  x.sched_date >= ?                                      ");
        query.append("  AND  x.sched_date <= ?                                      ");
        query.append("  AND  y.plant = ?                                            ");
        query.append("  AND  d.is_last_version ='Y'                                 ");
        query.getDeptLevelQuery("y.dept_id", workCalPmInsApprDetailDTO.getDeptId(), "", user.getCompNo());
        query.getAndQuery("x.pmsched_status", workCalPmInsApprCommonDTO.getPmschedStatus());

        Object[] objects = new Object[] {
                workCalPmInsApprDetailDTO.getPmInsSchedApprId(),
                user.getCompNo(),
                workCalPmInsApprDetailDTO.getStartDate(),
                workCalPmInsApprDetailDTO.getEndDate(),
                workCalPmInsApprDetailDTO.getPlantId()
                
        };
        
        this.getJdbcTemplate().update(query.toString(), objects);
    }
}