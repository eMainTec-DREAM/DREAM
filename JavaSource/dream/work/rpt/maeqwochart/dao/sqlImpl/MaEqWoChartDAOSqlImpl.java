package dream.work.rpt.maeqwochart.dao.sqlImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.maeqwochart.dao.MaEqWoChartDAO;
import dream.work.rpt.maeqwochart.dto.MaEqWoChartDTO;

/**
 * 설비작업현황 DAO
 * @author  kim21017
 * @version $Id: MaEqWoChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqWoChartDAOTarget"
 * @spring.txbn id="maEqWoChartDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqWoChartDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqWoChartDAO
{
    /**
     * wo grid find
     * @author  kim21017
     * @version $Id: MaEqWoChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqWoChartDTO
     * @return List
     */
    public List findWoList(MaEqWoChartDTO maEqWoChartDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

//        String pmTypeKeyNo	   = "";
//        String pmTypeKoDesc	   = "";
//        String pmTypeDesc	   = "";
        String pmSchedStatus   = "";
        String pmSchedKoStatus = "";
        String lang = maEqWoChartDTO.getUserLang();
        String[] dateArr = maEqWoChartDTO.getDateArrStr().split(",");
        
        query.append("SELECT '' a, '전체[All]' b 									");
        query.append("UNION ALL													");
        query.append("SELECT * FROM (											");
        query.append("SELECT TOP 100 PERCENT cdsysd_no,							");
        query.append("       ISNULL((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+lang+"'						");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       description										");
        query.append("FROM TACDSYSD	x											");
        query.append("WHERE is_use='Y' AND list_type='WO_TYPE' AND cdsysd_no NOT IN('PMI', 'PMP', 'PMU') ");
        query.append("ORDER BY ISNULL(ord_no, '99999999')) tc					");
        
        String[][] list1 = QuerySqlBuffer.toStringArray(getJdbcTemplate().queryForList(query.toString()));
        
        List list = this.getLang("'all','pm','replacement','repair','refueling','clean','calibration','plan','result','completeRate'", lang);
        HashMap<Object, Object> map1 = new HashMap();
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            try
            {
                Map map = (Map) it.next();
                map1.put(map.get("KEY_NO"), map.get("KEY_NAME"));
            }
            catch (Exception ignored)
            {}
        }
        
        query = new QuerySqlBuffer();
        
        for (int i = 0; i < list1.length; i++) {
//        	switch (i) {
//    		case 0: pmTypeKeyNo="all";			pmTypeKoDesc = (String) map1.get(pmTypeKeyNo); 							break;
//    		case 1: pmTypeKeyNo="pm"; 			pmTypeKoDesc = (String) map1.get(pmTypeKeyNo);	pmTypeDesc = "INS";		break;
//    		case 2: pmTypeKeyNo="replacement"; 	pmTypeKoDesc = (String) map1.get(pmTypeKeyNo);	pmTypeDesc = "RPL";		break;
//    		case 3: pmTypeKeyNo="repair"; 		pmTypeKoDesc = (String) map1.get(pmTypeKeyNo);	pmTypeDesc = "RPR";		break;
//    		case 4: pmTypeKeyNo="refueling"; 	pmTypeKoDesc = (String) map1.get(pmTypeKeyNo);	pmTypeDesc = "OIL";		break;
//    		case 5: pmTypeKeyNo="clean"; 		pmTypeKoDesc = (String) map1.get(pmTypeKeyNo);	pmTypeDesc = "CLN";		break;
//    		case 6: pmTypeKeyNo="calibration"; 	pmTypeKoDesc = (String) map1.get(pmTypeKeyNo);	pmTypeDesc = "CLB";		break;
//    		}
        	//계획,실적,완료율
        	for (int j = 0; j < 3; j++) {
        		
        		//전체일때만 완료율표시(전체가 아니면 완료율을 표시하지않는다.)
        		if(i>0&&j==2) continue;
        		
        		if(i==0&&j==0){
        		}else{
        			query.append("UNION ALL												");
        		}
        		
        		switch (j) {
        		case 0: pmSchedStatus="plan"; 		  pmSchedKoStatus = (String) map1.get(pmSchedStatus);	break;
        		case 1: pmSchedStatus="result"; 	  pmSchedKoStatus = (String)map1.get(pmSchedStatus);	break;
        		case 2: pmSchedStatus="completeRate"; pmSchedKoStatus = (String)map1.get(pmSchedStatus); 	break;
        		}
        		
        		query.append("SELECT																		");
                query.append("		'"+list1[i][0]+"'		AS PMTYPEDESC									");
                query.append("		,'"+pmSchedStatus+"'	AS PMSTATUS										");
                query.append("		,'"+list1[i][1]+"'		AS PMTYPE");
                query.append("		,'"+pmSchedKoStatus+"'	AS PMSCHEDSTATUS");
                for (int k = 1; k < dateArr.length; k++) {
                	switch (j) {
            		case 0: 
            			query.append(",ISNULL(SUM(CASE startDate WHEN '"+CommonUtil.convertDate(dateArr[k])+"' THEN 1 END),0) AS \""+dateArr[k]+"\" ");
            			break;
            		case 1: 
            			query.append(",ISNULL(SUM(CASE startDate+woStatus WHEN '"+CommonUtil.convertDate(dateArr[k])+"C' THEN 1 END),0) AS \""+dateArr[k]+"\" ");
            			break;
            		case 2: 
            			query.append(",ROUND(ISNULL(SUM(CASE startDate+woStatus WHEN '"+CommonUtil.convertDate(dateArr[k])+"C' THEN 1 END)/SUM(CASE startDate WHEN '"+CommonUtil.convertDate(dateArr[k])+"' THEN 1 END)*100,0),3) AS \""+dateArr[k]+"\" ");
            			break;
            		}
        		}
                query.append("FROM 														");
                query.append("(															");
                query.append("	SELECT x.wkor_date startDate							");
                query.append("			,x.wo_status woStatus							");
                query.append("	FROM TAWORKORDER x										");
                query.append("	WHERE 1=1												");
                query.append("  AND x.wo_type NOT IN('PMI', 'PMP', 'PMU')               ");
                //wo_type구분
                if(i>0){
                	query.append("AND x.wo_type = '"+list1[i][0]+"'							");
                }
                query.append(this.getWhere(maEqWoChartDTO,"x.wkor_date"));
                query.append(")	ta														");
			}
		}
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * pm grid find
     * @author  kim21017
     * @version $Id: MaEqWoChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqWoChartDTO
     * @return List
     */
    public List findPmList(MaEqWoChartDTO maEqWoChartDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

//        String pmTypeKeyNo	   = "";
//        String pmTypeKoDesc	   = "";
//        String pmTypeDesc	   = "";
        String pmSchedStatus   = "";
        String pmSchedKoStatus = "";
        String lang = maEqWoChartDTO.getUserLang();
        String[] dateArr = maEqWoChartDTO.getDateArrStr().split(",");

        query.append("SELECT '' a, '전체[All]' b									");
        query.append("UNION ALL													");
        query.append("SELECT * FROM (											");
        query.append("SELECT TOP 100 PERCENT cdsysd_no,							");
        query.append("       ISNULL((SELECT aa.key_name							");
        query.append("            FROM TALANG aa								");
        query.append("            WHERE  lang = '"+lang+"'						");
        query.append("            AND x.key_type = aa.key_type					");
        query.append("            AND x.key_no = aa.key_no), x.description)		");
        query.append("       description										");
        query.append("FROM TACDSYSD	x											");
        query.append("WHERE is_use='Y' AND list_type='PMW_TYPE'	                ");
        query.append("ORDER BY ISNULL(ord_no, '99999999')) tc					");
        
        String[][] list1 = QuerySqlBuffer.toStringArray(getJdbcTemplate().queryForList(query.toString()));
        
        List list = this.getLang("'plan','result','completeRate'", lang);
        HashMap<Object, Object> map1 = new HashMap();
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            try
            {
                Map map = (Map) it.next();
                map1.put(map.get("KEY_NO"), map.get("KEY_NAME"));
            }
            catch (Exception ignored)
            {}
        }
        
        query = new QuerySqlBuffer();
        //전체,점검,교체,수리,주유,청소,교정
        for (int i = 0; i < list1.length; i++) {
        	//계획,실적,완료율
        	for (int j = 0; j < 3; j++) {
        		
        		//전체일때만 완료율표시(전체가 아니면 완료율을 표시하지않는다.)
        		if(i>0&&j==2) continue;
        		if(i==0&&j==0){
        		}else{
        			query.append("UNION ALL												");
        		}
        		
        		switch (j) {
        		case 0: pmSchedStatus="plan"; 		  pmSchedKoStatus = (String) map1.get(pmSchedStatus);	break;
        		case 1: pmSchedStatus="result"; 	  pmSchedKoStatus = (String) map1.get(pmSchedStatus);	break;
        		case 2: pmSchedStatus="completeRate"; pmSchedKoStatus = (String) map1.get(pmSchedStatus); 	break;
        		}
        		
        		query.append("SELECT													");
                query.append("		'"+list1[i][0]+"'		AS PMTYPEDESC				");
                query.append("		,'"+pmSchedStatus+"'	AS PMSTATUS					");
                query.append("		,'"+list1[i][1]+"'		AS PMTYPE					");
                query.append("		,'"+pmSchedKoStatus+"'	AS PMSCHEDSTATUS			");
                for (int k = 1; k < dateArr.length; k++) {
                	switch (j) {
            		case 0: 
            			query.append(",ISNULL(SUM(CASE startDate WHEN '"+CommonUtil.convertDate(dateArr[k])+"' THEN 1 END),0) AS \""+dateArr[k]+"\" ");
            			break;
            		case 1: 
            			query.append(",ISNULL(SUM(CASE startDate+woStatus WHEN '"+CommonUtil.convertDate(dateArr[k])+"C' THEN 1 END),0) AS \""+dateArr[k]+"\" ");
            			break;
            		case 2: 
            			query.append(",ROUND(ISNULL(SUM(CASE startDate+woStatus WHEN '"+CommonUtil.convertDate(dateArr[k])+"C' THEN 1 END)/SUM(CASE startDate WHEN '"+CommonUtil.convertDate(dateArr[k])+"' THEN 1 END)*100,0),3) AS \""+dateArr[k]+"\" ");
            			break;
            		}
        		}
                query.append("FROM 														");
                query.append("(															");
                query.append("	SELECT x.wkor_date startDate							");
                query.append("			,x.wo_status woStatus							");
                query.append("	FROM TAWORKORDER x										");
                query.append("	WHERE 1=1												");
                query.append("    AND x.wo_type IN ('PM', 'PMW')                        ");
                //pm_type구분
                if(i>0){
                	query.append("AND x.pm_type = '"+list1[i][0]+"'							");
                }
                query.append(this.getWhere(maEqWoChartDTO,"x.wkor_date"));
                query.append(")	ta														");
			}
		}
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * pt grid find
     * @author  kim21017
     * @version $Id: MaEqWoChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqWoChartDTO
     * @return List
     */
    public List findPtList(MaEqWoChartDTO maEqWoChartDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        String ptRecKeyNo	   = "ptRec";
        String ptRecKoDesc	   = "입고";
        String ptTypeKeyNo	   = "";
        String ptTypeKoDesc	   = "";
        String cntAmtCode	   = "";
        String cntAmtKoDesc    = "";
        String lang = maEqWoChartDTO.getUserLang();
        String[] dateArr = maEqWoChartDTO.getDateArrStr().split(",");
        
        List list = this.getLang("'buy','repair','total','cnt','amt'", lang);
        HashMap<Object, Object> map1 = new HashMap();
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            try
            {
                Map map = (Map) it.next();
                map1.put(map.get("KEY_NO"), map.get("KEY_NAME"));
            }
            catch (Exception ignored)
            {}
        }
        
        //구매,수리,합계
        for (int i = 0; i < 3; i++) {
        	switch (i) {
    		case 0: ptTypeKeyNo="buy"; 		  ptTypeKoDesc = (String) map1.get(ptTypeKeyNo);		break;
    		case 1: ptTypeKeyNo="repair"; 	  ptTypeKoDesc = (String) map1.get(ptTypeKeyNo);		break;
    		case 2: ptTypeKeyNo="total"; 	  ptTypeKoDesc = (String) map1.get(ptTypeKeyNo);		break;
    		}
        	//건, 금액
        	for (int j = 0; j < 2; j++) {
        		if(i==0&&j==0){
        		}else{
        			query.append("UNION ALL													");
        		}
        		
        		switch (j) {
        		case 0: cntAmtCode="cnt"; 		  cntAmtKoDesc = (String) map1.get(cntAmtCode);		break;
        		case 1: cntAmtCode="amt"; 		  cntAmtKoDesc = (String) map1.get(cntAmtCode);		break;
        		}
        		
        		query.append("SELECT									");
        		query.append("		'"+ptTypeKeyNo+"'   AS TYPE			");
                query.append("		,'"+ptRecKoDesc+"'  AS PTREC		");
                query.append("		,'"+ptTypeKoDesc+"' AS PTTYPE		");
                query.append("		,'"+cntAmtKoDesc+"' AS PTCNT		");
                for (int k = 1; k < dateArr.length; k++) {
                	switch (i) {
            		case 0: 
            			if(j==0)
            				query.append(",ISNULL(SUM(CASE recDate WHEN '"+CommonUtil.convertDate(dateArr[k])+"' THEN recQty END),0) AS \""+dateArr[k]+"\" ");
            			else if(j==1)
            				query.append(",ISNULL(SUM(CASE recDate WHEN '"+CommonUtil.convertDate(dateArr[k])+"' THEN totPrice END),0) AS \""+dateArr[k]+"\" ");
            			
            			break;
            		case 1: 
            			if(j==0)
            				query.append(",ISNULL(SUM(CASE repairDate WHEN '"+CommonUtil.convertDate(dateArr[k])+"' THEN repairQty END),0) AS \""+dateArr[k]+"\" ");
            			else if(j==1)
            				query.append(",ISNULL(SUM(CASE repairDate WHEN '"+CommonUtil.convertDate(dateArr[k])+"' THEN totPrice END),0) AS \""+dateArr[k]+"\" ");
            			
            			break;
            		case 2: 
            			if(j==0)
            				query.append(",ISNULL(SUM(CASE recDate WHEN '"+CommonUtil.convertDate(dateArr[k])+"' THEN recQty END),0) AS \""+dateArr[k]+"\" ");
            			else if(j==1)
            				query.append(",ISNULL(SUM(CASE recDate WHEN '"+CommonUtil.convertDate(dateArr[k])+"' THEN totPrice END),0) AS \""+dateArr[k]+"\" ");
            			
            			break;
            		}
        		}
                
                switch (i) {
        		case 0: 
        			query.append("FROM 															");
                    query.append("(																");
                    query.append("	SELECT x.rec_date recDate									");
                    query.append("			,x.rec_qty recQty									");
                    query.append("			,x.tot_price totPrice								");
                    query.append("	FROM TAPTPRRECLIST x										");
                    query.append("	WHERE 1=1													");
                    query.append("	  AND x.prreclist_status='C'								");
                    query.append(this.getWhere(maEqWoChartDTO,"x.rec_date"));
                    query.append(")	ta															");
        			break;
        		case 1:
        			query.append("FROM 															");
                    query.append("(																");
                    query.append("	SELECT x.repair_date repairDate								");
                    query.append("			,x.repair_qty repairQty								");
                    query.append("			,x.tot_price totPrice								");
                    query.append("	FROM TAPTREPAIRLIST x										");
                    query.append("	WHERE 1=1													");
                    query.append("	  AND x.ptrepairlist_status='V'								");
                    query.append(this.getWhere(maEqWoChartDTO,"x.repair_date"));
                    query.append(")	ta															");
        			break;
        		case 2:
        			query.append("FROM 															");
                    query.append("(																");
                    query.append("	SELECT x.rec_date recDate									");
                    query.append("			,x.rec_qty recQty									");
                    query.append("			,x.tot_price totPrice								");
                    query.append("	FROM TAPTPRRECLIST x										");
                    query.append("	WHERE 1=1													");
                    query.append("	  AND x.prreclist_status='C'								");
                    query.append(this.getWhere(maEqWoChartDTO,"x.rec_date"));
                    query.append("UNION ALL														");
                    query.append("	SELECT x.repair_date repairDate								");
                    query.append("			,x.repair_qty repairQty								");
                    query.append("			,x.tot_price totPrice								");
                    query.append("	FROM TAPTREPAIRLIST x										");
                    query.append("	WHERE 1=1													");
                    query.append("	  AND x.ptrepairlist_status='V'								");
                    query.append(this.getWhere(maEqWoChartDTO,"x.repair_date"));
                    query.append(") ta															");
        			break;
                }
			}
		}
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * use pt grid find
     * @author  kim21017
     * @version $Id: MaEqWoChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqWoChartDTO
     * @return List
     */
    public List findUsePtList(MaEqWoChartDTO maEqWoChartDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        String lang = maEqWoChartDTO.getUserLang();
        String[] dateArr = maEqWoChartDTO.getDateArrStr().split(",");
        
        List list = this.getLang("'usePt','cnt'", lang);
        HashMap<Object, Object> map1 = new HashMap();
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            try
            {
                Map map = (Map) it.next();
                map1.put(map.get("KEY_NO"), map.get("KEY_NAME"));
            }
            catch (Exception ignored)
            {}
        }
        String ptUseKeyNo	   = "usePt";
        String ptUseKoDesc	   = (String) map1.get(ptUseKeyNo);
        String useCntKeyNo	   = "cnt";
        String useCntKeyDesc   = (String) map1.get(useCntKeyNo);
        
        query.append("SELECT									");
        query.append("		'"+ptUseKoDesc+"' AS USETYPE");
        query.append("		,'"+useCntKeyDesc+"' AS USECNT");
        
		for (int k = 1; k < dateArr.length; k++) {
			query.append(",ISNULL(SUM(CASE x.wkor_date WHEN '"+CommonUtil.convertDate(dateArr[k])+"' THEN y.use_qty END),0) AS \""+dateArr[k]+"\" ");
		}
        query.append("FROM TAWORKORDER x, TAWOPARTS y							");
        query.append("WHERE x.wkor_id = y.wkor_id								");
        query.append("  AND x.comp_no = y.comp_no								");
        query.getAndQuery("x.wo_status", "C");
        query.append(this.getWhere(maEqWoChartDTO,"x.wkor_date"));
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaEqWoChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqWoChartDTO
     * @param dateColName
     * @return
     * @throws Exception
     */
    private String getWhere(MaEqWoChartDTO maEqWoChartDTO,String dateColName)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", maEqWoChartDTO.getCompNo());
        query.getAndDateQuery(dateColName, maEqWoChartDTO.getFilterStartDate(), maEqWoChartDTO.getFilterEndDate());
        //담당부서
        query.getDeptLevelQuery("x.dept_id", maEqWoChartDTO.getFilterDeptId(), maEqWoChartDTO.getFilterDeptDesc(), maEqWoChartDTO.getCompNo());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
        		maEqWoChartDTO.getFilterPlantId(), maEqWoChartDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
    private List getLang(String ids, String lang){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT key_no, key_name");
    	query.append("FROM TALANG");
    	query.append("WHERE 1=1");
    	query.getAndQuery("lang", lang);
    	query.getAndQuery("key_type", "LABEL");
    	query.append("AND key_no IN ("+ids+")");
    	
    	return this.getJdbcTemplate().queryForList(query.toString());
    }
}