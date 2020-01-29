package dream.tool.rpt.renthist.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.tool.rpt.renthist.dao.MaPttRentListDAO;
import dream.tool.rpt.renthist.dto.MaPttRentCommonDTO;
import dream.tool.rpt.renthist.form.MaPttRentListForm;

/**
 * 공기구대여내역 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPttRentListDAOTarget"
 * @spring.txbn id="maPttRentListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttRentListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPttRentListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRentCommonDTO
     * @return List
     */
    public List findList(MaPttRentCommonDTO maPttRentCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maPttRentCommonDTO.getCompNo();
        
        query.append("SELECT");
        query.append(" ''                                             seqNo,    ");
        query.append("       ''                                    AS isDelCheck,     ");
        query.append("       (SELECT wname FROM TAWAREHOUSE                      ");
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  wcode_id = x.wcode_id)        wcodeName,");
        query.append("         dbo.SFAIDTODESC(x.rec_dept, '', 'DEPT', x.comp_no)  recDeptDesc,    ");
        query.append("         dbo.SFAIDTODESC(x.rec_by, '', 'EMP', x.comp_no) recUserName ,");
        query.append("         y.part_no partNo,");
        query.append("         y.description+', '+ISNULL(y.pt_size,'')                 partNameSize,");
        query.append("         x.rent_qty rentQty,");
        query.append("(SELECT ISNULL(SUM(z.rtn_qty),0) FROM TAPTRTNLIST z WHERE z.comp_no='"+compNo+"' AND z.ptrtn_status='W' AND z.wcode_id=x.wcode_id AND x.part_id=z.part_id AND x.rec_by=z.rec_by)returnQty,");
        query.append("         x.ptrentlist_id ptRentListId");
        query.append(" FROM TAPTRENT_STOCK x, TAPARTS y");
        query.append(" WHERE x.part_id=y.part_id");
        query.append("  AND x.comp_no = '"+compNo+"'                           ");
        query.append(this.getWhere(maPttRentCommonDTO));
        query.append("ORDER BY x.part_id DESC                                 ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRentCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPttRentCommonDTO maPttRentCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        // 부서
        query.getDeptLevelQuery("x.rec_dept", maPttRentCommonDTO.getFilterDeptId(), maPttRentCommonDTO.getFilterDeptDesc(), maPttRentCommonDTO.getCompNo());
        

     // 창고명
        if(!"".equals(maPttRentCommonDTO.getFilterWcodeId()))
        {
            query.getLikeQuery("x.wcode_id", maPttRentCommonDTO.getFilterWcodeId());
        }
        else if(!"".equals(maPttRentCommonDTO.getFilterWname()))
        {
            query.append(" AND  x.wcode_id IN (SELECT wcode_id FROM TAWAREHOUSE ");
            query.append("                     WHERE  comp_no = x.comp_no       ");
            query.getLikeQuery("wname", maPttRentCommonDTO.getFilterWname());
            query.append("					   )						        ");
        }

     // 품명/규격
        if(!"".equals(maPttRentCommonDTO.getFilterPartNameSize()))
        {
            query.append(" AND  x.part_id IN (SELECT part_id FROM TAPARTS       ");
            query.append("                    WHERE  comp_no = x.comp_no        ");
            query.getLikeQuery("description", maPttRentCommonDTO.getFilterPartNameSize());
            query.append("                    UNION ALL");      
            query.append("                    SELECT part_id FROM TAPARTS       ");
            query.append("                    WHERE  comp_no = x.comp_no        ");
            query.getLikeQuery("pt_size", maPttRentCommonDTO.getFilterPartNameSize());
            query.append("                    )                                 ");      
        }
        
        query.getCodeLikeQuery("x.rec_by", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.rec_by)", maPttRentCommonDTO.getFilterRecBy(), maPttRentCommonDTO.getFilterRecName());
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
    public int reqHdrPtRtn(MaPttRentListForm maPttRentListForm, String ptrentlistId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;
    	
    	query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPTRTNLIST (                                 ");
    	query.append("  comp_no,    ptRtnlist_id,  wcode_id,   part_grade,      ");
    	query.append("  rtn_date,   part_id,       rtn_qty,    ptrtn_status, 	");
    	query.append("  exe_dept,   exe_by,        rec_by,     rec_dept,        ");
    	query.append("  remark                                                  ");
    	query.append(")  SELECT ");
    	query.append("  comp_no,    NEXT VALUE FOR SQAPTRTNLIST_ID,   wcode_id,   part_grade,");
    	query.append("  CONVERT(VARCHAR, GETDATE(), 112), part_id,   rent_qty,    'W',");
    	query.append("  ?,          ?,             rec_by,     rec_dept,");
    	query.append("  ''");
    	query.append("  FROM TAPTRENT_STOCK");
    	query.append("  WHERE ptrentlist_id = ?      ");

    	
    	Object[] objects = new Object[] {
    			user.getDeptId(),
    			user.getEmpId(),
    			ptrentlistId
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
    public int reqDtlPtRtn(MaPttRentListForm maPttRentListForm, String partId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	MaPttRentCommonDTO maPttRentCommonDTO = maPttRentListForm.getMaPttRentCommonDTO();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPTPRITEM (								");
    	query.append("	currency,comp_no,		ptpritem_id,	ptprlist_id,");
    	query.append("	description,	part_id,		pt_size,			");
    	query.append("	rec_qty												");
    	query.append("	)	VALUES	(										");
    	query.append("	(select cdsysd_no from tacdsysd where list_Type='CURRENCY' and param1='Y'),		");
    	query.append("	?,				NEXT VALUE FOR SQAPTPRITEM_ID,?,			");
    	query.append("	(SELECT description FROM TAPARTS WHERE comp_no = '"+user.getCompNo()+"' AND part_id = '"+partId+"'),	?,");
    	query.append("	(SELECT pt_size FROM TAPARTS WHERE comp_no = '"+user.getCompNo()+"' AND part_id = '"+partId+"'),");
    	query.append("	ISNULL((SELECT CASE WHEN									");
    	query.append("			ISNULL(y.safty_qty,0)							");
    	query.append("			- ABS(ISNULL(x.stock_qty,0))					");
    	query.append("			- ABS((SELECT ISNULL(SUM(use_qty),0) FROM TAWOPARTS		");
    	query.append("				WHERE wkor_id IN (SELECT wkor_id FROM TAWORKORDER WHERE comp_no='"+user.getCompNo()+"' AND wo_status='P')");
    	query.append("				AND part_id = x.part_id					");
    	query.append("			AND part_grade= 'A')) <=0 					");
    	query.append("		THEN 1											");
    	query.append("		ELSE  ISNULL(y.safty_qty,0)						");
    	query.append("			- ABS(ISNULL(x.stock_qty,0))					");
    	query.append("			- ABS((SELECT ISNULL(SUM(use_qty),0) FROM TAWOPARTS");
    	query.append("				WHERE wkor_id IN (SELECT wkor_id FROM TAWORKORDER WHERE comp_no='"+user.getCompNo()+"' AND wo_status='P')");
    	query.append("			AND part_id = x.part_id						");
    	query.append("		AND part_grade= 'A')) END						");
    	query.append("	FROM   TAPTSTOCK x,	TAPTSAFTYSTOCK y				");
    	query.append("	WHERE  x.comp_no = y.comp_no						");
    	query.append("	AND  x.wcode_id = y.wcode_id						");
    	query.append("	AND  x.part_id  = y.part_id							");
    	query.append("	AND  x.comp_no  = '"+user.getCompNo()+"'			");
    	query.append("	AND x.part_id = '"+partId+"'						");
    	query.append("	AND x.part_grade = 'A'								");
    	query.append("	),1)												");
    	query.append(")														");
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			maPttRentCommonDTO.getReqIdx(),
    			partId
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}