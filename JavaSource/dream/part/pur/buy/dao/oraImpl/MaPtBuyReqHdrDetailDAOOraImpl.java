package dream.part.pur.buy.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.part.pur.buy.dao.MaPtBuyReqHdrDetailDAO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 구매신청 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaPtBuyReqHdrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maPtBuyReqHdrDetailDAOTarget"
 * @spring.txbn id="maPtBuyReqHdrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtBuyReqHdrDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtBuyReqHdrDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrCommonDTO
     * @return
     */
    public MaPtBuyReqHdrDetailDTO findDetail(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                   										");
        query.append("       x.ptprlist_id 						AS ptPrListId		");
        query.append("       ,x.ptprlist_no 					AS ptPrListNo		");
        query.append("       ,x.description 					AS ptPrListDesc		");
        query.append("       ,x.vendor_id 						AS vendorId			");
        query.append("       ,(SELECT description 									");
        query.append("          FROM TAVENDOR a										");
        query.append("         WHERE a.comp_no = x.comp_no							");
        query.append("           AND a.vendor_id = x.vendor_id)	AS vendorDesc		");
        query.append("       ,x.ptprlist_status 				AS ptPrListStatusId	");
        query.append("		 ,SFACODE_TO_DESC(x.ptprlist_status,'PTPRLIST_STATUS','SYS','','"+user.getLangId()+"')	As ptPrListStatusDesc	");
        query.append("       ,x.dept_id 						AS deptId			");
        query.append("       ,(SELECT description 									");
        query.append("          FROM TADEPT a										");
        query.append("         WHERE a.comp_no = x.comp_no							");
        query.append("           AND a.dept_id = x.dept_id) 	AS deptDesc			");
        query.append("       ,x.user_id 						AS userId			");
        query.append("       ,(SELECT emp_name 					    				");
        query.append("           FROM TAEMP a										");
        query.append("          WHERE a.comp_no = x.comp_no							");
        query.append("            AND a.emp_id = x.user_id) 	As userDesc			");
        query.append("		 ,x.req_date 						AS reqDate			");
        query.append("		 ,x.remark 							AS remark			");
        query.append("       ,x.plant                        	AS plantId			");
        query.append("       ,(SELECT description                            		");
        query.append("          FROM TAPLANT                                 		");
        query.append("          WHERE comp_no = x.comp_no                    		");
        query.append("            AND plant = x.plant)       	AS plantDesc  		");
        query.append("        ,x.rec_by                         AS recById			");
        query.append("        ,(SELECT a.emp_name FROM TAEMP a                    	");
        query.append("         WHERE a.comp_no = x.comp_no and a.emp_id = x.rec_by)   		AS recByName	");
        query.append("       ,x.erp_pr_no                       AS erpPrNo          ");
        query.append("      , x.rec_plant                                           AS recPlant     ");
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
        query.append("       ,x.tot_amt                        	AS totAmt			");
        query.append("       ,x.wcode_id                        AS wcodeId			");
        query.append("FROM   TAPTPRLIST x        									");
        query.append("WHERE  x.comp_no = '"+user.getCompNo()+"' 					");
        query.append("  AND  x.ptprlist_id = '"+maPtBuyReqHdrCommonDTO.getPtPrListId()+"'");
    
        MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO = 
        		(MaPtBuyReqHdrDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtBuyReqHdrDetailDTO()));
        
        return maPtBuyReqHdrDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailDTO
     * @return
     */
    public int insertDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPTPRLIST							");
    	query.append("	(comp_no,		ptprlist_id,	ptprlist_no,	");
    	query.append("	 description,	ptprlist_status,dept_id,		");
    	query.append("	 vendor_id,		req_date,		remark,			");
    	query.append("	 user_id,		plant,			rec_by,			");
    	query.append("  rec_plant,      wcode_id                        ");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,				?, 				");
    	query.append("	 ?,             ?    			 				");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo()
    			, maPtBuyReqHdrDetailDTO.getPtPrListId()
    			, maPtBuyReqHdrDetailDTO.getPtPrListNo()
    			, maPtBuyReqHdrDetailDTO.getPtPrListDesc()
    			, maPtBuyReqHdrDetailDTO.getPtPrListStatusId()
    			, maPtBuyReqHdrDetailDTO.getDeptId()
    			, maPtBuyReqHdrDetailDTO.getVendorId()
    			, maPtBuyReqHdrDetailDTO.getReqDate()
    			, maPtBuyReqHdrDetailDTO.getRemark()
    			, maPtBuyReqHdrDetailDTO.getUserId()
    			, maPtBuyReqHdrDetailDTO.getPlantId()
    			, maPtBuyReqHdrDetailDTO.getRecById()
    			, maPtBuyReqHdrDetailDTO.getRecPlant()
    			, maPtBuyReqHdrDetailDTO.getWcodeId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailDTO
     * @return
     */
    public int updateDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPTPRLIST SET			");
    	query.append("		description		= ?		");
    	query.append("		,dept_id		= ?		");
    	query.append("		,user_id		= ?		");
    	query.append("		,vendor_id		= ?		");
    	query.append("		,req_date		= ?		");
    	query.append("		,remark			= ?		");
    	query.append("		,plant			= ?		");
    	query.append("		,rec_by			= ?		");
    	query.append("      ,rec_plant      = ?     ");
    	query.append("      ,wcode_id       = ?     ");
    	query.append("      ,ptprlist_status= ?     ");
    	query.append("WHERE ptprlist_id		= ?		");
    	query.append("  AND comp_no 		= ?		");
    	
    	Object[] objects = new Object[] {
    			maPtBuyReqHdrDetailDTO.getPtPrListDesc()
    			, maPtBuyReqHdrDetailDTO.getDeptId()
    			, maPtBuyReqHdrDetailDTO.getUserId()
    			, maPtBuyReqHdrDetailDTO.getVendorId()
    			, CommonUtil.getRowDateToNum(maPtBuyReqHdrDetailDTO.getReqDate())
    			, maPtBuyReqHdrDetailDTO.getRemark()
    			, maPtBuyReqHdrDetailDTO.getPlantId()
    			, maPtBuyReqHdrDetailDTO.getRecById()
    			, maPtBuyReqHdrDetailDTO.getRecPlant()
    			, maPtBuyReqHdrDetailDTO.getWcodeId()
    			, maPtBuyReqHdrDetailDTO.getPtPrListStatusId()
    			, maPtBuyReqHdrDetailDTO.getPtPrListId()
    			, user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail check
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailDTO
     * @return
     */
    public String checkDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT count(NVL(rec_qty,0))			");
    	query.append("FROM TAPTPRITEM						");
    	query.append("WHERE comp_no 		= '"+maPtBuyReqHdrDetailDTO.getCompNo()+"'");
    	query.getAndQuery("ptprlist_id", maPtBuyReqHdrDetailDTO.getPtPrListId());
    	query.append("AND (rec_qty <=0 OR rec_qty is null)	");
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    public List findReportPtDetail(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO) {

		QueryBuffer query = new QueryBuffer();
		String lang = maPtBuyReqHdrDetailDTO.getUserLang();
		
        query.append("SELECT ptprlist_no ptNo,																									");
        query.append("		(SELECT description																									");
        query.append("			FROM TADEPT																										");
        query.append("			WHERE comp_no = x.comp_no																						");
        query.append("			AND dept_id = x.dept_id) deptDesc,																				");
        query.append("		x.description 			ptBuyReqTitle,																				");
        query.append("		(SELECT user_name																									");
        query.append("			FROM TAUSER																										");
        query.append("			WHERE comp_no = x.comp_no																						");
        query.append("			AND user_id = x.user_id) empName,																				");
        query.append("		(SELECT SUM((NVL(a.unit_price,0)*a.rec_qty))																		");
        query.append("			FROM TAPTPRITEM a																								");
        query.append("			WHERE a.comp_no = x.comp_no																						");
        query.append("			AND a.ptprlist_id = x.ptprlist_id) totalPrice,																	");
        query.append("		TO_CHAR(TO_DATE(x.req_date,'yyyymmdd'),'yyyy/mm') yyyymm,															");
        query.append("		TO_CHAR(TO_DATE(x.req_date,'yyyymmdd'),'yyyy-mm-dd') yyyymmdd,														");
        query.append("		TO_CHAR(sysdate,'/mm/yyyy') mmyyyy,																					");
        query.append("		TO_CHAR(sysdate,'yyyy-mm-dd HH24:mi') TODAY,																		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='ptReqBuyList' AND key_type='LABEL') ptReqBuyList,	");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='number' AND key_type='LABEL') no,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='title' AND key_type='LABEL') title,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='manager' AND key_type='LABEL') manager,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='ptBuyReqTitle' AND key_type='LABEL') ptBuyReqTitle1,	");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='requestDate' AND key_type='LABEL') requestDate,		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNo' AND key_type='LABEL') partNo,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNameSize' AND key_type='LABEL') partNameSize,	");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='model' AND key_type='LABEL') model,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='uom' AND key_type='LABEL') uom,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='reqQty' AND key_type='LABEL') reqQty,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='stockQty' AND key_type='LABEL') STOCKQTY,			");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='maxSaftyQty' AND key_type='LABEL') maxSaftyQty,		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='minSaftyQty' AND key_type='LABEL') minSaftyQty,		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='price' AND key_type='LABEL') price,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='remark' AND key_type='LABEL') remark,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='seqNo' AND key_type='LABEL') seqNo					");
        query.append("FROM TAPTPRLIST x																											");
        query.append("WHERE 1=1																													");
        query.getAndQuery("x.comp_no", maPtBuyReqHdrDetailDTO.getCompNo());
        query.getAndQuery("x.ptprlist_id", maPtBuyReqHdrDetailDTO.getPtPrListId());

		return getJdbcTemplate().queryForList(query.toString());
	}
    
    public List findReportPartList(MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO) {

		QueryBuffer query = new QueryBuffer();

		query.append("SELECT											");
		query.append("		ROWNUM seqNum,								");
		query.append("		(SELECT part_no 							");
		query.append("		   FROM TAPARTS								");
		query.append("		WHERE comp_no = x.comp_no					");
		query.append("		AND part_id = x.part_id) partNum,			");
		
		query.append("		DECODE(x.part_id,'', x.description, (SELECT description||', '||pt_size 			");
		query.append("		   FROM TAPARTS								");
		query.append("		WHERE comp_no = x.comp_no					");
		query.append("		AND part_id = x.part_id)) ptNameSize,		");
		
		query.append("		(SELECT model 								");
		query.append("		   FROM TAPARTS								");
		query.append("		WHERE comp_no = x.comp_no					");
		query.append("		AND part_id = x.part_id) ptmodel,			");
		
		query.append("		(SELECT uom 								");
		query.append("		   FROM TAPARTS								");
		query.append("		WHERE comp_no = x.comp_no					");
		query.append("		AND part_id = x.part_id) uomea,				");
		query.append("		x.rec_qty recQty,							");
		query.append("		x.remark reqRemark,							");
		query.append("		(SELECT NVL(SUM(a.stock_qty),0)				");
		query.append("			FROM TAPTSTOCK a						");
		query.append("			WHERE a.comp_no = x.comp_no				");
		query.append("			AND a.part_id = x.part_id ) reqStockQty,");
		query.append("		(SELECT NVL(SUM(a.max_safty_qty),0)			");
		query.append("			FROM taptsaftystock a					");
		query.append("			WHERE a.comp_no = x.comp_no				");
		query.append("			AND a.part_id = x.part_id ) reqMaxSaftyQty,	");
		query.append("		(SELECT NVL(SUM(a.safty_qty),0)				");
		query.append("			FROM taptsaftystock a					");
		query.append("			WHERE a.comp_no = x.comp_no				");
		query.append("			AND a.part_id = x.part_id ) reqMinSaftyQty,	");
		query.append("		(NVL(x.unit_price,0) * x.rec_qty) reqPrice	");
        query.append("FROM TAPTPRITEM x									");
        query.append("WHERE 1=1											");
        query.getAndQuery("x.comp_no", maPtBuyReqHdrDetailDTO.getCompNo());
        query.getAndQuery("x.ptprlist_id", maPtBuyReqHdrDetailDTO.getPtPrListId());
        query.append("ORDER BY ptpritem_id desc 						");
		return getJdbcTemplate().queryForList(query.toString());
	}
    
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPTPRLIST SET                    ");
        query.append("       ptprlist_status   = ?             ");
        query.append("WHERE  ptprlist_id       = ?             ");
        query.append("AND  comp_no       = ?             ");
        
        Object[] objects = new Object[] {
                appReqDetailDTO.getParentStatus(),
                appReqDetailDTO.getObjectId(),
                user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}