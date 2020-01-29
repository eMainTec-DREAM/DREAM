package dream.pers.priv.notice.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.notice.dao.PersPrivNoticeDetailDAO;
import dream.pers.priv.notice.dto.PersPrivNoticeDetailDTO;

/**
 * Notice 설정 - 상세 DaoImpl
 * @author  nhkim8548
 * @version $Id$
 * @since   1.0
 * @spring.bean id="persPrivNoticeDetailDAOTarget"
 * @spring.txbn id="persPrivNoticeDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PersPrivNoticeDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements PersPrivNoticeDetailDAO
{
    /**
     * detail find
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param maMyInfoCommonDTO
     * @param user 
     * @return
     */
    public PersPrivNoticeDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                            ");
        query.append("       x.initalarmempset_id                                               AS alarmEmpSetId        ");
        query.append("     , y.cdsysd_no                                                        AS alarmCode            ");
        query.append("     , y.description                                                      AS alarmName            ");
        query.append("     , x.is_notice                                                        AS isNotice             ");
        query.append("     , SFACODE_TO_DESC(x.is_notice, 'IS_USE', 'SYS', x.comp_no, ?)    AS isNoticeDesc             ");
        query.append("  FROM TAINITALARMEMPSET x INNER JOIN TACDSYSD y                                                  ");
        query.append("                              ON x.init_alarm_list = y.cdsysd_no                                  ");
        query.append("                             AND y.list_type = ?                                                  ");
        query.append(" WHERE 1 = 1                                                                                      ");
        query.append("   AND x.comp_no = ?                                                                              ");
        query.append("   AND x.emp_id = ?                                                                               ");
        query.append("   AND x.initalarmempset_id = ?                                                                   ");
        
        Object[] objects  = new Object[] {
                 user.getLangId()
               , "INIT_ALARM_LIST"
               , user.getCompNo()
               , user.getEmpId()
               , maMyInfoCommonDTO.getAlarmEmpSetId()
        };

        PersPrivNoticeDetailDTO persPrivNoticeDetailDTO = (PersPrivNoticeDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new PersPrivNoticeDetailDTO()));
        
        return persPrivNoticeDetailDTO;
    }
    
    /**
     * detail insert
     * @author nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivNoticeDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int insertDetail(PersPrivNoticeDetailDTO persPrivNoticeDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAINITALARMEMPSET (                                                                   ");
        query.append("      comp_no             , initalarmempset_id                , emp_id                            ");
        query.append("    , init_alarm_list     , is_notice                         , is_use                            ");
        query.append("    , remark                                                                                      ");
        query.append(" ) VALUES (                                                                                       ");
        query.append("      ?                   , ?                                 , ?                                 ");
        query.append("    , ?                   , ?                                 , ?                                 ");
        query.append("    , (SELECT description FROM TACDSYSD WHERE list_type = ? AND cdsysd_no = ? and is_use = ?)     ");
        query.append(" )                                                                                                ");

        Object[] objects = new Object[] {
                 user.getCompNo()
               , maMyInfoCommonDTO.getAlarmEmpSetId()
               , user.getEmpId()
               , persPrivNoticeDetailDTO.getAlarmCode()
               , persPrivNoticeDetailDTO.getIsNotice()
               , "Y"
               , "INIT_ALARM_LIST"
               , persPrivNoticeDetailDTO.getAlarmCode()
               , "Y"
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivNoticeDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int updateDetail(PersPrivNoticeDetailDTO persPrivNoticeDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAINITALARMEMPSET SET              ");
        query.append("       is_notice                  = ?     ");
        query.append(" WHERE comp_no                    = ?     ");
        query.append("   AND emp_id                     = ?     ");
        query.append("   AND initalarmempset_id         = ?     ");
        
        Object[] objects = new Object[] {
                 persPrivNoticeDetailDTO.getIsNotice()
               , user.getCompNo()
               , user.getEmpId()
               , persPrivNoticeDetailDTO.getAlarmEmpSetId()
       };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
}