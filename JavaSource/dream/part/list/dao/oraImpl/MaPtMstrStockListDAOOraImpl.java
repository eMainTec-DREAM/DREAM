package dream.part.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.list.dao.MaPtMstrStockListDAO;
import dream.part.list.dto.MaPtMstrStockListDTO;

/**
 * 자재재고 - 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtMstrStockListDAOTarget"
 * @spring.txbn id="maPtMstrStockListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtMstrStockListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtMstrStockListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrStockListDTO
     * @return List
     */
    public List findPtMstrStockList(MaPtMstrStockListDTO maPtMstrStockListDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                                                                                                  ");
        query.append("        ''                                                                  AS seqNo                                                                                                    ");
        query.append("       ,''                                                                  AS isDelCheck                                                                                               ");
        query.append("       ,a.comp_no                                                           AS compNo                                                                                                   ");
        query.append("       ,a.wcode_id                                                          AS wcodeId                                                                                                  ");
        query.append("       ,a.wname                                                             AS wname                                                                                                    ");
        query.append("       ,a.part_id                                                           AS partId                                                                                                   ");
        query.append("       ,a.part_no                                                           AS partNo                                                                                                   ");
        query.append("       ,a.model                                                             AS model                                                                                                    ");
        query.append("       ,a.vendor_code                                                       AS oldNo                                                                                                    ");
        query.append("       ,a.description||','||a.pt_size                                       AS partNameSize                                                                                             ");
        query.append("       ,SFACODE_TO_DESC(a.part_group, 'PART_GROUP', 'USR', a.comp_no,'"+user.getLangId()+"')  AS partGroupDesc                                                                                            ");
        query.append("       ,a.description                                                       AS partDesc                                                                                                 ");
        query.append("       ,a.pt_size                                                           AS partSpec                                                                                                 ");
        query.append("       ,a.pt_abc_class                                                      AS ptAbcClass                                                                                               ");
        query.append("       ,SFACODE_TO_DESC(a.pt_abc_class, 'PT_ABC_CLASS', 'SYS', a.comp_no,'"+user.getLangId()+"')  AS ptAbcClassDesc                                                                     ");
        query.append("       ,nvl(a.a_stock_qty,0)                                                AS aStockQty                                                                                                ");
        query.append("       ,nvl(a.b_stock_qty,0)                                                AS bStockQty                                                                                                ");
        query.append("       ,nvl(a.tot_stock_qty,0)                                              AS totStockQty                                                                                              ");
        query.append("       ,nvl(a.pr_qty,0)                                                     AS prQty                                                                                                    ");
        query.append("       ,nvl(a.ordered_qty,0)                                                AS orderedQty                                                                                               ");
        query.append("       ,a.a_bin_no                                                          AS abinNo                                                                                                   ");
        query.append("       ,a.b_bin_no                                                          AS bbinNo                                                                                                   ");
        query.append("       ,nvl(a.max_safty_qty,0)                                              AS maxSaftyQty                                                                                              ");
        query.append("       ,nvl(a.min_safty_qty,0)                                              AS minSaftyQty                                                                                              ");
        query.append("       ,nvl(a.current_using_qty,0)                                          AS currentUsingQty                                                                                          ");
        query.append("       ,nvl(a.emg_iss_qty,0)                                                AS emgIssQty                                                                                                ");
        query.append("       ,CASE WHEN nvl(a.min_safty_qty,0) - ((nvl(a.tot_stock_qty,0)+(nvl(a.ordered_qty,0)+nvl(a.pr_qty,0))) - nvl(a.emg_iss_qty,0) - nvl(a.current_using_qty,0)) < 0 OR a.min_safty_qty IS NULL OR a.min_safty_qty = 0 THEN 0             ");
        query.append("           ELSE nvl(a.min_safty_qty,0) - ((nvl(a.tot_stock_qty,0)+(nvl(a.ordered_qty,0)+nvl(a.pr_qty,0))) - nvl(a.emg_iss_qty,0) - nvl(a.current_using_qty,0)) END AS underSaftyQty     ");
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
        query.append("                 ,(select  sum(nvl(bb.rec_qty,0) - nvl(bb.po_qty,0)) as pr_qty                                                                                                          ");
        query.append("                   from taptprlist aa inner join taptpritem bb on aa.comp_no = bb.comp_no and aa.ptprlist_id = bb.ptprlist_id                                                           ");
        query.append("                   where a.comp_no = bb.comp_no and a.part_id = bb.part_id and a.wcode_id=aa.wcode_id  and aa.ptprlist_status in ('C','POW','POC','GRW','GRC')                          ");
        query.append("                 )                                                                                                               AS pr_qty                                              ");
        query.append("                 ,(select  sum(nvl(bb.po_qty,0) - nvl(bb.gr_qty,0)) as po_qty                                                                                                           ");
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
        query.append("                    ,nvl(max(CASE WHEN a.part_grade='A' THEN a.stock_qty else NULL END),0) AS a_stock_qty                                                                                  ");
        query.append("                    ,nvl(max(CASE WHEN a.part_grade='B' THEN a.stock_qty else NULL END),0) AS b_stock_qty                                                                                  ");
        query.append("                    ,nvl(sum(a.stock_qty),0) AS tot_stock_qty                                                                                                                           ");
        query.append("                    ,max(CASE WHEN a.part_grade='A' THEN a.bin_no END)    AS a_bin_no                                                                                                   ");
        query.append("                    ,max(CASE WHEN a.part_grade='B' THEN a.bin_no END)    AS b_bin_no                                                                                                   ");
        query.append("                from taptstock a                                                                                                                                                        ");
        query.append("                where 1=1                                                                                                                                                               ");
        query.append("                group by a.comp_no, a.wcode_id, a.part_id                                                                                                                               ");
        query.append("            ) a inner join tawarehouse b on a.comp_no = b.comp_no and a.wcode_id = b.wcode_id                                                                                           ");
        query.append("                inner join taparts c on a.comp_no = c.comp_no and a.part_id = c.part_id                                                                                                 ");
        query.append("        where 1=1                                                                                                                                                                       ");
        query.append(this.getWhere(maPtMstrStockListDTO, user));
        query.append(") a                                                           ");
        query.getOrderByQuery("a.part_no", maPtMstrStockListDTO.getOrderBy(), maPtMstrStockListDTO.getDirection());
        
//        query.append("SELECT ''                                     AS seqNo      	");
//        query.append("       ,''                                    AS isDelCheck 	");
//        query.append("       ,a.comp_no AS compNo									");
//        query.append("       ,a.wcode_id AS wcodeId									");
//        query.append("       ,a.wname AS wname										");
//        query.append("       ,nvl(a.a_stock_qty,0) AS aStockQty						");
//        query.append("       ,nvl(a.b_stock_qty,0) AS bStockQty						");
//        query.append("       ,nvl(a.tot_stock_qty,0) AS totStockQty					");
//        query.append("       ,(nvl(a.ordered_qty,0)-nvl(a.reced_qty,0)) AS orderedQty");
//        query.append("       ,a.abin_no AS abinNo									");
//        query.append("       ,a.bbin_no AS bbinNo									");
//        query.append("       ,nvl(a.max_safty_qty,0) AS maxSaftyQty					");
//        query.append("       ,nvl(a.min_safty_qty,0) AS minSaftyQty					");
//        query.append("       ,nvl(a.current_using_qty,0) AS currentUsingQty			");
//        query.append("       ,CASE WHEN nvl(a.min_safty_qty,0) - ((nvl(a.tot_stock_qty,0)+(nvl(a.ordered_qty,0)-nvl(a.reced_qty,0))) - nvl(a.current_using_qty,0)) < 0 THEN 0	");
//        query.append("       	ELSE nvl(a.min_safty_qty,0) - ((nvl(a.tot_stock_qty,0)+(nvl(a.ordered_qty,0)-nvl(a.reced_qty,0))) - nvl(a.current_using_qty,0)) END AS underSaftyQty	");
//        query.append("FROM ( SELECT													");
//        query.append("       	a.comp_no											");
//        query.append("       	,a.wcode_id											");
//        query.append("       	,b.wname											");
//        query.append("       	,a.part_id											");
//        query.append("       	,MAX(CASE WHEN a.part_grade='A' THEN a.bin_no END) AS abin_no");
//        query.append("       	,MAX(CASE WHEN a.part_grade='B' THEN a.bin_no END) AS bbin_no");
//        query.append("       	,(SELECT safty_qty									");
//        query.append("       	FROM taptsaftystock aa								");
//        query.append("       	WHERE aa.comp_no = a.comp_no						");
//        query.append("       	AND aa.wcode_id = a.wcode_id						");
//        query.append("       	AND aa.part_id = a.part_id) AS min_safty_qty		");
//        query.append("       	,(SELECT max_safty_qty								");
//        query.append("       	FROM taptsaftystock aa								");
//        query.append("       	WHERE aa.comp_no = a.comp_no						");
//        query.append("       	AND aa.wcode_id = a.wcode_id						");
//        query.append("       	AND aa.part_id = a.part_id) AS max_safty_qty		");
//        query.append("       	,SUM(CASE WHEN a.part_grade='A' THEN a.stock_qty END) AS a_stock_qty");
//        query.append("       	,SUM(CASE WHEN a.part_grade='B' THEN a.stock_qty END) AS b_stock_qty");
//        query.append("       	,SUM(a.stock_qty) AS tot_stock_qty					");
//        query.append("			,(SELECT SUM(aa.po_qty)								");
//        query.append("				FROM TAPTPOLIST aa								");
//        query.append("				WHERE aa.comp_no= a.comp_no						");
//        query.getAndQuery("aa.polist_status", "P");
//        query.append("				AND aa.part_id = a.part_id           ) as ordered_qty");
//        query.append("			,(SELECT SUM(aa.rec_qty)							");
//        query.append("				FROM TAPTPOLIST aa								");
//        query.append("				WHERE aa.comp_no= a.comp_no						");
//        query.getAndQuery("aa.polist_status", "P");
//        query.append("				AND aa.part_id = a.part_id           ) as reced_qty");
//        query.append("       	,(SELECT SUM(aa.issue_qty)							");
//        query.append("       	FROM TAPTISSLIST aa									");
//        query.append("       	where aa.comp_no =a.comp_no							");
//        query.append("       	and aa.part_id = a.part_id							");
//        query.append("      	and aa.ptiss_status in ('W','X')) as current_using_qty	");
//        query.append("FROM taptstock a INNER JOIN tawarehouse b					    ");
//        query.append("ON a.comp_no = b.comp_no										");
//        query.append("AND a.wcode_id = b.wcode_id									");
//        query.append("WHERE 1=1														");
//        query.append(this.getWhere(maPtMstrStockListDTO, user));
//        query.append("GROUP BY a.comp_no,a.wcode_id,b.wname,a.part_id               ");
//        query.append(") a															");
//        query.getOrderByQuery("a.wname", maPtMstrStockListDTO.getOrderBy(), maPtMstrStockListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtMstrStockListDTO.getIsLoadMaxCount(), maPtMstrStockListDTO.getFirstRow()));
    }
    
    private String getWhere(MaPtMstrStockListDTO maPtMstrStockListDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        query.getAndQuery("b.wh_categ", "PART");
        query.getAndQuery("c.part_categ", "SPPT");
        query.getAndQuery("c.is_stock_control", "Y");

        if (!"".equals(maPtMstrStockListDTO.getWcodeId())||
        		!"".equals(maPtMstrStockListDTO.getPartId()))
        {
            query.getAndQuery("a.wcode_id", maPtMstrStockListDTO.getWcodeId());
            query.getAndQuery("a.part_id", maPtMstrStockListDTO.getPartId());
            return query.toString();
        }
//        query.getAndQuery("a.comp_no", user.getCompNo());
//        query.getAndQuery("a.part_id", maPtMstrStockListDTO.getPartId());
//        query.getAndQuery("b.wh_categ", "PART");
//        query.append("AND EXISTS(SELECT part_id             ");
//        query.append("           FROM TAPARTS               ");
//        query.append("           WHERE 1 = 1                ");
//        query.append("           AND comp_no = a.comp_no    ");
//        query.append("           AND part_id = a.part_id    ");
//        query.append("           AND part_categ = 'SPPT'    ");
//        query.append("           AND is_stock_control = 'Y' ");
//        query.append("          )                           ");
        
//        if (!"".equals(maPtMstrStockListDTO.getWcodeId()))
//        {
//            query.getAndQuery("a.wcode_id", maPtMstrStockListDTO.getWcodeId());
//            return query.toString();
//        }
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(MaPtMstrStockListDTO maPtMstrStockListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                      ");
        query.append("       COUNT(1)                                                      ");
        query.append("FROM ( 	SELECT                                                      ");
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
        query.append("                 ,(select  sum(nvl(bb.rec_qty,0) - nvl(bb.po_qty,0)) as pr_qty                                                                                                          ");
        query.append("                   from taptprlist aa inner join taptpritem bb on aa.comp_no = bb.comp_no and aa.ptprlist_id = bb.ptprlist_id                                                           ");
        query.append("                   where a.comp_no = bb.comp_no and a.part_id = bb.part_id and a.wcode_id=aa.wcode_id  and aa.ptprlist_status in ('C','POW','POC','GRW','GRC')                          ");
        query.append("                 )                                                                                                               AS pr_qty                                              ");
        query.append("                 ,(select  sum(nvl(bb.po_qty,0) - nvl(bb.gr_qty,0)) as po_qty                                                                                                           ");
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
        query.append("                    ,nvl(max(CASE WHEN a.part_grade='A' THEN a.stock_qty else NULL END),0) AS a_stock_qty                                                                                  ");
        query.append("                    ,nvl(max(CASE WHEN a.part_grade='B' THEN a.stock_qty else NULL END),0) AS b_stock_qty                                                                                  ");
        query.append("                    ,nvl(sum(a.stock_qty),0) AS tot_stock_qty                                                                                                                           ");
        query.append("                    ,max(CASE WHEN a.part_grade='A' THEN a.bin_no END)    AS a_bin_no                                                                                                   ");
        query.append("                    ,max(CASE WHEN a.part_grade='B' THEN a.bin_no END)    AS b_bin_no                                                                                                   ");
        query.append("                from taptstock a                                                                                                                                                        ");
        query.append("                where 1=1                                                                                                                                                               ");
        query.append("                group by a.comp_no, a.wcode_id, a.part_id                                                                                                                               ");
        query.append("            ) a inner join tawarehouse b on a.comp_no = b.comp_no and a.wcode_id = b.wcode_id                                                                                           ");
        query.append("                inner join taparts c on a.comp_no = c.comp_no and a.part_id = c.part_id                                                                                                 ");
        query.append("        where 1=1                                                                                                                                                                       ");
        query.append(this.getWhere(maPtMstrStockListDTO, user));
        query.append(") a                                                           ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}