package dream.part.pur.buy.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.pur.buy.dao.LovPartPurBuyPnListDAO;
import dream.part.pur.buy.dto.LovPartPurBuyPnListDTO;

/**
 * 현장신청부품 선택 Lov
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovPartPurBuyPnListDAOTarget"
 * @spring.txbn id="lovPartPurBuyPnListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovPartPurBuyPnListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovPartPurBuyPnListDAO
{
    /**
     * 현장신청부품 검색 AC LOV
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPartPurBuyPnListDTO
     * @param user
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public List findAcList(LovPartPurBuyPnListDTO lovPartPurBuyPnListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT ''                					seqNo		");
    	query.append("        ,''               	 			isDelCheck	");
    	query.append("        ,x.ptpnlist_id        			ptPnListId	");
    	query.append("        ,x.ptpnlist_no        			ptPnListNo	");
    	query.append("        ,x.part_id	        			partId		");
    	query.append("        ,y.part_no    					partNo		");
    	query.append("        ,y.erp_part_no    				erpPartNo	");
    	query.append("        ,CONCAT(x.description,',',x.pt_size)    			partDesc	");
    	query.append("        ,x.description				    partName	");
    	query.append("        ,x.pt_size				        ptSize	    ");
    	query.append("        ,y.model				            model	    ");
    	query.append("        ,x.rec_qty            			recQty		");
    	query.append("        ,x.pr_qty            				prQty		");
    	query.append("        ,x.plant            				plantId		");
    	query.append("        ,(SELECT aa.description						");
    	query.append("          FROM TAPLANT aa								");
    	query.append("          WHERE aa.comp_no = x.comp_no				");
    	query.append("          AND aa.plant = x.plant)    		plantDesc	");
    	query.append("        ,x.dept_id            			deptId		");
    	query.append("        ,(SELECT aa.description						");
    	query.append("        	FROM TADEPT aa								");
    	query.append("        	WHERE aa.comp_no = x.comp_no				");
    	query.append("        	AND aa.dept_id = x.dept_id)    	deptDesc	");
    	query.append("        , x.user_id            			userId		");
    	query.append("        ,(SELECT aa.emp_name							");
    	query.append("        	FROM TAEMP aa								");
    	query.append("        	WHERE aa.comp_no = x.comp_no				");
    	query.append("        	AND aa.emp_id = x.user_id)    	userDesc	");
    	query.append("        ,x.equip_id            			equipId		");
    	query.append("        ,(SELECT aa.description						");
    	query.append("        	FROM TAEQUIPMENT aa							");
    	query.append("        	WHERE aa.comp_no = x.comp_no				");
    	query.append("        	AND aa.equip_id = x.equip_id)   equipDesc	");
    	query.append("        ,x.usage                    		usage		");
    	query.append("        ,x.remark                    		remark		");
    	query.append("FROM TAPTPNLIST x LEFT OUTER JOIN TAPARTS y			");
    	query.append("ON x.COMP_NO = y.COMP_NO AND x.PART_ID = y.PART_ID	");
        query.append("WHERE 1=1                                             ");
        query.append("  AND x.ptpnlist_status != 'W'						");
        query.append("  AND x.rec_qty > ISNULL(x.pr_on_qty, 0)				");
        query.append(this.getWhere(lovPartPurBuyPnListDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("x.ptpnlist_id","x.ptpnlist_id", lovPartPurBuyPnListDTO.getOrderBy(), lovPartPurBuyPnListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovPartPurBuyPnListDTO.getIsLoadMaxCount(), lovPartPurBuyPnListDTO.getFirstRow()));

    }
    
    private String getWhere(LovPartPurBuyPnListDTO lovPartPurBuyPnListDTO, User user, Map<String, String> columnMap,
            Map<String, String> conditionMap)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        // 요청번호
        query.getLikeQuery("x.ptpnlist_no", lovPartPurBuyPnListDTO.getFilterPtPnListNo());
        
        // 부품번호
        query.getLikeQuery("y.part_no", lovPartPurBuyPnListDTO.getFilterPartNo());
        
        // 신청일자
        query.getAndDateQuery("x.req_date", lovPartPurBuyPnListDTO.getFilterReqStartDate(), lovPartPurBuyPnListDTO.getFilterReqEndDate());

        // 공장
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		lovPartPurBuyPnListDTO.getFilterPlantId(), lovPartPurBuyPnListDTO.getFilterPlantDesc());
        
        // 부품명
        query.getLikeQuery("y.description", lovPartPurBuyPnListDTO.getFilterPartNameSize());
        
        // 신청자
        query.getCodeLikeQuery("x.user_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.user_id AND a.comp_no='"+user.getCompNo()+"')", 
        		lovPartPurBuyPnListDTO.getFilterUserId(), lovPartPurBuyPnListDTO.getFilterUserDesc());

        // 신청부서
        query.getDeptLevelQuery("x.dept_id", lovPartPurBuyPnListDTO.getFilterDeptId(), lovPartPurBuyPnListDTO.getFilterDeptDesc(), user.getCompNo());
        
        // 사용용도
        query.getLikeQuery("x.usage", lovPartPurBuyPnListDTO.getFilterUsage());
        
        // 사용설비
        query.getCodeLikeQuery("x.equip_id", "(SELECT a.description FROM  TAEQUIPMENT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.equip_id = x.equip_id )", 
        		lovPartPurBuyPnListDTO.getFilterEquipId(), lovPartPurBuyPnListDTO.getFilterEquipDesc());
        
        return query.toString();
    }
    
    public String findTotalCount(
            LovPartPurBuyPnListDTO lovPartPurBuyPnListDTO, User user, Map<String, String> columnMap,
            Map<String, String> conditionMap) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                ");
        query.append("       COUNT(1)                                       ");
        query.append("FROM TAPTPNLIST x LEFT OUTER JOIN TAPARTS y			");
    	query.append("ON x.COMP_NO = y.COMP_NO AND x.PART_ID = y.PART_ID	");
        query.append("WHERE 1=1                                             ");
        query.append("  AND x.ptpnlist_status != 'W'						");
        query.append("  AND x.rec_qty > ISNULL(x.pr_on_qty, 0)				");
        query.append(this.getWhere(lovPartPurBuyPnListDTO, user, columnMap, conditionMap));
        query.append("ORDER BY 1                                            ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}