package dream.pers.priv.info.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.pers.priv.info.dao.PersPrivInfoMsgEmpListDAO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 *  ¸ñ·Ï dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="persPrivInfoMsgEmpListDAOTarget"
 * @spring.txbn id="persPrivInfoMsgEmpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PersPrivInfoMsgEmpListDAOOraImpl extends BaseJdbcDaoSupportOra implements PersPrivInfoMsgEmpListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param maMyInfoCommonDTO
     * @param user 
     * @return List
     */
    public List findList(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 									");
        query.append("    	'' 						seqNo		");
        query.append("    , '' 						isDelCheck	");
        query.append("    , x.msgempset_id  		msgEmpSetId	");
        query.append("    , x.message_object_type 	msgObjType	");
        query.append("    , SFACODE_TO_DESC(x.message_object_type, 'MESSAGE_OBJECT_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"') msgObjTypeDesc		");
        query.append("    , SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no, '"+user.getLangId()+"') isUseDesc		");
        query.append("    , SFACODE_TO_DESC(x.mail_use, 'IS_USE', 'SYS', x.comp_no, '"+user.getLangId()+"') isMailUseDesc		");
        query.append("    , SFACODE_TO_DESC(x.sms_use, 'IS_USE', 'SYS', x.comp_no, '"+user.getLangId()+"') isSmsUseDesc		");
        query.append("    , x.mail_use 				isMailUse	");
        query.append("    , x.sms_use 				isSmsUse	");
        query.append("    , x.is_use 				isUse		");
        query.append("    , x.remark 				remark		");
        query.append("FROM TAMSGEMPSET x						");
        query.append("WHERE  1=1							    ");
        query.append(this.getWhere(maMyInfoCommonDTO,user));
        query.getOrderByQuery("x.message_object_type", maMyInfoCommonDTO.getOrderBy(), maMyInfoCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maMyInfoCommonDTO.getIsLoadMaxCount(), maMyInfoCommonDTO.getFirstRow()));
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
    	
    	query.append("DELETE FROM TAMSGEMPSET			    ");
    	query.append("WHERE  msgempset_id 	= '"+id+"'	    ");
    	query.append("AND comp_no = '"+user.getCompNo()+"'  ");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.emp_id", user.getEmpId());
    	
    	if (!"".equals(maMyInfoCommonDTO.getMsgEmpSetId()))
        {
            query.getAndQuery("x.msgempset_id", maMyInfoCommonDTO.getMsgEmpSetId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	public String findTotalCount(MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception 
	{
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT                ");
        query.append("       COUNT(1)       ");
        query.append("FROM   TAMSGEMPSET x	");
        query.append("WHERE  1=1            ");
    	query.append(this.getWhere(maMyInfoCommonDTO,user));
    	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}