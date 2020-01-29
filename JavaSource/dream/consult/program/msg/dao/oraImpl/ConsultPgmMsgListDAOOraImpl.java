package dream.consult.program.msg.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.program.msg.dao.ConsultPgmMsgListDAO;
import dream.consult.program.msg.dto.ConsultPgmMsgCommonDTO;

/**
 * 메시지 설정(메일, SMS) - 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="consultPgmMsgListDAOTarget"
 * @spring.txbn id="consultPgmMsgListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmMsgListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultPgmMsgListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgCommonDTO
     * @return List
     */
    public List findList(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT									");
        query.append("      '' 						seqNo		");
        query.append("    , '' 						isDelCheck	");
        query.append("	  , x.messagecateg_id		msgCategId	");
        query.append("    , x.message_object_type 	msgObjType	");
        query.append("    , SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', '', '"+user.getLangId()+"' ) msgObjTypeDesc		");
        query.append("    , SFACODE_TO_DESC(x.mail_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )  isMailUseDesc		");
        query.append("    , SFACODE_TO_DESC(x.sms_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )   isSmsUseDesc		");
        query.append("    , SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"' )    isUseDesc			");
        query.append("    , x.remark    			remark 		");
        query.append("FROM TAMESSAGECATEG x						");
        query.append("WHERE 1=1									");
        query.append(this.getWhere(consultPgmMsgCommonDTO , user));
        query.getOrderByQuery("x.message_object_type", consultPgmMsgCommonDTO.getOrderBy(), consultPgmMsgCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultPgmMsgCommonDTO.getIsLoadMaxCount(), consultPgmMsgCommonDTO.getFirstRow()));
    }
    
    public String findTotalCount(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO, User user){
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)			");
        query.append("FROM 	TAMESSAGECATEG x	");
        query.append("WHERE 1=1					");
        query.append(this.getWhere(consultPgmMsgCommonDTO , user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        if(!"".equals(consultPgmMsgCommonDTO.getMsgCategId())){
            query.getAndQuery("x.messagecateg_id", consultPgmMsgCommonDTO.getMsgCategId());
            return query.toString();
        }
        
        //메시지전송유형
        query.getSysCdQuery("x.message_object_type", consultPgmMsgCommonDTO.getFilterMsgObjType(), consultPgmMsgCommonDTO.getFilterMsgObjTypeDesc(), "MESSAGE_OBJECT_TYPE", user.getCompNo(), user.getLangId());
        //mail사용여부
        query.getSysCdQuery("x.mail_use", consultPgmMsgCommonDTO.getFilterIsMailUse(), consultPgmMsgCommonDTO.getFilterIsMailUseDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        //sms사용여부
        query.getSysCdQuery("x.sms_use", consultPgmMsgCommonDTO.getFilterIsSmsUse(), consultPgmMsgCommonDTO.getFilterIsSmsUseDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        //사용여부
        query.getSysCdQuery("x.is_use", consultPgmMsgCommonDTO.getFilterIsUse(), consultPgmMsgCommonDTO.getFilterIsUseDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        
        return query.toString();
    }

    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteList(String id, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAMESSAGECATEG		");
    	query.append("WHERE messagecateg_id = "+id+"	");

    	return this.getJdbcTemplate().update(query.toString());
    }
}