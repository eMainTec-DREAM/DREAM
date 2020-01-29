package dream.consult.comp.terminal.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.comp.terminal.dao.ConsultCompTerminalExDetailDAO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExCommonDTO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExDetailDTO;

/**
 * 접근터미널 - 상세 dao
 *
 * @author kim21017
 * @version $Id: ConsultCompTerminalExDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="consultCompTerminalExDetailDAOTarget"
 * @spring.txbn id="consultCompTerminalExDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompTerminalExDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompTerminalExDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompTerminalExDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExCommonDTO
     * @param user
     * @return
     */
    public ConsultCompTerminalExDetailDTO findDetail(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT														");
        query.append("       x.comp_no					compNo						");
        query.append("       ,(SELECT a.description									");
        query.append("       	FROM TACOMP a										");
        query.append("       	WHERE 1=1											");
        query.append("       	AND a.comp_no = x.comp_no) compNoDesc				");
        query.append("       ,x.accessmnlex_id			accessMnlExId				");
        query.append("       ,x.service_type			serviceTypeId				");
        query.append("		 ,dbo.SFACODE_TO_DESC(x.service_type,'SERVICE_TYPE','SYS'");
        query.append("		 			,'','"+user.getLangId()+"') serviceTypeDesc	");
        query.append("		 ,x.terminal_no				terminalNo					");
        query.append("		 ,x.description				description					");
        query.append("		 ,x.is_use					isUseId						");
        query.append("		 ,dbo.SFACODE_TO_DESC(x.is_use,'IS_USE','SYS'			");
        query.append("		 			,'','"+user.getLangId()+"') isUseDesc		");
        query.append("		 ,x.remark					remark						");
       query.append("FROM   TAACCESSMNLEX x											");
        query.append("WHERE  x.comp_no = '"+consultCompTerminalExCommonDTO.getCompNo()+"'	");
        query.append("    and x.accessmnlex_id = "+consultCompTerminalExCommonDTO.getAccessMnlExId()+"	");

        ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO =
        		(ConsultCompTerminalExDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ConsultCompTerminalExDetailDTO()));

        return consultCompTerminalExDetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompTerminalExDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExDetailDTO
     * @param user
     * @return
     */
    public int insertDetail(ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAACCESSMNLEX								");
    	query.append("	(comp_no,		accessmnlex_id, 	service_type,	");
    	query.append("	 terminal_no,	description, 	is_use,				");
    	query.append("	 remark												");
    	query.append("	)	VALUES											");
    	query.append("	(?,				?,				?,					");
    	query.append("	 ?,				?,				?,					");
    	query.append("	 ?													");
    	query.append("	)													");

    	Object[] objects = new Object[] {
    			consultCompTerminalExDetailDTO.getCompNo()
    			,consultCompTerminalExDetailDTO.getAccessMnlExId()
    			,consultCompTerminalExDetailDTO.getServiceTypeId()
    			,consultCompTerminalExDetailDTO.getTerminalNo()
    			,consultCompTerminalExDetailDTO.getDescription()
    			,consultCompTerminalExDetailDTO.getIsUseId()
    			,consultCompTerminalExDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompTerminalExDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAACCESSMNLEX SET	");
    	query.append("	service_type	= ? 	");
    	query.append("	,terminal_no	= ? 	");
    	query.append("	,description	= ? 	");
    	query.append("	,is_use			= ? 	");
    	query.append("	,remark			= ? 	");
    	query.append("	,comp_no		= ? 	");
    	query.append("WHERE 1=1					");
    	query.append("  AND accessmnlex_id = ?	");

    	Object[] objects = new Object[] {
    			consultCompTerminalExDetailDTO.getServiceTypeId()
    			,consultCompTerminalExDetailDTO.getTerminalNo()
    			,consultCompTerminalExDetailDTO.getDescription()
    			,consultCompTerminalExDetailDTO.getIsUseId()
    			,consultCompTerminalExDetailDTO.getRemark()
    			,consultCompTerminalExDetailDTO.getCompNo()
    			,consultCompTerminalExDetailDTO.getAccessMnlExId()
    	};

    	return getJdbcTemplate().update(query.toString(), objects);
    }
}