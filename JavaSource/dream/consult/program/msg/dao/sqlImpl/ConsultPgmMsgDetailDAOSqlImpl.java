package dream.consult.program.msg.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.program.msg.dao.ConsultPgmMsgDetailDAO;
import dream.consult.program.msg.dto.ConsultPgmMsgCommonDTO;
import dream.consult.program.msg.dto.ConsultPgmMsgDetailDTO;

/**
 * 메시지 설정(메일, SMS) - 상세 dao
 *
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="consultPgmMsgDetailDAOTarget"
 * @spring.txbn id="consultPgmMsgDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmMsgDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultPgmMsgDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgCommonDTO
     * @param user
     * @return
     */
    public ConsultPgmMsgDetailDTO findDetail(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT        								");
        query.append("	    x.messagecateg_id		msgCategId		");
        query.append("    , x.message_object_type 	msgObjType		");
        query.append("    , dbo.SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', '', ? ) msgObjTypeDesc		");
        query.append("    , dbo.SFACODE_TO_DESC(x.mail_use, 'IS_USE', 'SYS', '', ? )  isMailUseDesc		");
        query.append("    , dbo.SFACODE_TO_DESC(x.sms_use, 'IS_USE', 'SYS', '', ? )   isSmsUseDesc		");
        query.append("    , dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '', ? )    isUseDesc			");
        query.append("    , x.message_object_type   msgObjType		");
        query.append("    , x.mail_use    			isMailUse		");
        query.append("    , x.sms_use        		isSmsUse		");
        query.append("    , x.is_use        		isUse			");
        query.append("    , x.remark    			remark 			");
        query.append("FROM TAMESSAGECATEG x							");
        query.append("WHERE 1=1										");
        query.append("  AND x.messagecateg_id = ?					");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getLangId()
        		,user.getLangId()
        		,user.getLangId()
                ,consultPgmMsgCommonDTO.getMsgCategId()
        };
        
        ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO =
        		(ConsultPgmMsgDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new ConsultPgmMsgDetailDTO()));

        return consultPgmMsgDetailDTO;
    }
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgDetailDTO
     * @param user
     * @return
     */
    public int insertDetail(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAMESSAGECATEG ( 									");
    	query.append(" 		messagecateg_id    , message_object_type	, description ");
    	query.append("	  , mail_use  		   , sms_use			 	, is_use  	");
    	query.append("    , remark													");
    	query.append(" ) VALUES (													");
    	query.append(" 		?                  , ?						, ?			");
    	query.append(" 	  , ?         		   , ?                		, ?			");
    	query.append(" 	  , ?         												");
    	query.append(" )															");

    	Object[] objects = new Object[] {
    			 consultPgmMsgDetailDTO.getMsgCategId()
    			, consultPgmMsgDetailDTO.getMsgObjType()
    			, consultPgmMsgDetailDTO.getMsgObjTypeDesc()
    			, consultPgmMsgDetailDTO.getIsMailUse()
    			, consultPgmMsgDetailDTO.getIsSmsUse()
    			, consultPgmMsgDetailDTO.getIsUse()
    			, consultPgmMsgDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAMESSAGECATEG SET             ");
        query.append("   is_use                 = ?         ");
        query.append("  ,description 			= ? 		");
        query.append("  ,sms_use                = ?         ");
        query.append("  ,mail_use               = ?         ");
        query.append("  ,remark                 = ?         ");
        query.append("WHERE  messagecateg_id    = ?         ");
        
        Object[] objects = new Object[] {
        		consultPgmMsgDetailDTO.getIsUse()
        		,consultPgmMsgDetailDTO.getMsgObjTypeDesc()
        		,consultPgmMsgDetailDTO.getIsSmsUse()
        		,consultPgmMsgDetailDTO.getIsMailUse()
        		,consultPgmMsgDetailDTO.getRemark()
                ,consultPgmMsgDetailDTO.getMsgCategId()
        };
        
    	return getJdbcTemplate().update(query.toString(), objects);
    }

	public int updateLang(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user) throws Exception 
	{
        QuerySqlBuffer query = new QuerySqlBuffer();

        int rtnValue  = 0;

		query.append("UPDATE TALANG SET 	");
		query.append("    key_name 	= ? 	");
		query.append("WHERE lang 	= ?		");
		query.append("AND key_type 	= ? 	");
		query.append("AND key_no 	= ?		");


        Object[] objects = new Object[] {
        		consultPgmMsgDetailDTO.getMsgObjTypeDesc()
               ,user.getLangId()
               ,"CODESET"
               ,"MESSAGE_OBJECT_TYPE."+consultPgmMsgDetailDTO.getMsgObjType()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
	}

	public String validMsgObjType(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO, User user) {
		
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT COUNT(*)					");
        query.append("FROM TAMESSAGECATEG x				");
        query.append("WHERE 1=1							");
        query.append("AND x.message_object_type = ?		");
        
        Object[] objects = new Object[] {
        		consultPgmMsgDetailDTO.getMsgObjType()
        };
        
		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
	}
}