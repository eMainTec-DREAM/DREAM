package dream.mgr.msgrec.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.msgrec.dao.MgrMsgRecListDAO;
import dream.mgr.msgrec.dto.MgrMsgRecCommonDTO;

/**
 * 메시지 수신설정 Page - List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="mgrMsgRecListDAOTarget"
 * @spring.txbn id="mgrMsgRecListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrMsgRecListDAOOraImpl  extends BaseJdbcDaoSupportOra implements MgrMsgRecListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrMsgRecCommonDTO
     * @return List
     */
    public List findList(MgrMsgRecCommonDTO mgrMsgRecCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT									");
        query.append("      '' 						seqNo		");
        query.append("    , '' 						isDelCheck	");
        query.append("	  , x.msgcompset_id			msgCompSetId");
        query.append("    , x.message_object_type 	msgObjType	");
        query.append("    , SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"' ) msgObjTypeDesc		");
        query.append("    , SFACODE_TO_DESC(x.mail_use, 'IS_USE', 'SYS', x.comp_no, '"+user.getLangId()+"' )  isMailUse		");
        query.append("    , SFACODE_TO_DESC(x.sms_use, 'IS_USE', 'SYS', x.comp_no, '"+user.getLangId()+"' )   isSmsUse		");
        query.append("    , SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no, '"+user.getLangId()+"' )    isUse			");
        query.append("    , x.remark    			remark 		");
        query.append("FROM TAMSGCOMPSET x						");
        query.append("WHERE 1=1									");
        query.append(this.getWhere(mgrMsgRecCommonDTO, user));
        query.getOrderByQuery("x.message_object_type", mgrMsgRecCommonDTO.getOrderBy(), mgrMsgRecCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrMsgRecCommonDTO.getIsLoadMaxCount(), mgrMsgRecCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrMsgRecCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MgrMsgRecCommonDTO mgrMsgRecCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        
        if(!"".equals(mgrMsgRecCommonDTO.getMsgCompSetId())){
            query.getAndQuery("x.msgcompset_id", mgrMsgRecCommonDTO.getMsgCompSetId());
            return query.toString();
        }
        
        //메시지전송유형
        query.getSysCdQuery("x.message_object_type", mgrMsgRecCommonDTO.getFilterMsgObjType(), mgrMsgRecCommonDTO.getFilterMsgObjTypeDesc(), "MESSAGE_OBJECT_TYPE", user.getCompNo(), user.getLangId());
        //mail사용여부
        query.getSysCdQuery("x.mail_use", mgrMsgRecCommonDTO.getFilterIsMailUse(), mgrMsgRecCommonDTO.getFilterIsMailUseDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        //sms사용여부
        query.getSysCdQuery("x.sms_use", mgrMsgRecCommonDTO.getFilterIsSmsUse(), mgrMsgRecCommonDTO.getFilterIsSmsUseDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        //사용여부
        query.getSysCdQuery("x.is_use", mgrMsgRecCommonDTO.getFilterIsUse(), mgrMsgRecCommonDTO.getFilterIsUseDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        
        return query.toString();
    }

    public String findTotalCount(MgrMsgRecCommonDTO mgrMsgRecCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TAMSGCOMPSET x					");
        query.append("WHERE 1=1 							");
        query.append(this.getWhere(mgrMsgRecCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

	public int deleteList(String id, User user) throws Exception 
	{
		QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAMSGCOMPSET x	");
        query.append("WHERE x.comp_no = ?         	");
        query.append("  AND x.msgcompset_id = ?     ");
        
        Object[] objects = new Object[] {   
	            user.getCompNo()
	            ,id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
	}    

}
