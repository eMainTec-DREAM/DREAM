package dream.part.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.part.list.dao.PartListImgLinkUrlDAO;
import dream.part.list.dto.PartListImgLinkUrlDTO;

/**
 * ºÎÇ°Image Link DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partListImgLinkUrlDAOTarget"
 * @spring.txbn id="partListImgLinkUrlDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartListImgLinkUrlDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartListImgLinkUrlDAO
{
    public List find(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception
    { 
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT                                                                                                                                                ");
        query.append("    ''                                                                                                                    AS seqNo                    ");
        query.append("    ,''                                                                                                                   AS ISDELCHECK               ");
        query.append("    ,a.ptimgurl_id                                                                                                        AS ptImgUrlId               ");
        query.append("    ,a.part_id                                                                                                            AS partId                   ");
        query.append("    ,a.img_url                                                                                                            AS imgUrl                   ");
        query.append("    ,a.is_received                                                                                                        AS isReceived               ");
        query.append("    ,dbo.SFACODE_TO_DESC(a.is_received,'IS_USE','SYS','','"+user.getLangId()+"')                                          AS isReceivedDesc           ");
        query.append("    ,a.received_time                                                                                                      AS receivedTime             ");
        query.append("    ,a.image_receive_status                                                                                               AS imageReceiveStatus       ");
        query.append("    ,dbo.SFACODE_TO_DESC(a.image_receive_status,'IMAGE_RECEIVE_STATUS','SYS','','"+user.getLangId()+"')                   AS imageReceiveStatusDesc   ");
        query.append("    ,a.cre_date                                                                                                           AS creDate                  ");
        query.append("    ,a.cre_by                                                                                                             AS creBy                    ");
        query.append("    ,(SELECT user_name FROM TAUSER                                                                                                                    ");
        query.append("      WHERE comp_no=a.comp_no AND user_id=a.cre_by)                                                                       AS creByDesc                ");
        query.append("    ,a.upd_date                                                                                                           AS updDate                  ");
        query.append("    ,a.upd_by                                                                                                             AS updBy                    ");
        query.append("    ,(SELECT user_name FROM TAUSER                                                                                                                    ");
        query.append("      WHERE comp_no=a.comp_no AND user_id=a.upd_by)                                                                       AS updByDesc                ");
        query.append("FROM TAPTIMGURL a                                                                                                                                     ");
        query.append("WHERE 1 = 1                                                                                                                                           ");
        query.append(this.getWhere(partListImgLinkUrlDTO, user));
        query.getOrderByQuery("a.ptimgurl_id","a.ptimgurl_id", partListImgLinkUrlDTO.getOrderBy(), partListImgLinkUrlDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(partListImgLinkUrlDTO.getIsLoadMaxCount(), partListImgLinkUrlDTO.getFirstRow()));
    } 

	private String getWhere(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user)
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("AND a.part_id IS NOT NULL");
		
		query.getStringEqualQuery("a.comp_no", user.getCompNo());
        
        if(!"".equals(partListImgLinkUrlDTO.getPtImgUrlId())){
            query.getAndQuery("a.ptimgurl_id", partListImgLinkUrlDTO.getPtImgUrlId());
            return query.toString();
        }
        
        query.getAndQuery("a.part_id", partListImgLinkUrlDTO.getPartId());
        
        query.getSysCdQuery("a.is_received", partListImgLinkUrlDTO.getIsReceived(), partListImgLinkUrlDTO.getIsReceivedDesc(), "IS_USE", user.getCompNo(), user.getLangId());
        
        query.getSysCdQuery("a.image_receive_status", partListImgLinkUrlDTO.getImageReceiveStatus(), partListImgLinkUrlDTO.getImageReceiveStatusDesc(), "IMAGE_RECEIVE_STATUS", user.getCompNo(), user.getLangId());
        
        return query.toString();
    } 

    public int delete(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAPTIMGURL            ");
        query.append("WHERE  comp_no            = ?     ");
        query.append("AND ptimgurl_id           = ?     ");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT                                   ");
        query.append("       COUNT(1)                           ");
        query.append("FROM TAPTIMGURL a                         ");
        query.append("WHERE 1=1                                 ");
        query.append(this.getWhere(partListImgLinkUrlDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
    
    public int insert(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;

        query.append("INSERT INTO TAPTIMGURL(                                   ");
        query.append("   comp_no        ,ptimgurl_id    ,part_id                ");
        query.append("  ,img_url        ,is_received    ,received_time          ");
        query.append("  ,image_receive_status ,cre_date ,cre_by                 ");
        query.append("  ,upd_date       ,upd_by                                 ");
        query.append(")                                                         ");
        query.append("VALUES(                                                   ");
        query.append("   ?              ,?              ,?                      ");
        query.append("  ,?              ,?              ,?                      ");
        query.append("  ,?              ,?              ,?                      ");
        query.append("  ,?              ,?                                      ");
        query.append(")                                                         ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
                 ,partListImgLinkUrlDTO.getPtImgUrlId()
                 ,partListImgLinkUrlDTO.getPartId()
                 ,partListImgLinkUrlDTO.getImgUrl()
                 ,partListImgLinkUrlDTO.getIsReceived()
                 ,CommonUtil.getRowDateToNum(partListImgLinkUrlDTO.getReceivedTime())
                 ,partListImgLinkUrlDTO.getImageReceiveStatus()
                 ,CommonUtil.getRowDateToNum(partListImgLinkUrlDTO.getCreDate())
                 ,partListImgLinkUrlDTO.getCreBy()
                 ,CommonUtil.getRowDateToNum(partListImgLinkUrlDTO.getUpdDate())
                 ,partListImgLinkUrlDTO.getUpdBy()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
        
        return rtnValue;
    }
    
    
    
    public int update(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        int rtnValue  = 0;

        query.append("UPDATE TAPTIMGURL SET             ");
        query.append("    part_id                = ?    ");
        query.append("    ,img_url               = ?    ");
        query.append("    ,is_received           = ?    ");
        query.append("    ,received_time         = ?    ");
        query.append("    ,image_receive_status  = ?    ");
        query.append("    ,upd_date              = ?    ");
        query.append("    ,upd_by                = ?    ");
        query.append("WHERE comp_no              = ?    ");
        query.append(" AND ptimgurl_id           = ?    ");
        
        Object[] objects = new Object[] {
                partListImgLinkUrlDTO.getPartId()
                ,partListImgLinkUrlDTO.getImgUrl()
                ,partListImgLinkUrlDTO.getIsReceived()
                ,CommonUtil.getRowDateToNum(partListImgLinkUrlDTO.getReceivedTime())
                ,partListImgLinkUrlDTO.getImageReceiveStatus()
                ,CommonUtil.getRowDateToNum(partListImgLinkUrlDTO.getUpdDate())
                ,partListImgLinkUrlDTO.getUpdBy()
                ,user.getCompNo()
                ,partListImgLinkUrlDTO.getPtImgUrlId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
        
        return rtnValue;
    }
}