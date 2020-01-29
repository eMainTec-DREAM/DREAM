package dream.part.pur.req.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.part.pur.req.dao.MaPtPurReqDetailDAO;
import dream.part.pur.req.dto.MaPtPurReqDetailDTO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;

/**
 * 부품수리 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.2
 * @spring.bean id="maPtPurReqDetailDAOTarget"
 * @spring.txbn id="maPtPurReqDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtPurReqDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtPurReqDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtReqCommonDTO111
     * @param loginUser
     * @return
     */
    public MaPtPurReqDetailDTO findDetail(MaPtReqCommonDTO maPtReqCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT                                                                     ");
        query.append("         x.DEPT_ID                                         AS entDept       ");
        query.append("       , SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no)     AS entDeptDesc   ");
        query.append("       , x.DESCRIPTION                                     AS partDesc      ");
        query.append("       , x.PART_ID                                         AS partId        ");
        query.append("       , y.part_no                                         AS partNo        ");
        query.append("       , y.erp_part_no                                     AS erpPartNo     ");
        query.append("       , x.PT_SIZE                                         AS ptSize        ");
        query.append("       , x.PTPNLIST_ID                                     AS reqId         ");
        query.append("       , x.PTPNLIST_NO                                     AS reqNo         ");
        query.append("       , x.PTPNLIST_STATUS                                 AS inputStatus   ");
        query.append("       , SFACODE_TO_DESC(x.ptpnlist_status, 'PTPNLIST_STATUS', 'SYS', '"+ loginUser.getCompNo() +"','"+loginUser.getLangId()+"') AS inputStatusDesc ");
        query.append("       , x.REC_QTY                                         AS qty           ");
        query.append("       , x.REMARK                                          AS remark        ");
        query.append("       , x.REQ_DATE                                        AS reDate        ");
        query.append("       , x.REc_DATE                                        AS recDate       ");
        query.append("       , x.rec_by                                          AS recBy         ");
        query.append("       , ( SELECT emp_name                                                  ");
        query.append("             FROM TAEMP                                                     ");
        query.append("            WHERE comp_no = x.comp_no                                       ");
        query.append("              AND emp_id  = x.rec_by )                     AS recByName     ");
        query.append("       , x.USER_ID                                         AS enterById     ");
        query.append("       , ( SELECT emp_name                                                  ");
        query.append("             FROM TAEMP                                                     ");
        query.append("            WHERE comp_no = x.comp_no                                       ");
        query.append("              AND emp_id  = x.user_id )                    AS  enterByName  ");
        query.append("       , y.part_no||NVL2(y.part_no,',','')||x.description||NVL2(x.description,',','')||x.pt_size AS detailTitle ");
        query.append("       , x.plant                                           AS plant         ");
        query.append("       , ( SELECT a.description                                             ");
        query.append("             FROM taplant a                                                 ");
        query.append("            WHERE a.comp_no = x.comp_no                                     ");
        query.append("              AND a.plant = x.plant )                      AS plantDesc     ");
        query.append("       , x.usage                                           AS usage         ");
        query.append("       , x.equip_id                                        AS usedEquip     ");
        query.append("       , ( SELECT a.description                                             ");
        query.append("             FROM taequipment a                                             ");
        query.append("            WHERE a.comp_no  = x.comp_no                                    ");
        query.append("              AND a.equip_id = x.equip_id )                AS usedEquipDesc ");
        query.append("       , x.rec_by                                          AS recBy         ");
        query.append("       , ( SELECT a.emp_name                                                ");
        query.append("             FROM taemp a                                                   ");
        query.append("            WHERE a.comp_no = x.comp_no                                     ");
        query.append("              AND a.emp_id  = x.rec_by)                    AS recByName     ");
        query.append("       , x.rec_dept                                        AS recDept       ");
        query.append("       , ( SELECT a.description                                             ");
        query.append("             FROM tadept a                                                  ");
        query.append("            WHERE a.comp_no = x.comp_no                                     ");
        query.append("              AND a.dept_id = x.rec_dept )                 AS recDeptDesc   ");
        query.append("       , SFACODE_TO_DESC(x.ptpnlist_status, 'PTPNLIST_STATUS', 'SYS', '"+ loginUser.getCompNo() +"','"+loginUser.getLangId()+"') AS inputStatusDesc");
        query.append("       , y.model 											 AS ptmodel		  ");
        query.append("       , x.uom 											 AS uom		      ");
        query.append("       , SFACODE_TO_DESC(x.uom, 'UOM', 'USR', x.comp_no,'"+loginUser.getLangId()+"') AS uomDesc       ");
        query.append("       , x.pr_date 										 AS prDate		  ");
        query.append("       , x.pr_on_qty 										 AS prOnQty		  ");
        query.append("       , x.pr_qty 										 AS prQty		  ");
        query.append("       , x.po_on_qty 										 AS poOnQty		  ");
        query.append("       , x.po_qty 										 AS poQty		  ");
        query.append("       , x.gr_on_qty 										 AS grOnQty		  ");
        query.append("       , x.gr_qty 										 AS grQty		  ");
        query.append("       , y.last_price 									 AS lastPrice	  ");
        query.append("   FROM TAPTPNLIST x LEFT OUTER JOIN TAPARTS y                              ");
        query.append("     ON x.comp_no = y.comp_no                                               ");
        query.append("    AND x.part_id = y.part_id                                               ");
        query.append("  WHERE 1=1                                                                 ");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.PTPNLIST_ID", maPtReqCommonDTO.getReqId());
    
        return (MaPtPurReqDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtPurReqDetailDTO()));
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailDTO
     * @return
     */
    public int insertDetail(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO TAPTPNLIST       ");
        query.append("          (                  ");
        query.append("             comp_no         ");
        query.append("           , ptpnlist_id     ");
        query.append("           , ptpnlist_no     ");
        query.append("           , ptpnlist_status ");
        query.append("           , dept_id         ");
        query.append("           , user_id         ");
        query.append("           , req_date        ");
        query.append("           , part_id         ");
        query.append("           , description     ");
        query.append("           , pt_size         ");
        query.append("           , rec_qty         ");
        query.append("           , rec_dept        ");
        query.append("           , rec_by          ");
        query.append("           , equip_id        ");
        query.append("           , usage           ");
        query.append("           , plant           ");
        query.append("           , remark          ");
        query.append("           , uom             ");
        query.append("          ) VALUES (         ");
        query.append("             ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("           , ?               ");
        query.append("          )                  ");

        Object[] objects = new Object[] {
                 user.getCompNo()
               , maPtPurReqDetailDTO.getReqId()
               , maPtPurReqDetailDTO.getReqNo()
               , maPtPurReqDetailDTO.getInputStatus()
               , maPtPurReqDetailDTO.getEntDept()
               , maPtPurReqDetailDTO.getEnterById()
               , maPtPurReqDetailDTO.getReDate()
               , maPtPurReqDetailDTO.getPartId()
               , maPtPurReqDetailDTO.getPartDesc()
               , maPtPurReqDetailDTO.getPtSize()
               , maPtPurReqDetailDTO.getQty()
               , maPtPurReqDetailDTO.getRecDept()
               , maPtPurReqDetailDTO.getRecBy()
               , maPtPurReqDetailDTO.getUsedEquip()
               , maPtPurReqDetailDTO.getUsage()
               , maPtPurReqDetailDTO.getPlant()
               , maPtPurReqDetailDTO.getRemark()
               , maPtPurReqDetailDTO.getUom()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailDTO
     * @return
     */
    public int updateDetail(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPTPNLIST SET     ");
        query.append("       dept_id        = ? ");
        query.append("     , description    = ? ");
        query.append("     , part_id        = ? ");
        query.append("     , pt_size        = ? ");
        query.append("     , rec_qty        = ? ");
        query.append("     , remark         = ? ");
        query.append("     , user_id        = ? ");
        query.append("     , rec_dept       = ? ");
        query.append("     , rec_by         = ? ");
        query.append("     , rec_date       = ? ");
        query.append("     , equip_id       = ? ");
        query.append("     , usage          = ? ");
        query.append("     , uom            = ? ");
        query.append("     , pr_date        = ? ");
        query.append("     , pr_on_qty      = ? ");
        query.append("     , pr_qty         = ? ");
        query.append("     , po_on_qty      = ? ");
        query.append("     , po_qty         = ? ");
        query.append("     , gr_on_qty      = ? ");
        query.append("     , gr_qty         = ? ");
        query.append("     , ptpnlist_status= ? ");
        query.append(" WHERE comp_no        = ? ");
        query.append("   AND ptpnlist_id    = ? ");
       
        Object[] objects = new Object[] {
                 maPtPurReqDetailDTO.getEntDept()
               , maPtPurReqDetailDTO.getPartDesc()
               , maPtPurReqDetailDTO.getPartId()
               , maPtPurReqDetailDTO.getPtSize()
               , CommonUtil.getRowMoneyToNum(maPtPurReqDetailDTO.getQty())
               , maPtPurReqDetailDTO.getRemark()
               , maPtPurReqDetailDTO.getEnterById()
               , maPtPurReqDetailDTO.getRecDept()
               , maPtPurReqDetailDTO.getRecBy()
               , CommonUtil.getRowDateToNum(maPtPurReqDetailDTO.getRecDate())
               , maPtPurReqDetailDTO.getUsedEquip()
               , maPtPurReqDetailDTO.getUsage()
               , maPtPurReqDetailDTO.getUom()
               , CommonUtil.getRowDateToNum(maPtPurReqDetailDTO.getPrDate())
               , CommonUtil.getRowMoneyToNum(maPtPurReqDetailDTO.getPrOnQty())
               , CommonUtil.getRowMoneyToNum(maPtPurReqDetailDTO.getPrQty())
               , CommonUtil.getRowMoneyToNum(maPtPurReqDetailDTO.getPoOnQty())
               , CommonUtil.getRowMoneyToNum(maPtPurReqDetailDTO.getPoQty())
               , CommonUtil.getRowMoneyToNum(maPtPurReqDetailDTO.getGrOnQty())
               , CommonUtil.getRowMoneyToNum(maPtPurReqDetailDTO.getGrQty())
               , maPtPurReqDetailDTO.getInputStatus()
               , user.getCompNo()
               , maPtPurReqDetailDTO.getReqId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * update 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailDTO
     * @return
     */
    public int updateStatus(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPTPNLIST SET             ");
        query.append("       ptpnlist_status  = 'C'     ");
        query.append("       ,req_date  	  = ?       ");
        query.append("WHERE  comp_no          = ?       ");
        query.append("  AND  ptpnlist_id      = ?       ");
        
        Object[] objects = new Object[] {
        		maPtPurReqDetailDTO.getReDate(),
                maPtPurReqDetailDTO.getCompNo(),
                maPtPurReqDetailDTO.getReqId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * update rec
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailDTO
     * @return
     */
    public int recStatus(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAPTPNLIST SET             ");
    	query.append("       ptpnlist_status  = 'P'     ");
    	query.append("       ,rec_date  	  = ?       ");
    	query.append("       ,rec_by     	  = ?       ");
    	query.append("WHERE  comp_no          = ?       ");
    	query.append("  AND  ptpnlist_id      = ?       ");
    	
    	Object[] objects = new Object[] {
    			maPtPurReqDetailDTO.getRecDate(),
    			loginUser.getEmpId(),
    			maPtPurReqDetailDTO.getCompNo(),
    			maPtPurReqDetailDTO.getReqId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * 작성상태 확인
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param   id
     * @param   user
     * @return  QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()))
     */
    public String chkPurStatus(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT count(*)                                   ");
        query.append("   FROM TAPTPNLIST                                 ");
        query.append("  WHERE comp_no         = '"+ user.getCompNo() +"' ");
        query.append("    AND ptpnlist_status = 'W'                      ");
        query.append("    AND ptpnlist_id     = '"+ maPtPurReqDetailDTO.getReqId() +"' ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    /**
     * 작성자 확인
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param   id
     * @param   user
     * @return
     */
    public String chkDelUser(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT count(*)                                                                        ");
        query.append("   FROM TAPTPNLIST                                                                      ");
        query.append("  WHERE comp_no     = '"+ user.getCompNo() +"'                                          ");
        query.append("    AND ptpnlist_id = '"+ maPtPurReqDetailDTO.getReqId() +"'                            ");
        query.append("    AND user_id IN (SELECT emp_id FROM TAUSER WHERE user_id = '"+ user.getUserId() +"') ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }

    /**
     * 요청취소 상태변경
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * @param   id
     * @param   user
     * @return
     */
	@Override
	public int recCancel(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser) {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAPTPNLIST SET             ");
    	query.append("       ptpnlist_status  = 'W'     ");
    	query.append("WHERE  comp_no          = ?       ");
    	query.append("  AND  ptpnlist_id      = ?       ");
    	
    	Object[] objects = new Object[] {
    			maPtPurReqDetailDTO.getCompNo(),
    			maPtPurReqDetailDTO.getReqId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
	}
}