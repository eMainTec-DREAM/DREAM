package dream.work.cal.pmmonth.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.cal.pmmonth.dao.MaWoMonthSchedDetailDAO;
import dream.work.cal.pmmonth.dto.MaWoMonthSchedDetailDTO;

/**
 * 월간예방일정 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaWoMonthSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maWoMonthSchedDetailDAOTarget"
 * @spring.txbn id="maWoMonthSchedDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoMonthSchedDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoMonthSchedDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoMonthSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public MaWoMonthSchedDetailDTO findDetail(String pmSchedId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT																			");
        query.append("       x.pmsched_id 											AS pmSchedId		");
        query.append("       ,x.plan_date 											AS planDate			");
        query.append("       ,x.sched_date 											AS schedDate		");
        query.append("       ,y.pm_id  												AS pmId        		");
        query.append("       ,y.pm_no  												AS pmNo        		");
        query.append("       ,y.description  										AS pmDesc			");
        query.append("		 ,(SELECT CASE WHEN COUNT(*)>1 												");
        query.append("				  THEN max(b.description)||( SELECT ' '||key_name||' ' 				");
        query.append("											 FROM TALANG 							");
        query.append("											 WHERE lang='"+user.getLangId()+"'		");
        query.append("				  							  AND key_no='aFew'						");
        query.append("				  							   AND key_type='LABEL'					");
        query.append("										   )||(COUNT(*)-1)							");
        query.append("				   ELSE max(b.description)											");
        query.append("				END  																");
        query.append("			FROM TAPMEQUIP a, TAEQUIPMENT b											");
        query.append("			WHERE a.comp_no = b.comp_no												");
        query.append("				AND a.equip_id = b.equip_id											");
        query.append("				AND a.pm_id = y.pm_id												");
        query.append("				AND a.comp_no = y.comp_no											");
        query.append("			GROUP By a.comp_no, a.pm_id	) 						AS equipDesc		");
        query.append("       ,(SELECT description														");
        query.append("         FROM TADEPT																");
        query.append("         WHERE comp_no = y.comp_no												");
        query.append("           AND dept_id = y.dept_id)  							AS deptDesc			");
        query.append("       ,SFACODE_TO_DESC(y.pm_type,y.wo_type||'_TYPE','SYS','','"+user.getLangId()+"')		AS pmType		");
        query.append("       ,SFACODE_TO_DESC(y.schedule_type,'SCHEDULE_TYPE','SYS','','"+user.getLangId()+"')	AS scheduleType	");
        query.append("       ,y.cycle  												AS cycle			");
        query.append("       ,y.period_type 		 								AS periodType		");
        query.append("       ,x.pmsched_status  									AS pmSchedStatus	");
        query.append("       ,x.remark  											AS remark			");
        query.append("       ,y.USAGE  												AS usage			");
        query.append("       ,x.wkor_id  											AS wkorId			");
        query.append("       ,(SELECT A.param2 															");
        query.append("       	FROM TACDSYSD A 														");
        query.append("       	WHERE A.cdsysd_no=y.pm_type												");
        query.append("       	 AND A.list_type=y.wo_type||'_TYPE' )        		AS param			");
        query.append("FROM TAPMSCHED x, TAPMLST y														");
        query.append("WHERE x.comp_no = y.comp_no														");
        query.append("  AND x.pm_id = y.pm_id															");
        query.append("  AND  x.pmsched_id 	= '"+pmSchedId+"'											");
        query.append("  AND  x.comp_no 		= '"+compNo+"'												");
    
        MaWoMonthSchedDetailDTO maWoMonthSchedDetailDTO = 
        		(MaWoMonthSchedDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaWoMonthSchedDetailDTO()));
        
        return maWoMonthSchedDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoMonthSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedDetailDTO
     * @return
     */
    public int updateDetail(MaWoMonthSchedDetailDTO maWoMonthSchedDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPMSCHED SET		");
    	query.append("	sched_date		= ?		");
    	query.append("	,remark 		= ?		");
    	query.append("WHERE pmsched_id 	= ?		");
    	query.append("  AND comp_no 	= ?		");
    	
    	Object[] objects = new Object[] {
    			maWoMonthSchedDetailDTO.getSchedDate(),
    			maWoMonthSchedDetailDTO.getRemark(),
    			maWoMonthSchedDetailDTO.getPmSchedId(),
    			maWoMonthSchedDetailDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}