package dream.pers.priv.info.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.pers.priv.info.dao.LovMsgCompListDAO;
import dream.pers.priv.info.dto.LovMsgCompListDTO;

/**
 * 메시지타입 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovMsgCompListDAOTarget"
 * @spring.txbn id="lovMsgCompListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovMsgCompListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovMsgCompListDAO
{

	@Override
	public List findMsgCompAcList(LovMsgCompListDTO lovMsgCompListDTO, User user, Map<String, String> conditionMap)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SELECT 									");
		query.append("      '' 						seqNo		");
        query.append("	  , x.msgcompset_id			msgCompSetId");
        query.append("    , x.message_object_type 	msgObjType	");
        query.append("    , dbo.SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', '', '"+user.getLangId()+"' ) msgObjTypeDesc		");
        query.append("    , dbo.SFACODE_TO_DESC(x.mail_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )  isMailUseDesc		");
        query.append("    , dbo.SFACODE_TO_DESC(x.sms_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )   isSmsUseDesc			");
        query.append("    , dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )    isUseDesc			");
        query.append("    , x.remark    			remark 		");
        query.append("FROM TAMSGCOMPSET x						");
        query.append("WHERE  1=1								");
        query.getAndQuery("x.is_use", conditionMap);
        query.append(this.getWhere(lovMsgCompListDTO, user));	
        query.getOrderByQuery("x.msgcompset_id","x.message_object_type", lovMsgCompListDTO.getOrderBy(), lovMsgCompListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(lovMsgCompListDTO.getIsLoadMaxCount(), lovMsgCompListDTO.getFirstRow()));
	}
	
	private String getWhere(LovMsgCompListDTO lovMsgCompListDTO, User user)
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		//메시지전송유형
        query.getSysCdQuery("x.message_object_type", lovMsgCompListDTO.getFilterMsgObjType(), lovMsgCompListDTO.getFilterMsgObjTypeDesc(), "MESSAGE_OBJECT_TYPE", user.getCompNo(), user.getLangId());
        //mail사용여부
        query.getSysCdQuery("x.mail_use", lovMsgCompListDTO.getFilterIsMailUse(), lovMsgCompListDTO.getFilterIsMailUseDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        //sms사용여부
        query.getSysCdQuery("x.sms_use", lovMsgCompListDTO.getFilterIsSmsUse(), lovMsgCompListDTO.getFilterIsSmsUseDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        //사용여부
        query.getSysCdQuery("x.is_use", lovMsgCompListDTO.getFilterIsUse(), lovMsgCompListDTO.getFilterIsUseDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        
    	return query.toString();
    }

	@Override
	public String findTotalCount(LovMsgCompListDTO lovMsgCompListDTO, User user, Map<String, String> conditionMap) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("FROM TAMSGCOMPSET x		");
        query.append("WHERE  1=1				");
        query.getAndQuery("x.is_use", conditionMap);
    	query.append(this.getWhere(lovMsgCompListDTO, user));	
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}
}