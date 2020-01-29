package dream.work.rpt.mabmctgchart.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.mabmctgchart.dao.MaBmCtgChartDAO;
import dream.work.rpt.mabmctgchart.dto.MaBmCtgChartDTO;

/**
 * 설비종류별고장분석 DAO
 * @author  kim21017
 * @version $Id: MaBmCtgChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maBmCtgChartDAOTarget"
 * @spring.txbn id="maBmCtgChartDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBmCtgChartDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaBmCtgChartDAO
{
    /**
     * 설비종류별고장분석 find grid
     * @author  kim21017
     * @version $Id: MaBmCtgChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmCtgChartDTO
     * @return List
     */
    public List findBmList(MaBmCtgChartDTO maBmCtgChartDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String yyyy = maBmCtgChartDTO.getFilterYyyy().substring(0, 4);
        String lang = maBmCtgChartDTO.getUserLang();

        query.append("SELECT 																																					");
        query.append("		deptId,																																				");
        query.append("		CASE ISNULL(deptId, '') WHEN '' THEN (SELECT key_name FROM TALANG WHERE key_no='all' AND lang='ko') ELSE MAX(deptDesc) END deptDesc, 				");
        query.append("		eqlocId,																																			");
        query.append("		CASE ISNULL(eqlocId, '') WHEN '' THEN (CASE ISNULL(deptId, '') WHEN '' THEN '' ELSE (SELECT key_name from TALANG where key_no='total' and lang='ko') END) ELSE MAX(eqlocDesc) END eqlocDesc,	");
        query.append("		eqctgId,																																			");
        query.append("		CASE ISNULL(eqctgId, '') WHEN '' THEN (CASE ISNULL(eqlocId, '') WHEN '' THEN '' ELSE (SELECT key_name FROM TALANG WHERE key_no='subTotal' AND lang='ko') END) ELSE MAX(eqctgDesc) END eqctgDesc	");
        query.append("		 ,SUM(CNT01) AS CNT01, SUM(TIME01) AS TIME01																										");
        query.append("		 ,SUM(CNT02) AS CNT02, SUM(TIME02) AS TIME02																										");
        query.append("		 ,SUM(CNT03) AS CNT03, SUM(TIME03) AS TIME03																										");
        query.append("		 ,SUM(CNT04) AS CNT04, SUM(TIME04) AS TIME04																										");
        query.append("		 ,SUM(CNT05) AS CNT05, SUM(TIME05) AS TIME05																										");
        query.append("		 ,SUM(CNT06) AS CNT06, SUM(TIME06) AS TIME06																										");
        query.append("		 ,SUM(CNT07) AS CNT07, SUM(TIME07) AS TIME07  																										");
        query.append("		 ,SUM(CNT08) AS CNT08, SUM(TIME08) AS TIME08     																									");
        query.append("		 ,SUM(CNT09) AS CNT09, SUM(TIME09) AS TIME09																										");
        query.append("		 ,SUM(CNT10) AS CNT10, SUM(TIME10) AS TIME10   																										");
        query.append("		 ,SUM(CNT11) AS CNT11, SUM(TIME11) AS TIME11																										");
        query.append("		 ,SUM(CNT12) AS CNT12, SUM(TIME12) AS TIME12  																										");
        query.append("		 ,SUM(CNT) AS CNT, SUM(TIME) AS TIME																												");
        query.append("FROM (																																					");
        query.append("SELECT TOP 100 PERCENT																																					");
        query.append("		x.dept_id deptId,(SELECT description FROM TADEPT aa WHERE aa.comp_no = x.comp_no AND aa.dept_id = x.dept_id) deptDesc,												");
        query.append("		y.eqloc_id+''    AS eqlocId,																														");
        query.append("		(SELECT full_desc FROM TAEQLOC WHERE comp_no=x.comp_no AND eqloc_id= y.eqloc_id) AS eqlocDesc  ,													");
        query.append("		y.eqctg_id+''    AS eqctgId,																														");
        query.append("		(SELECT full_desc FROM TAEQCTG WHERE comp_no=x.comp_no AND eqctg_id= y.eqctg_id) AS eqctgDesc														");
        //설비종류별
        for (int i = 1; i <= 12; i++) {
			String month = i<10?"0"+i:i+"";
			query.append(",ISNULL(SUM(CASE SUBSTRING(x.start_date,1,6) WHEN '"+yyyy+month+"' THEN 1 END),0) AS CNT"+month);
	        query.append(",ROUND(ISNULL(SUM(CASE SUBSTRING(x.start_date,1,6) WHEN '"+yyyy+month+"' THEN work_time END),0)/60,2) AS TIME"+month);
		}
        //합계
        query.append(",ISNULL(SUM(CASE SUBSTRING(x.start_date,1,4) WHEN '"+yyyy+"' THEN 1 END),0) AS CNT		");
        query.append(",ROUND(ISNULL(SUM(CASE SUBSTRING(x.start_date,1,4) WHEN '"+yyyy+"' THEN work_time END),0)/60,2) AS TIME	");
        query.append("FROM TAWORKORDER x,														");
        query.append("		(SELECT b.eqloc_id eqloc_id											");
        query.append("				,b.eqctg_id eqctg_id										");
        query.append("              ,b.usage_dept usage_dept                                    ");
        query.append("				,b.comp_no comp_no											");
        query.append("				,a.wkor_id wkor_id											");
        query.append("				,b.equip_id equip_id										");
        query.append("				,b.main_mng_id main_mng_id									");
        query.append("				,b.sub_mng_id sub_mng_id									");
        query.append("				,b.is_law_eq is_law_eq										");
        query.append("				,b.description description									");
        query.append("		FROM TAWOEQUIP a, TAEQUIPMENT b										");
        query.append("		WHERE a.comp_no = b.comp_no 										");
        query.append("		AND a.equip_id = b.equip_id											");
        query.append("		AND a.comp_no = '"+maBmCtgChartDTO.getCompNo()+"') y				");
        query.append("WHERE x.comp_no = y.comp_no												");
        query.append("  AND x.wkor_id = y.wkor_id												");
        query.append("  AND (SELECT eqloc_id FROM TAEQLOC WHERE eqloc_id=y.eqloc_id AND comp_no=y.comp_no AND eqloc_lvl_type='L3') = y.eqloc_id									");
        query.append("  AND x.wo_type='BM'														");
        query.append("  and x.wo_status = 'C'                                                   ");
        query.append(this.getWhere(maBmCtgChartDTO, user));
        query.append("GROUP BY x.comp_no,x. dept_id, y.eqloc_id,eqctg_id									");
        query.append("ORDER BY (SELECT description FROM TAEQLOC WHERE comp_no=x.comp_no AND eqloc_id= y.eqloc_id)																");
        query.append("			,(SELECT description FROM TAEQCTG WHERE comp_no=x.comp_no AND eqctg_id= y.eqctg_id)																");
        query.append(")	AS TW																		");
        query.append("GROUP BY ROLLUP(deptId, eqlocId, eqctgId)											");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 설비종류별고장분석 find chart
     * @author  kim21017
     * @version $Id: MaBmCtgChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmCtgChartDTO
     * @return List
     */
    public List findBmChart(MaBmCtgChartDTO maBmCtgChartDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String yyyy   = maBmCtgChartDTO.getYyyy().substring(0, 4);
        String lang   = user.getLang();
        String eqlocId = maBmCtgChartDTO.getEqlocId();
        String eqctgId = maBmCtgChartDTO.getEqctgId();
        String deptId  = maBmCtgChartDTO.getDeptId();
        
        query.append("SELECT ROW_NUMBER() OVER(ORDER BY MONTH) AS ID			");
        query.append("		  ,MONTH+(SELECT key_name							");
        query.append("					FROM TALANG								");
        query.append("					WHERE lang='"+lang+"'					");
        query.append("					  AND key_no='month') AS MONTH			");
        query.append("		  ,cnt AS CNT										");
        query.append("FROM														");
        query.append("(SELECT TOP 100 PERCENT									");
        query.append("			SUBSTRING(x.tmonth,5,2) AS MONTH				");
        query.append("		  ,(SELECT count(1)									");
        query.append("			FROM TAWORKORDER a, 							");
        query.append("		(SELECT b.eqloc_id eqloc_id							");
        query.append("				,b.eqctg_id eqctg_id						");
        query.append("				,b.usage_dept usage_dept						");
        query.append("				,b.comp_no comp_no							");
        query.append("				,a.wkor_id wkor_id							");
        query.append("				,b.equip_id equip_id						");
        query.append("				,b.main_mng_id main_mng_id					");
        query.append("				,b.sub_mng_id sub_mng_id					");
        query.append("				,b.is_law_eq is_law_eq						");
        query.append("				,b.dept_id dept_id							");
        query.append("				,b.description description					");
        query.append("		FROM TAWOEQUIP a, TAEQUIPMENT b						");
        query.append("		WHERE a.comp_no = b.comp_no 						");
        query.append("		AND a.equip_id = b.equip_id							");
        query.append("		AND a.comp_no = '"+user.getCompNo()+"') b");
        query.append("			WHERE a.comp_no = b.comp_no						");
        query.append("			  AND a.wkor_id = b.wkor_id						");
        query.append("			  AND (SELECT eqloc_id FROM TAEQLOC WHERE eqloc_id=b.eqloc_id AND comp_no=b.comp_no AND eqloc_lvl_type='L3') = b.eqloc_id							");
        query.append("			  AND a.wo_type='BM' 							");
        query.append("            and a.wo_status = 'C'                         ");
        query.append("			  AND a.comp_no='"+user.getCompNo()+"'");
        query.append("			  AND SUBSTRING(a.start_date,1,6)=x.tmonth		");
        query.getDeptLevelQuery("b.usage_dept", maBmCtgChartDTO.getUsageDeptId(),maBmCtgChartDTO.getUsageDeptDesc(), user.getCompNo());
        query.getAndQuery("b.eqloc_id", eqlocId);
        query.getAndQuery("b.eqctg_id", eqctgId);
        query.getAndQuery("a.dept_id", deptId);
        query.append("			) AS CNT										");
        query.append("	FROM TADAY x											");
        query.append("	WHERE x.tyyyy =	'"+yyyy+"'								");
        query.append("	GROUP BY x.tmonth										");
        query.append("	ORDER BY x.tmonth										");
        query.append(")		AS TW												");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaBmCtgChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmCtgChartDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaBmCtgChartDTO maBmCtgChartDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("SUBSTRING(x.start_date,1,4)", maBmCtgChartDTO.getFilterYyyy().substring(0, 4));
        query.getDeptLevelQuery("x.dept_id", maBmCtgChartDTO.getFilterDeptId(),maBmCtgChartDTO.getFilterDeptDesc(), maBmCtgChartDTO.getCompNo());
        //위치
        query.getEqLocLevelQuery("y.eqloc_id", maBmCtgChartDTO.getFilterEqLocId(), maBmCtgChartDTO.getFilterEqLocDesc(), maBmCtgChartDTO.getCompNo());
        //종류
        query.getEqCtgLevelQuery("y.eqctg_id", maBmCtgChartDTO.getFilterEqCtgId(), maBmCtgChartDTO.getFilterEqCtgDesc(), maBmCtgChartDTO.getCompNo());
        //설비사용부서
        query.getDeptLevelQuery("y.usage_dept", maBmCtgChartDTO.getFilterUsageDeptId(),maBmCtgChartDTO.getFilterUsageDeptDesc(), maBmCtgChartDTO.getCompNo());
        //관리자(정)
        query.getCodeLikeQuery("y.main_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = y.main_mng_id AND aa.comp_no='"+maBmCtgChartDTO.getCompNo()+"')", 
        		maBmCtgChartDTO.getFilterMainMngId(), maBmCtgChartDTO.getFilterMainMngName());
        //관리자(부)
        query.getCodeLikeQuery("y.sub_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = y.sub_mng_id AND aa.comp_no='"+maBmCtgChartDTO.getCompNo()+"')", 
        		maBmCtgChartDTO.getFilterSubMngId(), maBmCtgChartDTO.getFilterSubMngName());
        //법정설비
        query.getLikeQuery("y.is_law_eq", maBmCtgChartDTO.getFilterIsLawEq());
        //설비
    	query.getCodeLikeQuery("y.equip_id", 
    			"y.description", maBmCtgChartDTO.getFilterEquipId(), maBmCtgChartDTO.getFilterEquipDesc());
    	//공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
        		maBmCtgChartDTO.getFilterPlantId(), maBmCtgChartDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
}