package dream.tool.iss.rent.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.tool.iss.rent.dao.MaPttIssListDAO;
import dream.tool.iss.rent.dto.MaPttIssCommonDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPttIssListDAOTarget"
 * @spring.txbn id="maPttIssListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttIssListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPttIssListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0 
     * 
     * @param maPttIssCommonDTO
     * @return List
     */
    public List findList(MaPttIssCommonDTO maPttIssCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT ''                                         		    																	seqNo,         			");
        query.append("       ''                                             																			isDelCheck,     		");
        query.append("       x.comp_no                                      																			compNo,     			");
        query.append("       x.ptisslist_id    																											ptIssListId,			");
        query.append("       SFAIDTODESC(x.issue_dept, '', 'DEPT', x.comp_no)  																			deptDesc,      			");
        query.append("       DECODE(x.issue_date,'','',SUBSTR(x.issue_date, 0, 4)||'-'||SUBSTR(x.issue_date, 5, 2)||'-'||SUBSTR(x.issue_date, 7, 2)) as issDate,				");
        query.append("       y.part_no 																													partNo,					");
        query.append("       y.description||', '||y.pt_size                																				partNameSize, 			");
        query.append("       x.issue_qty 																												issQty,					");
        query.append("       SFACODE_TO_DESC(x.ptiss_status, 'PTISS_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') 									issStatusDesc, 			");
        query.append("       SFAIDTODESC(x.rec_dept, '', 'DEPT', x.comp_no)  																			recDeptDesc, 			");
        query.append("       SFAIDTODESC(x.rec_by, '', 'EMP', x.comp_no) 																				recUserName,			");
        query.append("  	(SELECT wname FROM TAWAREHOUSE                      																								");
        query.append("       WHERE  comp_no  = x.comp_no                        																								");
        query.append("        AND  wcode_id = x.wcode_id)        																						wcodeName,				");
        query.append("             x.remark 																											remark,					");
        query.append("             x.ptiss_status issStatus");
        query.append("FROM TAPTISSLIST x, TAPARTS y");
        query.append("WHERE x.comp_no = y.comp_no(+)                            ");
        query.append("  AND x.part_id = y.part_id(+)                            ");
        query.append("  AND y.part_categ(+) = 'TOOL'							");   
        query.append(this.getWhere(maPttIssCommonDTO, user));
        query.getOrderByQuery("x.ptisslist_id DESC", maPttIssCommonDTO.getOrderBy(), maPttIssCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPttIssCommonDTO.getIsLoadMaxCount(), maPttIssCommonDTO.getFirstRow()));
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
     * @param maPttIssCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPttIssCommonDTO maPttIssCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();      

     
        query.getAndQuery("x.comp_no", maPttIssCommonDTO.getCompNo());
        
        if (!"".equals(maPttIssCommonDTO.getPtIssListId()))
        {
            query.getAndQuery("x.ptisslist_id", maPttIssCommonDTO.getPtIssListId());
            return query.toString();
        }

        query.getLikeQuery("y.full_desc(+)", maPttIssCommonDTO.getFilterPartNameSize());
        // 품명/규격
        /*if(!"".equals(maPttIssCommonDTO.getFilterPartNameSize()))
        {
            query.append(" AND  x.part_id IN (SELECT part_id FROM TAPARTS       ");
            query.append("                    WHERE  comp_no = x.comp_no        ");
            query.getLikeQuery("description", maPttIssCommonDTO.getFilterPartNameSize());
            query.append("                    UNION ALL");      
            query.append("                    SELECT part_id FROM TAPARTS       ");
            query.append("                    WHERE  comp_no = x.comp_no        ");
            query.getLikeQuery("pt_size", maPttIssCommonDTO.getFilterPartNameSize());
            query.append("                    )                                 ");      
        }*/
        
        query.getCodeLikeQuery("x.issue_dept", "(SELECT a.description FROM TADEPT a WHERE a.dept_id = x.issue_dept) ", maPttIssCommonDTO.getFilterDeptId(), maPttIssCommonDTO.getFilterDeptDesc());

        // 창고명
        if(!"".equals(maPttIssCommonDTO.getFilterWcodeId()))
        {
            query.getLikeQuery("x.wcode_id", maPttIssCommonDTO.getFilterWcodeId());
        }
        else if(!"".equals(maPttIssCommonDTO.getFilterWname()))
        {
            query.append(" AND  x.wcode_id IN (SELECT wcode_id FROM TAWAREHOUSE ");
            query.append("                     WHERE  comp_no = a.comp_no       ");
            query.getLikeQuery("wname", maPttIssCommonDTO.getFilterWname());
            query.append("					   )						        ");
        }
        //query.getCodeLikeQuery("y.part_id", "(SELECT a.description FROM TAPARTS a WHERE a.part_id = y.part_id)", maPttIssCommonDTO.getpart(), maPtIssCommonDTO.getPartDesc());
        query.getCodeLikeQuery("x.rec_by", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.rec_by)", maPttIssCommonDTO.getFilterRecBy(), maPttIssCommonDTO.getFilterRecName());
        query.getAndDateQuery("x.issue_date", maPttIssCommonDTO.getFilterIssStartDate(), maPttIssCommonDTO.getFilterIssEndDate());
        query.getCodeLikeQuery("x.ptiss_status", "SFACODE_TO_DESC(y.ptiss_status,'PTISS_STATUS','SYS','','"+user.getLangId()+"')", maPttIssCommonDTO.getPtIssStatus(), maPttIssCommonDTO.getPtIssStatusDesc());
        
        return query.toString();
    }

    public int deleteList(String compNo, String ptIssListId)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPTISSLIST		              ");
    	query.append("WHERE  comp_no       = '"+compNo+"'		  ");
    	query.append("  AND  ptisslist_id  = '"+ptIssListId+"'    ");
    	
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
	public String findTotalCount(MaPttIssCommonDTO maPttIssCommonDTO, User user) {
		
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT    	      		            		");
        query.append("		  COUNT(*)   		   			        ");
        query.append("FROM TAPTISSLIST x, TAPARTS y					");
        query.append("WHERE x.comp_no = y.comp_no(+)                ");
        query.append("  AND x.part_id = y.part_id(+)                ");
        query.append("  AND y.part_categ(+) = 'TOOL'				");   
        query.append(this.getWhere(maPttIssCommonDTO, user));
        
        List resultList =  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
        
	}
}