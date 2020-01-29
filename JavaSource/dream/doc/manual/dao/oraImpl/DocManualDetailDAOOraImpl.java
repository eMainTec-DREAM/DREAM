package dream.doc.manual.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.doc.manual.dao.DocManualDetailDAO;
import dream.doc.manual.dto.DocManualDetailDTO;

/**
 * 사용자매뉴얼 - 상세 dao
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="docManualDetailDAOTarget"
 * @spring.txbn id="docManualDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class DocManualDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements DocManualDetailDAO
{
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public DocManualDetailDTO findDetail(String id, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                           ");
        query.append("    a.onlinehelp_id                                                 onlineHelpId ");
        query.append("    ,a.onlinehelp_id                                                onlineHelpNo ");
        query.append("    ,a.description                                                  title        ");
        query.append("    ,a.file_name                                                    fileName     ");
        query.append("    ,(SELECT page_id FROM TAPAGE WHERE file_name=a.file_name)       pageId       ");
        query.append("    ,(SELECT description FROM TAPAGE WHERE file_name=a.file_name)   pageDesc     ");
        query.append("    ,a.upd_date                                                     updDate      ");
        query.append("    ,(SELECT description FROM TADEPT                                             ");
        query.append("      WHERE dept_id = (SELECT dept_id FROM TAEMP                                 ");
        query.append("                       WHERE emp_id = a.emp_id                                   ");
        query.append("                       AND comp_no = a.comp_no ))                   updDept      ");
        query.append("    ,a.emp_name                                                     updBy        ");
        query.append("    ,a.contents                                                     contents     ");
        query.append("FROM TAONLINEHELP a                                                              ");
        query.append("WHERE  a.onlinehelp_id = '"+id+"'						                           ");
    
        DocManualDetailDTO docManualDetailDTO = 
        		(DocManualDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new DocManualDetailDTO()));
        
        return docManualDetailDTO;
    }
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param docManualDetailDTO
     * @return
     */
    public int insertDetail(DocManualDetailDTO docManualDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAONLINEHELP				");
    	query.append("	(onlinehelp_id,		description,	");
    	query.append("	 file_name,		    comp_no,		");
    	query.append("	 contents,          upd_date,       ");
    	query.append("   emp_id,            emp_no,         ");
    	query.append("   emp_name,          menu_id         ");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,             TO_CHAR(SYSDATE,'yyyymmdd'),");
    	query.append("   ?,             (SELECT emp_no FROM TAEMP WHERE emp_id = ? AND comp_no = ?),");
    	query.append("   ?,             ?                   ");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			docManualDetailDTO.getOnlineHelpId(),
    			docManualDetailDTO.getTitle(),
    			docManualDetailDTO.getFileName(),
    			user.getCompNo(),
    			docManualDetailDTO.getContents(),
    			user.getEmpId(),
    			user.getEmpId(),
    			user.getCompNo(),
    			user.getEmpName(),
    			docManualDetailDTO.getMenuId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param docManualDetailDTO
     * @return
     */
    public int updateDetail(DocManualDetailDTO docManualDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAONLINEHELP SET	");
    	query.append("	description	    = ?,	");
    	query.append("  menu_id         = ?,    ");
    	query.append("	file_name		= ?,	");
    	query.append("	contents	    = ?,	");
    	query.append("  upd_date        = TO_CHAR(SYSDATE,'yyyymmdd'),");
    	query.append("  emp_id          = ?,    ");
    	query.append("  emp_no          = (SELECT emp_no FROM TAEMP WHERE emp_id = ? AND comp_no = ?),");
    	query.append("  emp_name        = ?     ");
    	query.append("WHERE onlinehelp_id = ?	");
    	
    	Object[] objects = new Object[] {
    			docManualDetailDTO.getTitle(),
    			docManualDetailDTO.getMenuId(),
    			docManualDetailDTO.getFileName(),
    			docManualDetailDTO.getContents(),
    			user.getEmpId(),
    			user.getEmpId(),
    			user.getCompNo(),
    			user.getEmpName(),
    			docManualDetailDTO.getOnlineHelpId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}