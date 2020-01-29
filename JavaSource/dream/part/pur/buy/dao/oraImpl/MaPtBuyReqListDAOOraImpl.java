package dream.part.pur.buy.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.pur.buy.dao.MaPtBuyReqListDAO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;

/**
 * 구매신청 item 목록 dao
 * @author  kim21017
 * @version $Id: MaPtBuyReqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPtBuyReqListDAOTarget"
 * @spring.txbn id="maPtBuyReqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtBuyReqListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtBuyReqListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPtBuyReqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrCommonDTO
     * @param maPtBuyReqListDTO
     * @return List
     */
    public List findItemList(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, MaPtBuyReqListDTO maPtBuyReqListDTO, User user)
    {
 
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        query.append("SELECT												");
        query.append("       '' 								seqNo		");
        query.append("     , '' 								isDelCheck	");
        query.append("     , x.ptprlist_id						ptPrListId	");
        query.append("     , x.part_id						    partId	    ");
        query.append("     , x.part_grade						partGrade	");
        query.append("     , SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"') AS partGradeDesc    ");
        query.append("     , x.currency                         curr        ");
        query.append("     , SFACODE_TO_DESC(x.currency,'CURRENCY','SYS','','"+user.getLangId()+"') currDesc    ");
        query.append("     , y.part_no                       	partNo		");
        query.append("     , y.erp_part_no                   	erpPartNo	");
        query.append("     , y.model                         	model		");
        query.append("     , SFACODE_TO_DESC(y.uom,'UOM','USR',y.comp_no,'"+user.getLangId()+"') 	uomDesc    	");
        query.append("     , y.seller                        	seller		");
        query.append("     , y.part_no||', '||x.description||', '||x.pt_size   partDescSize    ");
        query.append("     , x.description						partDesc	");
        query.append("     , x.pt_size							ptSize		");
        query.append("     , x.rec_qty							recQty		");
        query.append("     , x.remark							remark		");
        query.append("     , x.ptpritem_id						ptPrItemId	");
        query.append("     , NVL(x.unit_price,0)    			unitPrice   ");
        query.append("     , NVL(x.unit_price,0)*NVL(x.rec_qty,0)    totalPrice            ");
        query.append("     , x.emp_id      						appReqById	");
        query.append("     , (SELECT a.emp_name 							");
        query.append("          FROM TAEMP a 								");
        query.append("         WHERE a.comp_no = x.comp_no 					");
        query.append("          AND a.emp_id = x.emp_id) 		appReqByDesc ");
        query.append("    , x.po_on_qty                         poOnQty		");
        query.append("    , x.po_qty                            poQty		");
        query.append("    , x.gr_on_qty                         grOnQty		");
        query.append("    , x.gr_qty                            grQty		");
        query.append("    , x.ptpnlist_id                       ptpnlistId	");
        query.append("    , x.accnt_id                          accntId     ");
        query.append("    , (SELECT a.description                            ");
        query.append("         FROM TAACCOUNT a                             ");
        query.append("        WHERE a.comp_no = x.comp_no                   ");
        query.append("         AND a.accnt_id = x.accnt_id)     accntDesc   ");
        query.append("    , x.erp_pr_no                         erpPrNo	    ");
        query.append("    , x.erp_pr_seq                        erpPrSeq	");
        query.append("    , x.pur_group                         purGroup    ");
        query.append("    , SFACODE_TO_DESC(x.pur_group,'PUR_GROUP','USR',x.comp_no,'"+user.getLangId()+"')   purGroupDesc   ");
        query.append("    , x.accnt_type                        accntType   ");
        query.append("    , SFACODE_TO_DESC(x.accnt_type,'ACCNT_TYPE','SYS','','"+user.getLangId()+"')   accntTypeDesc   ");
        query.append("    , x.ctctr_id                          ctCtrId     ");
        query.append("    , (SELECT a.description 							");
        query.append("          FROM TACTCTR a 								");
        query.append("         WHERE a.comp_no = x.comp_no 					");
        query.append("          AND a.ctctr_id = x.ctctr_id) 	ctctrDesc   ");
        query.append("    , x.req_com_date                   AS reqComDate  ");
        query.append("    , z.ptprlist_status                AS ptPrListStatus  ");
        query.append("FROM TAPTPRITEM x LEFT OUTER JOIN TAPARTS y			");
        query.append("ON x.comp_no = y.comp_no						        ");
        query.append("AND x.part_id = y.part_id						        ");
        query.append("LEFT OUTER JOIN TAPTPRLIST z			                ");
        query.append("ON x.comp_no = z.comp_no						        ");
        query.append("AND x.ptprlist_id = z.ptprlist_id						");
        query.append("WHERE x.comp_no = '"+compNo+"'						");
        query.append(this.getWhere(maPtBuyReqHdrCommonDTO,maPtBuyReqListDTO, user));
        query.getOrderByQuery("x.ptpritem_id", "x.ptpritem_id desc", maPtBuyReqHdrCommonDTO.getOrderBy(), maPtBuyReqHdrCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maPtBuyReqHdrCommonDTO.getIsLoadMaxCount(), maPtBuyReqHdrCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPtBuyReqListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow, String deleteRowsExt, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	String ptprlist_id=deleteRow;
    	String ptpritem_id=deleteRowsExt;
    	
    	query.append("DELETE FROM TAPTPRITEM						");
    	query.append("WHERE ptpritem_id 	= '"+ptpritem_id+"'		");
    	query.append("  AND ptprlist_id 	= '"+ptprlist_id+"'		");
    	query.getAndQuery("comp_no", user.getCompNo());
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, MaPtBuyReqListDTO maPtBuyReqListDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
        String compNo = user.getCompNo();
        
    	query.getAndQuery("x.ptprlist_id", maPtBuyReqHdrCommonDTO.getPtPrListId());
    	if (!"".equals(maPtBuyReqListDTO.getPtPrItemId()))
        {
            query.getAndQuery("x.ptpritem_id", maPtBuyReqListDTO.getPtPrItemId());
            return query.toString();
        }
    	
    	query.getAndQuery("x.ptpnlist_id", maPtBuyReqListDTO.getPtPnListId());
    	
    	return query.toString();
    }
	
	@Override
    public String findTotalCount(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, MaPtBuyReqListDTO maPtBuyReqListDTO, User user) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAPTPRITEM x                                         ");
        query.append("WHERE x.comp_no = '"+user.getCompNo()+"'                      ");
        query.append(this.getWhere(maPtBuyReqHdrCommonDTO, maPtBuyReqListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

}