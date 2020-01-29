package dream.part.pur.buy.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.part.pur.buy.dao.MaPtBuyReqDetailDAO;
import dream.part.pur.buy.dto.MaPtBuyReqDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;

/**
 * 구매신청 item 상세 dao
 * @author  kim21017
 * @version $Id: MaPtBuyReqDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPtBuyReqDetailDAOTarget"
 * @spring.txbn id="maPtBuyReqDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtBuyReqDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtBuyReqDetailDAO
{
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPtBuyReqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqDetailDTO
     * @param maPtBuyReqHdrCommonDTO
     * @return
     */
    public int updateDetail(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPTPRITEM SET				");
    	query.append("	  part_id				= ?		");
    	query.append("	, description			= ?		");
    	query.append("	, pt_size				= ?		");
    	query.append("	, rec_qty				= ?		");
    	query.append("	, unit_price			= ?		");
    	query.append("	, currency				= ?		");
    	query.append("	, part_grade			= ?		");
    	query.append("	, emp_id				= ?		");
    	query.append("	, remark				= ?		");
    	query.append("	, accnt_id				= ?		");
    	query.append("	, ctctr_id				= ?		");
    	query.append("	, pur_group				= ?		");
    	query.append("	, accnt_type			= ?		");
    	query.append("  , req_com_date          = ?     ");
    	query.append("  , po_on_qty             = ?     ");
    	query.append("  , po_qty                = ?     ");
    	query.append("  , gr_on_qty             = ?     ");
    	query.append("  , gr_qty                = ?     ");
    	query.append("WHERE ptprlist_id 		= ?		");
    	query.append("  AND ptpritem_id			= ? 	");
    	query.append("  AND comp_no				= ? 	");
    	
    	Object[] objects = new Object[] {
    			  maPtBuyReqDetailDTO.getPartId()
    			, maPtBuyReqDetailDTO.getPartDesc()
    			, maPtBuyReqDetailDTO.getPtSize()
    			, CommonUtil.getRowMoneyToNum(maPtBuyReqDetailDTO.getRecQty())
    			, CommonUtil.getRowMoneyToNum(maPtBuyReqDetailDTO.getUnitPrice())
    			, maPtBuyReqDetailDTO.getCurr()
    			, maPtBuyReqDetailDTO.getPartGrade()
    			, maPtBuyReqDetailDTO.getAppReqById()
    			, maPtBuyReqDetailDTO.getRemark()
    			, maPtBuyReqDetailDTO.getAccntId()
    			, maPtBuyReqDetailDTO.getCtCtrId()
    			, maPtBuyReqDetailDTO.getPurGroup()
    			, maPtBuyReqDetailDTO.getAccntType()
    			, CommonUtil.getRowDateToNum(maPtBuyReqDetailDTO.getReqComDate())
    			, CommonUtil.getRowMoneyToNum(maPtBuyReqDetailDTO.getPoOnQty())
    			, CommonUtil.getRowMoneyToNum(maPtBuyReqDetailDTO.getPoQty())
    			, CommonUtil.getRowMoneyToNum(maPtBuyReqDetailDTO.getGrOnQty())
    			, CommonUtil.getRowMoneyToNum(maPtBuyReqDetailDTO.getGrQty())
    			, maPtBuyReqHdrCommonDTO.getPtPrListId()
    			, maPtBuyReqDetailDTO.getPtPrItemId()
    			, user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPtBuyReqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqDetailDTO
     * @param maPtBuyReqHdrCommonDTO
     * @return
     */
    public int insertDetail(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPTPRITEM (								");
    	query.append("	  comp_no		 , ptpritem_id	     , ptprlist_id	");
    	query.append("	, description	 , part_id			 , pt_size		");
    	query.append("	, rec_qty		 , remark			 , unit_price 	");
    	query.append("	, currency       , part_grade		 , emp_id		");
    	query.append("	, ptpnlist_id	 , accnt_id			 , ctctr_id		");
    	query.append("  , pur_group      , accnt_type        , req_com_date ");
    	query.append("	)	VALUES				(							");
    	query.append("	  ?				 , ?			     , ?			");
    	query.append("	, ?				 , ?			     , ?			");
    	query.append("	, ?				 , ?			     , ?			");
    	query.append("	, ?              , ?			     , ?			");
    	query.append("	, ?              , ?			     , ?			");
    	query.append("	, ?              , ?			     , ?   			");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			  user.getCompNo()
    			, maPtBuyReqDetailDTO.getPtPrItemId()
    			, maPtBuyReqHdrCommonDTO.getPtPrListId()
    			, maPtBuyReqDetailDTO.getPartDesc()
    			, maPtBuyReqDetailDTO.getPartId()
    			, maPtBuyReqDetailDTO.getPtSize()
    			, CommonUtil.getRowMoneyToNum(maPtBuyReqDetailDTO.getRecQty())
    			, maPtBuyReqDetailDTO.getRemark()
    			, CommonUtil.getRowMoneyToNum(maPtBuyReqDetailDTO.getUnitPrice())
    			, maPtBuyReqDetailDTO.getCurr()
    			, maPtBuyReqDetailDTO.getPartGrade()
    			, maPtBuyReqDetailDTO.getAppReqById()
    			, maPtBuyReqDetailDTO.getPtPnListId()
    			, maPtBuyReqDetailDTO.getAccntId()
    			, maPtBuyReqDetailDTO.getCtCtrId()
    			, maPtBuyReqDetailDTO.getPurGroup()
    			, maPtBuyReqDetailDTO.getAccntType()
    			, CommonUtil.getRowDateToNum(maPtBuyReqDetailDTO.getReqComDate())
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}