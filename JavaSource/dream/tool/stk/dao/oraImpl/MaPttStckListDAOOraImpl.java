package dream.tool.stk.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.tool.stk.dao.MaPttStckListDAO;
import dream.tool.stk.dto.MaPttStckCommonDTO;
import dream.tool.stk.form.MaPttStckListForm;

/**
 * 자재재고 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPttStckListDAOTarget"
 * @spring.txbn id="maPttStckListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttStckListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPttStckListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckCommonDTO
     * @return List
     */
    public List findPtStckList(MaPttStckCommonDTO maPttStckCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maPttStckCommonDTO.getCompNo();
        
        query.append("SELECT ''                                     AS seqNo          ");
        query.append("       ,''                                    AS isDelCheck     ");
        query.append("       ,a.comp_no AS compNo                                     ");
        query.append("       ,a.wcode_id AS wcodeId                                   ");
        query.append("       ,a.wname AS wname                                        ");
        query.append("       ,a.part_id AS partId                                     ");
        query.append("       ,a.part_no AS partNo                                     ");
        query.append("       ,a.partNameSize AS partNameSize                          ");
        query.append("       ,nvl(a.stock_qty,0) AS stockQty                          ");
        query.append("       ,nvl(a.tot_stock_qty,0) AS totStockQty                   ");
        query.append("       ,a.bin_no AS binNo                                       ");
        query.append("       ,nvl(a.max_safty_qty,0) AS maxSaftyQty                   ");
        query.append("       ,nvl(a.min_safty_qty,0) AS minSaftyQty                   ");
        query.append("       ,nvl(a.rent_qty,0) AS rentQty                    		  ");
        query.append("       ,CASE WHEN nvl(a.min_safty_qty,0) - (nvl(a.tot_stock_qty,0) - nvl(a.rent_qty,0)) < 0 THEN 0    ");
        query.append("           ELSE nvl(a.min_safty_qty,0) - (nvl(a.tot_stock_qty,0) - nvl(a.rent_qty,0)) END AS underSaftyQty    ");

        query.append("FROM ( SELECT                                                    ");
        query.append("           a.comp_no                                            ");
        query.append("           ,a.wcode_id                                            ");
        query.append("           ,b.wname                                            ");
        query.append("           ,a.part_id                                            ");
        query.append("           ,c.part_no                                            ");
        query.append("           ,c.description||','||c.pt_size as partNameSize        ");
        query.append("           ,MAX(a.bin_no) AS bin_no                            ");
        query.append("           ,(SELECT safty_qty                                    ");
        query.append("           FROM taptsaftystock aa                                ");
        query.append("           WHERE aa.comp_no = a.comp_no                        ");
        query.append("           AND aa.wcode_id = a.wcode_id                        ");
        query.append("           AND aa.part_id = a.part_id) AS min_safty_qty        ");
        query.append("           ,(SELECT max_safty_qty                                ");
        query.append("           FROM taptsaftystock aa                                ");
        query.append("           WHERE aa.comp_no = a.comp_no                        ");
        query.append("           AND aa.wcode_id = a.wcode_id                        ");
        query.append("           AND aa.part_id = a.part_id) AS max_safty_qty        ");
        query.append("           ,SUM(CASE WHEN a.part_grade='B' THEN a.stock_qty END) AS stock_qty");
        query.append("           ,(SELECT SUM(z1.rent_qty) FROM TAPTRENT_STOCK z1 WHERE z1.comp_no=a.comp_no AND z1.wcode_id=a.wcode_id AND a.part_id=z1.part_id)rent_qty");
        query.append("           ,SUM(a.stock_qty)+(SELECT NVL(SUM(z1.rent_qty),0) FROM TAPTRENT_STOCK z1 WHERE z1.comp_no=a.comp_no AND z1.wcode_id=a.wcode_id AND a.part_id=z1.part_id) AS tot_stock_qty  ");
        query.append("FROM taptstock a, tawarehouse b, taparts c   ");

        query.append("WHERE 1=1														");
        query.append("AND a.comp_no = b.comp_no										");
        query.append("AND a.wcode_id = b.wcode_id									");
        query.append("AND a.comp_no = c.comp_no										");
        query.append("AND a.part_id = c.part_id										");
        query.getAndQuery("a.comp_no", compNo);
        query.getAndQuery("b.wh_categ", "TOOL");
        query.getAndQuery("c.part_categ", "TOOL");
        query.append(this.getWhere(maPttStckCommonDTO));
        query.append("GROUP BY a.comp_no,a.wcode_id,b.wname,a.part_id,c.part_no,c.description||','||c.pt_size");
        query.append(") a															");
        query.append("WHERE 1=1														");
        if("Y".equals(maPttStckCommonDTO.getFilterSaftyYN())){
        	query.append("AND (nvl(a.min_safty_qty,0) - (nvl(a.tot_stock_qty,0) - nvl(a.rent_qty,0))) > 0	");
        }else if("N".equals(maPttStckCommonDTO.getFilterSaftyYN())){
        	query.append("AND (nvl(a.min_safty_qty,0) - (nvl(a.tot_stock_qty,0) - nvl(a.rent_qty,0))) <= 0	");
        }
        if(!"".equals(maPttStckCommonDTO.getFilterQtyCnt())){
        	query.append("AND nvl(a.tot_stock_qty,0) >= "+maPttStckCommonDTO.getFilterQtyCnt()+"");
        }
        query.getOrderByQuery("a.part_no DESC", maPttStckCommonDTO.getOrderBy(), maPttStckCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPttStckCommonDTO.getIsLoadMaxCount(), maPttStckCommonDTO.getFirstRow()));
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
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPttStckCommonDTO maPttStckCommonDTO)
    {        
        QueryBuffer query = new QueryBuffer();
      
        if (!"".equals(maPttStckCommonDTO.getWcodeId())&&
        		!"".equals(maPttStckCommonDTO.getPartId()))
        {
            query.getAndQuery("a.wcode_id", maPttStckCommonDTO.getWcodeId());
            query.getAndQuery("a.part_id", maPttStckCommonDTO.getPartId());
            return query.toString();
        }
        // 창고명
        if(!"".equals(maPttStckCommonDTO.getFilterWcodeId()))
        {
            query.getLikeQuery("a.wcode_id", maPttStckCommonDTO.getFilterWcodeId());
        }
        else if(!"".equals(maPttStckCommonDTO.getFilterWname()))
        {
            query.append(" AND  a.wcode_id IN (SELECT wcode_id FROM TAWAREHOUSE ");
            query.append("                     WHERE  comp_no = a.comp_no       ");
            query.getLikeQuery("wname", maPttStckCommonDTO.getFilterWname());
            query.append("					   )						        ");
        }
        
        if(!"".equals(maPttStckCommonDTO.getFilterPartNo())
                || !"".equals(maPttStckCommonDTO.getFilterPartDesc())
                || !"".equals(maPttStckCommonDTO.getFilterPtSize()) 
                || !"".equals(maPttStckCommonDTO.getFilterMaker()) 
                || !"".equals(maPttStckCommonDTO.getFilterModel()) )
        {
            query.append(" AND  a.part_id IN (SELECT part_id FROM TAPARTS       ");
            query.append("                    WHERE  comp_no = a.comp_no        ");
            query.getLikeQuery("part_no", maPttStckCommonDTO.getFilterPartNo()); 
            query.getLikeQuery("description", maPttStckCommonDTO.getFilterPartDesc()); 
            query.getLikeQuery("pt_size", maPttStckCommonDTO.getFilterPtSize()); 
            query.getLikeQuery("maker", maPttStckCommonDTO.getFilterMaker()); 
            query.getLikeQuery("model", maPttStckCommonDTO.getFilterModel()); 
            query.append("					   )						        ");
        }
        
        return query.toString();
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
    public int reqHdrPtStck(MaPttStckListForm maPttStckListForm, String wcodeId, String partId,User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	MaPttStckCommonDTO maPtStckCommonDTO = maPttStckListForm.getMaPttStckCommonDTO();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();

    	query.append("INSERT INTO TAPTDISUSELIST								");
    	query.append("	(comp_no,		ptdisuselist_id,	ptdisuse_status,	");
    	query.append("	 wcode_id,	    description,        disuse_date,		");
    	query.append("	 exe_dept,		exe_by,             remark				");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,(SELECT description||'...' FROM TAPARTS WHERE comp_no = '"+user.getCompNo()+"' AND part_id = '"+partId+"'),	TO_CHAR(sysdate,'yyyymmdd'),				");
    	query.append("	 ?,            ?,         ?					");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			maPtStckCommonDTO.getReqIdx(),
    			"W",
    			wcodeId,
    			user.getDeptId(),
    			user.getEmpId(),
    			""
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
    public int reqDtlPtStck(MaPttStckListForm maPttStckListForm, String partId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	MaPttStckCommonDTO maPttStckCommonDTO = maPttStckListForm.getMaPttStckCommonDTO();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPTDISUSEITEM (								");
    	query.append("	comp_no,		ptdisuseitem_id,	ptdisuselist_id,	");
    	query.append("	part_id,		part_grade,			disuse_qty,");
    	query.append("	remark												");
    	query.append("	)	VALUES	(										");
    	query.append("	?,				SQAPTPRITEM_ID.nextval,?,			");
    	query.append("	?,            ?,                    ?,				");
    	query.append("	?)													");

    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			maPttStckCommonDTO.getReqIdx(),
    			partId,
    			"B",
    			"1",
    			""
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }

	@Override
	public String findTotalCount(MaPttStckCommonDTO maPttStckCommonDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT        								        		");
		query.append("      Count(*)												");
        query.append("FROM taptstock a, tawarehouse b, taparts c   					");
        query.append("WHERE 1=1														");
        query.append("AND a.comp_no = b.comp_no										");
        query.append("AND a.wcode_id = b.wcode_id									");
        query.append("AND a.comp_no = c.comp_no										");
        query.append("AND a.part_id = c.part_id										");
        query.getAndQuery("a.comp_no", user.getCompNo()  );
        query.getAndQuery("b.wh_categ", "TOOL			");
        query.getAndQuery("c.part_categ", "TOOL			");
        query.append(this.getWhere(maPttStckCommonDTO)   );
        
		List resultList=  getJdbcTemplate().queryForList(query.toString());
		return QueryBuffer.listToString(resultList);
	}
}