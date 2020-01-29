package dream.asset.rpt.lcc.usdept.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.rpt.lcc.usdept.dao.AssetRptLccUsDeptListDAO;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptCommonDTO;

/**
 * 고장TOP(사용부서) 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptLccUsDeptListDAOTarget"
 * @spring.txbn id="assetRptLccUsDeptListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptLccUsDeptListDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetRptLccUsDeptListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccUsDeptCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        								");
        query.append("    ''     		  				SEQNO      	");
        query.append("    ,''     		  				ISDELCHECK  ");
        query.append("    ,b.usage_dept     			usageDeptId ");
        query.append("    ,(SELECT SUBSTR(SYS_CONNECT_BY_PATH(description, '-'),2)      ");
        query.append("      FROM   TADEPT x   						");
        query.append("      WHERE x.dept_id = b.usage_dept  		");
        query.append("      START WITH x.p_dept_id = 0  			");
        query.append("      CONNECT BY PRIOR dept_id = p_dept_id    ");
        query.append("     ) usageDeptDesc  						");
        query.append("    ,COUNT(a.item_no)    			bdTimeFreq  ");
        query.append("    ,NVL(SUM(a.work_time),0)   	woTimeMin   ");
        query.append("    ,NVL(SUM(a.tot_amt),0)        maintAmt    ");
        query.append("    ,NVL(SUM(a.eqdn_work_time),0) bdTimeMin	");
        query.append("FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b   ");
        query.append("ON a.item_no=b.item_no        				");
        query.append("AND a.comp_no=b.comp_no        				");
        query.append("WHERE 1=1     								");
        query.append(this.getWhere(assetRptLccUsDeptCommonDTO,loginUser));
        query.append("GROUP BY      								");
        query.append("    b.comp_no	        						");
        query.append("    ,b.usage_dept        						");
        query.getOrderByQuery("COUNT(a.item_no) DESC", assetRptLccUsDeptCommonDTO.getOrderBy(), assetRptLccUsDeptCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptLccUsDeptCommonDTO.getIsLoadMaxCount(), assetRptLccUsDeptCommonDTO.getFirstRow()));
    }
    
    public String getWhere(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("b.is_last_version", "Y");
        query.append("AND a.wo_type='BM'        	");    
        query.append("AND a.wkor_date IS NOT NULL   ");
        query.append("AND b.usage_dept IS NOT NULL  ");

        //일자
        String fromDate = assetRptLccUsDeptCommonDTO.getFilterStartDate()+"01";
        String toDate   = assetRptLccUsDeptCommonDTO.getFilterEndDate()+"31";
        query.getAndDateQuery("a.wkor_date", fromDate, toDate);

        query.getSysCdQuery("b.eq_grade", assetRptLccUsDeptCommonDTO.getFilterEqGrade(), assetRptLccUsDeptCommonDTO.getFilterEqGradeDesc(), "EQ_GRADE", loginUser.getCompNo(), loginUser.getLangId());
        //사용부서
        query.getDeptLevelQuery("b.usage_dept", assetRptLccUsDeptCommonDTO.getFilterUsageDept(), assetRptLccUsDeptCommonDTO.getFilterUsageDeptDesc(), loginUser.getCompNo());
        
        //공장코드
//        query.getCodeLikeQuery("b.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = b.plant )", 
//                assetRptLccUsDeptCommonDTO.getFilterPlantId(), assetRptLccUsDeptCommonDTO.getFilterPlantDesc());
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant )", 
        		assetRptLccUsDeptCommonDTO.getFilterPlantId(), assetRptLccUsDeptCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
    public String getOuterWhere(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO,User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	//작업시간(분) 
    	query.getInequalityQuery("x.woTimeMin", assetRptLccUsDeptCommonDTO.getFilterWorkConOperDesc(), assetRptLccUsDeptCommonDTO.getFilterWorkTime());
    	
    	//고장시간(분) 
    	query.getInequalityQuery("x.bdTimeMin", assetRptLccUsDeptCommonDTO.getFilterEqDnConOperDesc(), assetRptLccUsDeptCommonDTO.getFilterEqDnTime());

    	return query.toString();
    }
    
    @Override
    public String findTotalCount(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        if("".equals(assetRptLccUsDeptCommonDTO.getFilterDwSeparation()))
        {
        	query.append("SELECT                                         	");
        	query.append("       COUNT(1)                                	");
        	query.append("FROM (                                           	");
	        query.append("SELECT        									");
	        query.append("    b.comp_no		                                ");
	        query.append("FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b       ");
	        query.append("ON a.item_no=b.item_no        					");
	        query.append("AND a.comp_no=b.comp_no        					");
	        query.append("WHERE 1=1     									");
	        query.append(this.getWhere(assetRptLccUsDeptCommonDTO,user));
	        query.append("GROUP BY      									");
	        query.append("    b.comp_no	        							");
	        query.append("    ,b.usage_dept        							");
	        query.append("		) c                                        	");
        }
        else if("L1".equals(assetRptLccUsDeptCommonDTO.getFilterDwSeparation()))
        {
        	query.append("SELECT                                         	");
        	query.append("       COUNT(1)                                	");
        	query.append("FROM (                                           	");
        	query.append(" SELECT 											");
        	query.append("   x.comp_no										");
        	query.append(" FROM (											");
	        query.append("	SELECT        									");
	        query.append("    	b.comp_no		                            ");
	        query.append("       ,(SELECT c.dept_id                         ");
	        query.append("           FROM   TADEPT c                        ");
	        query.append("          WHERE dept_categ = 'L1'                 ");
	        query.append("          START WITH c.dept_id = b.usage_dept     ");
	        query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
	        query.append("         )                            plant       ");
	        query.append("       ,(SELECT c.dept_id                         ");
	        query.append("           FROM   TADEPT c                        ");
	        query.append("          WHERE dept_categ = 'L3'                 ");
	        query.append("          START WITH c.dept_id = b.usage_dept     ");
	        query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
	        query.append("         )                            part        ");
	        query.append("       , NVL(SUM(a.eqdn_work_time),0) bdTimeMin	");
	        query.append("       , NVL(SUM(a.work_time),0)   	woTimeMin   ");
	        query.append("	FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b     ");
	        query.append("	ON a.item_no=b.item_no        					");
	        query.append("	AND a.comp_no=b.comp_no        					");
	        query.append("	WHERE 1=1     									");
	        query.append(this.getWhere(assetRptLccUsDeptCommonDTO,user));
	        query.append("	GROUP BY                                      	");
	        query.append("  	  b.comp_no    								");
	        query.append("  	  ,b.usage_dept   							");
	        query.append("	) x												");
	        query.append("WHERE 1=1											");
    		query.append("  AND x.part IS NOT NULL							");
	        query.append(this.getOuterWhere(assetRptLccUsDeptCommonDTO,user));
	        query.append("GROUP BY x.comp_no, x.plant						");
	        query.append(") c             									");
        }
        else if("L2".equals(assetRptLccUsDeptCommonDTO.getFilterDwSeparation()))
        {
        	query.append("SELECT                                         	");
        	query.append("       COUNT(1)                                	");
        	query.append("FROM (                                           	");
        	query.append(" SELECT 											");
        	query.append("   x.comp_no										");
        	query.append(" FROM (											");
	        query.append("	SELECT        									");
	        query.append("    	b.comp_no		                            ");
	        query.append("       ,(SELECT c.dept_id                         ");
	        query.append("           FROM   TADEPT c                        ");
	        query.append("          WHERE dept_categ = 'L2'                 ");
	        query.append("          START WITH c.dept_id = b.usage_dept     ");
	        query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
	        query.append("         )                            team        ");
	        query.append("       ,(SELECT c.dept_id                         ");
	        query.append("           FROM   TADEPT c                        ");
	        query.append("          WHERE dept_categ = 'L3'                 ");
	        query.append("          START WITH c.dept_id = b.usage_dept     ");
	        query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
	        query.append("         )                            part        ");
	        query.append("       , NVL(SUM(a.eqdn_work_time),0) bdTimeMin	");
	        query.append("       , NVL(SUM(a.work_time),0)   	woTimeMin   ");
	        query.append("	FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b     ");
	        query.append("	ON a.item_no=b.item_no        					");
	        query.append("	AND a.comp_no=b.comp_no        					");
	        query.append("	WHERE 1=1     									");
	        query.append(this.getWhere(assetRptLccUsDeptCommonDTO,user));
	        query.append("	GROUP BY                                      	");
	        query.append("  	  b.comp_no    								");
	        query.append("  	  ,b.usage_dept   							");
	        query.append("	) x												");
	        query.append("WHERE 1=1											");
    		query.append("  AND x.part IS NOT NULL							");
	        query.append(this.getOuterWhere(assetRptLccUsDeptCommonDTO,user));
	        query.append("GROUP BY x.comp_no, x.team						");
	        query.append(") c             									");
        }
        else if("L3".equals(assetRptLccUsDeptCommonDTO.getFilterDwSeparation()))
        {
        	query.append("SELECT                                         	");
        	query.append("       COUNT(1)                                	");
        	query.append("FROM (                                           	");
        	query.append(" SELECT 											");
        	query.append("   x.comp_no										");
        	query.append(" FROM (											");
	        query.append("	SELECT        									");
	        query.append("    	b.comp_no		                            ");
	        query.append("       ,(SELECT c.dept_id                         ");
	        query.append("           FROM   TADEPT c                        ");
	        query.append("          WHERE dept_categ = 'L3'                 ");
	        query.append("          START WITH c.dept_id = b.usage_dept     ");
	        query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
	        query.append("         )                            part        ");
	        query.append("       , NVL(SUM(a.eqdn_work_time),0) bdTimeMin	");
	        query.append("       , NVL(SUM(a.work_time),0)   	woTimeMin   ");
	        query.append("	FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b     ");
	        query.append("	ON a.item_no=b.item_no        					");
	        query.append("	AND a.comp_no=b.comp_no        					");
	        query.append("	WHERE 1=1     									");
	        query.append(this.getWhere(assetRptLccUsDeptCommonDTO,user));
	        query.append("	GROUP BY                                      	");
	        query.append("  	  b.comp_no    								");
	        query.append("  	  ,b.usage_dept   							");
	        query.append("	) x												");
	        query.append("WHERE 1=1											");
    		query.append("  AND x.part IS NOT NULL							");
	        query.append(this.getOuterWhere(assetRptLccUsDeptCommonDTO,user));
	        query.append("GROUP BY x.comp_no, x.part						");
	        query.append(") c             									");
        }
        else if("L4".equals(assetRptLccUsDeptCommonDTO.getFilterDwSeparation()))
        {
        	query.append("SELECT                                         	");
        	query.append("       COUNT(1)                                	");
        	query.append("FROM (                                           	");
        	query.append(" SELECT 											");
        	query.append("   x.comp_no										");
        	query.append(" FROM (											");
	        query.append("	SELECT        									");
	        query.append("    	b.comp_no		                            ");
	        query.append("      ,b.usage_dept								");
	        query.append("      ,b.equip_id									");
	        query.append("      ,b.description								");
	        query.append("       ,(SELECT c.dept_id                         ");
	        query.append("           FROM   TADEPT c                        ");
	        query.append("          WHERE dept_categ = 'L3'                 ");
	        query.append("          START WITH c.dept_id = b.usage_dept     ");
	        query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
	        query.append("         )                            part        ");
	        query.append("       , NVL(SUM(a.eqdn_work_time),0) bdTimeMin	");
	        query.append("       , NVL(SUM(a.work_time),0)   	woTimeMin   ");
	        query.append("	FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b     ");
	        query.append("	ON a.item_no=b.item_no        					");
	        query.append("	AND a.comp_no=b.comp_no        					");
	        query.append("	WHERE 1=1     									");
	        query.append(this.getWhere(assetRptLccUsDeptCommonDTO,user));
	        query.append("	GROUP BY                                      	");
	        query.append("  	  b.comp_no    								");
	        query.append("  	  ,b.usage_dept   							");
	        query.append("  	  ,b.equip_id       						");
	        query.append("  	  ,b.description   							");
	        query.append("	) x												");
	        query.append("WHERE 1=1											");
    		query.append("  AND x.part IS NOT NULL							");
	        query.append(this.getOuterWhere(assetRptLccUsDeptCommonDTO,user));
	        query.append("GROUP BY x.comp_no, x.usage_dept, x.equip_id , x.description	");
	        query.append(") c             									");
        }
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

	@Override
	public List findPlantList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser) 
	{
		QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT 											");
		query.append("     ''                   SEQNO          			");
		query.append("    ,''                   ISDELCHECK  			");
		query.append("    ,x.plant    			usageDeptId				");
		query.append("    ,NVL((SELECT SUBSTR(SYS_CONNECT_BY_PATH(c.description, '-'),2)                             		");
		query.append("           FROM TADEPT c                          ");
		query.append("          WHERE c.dept_id = x.plant               ");
		query.append("          START WITH c.p_dept_id = 0              ");
		query.append("          CONNECT BY PRIOR dept_id = p_dept_id    ");
		query.append("         ),'파트지정안됨')    usageDeptDesc        	");
		query.append("    ,SUM(x.bdTimeFreq) 	bdTimeFreq				");
		query.append("    ,SUM(x.woTimeMin) 	woTimeMin				");
		query.append("    ,SUM(x.maintAmt) 		maintAmt				");
		query.append("    ,SUM(x.bdTimeMin)     bdTimeMin     			");
		query.append("FROM (											");
		query.append("    SELECT                                        ");
		query.append("        b.comp_no						comp_no		");
		query.append("       ,(SELECT c.dept_id                         ");
		query.append("           FROM   TADEPT c                        ");
		query.append("          WHERE dept_categ = 'L1'                 ");
		query.append("          START WITH c.dept_id = b.usage_dept     ");
		query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
		query.append("         )                            plant       ");
		query.append("       ,(SELECT c.dept_id                         ");
		query.append("           FROM   TADEPT c                        ");
		query.append("          WHERE dept_categ = 'L2'                 ");
		query.append("          START WITH c.dept_id = b.usage_dept     ");
		query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
		query.append("         )                            team        ");
		query.append("       ,(SELECT c.dept_id                         ");
		query.append("           FROM   TADEPT c                        ");
		query.append("          WHERE dept_categ = 'L3'                 ");
		query.append("          START WITH c.dept_id = b.usage_dept     ");
		query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
		query.append("         )                            part		");
		query.append("       , COUNT(a.item_no)    			bdTimeFreq  ");
        query.append("       , NVL(SUM(a.work_time),0)   	woTimeMin   ");
        query.append("       , NVL(SUM(a.tot_amt),0)        maintAmt    ");
        query.append("       , NVL(SUM(a.eqdn_work_time),0) bdTimeMin	");
        query.append("FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b		");
        query.append("ON a.item_no=b.item_no        					");
        query.append("AND a.comp_no=b.comp_no        					");
        query.append("WHERE 1=1     									");
        query.append(this.getWhere(assetRptLccUsDeptCommonDTO,loginUser));
        query.append("GROUP BY      									");
        query.append("    b.comp_no	        							");
        query.append("    ,b.usage_dept    								");
        query.append(") x												");
        query.append("WHERE 1=1											");
        query.append("  AND x.part IS NOT NULL							");
        query.append(this.getOuterWhere(assetRptLccUsDeptCommonDTO,loginUser));
        query.append("GROUP BY x.comp_no, x.plant						");
        query.getOrderByQuery("SUM(x.bdTimeFreq) DESC", assetRptLccUsDeptCommonDTO.getOrderBy(), assetRptLccUsDeptCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptLccUsDeptCommonDTO.getIsLoadMaxCount(), assetRptLccUsDeptCommonDTO.getFirstRow()));
        
	}

	@Override
	public List findTeamList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser) {
		QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT 											");
		query.append("     ''                   SEQNO          			");
		query.append("    ,''                   ISDELCHECK  			");
		query.append("    ,x.team    			usageDeptId				");
		query.append("    ,NVL((SELECT SUBSTR(SYS_CONNECT_BY_PATH(c.description, '-'),2)                             		");
		query.append("           FROM TADEPT c                          ");
		query.append("          WHERE c.dept_id = x.team                ");
		query.append("          START WITH c.p_dept_id = 0              ");
		query.append("          CONNECT BY PRIOR dept_id = p_dept_id    ");
		query.append("         ),'파트지정안됨')    usageDeptDesc        	");
		query.append("    ,SUM(x.bdTimeFreq) 	bdTimeFreq				");
		query.append("    ,SUM(x.woTimeMin) 	woTimeMin				");
		query.append("    ,SUM(x.maintAmt) 		maintAmt				");
		query.append("    ,SUM(x.bdTimeMin)     bdTimeMin     			");
		query.append("FROM (											");
		query.append("    SELECT                                        ");
		query.append("        b.comp_no						comp_no		");
		query.append("       ,(SELECT c.dept_id                         ");
		query.append("           FROM   TADEPT c                        ");
		query.append("          WHERE dept_categ = 'L1'                 ");
		query.append("          START WITH c.dept_id = b.usage_dept     ");
		query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
		query.append("         )                            plant       ");
		query.append("       ,(SELECT c.dept_id                         ");
		query.append("           FROM   TADEPT c                        ");
		query.append("          WHERE dept_categ = 'L2'                 ");
		query.append("          START WITH c.dept_id = b.usage_dept     ");
		query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
		query.append("         )                            team        ");
		query.append("       ,(SELECT c.dept_id                         ");
		query.append("           FROM   TADEPT c                        ");
		query.append("          WHERE dept_categ = 'L3'                 ");
		query.append("          START WITH c.dept_id = b.usage_dept     ");
		query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
		query.append("         )                            part		");
		query.append("       , COUNT(a.item_no)    			bdTimeFreq  ");
        query.append("       , NVL(SUM(a.work_time),0)   	woTimeMin   ");
        query.append("       , NVL(SUM(a.tot_amt),0)        maintAmt    ");
        query.append("       , NVL(SUM(a.eqdn_work_time),0) bdTimeMin	");
        query.append("FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b		");
        query.append("ON a.item_no=b.item_no        					");
        query.append("AND a.comp_no=b.comp_no        					");
        query.append("WHERE 1=1     									");
        query.append(this.getWhere(assetRptLccUsDeptCommonDTO,loginUser));
        query.append("GROUP BY      									");
        query.append("    b.comp_no	        							");
        query.append("    ,b.usage_dept    								");
        query.append(") x												");
        query.append("WHERE 1=1											");
		query.append("  AND x.part IS NOT NULL							");
        query.append(this.getOuterWhere(assetRptLccUsDeptCommonDTO,loginUser));
        query.append("GROUP BY x.comp_no, x.team						");
        query.getOrderByQuery("SUM(x.bdTimeFreq) DESC", assetRptLccUsDeptCommonDTO.getOrderBy(), assetRptLccUsDeptCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptLccUsDeptCommonDTO.getIsLoadMaxCount(), assetRptLccUsDeptCommonDTO.getFirstRow()));
        
	}

	@Override
	public List findPartList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser) {
		QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT 											");
		query.append("     ''                   SEQNO          			");
		query.append("    ,''                   ISDELCHECK  			");
		query.append("    ,x.part    			usageDeptId				");
		query.append("    ,NVL((SELECT SUBSTR(SYS_CONNECT_BY_PATH(c.description, '-'),2)                             		");
		query.append("           FROM TADEPT c                          ");
		query.append("          WHERE c.dept_id = x.part                ");
		query.append("          START WITH c.p_dept_id = 0              ");
		query.append("          CONNECT BY PRIOR dept_id = p_dept_id    ");
		query.append("         ),'파트지정안됨')    usageDeptDesc        	");
		query.append("    ,SUM(x.bdTimeFreq) 	bdTimeFreq				");
		query.append("    ,SUM(x.woTimeMin) 	woTimeMin				");
		query.append("    ,SUM(x.maintAmt) 		maintAmt				");
		query.append("    ,SUM(x.bdTimeMin)     bdTimeMin     			");
		query.append("FROM (											");
		query.append("    SELECT                                        ");
		query.append("        b.comp_no						comp_no		");
		query.append("       ,(SELECT c.dept_id                         ");
		query.append("           FROM   TADEPT c                        ");
		query.append("          WHERE dept_categ = 'L1'                 ");
		query.append("          START WITH c.dept_id = b.usage_dept     ");
		query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
		query.append("         )                            plant       ");
		query.append("       ,(SELECT c.dept_id                         ");
		query.append("           FROM   TADEPT c                        ");
		query.append("          WHERE dept_categ = 'L2'                 ");
		query.append("          START WITH c.dept_id = b.usage_dept     ");
		query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
		query.append("         )                            team        ");
		query.append("       ,(SELECT c.dept_id                         ");
		query.append("           FROM   TADEPT c                        ");
		query.append("          WHERE dept_categ = 'L3'                 ");
		query.append("          START WITH c.dept_id = b.usage_dept     ");
		query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
		query.append("         )                            part		");
		query.append("       , COUNT(a.item_no)    			bdTimeFreq  ");
        query.append("       , NVL(SUM(a.work_time),0)   	woTimeMin   ");
        query.append("       , NVL(SUM(a.tot_amt),0)        maintAmt    ");
        query.append("       , NVL(SUM(a.eqdn_work_time),0) bdTimeMin	");
        query.append("FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b		");
        query.append("ON a.item_no=b.item_no        					");
        query.append("AND a.comp_no=b.comp_no        					");
        query.append("WHERE 1=1     									");
        query.append(this.getWhere(assetRptLccUsDeptCommonDTO,loginUser));
        query.append("GROUP BY      									");
        query.append("    b.comp_no	        							");
        query.append("    ,b.usage_dept    								");
        query.append(") x												");
        query.append("WHERE 1=1											");
		query.append("  AND x.part IS NOT NULL							");
        query.append(this.getOuterWhere(assetRptLccUsDeptCommonDTO,loginUser));
        query.append("GROUP BY x.comp_no, x.part						");
        query.getOrderByQuery("SUM(x.bdTimeFreq) DESC", assetRptLccUsDeptCommonDTO.getOrderBy(), assetRptLccUsDeptCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptLccUsDeptCommonDTO.getIsLoadMaxCount(), assetRptLccUsDeptCommonDTO.getFirstRow()));
        
	}

	@Override
	public List findEquipList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT 											");
		query.append("     ''                   SEQNO          			");
		query.append("    ,''                   ISDELCHECK  			");
		query.append("	  ,x.equip_id			equipId					");
		query.append("	  ,x.description		equipDesc				");
		query.append("    ,x.usage_dept 		usageDeptId				");
		query.append("    ,NVL((SELECT SUBSTR(SYS_CONNECT_BY_PATH(c.description, '-'),2)                             		");
		query.append("           FROM TADEPT c                          ");
		query.append("          WHERE c.dept_id = x.usage_dept          ");
		query.append("          START WITH c.p_dept_id = 0              ");
		query.append("          CONNECT BY PRIOR dept_id = p_dept_id    ");
		query.append("         ),'파트지정안됨')    usageDeptDesc        	");
		query.append("    ,SUM(x.bdTimeFreq) 	bdTimeFreq				");
		query.append("    ,SUM(x.woTimeMin) 	woTimeMin				");
		query.append("    ,SUM(x.maintAmt) 		maintAmt				");
		query.append("    ,SUM(x.bdTimeMin)     bdTimeMin     			");
		query.append("FROM (											");
		query.append("    SELECT                                        ");
		query.append("        b.comp_no						comp_no		");
		query.append("    	 ,b.usage_dept    				usage_dept	");
		query.append("    	 ,b.equip_id    				equip_id	");
		query.append("    	 ,b.description   				description	");
		query.append("       ,(SELECT c.dept_id                         ");
		query.append("           FROM   TADEPT c                        ");
		query.append("          WHERE dept_categ = 'L3'                 ");
		query.append("          START WITH c.dept_id = b.usage_dept     ");
		query.append("        CONNECT BY PRIOR c.p_dept_id = c.dept_id  ");
		query.append("         )                            part		");
		query.append("       , COUNT(a.item_no)    			bdTimeFreq  ");
		query.append("       , NVL(SUM(a.work_time),0)   	woTimeMin   ");
		query.append("       , NVL(SUM(a.tot_amt),0)        maintAmt    ");
        query.append("       , NVL(SUM(a.eqdn_work_time),0) bdTimeMin	");
        query.append("FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b		");
		query.append("ON a.item_no=b.item_no        					");
		query.append("AND a.comp_no=b.comp_no        					");
		query.append("WHERE 1=1     									");
		query.append(this.getWhere(assetRptLccUsDeptCommonDTO,loginUser));
		query.append("GROUP BY      									");
		query.append("    b.comp_no	        							");
		query.append("    ,b.usage_dept    								");
		query.append("    ,b.equip_id    								");
		query.append("    ,b.description   								");
		query.append(") x												");
		query.append("WHERE 1=1											");
		query.append("  AND x.part IS NOT NULL							");
        query.append(this.getOuterWhere(assetRptLccUsDeptCommonDTO,loginUser));
		query.append("GROUP BY x.comp_no, x.usage_dept, x.equip_id, x.description		");
		query.getOrderByQuery("SUM(x.bdTimeFreq) DESC", assetRptLccUsDeptCommonDTO.getOrderBy(), assetRptLccUsDeptCommonDTO.getDirection());
		
		return getJdbcTemplate().queryForList(query.toString(assetRptLccUsDeptCommonDTO.getIsLoadMaxCount(), assetRptLccUsDeptCommonDTO.getFirstRow()));
		
	}
    
}