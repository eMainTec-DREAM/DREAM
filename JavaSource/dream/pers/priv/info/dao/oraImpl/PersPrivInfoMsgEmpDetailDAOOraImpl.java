package dream.pers.priv.info.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.pers.priv.info.dao.PersPrivInfoMsgEmpDetailDAO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.PersPrivInfoMsgEmpDetailDTO;


/**
 * »ó¼¼ dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="persPrivInfoMsgEmpDetailDAOTarget"
 * @spring.txbn id="persPrivInfoMsgEmpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PersPrivInfoMsgEmpDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements PersPrivInfoMsgEmpDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     */
    public PersPrivInfoMsgEmpDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
        
    	query.append("SELECT                                    ");
    	query.append("      x.msgempset_id  		msgEmpSetId	");
        query.append("    , x.message_object_type 	msgObjType	");
        query.append("	  , x.is_use 				isUse		");
        query.append("	  , x.mail_use 				isMailUse	");
        query.append("	  , x.sms_use 				isSmsUse	");
        query.append("    , SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', x.comp_no, ?) msgObjTypeDesc		");
        query.append("    , SFACODE_TO_DESC(x.mail_use, 'IS_USE', 'SYS', x.comp_no, ?) isMailUseDesc		");
        query.append("    , SFACODE_TO_DESC(x.sms_use, 'IS_USE', 'SYS', x.comp_no, ?) isSmsUseDesc		");
        query.append("    , x.remark 				remark		");
        query.append("    , x.emp_id				empId		");
        query.append("FROM TAMSGEMPSET x						");
        query.append("WHERE  1=1							    ");
        query.append("AND x.comp_no = ?							");
        query.append("AND x.emp_id = ?							");
        query.append("AND x.msgempset_id = ?					");
        
        Object[] objects  = new Object[] {
                user.getLangId()
                , user.getLangId()
                , user.getLangId()
                , user.getCompNo()
                , user.getEmpId()
                , maMyInfoCommonDTO.getMsgEmpSetId()
        };

        PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO = 
        		(PersPrivInfoMsgEmpDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new PersPrivInfoMsgEmpDetailDTO()));
        
        return persPrivInfoMsgEmpDetailDTO;
    }
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivInfoMsgEmpDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int updateDetail(PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAMSGEMPSET SET            ");
        query.append("   is_use                 = ?     ");
        query.append("  ,sms_use                = ?     ");
        query.append("  ,mail_use               = ?     ");
        query.append("  ,remark                 = ?     ");
        query.append("WHERE comp_no            	= ?     ");
        query.append("  AND emp_id 				= ?		");
        query.append("  AND msgempset_id 		= ?		");
        
        Object[] objects = new Object[] {
        		persPrivInfoMsgEmpDetailDTO.getIsUse()
        		, persPrivInfoMsgEmpDetailDTO.getIsSmsUse()
        		, persPrivInfoMsgEmpDetailDTO.getIsMailUse()
        		, persPrivInfoMsgEmpDetailDTO.getRemark()
        		, user.getCompNo()
        		, user.getEmpId()
        		, maMyInfoCommonDTO.getMsgEmpSetId()
        };
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivInfoMsgEmpDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int insertDetail(PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAMSGEMPSET ( 										");
    	query.append(" 		comp_no			, msgempset_id		, message_object_type	");
    	query.append("	  , mail_use  		, sms_use			, is_use  				");
    	query.append("    , remark			, emp_id									");
    	query.append(" ) VALUES (														");
    	query.append(" 		?         		, ?					, ?						");
    	query.append(" 	  , ?         		, ?                	, ?						");
    	query.append(" 	  , ?         		, ?											");
    	query.append(" )																");

    	Object[] objects = new Object[] {
    			user.getCompNo()
    			, persPrivInfoMsgEmpDetailDTO.getMsgEmpSetId()
    			, persPrivInfoMsgEmpDetailDTO.getMsgObjType()
    			, persPrivInfoMsgEmpDetailDTO.getIsMailUse()
    			, persPrivInfoMsgEmpDetailDTO.getIsSmsUse()
    			, persPrivInfoMsgEmpDetailDTO.getIsUse()
    			, persPrivInfoMsgEmpDetailDTO.getRemark()
    			, user.getEmpId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }

    public String validMsgObjType(PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO, User user) throws Exception 
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT COUNT(*)					");
        query.append("FROM TAMSGEMPSET x				");
        query.append("WHERE 1=1							");
        query.append("AND x.comp_no = ?					");
        query.append("AND x.emp_id = ?					");
        query.append("AND x.message_object_type = ?		");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
        		, user.getEmpId()
        		, persPrivInfoMsgEmpDetailDTO.getMsgObjType()
        };
        
		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));

	}
}