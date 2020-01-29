package dream.work.list.dept.sched.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.list.dept.sched.list.dao.WorkListDeptSchedListDAO;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;

/**
 * 업체별 작업스케줄 목록 dao
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workListDeptSchedListDAOTarget"
 * @spring.txbn id="workListDeptSchedListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListDeptSchedListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkListDeptSchedListDAO
{
	@Override
    public List findList(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																									");
        query.append("    ''                       	 															AS seqNo		");
        query.append("    ,x.dept_id                															AS deptId		");
        query.append("    ,(SELECT description FROM tadept WHERE comp_no = x.comp_no AND dept_id = x.dept_id) 	AS deptDesc		");
        query.append(this.makeDate(workListDeptSchedCommonDTO,user));
        query.append("FROM (		");
        query.append("    SELECT		");
        query.append("        b.tday, a.comp_no, a.dept_id, sum(CASE WHEN a.wkor_id IS NOT NULL THEN 1 ELSE 0 END) AS cnt		");
        query.append("    FROM taworkorder a INNER JOIN taday b		");
        query.append("    ON a.start_date<=b.tday		");
        query.append("    AND a.end_date>=b.tday		");
        query.append("    AND a.start_date IS NOT NULL		");
        query.append("    AND a.end_date IS NOT NULL		");
        query.append("    WHERE 1=1							");
        query.append(this.getWhere(workListDeptSchedCommonDTO,user));
        query.append("    GROUP BY b.tday, a.comp_no, a.dept_id		");
        query.append(") x		");
        query.append("GROUP BY x.comp_no, x.dept_id		");
        query.getOrderByQuery("x.dept_id", workListDeptSchedCommonDTO.getOrderBy(), workListDeptSchedCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workListDeptSchedCommonDTO.getIsLoadMaxCount(), workListDeptSchedCommonDTO.getFirstRow()));
    }
    
    public String makeDate(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();

        String startDate = workListDeptSchedCommonDTO.getFilterStartDate();
        String endDate = workListDeptSchedCommonDTO.getFilterEndDate();
        
        // 상세리스트에도 검색했을 당시에 그 조건 그대로 걸리도록하기위해서 hidden값으로 검색조건 넣어줌
        query.append("	,'"+startDate+"'										AS startDate	");
        query.append("	,'"+endDate+"'											AS endDate		");
        query.append("	,'"+workListDeptSchedCommonDTO.getFilterEqCtgId()+"'	AS eqCtgId		");
        query.append("	,'"+workListDeptSchedCommonDTO.getFilterEqCtgDesc()+"'	AS eqCtgDesc	");
        query.append("	,'"+workListDeptSchedCommonDTO.getFilterPlantId()+"'	AS plantId		");
        query.append("	,'"+workListDeptSchedCommonDTO.getFilterPlantDesc()+"'	AS plantDesc	");
        
		try {
			// 기간 동적으로 grid 생성
			String date = "";
			for (int i = 0; i < DateUtil.getDayInterval(startDate, endDate)+1; i++) {
				date = DateUtil.getAfterDays(startDate, i);
				query.append("    ,sum(CASE WHEN tday='"+date+"' THEN cnt ELSE 0 END) AS \""+date.substring(4,6)+"/"+date.substring(6)+"\"	");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        return query.toString();
    }
    
    public String getWhere(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO,User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getAndQuery("a.comp_no", user.getCompNo());
        query.getAndQuery("a.is_deleted", "N");
        
        // 기간
        query.getAndDateQuery("b.tday", workListDeptSchedCommonDTO.getFilterStartDate(), workListDeptSchedCommonDTO.getFilterEndDate());
        
        query.append(" AND	a.dept_id is not null	");
        
        // 공장
        query.getCodeLikeQuery("a.plant", "(SELECT aa.description FROM  TAPLANT aa WHERE aa.comp_no = '"+user.getCompNo()+"' AND aa.plant = a.plant )", 
        		workListDeptSchedCommonDTO.getFilterPlantId(), workListDeptSchedCommonDTO.getFilterPlantDesc());
        // 설비종류
        if(!"".equals(workListDeptSchedCommonDTO.getFilterEqCtgId()) ||!"".equals(workListDeptSchedCommonDTO.getFilterEqCtgDesc()))
        {
	        query.append("AND EXISTS  (SELECT aa.wkor_id         		");
	        query.append("             FROM TAWOEQUIP aa        		");
	        query.append("             WHERE aa.comp_no = a.comp_no     ");
	        query.append("             AND aa.wkor_id = a.wkor_id       ");
	        query.getCodeLikeQuery("aa.eqctg_id", "(SELECT a.description FROM  TAEQCTG a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.eqctg_id = aa.eqctg_id )", 
	        		workListDeptSchedCommonDTO.getFilterEqCtgId(), workListDeptSchedCommonDTO.getFilterEqCtgDesc());
	        query.append("                                )        		");
        }     
    	
    	
    	return query.toString();
    }

    @Override
    public String findTotalCount(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT count(1)																							");
        query.append("FROM	(																									");
        query.append("	SELECT																									");
        query.append("	    ''                       	 															AS seqNo	");
        query.append("	    ,x.dept_id                															AS deptId		");
        query.append("	    ,(SELECT description FROM tadept WHERE comp_no = x.comp_no AND dept_id = x.dept_id) 	AS deptDesc	");
        query.append(this.makeDate(workListDeptSchedCommonDTO,user));
        query.append("	FROM (																									");
        query.append("	    SELECT																								");
        query.append("	        b.tday, a.comp_no, a.dept_id, sum(CASE WHEN a.wkor_id IS NOT NULL THEN 1 ELSE 0 END) AS cnt		");
        query.append("	    FROM taworkorder a INNER JOIN taday b																");
        query.append("	    ON a.start_date<=b.tday																				");
        query.append("	    AND a.end_date>=b.tday																				");
        query.append("	    AND a.start_date IS NOT NULL																		");
        query.append("	    AND a.end_date IS NOT NULL																			");
        query.append("	    WHERE 1=1																							");
        query.append(this.getWhere(workListDeptSchedCommonDTO,user));
        query.append("	    GROUP BY b.tday, a.comp_no, a.dept_id																");
        query.append("	) x		");
        query.append("	GROUP BY x.comp_no, x.dept_id																			");
        query.append(")	aa																										");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}