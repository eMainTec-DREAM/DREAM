package dream.mgr.msgrec.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.msgrec.dao.MgrMsgRecListDAO;
import dream.mgr.msgrec.dto.MgrMsgRecCommonDTO;

/**
 * 메시지 수신설정 Page - List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrMsgRecListDAOTarget"
 * @spring.txbn id="mgrMsgRecListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrMsgRecListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements MgrMsgRecListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT									");
        query.append("      '' 						seqNo		");
        query.append("    , '' 						isDelCheck	");
        query.append("	  , x.msgcompset_id			msgCompSetId");
        query.append("    , x.message_object_type 	msgObjType	");
        query.append("    , dbo.SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"' ) msgObjTypeDesc		");
        query.append("    , dbo.SFACODE_TO_DESC(x.mail_use, 'IS_USE', 'SYS', x.comp_no, '"+user.getLangId()+"' )  isMailUse		");
        query.append("    , dbo.SFACODE_TO_DESC(x.sms_use, 'IS_USE', 'SYS', x.comp_no, '"+user.getLangId()+"' )   isSmsUse		");
        query.append("    , dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no, '"+user.getLangId()+"' )    isUse			");
        query.append("    , x.remark    			remark 		");
        query.append("FROM TAMSGCOMPSET x						");
        query.append("WHERE 1=1									");
        query.append(this.getWhere(mgrMsgRecCommonDTO, user));
        query.getOrderByQuery("x.msgcompset_id","x.message_object_type", mgrMsgRecCommonDTO.getOrderBy(), mgrMsgRecCommonDTO.getDirection());
        
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

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
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TAMSGCOMPSET x					");
        query.append("WHERE 1=1 							");
        query.append(this.getWhere(mgrMsgRecCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

	public int deleteList(String id, User user) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("DELETE FROM TAMSGCOMPSET 	");
        query.append("WHERE comp_no = ?         ");
        query.append("  AND msgcompset_id = ?   ");
        
        Object[] objects = new Object[] {   
	            user.getCompNo()
	            ,id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);		
	}    
}
