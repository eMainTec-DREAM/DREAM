package dream.work.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.WorkListPointDetailDAO;
import dream.work.list.dto.WorkListPointDetailDTO;
import dream.work.list.dto.WorkListPointListDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;

/**
 * ���˳��� - Detail DAO implements
 * @author youngjoo38
 * @version $Id: WorkListPointDetailDAOSqlImpl.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListPointDetailDAOTarget"
 * @spring.txbn id="workListPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListPointDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkListPointDetailDAO
{
	
    public WorkListPointDetailDTO findDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, WorkListPointListDTO workListPointListDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                                                        ");
        query.append("       ''                                                     AS seqNo                        ");
        query.append("     , ''                                                     AS isDelCheck                   ");
        query.append("     , pmptrlsched_id                                         AS pmPtrlSchedId                "); //-- �����׸� ID
        query.append("     , check_by                                               AS ptrlInspectorId              "); //-- ������ ID
        query.append("     , (SELECT description                                                                    ");
        query.append("        FROM TAPMEQUIP a                                                                      ");
        query.append("        WHERE a.pmequip_id = x.pmequip_id)                    AS ptrlAreaDesc                 "); //-- ��ȸ����
        query.append("     , (SELECT emp_name                                                                       ");
        query.append("        FROM TAEMP a                                                                          ");
        query.append("        WHERE a.emp_id = x.check_by)                          AS ptrlInspectorDesc            "); //-- ������Desc
        query.append("     , (SELECT end_date                                                                       ");
        query.append("        FROM TAPMPTRLRSLTLIST a                                                               ");
        query.append("        WHERE a.pmptrlrsltlist_id = x.pmptrlrsltlist_id)      As ptrlComDate                  ");
        query.append("     , (SELECT end_time                                                                       ");
        query.append("        FROM TAPMPTRLRSLTLIST a                                                               ");
        query.append("        WHERE a.pmptrlrsltlist_id = x.pmptrlrsltlist_id)      AS ptrlComTime                  "); 
        query.append("     , (SELECT end_date                                                                       ");
        query.append("        FROM TAPMPTRLRSLTLIST a                                                               ");
        query.append("        WHERE a.pmptrlrsltlist_id = x.pmptrlrsltlist_id)                                      ");
        query.append("        || ' ' ||                                                                             ");
        query.append("       (SELECT end_time                                                                       ");
        query.append("        FROM TAPMPTRLRSLTLIST a                                                               ");
        query.append("        WHERE a.pmptrlrsltlist_id = x.pmptrlrsltlist_id)      AS ptrlComDateTime              "); //-- ���˽ð�(��¥+�ð�)
        query.append("     , pm_point_id                                            AS pmPointId                    "); //-- �����׸� ID
        query.append("     , (SELECT check_point                                                                    ");
        query.append("        FROM TAPMPOINT a                                                                      ");
        query.append("        WHERE a.pm_point_id = x.pm_point_id)                  AS pmPointDesc                  "); //-- �����׸�Desc(���˳���)
        query.append("     , (SELECT step_num                                                                       ");
        query.append("        FROM TAPMPOINT a                                                                      ");
        query.append("        WHERE a.pm_point_id = x.pm_point_id)                  AS stepNum                      "); //-- ���˼���
        query.append("     , pm_point_rslt_status                                   AS pmPointRsltStatusId          "); //--�۾�������ȹ����Id (���˰��)
        query.append("     , SFACODE_TO_DESC( x.pm_point_rslt_status, 'PM_POINT_RSLT_STATUS','SYS',x.comp_no, ?)    AS pmPointRsltStatusDesc  --�۾�������ȹ����Desc (���˰��)      ");
        query.append("     , pmsched_status                                         AS pmSchedStatus                "); //-- �۾�������ȹ����(Planned, Completed...etc)
        query.append("     , description                                            AS description                  "); //-- ���˳���??
        query.append("     , eqctg_id                                                                               ");
        query.append("     , equip_id                                                                               ");
        query.append("     , pmequip_id                                             AS ptrlAreaId                   ");
        query.append("     , pmptrlrsltlist_id                                      AS pmptrlrsltlistId             ");
        query.append("     , pmptrlrsltpoint_id                                     AS pmPtrlRsltPointId            ");
        query.append("     , remark                                                 AS remark                       ");
        query.append("FROM TAPMPTRLRSLTPOINT x                                                                      ");
        query.append("WHERE  1=1                                                        ");
        query.append("AND  x.pmPtrlRsltPoint_Id  = ?                                     ");
        query.append("AND  x.comp_no            = ?                                     ");
        
        Object[] objects = new Object[] {
                user.getLangId()
              , workListPointListDTO.getPmPtrlRsltPointId()
              , user.getCompNo()
        };
    
        WorkListPointDetailDTO workListPointDetailDTO = 
                (WorkListPointDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkListPointDetailDTO()));
        
        return workListPointDetailDTO;
        
        
    }

    public int insertDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,WorkListPointDetailDTO workListPointDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAPMPTRLRSLTPOINT (        ");
        query.append("  comp_no                        ");
        query.append(", pmptrlrsltpoint_id             ");
        query.append(", pmptrlrsltlist_id              ");
        query.append(", pmequip_id                     "); 
        query.append(", pm_point_id                    "); 
        query.append(", pm_point_rslt_status           "); 
        query.append(", check_by                       "); 
        query.append(", REMARK                         "); 
        query.append(") VALUES (                       ");
        query.append("?                                ");
        query.append(", ?                              ");
        query.append(", ?                              ");
        query.append(", ?                              ");
        query.append(", ?                              ");
        query.append(", ?                              ");
        query.append(", ?                              ");
        query.append(", ?                              ");
        query.append(")                                ");

        Object[] objects = new Object[] {
                 user.getCompNo()
               , workListPointDetailDTO.getPmPtrlRsltPointId()
               , workListPointDetailDTO.getPmPtrlRsltListId()
               , workListPointDetailDTO.getPtrlAreaId()               
               , workListPointDetailDTO.getPmPointId()
               , workListPointDetailDTO.getPmPointRsltStatusId()
               , workListPointDetailDTO.getPtrlInspectorId()
               , workListPointDetailDTO.getRemark()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public int updateDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,WorkListPointDetailDTO workListPointDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;

        query.append("UPDATE TAPMPTRLRSLTPOINT SET              ");
        query.append("   check_by               = ?             ");
        query.append("  ,pm_point_rslt_status   = ?             ");
        query.append("  ,remark                 = ?             ");
        query.append("WHERE  comp_no            = ?             ");
        query.append("  AND  pmPtrlRsltPoint_Id = ?             ");
        
        Object[] objects = new Object[] {
                workListPointDetailDTO.getPtrlInspectorId()
              , workListPointDetailDTO.getPmPointRsltStatusId()
              , workListPointDetailDTO.getRemark()
              , user.getCompNo()
              , workListPointDetailDTO.getPmPtrlRsltPointId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
}