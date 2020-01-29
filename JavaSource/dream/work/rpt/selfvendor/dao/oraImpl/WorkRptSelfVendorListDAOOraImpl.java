package dream.work.rpt.selfvendor.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.rpt.selfvendor.dao.WorkRptSelfVendorListDAO;
import dream.work.rpt.selfvendor.dto.WorkRptSelfVendorCommonDTO;

/**
 * 사내, 외주 작업 현황 Report - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptSelfVendorListDAOTarget"
 * @spring.txbn id="workRptSelfVendorListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptSelfVendorListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkRptSelfVendorListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptSelfVendorCommonDTO
     * @return List
     */
    public List findList(WorkRptSelfVendorCommonDTO workRptSelfVendorCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        String lang = user.getLangId();
        
        query.append("SELECT *																		");
        query.append("FROM (SELECT         															");
        query.append("     ''                                                 AS seqNo        		");
        query.append("    ,a.tmonth                                           AS yyyymm        		");
        query.append("    ,nvl(b.self_cnt,0)                                  AS selfCnt        	");
        query.append("    ,nvl(round((b.self_cnt / b.tot_cnt) * 100,0),0)     AS selfRate        	");
        query.append("    ,nvl(b.self_amt,0)                                  AS selfAmt        	");
        query.append("    ,nvl(b.vendor_cnt,0)                                AS vendorCnt        	");
        query.append("    ,nvl(round((b.vendor_cnt / b.tot_cnt) * 100,0),0)   AS vendorRate        	");
        query.append("    ,nvl(b.vendor_amt,0)                                AS vendorAmt        	");
        query.append("    ,nvl(b.unit_cnt,0)                                  AS unitCnt        	");
        query.append("    ,nvl(round((b.unit_cnt / b.tot_cnt) * 100,0),0)     AS unitRate        	");
        query.append("    ,nvl(b.unit_amt,0)                                  AS unitAmt        	");
        query.append("    ,nvl(b.tot_cnt,0)                                   AS totCnt        		");
        query.append("    ,nvl(b.tot_amt,0)                                   AS totAmt        		");
        query.append("FROM tamonth a LEFT OUTER JOIN        										");
        query.append("(  SELECT        																");
        query.append("         substr(a.wkor_date, 1,6) AS yyyymm        							");
        query.append("         ,sum(CASE WHEN a.self_vendor_type = 'SELF' THEN 1 ELSE 0 END)            AS SELF_CNT        		");
        query.append("         ,sum(CASE WHEN a.self_vendor_type = 'SELF' THEN a.tot_amt ELSE 0 END)    AS SELF_AMT        		");
        query.append("         ,sum(CASE WHEN a.self_vendor_type = 'VENDOR' THEN 1 ELSE 0 END)          AS VENDOR_CNT        	");
        query.append("         ,sum(CASE WHEN a.self_vendor_type = 'VENDOR' THEN a.tot_amt ELSE 0 END)  AS VENDOR_AMT        	");
        query.append("         ,sum(CASE WHEN a.self_vendor_type = 'UNIT' THEN 1 ELSE 0 END)            AS UNIT_CNT        		");
        query.append("         ,sum(CASE WHEN a.self_vendor_type = 'UNIT' THEN a.tot_amt ELSE 0 END)    AS UNIT_AMT        		");
        query.append("         ,sum(1) AS TOT_CNT        																		");
        query.append("         ,sum(a.tot_amt) AS TOT_AMT        																");
        query.append(" 	 FROM taworkorder a        																				");
        query.append("   WHERE 1=1        																						");
        query.append("  	AND a.self_vendor_type IS NOT NULL																	");
        query.append(this.getWhere(workRptSelfVendorCommonDTO, user));        
        query.append("   GROUP BY substr(a.wkor_date, 1,6)        																");
        query.append("     ) b ON a.tmonth = b.yyyymm        																	");
        query.append("WHERE 1=1        																							");
        
        if (workRptSelfVendorCommonDTO.getFilterStartDate() != null && !"".equals(workRptSelfVendorCommonDTO.getFilterStartDate()) 
        		&& workRptSelfVendorCommonDTO.getFilterEndDate() != null && !"".equals(workRptSelfVendorCommonDTO.getFilterEndDate()))
        {
        	query.append("AND a.tmonth >='"+workRptSelfVendorCommonDTO.getFilterStartDate()+"' AND a.tmonth <= '"+workRptSelfVendorCommonDTO.getFilterEndDate()+"'		");
        }
        
        query.append("ORDER BY a.tmonth        																					");
        query.append(")																											");
        
        query.append("UNION ALL																									");
        
        // 합계
        query.append("SELECT seqNo																");
        query.append("    ,yyyyMM																");
        query.append("    ,nvl(b.self_cnt,0)                                  AS selfCnt        ");
        query.append("    ,nvl(round((b.self_cnt / b.tot_cnt) * 100,0),0)     AS selfRate       ");
        query.append("    ,nvl(b.self_amt,0)                                  AS selfAmt        ");
        query.append("    ,nvl(b.vendor_cnt,0)                                AS vendorCnt      ");
        query.append("    ,nvl(round((b.vendor_cnt / b.tot_cnt) * 100,0),0)   AS vendorRate     ");
        query.append("    ,nvl(b.vendor_amt,0)                                AS vendorAmt      ");
        query.append("    ,nvl(b.unit_cnt,0)                                  AS unitCnt        ");
        query.append("    ,nvl(round((b.unit_cnt / b.tot_cnt) * 100,0),0)     AS unitRate       ");
        query.append("    ,nvl(b.unit_amt,0)                                  AS unitAmt        ");
        query.append("    ,nvl(b.tot_cnt,0)                                   AS totCnt        	");
        query.append("    ,nvl(b.tot_amt,0)                                   AS totAmt  		");
        query.append("FROM (SELECT        '' 								  											AS seqNo		");
        query.append("       ,NVL(MAX((SELECT key_name FROM TALANG WHERE key_no='total2' AND key_type='LABEL' AND lang='"+lang+"')),'계')	AS yyyyMM	");
        query.append("       ,sum(CASE WHEN a.self_vendor_type = 'SELF' THEN 1 ELSE 0 END)                  			AS SELF_CNT     ");
        query.append("       ,sum(CASE WHEN a.self_vendor_type = 'SELF' THEN a.tot_amt ELSE 0 END)      				AS SELF_AMT     ");
        query.append("       ,sum(CASE WHEN a.self_vendor_type = 'VENDOR' THEN 1 ELSE 0 END)             				AS VENDOR_CNT   ");
        query.append("       ,sum(CASE WHEN a.self_vendor_type = 'VENDOR' THEN a.tot_amt ELSE 0 END) 					AS VENDOR_AMT   ");
        query.append("       ,sum(CASE WHEN a.self_vendor_type = 'UNIT' THEN 1 ELSE 0 END)                  			AS UNIT_CNT     ");
        query.append("       ,sum(CASE WHEN a.self_vendor_type = 'UNIT' THEN a.tot_amt ELSE 0 END)      				AS UNIT_AMT     ");
        query.append("       ,sum(1) 																					AS TOT_CNT      ");
        query.append("       ,sum(a.tot_amt) 																			AS TOT_AMT      ");
        query.append("      FROM taworkorder a        		");
        query.append("      WHERE 1=1        		");
        query.append("       AND a.self_vendor_type IS NOT NULL        		");
        query.append(this.getWhere(workRptSelfVendorCommonDTO, user));     
        query.append("  ) b		");

        return getJdbcTemplate().queryForList(query.toString(workRptSelfVendorCommonDTO.getIsLoadMaxCount(), workRptSelfVendorCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     *   
     * @param workRptSelfVendorCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptSelfVendorCommonDTO workRptSelfVendorCommonDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        
        query.append("AND a.wo_status IN ('PRC','C')");
        
        //부서
        query.getDeptLevelQuery("a.dept_id", workRptSelfVendorCommonDTO.getFilterDeptId(), workRptSelfVendorCommonDTO.getFilterDeptDesc(), compNo);
        
        //공장
        query.getCodeLikeQuery("a.plant", "(SELECT x.description FROM  TAPLANT x WHERE x.comp_no = '"+compNo+"' AND a.plant = x.plant )", 
        		workRptSelfVendorCommonDTO.getFilterPlantId(), workRptSelfVendorCommonDTO.getFilterPlantDesc());
       
        // 일자
        String fromMonth = workRptSelfVendorCommonDTO.getFilterStartDate();
        String toMonth   = workRptSelfVendorCommonDTO.getFilterEndDate();
        if (fromMonth != null && !"".equals(fromMonth) && toMonth != null && !"".equals(toMonth))
        {
        	query.getAndDateQuery("a.wkor_date", fromMonth +"01", DateUtil.plusLastDayOfMonth(toMonth));
        }
 
        return query.toString();
    }
    

    public String findTotalCount(
            WorkRptSelfVendorCommonDTO workRptSelfVendorCommonDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT         											");
        query.append("     count(1)   											");
        query.append("FROM tamonth a LEFT OUTER JOIN        					");
        query.append("      (  SELECT        									");
        query.append("                     substr(a.wkor_date, 1,6) AS yyyymm	");
        query.append("            FROM taworkorder a        					");
        query.append("            WHERE 1=1        								");
        query.append("       		AND a.self_vendor_type IS NOT NULL        	");
        query.append(this.getWhere(workRptSelfVendorCommonDTO, user));
        query.append("            GROUP BY substr(a.wkor_date, 1,6)        		");
        query.append("     ) b ON a.tmonth = b.yyyymm        					");
        query.append("WHERE 1=1        											");
        if (workRptSelfVendorCommonDTO.getFilterStartDate() != null && !"".equals(workRptSelfVendorCommonDTO.getFilterStartDate()) 
        		&& workRptSelfVendorCommonDTO.getFilterEndDate() != null && !"".equals(workRptSelfVendorCommonDTO.getFilterEndDate()))
        {
        	query.append("AND a.tmonth >='"+workRptSelfVendorCommonDTO.getFilterStartDate()+"' AND a.tmonth <= '"+workRptSelfVendorCommonDTO.getFilterEndDate()+"'		");
        }
        query.append("ORDER BY a.tmonth        									");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
