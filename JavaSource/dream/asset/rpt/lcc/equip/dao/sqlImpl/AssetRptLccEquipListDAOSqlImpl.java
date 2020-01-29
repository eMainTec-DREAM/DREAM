package dream.asset.rpt.lcc.equip.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.rpt.lcc.equip.dao.AssetRptLccEquipListDAO;
import dream.asset.rpt.lcc.equip.dto.AssetRptLccEquipCommonDTO;

/**
 * 고장TOP(설비) 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptLccEquipListDAOTarget"
 * @spring.txbn id="assetRptLccEquipListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptLccEquipListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetRptLccEquipListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccEquipCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(AssetRptLccEquipCommonDTO assetRptLccEquipCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     ");
        query.append("SELECT        										");
        query.append("    ''     				SEQNO      					");
        query.append("    ,''     				ISDELCHECK     				");
        query.append("    ,a.item_no      		itemNo     					");
        query.append("    ,b.eqloc_id     		eqLocId      				");
        query.append("    ,b.equip_id     		equipId      				");
        query.append("    ,(SELECT full_desc FROM TAEQLOC WHERE eqloc_id=b.eqloc_id)  	eqLocDesc   ");
        query.append("    ,b.description  		itemDesc      				");
        query.append("    ,b.eqctg_id       	eqCtgId      				");
        query.append("    ,(SELECT description FROM TAEQCTG WHERE eqctg_id=b.eqctg_id) 	eqctgDesc	");
        query.append("    ,b.dept_id        	deptId      				");
        query.append("    ,(SELECT description FROM TADEPT WHERE dept_id=b.dept_id)     deptDesc    ");
        query.append("    ,b.usage_dept   usageDept     ");
        query.append("    ,(SELECT description FROM TADEPT WHERE dept_id=b.usage_dept)   usageDeptDesc        ");
        query.append("    ,COUNT(a.item_no) 	bdTimeFreq       			");
        query.append("    ,ISNULL(SUM(a.work_time),0)   	woTimeMin      	");
        query.append("    ,ISNULL(SUM(a.tot_amt),0)         maintAmt        ");
        query.append("    ,(SELECT c.emp_name FROM TAEMP c WHERE c.comp_no = b.comp_no AND c.emp_id = b.main_mng_id) 	mainMngName		");
        query.append("    ,(SELECT c.emp_name FROM TAEMP c WHERE c.comp_no = b.comp_no AND c.emp_id = b.sub_mng_id) 	subMngName		");
        query.append("FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b       	");
        query.append("ON a.item_no=b.item_no        						");
        query.append("AND a.comp_no=b.comp_no        						");
        query.append("WHERE 1=1     										");
        query.append(this.getWhere(assetRptLccEquipCommonDTO,loginUser));
        query.append("GROUP BY      										");
        query.append("    a.item_no     									");
        query.append("    ,b.comp_no								        ");
        query.append("    ,b.equip_id       								");
        query.append("    ,b.eqloc_id       								");
        query.append("    ,b.description        							");
        query.append("    ,b.eqctg_id       								");
        query.append("    ,b.dept_id        								");
        query.append("    ,b.usage_dept                             ");
        query.append("    ,b.main_mng_id       								");
        query.append("    ,b.sub_mng_id       								");
        query.getOrderByQuery("a.eqhistory_id", "COUNT(a.item_no) DESC", assetRptLccEquipCommonDTO.getOrderBy(), assetRptLccEquipCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptLccEquipCommonDTO.getIsLoadMaxCount(), assetRptLccEquipCommonDTO.getFirstRow()));
    }
    
    public String getWhere(AssetRptLccEquipCommonDTO assetRptLccEquipCommonDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("b.is_last_version", "Y");
        
        query.append("AND a.wo_type='BM'        ");    
        
        query.append("AND a.wkor_date IS NOT NULL      ");
        
        String fromDate = assetRptLccEquipCommonDTO.getFilterStartDate()+"01";
        String toDate   = assetRptLccEquipCommonDTO.getFilterEndDate()+"31";
        
        query.getAndDateQuery("a.wkor_date", fromDate, toDate);
        
        query.getEqLocLevelQuery("b.eqloc_id", assetRptLccEquipCommonDTO.getFilterEqLocId(), assetRptLccEquipCommonDTO.getFilterEqLocDesc(), loginUser.getCompNo());
        query.getEqCtgLevelQuery("b.eqctg_id", assetRptLccEquipCommonDTO.getFilterEqCtgId(), assetRptLccEquipCommonDTO.getFilterEqCtgDesc(), loginUser.getCompNo());
        query.getDeptLevelQuery("b.dept_id", assetRptLccEquipCommonDTO.getFilterDeptId(), assetRptLccEquipCommonDTO.getFilterDeptDesc(), loginUser.getCompNo());
        query.getDeptLevelQuery("b.usage_dept", assetRptLccEquipCommonDTO.getFilterUsageDept(), assetRptLccEquipCommonDTO.getFilterUsageDeptDesc(), loginUser.getCompNo());
        
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant )", 
                assetRptLccEquipCommonDTO.getFilterPlantId(), assetRptLccEquipCommonDTO.getFilterPlantDesc());
        
        //관리자(정)
        query.getCodeLikeQuery("b.main_mng_id", "(SELECT c.emp_name FROM  TAEMP c WHERE c.comp_no = b.comp_no AND c.emp_id = b.main_mng_id )", 
        		assetRptLccEquipCommonDTO.getFilterMainMngId(), assetRptLccEquipCommonDTO.getFilterMainMngName());
        //관리자(부)
        query.getCodeLikeQuery("b.sub_mng_id", "(SELECT c.emp_name FROM  TAEMP c WHERE c.comp_no = b.comp_no AND c.emp_id = b.sub_mng_id )", 
        		assetRptLccEquipCommonDTO.getFilterSubMngId(), assetRptLccEquipCommonDTO.getFilterSubMngName());
                
        //설비번호
        query.getLikeQuery("b.item_no", assetRptLccEquipCommonDTO.getFilterItemNo());
                
        //설비
        query.getCodeLikeQuery("b.equip_id", "b.description||b.item_no", 
        		assetRptLccEquipCommonDTO.getFilterEquipId(), assetRptLccEquipCommonDTO.getFilterEquipDesc());
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(AssetRptLccEquipCommonDTO assetRptLccEquipCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED; ");
        query.append("SELECT                                            ");
        query.append("       COUNT(1)                                   ");
        query.append("FROM (                                            ");
        query.append("  SELECT                                          ");
        query.append("    a.item_no                                     ");
        query.append("    ,b.eqloc_id                                   ");
        query.append("    ,b.description                                ");
        query.append("    ,b.eqctg_id                                   ");
        query.append("    ,b.dept_id                                    ");
        query.append("    ,b.main_mng_id       							");
        query.append("    ,b.sub_mng_id       							");
        query.append("FROM TAEQHISTORY a INNER JOIN TAEQUIPMENT b       ");
        query.append("ON a.item_no=b.item_no                            ");
        query.append("AND a.comp_no=b.comp_no                           ");
        query.append("WHERE 1=1                                         ");
        query.append(this.getWhere(assetRptLccEquipCommonDTO,user));
        query.append("GROUP BY                                          ");
        query.append("    a.item_no                                     ");
        query.append("    ,b.eqloc_id                                   ");
        query.append("    ,b.description                                ");
        query.append("    ,b.eqctg_id                                   ");
        query.append("    ,b.dept_id                                    ");
        query.append("    ,b.main_mng_id       							");
        query.append("    ,b.sub_mng_id       							");
        query.append("   ) a                                            ");
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
}