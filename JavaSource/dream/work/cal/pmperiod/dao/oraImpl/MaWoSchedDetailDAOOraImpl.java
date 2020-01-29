package dream.work.cal.pmperiod.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.cal.pmperiod.dao.MaWoSchedDetailDAO;
import dream.work.cal.pmperiod.dto.MaWoSchedDetailDTO;

/**
 * 예방작업일정(기간) - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaWoSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maWoSchedDetailDAOTarget"
 * @spring.txbn id="maWoSchedDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoSchedDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoSchedDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public MaWoSchedDetailDTO findDetail(String pmSchedId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT																			");
        query.append("       x.pmsched_id pmSchedId,      												");
        query.append("       x.plan_date planDate,														");
        query.append("       x.sched_date schedDate,													");
        query.append("       y.pm_id pmId,        														");
        query.append("       y.pm_no pmNo,        														");
        query.append("       y.description pmDesc,														");
        query.append("       (SELECT description                                                        ");
        query.append("          FROM TAEQUIPMENT                                                        ");
        query.append("         WHERE comp_no = x.comp_no                                                ");
        query.append("           AND equip_id = x.equip_id )                        AS equipDesc,       ");
        query.append("       (SELECT description														");
        query.append("          FROM TADEPT																");
        query.append("         WHERE comp_no = y.comp_no												");
        query.append("           AND dept_id = y.dept_id) deptDesc,										");
        query.append("       SFACODE_TO_DESC(y.pm_type,y.wo_type||'_TYPE','SYS','','"+user.getLangId()+"') pmType,						");
        query.append("       SFACODE_TO_DESC(y.schedule_type,'SCHEDULE_TYPE','SYS','','"+user.getLangId()+"') scheduleType,		");
        query.append("       y.cycle cycle,																");
        query.append("       y.period_type periodType,													");
        query.append("       x.pmsched_status pmSchedStatus,											");
        query.append("       x.remark remark,															");
        query.append("       y.USAGE usage,																");
        query.append("       x.wkor_id wkorId															");
        query.append("       ,(SELECT A.param2 															");
        query.append("       	FROM TACDSYSD A 														");
        query.append("       	WHERE A.cdsysd_no=y.pm_type												");
        query.append("       	 AND A.list_type=y.wo_type||'_TYPE' )        		AS param			");
        query.append("FROM TAPMSCHED x INNER JOIN TAPMLST y												");
        query.append("ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id									");
        query.append("WHERE x.pmsched_id = '"+pmSchedId+"'											    ");
        query.append("  AND x.comp_no    = '"+compNo+"'												    ");
    
        MaWoSchedDetailDTO maWoSchedDetailDTO = 
        		(MaWoSchedDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaWoSchedDetailDTO()));
        
        return maWoSchedDetailDTO;
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoSchedDetailDTO
     * @return
     */
    public int inputDetail(MaWoSchedDetailDTO maWoSchedDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAPMSCHED (                               	");
    	query.append("  comp_no,    pmsched_id,  pm_id,   plan_init_date,		");
    	query.append("  plan_date, pmsched_status,       sched_date    			");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,         SQAPMSCHED_ID.NEXTVAL, ?,            ?,              ");
    	query.append("  ?,         ?,             ?            					");
    	query.append(")                                                         ");
    	
    	Object[] objects = new Object[] {
    			maWoSchedDetailDTO.getCompNo(),
    			maWoSchedDetailDTO.getPmId(),
    			maWoSchedDetailDTO.getSchedDate(),
    			maWoSchedDetailDTO.getSchedDate(),
    			"P",
    			maWoSchedDetailDTO.getSchedDate()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
}