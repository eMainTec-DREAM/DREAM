package dream.part.stk.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.stk.dao.MaPtStckListDAO;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.form.MaPtStckListForm;

/**
 * ������� - ��� dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtStckListDAOTarget"
 * @spring.txbn id="maPtStckListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtStckListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtStckListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckCommonDTO
     * @return List
     */
    public List findPtStckList(MaPtStckCommonDTO maPtStckCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maPtStckCommonDTO.getCompNo();
        
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
        query.append("       ,CASE WHEN nvl(a.min_safty_qty,0) - ((nvl(a.tot_stock_qty,0)+(nvl(a.ordered_qty,0)+nvl(a.pr_qty,0))) - nvl(a.emg_iss_qty,0) - nvl(a.current_using_qty,0)) < 0 OR a.min_safty_qty IS NULL OR a.min_safty_qty = 0 THEN 0 ");
        query.append("           ELSE nvl(a.min_safty_qty,0) - ((nvl(a.tot_stock_qty,0)+(nvl(a.ordered_qty,0)+nvl(a.pr_qty,0))) - nvl(a.emg_iss_qty,0) - nvl(a.current_using_qty,0)) END AS underSaftyQty     ");
        query.append("FROM (                                                                                                                                                                                  ");
        query.append("        select                                                                                                                                                                          ");
        query.append("                a.comp_no                                                                                                                                                               ");
        query.append("               ,a.wcode_id                                                                                                                                                              ");
        query.append("               ,b.wname                                                                                                                                                                 ");
        query.append("               ,a.part_id                                                                                                                                                               ");
        query.append("               ,c.part_no                                                                                                                                                               ");
        query.append("               ,c.model                                                                                                                                                                 ");
        query.append("               ,c.maker                                                                                                                                                                 ");
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
        query.append(this.getWhere(maPtStckCommonDTO, user));
        if(!"".equals(maPtStckCommonDTO.getFilterQtyCnt())){
        	query.append("AND nvl(a.tot_stock_qty,0) >= "+maPtStckCommonDTO.getFilterQtyCnt()+"");
        }
        query.append(") a                                                               ");
        query.append("WHERE 1=1                                                         ");
        if("Y".equals(maPtStckCommonDTO.getFilterSaftyYN())){
        	query.append("AND (nvl(a.min_safty_qty,0) - ((nvl(a.tot_stock_qty,0)+(nvl(a.ordered_qty,0)+nvl(a.pr_qty,0))) - nvl(a.emg_iss_qty,0) - nvl(a.current_using_qty,0))) > 0	");
        	query.append("AND a.min_safty_qty IS NOT NULL   ");
        	query.append("AND a.min_safty_qty != 0	        ");
        }else if("N".equals(maPtStckCommonDTO.getFilterSaftyYN())){
        	query.append("AND ( (nvl(a.min_safty_qty,0) - ((nvl(a.tot_stock_qty,0)+(nvl(a.ordered_qty,0)+nvl(a.pr_qty,0))) - nvl(a.emg_iss_qty,0) - nvl(a.current_using_qty,0))) <= 0	");
        	query.append("    OR a.min_safty_qty IS NULL	");
        	query.append("    OR a.min_safty_qty = 0 )	    ");
        }
        query.getOrderByQuery("a.part_no", maPtStckCommonDTO.getOrderBy(), maPtStckCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtStckCommonDTO.getIsLoadMaxCount(), maPtStckCommonDTO.getFirstRow()));
        
    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePtStck(String compNo, String wcodeId, String partId)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPTSTOCK			              ");
    	query.append("WHERE  comp_no       = '"+compNo+"'		  ");
    	query.append("  AND  wcode_id      = '"+wcodeId+"'		  ");
    	query.append("  AND  part_id       = '"+partId+"'		  ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    /**
     * req hdr create
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partId
     * @param user
     * @return
     */
    public int reqHdrPtStck(MaPtStckListForm maPtStckListForm, String partId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	MaPtStckCommonDTO maPtStckCommonDTO = maPtStckListForm.getMaPtStckCommonDTO();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAPTPRLIST (                                                                                  ");
    	query.append("    comp_no                                                                                               ");
    	query.append("    ,ptprlist_id                                                                                          ");
    	query.append("    ,ptprlist_no                                                                                          ");
    	query.append("    ,description                                                                                          ");
    	query.append("    ,ptprlist_status                                                                                      ");
    	query.append("    ,dept_id                                                                                              ");
    	query.append("    ,req_date                                                                                             ");
    	query.append("    ,user_id                                                                                              ");
    	query.append("    ,plant                                                                                              ");
    	query.append("    ) VALUES(                                                                                             ");
    	query.append("     ?                                                                                                    ");
    	query.append("    ,?                                                                                                    ");
    	query.append("    ,?                                                                                                    ");
    	query.append("    ,(SELECT description FROM TAPARTS WHERE comp_no = '"+user.getCompNo()+"' AND part_id = '"+partId+"')  ");
    	query.append("    ,?                                                                                                    ");
    	query.append("    ,?                                                                                                    ");
    	query.append("    ,TO_CHAR(sysdate,'yyyymmdd')                                                                          ");
    	query.append("    ,?                                                                                                    ");
    	query.append("    ,?                                                                                                    ");
    	query.append("    )                                                                                                     ");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			maPtStckCommonDTO.getReqIdx(),
    			maPtStckCommonDTO.getReqIdx(),
    			"W",
    			user.getDeptId(),
    			user.getUserId()
    			,user.getPlant()
    	};
    	rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }

    /**
     * req dtl create
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partId
     * @param user
     * @return
     */
    public int reqDtlPtStck(MaPtStckListForm maPtStckListForm, String partId, String recQty, User user, String partGrade)
    {
    	QueryBuffer query = new QueryBuffer();
    	MaPtStckCommonDTO maPtStckCommonDTO = maPtStckListForm.getMaPtStckCommonDTO();
    	int rtnValue  = 0;
    	
    	query.append("MERGE INTO TAPTPRITEM a                                                                                               ");
        query.append("USING(    SELECT  ?                                                                                    comp_no        ");
        query.append("  ,(select cdsysd_no from tacdsysd where list_Type='CURRENCY' and param1='Y')                          currency       ");
        query.append("  ,?                                                                                                   ptprlist_id    ");
        query.append("  ,(SELECT description FROM TAPARTS WHERE comp_no = '"+user.getCompNo()+"' AND part_id = '"+partId+"') description    ");
        query.append("  ,?                                                                                                   part_id        ");
        query.append("  ,(SELECT pt_size FROM TAPARTS WHERE comp_no = '"+user.getCompNo()+"' AND part_id = '"+partId+"')     pt_size        ");
        query.append("  ,?                                                                                                   rec_qty        ");
        query.append("  ,(SELECT NVL(x.last_price,0)                                                                                        ");
        query.append("    FROM   TAPARTS x                                                                                                  ");
        query.append("    WHERE 1=1                                                                                                         ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getAndNumKeyQuery("x.part_id", partId);
        query.append("    )                                                                                                  unit_price     ");
        query.append("  ,?                                                                                                   part_grade     ");
        query.append("          FROM DUAL) b                                                                                                ");
        query.append("ON(   a.comp_no = b.comp_no                                                                                           ");
        query.append("  AND a.ptprlist_id = b.ptprlist_id                                                                                   ");
        query.append("  AND a.part_id = b.part_id  )                                                                                        ");
        query.append("WHEN MATCHED THEN                                                                                                     ");
        query.append("  UPDATE SET  a.rec_qty = a.rec_qty+b.rec_qty                                                                         ");
        query.append("WHEN NOT MATCHED THEN                                                                                                 ");
        query.append("  INSERT (a.comp_no,          a.currency,         a.ptpritem_id,          a.ptprlist_id,                              ");
        query.append("          a.description,      a.part_id,          a.pt_size,              a.rec_qty,                                  ");
        query.append("          a.unit_price,       a.part_grade                                                )                           ");
        query.append("  VALUES (b.comp_no,          b.currency,         SQAPTPRITEM_ID.nextval, b.ptprlist_id,                              ");
        query.append("          b.description,      b.part_id,          b.pt_size,              b.rec_qty,                                  ");
        query.append("          b.unit_price,       b.part_grade                                                )                           ");
        
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			maPtStckCommonDTO.getReqIdx(),
    			partId,
    			recQty,
    			partGrade
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    /**
     * Filter ����
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtStckCommonDTO maPtStckCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
      
        query.getAndQuery("a.comp_no", user.getCompNo());
        query.getAndQuery("b.wh_categ", "PART");
        query.getAndQuery("c.part_categ", "SPPT");
        query.getAndQuery("c.is_stock_control", "Y");

        if (!"".equals(maPtStckCommonDTO.getWcodeId())||
        		!"".equals(maPtStckCommonDTO.getPartId()))
        {
            query.getAndQuery("a.wcode_id", maPtStckCommonDTO.getWcodeId());
            query.getAndQuery("a.part_id", maPtStckCommonDTO.getPartId());
            return query.toString();
        }
        
        // â���
        if(!"".equals(maPtStckCommonDTO.getFilterWcodeId()))
        {
            query.getLikeQuery("a.wcode_id", maPtStckCommonDTO.getFilterWcodeId());
        }
        else if(!"".equals(maPtStckCommonDTO.getFilterWname()))
        {
            query.append(" AND  a.wcode_id IN (SELECT wcode_id FROM TAWAREHOUSE ");
            query.append("                     WHERE  comp_no = a.comp_no       ");
            query.getLikeQuery("wname", maPtStckCommonDTO.getFilterWname());
            query.append("					   )						        ");
        }
  
        //�����
        query.getCodeLikeQuery("b.plant", "(SELECT a.description FROM TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = b.plant )", 
        		maPtStckCommonDTO.getFilterPlantId(), maPtStckCommonDTO.getFilterPlantDesc());
        
        if(!"".equals(maPtStckCommonDTO.getFilterPartNo())
                || !"".equals(maPtStckCommonDTO.getFilterPartDesc())
                || !"".equals(maPtStckCommonDTO.getFilterMaker()) 
                || !"".equals(maPtStckCommonDTO.getFilterModel()) 
                || !"".equals(maPtStckCommonDTO.getFilterPartGroupDesc())
                || !"".equals(maPtStckCommonDTO.getFilterPtAbcClassDesc())
                || !"".equals(maPtStckCommonDTO.getFilterVendorPtCode()) )
        {
            query.append(" AND  a.part_id IN (SELECT part_id FROM TAPARTS       ");
            query.append("                    WHERE  comp_no = a.comp_no        ");
            query.getLikeQuery("part_no", maPtStckCommonDTO.getFilterPartNo()); 
            query.getLikeQuery("full_desc", maPtStckCommonDTO.getFilterPartDesc()); 
            query.getSysCdQuery("pt_abc_class", maPtStckCommonDTO.getFilterPtAbcClass(), maPtStckCommonDTO.getFilterPtAbcClassDesc(), "PT_ABC_CLASS", maPtStckCommonDTO.getCompNo(),user.getLangId());
            query.getLikeQuery("maker", maPtStckCommonDTO.getFilterMaker()); 
            query.getLikeQuery("model", maPtStckCommonDTO.getFilterModel()); 
            query.getLikeQuery("vendor_code", maPtStckCommonDTO.getFilterVendorPtCode()); 
            // ����׷�
            query.getUsrCdQuery("part_group", maPtStckCommonDTO.getFilterPartGroup(), maPtStckCommonDTO.getFilterPartGroupDesc(), "PART_GROUP", maPtStckCommonDTO.getCompNo(), user.getLangId());
            
            query.append("					   )						        ");
        }
        
        //����
        if(!"".equals(maPtStckCommonDTO.getFilterEquipId()))
        {
            query.append(" AND c.part_id IN(SELECT a.part_id FROM TAEQPART a       ");
            query.append("                            WHERE a.comp_no=c.comp_no       ");
            query.getAndQuery("a.equip_id", maPtStckCommonDTO.getFilterEquipId());
            query.append("                            )     ");
        }
        else if(!"".equals(maPtStckCommonDTO.getFilterEquipDesc()))
        {
            query.append(" AND c.part_id IN(SELECT DISTINCT a.part_id FROM TAEQPART a       ");
            query.append("                            WHERE a.comp_no=c.comp_no         ");
            query.append("                            AND a.equip_id IN(SELECT aa.equip_id FROM TAEQUIPMENT aa      ");
            query.append("                                                    WHERE aa.comp_no=a.comp_no        ");
            query.getLikeQuery("aa.description", maPtStckCommonDTO.getFilterEquipDesc());
            query.append("                                                    )     ");
            query.append("                            )     ");
        }
        
        return query.toString();
    }

    public int insertQrCode(String id, String wcodeId,String compNo, User loginUser) {
		QueryBuffer query = new QueryBuffer();

		query = new QueryBuffer();
    	
		 query.append("INSERT INTO TAPTBCPRINT (   ");
	        query.append("        comp_no               ");
	        query.append("        ,user_id              ");
	        query.append("        ,part_id             ");
	        query.append("        ,wcode_id             ");
	        query.append(")                             ");
	        query.append("VALUES (                      ");
	        query.append("        :comp_no              ");
	        query.append("        ,:user_id             ");
	        query.append("        ,:equip_id            ");
	        query.append("        ,:wcode_id            ");
	        query.append(")                             ");
    	
	        
	        Object[] objects = new Object[]{
	        		loginUser.getCompNo(),
	        		loginUser.getUserId(),
	        		id,
	        		wcodeId
	        };
	        
	        int result = this.getJdbcTemplate().update(query.toString(),objects);

	        return result;
	}
    public int insertListQrCode(MaPtStckCommonDTO maPtStckCommonDTO, User loginUser) {
    	QueryBuffer query = new QueryBuffer();
    	String compNo = loginUser.getCompNo();
    	query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAPTBCPRINT (   ");
    	query.append("        comp_no               ");
    	query.append("        ,user_id              ");
    	query.append("        ,part_id             ");
    	query.append("        ,wcode_id             ");
    	query.append(")                             ");
    	query.append("SELECT comp_no,?,part_id,a.wcode_id							");
    	query.append("FROM ( SELECT													");
        query.append("       	a.comp_no											");
        query.append("       	,a.wcode_id											");
        query.append("       	,b.wname											");
        query.append("       	,a.part_id											");
        query.append("       	,c.part_no											");
        query.append("       	,c.model                                model       ");
        query.append("       	,c.vendor_code                          oldNo       ");
        query.append("       	,MAX(c.pt_abc_class)                    pt_abc_class");
        query.append("       	,c.description||','||c.pt_size as partNameSize		");
        query.append("       	,MAX(CASE WHEN a.part_grade='A' THEN a.bin_no END) AS abin_no");
        query.append("       	,MAX(CASE WHEN a.part_grade='B' THEN a.bin_no END) AS bbin_no");
        query.append("       	,(SELECT safty_qty									");
        query.append("       	FROM taptsaftystock aa								");
        query.append("       	WHERE aa.comp_no = a.comp_no						");
        query.append("       	AND aa.wcode_id = a.wcode_id						");
        query.append("       	AND aa.part_id = a.part_id) AS min_safty_qty		");
        query.append("       	,(SELECT max_safty_qty								");
        query.append("       	FROM taptsaftystock aa								");
        query.append("       	WHERE aa.comp_no = a.comp_no						");
        query.append("       	AND aa.wcode_id = a.wcode_id						");
        query.append("       	AND aa.part_id = a.part_id) AS max_safty_qty		");
        query.append("       	,SUM(CASE WHEN a.part_grade='A' THEN a.stock_qty END) AS a_stock_qty");
        query.append("       	,SUM(CASE WHEN a.part_grade='B' THEN a.stock_qty END) AS b_stock_qty");
        query.append("       	,SUM(a.stock_qty) AS tot_stock_qty					");
        query.append("			,(SELECT SUM(aa.po_qty)								");
        query.append("				FROM TAPTPOLIST aa								");
        query.append("				WHERE aa.comp_no= a.comp_no						");
        query.getAndQuery("aa.polist_status", "P");
        query.append("				AND aa.part_id = a.part_id           ) as ordered_qty");
        query.append("			,(SELECT SUM(aa.rec_qty)							");
        query.append("				FROM TAPTPOLIST aa								");
        query.append("				WHERE aa.comp_no= a.comp_no						");
        query.getAndQuery("aa.polist_status", "P");
        query.append("				AND aa.part_id = a.part_id           ) as reced_qty");
        query.append("       	,(SELECT SUM(aa.issue_qty)							");
        query.append("       	FROM TAPTISSLIST aa									");
        query.append("       	where aa.comp_no =a.comp_no							");
        query.append("       	and aa.part_id = a.part_id							");
        query.append("      	and aa.ptiss_status in ('W','X')) as current_using_qty	");
        query.append("FROM taptstock a, tawarehouse b, taparts c					");
        query.append("WHERE 1=1														");
        query.append("AND a.comp_no = b.comp_no										");
        query.append("AND a.wcode_id = b.wcode_id									");
        query.append("AND a.comp_no = c.comp_no										");
        query.append("AND a.part_id = c.part_id										");
        query.getAndQuery("a.comp_no", compNo);
        query.getAndQuery("b.wh_categ", "PART");
        query.getAndQuery("c.part_categ", "SPPT");
        query.append(this.getWhere(maPtStckCommonDTO, loginUser));
        query.append("GROUP BY a.comp_no,a.wcode_id,b.wname,a.part_id,c.part_no,c.description||','||c.pt_size,c.model,c.vendor_code");
        query.append(") a															");
        query.append("WHERE 1=1														");
        if("Y".equals(maPtStckCommonDTO.getFilterSaftyYN())){
        	query.append("AND (nvl(a.min_safty_qty,0) - ((nvl(a.tot_stock_qty,0)+(nvl(a.ordered_qty,0)-nvl(a.reced_qty,0))) - nvl(a.current_using_qty,0))) > 0	");
        }else if("N".equals(maPtStckCommonDTO.getFilterSaftyYN())){
        	query.append("AND (nvl(a.min_safty_qty,0) - ((nvl(a.tot_stock_qty,0)+(nvl(a.ordered_qty,0)-nvl(a.reced_qty,0))) - nvl(a.current_using_qty,0))) <= 0	");
        }
        if(!"".equals(maPtStckCommonDTO.getFilterQtyCnt())){
        	query.append("AND nvl(a.tot_stock_qty,0) >= "+maPtStckCommonDTO.getFilterQtyCnt()+"");
        }
    	
    	
    	Object[] objects = new Object[]{
    			loginUser.getUserId()
    	};
    	
    	int result = this.getJdbcTemplate().update(query.toString(),objects);
    	
    	return result;
    }

	public int deleteQrCode(User loginUser) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAPTBCPRINT      ");
        query.append("WHERE  comp_no = :comp_no ");
        query.append("AND    user_id = :user_id ");

        Object[] objects = new Object[]{
                loginUser.getCompNo()
                ,loginUser.getUserId()
        };

        int result = this.getJdbcTemplate().update(query.toString(),objects);

        return result;
    }

    @Override
    public String findTotalCount(MaPtStckCommonDTO maPtStckCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maPtStckCommonDTO.getCompNo();
        
        query.append("SELECT                                                                                                                                                                                  ");
        query.append("        count(*)                                                                                                                                                                        ");
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
        query.getAndQuery("a.comp_no", compNo);
        query.getAndQuery("b.wh_categ", "PART");
        query.getAndQuery("c.part_categ", "SPPT");
        query.append(this.getWhere(maPtStckCommonDTO, user));
        if(!"".equals(maPtStckCommonDTO.getFilterQtyCnt())){
        	query.append("AND nvl(a.tot_stock_qty,0) >= "+maPtStckCommonDTO.getFilterQtyCnt()+"");
        }
        query.append(") a                                                                                                                                                                                    ");
        query.append("WHERE 1=1                                                                                                                                                                              ");
        if("Y".equals(maPtStckCommonDTO.getFilterSaftyYN())){
            query.append("AND (nvl(a.min_safty_qty,0) - ((nvl(a.tot_stock_qty,0)+(nvl(a.ordered_qty,0)+nvl(a.pr_qty,0))) - nvl(a.emg_iss_qty,0) - nvl(a.current_using_qty,0))) > 0  ");
            query.append("AND a.min_safty_qty IS NOT NULL   ");
            query.append("AND a.min_safty_qty != 0          ");
        }else if("N".equals(maPtStckCommonDTO.getFilterSaftyYN())){
            query.append("AND ( (nvl(a.min_safty_qty,0) - ((nvl(a.tot_stock_qty,0)+(nvl(a.ordered_qty,0)+nvl(a.pr_qty,0))) - nvl(a.emg_iss_qty,0) - nvl(a.current_using_qty,0))) <= 0   ");
            query.append("    OR a.min_safty_qty IS NULL    ");
            query.append("    OR a.min_safty_qty = 0 )      ");
        }
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}