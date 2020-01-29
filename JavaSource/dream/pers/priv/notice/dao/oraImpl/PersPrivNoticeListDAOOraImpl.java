package dream.pers.priv.notice.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.notice.dao.PersPrivNoticeListDAO;

/**
 * Notice 설정 - 목록 DaoImpl
 * @author  nhkim8548
 * @version $Id$
 * @since   1.0
 * @spring.bean id="persPrivNoticeListDAOTarget"
 * @spring.txbn id="persPrivNoticeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PersPrivNoticeListDAOOraImpl extends BaseJdbcDaoSupportOra implements PersPrivNoticeListDAO
{
    /**
     * grid find
     * @author  nhkim8548
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
        
        query.append("SELECT                                                                                                                                    ");
        query.append("       ''                                                                                                             AS seqNo            ");
        query.append("     , y.initalarmempset_id                                                                                           AS alarmEmpSetId    ");
        query.append("     , x.cdsysd_no                                                                                                    AS alarmCode        ");
        query.append("     , SFACODE_TO_DESC(x.cdsysd_no, 'INIT_ALARM_LIST', 'SYS', '"+ user.getCompNo() +"', '"+ user.getLangId() +"')     AS alarmName        ");
        query.append("     , NVL(y.is_notice, 'N')                                                                                          AS isNotice         ");
        query.append("     , NVL(SFACODE_TO_DESC(y.is_notice, 'IS_USE', 'SYS', '"+ user.getCompNo() +"', '"+ user.getLangId() +"'), 'N')    AS isNoticeDesc     ");
        query.append("  FROM tacdsysd x LEFT OUTER JOIN TAINITALARMEMPSET y                                                                                     ");
        query.append("                    ON x.cdsysd_no = y.init_alarm_list                                                                                    ");
        query.append("                   AND y.comp_no = '"+ user.getCompNo() +"'                                                                               ");
        query.append("                   AND y.emp_id = '"+ user.getEmpId() +"'                                                                                 ");
        query.append(" WHERE 1 = 1                                                                                                                              ");
        query.append(this.getWhere(maMyInfoCommonDTO, user));
        query.getOrderByQuery("y.initalarmempset_id", maMyInfoCommonDTO.getOrderBy(), maMyInfoCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maMyInfoCommonDTO.getIsLoadMaxCount(), maMyInfoCommonDTO.getFirstRow()));
    }
    
    private String getWhere(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	// 시스템 코드 분류
        query.getAndQuery("x.list_type", "INIT_ALARM_LIST");
        
        // 시스템 코드 사용 여부
        query.getAndQuery("x.is_use", "Y");
        
        if (!"".equals(maMyInfoCommonDTO.getAlarmEmpSetId()))
        {
            query.getAndQuery("y.initalarmempset_id", maMyInfoCommonDTO.getAlarmEmpSetId());
            return query.toString();
        }
                
        return query.toString();
    }

	public String findTotalCount(MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception 
	{
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT                                                            ");
        query.append("       COUNT(1)                                                   ");
        query.append("  FROM tacdsysd x LEFT OUTER JOIN TAINITALARMEMPSET y             ");
        query.append("                    ON x.cdsysd_no = y.init_alarm_list            ");
        query.append("                   AND y.comp_no = '"+ user.getCompNo() +"'       ");
        query.append("                   AND y.emp_id = '"+ user.getEmpId() +"'         ");
        query.append(" WHERE 1 = 1                                                      ");
        query.append(this.getWhere(maMyInfoCommonDTO,user));
    	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
}