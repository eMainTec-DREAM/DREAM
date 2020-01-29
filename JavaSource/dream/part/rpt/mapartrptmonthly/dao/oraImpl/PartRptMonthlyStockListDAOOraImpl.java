package dream.part.rpt.mapartrptmonthly.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.rpt.mapartrptmonthly.dao.PartRptMonthlyStockListDAO;
import dream.part.rpt.mapartrptmonthly.dto.PartRptMonthlyStockListDTO;

/**
 * 부품수불부 요약 DAO
 * @author  euna0207
 * @version $Id: PartRptMonthlyStockListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 * @spring.bean id="partRptMonthlyStockListDAOTarget"
 * @spring.txbn id="partRptMonthlyStockListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartRptMonthlyStockListDAOOraImpl extends BaseJdbcDaoSupportOra implements PartRptMonthlyStockListDAO
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: PartRptMonthlyStockListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     * 
     * @param partRptMonthlyStockListDTO
     * @return List
     */
    public List findList(PartRptMonthlyStockListDTO partRptMonthlyStockListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 																											");
        query.append("x.comp_no 																						compNo			");
        query.append(",'' 																								seqNo			");
        query.append(", (SELECT a.part_no FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id)   		partNo			");     
        query.append(", (SELECT a.description FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id)   	partDesc		");     
        query.append(", (SELECT a.model FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id)   		model			");     
        query.append(", (SELECT a.pt_size FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id)   		ptSize			");     
        query.append(", CASE WHEN x.result_tot = 0 THEN 0                                                                               ");
        query.append("       ELSE (x.result_tot_price/x.result_tot) END                                                 unitPrice       ");
        query.append(", CASE WHEN x.rec_qty = 0 THEN 0                                                                                  ");
        query.append("       ELSE (x.rec_tot_price/x.rec_qty) END                                                       recPrice        ");
        query.append(", x.result_tot 																					resultTot 		");
        query.append(", x.result_tot_price																				resultTotPrice	");
        query.append(", x.base_qty																						baseQty			");
        query.append(", x.base_tot_price																				baseTotPrice	");
        query.append(", x.rec_qty																						recQty			");
        query.append(", x.rec_tot_price																					recTotPrice		");
        query.append(", x.issue_qty																						issueQty		");
        query.append(", x.issue_tot_price																				issueTotPrice	");
        query.append(" FROM TAPTMONTHLYSTOCK x 									");
        query.append(" WHERE 1=1												");
        query.append(this.getWhere(partRptMonthlyStockListDTO));
        query.getOrderByQuery("x.part_id", partRptMonthlyStockListDTO.getOrderBy(), partRptMonthlyStockListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(partRptMonthlyStockListDTO.getIsLoadMaxCount(), partRptMonthlyStockListDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건1
     * @author  euna0207
     * @version $Id: PartRptMonthlyStockListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     * 
     * @param partRptMonthlyStockListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(PartRptMonthlyStockListDTO partRptMonthlyStockListDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        
        //창고명
        query.getCodeLikeQuery("x.wcode_id", "(SELECT a.wname FROM TAWAREHOUSE a WHERE a.comp_no = x.comp_no AND a.wcode_id = x.wcode_id )", 
        		partRptMonthlyStockListDTO.getFilterWcodeId(), partRptMonthlyStockListDTO.getFilterWcodeDesc());
        //부품명
        query.getCodeLikeQuery("x.part_id", "(SELECT a.description FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id )", 
        		partRptMonthlyStockListDTO.getFilterPartsId(), partRptMonthlyStockListDTO.getFilterPartsDesc());        
        //년월
        query.getAndQuery("x.yyyymm", partRptMonthlyStockListDTO.getFilterYearMonthDesc());
        return query.toString();
    }

	@Override
	public String findTotalCount(PartRptMonthlyStockListDTO partRptMonthlyStockListDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                 					");
        query.append("       COUNT(1)                        					");
        query.append(" FROM TAPTMONTHLYSTOCK x 									");
    	query.append("WHERE  1=1												");
    	query.append(this.getWhere(partRptMonthlyStockListDTO));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}