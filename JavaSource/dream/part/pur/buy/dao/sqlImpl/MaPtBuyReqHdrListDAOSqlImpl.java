package dream.part.pur.buy.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.pur.buy.dao.MaPtBuyReqHdrListDAO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;

/**
 * 구매신청 dao
 * @author  kim21017
 * @version $Id: MaPtBuyReqHdrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPtBuyReqHdrListDAOTarget"
 * @spring.txbn id="maPtBuyReqHdrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtBuyReqHdrListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtBuyReqHdrListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPtBuyReqHdrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrCommonDTO
     * @return List
     */
    public List findBuyList(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                   								");
        query.append("       '' AS seqNo,									");
        query.append("       '' AS isDelCheck,								");
        query.append("       x.ptprlist_id AS ptprlistId,					");
        query.append("       x.ptprlist_no AS ptprlistNo,					");
        query.append("       x.description AS ptPrListDesc,					");
        query.append("       x.ptprlist_status AS ptPrListStatusId,			");
        query.append("		dbo.SFACODE_TO_DESC(x.ptprlist_status,'PTPRLIST_STATUS','SYS','','"+user.getLangId()+"') ptPrListStatus,	");
        query.append("       (SELECT description 							");
        query.append("          FROM TAVENDOR a								");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.vendor_id = x.vendor_id) vendorDesc,	");
        query.append("       (SELECT description 							");
        query.append("          FROM TADEPT a								");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.dept_id = x.dept_id) reqDept,		");
        query.append("       (SELECT emp_name 								");
        query.append("          FROM TAEMP a								");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.emp_id = x.user_id)  reqUser,		");
        query.getDate("x.req_date", "reqDate,");
        query.append("       x.remark AS remark								");
        query.append("       ,x.plant			 		AS  plant			");
        query.append("       ,(SELECT a.description          				");
        query.append("          FROM TAPLANT a                  			");
        query.append("          WHERE a.comp_no = x.comp_no      			");
        query.append("          AND a.plant = x.plant            			");
        query.append("         )                    		plantDesc   	");
        query.append("     , (SELECT ISNULL(SUM(a.rec_qty*a.unit_price),0) 	");
        query.append("          FROM TAPTPRITEM a 							");
        query.append("         WHERE a.comp_no = x.comp_no 					");
        query.append("           AND a.ptprlist_id = x.ptprlist_id)    totalAmt		");
        query.append("      , (SELECT emp_name                                                      ");
        query.append("           FROM TAEMP a                                                       ");
        query.append("          WHERE a.comp_no = x.comp_no                                         ");
        query.append("            AND a.emp_id  = x.rec_by)                         AS recByName    ");
        query.append("      , (SELECT description                                                   ");
        query.append("           FROM TAPLANT a                                                     ");
        query.append("          WHERE a.comp_no = x.comp_no                                         ");
        query.append("            AND a.plant   = x.rec_plant)                      AS recPlantDesc ");
        query.append("      , (SELECT description                                                   ");
        query.append("           FROM tadept y                                                      ");
        query.append("          WHERE y.comp_no = x.comp_no                                         ");
        query.append("            AND y.dept_id = (SELECT dept_id                                   ");
        query.append("                               FROM taemp z                                   ");
        query.append("                              WHERE z.comp_no = y.comp_no                     ");
        query.append("                                AND z.emp_id  = x.rec_by))    AS recDeptDesc  ");
        query.append("FROM   TAPTPRLIST x        							");
        query.append("WHERE  1=1               								");
        query.append(this.getWhere(maPtBuyReqHdrCommonDTO, user));
        //query.append("ORDER by x.ptprlist_no desc       					");
        query.getOrderByQuery("x.ptprlist_id", "x.ptprlist_no desc, x.ptprlist_id", maPtBuyReqHdrCommonDTO.getOrderBy(), maPtBuyReqHdrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtBuyReqHdrCommonDTO.getIsLoadMaxCount(), maPtBuyReqHdrCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaPtBuyReqHdrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = user.getCompNo();
        
        query.getAndQuery("x.comp_no", compNo);
        if (!"".equals(maPtBuyReqHdrCommonDTO.getPtPrListId()))
        {
            query.getAndQuery("x.ptprlist_id", maPtBuyReqHdrCommonDTO.getPtPrListId());
            return query.toString();
        }
        query.getLikeQuery("x.description", maPtBuyReqHdrCommonDTO.getFilterPtPrListDesc());
        
        if(!"".equals(maPtBuyReqHdrCommonDTO.getFilterPartId())){
        	query.append("AND x.ptprlist_id IN (SELECT ptprlist_id FROM TAPTPRITEM WHERE comp_no = '"+compNo+"' AND part_id = '"+maPtBuyReqHdrCommonDTO.getFilterPartId()+"')");
        }else if(!"".equals(maPtBuyReqHdrCommonDTO.getFilterPartDesc())){
        	query.append("AND x.ptprlist_id IN (SELECT ptprlist_id FROM TAPTPRITEM WHERE comp_no = '"+compNo+"' AND part_id IN (SELECT part_id FROM TAPARTS WHERE comp_no='"+compNo+"' ");
        	query.append("				AND description like '%"+maPtBuyReqHdrCommonDTO.getFilterPartDesc()+"%'))");
        }
        
        query.getSysCdQuery("x.ptprlist_status", maPtBuyReqHdrCommonDTO.getFilterPtPrListStatusId(), maPtBuyReqHdrCommonDTO.getFilterPtPrListStatusDesc(), "PTPRLIST_STATUS", compNo, user.getLangId());
        query.getAndQuery("x.ptprlist_no", maPtBuyReqHdrCommonDTO.getFilterPtPrListNo());
        query.getAndDateQuery("x.req_date", maPtBuyReqHdrCommonDTO.getFilterStartReqDate(), maPtBuyReqHdrCommonDTO.getFilterEndReqDate());
        query.getCodeLikeQuery("x.user_id", "(SELECT a.user_name FROM TAUSER a WHERE a.user_id = x.user_id AND a.comp_no='"+compNo+"')", 
        		maPtBuyReqHdrCommonDTO.getFilterUserId(), maPtBuyReqHdrCommonDTO.getFilterUserDesc());
        query.getDeptLevelQuery("x.dept_id", maPtBuyReqHdrCommonDTO.getFilterDeptId(), maPtBuyReqHdrCommonDTO.getFilterDeptDesc(), compNo);
        // 품명/규격
        if(!"".equals(maPtBuyReqHdrCommonDTO.getFilterPtDescSize()))
        {
            query.append(" AND  x.ptprlist_id IN (SELECT ptprlist_id FROM TAPTPRITEM WHERE comp_no = '"+compNo+"' AND part_id IN (SELECT part_id FROM TAPARTS       ");
            query.append("                    WHERE  comp_no = x.comp_no        ");
            query.getLikeQuery("full_desc", maPtBuyReqHdrCommonDTO.getFilterPtDescSize());
            query.append("                    ))                                 ");      
        }
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+compNo+"' AND a.plant = x.plant )", 
        		maPtBuyReqHdrCommonDTO.getFilterPlantId(), maPtBuyReqHdrCommonDTO.getFilterPlantDesc());
        
        //현상요청#
        if(!"".equals(maPtBuyReqHdrCommonDTO.getFilterPtPnListNo()) && !"".equals(maPtBuyReqHdrCommonDTO.getFilterPtPnListNo()))
        {
            query.append(" AND x.ptprlist_id IN (SELECT ptprlist_id							");
            query.append("                        FROM TAPTPRITEM							");
            query.append("                        WHERE 1 = 1                       		");
            query.getAndQuery("comp_no", compNo);
            query.append("                        AND ptpnlist_id IN (SELECT ptpnlist_id	");
            query.append("                                            FROM TAPTPNLIST		");
            query.append("                        					  WHERE 1 = 1           ");
            query.getAndQuery("comp_no", compNo);
            query.getLikeQuery("ptpnlist_no", maPtBuyReqHdrCommonDTO.getFilterPtPnListNo());
            query.append("                                            )						");
            query.append("                       )											");
        }
        
        //ERP PR번호
        query.getAndQuery("x.erp_pr_no", maPtBuyReqHdrCommonDTO.getFilterErpPrNo());
        
        // 접수자
        query.getCodeLikeQuery("x.rec_by", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.rec_by AND a.comp_no='"+ user.getCompNo() +"')", 
                maPtBuyReqHdrCommonDTO.getFilterRecById(), maPtBuyReqHdrCommonDTO.getFilterRecByDesc());
        
        // 접수공장
        query.getCodeLikeQuery("x.rec_plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+ user.getCompNo() +"' AND a.plant = x.rec_plant )", 
                maPtBuyReqHdrCommonDTO.getFilterRecPlantId(), maPtBuyReqHdrCommonDTO.getFilterRecPlantDesc());
        
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteBuy(String id, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TAPTPRLIST			");
    	query.append("WHERE ptprlist_id = '"+id+"'		");
    	query.getAndQuery("comp_no", user.getCompNo());
    	query.getAndQuery("ptprlist_status", "W");
    	query.getAndQuery("user_id",user.getEmpId());
    	
    	return this.getJdbcTemplate().update(query.toString());
    }

	@Override
	public String findTotalCount(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user)
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                   			");
        query.append("       COUNT(1)					");
        query.append("FROM   TAPTPRLIST x        		");
        query.append("WHERE  1=1               			");
        query.append(this.getWhere(maPtBuyReqHdrCommonDTO, user));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
    	
		return QuerySqlBuffer.listToString(resultList);
	}
}