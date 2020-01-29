package dream.tool.iss.rtn.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.tool.iss.rtn.dao.MaPttRtnListDAO;
import dream.tool.iss.rtn.dto.MaPttRtnCommonDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPttRtnListDAOTarget"
 * @spring.txbn id="maPttRtnListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttRtnListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPttRtnListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0 
     * 
     * @param maPttRtnCommonDTO
     * @return List
     */
    public List findList(MaPttRtnCommonDTO maPttRtnCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maPttRtnCommonDTO.getCompNo();
        
        query.append("SELECT ''                                             																	seqNo,          ");
        query.append("       ''                                            																	 	isDelCheck,     ");
        query.append("       x.comp_no                                     																	 	compNo,    		");
        query.append("       x.ptrtnlist_id    																									ptRtnListId,	");
        query.append("       SFAIDTODESC(x.exe_dept, '', 'DEPT', x.comp_no)  																	deptDesc,       ");
        query.append("       DECODE(x.rtn_date,'','',SUBSTR(x.rtn_date, 0, 4)||'-'||SUBSTR(x.rtn_date, 5, 2)||'-'||SUBSTR(x.rtn_date, 7, 2)) as rtnDate,		");
        query.append("       y.part_no 																											partNo,			");
        query.append("       y.description||', '||y.pt_size                 																	partNameSize,   ");
        query.append("       x.rtn_qty 																											rtnQty,			");
        query.append("       SFACODE_TO_DESC(x.ptrtn_status, 'PTRTN_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') 							rtnStatusDesc,  ");
        query.append("       SFAIDTODESC(x.rec_dept, '', 'DEPT', x.comp_no)  																	recDeptDesc,    ");
        query.append("       SFAIDTODESC(x.rec_by, '', 'EMP', x.comp_no) 																		recUserName,	");
        query.append("      (SELECT wname FROM TAWAREHOUSE                     																		 			");
        query.append("       WHERE  comp_no  = x.comp_no                        																				");
        query.append("         AND  wcode_id = x.wcode_id)        																				wcodeName,		");
        query.append("             x.remark 																									remark,			");
        query.append("             x.ptrtn_status 																								rtnStatus		");
        query.append("FROM TAPTRTNLIST x, TAPARTS y								");
        query.append("WHERE x.comp_no = y.comp_no(+)                            ");
        query.append("  AND x.part_id = y.part_id(+)                            ");
        query.append("  AND y.part_categ(+) = 'TOOL'							");
        query.append(this.getWhere(maPttRtnCommonDTO,user)						);
        query.getOrderByQuery("x.ptrtnlist_id DESC", maPttRtnCommonDTO.getOrderBy(), maPttRtnCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPttRtnCommonDTO.getIsLoadMaxCount(), maPttRtnCommonDTO.getFirstRow()));
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
    public int deletePtIss(String compNo, String wcodeId, String partId, String partGrade)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPTSTOCK			              ");
    	query.append("WHERE  comp_no       = '"+compNo+"'		  ");
    	query.append("  AND  wcode_id      = '"+wcodeId+"'		  ");
    	query.append("  AND  part_id       = '"+partId+"'		  ");
    	query.append("  AND  part_grade    = '"+partGrade+"'	  ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPttRtnCommonDTO maPttRtnCommonDTO,User user)
    {        
        QueryBuffer query = new QueryBuffer();      

     
        query.getAndQuery("x.comp_no", maPttRtnCommonDTO.getCompNo());
        
        if (!"".equals(maPttRtnCommonDTO.getPtRtnListId()))
        {
            query.getAndQuery("x.ptrtnlist_id", maPttRtnCommonDTO.getPtRtnListId());
            return query.toString();
        }

        query.getLikeQuery("y.full_desc(+)", maPttRtnCommonDTO.getFilterPartNameSize());
        // 품명/규격
        /*if(!"".equals(maPttRtnCommonDTO.getFilterPartNameSize()))
        {
            query.append(" AND  x.part_id IN (SELECT part_id FROM TAPARTS       ");
            query.append("                    WHERE  comp_no = x.comp_no        ");
            query.getLikeQuery("description", maPttRtnCommonDTO.getFilterPartNameSize());
            query.append("                    UNION ALL");      
            query.append("                    SELECT part_id FROM TAPARTS       ");
            query.append("                    WHERE  comp_no = x.comp_no        ");
            query.getLikeQuery("pt_size", maPttRtnCommonDTO.getFilterPartNameSize());
            query.append("                    )                                 ");      
        }*/
        
        query.getCodeLikeQuery("x.exe_dept", "(SELECT a.description FROM TADEPT a WHERE a.dept_id = x.exe_dept) ", maPttRtnCommonDTO.getFilterDeptId(), maPttRtnCommonDTO.getFilterDeptDesc());

        // 창고명
        if(!"".equals(maPttRtnCommonDTO.getFilterWcodeId()))
        {
            query.getAndNumKeyQuery("x.wcode_id", maPttRtnCommonDTO.getFilterWcodeId());
        }
        else if(!"".equals(maPttRtnCommonDTO.getFilterWname()))
        {
            query.append(" AND  x.wcode_id IN (SELECT wcode_id FROM TAWAREHOUSE ");
            query.append("                     WHERE  comp_no = a.comp_no       ");
            query.getLikeQuery("wname", maPttRtnCommonDTO.getFilterWname());
            query.append("					   )						        ");
        }
        //query.getCodeLikeQuery("y.part_id", "(SELECT a.description FROM TAPARTS a WHERE a.part_id = y.part_id)", maPttRtnCommonDTO.getpart(), maPtIssCommonDTO.getPartDesc());
        query.getCodeLikeQuery("x.rec_by", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.rec_by)", maPttRtnCommonDTO.getFilterRecBy(), maPttRtnCommonDTO.getFilterRecName());
        query.getAndDateQuery("x.rtn_date", maPttRtnCommonDTO.getFilterRtnStartDate(), maPttRtnCommonDTO.getFilterRtnEndDate());
        query.getCodeLikeQuery("x.ptrtn_status", "SFACODE_TO_DESC(x.ptrtn_status,'PTRTN_STATUS','SYS','','"+user.getLangId()+"')", maPttRtnCommonDTO.getPtRtnStatus(), maPttRtnCommonDTO.getPtRtnStatusDesc());
        
        return query.toString();
    }

    public int deleteList(String compNo, String ptRtnListId)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPTRTNLIST		              ");
    	query.append("WHERE  comp_no       = '"+compNo+"'		  ");
    	query.append("  AND  ptrtnlist_id  = '"+ptRtnListId+"'    ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }

    public int deleteWoParts(String wopartId)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAWOPARTS             ");
        query.append("WHERE wopart_id = '"+wopartId+"'  ");
        
        return this.getJdbcTemplate().update(query.toString());
    }

	@Override
	public String findTotalCount(MaPttRtnCommonDTO maPttRtnCommonDTO, User user) {

		QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT          		            				 	    ");
        query.append("		 COUNT(*) 	     			                		");
        query.append("FROM TAPTRTNLIST x, TAPARTS y								");
        query.append("WHERE x.comp_no = y.comp_no(+)                            ");
        query.append("  AND x.part_id = y.part_id(+)                            ");
        query.append("  AND y.part_categ(+) = 'TOOL'							");
        query.append(this.getWhere(maPttRtnCommonDTO,user)						);

        List resultList =  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}