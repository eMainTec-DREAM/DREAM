package dream.part.pur.req.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.part.pur.req.dao.MaPtPurReqListDAO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;

/**
 * 부품수리 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtPurReqListDAOTarget"
 * @spring.txbn id="maPtPurReqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtPurReqListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtPurReqListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtReqCommonDTO
     * @return List
     */
    public List findList(MaPtReqCommonDTO maPtReqCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append(" SELECT                                                                                                                                     ");
        query.append("         ''                                                                                                                AS seqNo         ");
        query.append("       , ''                                                                                                                AS isDelCheck    ");
        query.append("       , x.ptpnlist_id                                                                                                     AS reqId         ");
        query.append("       , x.comp_no                                                                                                         AS compNo        ");
        query.append("       , x.ptpnlist_no                                                                                                     AS reqNo         ");
        query.append("       , y.part_no                                                                                                         AS partNo        ");
        query.append("       , y.erp_part_no                                                                                                     AS erpPartNo     ");
        query.append("       , x.part_id                                                                                                         AS partId        ");
        query.append("       , x.description                                                                                                     AS partDesc      ");
        query.append("       , x.pt_size                                                                                                         AS ptSize        ");
        query.append("       , y.model                                                                                                           AS model         ");
        query.append("       , x.rec_qty                                                                                                         AS qty           ");
        query.append("       , x.ptpnlist_status                                                                                                 AS purStatusId   ");
        query.append("       , SFACODE_TO_DESC(x.ptpnlist_status, 'PTPNLIST_STATUS', 'SYS', '"+ user.getCompNo() +"','"+user.getLangId()+"')     AS purstatus     ");
        query.append("       , SFAIDTODESC(x.dept_id, '', 'DEPT', '"+ user.getCompNo() +"')                                                      AS reqDept       ");
        query.append("       , x.dept_id                                                                                                         AS entDept       ");
        query.append("       , x.user_id                                                                                                         AS enterById     ");
        query.append("       , ( SELECT emp_name                                                                                                                  ");
        query.append("             FROM TAEMP a                                                                                                                   ");
        query.append("            WHERE a.comp_no = x.comp_no                                                                                                     ");
        query.append("              AND a.emp_id = x.user_id )                                                                                   AS enterBy,      ");
        query.getDate("x.req_date", "reqDate");
        query.append("       , x.rec_dept                                                                                                        AS recDept       ");
        query.append("       , SFAIDTODESC(x.rec_dept, '', 'DEPT', '"+ user.getCompNo() +"')                                                     AS recDeptDesc   ");
        query.append("       , x.rec_by                                                                                                          AS recBy         ");
        query.append("       , ( SELECT emp_name                                                                                                                  ");
        query.append("             FROM TAEMP a                                                                                                                   ");
        query.append("            WHERE a.comp_no = '"+ user.getCompNo() +"'                                                                                      ");
        query.append("              AND a.emp_id  = x.rec_by )                                                                                   AS recByDesc     ");
        query.append("       , x.equip_id                                                                                                        AS usedEquip     ");
        query.append("       , ( SELECT description                                                                                                               ");
        query.append("             FROM taequipment                                                                                                               ");
        query.append("            WHERE equip_id = x.equip_id )                                                                                  AS usedEquipDesc ");
        query.append("       , x.USAGE                                                                                                           AS usage         ");
        query.append("       , x.PR_QTY                                                                                                          AS prQry         ");
        query.append("       , x.gr_qty                                                                                                          AS grQty         ");
        query.append("       , x.remark                                                                                                          AS remark        ");
        query.append("       , SFACODE_TO_DESC(x.uom, 'UOM', 'USR', x.comp_no,'"+user.getLangId()+"')                                            AS uomDesc       ");
        query.append("   FROM  TAPTPNLIST x LEFT OUTER JOIN TAPARTS y                                                                                             ");
        query.append("     ON  x.comp_no = y.comp_no                                                                                                              ");
        query.append("    AND  x.part_id = y.part_id                                                                                                              ");
        query.append("  WHERE  1=1                                                                                                                                ");
        query.append(this.getWhere(maPtReqCommonDTO, user));
        query.getOrderByQuery("x.ptpnlist_id DESC", maPtReqCommonDTO.getOrderBy(), maPtReqCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtReqCommonDTO.getIsLoadMaxCount(), maPtReqCommonDTO.getFirstRow()));
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
    public int deleteList(User user, String ptPnListId)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;
        
        query.append("DELETE FROM TAPTPNLIST                             ");
        query.append("      WHERE comp_no     = '"+ user.getCompNo() +"' ");
        query.append("        AND ptpnlist_id = '"+ ptPnListId +"'       ");
                
        rtnValue = this.getJdbcTemplate().update(query.toString());

        return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtReqCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtReqCommonDTO maPtReqCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        
        if (!"".equals(maPtReqCommonDTO.getReqId()))
        {
            query.getAndQuery("x.ptpnlist_id", maPtReqCommonDTO.getReqId());
            return query.toString();
        }
        
        //신청부서
        query.getDeptLevelQuery("x.dept_id", maPtReqCommonDTO.getFilterDeptId(),maPtReqCommonDTO.getFilterDeptDesc(), maPtReqCommonDTO.getCompNo());
        
        //신청일자
        String startDate = maPtReqCommonDTO.getFilterRegStartDate();
        String endDate = maPtReqCommonDTO.getFilterRegEndDate();
        
        query.getAndDateQuery("x.req_date", startDate, endDate);
        query.getAndDateQuery("x.rec_date", maPtReqCommonDTO.getFilterStartRecDate(), maPtReqCommonDTO.getFilterEndRecDate());
        
        //품번
        query.getLikeQuery("y.part_no", maPtReqCommonDTO.getFilterPartNo());
        
        //품명
        query.getLikeQuery("y.full_desc", maPtReqCommonDTO.getFilterPartNameSize());
        
        //작성상태
        query.getSysCdQuery("x.ptpnlist_status", maPtReqCommonDTO.getInputStatus(), maPtReqCommonDTO.getInputStatusDesc(), "PTPNLIST_STATUS", user.getCompNo(), user.getLangId());
        
        //요청번호
        query.getAndQuery("x.ptpnlist_no", maPtReqCommonDTO.getFilterReqNo());
        
        //작성자
        query.getCodeLikeQuery("x.user_id", "(SELECT emp_name FROM TAEMP WHERE comp_no = x.comp_no AND emp_id = x.user_id)", maPtReqCommonDTO.getFilterEnterId(), maPtReqCommonDTO.getFilterEnterName());
        
        //공장
        query.getPlantCdQuery("x.plant", maPtReqCommonDTO.getFilterPlant(), maPtReqCommonDTO.getFilterPlantDesc(), user.getCompNo());

        return query.toString();
    }
    /**
     * 작성상태 변경
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * @param   user
     * @param   updateRow
     * @return  getJdbcTemplate().update(query.toString(), objects)
     */
    public int updateStatus(User user, String updateRow) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE taptpnlist          ");
        query.append("   SET ptpnlist_status = ? ");
        query.append(" WHERE comp_no         = ? ");
        query.append("   AND ptpnlist_id     = ? ");

        Object[] objects = new Object[] {
                           "C"
                         , user.getCompNo()
                         , updateRow
       };
       
       return getJdbcTemplate().update(query.toString(), objects);
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
    public String chkPurStatus(String id, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT count(*)                                   ");
        query.append("   FROM TAPTPNLIST                                 ");
        query.append("  WHERE comp_no         = '"+ user.getCompNo() +"' ");
        query.append("    AND ptpnlist_status = 'W'                      ");
        query.append("    AND ptpnlist_id     = '"+ id +"'               ");
        
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
    public String chkDelUser(String id, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT count(*)                                                                        ");
        query.append("   FROM TAPTPNLIST                                                                      ");
        query.append("  WHERE comp_no     = '"+ user.getCompNo() +"'                                          ");
        query.append("    AND ptpnlist_id = '"+ id +"'                                                        ");
        query.append("    AND user_id IN (SELECT emp_id FROM TAUSER WHERE user_id = '"+ user.getUserId() +"') ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }

    public String[] findMaPtPurReqUserMailList(String id, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT a.e_mail                                 ");
        query.append("   FROM TAEMP a                                  ");
        query.append("  WHERE a.comp_no = ?                            ");
        query.append("    AND a.emp_id  = ( SELECT b.rec_by            ");
        query.append("                        FROM TAPTPNLIST b        ");
        query.append("                       WHERE b.comp_no = ?       ");
        query.append("                         AND b.ptpnlist_id = ? ) ");
        query.append("    AND a.is_mail_rec = 'Y'                      ");
       
        Object[] objects = new Object[] {
                  user.getCompNo()
                , user.getCompNo()
                , id
        };
        return QueryBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
    }

    public String[] findMaPtPurReqDeptMailList(String id, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT a.e_mail                                 ");
        query.append("   FROM TAEMP a                                  ");
        query.append("  WHERE a.comp_no = ?                            ");
        query.append("    AND a.dept_id = ( SELECT b.dept_id           ");
        query.append("                        FROM TAPTPNLIST b        ");
        query.append("                       WHERE b.comp_no = ?       ");
        query.append("                         AND b.ptpnlist_id = ? ) ");
        query.append("    AND a.is_mail_rec = 'Y'                      ");
       
        Object[] objects = new Object[] {
                  user.getCompNo()
                , user.getCompNo()
                , id
        };
        return QueryBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    public String[] findMaPtPurReqUserEmpNoList(String id, User user) {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append(" SELECT a.emp_no                                 ");
    	query.append("   FROM TAEMP a                                  ");
    	query.append("  WHERE a.comp_no = ?                            ");
    	query.append("    AND a.emp_id  = ( SELECT b.rec_by            ");
    	query.append("                        FROM TAPTPNLIST b        ");
    	query.append("                       WHERE b.comp_no = ?       ");
    	query.append("                         AND b.ptpnlist_id = ? ) ");
    	query.append("    AND a.is_mail_rec = 'Y'                      ");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			, user.getCompNo()
    			, id
    	};
    	return QueryBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    public String[] findMaPtPurReqDeptEmpNoList(String id, User user) {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append(" SELECT a.emp_no                                 ");
    	query.append("   FROM TAEMP a                                  ");
    	query.append("  WHERE a.comp_no = ?                            ");
    	query.append("    AND a.dept_id = ( SELECT b.dept_id           ");
    	query.append("                        FROM TAPTPNLIST b        ");
    	query.append("                       WHERE b.comp_no = ?       ");
    	query.append("                         AND b.ptpnlist_id = ? ) ");
    	query.append("    AND a.is_mail_rec = 'Y'                      ");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			, user.getCompNo()
    			, id
    	};
    	return QueryBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    public String findTitle(String keyNo, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT a.key_name   AS msg ");
        query.append("   FROM TALANG a            ");
        query.append("  WHERE a.key_type = ?      ");
        query.append("    AND a.lang     = ?      ");
        query.append("    AND a.key_no   = ?      ");
        
        Object[] objects = new Object[] {
                "MESSAGE"
                ,user.getLangId()
                ,keyNo
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    public List selectMaPtPurReqDetail(String id, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT                                                                                                ");
        query.append("        '구매신청'                                                                      AS TITLE          ");
        query.append("      , x.ptpnlist_no                                                                AS PTPNLISTNO     ");
        query.append("      , SFACODE_TO_DESC(x.ptpnlist_status, 'PTPNLIST_STATUS', 'SYS', '100','ko')     AS PTPNLISTSTATUS ");
        query.append("      , ( SELECT                                                                                       ");
        query.append("                 b.part_no                                                                             ");
        query.append("            FROM TAPARTS  b                                                                            ");
        query.append("            WHERE b.comp_no = x.comp_no                                                                ");
        query.append("              AND b.part_id = x.part_id )                                            AS PARTNO         ");
        query.append("      , ( SELECT                                                                                       ");
        query.append("                 a.description                                                                         ");
        query.append("            FROM TAPARTS a                                                                             ");
        query.append("           WHERE a.comp_no = x.comp_no                                                                 ");
        query.append("             AND a.part_id = x.part_id )                                             AS PARTDESC       ");
        query.append("      , x.rec_qty                                                                    AS RECQTY         ");
        query.append("      , x.pt_size                                                                    AS PTSIZE         ");
        query.append("      , ( SELECT                                                                                       ");
        query.append("                 c.description                                                                         ");
        query.append("            FROM TADEPT c                                                                              ");
        query.append("           WHERE c.comp_no = x.comp_no                                                                 ");
        query.append("             AND c.dept_id = x.dept_id )                                             AS DEPTDESC       ");
        query.append("      , ( SELECT                                                                                       ");
        query.append("                 d.emp_name                                                                            ");
        query.append("            FROM TAEMP d                                                                               ");
        query.append("           WHERE d.comp_no = x.comp_no                                                                 ");
        query.append("             AND d.emp_id = x.user_id )                                              AS EMPNAME,       ");
        query.getDate("x.req_date", "REQDATE");
        query.append("      , ( SELECT                                                                                       ");
        query.append("                 e.description                                                                         ");
        query.append("            FROM TAPLANT e                                                                             ");
        query.append("           WHERE e.comp_no = x.comp_no                                                                 ");
        query.append("             AND e.plant = x.plant )                                                 AS PLANTDESC      ");
        query.append("      , ( SELECT                                                                                       ");
        query.append("                 f.description                                                                         ");
        query.append("            FROM TAEQUIPMENT f                                                                         ");
        query.append("           WHERE f.comp_no = x.comp_no                                                                 ");
        query.append("             AND f.equip_id = x.equip_id )                                           AS EQUIPDESC      ");
        query.append("      , x.usage                                                                      AS USAGE          ");
        query.append("      , x.remark                                                                     AS REMARK         ");
        query.append("  FROM TAPTPNLIST x                                                                                    ");
        query.append(" WHERE comp_no     = ?                                                                                 ");
        query.append("   AND ptpnlist_id = ?                                                                                 ");

        Object[] objects = new Object[] {
                 user.getCompNo()
               , id 
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects); 
    }

	@Override
	public String findTotalCount(MaPtReqCommonDTO maPtReqCommonDTO, User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
		
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("   FROM  TAPTPNLIST x LEFT OUTER JOIN TAPARTS y                                                                                             ");
        query.append("     ON  x.comp_no = y.comp_no                                                                                                              ");
        query.append("    AND  x.part_id = y.part_id                                                                                                              ");
        query.append("  WHERE  1=1                                                                                                                                ");
        query.append(this.getWhere(maPtReqCommonDTO, user));
		
		List resultList=  getJdbcTemplate().queryForList(query.toString());
		return QueryBuffer.listToString(resultList);
	}
}