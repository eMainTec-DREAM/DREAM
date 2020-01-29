package dream.mgr.msgrec.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.msgrec.dao.LovMsgCategListDAO;
import dream.mgr.msgrec.dto.LovMsgCategListDTO;

/**
 * 메시지타입 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovMsgCategListDAOTarget"
 * @spring.txbn id="lovMsgCategListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovMsgCategListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovMsgCategListDAO
{
	@Override
	public List findMsgCategAcList(LovMsgCategListDTO lovMsgCategListDTO, User user, Map<String, String> conditionMap)
	{
		QueryBuffer query = new QueryBuffer();

		query.append("SELECT 																									");
		query.append("      '' 						seqNo		");
        query.append("	  , x.messagecateg_id		msgCategId	");
        query.append("    , x.message_object_type 	msgObjType	");
        query.append("    , SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', '', '"+user.getLangId()+"' ) msgObjTypeDesc		");
        query.append("    , SFACODE_TO_DESC(x.mail_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )  isMailUseDesc		");
        query.append("    , SFACODE_TO_DESC(x.sms_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )   isSmsUseDesc		");
        query.append("    , SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )    isUseDesc			");
        query.append("    , x.remark    			remark 		");
        query.append("FROM TAMESSAGECATEG x						");
        query.append("WHERE  1=1								");
    	query.append(this.getWhere(lovMsgCategListDTO, user));	
        query.getOrderByQuery("x.message_object_type", lovMsgCategListDTO.getOrderBy(), lovMsgCategListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(lovMsgCategListDTO.getIsLoadMaxCount(), lovMsgCategListDTO.getFirstRow()));
	}
	
	private String getWhere(LovMsgCategListDTO lovMsgCategListDTO, User user)
    {
		QueryBuffer query = new QueryBuffer();
		
		//메시지전송유형
        query.getSysCdQuery("x.message_object_type", lovMsgCategListDTO.getFilterMsgObjType(), lovMsgCategListDTO.getFilterMsgObjTypeDesc(), "MESSAGE_OBJECT_TYPE", user.getCompNo(), user.getLangId());
        //mail사용여부
        query.getSysCdQuery("x.mail_use", lovMsgCategListDTO.getFilterIsMailUse(), lovMsgCategListDTO.getFilterIsMailUseDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        //sms사용여부
        query.getSysCdQuery("x.sms_use", lovMsgCategListDTO.getFilterIsSmsUse(), lovMsgCategListDTO.getFilterIsSmsUseDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        //사용여부
        query.getSysCdQuery("x.is_use", lovMsgCategListDTO.getFilterIsUse(), lovMsgCategListDTO.getFilterIsUseDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        
    	return query.toString();
    }

	@Override
	public String findTotalCount(LovMsgCategListDTO lovMsgCategListDTO, User user, Map<String, String> conditionMap) throws Exception
	{
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("FROM TAMESSAGECATEG x		");
        query.append("WHERE  1=1				");
    	query.append(this.getWhere(lovMsgCategListDTO, user));	
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}
	
}