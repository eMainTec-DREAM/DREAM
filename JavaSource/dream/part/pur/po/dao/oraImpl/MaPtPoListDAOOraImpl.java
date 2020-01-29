package dream.part.pur.po.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.pur.po.dao.MaPtPoListDAO;
import dream.part.pur.po.dto.MaPtPoCommonDTO;

/**
 * 발주이력 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtPoListDAOTarget"
 * @spring.txbn id="maPtPoListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtPoListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtPoListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoCommonDTO
     * @return List
     */
    public List findList(MaPtPoCommonDTO maPtPoCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maPtPoCommonDTO.getCompNo();
        
        query.append("SELECT ''                                             seqNo,			");
        query.append("       ''                                             isDelCheck,		");
        query.append("       x.polist_id                                    poListId,		");
        query.append("       x.polist_no                                    poListNo,		");
        query.append("       SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no)  deptDesc,		"); 
        query.getDate("x.po_date", "poDate,");
        query.append("       x.is_fcrec_yn									isFcRecYn,		");
        query.append("       (SELECT a.part_no 												");
        query.append("       FROM TAPARTS a 												");
        query.append("       WHERE a.comp_no = x.comp_no 									");
        query.append("       AND a.part_id = x.part_id )  					partNo, 		");
        query.append("       (SELECT a.description||', '||a.pt_size							");
        query.append("       FROM TAPARTS a 												");
        query.append("       WHERE a.comp_no = x.comp_no 									");
        query.append("       AND a.part_id = x.part_id )  					ptNameSize, 	");
        query.append("       NVL(x.req_qty,0)                               reqQty,         ");
        query.append("       NVL(x.po_qty,0)                                poQty,          ");
        query.append("       NVL(x.rec_qty,0)                               recQty,         ");
        query.append("       x.polist_status								poListStatusId, ");
        query.append("       SFACODE_TO_DESC(x.polist_status, 'PO_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') poListStatus, ");
        query.append("       (SELECT a.description											");
        query.append("       FROM TAVENDOR a 												");
        query.append("       WHERE a.comp_no = x.comp_no 									");
        query.append("       AND a.vendor_id = x.vendor_id 								 	");
        query.getAndQuery("a.is_use", "Y");
        query.append("       )												vendorDesc, 	");
        query.append("       (SELECT (SELECT ptprlist_no FROM TAPTPRLIST 					");
        query.append("                WHERE comp_no     = a.comp_no 						");
        query.append("                  AND ptprlist_id = a.ptprlist_id)					");
        query.append("       FROM TAPTPRITEM a 												");
        query.append("       WHERE a.comp_no = x.comp_no 									");
        query.append("       AND a.ptpritem_id = x.ptpritem_id 								");
        query.append("       )												requestNo, 		");
        query.append("       x.remark                 						remark			");
        query.append("FROM   TAPTPOLIST x, TAPARTS y                                        ");
        query.append("WHERE x.comp_no = y.comp_no                                           ");
        query.append("AND   x.part_id = y.part_id                                           ");
        query.getAndQuery("x.comp_no", compNo);
        query.append(this.getWhere(maPtPoCommonDTO,user));
        query.getOrderByQuery("x.po_date DESC", maPtPoCommonDTO.getOrderBy(), maPtPoCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtPoCommonDTO.getIsLoadMaxCount(), maPtPoCommonDTO.getFirstRow()));
    
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String compNo, String poListId)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPTPOLIST		              ");
    	query.append("WHERE  comp_no       = '"+compNo+"'		  ");
    	query.append("  AND  polist_id     = '"+poListId+"'       ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    public int insertRec(MaPtPoCommonDTO maPtPoCommonDTO, String poListId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
		query.append("SELECT SQAFCRECLIST_ID.nextval		");
        query.append("FROM DUAL								");
        
		String recId = QueryBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString()));
		
		query = new QueryBuffer();
		query.append("SELECT SQAPTRECHIST_ID.nextval		");
		query.append("FROM DUAL								");
		
		String histId = QueryBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString()));

    	query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAPTFCRECLIST                                                 ");
    	query.append("	(COMP_NO, FCRECLIST_ID, FCRECLIST_NO, FCRECLIST_STATUS,                 ");
    	query.append("	 DEPT_ID, WCODE_ID, REC_DATE, PART_ID,                                  ");
    	query.append("	 PART_GRADE, REC_QTY, UNIT_PRICE, TOT_PRICE,                            ");
    	query.append("	 REMARK, VENDOR_ID                                   					");
    	query.append("	)                                   									");
        query.append("SELECT x.comp_no, '"+recId+"', '"+recId+"', 'C',	");
        query.append("       x.dept_id, x.wcode_id, x.rec_date, x.part_id,						");
        query.append("       'B', x.rec_qty,0,0,                                  				");
        query.append("       x.remark, x.vendor_id												");
        query.append("FROM   TAPTPOLIST x                                    					");
        query.append("WHERE  x.comp_no      = '"+user.getCompNo()+"'							");
        query.getAndQuery("x.polist_id", poListId);
        query.getAndQuery("x.is_fcrec_yn", "N");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());

    	query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAPTRECHIST(                                  				");
        query.append("	COMP_NO, PTRECHIST_ID, PTREC_MODE, PTREC_TYPE, 							");
        query.append("	RECLIST_ID, DEPT_ID, WCODE_ID, VENDOR_ID, 								");
        query.append("	REC_DATE, PART_ID, PART_GRADE, REC_QTY, 								");
        query.append("	UNIT_PRICE, TOT_PRICE													");
        query.append("		)																	");
        query.append("SELECT x.comp_no, '"+histId+"', 'C', 'FCREC',					");
        query.append("       '"+recId+"', x.dept_id, x.wcodE_id, x.vendor_id,					");
        query.append("       x.rec_date, x.part_id,'B',x.rec_qty,								");
        query.append("       0,0																");
        query.append("FROM   TAPTPOLIST x                                    					");
        query.append("WHERE  x.comp_no      = '"+user.getCompNo()+"'							");
        query.getAndQuery("x.polist_id", poListId);
        query.getAndQuery("x.is_fcrec_yn", "N");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());

    	query = new QueryBuffer();
    	
    	query.append("UPDATE TAPTPOLIST x SET x.is_fcrec_yn = 'Y', x.fcreclist_id= '"+recId+"' 	");
        query.append("WHERE  x.comp_no      = '"+user.getCompNo()+"'							");
        query.getAndQuery("x.polist_id", poListId);
        query.getAndQuery("x.is_fcrec_yn", "N");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	query.append("begin                                                     ");
        query.append("SP_PT_INSTOCK('"+user.getCompNo()+"', '"+histId+"' );          ");
        query.append("end;                                                      ");
      
        this.getJdbcTemplate().execute(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtPoCommonDTO maPtPoCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
      
        if (!"".equals(maPtPoCommonDTO.getPoListId()))
        {
            query.getAndQuery("x.polist_id", maPtPoCommonDTO.getPoListId());
            return query.toString();
        }
        //입고여부
        query.getAndQuery("x.is_fcrec_yn", maPtPoCommonDTO.getFilterIsTransfer());
        //상태
        query.getSysCdQuery("x.polist_status", maPtPoCommonDTO.getFilterPoStatusId(), maPtPoCommonDTO.getFilterPoStatusDesc(), "PO_STATUS", maPtPoCommonDTO.getCompNo(),user.getLangId());
        // 부서
        query.getDeptLevelQuery("x.dept_id", maPtPoCommonDTO.getFilterDeptId(), maPtPoCommonDTO.getFilterDeptDesc(), maPtPoCommonDTO.getCompNo());
        //발주일자
        query.getAndDateQuery("x.po_date", maPtPoCommonDTO.getFilterStartPoDate(), maPtPoCommonDTO.getFilterEndPoDate());
        //청구일자
        if(!"".equals(maPtPoCommonDTO.getFilterStartRequestDate())){
        	query.append("AND (SELECT (SELECT req_date FROM TAPTPRLIST 							");
            query.append("                WHERE comp_no     = a.comp_no 						");
            query.append("                  AND ptprlist_id = a.ptprlist_id)					");
            query.append("       FROM TAPTPRITEM a 												");
            query.append("       WHERE a.comp_no = x.comp_no 									");
            query.append("       AND a.ptpritem_id = x.ptpritem_id 								");
            query.append("       )	>='"+maPtPoCommonDTO.getFilterStartRequestDate()+"' 		");
        }
        if(!"".equals(maPtPoCommonDTO.getFilterEndRequestDate())){
        	query.append("AND (SELECT (SELECT req_date FROM TAPTPRLIST 							");
            query.append("                WHERE comp_no     = a.comp_no 						");
            query.append("                  AND ptprlist_id = a.ptprlist_id)					");
            query.append("       FROM TAPTPRITEM a 												");
            query.append("       WHERE a.comp_no = x.comp_no 									");
            query.append("       AND a.ptpritem_id = x.ptpritem_id 								");
            query.append("       )	<='"+maPtPoCommonDTO.getFilterEndRequestDate()+"' 			");
        }
        // 품명/규격
        query.getLikeQuery("y.full_desc", maPtPoCommonDTO.getFilterPtNameSize());
        //거래처
        if(!"".equals(maPtPoCommonDTO.getFilterVendorId())){
        	query.getAndQuery("x.vendor_id", maPtPoCommonDTO.getFilterVendorId());
        }else if(!"".equals(maPtPoCommonDTO.getFilterVendorDesc())){
        	query.append("AND x.vendor_id IN (SELECT a.vendor_id FROM TAVENDOR a WHERE 1=1	");
            query.getAndQuery("a.comp_no", maPtPoCommonDTO.getCompNo());
        	query.append("						AND a.description like '%"+maPtPoCommonDTO.getFilterVendorDesc()+"%'");
        	query.append("					)");
        }
        //발주 번호
        query.getAndQuery("x.polist_no", maPtPoCommonDTO.getFilterPoNo());
        //청구번호
        if(!"".equals(maPtPoCommonDTO.getFilterRequestNo())){
        	query.append("AND (SELECT (SELECT ptprlist_no FROM TAPTPRLIST 						");
            query.append("                WHERE comp_no     = a.comp_no 						");
            query.append("                  AND ptprlist_id = a.ptprlist_id)					");
            query.append("       FROM TAPTPRITEM a 												");
            query.append("       WHERE a.comp_no = x.comp_no 									");
            query.append("       AND a.ptpritem_id = x.ptpritem_id 								");
            query.append("       )	like '%"+maPtPoCommonDTO.getFilterRequestNo()+"%' 			");
        }
        return query.toString();
    }

	@Override
	public String findTotalCount(MaPtPoCommonDTO maPtPoCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT						                                       ");
        query.append("		COUNT(*)                				                       ");
        query.append("FROM   TAPTPOLIST x, TAPARTS y                                       ");
        query.append("WHERE x.comp_no = y.comp_no                                          ");
        query.append("AND   x.part_id = y.part_id                                          ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.append(this.getWhere(maPtPoCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}