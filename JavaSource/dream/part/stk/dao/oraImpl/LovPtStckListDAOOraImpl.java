package dream.part.stk.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.stk.dao.LovPtStckListDAO;
import dream.part.stk.dto.LovPtStckListDTO;

/**
 * 재고검색 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovPtStckListDAOTarget"
 * @spring.txbn id="lovPtStckListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovPtStckListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovPtStckListDAO
{
    /**
     * 재고 검색 AC LOV
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPtStckListDTO
     * @param loginUser
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public List findAcList(LovPtStckListDTO lovPtStckListDTO, User loginUser, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                             ");
        query.append("      a.comp_no                                                    ");
        query.append("      ,a.wcode_id                                                  ");
        query.append("      ,b.wname                                                     ");
        query.append("      ,a.part_id                                                   ");
        query.append("      ,c.part_no                                                   ");
        query.append("      ,c.description||', '||c.pt_size  AS pt_name_size             ");
        query.append("      ,c.description               AS pt_desc                      ");
        query.append("      ,c.pt_size                   AS pt_size                      ");
        query.append("      ,c.model                     AS model                        ");
        query.append("      ,c.part_group                                                ");
        query.append("      ,SFACODE_TO_DESC(c.part_group, 'PART_GROUP', 'USR', a.comp_no,'"+loginUser.getLangId()+"')      part_group_desc   ");
        query.append("      ,c.pt_abc_class                                              ");
        query.append("      ,SFACODE_TO_DESC(c.pt_abc_class, 'PT_ABC_CLASS', 'SYS', a.comp_no,'"+loginUser.getLangId()+"')  pt_abc_class_desc ");
        query.append("      ,a.part_grade                                                ");
        query.append("      ,SFACODE_TO_DESC(a.part_grade, 'PART_GRADE', 'SYS', a.comp_no,'"+loginUser.getLangId()+"')      part_grade_desc   ");
        query.append("      ,a.stock_qty                                                 ");
        query.append("      ,a.bin_no                                                    ");
        query.append("      ,(SELECT safty_qty                                           ");
        query.append("        FROM TAPTSAFTYSTOCK aa                                     ");
        query.append("        WHERE aa.comp_no = a.comp_no                               ");
        query.append("        AND aa.wcode_id = a.wcode_id                               ");
        query.append("        AND aa.part_id = a.part_id) AS min_safty_qty               ");
        query.append("      ,(SELECT max_safty_qty                                       ");
        query.append("        FROM TAPTSAFTYSTOCK aa                                     ");
        query.append("        WHERE aa.comp_no = a.comp_no                               ");
        query.append("        AND aa.wcode_id = a.wcode_id                               ");
        query.append("        AND aa.part_id = a.part_id) AS max_safty_qty               ");
        query.append("FROM TAPTSTOCK a INNER JOIN TAWAREHOUSE b                          ");
        query.append("ON a.comp_no = b.comp_no                                           ");
        query.append("AND a.wcode_id = b.wcode_id                                        ");
        query.append("INNER JOIN TAPARTS c                                               ");
        query.append("ON a.comp_no = c.comp_no                                           ");
        query.append("AND a.part_id = c.part_id                                          ");
        query.append("WHERE 1=1                                                          ");
        query.append(this.getWhere(lovPtStckListDTO, loginUser, columnMap, conditionMap));
        query.getOrderByQuery("c.part_no, b.wname", lovPtStckListDTO.getOrderBy(), lovPtStckListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovPtStckListDTO.getIsLoadMaxCount(), lovPtStckListDTO.getFirstRow()));

    }
    
    
    private String getWhere(LovPtStckListDTO lovPtStckListDTO, User user, Map<String, String> columnMap,
            Map<String, String> conditionMap)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("b.is_use", "Y");
        query.getAndQuery("c.is_use", "Y");
        query.getAndQuery("c.is_deleted", "N");
        
        query.getAndQuery("a.comp_no", conditionMap);
        query.getAndQuery("b.wh_categ", conditionMap);
        query.getAndQuery("c.part_categ", conditionMap);
        
        query.getCodeLikeQuery("b.wcode_id", "b.wname", lovPtStckListDTO.getFilterWId(), lovPtStckListDTO.getFilterWDesc());
        query.getLikeQuery("c.full_desc", lovPtStckListDTO.getFilterPartDesc());
        query.getCodeLikeQuery("c.part_id", "c.part_no", lovPtStckListDTO.getFilterPartId(), lovPtStckListDTO.getFilterPartNo());
        query.getLikeQuery("c.vendor_code", lovPtStckListDTO.getFilterVendorPtCode());
        
        // 모델
        query.getLikeQuery("c.model", lovPtStckListDTO.getFilterModel());
        // 총재고 이상수량
        if(!"".equals(lovPtStckListDTO.getFilterQtyCnt())){
        	query.append("AND nvl(a.stock_qty,0) >= "+lovPtStckListDTO.getFilterQtyCnt()+"");
        }
        
        return query.toString();
    }
    
    public String findTotalCount(
            LovPtStckListDTO lovPtStckListDTO, User user, Map<String, String> columnMap,
            Map<String, String> conditionMap) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                        ");
        query.append("       COUNT(1)                                               ");
        query.append("FROM TAPTSTOCK a INNER JOIN TAWAREHOUSE b                     ");
        query.append("ON a.comp_no = b.comp_no                                      ");
        query.append("AND a.wcode_id = b.wcode_id                                   ");
        query.append("INNER JOIN TAPARTS c                                          ");
        query.append("ON a.comp_no = c.comp_no                                      ");
        query.append("AND a.part_id = c.part_id                                     ");
        query.append("WHERE  1=1                                                    ");
        query.append(this.getWhere(lovPtStckListDTO, user, columnMap, conditionMap));
        query.append("ORDER BY 1                                                    ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
