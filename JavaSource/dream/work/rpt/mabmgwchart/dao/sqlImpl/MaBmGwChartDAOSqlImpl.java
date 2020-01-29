package dream.work.rpt.mabmgwchart.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.mabmgwchart.dao.MaBmGwChartDAO;
import dream.work.rpt.mabmgwchart.dto.MaBmGwChartDTO;

/**
 * 과별고장분석 DAO
 * @author  kim21017
 * @version $Id: MaBmGwChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maBmGwChartDAOTarget"
 * @spring.txbn id="maBmGwChartDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBmGwChartDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaBmGwChartDAO
{
    /**
     * 과별종합분석 find grid
     * @author  kim21017
     * @version $Id: MaBmGwChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmGwChartDTO
     * @return List
     */
    public List findBmList(MaBmGwChartDTO maBmGwChartDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String yyyy = maBmGwChartDTO.getFilterYyyy().substring(0, 4);
        String lang = maBmGwChartDTO.getUserLang();

        query.append("SELECT deptId,deptDesc													");
        query.append("		 ,CNT01,TIME01														");
        query.append("		 ,CNT02,TIME02														");
        query.append("		 ,CNT03,TIME03														");
        query.append("		 ,CNT04,TIME04														");
        query.append("		 ,CNT05,TIME05														");
        query.append("		 ,CNT06,TIME06														");
        query.append("		 ,CNT07,TIME07														");
        query.append("		 ,CNT08,TIME08														");
        query.append("		 ,CNT09,TIME09														");
        query.append("		 ,CNT10,TIME10														");
        query.append("		 ,CNT11,TIME11														");
        query.append("		 ,CNT12,TIME12														");
        query.append("		 ,CNT,TIME															");
        query.append("FROM (																	");
        query.append("SELECT TOP 100 PERCENT													");
        query.append("		x.dept_id+''	AS deptId,											");
        query.append("		(SELECT description FROM TADEPT WHERE dept_id=x.dept_id) AS deptDesc");
        //과별
        for (int i = 1; i <= 12; i++) {
			String month = i<10?"0"+i:i+"";
			query.append(",ISNULL(SUM(CASE SUBSTRING(x.start_date,1,6) WHEN '"+yyyy+month+"' THEN 1 END),0) AS CNT"+month);
	        query.append(",ROUND(ISNULL(SUM(CASE SUBSTRING(x.start_date,1,6) WHEN '"+yyyy+month+"' THEN work_time END),0)/60,2) AS TIME"+month);
		}
        //합계
        query.append(",ISNULL(SUM(CASE SUBSTRING(x.start_date,1,4) WHEN '"+yyyy+"' THEN 1 END),0) AS CNT		");
        query.append(",ROUND(ISNULL(SUM(CASE SUBSTRING(x.start_date,1,4) WHEN '"+yyyy+"' THEN work_time END),0)/60,2) AS TIME");
        query.append("FROM TAWORKORDER x, 														");
        query.append("		(SELECT b.eqloc_id eqloc_id											");
        query.append("				,b.eqctg_id eqctg_id										");
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
        query.append("		AND a.comp_no = '"+maBmGwChartDTO.getCompNo()+"') y					");
        query.append("WHERE x.wkor_id = y.wkor_id												");
        query.append("  AND x.comp_no = y.comp_no												");
        query.append("  AND x.wo_type='BM'														");
        query.append("  AND x.wo_status = 'C'													");

        query.append(this.getWhere(maBmGwChartDTO));
        query.append("GROUP BY x.dept_id														");
        query.append("ORDER BY x.dept_id														");
        query.append(")	AS SUB																	");
        query.append("UNION ALL																	");
        query.append("SELECT																	");
        query.append("		''	AS deptId,														");
        query.append("		ISNULL(((SELECT key_name FROM TALANG WHERE key_no='total2' AND key_type='LABEL' AND lang='"+lang+"')),'계') AS deptDesc");
        //계
        for (int i = 1; i <= 12; i++) {
			String month = i<10?"0"+i:i+"";
			query.append(",ISNULL(SUM(CASE SUBSTRING(x.start_date,1,6) WHEN '"+yyyy+month+"' THEN 1 END),0) AS CNT"+month);
	        query.append(",ROUND(ISNULL(SUM(CASE SUBSTRING(x.start_date,1,6) WHEN '"+yyyy+month+"' THEN work_time END),0)/60,2) AS TIME"+month);
		}
        //합계
        query.append(",ISNULL(SUM(CASE SUBSTRING(x.start_date,1,4) WHEN '"+yyyy+"' THEN 1 END),0) AS CNT		");
        query.append(",ROUND(ISNULL(SUM(CASE SUBSTRING(x.start_date,1,4) WHEN '"+yyyy+"' THEN work_time END),0)/60,2) AS TIME");
        query.append("FROM TAWORKORDER x, 														");
        query.append("		(SELECT b.eqloc_id eqloc_id											");
        query.append("				,b.eqctg_id eqctg_id										");
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
        query.append("		AND a.comp_no = '"+maBmGwChartDTO.getCompNo()+"') y					");
        query.append("WHERE x.wkor_id = y.wkor_id												");
        query.append("  AND x.comp_no = y.comp_no												");
        query.append("  AND x.wo_type='BM'														");
        query.append("  AND x.wo_status = 'C'                                                   ");
        query.append(this.getWhere(maBmGwChartDTO));
        
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 과별종합분석 find chart
     * @author  kim21017
     * @version $Id: MaBmGwChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmGwChartDTO
     * @return List
     */
    public List findBmChart(MaBmGwChartDTO maBmGwChartDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String yyyy   = maBmGwChartDTO.getYyyy().substring(0, 4);
        String lang   = maBmGwChartDTO.getUserLang();
        String deptId = maBmGwChartDTO.getDeptId();
        String filterDeptId = maBmGwChartDTO.getFilterDeptId();
        String filterDeptDesc = maBmGwChartDTO.getFilterDeptDesc();

        query.append("SELECT ROW_NUMBER() OVER(ORDER BY MONTH) AS ID			");
        query.append("		  ,MONTH+(SELECT key_name							");
        query.append("					FROM TALANG								");
        query.append("					WHERE lang='"+lang+"'					");
        query.append("					  AND key_no='month') AS MONTH			");
        query.append("		  ,cnt AS CNT										");
        query.append("FROM														");
        query.append("(SELECT SUBSTRING(x.tmonth,5,2) AS MONTH						");
        query.append("		  ,(SELECT count(1)									");
        query.append("FROM TAWORKORDER a,										");
        query.append("		(SELECT b.eqloc_id eqloc_id											");
        query.append("				,b.eqctg_id eqctg_id										");
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
        query.append("		AND a.comp_no = '"+maBmGwChartDTO.getCompNo()+"') b					");
        query.append("WHERE a.wkor_id = b.wkor_id								");
        query.append("  AND a.comp_no = b.comp_no								");
        query.append("	AND a.wo_type='BM'										");
        query.append("  AND a.wo_status = 'C'									");
        query.append("			  AND a.comp_no='"+maBmGwChartDTO.getCompNo()+"'");
        query.append("			  AND SUBSTRING(a.start_date,1,6)=x.tmonth			");
        if(!"".equals(deptId)){
        	query.getDeptLevelQuery("a.dept_id", deptId,"", maBmGwChartDTO.getCompNo());
        }else{
        	query.getDeptLevelQuery("a.dept_id", filterDeptId,filterDeptDesc, maBmGwChartDTO.getCompNo());
        }
      //과
        //query.append("			  AND dept_id IN (SELECT dept_id FROM TADEPT WHERE comp_no = a.comp_no AND dept_categ='OG')");
        query.append("			) AS CNT										");
        query.append("	FROM TADAY x											");
        query.append("	WHERE x.tyyyy =	'"+yyyy+"'								");
        query.append("	GROUP BY x.tmonth										");
        query.append("	ORDER BY x.tmonth										");
        query.append(")															");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaBmGwChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmGwChartDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaBmGwChartDTO maBmGwChartDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", maBmGwChartDTO.getCompNo());
        query.getAndQuery("SUBSTRING(x.start_date,1,4)", maBmGwChartDTO.getFilterYyyy().substring(0, 4));
      //담당부서
        //query.append("AND x.dept_id IN (SELECT dept_id FROM TADEPT WHERE comp_no = x.comp_no AND dept_categ='OG')");
        query.getDeptLevelQuery("x.dept_id", maBmGwChartDTO.getFilterDeptId(),maBmGwChartDTO.getFilterDeptDesc(), maBmGwChartDTO.getCompNo());
      //위치
        query.getEqLocLevelQuery("y.eqloc_id", maBmGwChartDTO.getFilterEqLocId(), maBmGwChartDTO.getFilterEqLocDesc(), maBmGwChartDTO.getCompNo());
        //종류
        query.getEqCtgLevelQuery("y.eqctg_id", maBmGwChartDTO.getFilterEqCtgId(), maBmGwChartDTO.getFilterEqCtgDesc(), maBmGwChartDTO.getCompNo());
      //관리자(정)
        query.getCodeLikeQuery("y.main_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = y.main_mng_id AND aa.comp_no='"+maBmGwChartDTO.getCompNo()+"')", 
        		maBmGwChartDTO.getFilterMainMngId(), maBmGwChartDTO.getFilterMainMngName());
        //관리자(부)
        query.getCodeLikeQuery("y.sub_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = y.sub_mng_id AND aa.comp_no='"+maBmGwChartDTO.getCompNo()+"')", 
        		maBmGwChartDTO.getFilterSubMngId(), maBmGwChartDTO.getFilterSubMngName());
      //법정설비
        query.getLikeQuery("y.is_law_eq", maBmGwChartDTO.getFilterIsLawEq());
        //설비
        query.getCodeLikeQuery("y.main_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = y.main_mng_id AND aa.comp_no='"+maBmGwChartDTO.getCompNo()+"')", 
        		maBmGwChartDTO.getFilterMainMngId(), maBmGwChartDTO.getFilterMainMngName());
        //설비
    	query.getCodeLikeQuery("y.equip_id", 
    			"y.description", maBmGwChartDTO.getFilterEquipId(), maBmGwChartDTO.getFilterEquipDesc());
    	//공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
        		maBmGwChartDTO.getFilterPlantId(), maBmGwChartDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
}