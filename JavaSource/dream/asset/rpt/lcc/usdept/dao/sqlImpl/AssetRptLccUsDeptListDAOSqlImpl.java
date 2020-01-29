package dream.asset.rpt.lcc.usdept.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class AssetRptLccUsDeptListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetRptLccUsDeptListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT        									");
        query.append("    ''     						SEQNO      		");
        query.append("    ,''     						ISDELCHECK      ");
        query.append("    ,b.usage_dept        			usageDeptId     ");
        query.append("    ,(SELECT c.description FROM TADEPT c WHERE c.dept_id = b.usage_dept)   usageDeptDesc        ");
        query.append("    ,COUNT(a.item_no)    			bdTimeFreq      ");
        query.append("    ,ISNULL(SUM(a.work_time),0)   woTimeMin 	    ");
        query.append("    ,ISNULL(SUM(a.tot_amt),0)     maintAmt        ");
        query.append("FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b       ");
        query.append("ON a.item_no=b.item_no        					");
        query.append("AND a.comp_no=b.comp_no        					");
        query.append("WHERE 1=1     									");
        query.append(this.getWhere(assetRptLccUsDeptCommonDTO,loginUser));
        query.append("GROUP BY      									");
        query.append("    b.comp_no	        							");
        query.append("    ,b.usage_dept        							");
        query.getOrderByQuery("b.usage_dept", "COUNT(a.item_no) DESC", assetRptLccUsDeptCommonDTO.getOrderBy(), assetRptLccUsDeptCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptLccUsDeptCommonDTO.getIsLoadMaxCount(), assetRptLccUsDeptCommonDTO.getFirstRow()));
    }
    
    public String getWhere(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
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
    
    @Override
    public String findTotalCount(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                            ");
        query.append("       COUNT(1)                                   ");
        query.append("FROM (                                            ");
        query.append("SELECT        									");
        query.append("    b.comp_no	                                    ");
        query.append("FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b       ");
        query.append("ON a.item_no=b.item_no        					");
        query.append("AND a.comp_no=b.comp_no        					");
        query.append("WHERE 1=1     									");
        query.append(this.getWhere(assetRptLccUsDeptCommonDTO,user));
        query.append("GROUP BY      									");
        query.append("    b.comp_no        								");
        query.append("    ,b.usage_dept        							");
        query.append("		) c                                        	");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

	@Override
	public List findPlantList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findTeamList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findPartList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findEquipList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}
    
}