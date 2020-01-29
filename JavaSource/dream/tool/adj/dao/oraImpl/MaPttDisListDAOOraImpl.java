package dream.tool.adj.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.tool.adj.dao.MaPttDisListDAO;
import dream.tool.adj.dto.MaPttDisCommonDTO;

/**
 * dao
 * @author  kim21017
 * @version $Id: MaPttDisListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPttDisListDAOTarget"
 * @spring.txbn id="maPttDisListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttDisListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPttDisListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPttDisListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisCommonDTO
     * @return List
     */
    public List findDisList(MaPttDisCommonDTO maPttDisCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT ''                                              seqNo,    		");
        query.append("       ''                                              isDelCheck,	");
        query.append("       x.comp_no                                       compNo,    	");
        query.append("       x.ptdisuselist_id    							 ptDisuseListId,");
        query.append("       SFAIDTODESC(x.exe_dept, '', 'DEPT', x.comp_no)  deptDesc,      ");
        query.append("       DECODE(x.disuse_date,'','',SUBSTR(x.disuse_date, 0, 4)||'-'||SUBSTR(x.disuse_date, 5, 2)||'-'||SUBSTR(x.disuse_date, 7, 2)) as disUseDate,");
        query.append("       x.description 									description,	");
        query.append("       SFACODE_TO_DESC(x.ptdisuse_status, 'PTDISUSE_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') disStatusDesc, ");
        query.append("       SFAIDTODESC(x.exe_by, '', 'EMP', x.comp_no) 	exeByName,		");
        query.append("       (SELECT wname FROM TAWAREHOUSE                 		   		");
        query.append("        WHERE  comp_no  = x.comp_no                        			");
        query.append("          AND  wcode_id = x.wcode_id)        			wcodeName,		");
        query.append("       x.remark 										remark,			");
        query.append("       x.ptdisuse_status 								disStatus		");
        query.append("FROM TAPTDISUSELIST x													");
        query.append("WHERE 1=1                    											");
        query.append(this.getWhere(maPttDisCommonDTO,user));
        query.getOrderByQuery("x.ptdisuselist_id DESC", maPttDisCommonDTO.getOrderBy(), maPttDisCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPttDisCommonDTO.getIsLoadMaxCount(), maPttDisCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaPttDisListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPttDisCommonDTO maPttDisCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("x.comp_no", maPttDisCommonDTO.getCompNo());
        
        if (!"".equals(maPttDisCommonDTO.getPtdisuselistId()))
        {
            query.getAndQuery("x.ptdisuselist_id", maPttDisCommonDTO.getPtdisuselistId());
            return query.toString();
        }

        // 품명/규격
        if(!"".equals(maPttDisCommonDTO.getFilterPartNameSize()))
        {
        	query.append("AND EXISTS (SELECT a.ptdisuseitem_id							");
        	query.append("            FROM   TAPTDISUSEITEM a LEFT OUTER JOIN TAPARTS b	");
        	query.append("                                    ON  a.part_id = b.part_id	");
        	query.append("                                    AND a.comp_no = b.comp_no	");
        	query.append("            WHERE a.ptdisuselist_id = x.ptdisuselist_id		");
        	query.getLikeQuery("b.full_desc",maPttDisCommonDTO.getFilterPartNameSize() );
        	query.append("                     ) 										");
  
        }
        
        query.getCodeLikeQuery("x.exe_dept", "(SELECT a.description FROM TADEPT a WHERE a.dept_id = x.exe_dept) ", maPttDisCommonDTO.getFilterDeptId(), maPttDisCommonDTO.getFilterDeptDesc());

        // 창고명
        if(!"".equals(maPttDisCommonDTO.getFilterWcodeId()))
        {
            query.getLikeQuery("x.wcode_id", maPttDisCommonDTO.getFilterWcodeId());
        }
        else if(!"".equals(maPttDisCommonDTO.getFilterWname()))
        {
            query.append(" AND  x.wcode_id IN (SELECT wcode_id FROM TAWAREHOUSE ");
            query.append("                     WHERE  comp_no = x.comp_no       ");
            query.getLikeQuery("wname", maPttDisCommonDTO.getFilterWname());
            query.append("					   )						        ");
        }
        //query.getCodeLikeQuery("y.part_id", "(SELECT a.description FROM TAPARTS a WHERE a.part_id = y.part_id)", maPttDisCommonDTO.getpart(), maPtIssCommonDTO.getPartDesc());
        query.getCodeLikeQuery("x.exe_by", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.exe_by)", maPttDisCommonDTO.getFilterExeBy(), maPttDisCommonDTO.getFilterExeName());
        query.getAndDateQuery("x.disuse_date", maPttDisCommonDTO.getFilterDisStartDate(), maPttDisCommonDTO.getFilterDisEndDate());
        query.getCodeLikeQuery("x.ptdisuse_status", "SFACODE_TO_DESC(x.ptdisuse_status,'PTDISUSE_STATUS','SYS','','"+user.getLangId()+"')", maPttDisCommonDTO.getPtDisStatus(), maPttDisCommonDTO.getPtDisStatusDesc());

        return query.toString();
    }
    
    /**
     * 상태 조회
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     */
    public String findPtDisListStatus(String compNo, String ptdisuselistId)
    {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT ptdisuse_status                                   ");
        query.append("FROM   TAPTDISUSELIST x                                    ");
        query.append("WHERE  comp_no      = '"+compNo+"'                        ");
        query.append("  AND  ptdisuselist_id = '"+ptdisuselistId+"'                   ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    /**
     * delete
     * @author kim21017
     * @version $Id: MaAppLineListDAO.java,v 1.0 2015   5/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteDis(String compNo, String id)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	String disId = id;
    	
    	query.append("DELETE FROM TAPTDISUSELIST			");
    	query.append("WHERE ptdisuselist_id = '"+disId+"'	");

    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPTDISUSEITEM				");
    	query.append("WHERE ptdisuselist_id = '"+disId+"'	");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }

	@Override
	public String findTotalCount(MaPttDisCommonDTO maPttDisCommonDTO, User user) {
		
    	QueryBuffer query = new QueryBuffer();
    	
        query.append("SELECT          		        ");
        query.append("		 COUNT(*) 	     		");
        query.append("FROM TAPTDISUSELIST x			");
        query.append("WHERE 1=1                    	");
        query.append(this.getWhere(maPttDisCommonDTO,user));
        
        List resultList =  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	
	}

}