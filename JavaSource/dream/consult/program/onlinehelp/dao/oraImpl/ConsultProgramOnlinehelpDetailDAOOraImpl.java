package dream.consult.program.onlinehelp.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.program.onlinehelp.dao.ConsultProgramOnlinehelpDetailDAO;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpDetailDTO;

/**
 * 도움말 - 상세 dao
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="consultProgramOnlinehelpDetailDAOTarget"
 * @spring.txbn id="consultProgramOnlinehelpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultProgramOnlinehelpDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultProgramOnlinehelpDetailDAO
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
    public ConsultProgramOnlinehelpDetailDTO findDetail(String id, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("    a.onlinehelp_id     onlineHelpId      ");
        query.append("    ,a.description      title     ");
        query.append("    ,a.file_name         fileName        ");
        query.append("    ,a.comp_no          compNo        ");
        query.append("    ,(SELECT page_id FROM TAPAGE WHERE file_name=a.file_name)       pageId        ");
        query.append("    ,(SELECT description FROM TAPAGE WHERE file_name=a.file_name)   pageDesc      ");
        query.append("    ,a.upd_date                                updDate     ");
        query.append("    ,a.emp_name                              updBy     ");
        query.append("    ,a.upd_date||' / '||a.emp_name     updDateBy     ");
        query.append("    ,a.contents                                contents      ");
        query.append("FROM TAONLINEHELP a       ");
        query.append("WHERE  a.onlinehelp_id = '"+id+"'						");
    
        ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO = 
        		(ConsultProgramOnlinehelpDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ConsultProgramOnlinehelpDetailDTO()));
        
        return consultProgramOnlinehelpDetailDTO;
    }
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpDetailDTO
     * @return
     */
    public int insertDetail(ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAONLINEHELP					");
    	query.append("	(onlinehelp_id,		description,		");
    	query.append("	 file_name,		    comp_no,		");
    	query.append("	 contents,          upd_date,       ");
    	query.append("     emp_name                             ");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,             TO_CHAR(SYSDATE,'yyyymmdd'),");
    	query.append("     ?                                 ");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			consultProgramOnlinehelpDetailDTO.getOnlineHelpId(),
    			consultProgramOnlinehelpDetailDTO.getTitle(),
    			consultProgramOnlinehelpDetailDTO.getFileName(),
    			consultProgramOnlinehelpDetailDTO.getCompNo(),
    			consultProgramOnlinehelpDetailDTO.getContents(),
    			consultProgramOnlinehelpDetailDTO.getUpdBy()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpDetailDTO
     * @return
     */
    public int updateDetail(ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAONLINEHELP SET		");
    	query.append("	description	    = ?,	");
    	query.append("	file_name		= ?,	");
    	query.append("	contents	        = ?,	");
    	query.append("    upd_date       = TO_CHAR(SYSDATE,'yyyymmdd'),   ");
    	query.append("    emp_name      = ?    ");
    	query.append("WHERE onlinehelp_id = ?		");
    	
    	Object[] objects = new Object[] {
    			consultProgramOnlinehelpDetailDTO.getTitle(),
    			consultProgramOnlinehelpDetailDTO.getFileName(),
    			consultProgramOnlinehelpDetailDTO.getContents(),
    			consultProgramOnlinehelpDetailDTO.getUpdBy(),
    			consultProgramOnlinehelpDetailDTO.getOnlineHelpId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}