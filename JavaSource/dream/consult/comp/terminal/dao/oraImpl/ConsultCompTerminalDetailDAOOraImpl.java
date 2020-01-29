package dream.consult.comp.terminal.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.comp.terminal.dao.ConsultCompTerminalDetailDAO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalCommonDTO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalDetailDTO;

/**
 * 접근터미널 - 상세 dao
 *
 * @author kim21017
 * @version $Id: ConsultCompTerminalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="consultCompTerminalDetailDAOTarget"
 * @spring.txbn id="consultCompTerminalDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompTerminalDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompTerminalDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompTerminalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalCommonDTO
     * @param user
     * @return
     */
    public ConsultCompTerminalDetailDTO findDetail(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT														");
        query.append("       x.comp_no					compNo						");
        query.append("       ,(SELECT a.description									");
        query.append("       	FROM TACOMP a										");
        query.append("       	WHERE 1=1											");
        query.append("       	AND a.comp_no = x.comp_no) compNoDesc				");
        query.append("       ,x.accessmnl_id			accessMnlId					");
        query.append("       ,x.service_type			serviceTypeId				");
        query.append("		 ,SFACODE_TO_DESC(x.service_type,'SERVICE_TYPE','SYS'	");
        query.append("		 			,'','"+user.getLangId()+"') serviceTypeDesc	");
        query.append("		 ,x.terminal_no				terminalNo					");
        query.append("		 ,x.description				description					");
        query.append("		 ,x.is_use					isUseId						");
        query.append("		 ,SFACODE_TO_DESC(x.is_use,'IS_USE','SYS'				");
        query.append("		 			,'','"+user.getLangId()+"') isUseDesc		");
        query.append("		 ,x.remark					remark						");
       query.append("FROM   TAACCESSMNL x											");
        query.append("WHERE  x.comp_no = '"+consultCompTerminalCommonDTO.getCompNo()+"'	");
        query.append("    and x.accessmnl_id = "+consultCompTerminalCommonDTO.getAccessMnlId()+"	");

        ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO =
        		(ConsultCompTerminalDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ConsultCompTerminalDetailDTO()));

        return consultCompTerminalDetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompTerminalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalDetailDTO
     * @param user
     * @return
     */
    public int insertDetail(ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAACCESSMNL								");
    	query.append("	(comp_no,		accessmnl_id, 	service_type,		");
    	query.append("	 terminal_no,	description, 	is_use,				");
    	query.append("	 remark												");
    	query.append("	)	VALUES											");
    	query.append("	(?,				?,				?,					");
    	query.append("	 ?,				?,				?,					");
    	query.append("	 ?													");
    	query.append("	)													");

    	Object[] objects = new Object[] {
    			consultCompTerminalDetailDTO.getCompNo()
    			,consultCompTerminalDetailDTO.getAccessMnlId()
    			,consultCompTerminalDetailDTO.getServiceTypeId()
    			,consultCompTerminalDetailDTO.getTerminalNo()
    			,consultCompTerminalDetailDTO.getDescription()
    			,consultCompTerminalDetailDTO.getIsUseId()
    			,consultCompTerminalDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompTerminalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAACCESSMNL SET	");
    	query.append("	service_type	= ? 	");
    	query.append("	,terminal_no	= ? 	");
    	query.append("	,description	= ? 	");
    	query.append("	,is_use			= ? 	");
    	query.append("	,remark			= ? 	");
    	query.append("WHERE 1=1					");
    	query.append("  AND accessmnl_id = ?	");
    	query.append("  AND comp_no = ?	        ");

    	Object[] objects = new Object[] {
    			consultCompTerminalDetailDTO.getServiceTypeId()
    			,consultCompTerminalDetailDTO.getTerminalNo()
    			,consultCompTerminalDetailDTO.getDescription()
    			,consultCompTerminalDetailDTO.getIsUseId()
    			,consultCompTerminalDetailDTO.getRemark()
    			,consultCompTerminalDetailDTO.getAccessMnlId()
    			,consultCompTerminalDetailDTO.getCompNo()
    	};

    	return getJdbcTemplate().update(query.toString(), objects);
    }
}