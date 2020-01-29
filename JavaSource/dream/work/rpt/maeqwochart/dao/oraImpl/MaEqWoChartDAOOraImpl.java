package dream.work.rpt.maeqwochart.dao.oraImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.work.rpt.maeqwochart.dao.MaEqWoChartDAO;
import dream.work.rpt.maeqwochart.dto.MaEqWoChartDTO;

/**
 * 설비작업현황 DAO
 * @author  kim21017
 * @version $Id: MaEqWoChartDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqWoChartDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqWoChartDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqWoChartDAO
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
        QueryBuffer query = new QueryBuffer();

//        String pmTypeKeyNo	   = "";
//        String pmTypeKoDesc	   = "";
//        String pmTypeDesc	   = "";
        String pmSchedStatus   = "";
        String pmSchedKoStatus = "";
        String lang = maEqWoChartDTO.getUserLang();
        String[] dateArr = maEqWoChartDTO.getDateArrStr().split(",");
        
        query.append("SELECT '' a, '전체[All]' b FROM DUAL						");
        query.append("UNION ALL													");
        query.append("SELECT * FROM (											");
        query.append("SELECT cdsysd_no,											");
        query.append("       nvl((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+lang+"'						");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       description										");
        query.append("FROM TACDSYSD	x											");
        query.append("WHERE is_use='Y' AND list_type='WO_TYPE' AND cdsysd_no NOT IN('PMI', 'PMP', 'PMU') ");
        query.append("ORDER BY ord_no)											");
        
        String[][] list1 = QueryBuffer.toStringArray(getJdbcTemplate().queryForList(query.toString()));
        
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
        
        query = new QueryBuffer();
        
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
            			query.append(",NVL(SUM(DECODE(startDate,'"+CommonUtil.convertDate(dateArr[k])+"',1)),0) AS \""+dateArr[k]+"\" ");
            			break;
            		case 1: 
            			query.append(",NVL(SUM(DECODE(startDate||woStatus,'"+CommonUtil.convertDate(dateArr[k])+"C',1)),0) AS \""+dateArr[k]+"\" ");
            			break;
            		case 2: 
            			query.append(",ROUND(NVL(SUM(DECODE(startDate||woStatus,'"+CommonUtil.convertDate(dateArr[k])+"C',1))/SUM(DECODE(startDate,'"+CommonUtil.convertDate(dateArr[k])+"',1))*100,0),3) AS \""+dateArr[k]+"\" ");
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
                query.append(")															");
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
        QueryBuffer query = new QueryBuffer();

//        String pmTypeKeyNo	   = "";
//        String pmTypeKoDesc	   = "";
//        String pmTypeDesc	   = "";
        String pmSchedStatus   = "";
        String pmSchedKoStatus = "";
        String lang = maEqWoChartDTO.getUserLang();
        String[] dateArr = maEqWoChartDTO.getDateArrStr().split(",");

        query.append("SELECT '' a, '전체[All]' b FROM DUAL						");
        query.append("UNION ALL													");
        query.append("SELECT * FROM (											");
        query.append("SELECT cdsysd_no,											");
        query.append("       nvl((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+lang+"'						");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       description										");
        query.append("FROM TACDSYSD	x											");
        query.append("WHERE is_use='Y' AND list_type='PMW_TYPE' 	            ");
        query.append("ORDER BY ord_no)											");
        
        String[][] list1 = QueryBuffer.toStringArray(getJdbcTemplate().queryForList(query.toString()));
        
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
        
        query = new QueryBuffer();
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
            			query.append(",NVL(SUM(DECODE(startDate,'"+CommonUtil.convertDate(dateArr[k])+"',1)),0) AS \""+dateArr[k]+"\" ");
            			break;
            		case 1: 
            			query.append(",NVL(SUM(DECODE(startDate||woStatus,'"+CommonUtil.convertDate(dateArr[k])+"C',1)),0) AS \""+dateArr[k]+"\" ");
            			break;
            		case 2: 
            			query.append(",ROUND(NVL(SUM(DECODE(startDate||woStatus,'"+CommonUtil.convertDate(dateArr[k])+"C',1))/SUM(DECODE(startDate,'"+CommonUtil.convertDate(dateArr[k])+"',1))*100,0),3) AS \""+dateArr[k]+"\" ");
            			break;
            		}
        		}
                query.append("FROM 														");
                query.append("(															");
                query.append("	SELECT x.wkor_date startDate							");
                query.append("			,x.wo_status woStatus							");
                query.append("	FROM TAWORKORDER x										");
                query.append("	WHERE 1=1												");
                query.append("	  AND x.wo_type IN ('PM', 'PMW')					    ");
                //pm_type구분
                if(i>0){
                	query.append("AND x.pm_type = '"+list1[i][0]+"'							");
                }
                query.append(this.getWhere(maEqWoChartDTO,"x.wkor_date"));
                query.append(")															");
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
        QueryBuffer query = new QueryBuffer();

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
            				query.append(",NVL(SUM(DECODE(recDate,'"+CommonUtil.convertDate(dateArr[k])+"',recQty)),0) AS \""+dateArr[k]+"\" ");
            			else if(j==1)
            				query.append(",NVL(SUM(DECODE(recDate,'"+CommonUtil.convertDate(dateArr[k])+"',totPrice)),0) AS \""+dateArr[k]+"\" ");
            			
            			break;
            		case 1: 
            			if(j==0)
            				query.append(",NVL(SUM(DECODE(repairDate,'"+CommonUtil.convertDate(dateArr[k])+"',repairQty)),0) AS \""+dateArr[k]+"\" ");
            			else if(j==1)
            				query.append(",NVL(SUM(DECODE(repairDate,'"+CommonUtil.convertDate(dateArr[k])+"',totPrice)),0) AS \""+dateArr[k]+"\" ");
            			
            			break;
            		case 2: 
            			if(j==0)
            				query.append(",NVL(SUM(DECODE(recDate,'"+CommonUtil.convertDate(dateArr[k])+"',recQty)),0) AS \""+dateArr[k]+"\" ");
            			else if(j==1)
            				query.append(",NVL(SUM(DECODE(recDate,'"+CommonUtil.convertDate(dateArr[k])+"',totPrice)),0) AS \""+dateArr[k]+"\" ");
            			
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
                    query.append(")																");
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
                    query.append(")																");
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
                    query.append(")																");
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
        QueryBuffer query = new QueryBuffer();

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
			query.append(",NVL(SUM(DECODE(x.wkor_date,'"+CommonUtil.convertDate(dateArr[k])+"',y.use_qty)),0) AS \""+dateArr[k]+"\" ");
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
    	QueryBuffer query = new QueryBuffer();
    	
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
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT key_no, key_name");
    	query.append("FROM TALANG");
    	query.append("WHERE 1=1");
    	query.getAndQuery("lang", lang);
    	query.getAndQuery("key_type", "LABEL");
    	query.append("AND key_no IN ("+ids+")");
    	
    	return this.getJdbcTemplate().queryForList(query.toString());
    }
}