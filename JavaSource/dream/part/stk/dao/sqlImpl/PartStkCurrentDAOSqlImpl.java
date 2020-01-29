package dream.part.stk.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.stk.dao.PartStkCurrentDAO;
import dream.part.stk.dto.PartStkCurrentDTO;

/**
 *  ¸ñ·Ï dao
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 * @spring.bean id="partStkCurrentDAOTarget"
 * @spring.txbn id="partStkCurrentDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartStkCurrentDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartStkCurrentDAO
{
 
	@Override   
	public List findPtStckList(PartStkCurrentDTO partStkCurrentDTO, User loginUser) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append(getColums(partStkCurrentDTO, loginUser));
        query.append(getTables(partStkCurrentDTO, loginUser));
        query.append(this.getWhere(partStkCurrentDTO, loginUser));
        query.append(getOrderBy(partStkCurrentDTO, loginUser));
        
		return getJdbcTemplate().queryForList(query.toString(partStkCurrentDTO.getIsLoadMaxCount(), partStkCurrentDTO.getFirstRow()));
	
	}   
   
	@Override
	public String findTotalCount(PartStkCurrentDTO partStkCurrentDTO, User user) throws Exception {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT								");
        query.append("    COUNT(1)							");
        query.append(getTables(partStkCurrentDTO, user));
        query.append(this.getWhere(partStkCurrentDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}

	@Override
	public String getColums(PartStkCurrentDTO partStkCurrentDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SELECT                                                                                                                                                                                  		");
		query.append("        ''                                                                  AS SEQNO                                                                                                    		");
		query.append("       ,''                                                                  AS ISDELCHECK                                                                                               		");
		query.append("       ,a.comp_no                                                           AS COMPNO                                                                                                   		");
		query.append("       ,a.wcode_id                                                          AS WCODEID                                                                                                  		");
		query.append("       ,a.wname                                                             AS WNAME                                                                                                    		");
		query.append("       ,a.part_id                                                           AS PARTID                                                                                                   		");
		query.append("       ,a.part_no                                                           AS PARTNO                                                                                                   		");
		query.append("       ,a.model                                                             AS MODEL                                                                                                    		");
		query.append("       ,a.vendor_code                                                       AS OLDNO                                                                                                    		");
		query.append("       ,a.description+','+a.pt_size                                         AS PARTNAMESIZE                                                                                             		");
		query.append("       ,a.pt_size                                         				  AS PTSIZE                                                                                             		");
		query.append("       ,dbo.SFACODE_TO_DESC(a.part_group, 'PART_GROUP', 'USR', a.comp_no,'"+user.getLangId()+"')  AS PARTGROUPDESC                                                                            ");
		query.append("       ,a.description                                                       AS PARTDESC                                                                                                 		");
		query.append("       ,a.pt_size                                                           AS PARTSPEC                                                                                                 		");
		query.append("       ,a.pt_abc_class                                                      AS PTABCCLASS                                                                                               		");
		query.append("       ,dbo.SFACODE_TO_DESC(a.pt_abc_class, 'PT_ABC_CLASS', 'SYS', a.comp_no,'"+user.getLangId()+"')  AS PTABCCLASSDESC                                                                     		");
		query.append("       ,isnull(a.a_stock_qty,0)                                                AS ASTOCKQTY                                                                                                		");
		query.append("       ,isnull(a.b_stock_qty,0)                                                AS BSTOCKQTY                                                                                                		");
		query.append("       ,isnull(a.tot_stock_qty,0)                                              AS TOTSTOCKQTY                                                                                              		");
		query.append("       ,isnull(a.pr_qty,0)                                                     AS PRQTY                                                                                                    		");
		query.append("       ,isnull(a.ordered_qty,0)                                                AS ORDEREDQTY                                                                                               		");
		query.append("       ,a.a_bin_no                                                          	 AS ABINNO                                                                                                   			");
		query.append("       ,a.b_bin_no                                                          	 AS BBINNO                                                                                                   			");
		query.append("       ,isnull(a.max_safty_qty,0)                                              AS MAXSAFTYQTY                                                                                              		");
		query.append("       ,isnull(a.min_safty_qty,0)                                              AS MINSAFTYQTY                                                                                              		");
		query.append("       ,isnull(a.current_using_qty,0)                                          AS CURRENTUSINGQTY                                                                                          		");
		query.append("       ,isnull(a.emg_iss_qty,0)                                                AS EMGISSQTY                                                                                                		");
		query.append("       ,CASE WHEN isnull(a.min_safty_qty,0) - ((isnull(a.tot_stock_qty,0)+(isnull(a.ordered_qty,0)+isnull(a.pr_qty,0))) - isnull(a.emg_iss_qty,0) - isnull(a.current_using_qty,0)) < 0 OR a.min_safty_qty IS NULL OR a.min_safty_qty = 0 THEN 0 		");
		query.append("           ELSE isnull(a.min_safty_qty,0) - ((isnull(a.tot_stock_qty,0)+(isnull(a.ordered_qty,0)+isnull(a.pr_qty,0))) - isnull(a.emg_iss_qty,0) - isnull(a.current_using_qty,0)) END AS UNDERSAFTYQTY     		");

		
		return query.toString();
	}

	@Override
	public String getTables(PartStkCurrentDTO partStkCurrentDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("FROM (                                                                                                                                                                                  ");
        query.append("        select                                                                                                                                                                          ");
        query.append("                a.comp_no                                                                                                                                                               ");
        query.append("               ,a.wcode_id                                                                                                                                                              ");
        query.append("               ,b.wname                                                                                                                                                                 ");
        query.append("               ,a.part_id                                                                                                                                                               ");
        query.append("               ,c.part_no                                                                                                                                                               ");
        query.append("               ,c.model                                                                                                                                                                 ");
        query.append("               ,c.vendor_code                                                                                                                                                           ");
        query.append("               ,c.part_group                                                                                                                                                            ");
        query.append("               ,c.description                                                                                                                                                           ");
        query.append("               ,c.pt_size                                                                                                                                                               ");
        query.append("               ,c.pt_abc_class                                                                                                                                                          ");
        query.append("               ,a.a_stock_qty                                                                                                                                                           ");
        query.append("               ,a.b_stock_qty                                                                                                                                                           ");
        query.append("               ,a.tot_stock_qty                                                                                                                                                         ");
        query.append("               ,a.a_bin_no                                                                                                                                                              ");
        query.append("               ,a.b_bin_no                                                                                                                                                              ");
        query.append("               ,(SELECT safty_qty                                                                                                                                                       ");
        query.append("                  FROM taptsaftystock aa                                                                                                                                                ");
        query.append("                  WHERE aa.comp_no = a.comp_no                                                                                                                                          ");
        query.append("                     AND aa.wcode_id = a.wcode_id                                                                                                                                       ");
        query.append("                     AND aa.part_id = a.part_id)    AS min_safty_qty                                                                                                                    ");
        query.append("                ,(SELECT max_safty_qty                                                                                                                                                  ");
        query.append("                  FROM taptsaftystock aa                                                                                                                                                ");
        query.append("                  WHERE aa.comp_no = a.comp_no                                                                                                                                          ");
        query.append("                    AND aa.wcode_id = a.wcode_id                                                                                                                                        ");
        query.append("                    AND aa.part_id = a.part_id)    AS max_safty_qty                                                                                                                     ");
        query.append("                 ,(select  sum(isnull(bb.rec_qty,0) - isnull(bb.po_qty,0)) as pr_qty                                                                                                          ");
        query.append("                   from taptprlist aa inner join taptpritem bb on aa.comp_no = bb.comp_no and aa.ptprlist_id = bb.ptprlist_id                                                           ");
        query.append("                   where a.comp_no = bb.comp_no and a.part_id = bb.part_id and a.wcode_id=aa.wcode_id  and aa.ptprlist_status in ('C','POW','POC','GRW','GRC')                          ");
        query.append("                 )                                                                                                               AS pr_qty                                              ");
        query.append("                 ,(select  sum(isnull(bb.po_qty,0) - isnull(bb.gr_qty,0)) as po_qty                                                                                                           ");
        query.append("                   from TAPTPOLIST aa inner join TAPTPOITEM bb on aa.comp_no = bb.comp_no and aa.polist_id = bb.polist_id                                                               ");
        query.append("                   where a.comp_no = bb.comp_no and a.part_id = bb.part_id and a.wcode_id=aa.wcode_id  and aa.polist_status in ('C','GRW','GRC')                                        ");
        query.append("                 )                                                                                                               AS ordered_qty                                         ");
        query.append("                  ,(SELECT SUM(aa.issue_qty)                                                                                                                                            ");
        query.append("                     FROM TAPTISSLIST aa                                                                                                                                                ");
        query.append("                     where aa.comp_no =a.comp_no                                                                                                                                        ");
        query.append("                         and aa.part_id = a.part_id                                                                                                                                     ");
        query.append("                         and a.wcode_id = aa.wcode_id                                                                                                                                   ");
        query.append("                         and aa.ptiss_status in ('W','X')                                                                                                                               ");
        query.append("                    )                                                                                                           AS  emg_iss_qty                                         ");
        query.append("                  ,(SELECT SUM(aa.use_qty)                                                                                                                                              ");
        query.append("                     FROM TAWOPARTS aa inner join  TAWORKORDER bb on aa.comp_no = bb.comp_no                                                                                            ");
        query.append("                                                                     and aa.wkor_id = bb.wkor_id                                                                                        ");
        query.append("                                                                     and bb.is_deleted= 'N'                                                                                             ");
        query.append("                                                                     and bb.wo_status in ( 'PRW','PRWDA','PRWRA','P','PRWOA','PRP' )                                                    ");
        query.append("                     where aa.comp_no =a.comp_no                                                                                                                                        ");
        query.append("                              and aa.part_id = a.part_id                                                                                                                                ");
        query.append("                              and  aa.wcode_id = a.wcode_id                                                                                                                             ");
        query.append("                              and aa.ptisslist_id is null                                                                                                                               ");
        query.append("                    )                                                                                                            AS current_using_qty                                   ");
        query.append("        from (                                                                                                                                                                          ");
        query.append("                select                                                                                                                                                                  ");
        query.append("                    a.comp_no                                                                                                                                                           ");
        query.append("                    ,a.wcode_id                                                                                                                                                         ");
        query.append("                    ,a.part_id                                                                                                                                                          ");
        query.append("                    ,isnull(max(CASE WHEN a.part_grade='A' THEN a.stock_qty else NULL END),0) AS a_stock_qty                                                                               ");
        query.append("                    ,isnull(max(CASE WHEN a.part_grade='B' THEN a.stock_qty else NULL END),0) AS b_stock_qty                                                                               ");
        query.append("                    ,isnull(sum(a.stock_qty),0) AS tot_stock_qty                                                                                                                           ");
        query.append("                    ,max(CASE WHEN a.part_grade='A' THEN a.bin_no END)    AS a_bin_no                                                                                                   ");
        query.append("                    ,max(CASE WHEN a.part_grade='B' THEN a.bin_no END)    AS b_bin_no                                                                                                   ");
        query.append("                from taptstock a                                                                                                                                                        ");
        query.append("                where 1=1                                                                                                                                                               ");
        query.append("                group by a.comp_no, a.wcode_id, a.part_id                                                                                                                               ");
        query.append("            ) a inner join tawarehouse b on a.comp_no = b.comp_no and a.wcode_id = b.wcode_id                                                                                           ");
        query.append("                inner join taparts c on a.comp_no = c.comp_no and a.part_id = c.part_id                                                                                              	  ");
        query.append("        		  where 1=1                                                                                                                                                                       ");
        query.getLikeQuery("a.a_bin_no", partStkCurrentDTO.getFilterBinNo());
        query.getAndQuery("b.wh_categ", "PART");
        query.getAndKeyQuery("b.wcode", partStkCurrentDTO.getFilterWcode().trim()); 
        query.getLikeQuery("b.plant", partStkCurrentDTO.getFilterPlant()); 
        query.getAndQuery("c.part_categ", "SPPT");
        query.getAndQuery("c.is_use", "Y");
        query.getAndQuery("c.is_stock_control", "Y");
        query.append(") a                                                               ");
		
		return query.toString();
	}

	@Override
	public String getOrderBy(PartStkCurrentDTO partStkCurrentDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.getOrderByQuery("a.part_id","a.part_no", partStkCurrentDTO.getOrderBy(), partStkCurrentDTO.getDirection());		
		
		return query.toString();
	}

	@Override
	public String getWhere(PartStkCurrentDTO partStkCurrentDTO, User user) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		  	query.append("WHERE 1=1 ");
	        query.getAndKeyQuery("a.comp_no",partStkCurrentDTO.getFilterCompNo().trim());
	        if(!"".equals(partStkCurrentDTO.getPartId())||!"".equals(partStkCurrentDTO.getWcodeId())) {
	        	query.getAndQuery("a.part_id",partStkCurrentDTO.getPartId());
	        	query.getAndQuery("a.wcode_id",partStkCurrentDTO.getWcodeId());
	        	return query.toString();
	        }

	        if(!"".equals(partStkCurrentDTO.getFilterPartNo())
	                || !"".equals(partStkCurrentDTO.getFilterPartDesc())
	                || !"".equals(partStkCurrentDTO.getFilterMaker()) 
	                || !"".equals(partStkCurrentDTO.getFilterModel()) 
	                || !"".equals(partStkCurrentDTO.getFilterPartGroupDesc())
	                || !"".equals(partStkCurrentDTO.getFilterVendorPtCode())
	                || !"".equals(partStkCurrentDTO.getFilterPartGroupDesc()))
	        {
	            query.append(" AND  a.part_id IN (SELECT part_id FROM TAPARTS       ");
	            query.append("                    WHERE  comp_no = a.comp_no        ");
	            query.getLikeQuery("part_no", partStkCurrentDTO.getFilterPartNo()); 
	            query.getLikeQuery("full_desc", partStkCurrentDTO.getFilterPartDesc()); 
	            query.getLikeQuery("maker", partStkCurrentDTO.getFilterMaker()); 
	            query.getLikeQuery("model", partStkCurrentDTO.getFilterModel()); 
	            query.getLikeQuery("vendor_code", partStkCurrentDTO.getFilterVendorPtCode()); 
	            query.getAndQuery("part_group", partStkCurrentDTO.getFilterPartGroupDesc().trim()); 
	             
	            query.append("					   )						        ");
	        }
	        
		return query.toString();
	}
}