package dream.work.rpt.mabmlnchart.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.mabmlnchart.dao.MaBmLnChartDAO;
import dream.work.rpt.mabmlnchart.dto.MaBmLnChartDTO;

/**
 * 라인고장분석 DAO
 * @author  kim21017
 * @version $Id: MaBmLnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maBmLnChartDAOTarget"
 * @spring.txbn id="maBmLnChartDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBmLnChartDAOOraImpl extends BaseJdbcDaoSupportOra implements MaBmLnChartDAO
{
    /**
     * 라인고장분석 find grid
     * @author  kim21017
     * @version $Id: MaBmLnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmLnChartDTO
     * @return List
     */
    public List findBmList(MaBmLnChartDTO maBmLnChartDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String yyyy = maBmLnChartDTO.getFilterYyyy().substring(0, 4);
        String lang = maBmLnChartDTO.getUserLang();

        query.append("SELECT deptDesc,eqlocId,eqlocDesc											");
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
        query.append("SELECT																	");
        query.append("		(SELECT aa.description FROM TADEPT aa WHERE aa.comp_no = x.comp_no AND aa.dept_id = x.dept_id) ||'' AS deptDesc,											");
        query.append("		y.eqloc_id||''    AS eqlocId,											");
        query.append("		(SELECT full_desc FROM TAEQLOC WHERE comp_no=x.comp_no AND eqloc_id= y.eqloc_id) AS eqlocDesc											");
        //과별
        for (int i = 1; i <= 12; i++) {
			String month = i<10?"0"+i:i+"";
			query.append(",NVL(SUM(DECODE(substr(x.start_date,0,6), '"+yyyy+month+"',1)),0) AS CNT"+month);
	        query.append(",ROUND(NVL(SUM(DECODE(substr(x.start_date,0,6), '"+yyyy+month+"',work_time)),0)/60,2) AS TIME"+month);
		}
        //합계
        query.append(",NVL(SUM(DECODE(substr(x.start_date,0,4), '"+yyyy+"',1)),0) AS CNT		");
        query.append(",ROUND(NVL(SUM(DECODE(substr(x.start_date,0,4), '"+yyyy+"',work_time)),0)/60,2) AS TIME");
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
        query.append("		AND a.comp_no = '"+maBmLnChartDTO.getCompNo()+"') y					");
        query.append("WHERE x.comp_no = y.comp_no												");
        query.append("  AND x.wkor_id = y.wkor_id												");
        query.append("  AND (SELECT eqloc_id FROM TAEQLOC WHERE eqloc_id=y.eqloc_id AND comp_no=y.comp_no AND is_lowest_lvl='Y') = y.eqloc_id														");
        query.append("  AND x.wo_type='BM'														");
        query.append("  and x.wo_status = 'C'													");
        query.append(this.getWhere(maBmLnChartDTO));
        query.append("GROUP BY x.comp_no,x.dept_id,y.eqloc_id									");
        query.append("ORDER BY y.eqloc_id														");
        query.append(")																			");
        query.append("UNION ALL																	");
        query.append("SELECT																	");
        query.append("		''	AS deptDesc,													");
        query.append("		''	AS eqlocId,														");
        query.append("		NVL(MAX((SELECT key_name FROM TALANG WHERE key_no='total2' AND key_type='LABEL' AND lang='"+lang+"')),'계') AS eqlocDesc");
        //계
        for (int i = 1; i <= 12; i++) {
			String month = i<10?"0"+i:i+"";
			query.append(",NVL(SUM(DECODE(substr(x.start_date,0,6), '"+yyyy+month+"',1)),0) AS CNT"+month);
	        query.append(",ROUND(NVL(SUM(DECODE(substr(x.start_date,0,6), '"+yyyy+month+"',work_time)),0)/60,2) AS TIME"+month);
		}
        //합계
        query.append(",NVL(SUM(DECODE(substr(x.start_date,0,4), '"+yyyy+"',1)),0) AS CNT		");
        query.append(",ROUND(NVL(SUM(DECODE(substr(x.start_date,0,4), '"+yyyy+"',work_time)),0)/60,2) AS TIME");
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
        query.append("		AND a.comp_no = '"+maBmLnChartDTO.getCompNo()+"') y					");
        query.append("WHERE x.comp_no = y.comp_no												");
        query.append("  AND x.wkor_id = y.wkor_id												");
        query.append("  AND (SELECT eqloc_id FROM TAEQLOC WHERE eqloc_id=y.eqloc_id AND comp_no=y.comp_no AND is_lowest_lvl='Y') = y.eqloc_id														");
        query.append("  AND x.wo_type='BM'														");
        query.append("  and x.wo_status = 'C'                                                   ");
        query.append(this.getWhere(maBmLnChartDTO));
        
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 라인고장분석 find chart
     * @author  kim21017
     * @version $Id: MaBmLnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmLnChartDTO
     * @return List
     */
    public List findBmChart(MaBmLnChartDTO maBmLnChartDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String yyyy   = maBmLnChartDTO.getYyyy().substring(0, 4);
        String lang   = maBmLnChartDTO.getUserLang();
        String eqlocId = maBmLnChartDTO.getEqlocId();
        String filterDeptId = maBmLnChartDTO.getFilterDeptId();
        String filterDeptDesc = maBmLnChartDTO.getFilterDeptDesc();

        query.append("SELECT ROW_NUMBER() OVER(ORDER BY MONTH) AS ID			");
        query.append("		  ,MONTH||(SELECT key_name							");
        query.append("					FROM TALANG								");
        query.append("					WHERE lang='"+lang+"'					");
        query.append("					  AND key_no='month') AS MONTH			");
        query.append("		  ,cnt AS CNT										");
        query.append("FROM														");
        query.append("(SELECT SUBSTR(x.tmonth,5,2) AS MONTH						");
        query.append("		  ,(SELECT count(1)									");
        query.append("			FROM TAWORKORDER a, 							");
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
        query.append("		AND a.comp_no = '"+maBmLnChartDTO.getCompNo()+"') b					");
        query.append("			WHERE a.comp_no = b.comp_no						");
        query.append("			  AND a.wkor_id = b.wkor_id						");
        query.append("			  AND (SELECT eqloc_id FROM TAEQLOC WHERE eqloc_id=b.eqloc_id AND comp_no=b.comp_no AND is_lowest_lvl='Y') = b.eqloc_id							");
        query.append("			  AND a.wo_type='BM' 							");
        query.append("            and a.wo_status = 'C'                         ");
        query.append("			  AND a.comp_no='"+maBmLnChartDTO.getCompNo()+"'");
        query.append("			  AND SUBSTR(a.start_date,0,6)=x.tmonth			");
        query.getDeptLevelQuery("a.dept_id", filterDeptId,filterDeptDesc, maBmLnChartDTO.getCompNo());
        query.getAndQuery("b.eqloc_id", eqlocId);
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
     * @version $Id: MaBmLnChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBmLnChartDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaBmLnChartDTO maBmLnChartDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("x.comp_no", maBmLnChartDTO.getCompNo());
        query.getAndQuery("substr(x.start_date,0,4)", maBmLnChartDTO.getFilterYyyy().substring(0, 4));
        query.getDeptLevelQuery("x.dept_id", maBmLnChartDTO.getFilterDeptId(),maBmLnChartDTO.getFilterDeptDesc(), maBmLnChartDTO.getCompNo());
      //위치
        query.getEqLocLevelQuery("y.eqloc_id", maBmLnChartDTO.getFilterEqLocId(), maBmLnChartDTO.getFilterEqLocDesc(), maBmLnChartDTO.getCompNo());
        //종류
        query.getEqCtgLevelQuery("y.eqctg_id", maBmLnChartDTO.getFilterEqCtgId(), maBmLnChartDTO.getFilterEqCtgDesc(), maBmLnChartDTO.getCompNo());
      //관리자(정)
        query.getCodeLikeQuery("y.main_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = y.main_mng_id AND aa.comp_no='"+maBmLnChartDTO.getCompNo()+"')", 
        		maBmLnChartDTO.getFilterMainMngId(), maBmLnChartDTO.getFilterMainMngName());
        //관리자(부)
        query.getCodeLikeQuery("y.sub_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = y.sub_mng_id AND aa.comp_no='"+maBmLnChartDTO.getCompNo()+"')", 
        		maBmLnChartDTO.getFilterSubMngId(), maBmLnChartDTO.getFilterSubMngName());
      //법정설비
        query.getLikeQuery("y.is_law_eq", maBmLnChartDTO.getFilterIsLawEq());
        //설비
        query.getCodeLikeQuery("y.main_mng_id", "(SELECT aa.emp_name FROM  TAEMP aa WHERE aa.emp_id = y.main_mng_id AND aa.comp_no='"+maBmLnChartDTO.getCompNo()+"')", 
        		maBmLnChartDTO.getFilterMainMngId(), maBmLnChartDTO.getFilterMainMngName());
        //설비
    	query.getCodeLikeQuery("y.equip_id", 
    			"y.description", maBmLnChartDTO.getFilterEquipId(), maBmLnChartDTO.getFilterEquipDesc());
    	//공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
        		maBmLnChartDTO.getFilterPlantId(), maBmLnChartDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
}