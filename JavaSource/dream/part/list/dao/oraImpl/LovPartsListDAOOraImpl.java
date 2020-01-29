package dream.part.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.list.dao.LovPartsListDAO;
import dream.part.list.dto.LovPartsListDTO;

/**
 * 자재검색 팝업
 * @author  kim21017
 * @version $Id: LovPartsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="lovPartsListDAOTarget"
 * @spring.txbn id="lovPartsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovPartsListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovPartsListDAO
{
    /**
     * 자재 검색
     * @author  kim21017
     * @version $Id: LovPartsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPartsListDTO
     * @param loginUser
     * @return
     */
    public List findPartsList(LovPartsListDTO lovPartsListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT															");
        query.append("       part_id partId,											");
        query.append("       part_no partNo,											");
        query.append("       description || (case when pt_size is null then '' else ',' || pt_size end )  || (case when vendor_code is null then '' else ',' || vendor_code end) partDesc,	");
        query.append("       maker maker,												");
        query.append("       model model,												");
        query.append("       plf_type plfType,											");
        query.append("       SFACODE_TO_DESC(plf_type,'PLF_TYPE','SYS','','"+loginUser.getLangId()+"') plfTypeDesc,	");
        query.append("       TO_CHAR(last_price,'999,999,999,999')	lastPrice,		    ");
        query.append("       vendor_code								 vendorCode,	");
        query.append("       is_serial_part		isSerial,							    ");
        query.append("       (SELECT SUM(stock_qty)                     	            ");
        query.append("          FROM TAPTSTOCK                              	        ");
        query.append("         WHERE comp_no=x.comp_no                  	            ");
        query.append("           AND part_id=x.part_id          		                ");
        query.getAndQuery("wcode_id", lovPartsListDTO.getFilterWId());
        query.append("        ) stockQty		                                        ");
        query.append("       ,(SELECT SFACODE_TO_DESC(x.part_group, 'PART_GROUP', 'USR', x.comp_no,'"+loginUser.getLangId()+"') FROM DUAL)  partGroupDesc       ");
        query.append("FROM TAPARTS x												    ");
        query.append("WHERE 1=1															");
        query.getAndQuery("part_categ", "SPPT");
        query.getAndQuery("is_deleted", "N");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        if(!"".equals(lovPartsListDTO.getDeptId())||!"".equals(lovPartsListDTO.getDeptDesc())){
        	query.append("AND part_id IN (SELECT part_id 										");
        	query.append("					FROM TAPTUSEDDEPT									");
        	query.append("					WHERE 1=1											");
        	query.getAndQuery("comp_no", loginUser.getCompNo());
        	query.getDeptLevelQuery("dept_id", lovPartsListDTO.getDeptId(),lovPartsListDTO.getDeptDesc(), loginUser.getCompNo());
        	query.append("					)													");
        }
        query.getLikeQuery("part_no", lovPartsListDTO.getPartNo());
        query.getLikeQuery("full_desc", lovPartsListDTO.getPartDesc());//부품명/규격 
        query.getLikeQuery("model", lovPartsListDTO.getFilterModel());
        query.getLikeQuery("maker", lovPartsListDTO.getFilterMaker());
        query.getLikeQuery("remark", lovPartsListDTO.getFilterRemark());
        query.getLikeQuery("vendor_code", lovPartsListDTO.getFilterVendorPtCode());
        // 자재그룹
        query.getUsrCdQuery("part_group", lovPartsListDTO.getFilterPartGroup(), lovPartsListDTO.getFilterPartGroupDesc(), "PART_GROUP", loginUser.getCompNo(),loginUser.getLangId());
        
        query.getOrderByQuery("x.part_no", lovPartsListDTO.getOrderBy(), lovPartsListDTO.getDirection());
        
        //return getJdbcTemplate().queryForList(query.toString());
        return getJdbcTemplate().queryForList(query.toString(lovPartsListDTO.getIsLoadMaxCount(), lovPartsListDTO.getFirstRow()));

    }
    /**
     * 자재 검색 AC LOV
     * @author  kim21017
     * @version $Id: LovPartsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPartsListDTO
     * @param loginUser
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public List findPartsAcList(LovPartsListDTO lovPartsListDTO, User loginUser, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();

    	query.append("SELECT															");
        query.append("       x.part_id 		AS part_id									");
        query.append("       ,x.part_no 		AS part_no								");
        query.append("       ,x.description 	AS description							");
        query.append("       ,x.description || (CASE WHEN x.pt_size IS NULL THEN '' ELSE ',' || x.pt_size END )  || (CASE WHEN x.vendor_code IS NULL THEN '' ELSE ',' || x.vendor_code END) 	AS full_desc								");
        query.append("       ,x.maker 		AS maker									");
        query.append("       ,x.model 		AS model									");
        query.append("       ,x.uom 		AS uom    									");
        query.append("       ,(SELECT SFACODE_TO_DESC(x.uom, 'UOM', 'USR', x.comp_no,'"+loginUser.getLangId()+"') FROM DUAL) AS uom_desc ");
        query.append("       ,x.plf_type 		AS plf_type								");
        query.append("       ,(SELECT SFACODE_TO_DESC(plf_type,'PLF_TYPE','SYS','','"+loginUser.getLangId()+"') FROM DUAL) AS plf_type_desc	");
        query.append("       ,TO_CHAR(x.last_price,'999,999,999,999') AS last_price ");
        query.append("       ,x.vendor_code	AS vendor_code								");
        query.append("       ,x.pt_size		AS pt_size									");
        query.append("       ,x.description || (CASE WHEN x.pt_size IS NULL THEN '' ELSE ',' || x.pt_size END )  || (CASE WHEN x.vendor_code IS NULL THEN '' ELSE ',' || x.vendor_code END) AS partNameSize				");
        query.append("       ,x.is_serial_part AS is_serial_part						");
        query.append("       ,(SELECT SUM(stock_qty)                                    ");
        query.append("          FROM TAPTSTOCK                                          ");
        query.append("         WHERE comp_no=x.comp_no                                  ");
        query.append("           AND part_id=x.part_id                                  ");
        query.getAndQuery("wcode_id", lovPartsListDTO.getFilterWId());
        query.append("         ) stockQty                                               ");
        query.append("       ,x.erp_part_no  AS erp_part_no						        ");
        query.append("       ,x.seller       AS seller						            ");
        query.append("       ,(SELECT SFACODE_TO_DESC(x.part_group, 'PART_GROUP', 'USR', x.comp_no,'"+loginUser.getLangId()+"') FROM DUAL)  partGroupDesc       ");
        query.append("       ,currency      AS currency                               	");
        query.append("       ,SFACODE_TO_DESC(currency,'CURRENCY','SYS','','"+ loginUser.getLangId() +"')	AS currencyDesc	");
        query.append("FROM TAPARTS x													");
        query.append("WHERE 1=1															");
        query.append(this.getWhere(lovPartsListDTO, loginUser, columnMap, conditionMap));

        query.getOrderByQuery("x.part_no", lovPartsListDTO.getOrderBy(), lovPartsListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(lovPartsListDTO.getIsLoadMaxCount(), lovPartsListDTO.getFirstRow()));

    }
    
    
    private String getWhere(LovPartsListDTO lovPartsListDTO, User user, Map<String, String> columnMap,
            Map<String, String> conditionMap)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("part_categ", conditionMap);
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("part_group", conditionMap);
        query.getAndQuery("model", conditionMap);
        query.getAndQuery("pt_size", conditionMap);
        if(conditionMap.containsKey("wcode_id") && !"".equals(conditionMap.get("wcode_id"))) {
            query.append("AND part_id  IN (SELECT part_id                                        ");
            query.append("                 FROM taptstock                                       ");
            query.append("                 WHERE 1=1                                            ");
            query.getAndQuery("comp_no", conditionMap);
            query.append("                  and wcode_id = '"+conditionMap.get("wcode_id")+"'   ");
            query.append("                  and stock_qty > 0)                                  ");
        }
        query.getAndQuery("is_deleted", "N");

        
        if(!"".equals(lovPartsListDTO.getDeptId())||!"".equals(lovPartsListDTO.getDeptDesc())){
            query.append("AND part_id IN (SELECT part_id                                        ");
            query.append("                  FROM TAPTUSEDDEPT                                   ");
            query.append("                  WHERE 1=1                                           ");
            query.getAndQuery("comp_no", conditionMap);
            query.getDeptLevelQuery("dept_id", lovPartsListDTO.getDeptId(),lovPartsListDTO.getDeptDesc(), String.valueOf(conditionMap.get("comp_no")));
            query.append("                  )                                                   ");
        }
        query.getLikeQuery("part_no", lovPartsListDTO.getPartNo());
        query.getLikeQuery("full_desc", lovPartsListDTO.getPartDesc());//부품명/규격 
        query.getLikeQuery("model", lovPartsListDTO.getFilterModel());
        query.getLikeQuery("maker", lovPartsListDTO.getFilterMaker());
        query.getLikeQuery("remark", lovPartsListDTO.getFilterRemark());
        query.getLikeQuery("vendor_code", lovPartsListDTO.getFilterVendorPtCode());
        query.getLikeQuery("erp_part_no", lovPartsListDTO.getFilterErpPartNo());
        query.getAndQuery("part_categ", lovPartsListDTO.getFilterPartCategCode());
        // 자재그룹
        query.getUsrCdQuery("part_group", lovPartsListDTO.getFilterPartGroup(), lovPartsListDTO.getFilterPartGroupDesc(), "PART_GROUP", String.valueOf(conditionMap.get("comp_no")),user.getLangId());
        
        // 설비 (TAEQPART)
        if(!"".equals(lovPartsListDTO.getFilterEquipId())){
            
            query.append(" AND "+lovPartsListDTO.getFilterEquipId()+" IN (SELECT equip_id FROM TAEQPART WHERE part_id = x.part_id) ");
        }
        else if (!"".equals(lovPartsListDTO.getFilterEquipDesc()))
        {
            query.append("AND part_id IN ( (SELECT part_id                          ");
            query.append("                  FROM TAEQPART                           ");
            query.append("                  WHERE part_id = x.part_id               ");
            query.append("                  AND EQUIP_ID IN (SELECT EQUIP_ID        ");
            query.append("                                   FROM TAEQUIPMENT       ");
            query.append("                                   WHERE 1=1              ");
            query.getLikeQuery("description", lovPartsListDTO.getFilterEquipDesc());
            query.append("                  ) ) )                                   ");

        }
        
        // 설비부위
        if(!"".equals(lovPartsListDTO.getFilterEqAsmbId()) || !"".equals(lovPartsListDTO.getFilterEqAsmbDesc()) )
        {
        	query.append("AND EXISTS (SELECT 1 FROM TAEQPART a INNER JOIN TAEQUIPMENT b		");
        	query.append("                ON a.comp_no = b.comp_no							");
        	query.append("               AND a.equip_id = b.equip_id						");
        	query.append("             WHERE 1=1											");
        	query.append("               AND a.comp_no = x.comp_no							");
        	query.append("               AND a.part_id = x.part_id							");
        	query.getAndQuery("a.equip_id", lovPartsListDTO.getFilterEquipId());
        	
        	if(!"".equals(lovPartsListDTO.getFilterEqAsmbId()))
        	{
        		query.append("AND exists (   SELECT eqasmb_id                               ");
                query.append("                       FROM TAEQASMB c                        ");
                query.append("                       WHERE c.comp_no = b.comp_no            ");
                query.append("                       and c.eqasmb_id = a.eqasmb_id          ");
                query.append("                       START wITH c.eqasmb_id = "+lovPartsListDTO.getFilterEqAsmbId()+"    ");
                query.append("                       CONNECT BY PRIOR c.eqasmb_id = c.p_eqasmb_id	");
                query.append("           )                                               	");
        	}
        	else
        	{
        		query.append("AND exists (   SELECT eqasmb_id                               ");
                query.append("                       FROM TAEQASMB c                        ");
                query.append("                       WHERE c.comp_no = b.comp_no            ");
                query.append("                       START wITH UPPER(c.full_desc) LIKE '%"+lovPartsListDTO.getFilterEqAsmbDesc()+"%'	");
                query.append("                       CONNECT BY PRIOR c.eqasmb_id = c.p_eqasmb_id    ");
                query.append("           )                                           		");
        	}
        	query.append("           )														");
        }
        
        // 창고 (재고수량이 0 이상인 것만 출력)
        if(!"".equals(lovPartsListDTO.getFilterWId())){
            query.append("AND part_id  IN (SELECT part_id                                        ");
            query.append("                 FROM taptstock                                       ");
            query.append("                 WHERE 1=1                                            ");
            query.getAndQuery("comp_no", conditionMap);
            query.append("                  and wcode_id = '"+lovPartsListDTO.getFilterWId()+"' ");
            query.append("                  and stock_qty > 0)                                  ");
        }
        else if(!"".equals(lovPartsListDTO.getFilterWDesc())){
            
            query.append("AND x.part_id IN ( (SELECT part_id                        ");
            query.append("                    FROM TAPTSTOCK                        ");
            query.append("                    WHERE part_id = x.part_id             ");
            query.append("                    AND stock_qty > 0                     ");
            query.append("                    AND wcode_id IN (SELECT wcode_id      ");
            query.append("                                     FROM TAWAREHOUSE     ");
            query.append("                                     WHERE 1=1            ");
            query.getLikeQuery("wname", lovPartsListDTO.getFilterWDesc());
            query.append("                  ) ) )                                   ");

        }
        //입고일자
        if(!("".equals(lovPartsListDTO.getFilterStartRecDate()) && "".equals(lovPartsListDTO.getFilterEndRecDate())))
        {
            query.append("AND EXISTS(SELECT prreclist_id           ");
            query.append("           FROM TAPTPRRECLIST            ");
            query.append("           WHERE comp_no = x.comp_no     ");
            query.append("           AND part_id = x.part_id       ");
            query.getAndDateQuery("rec_date", lovPartsListDTO.getFilterStartRecDate(), lovPartsListDTO.getFilterEndRecDate());
            query.append("           )                             ");
        }
        
        //출고일자
        if(!("".equals(lovPartsListDTO.getFilterStartIssDate()) && "".equals(lovPartsListDTO.getFilterEndIssDate())))
        {
            query.append("AND EXISTS(SELECT ptemgisslist_id        ");
            query.append("           FROM TAPTEMGISSLIST           ");
            query.append("           WHERE comp_no = x.comp_no     ");
            query.append("           AND part_id = x.part_id       ");
            query.getAndDateQuery("issue_date", lovPartsListDTO.getFilterStartIssDate(), lovPartsListDTO.getFilterEndIssDate());
            query.append("           )                             ");
        }
        
        //사용일자
        if(!("".equals(lovPartsListDTO.getFilterStartUseDate()) && "".equals(lovPartsListDTO.getFilterEndUseDate())))
        {
            query.append("AND EXISTS(SELECT a.wopart_id                          ");
            query.append("           FROM TAWOPARTS a INNER JOIN TAWORKORDER b   ");
            query.append("           ON a.comp_no=b.comp_no                      ");
            query.append("           AND a.wkor_id=b.wkor_id                     ");
            query.append("           WHERE a.comp_no = x.comp_no                 ");
            query.append("           AND a.part_id = x.part_id                   ");
            query.getAndDateQuery("b.wkor_date", lovPartsListDTO.getFilterStartUseDate(), lovPartsListDTO.getFilterEndUseDate());
            query.append("           )                                           ");
        }
        
        //재고수량관리여부
        query.getAndQuery("is_stock_control", conditionMap);
        query.getSysCdQuery("x.is_use", lovPartsListDTO.getFilterIsUse(), lovPartsListDTO.getFilterIsUseDesc(), "IS_USE", user.getCompNo(), user.getLangId());

        return query.toString();
    }
    
    public String findTotalCount(
            LovPartsListDTO lovPartsListDTO, User user, Map<String, String> columnMap,
            Map<String, String> conditionMap) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                        ");
        query.append("       COUNT(1)                                               ");
        query.append("FROM   TAPARTS x                                          ");
        query.append("WHERE  1=1                                                    ");
        query.append(this.getWhere(lovPartsListDTO, user, columnMap, conditionMap));
        query.append("ORDER BY 1                                                    ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
