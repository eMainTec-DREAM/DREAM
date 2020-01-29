package dream.comm.revision.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.comm.revision.dao.CommRevHistListDAO;
import dream.comm.revision.dto.CommRevHistCommonDTO;

/**
 * 제/개정 변경이력 DAO
 * @author  kim21017
 * @version $Id: CommRevHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="commRevHistListDAOTarget"
 * @spring.txbn id="commRevHistListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CommRevHistListDAOSqlImpl extends BaseJdbcDaoSupportSql implements CommRevHistListDAO
{
	/**
     * grid find
     * @author  kim21017
     * @version $Id: CommRevHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param commRevHistListDTO
     * @return List
     */
    public List findList(CommRevHistCommonDTO commRevHistCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT  																						");
        query.append("        '' seqNo 																				");
        query.append("        ,'' isDelCheck 																		");
        query.append("        ,dbo.SFACODE_TO_DESC(x.revision_type,'REVISION_TYPE','SYS','','"+user.getCompNo()+"') revisionTypeDesc  ");
        query.append("        ,x.doc_no docNo 																		");
        query.append("        ,x.rev_no revNo 																		");
        query.append("        ,x.wrk_date wrkDate 																	");
        query.append("        ,(SELECT b.description FROM taemp a INNER JOIN tadept b ON a.dept_id = b.dept_id AND a.comp_no = b.comp_no WHERE a.comp_no = x.comp_no AND a.emp_id = x.wrk_id) wrkDeptDesc ");
        query.append("        ,(SELECT a.emp_name FROM taemp a WHERE a.comp_no = x.comp_no AND a.emp_id = x.wrk_id) wrkEmpDesc	");
        query.append("        ,x.rev_desc revDesc 																	");
        query.append("        ,x.revision_object_type revisionObjectType 											");
        query.append("        ,x.revisionhist_id revisionhistId 													");
        query.append("FROM TAREVISIONHIST x 																		");
        query.append("WHERE 1=1 																					");
        query.append(this.getWhere(commRevHistCommonDTO));
        query.append("ORDER BY ISNULL(x.rev_no, '0.0') DESC															");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: CommRevHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param commRevHistListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(CommRevHistCommonDTO commRevHistCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        if (!"".equals(commRevHistCommonDTO.getRevisionhistId()))
        {
            query.getAndQuery("x.revisionhist_id", commRevHistCommonDTO.getRevisionhistId());
            return query.toString();
        }
        
        query.getAndQuery("x.comp_no", commRevHistCommonDTO.getCompNo());
        query.getAndQuery("x.object_no", commRevHistCommonDTO.getObjectNo());
        query.getAndQuery("x.revision_object_type", commRevHistCommonDTO.getRevisionObjType());
        
        return query.toString();
    }
    
}