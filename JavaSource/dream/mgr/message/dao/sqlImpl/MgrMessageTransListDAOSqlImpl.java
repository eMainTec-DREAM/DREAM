package dream.mgr.message.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.mgr.message.dao.MgrMessageTransListDAO;
import dream.mgr.message.dto.MgrMessageTransCommonDTO;

/**
 * Message Transfer Page - List DAO implements
 * @author syyang
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrMessageTransListDAOTarget"
 * @spring.txbn id="mgrMessageTransListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrMessageTransListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrMessageTransListDAO
{
	public List findList(MgrMessageTransCommonDTO mgrMessageTransCommonDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT                                                                                ");
        query.append("		''															AS seqNo			");
        query.append("		,''														   	AS isDelCheck		");
        query.append("		,x.messagelist_id									    	AS messageId		");
//        query.append("		,x.send_time												AS sendTime			");
        query.append("		,SUBSTRING(x.send_time, 1, 4)+'-'+SUBSTRING(x.send_time, 5, 2)+'-'+SUBSTRING(x.send_time, 7, 2)		");
        query.append("		 +' '+SUBSTRING(x.send_time, 9,2)+':'+SUBSTRING(x.send_time, 11,2)+':'+SUBSTRING(x.send_time, 13,2) AS sendTime	");
        query.append("		,x.method_type											    AS methodTypeId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.method_type,'METHOD_TYPE','SYS','','"+user.getLangId()+"')	AS methodTypeDesc			");
        query.append("		,x.description											    AS description		");
        query.append("		,x.receivers												AS receivers		");
        query.append("		,x.message_status											AS messageStatusId	");
        query.append("		,dbo.SFACODE_TO_DESC(x.message_status,'MESSAGE_STATUS','SYS','','"+user.getLangId()+"')	AS messageStatusDesc	");
        query.append("		,x.retry_cnt												AS retryCnt			");
//        query.append("		,x.cre_time													AS creTime			");
        query.append("		,SUBSTRING(x.cre_time, 1, 4)+'-'+SUBSTRING(x.cre_time, 5, 2)+'-'+SUBSTRING(x.cre_time, 7, 2)		");
        query.append("		 +' '+SUBSTRING(x.cre_time, 9,2)+':'+SUBSTRING(x.cre_time, 11,2)+':'+SUBSTRING(x.cre_time, 13,2) AS creTime			");
        query.append("FROM TAMESSAGELIST x																	");
    	query.append("WHERE  1=1																		   	");
    	query.append(this.getWhere(mgrMessageTransCommonDTO, user));
        query.getOrderByQuery("x.messagelist_id","x.messagelist_id desc", mgrMessageTransCommonDTO.getOrderBy(), mgrMessageTransCommonDTO.getDirection());
        
        
    	return getJdbcTemplate().queryForList(query.toString(mgrMessageTransCommonDTO.getIsLoadMaxCount(), mgrMessageTransCommonDTO.getFirstRow()));
    } 

	private String getWhere(MgrMessageTransCommonDTO mgrMessageTransCommonDTO, User user)
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();

        String fromDate = CommonUtil.dateToData(mgrMessageTransCommonDTO.getFilterStartDate());
        String toDate   = CommonUtil.dateToData(mgrMessageTransCommonDTO.getFilterEndDate());
        String creStartDate = CommonUtil.dateToData(mgrMessageTransCommonDTO.getFilterCreStartDate());
        String creEndDate   = CommonUtil.dateToData(mgrMessageTransCommonDTO.getFilterCreEndDate());
        // 전송일자는 시분초까지 있기때문에 비교를 위해 EndDate를 +1일 한다
        if(null != toDate && ""!=toDate)
        	toDate = DateUtil.getAfterDays(toDate, 1);
        // 생성일자는 시분초까지 있기때문에 비교를 위해 EndDate를 +1일 한다
        if(null != creEndDate && ""!=creEndDate)
        	creEndDate = DateUtil.getAfterDays(creEndDate, 1);
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        //전송구분
        query.getSysCdQuery("x.method_type", mgrMessageTransCommonDTO.getFilterMethodTypeID(), mgrMessageTransCommonDTO.getFilterMethodTypeDesc(), "METHOD_TYPE", user.getCompNo(), user.getLang());
        //수신자
        query.getLikeQuery("x.receivers", mgrMessageTransCommonDTO.getFilterReceiversDesc());
        
        //전송일자
        query.getAndDateQuery("x.send_time", fromDate, toDate);
        //전송상태
        query.getSysCdQuery("x.message_status", mgrMessageTransCommonDTO.getFilterMsgStatusID(), mgrMessageTransCommonDTO.getFilterMsgStatusDesc(), "MESSAGE_STATUS", user.getCompNo(), user.getLang());

        //생성일자
        query.getAndDateQuery("x.cre_time", creStartDate, creEndDate);
        //제목
        query.getLikeQuery("x.description", mgrMessageTransCommonDTO.getFilterDescription());

    	return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("DELETE FROM TAMESSAGELIST		");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  messagelist_id	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(MgrMessageTransCommonDTO mgrMessageTransCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAMESSAGELIST x										");
    	query.append("WHERE  1=1												");
    	query.append(this.getWhere(mgrMessageTransCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
}