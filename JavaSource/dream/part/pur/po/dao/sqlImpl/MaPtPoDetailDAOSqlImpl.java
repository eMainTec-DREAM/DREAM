package dream.part.pur.po.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.part.pur.po.dao.MaPtPoDetailDAO;
import dream.part.pur.po.dto.MaPtPoDetailDTO;

/**
 * 발주이력 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maPtPoDetailDAOTarget"
 * @spring.txbn id="maPtPoDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtPoDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtPoDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param poListId
     * @return
     */
    public MaPtPoDetailDTO findDetail(String poListId, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT                                                         ");
        query.append("       x.polist_id                           poListId,         ");
        query.append("       x.polist_no                           poListNo,         ");
        query.append("       x.dept_id                             deptId,           ");
        query.append("       dbo.SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no) deptDesc, ");
        query.append("       x.po_date                             poDate,           ");
        query.append("       x.rec_date                            recDate,           ");
        query.append("       x.part_id                             partId,           ");
        query.append("       y.part_no                             partNo,           ");
        query.append("       y.description+', '+ISNULL(y.pt_size,'')  partNamesize,     ");
        query.append("       ISNULL(x.req_qty, 0)                     reqQty,           ");
        query.append("       ISNULL(x.po_qty, 0)                      poQty,            ");
        query.append("       ISNULL(x.rec_qty, 0)                     recQty,           ");
        query.append("       x.polist_status                       poStatusId,       ");
        query.append("       dbo.SFACODE_TO_DESC(x.polist_status, 'PO_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') poStatusDesc, "); 
        query.append("       x.wcode_id 					wcodeId,		         ");
        query.append("       (SELECT wname									         ");
        query.append("          FROM TAWAREHOUSE							         ");
        query.append("         WHERE comp_no = x.comp_no					         ");
        query.append("           AND wcode_id = x.wcode_id) wcodeDesc,		         ");
        query.append("       x.vendor_id                           vendorId,         ");
        query.append("      (SELECT description FROM TAVENDOR                        ");
        query.append("       WHERE  comp_no  = x.comp_no                             ");
        query.getAndQuery("is_use", "Y");
        query.append("         AND  vendor_id = x.vendor_id)       vendorDesc,       ");
        query.append("       (SELECT (SELECT ptprlist_no FROM TAPTPRLIST 			 ");
        query.append("                WHERE comp_no     = a.comp_no 				 ");
        query.append("                  AND ptprlist_id = a.ptprlist_id)			 ");
        query.append("       FROM TAPTPRITEM a 										 ");
        query.append("       WHERE a.comp_no = x.comp_no 							 ");
        query.append("       AND a.ptpritem_id = x.ptpritem_id 						 ");
        query.append("       )											requestNo,   ");
        query.append("       x.remark                                   remark,      ");
        query.append("       x.part_grade                               partGrade    ");
        query.append("FROM   TAPTPOLIST x ,TAPARTS y                                 ");
        query.append("WHERE  x.comp_no      = y.comp_no	                             ");
        query.append("  AND  x.part_id      = y.part_id                              ");
        query.getAndQuery("x.comp_no", compNo);
        query.getAndQuery("x.polist_id", poListId);
    
        MaPtPoDetailDTO maPtPoDetailDTO = 
        		(MaPtPoDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtPoDetailDTO()));
        
        return maPtPoDetailDTO;
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoDetailDTO
     * @return
     */
    public int insertDetail(MaPtPoDetailDTO maPtPoDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPTPOLIST (                                  ");
    	query.append("  comp_no,   polist_id,    polist_no,    polist_status,   ");
    	query.append("  dept_id,   vendor_id,    po_date,      part_id,         ");
    	query.append("  part_grade,req_qty,      po_qty,       rec_qty, 	    ");
    	query.append("  unit_price,tot_price,    remark,       plant,           ");
        query.append("  currency,  wcode_id                                     ");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?				                            ");
    	query.append(")                                                         ");
    	
    	Object[] objects = new Object[] {
    	        user.getCompNo(),
    			maPtPoDetailDTO.getPoListId(),
    			maPtPoDetailDTO.getPoListNo(),
    			maPtPoDetailDTO.getPoStatusId(),
    			maPtPoDetailDTO.getDeptId(),
    			maPtPoDetailDTO.getVendorId(),
    			CommonUtil.getRowDateToNum(maPtPoDetailDTO.getPoDate()),
    			maPtPoDetailDTO.getPartId(),
    			maPtPoDetailDTO.getPartGrade(),
    			CommonUtil.getRowMoneyToNum(maPtPoDetailDTO.getReqQty()),
    			CommonUtil.getRowMoneyToNum(maPtPoDetailDTO.getPoQty()),
    			CommonUtil.getRowMoneyToNum(maPtPoDetailDTO.getRecQty()),
    			CommonUtil.getRowMoneyToNum(maPtPoDetailDTO.getUnitPrice()),
    			CommonUtil.getRowMoneyToNum(maPtPoDetailDTO.getTotPrice()),
    			maPtPoDetailDTO.getRemark(),
    			maPtPoDetailDTO.getPlant(),
                maPtPoDetailDTO.getCurrency(),
                maPtPoDetailDTO.getWcodeId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updateDetail(MaPtPoDetailDTO maPtPoDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAPTPOLIST  SET		      ");
    	query.append("	     polist_status      = ?,      ");
        query.append("       po_date            = ?,      ");
        query.append("       rec_date           = ?,      ");
        query.append("       wcode_id           = ?,      ");
        query.append("       vendor_id          = ?,      ");
        query.append("       part_id            = ?,      ");
        query.append("       dept_id            = ?,      ");
        query.append("       req_qty            = ?,      ");
        query.append("       po_qty             = ?,      ");
        query.append("       rec_qty            = ?,      ");
        query.append("       unit_price         = ?,      ");
        query.append("       tot_price          = ?,      ");
        query.append("       remark             = ?       ");
    	query.append("WHERE  comp_no            = ?	      ");
    	query.append("  AND  polist_id          = ?       ");
    	
    	Object[] objects = new Object[] {
    			maPtPoDetailDTO.getPoStatusId(),
    			maPtPoDetailDTO.getPoDate(),
    			maPtPoDetailDTO.getRecDate(),
    			maPtPoDetailDTO.getWcodeId(),
    			maPtPoDetailDTO.getVendorId(),
    			maPtPoDetailDTO.getPartId(),
    			maPtPoDetailDTO.getDeptId(),
    			maPtPoDetailDTO.getReqQty(),
    			maPtPoDetailDTO.getPoQty(),
    			maPtPoDetailDTO.getRecQty(),
    			maPtPoDetailDTO.getUnitPrice()==""?"0":maPtPoDetailDTO.getUnitPrice(),
    			maPtPoDetailDTO.getTotPrice()==""?"0":maPtPoDetailDTO.getTotPrice(),
    			maPtPoDetailDTO.getRemark(),
    			user.getCompNo(),
    			maPtPoDetailDTO.getPoListId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail 상태 Update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updatePtPoListStatus(MaPtPoDetailDTO maPtRecDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPTPOLIST SET		          ");
        query.append("	     polist_status      = ?       ");
        query.append("WHERE  comp_no            = ?	      ");
        query.append("  AND  polist_id          = ?       ");
        
        Object[] objects = new Object[] {
                maPtRecDetailDTO.getPoStatusId(),
                user.getCompNo(),
                maPtRecDetailDTO.getPoListId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
       
}