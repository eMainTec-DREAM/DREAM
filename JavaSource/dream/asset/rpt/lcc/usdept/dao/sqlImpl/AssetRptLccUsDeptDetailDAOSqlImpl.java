package dream.asset.rpt.lcc.usdept.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.asset.rpt.lcc.usdept.dao.AssetRptLccUsDeptDetailDAO;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptCommonDTO;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptDetailDTO;

/**
 * 고장TOP(사용부서) 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptLccUsDeptDetailDAOTarget"
 * @spring.txbn id="assetRptLccUsDeptDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptLccUsDeptDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetRptLccUsDeptDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccUsDeptDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO,AssetRptLccUsDeptDetailDTO assetRptLccUsDeptDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String [] monthArr = DateUtil.getArrayBetweenMonth(assetRptLccUsDeptDetailDTO.getStartDate().replaceAll("-", ""), assetRptLccUsDeptDetailDTO.getEndDate().replaceAll("-", ""));
        for(int i=0; i<monthArr.length; i++){
            assetRptLccUsDeptDetailDTO.setStartDate(monthArr[i]);
            assetRptLccUsDeptDetailDTO.setEndDate(monthArr[i]);
            
            if(i != 0) {
                query.append("UNION ALL");
            }
            query.append("SELECT");
            if("0".equals(this.findCount(assetRptLccUsDeptCommonDTO, assetRptLccUsDeptDetailDTO, loginUser))) {
                query.append("    SUBSTRING('"+monthArr[i]+"',1,4)+'-'+SUBSTRING('"+monthArr[i]+"',5,2) month         ");
                query.append("    ,0                                                                    bdTimeFreq    ");
                query.append("    ,0                                                                    woTimeMin     ");
                query.append("    ,0                                                                    maintAmt      ");
                query.append("    ,0                                                                    bdTimeMin     ");
                query.append("	  ,0																	usageDeptId	  ");
                query.append("	  ,''																	usageDeptDesc ");
            }
            else {
                query.append("    x.month                                                               month         ");
                query.append("    ,COUNT(x.itemNo)                                                      bdTimeFreq    ");
                query.append("    ,ISNULL(SUM(x.workTime),0)                                            woTimeMin     ");
                query.append("    ,ISNULL(SUM(x.totAmt),0)                                              maintAmt      ");
                query.append("    ,ISNULL(SUM(x.bdTimeMin),0)                                           bdTimeMin     ");
                query.append("	  ,ISNULL(MAX(x.usageDeptId),0)											usageDeptId	  ");
                query.append("	  ,ISNULL(MAX(x.usageDeptDesc),'')										usageDeptDesc ");
                query.append("FROM                                                                                    ");
                query.append("    (                                                                         	");
                query.append("    SELECT                                                                    	");
                query.append("        a.item_no                                                  itemNo     	");
                query.append("        ,SUBSTRING(a.wkor_date,1,4)+'-'+SUBSTRING(a.wkor_date,5,2) month      	");
                query.append("        ,a.work_time                                               workTime   	");
                query.append("        ,a.tot_amt                                                 totAmt     	");
                query.append("        ,a.eqdn_work_time											 bdTimeMin		");
                query.append("		  ,b.usage_dept											   	 usageDeptId	");
                query.append("		  ,(SELECT c.description FROM TADEPT c WHERE c.comp_no = b.comp_no AND c.dept_id = b.usage_dept)   usageDeptDesc  ");
                query.append("    FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b                               	");
                query.append("    ON a.item_no=b.item_no                                                    	");
                query.append("    AND a.comp_no=b.comp_no                                                   	");
                query.append("    WHERE 1=1                                                                 	");
                query.append(this.getWhere(assetRptLccUsDeptCommonDTO, assetRptLccUsDeptDetailDTO,loginUser));
                query.append("    )x                                                                        	");
                query.append("GROUP BY x.month");
            }
        }
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO,AssetRptLccUsDeptDetailDTO assetRptLccUsDeptDetailDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("b.is_last_version", "Y");
        
        query.append("    AND a.wo_type='BM'        ");
        query.append("    AND a.wkor_date IS NOT NULL       ");
        
        String fromDate = assetRptLccUsDeptDetailDTO.getStartDate().replace("-", "") + "01";
        String toDate   = assetRptLccUsDeptDetailDTO.getEndDate().replace("-", "") + "31";
        query.getAndDateQuery("a.wkor_date", fromDate, toDate);
        query.getSysCdQuery("b.eq_grade", assetRptLccUsDeptCommonDTO.getFilterEqGrade(), assetRptLccUsDeptCommonDTO.getFilterEqGradeDesc(), "EQ_GRADE", loginUser.getCompNo(), loginUser.getLangId());
        query.getAndQuery("b.usage_dept", assetRptLccUsDeptDetailDTO.getUsageDeptId());
//        query.getDeptLevelQuery("b.usage_dept", assetRptLccUsDeptDetailDTO.getUsageDeptId(), "", loginUser.getCompNo());
        
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant )",
        		assetRptLccUsDeptDetailDTO.getPlantId(), assetRptLccUsDeptDetailDTO.getPlantDesc());
      
        return query.toString();
    }
    
    public String findCount(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO,AssetRptLccUsDeptDetailDTO assetRptLccUsDeptDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                          ");
        query.append("    COUNT(1)                                    ");
        query.append("    FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b ");
        query.append("    ON a.item_no=b.item_no                      ");
        query.append("    AND a.comp_no=b.comp_no                     ");
        query.append("    WHERE 1=1                                   ");
        query.append(this.getWhere(assetRptLccUsDeptCommonDTO, assetRptLccUsDeptDetailDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
}