package dream.mgr.msgrec.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.msgrec.dao.MgrMsgRecDetailDAO;
import dream.mgr.msgrec.dto.MgrMsgRecCommonDTO;
import dream.mgr.msgrec.dto.MgrMsgRecDetailDTO;

/**
 * MgrMsgRec Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrMsgRecDetailDAOTarget"
 * @spring.txbn id="mgrMsgRecDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrMsgRecDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrMsgRecDetailDAO
{

    public MgrMsgRecDetailDTO findDetail(MgrMsgRecCommonDTO mgrMsgRecCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        								");
        query.append("	    x.msgcompset_id			msgCompSetId	");
        query.append("    , x.message_object_type 	msgObjType		");
        query.append("    , SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', x.comp_no, ? ) msgObjTypeDesc		");
        query.append("    , SFACODE_TO_DESC(x.mail_use, 'IS_USE', 'SYS', x.comp_no, ? )  isMailUseDesc		");
        query.append("    , SFACODE_TO_DESC(x.sms_use, 'IS_USE', 'SYS', x.comp_no, ? )   isSmsUseDesc		");
        query.append("    , SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no, ? )    isUseDesc			");
        query.append("    , x.message_object_type   msgObjType		");
        query.append("    , x.mail_use    			isMailUse		");
        query.append("    , x.sms_use        		isSmsUse		");
        query.append("    , x.is_use        		isUse			");
        query.append("    , x.remark    			remark 			");
        query.append("FROM TAMSGCOMPSET x							");
        query.append("WHERE 1=1										");
        query.append("  AND x.comp_no = ? 							");
        query.append("  AND x.msgcompset_id = ?						");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getLangId()
        		,user.getLangId()
        		,user.getLangId()
                ,user.getCompNo()
                ,mgrMsgRecCommonDTO.getMsgCompSetId()
        };
    
        MgrMsgRecDetailDTO mgrMsgRecDetailDTO = 
                (MgrMsgRecDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MgrMsgRecDetailDTO()));
        
        return mgrMsgRecDetailDTO;
        
    }

	public int updateDetail(MgrMsgRecDetailDTO mgrMsgRecDetailDTO, User user) throws Exception 
	{
		QueryBuffer query = new QueryBuffer();
		
		int rtnValue  = 0;

        query.append("UPDATE TAMSGCOMPSET SET               ");
        query.append("   is_use                 = ?         ");
        query.append("  ,sms_use                = ?         ");
        query.append("  ,mail_use               = ?         ");
        query.append("  ,remark                 = ?         ");
        query.append("WHERE  comp_no            = ?         ");
        query.append("  AND  msgcompset_id      = ?         ");
        
        Object[] objects = new Object[] {
        		mgrMsgRecDetailDTO.getIsUse()
        		,mgrMsgRecDetailDTO.getIsSmsUse()
        		,mgrMsgRecDetailDTO.getIsMailUse()
        		,mgrMsgRecDetailDTO.getRemark()
                ,user.getCompNo()
                ,mgrMsgRecDetailDTO.getMsgCompSetId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
	}

	public int updateLang(MgrMsgRecDetailDTO mgrMsgRecDetailDTO, User user) throws Exception 
	{
        QueryBuffer query = new QueryBuffer();
        
		int rtnValue  = 0;

		query.append("UPDATE TALANG SET 	");
		query.append("    key_name 	= ? 	");
		query.append("WHERE lang 	= ?		");
		query.append("AND key_type 	= ? 	");
		query.append("AND key_no 	= ?		");


        Object[] objects = new Object[] {
        		mgrMsgRecDetailDTO.getMsgObjTypeDesc()
               ,user.getLangId()
               ,"CODESET"
               ,"MESSAGE_OBJECT_TYPE."+mgrMsgRecDetailDTO.getMsgObjType()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
	}

	public String validMsgObjType(MgrMsgRecDetailDTO mgrMsgRecDetailDTO, User user) throws Exception 
	{
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT COUNT(*)					");
        query.append("FROM TAMSGCOMPSET x				");
        query.append("WHERE 1=1							");
        query.append("AND x.comp_no = ?					");
        query.append("AND x.message_object_type = ?		");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,mgrMsgRecDetailDTO.getMsgObjType()
        };
        
		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
	}

	public int insertDetail(MgrMsgRecDetailDTO mgrMsgRecDetailDTO, User user) throws Exception 
	{
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAMSGCOMPSET ( 										");
    	query.append(" 		comp_no			, msgcompset_id		, message_object_type	");
    	query.append("	  , mail_use  		, sms_use			, is_use  				");
    	query.append("    , remark														");
    	query.append(" ) VALUES (														");
    	query.append(" 		?         		, ?					, ?						");
    	query.append(" 	  , ?         		, ?                	, ?						");
    	query.append(" 	  , ?         													");
    	query.append(" )																");

    	Object[] objects = new Object[] {
    			user.getCompNo()
    			, mgrMsgRecDetailDTO.getMsgCompSetId()
    			, mgrMsgRecDetailDTO.getMsgObjType()
    			, mgrMsgRecDetailDTO.getIsMailUse()
    			, mgrMsgRecDetailDTO.getIsSmsUse()
    			, mgrMsgRecDetailDTO.getIsUse()
    			, mgrMsgRecDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
	}
}
