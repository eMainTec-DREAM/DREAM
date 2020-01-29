package dream.mgr.intf.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.mgr.intf.dao.MgrIntfParamsDAO;
import dream.mgr.intf.dto.IntfUserExecVO;
import dream.mgr.intf.dto.IntfUserExecValueVO;
import dream.mgr.intf.dto.MgrIntfCommonDTO;

/**
 * 인터페이스 파라미터 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="mgrIntfParamsDAOTarget"
 * @spring.txbn id="mgrIntfParamsDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrIntfParamsDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrIntfParamsDAO
{
    public List findList(MgrIntfCommonDTO mgrIntfCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                            ");
        query.append("    ''                                        AS seqNo            ");
        query.append("    ,a.intfmap_id                             AS intfMapId        ");
        query.append("    ,a.param_seq                              AS paramSeq         ");
        query.append("    ,a.s_table_name                           AS srcTableName     ");
        query.append("    ,a.s_field_name                           AS srcFieldName     ");
        query.append("    ,a.s_is_pk                                AS srcIsPk          ");
        query.append("    ,a.s_field_type                           AS srcFieldType     ");
        query.append("    ,a.s_field_size                           AS srcFieldSize     ");
        query.append("    ,a.s_is_not_null                          AS srcIsNotNull     ");
        query.append("    ,ISNULL(c.s_field_value,a.s_default_size) AS srcFieldValue    ");
        query.append("    ,a.s_remark                               AS srcRemark        ");
        query.append("FROM TAINTFMAP a LEFT OUTER JOIN TAINTFUSEREXEC b                 ");
        query.append("ON a.comp_no = b.comp_no                                          ");
        query.append("AND a.intf_id = b.intf_id                                         ");
        query.append("AND b.exe_by = ?                                                  ");
        query.append("LEFT OUTER JOIN TAINTFUSEREXECVALUE c                             ");
        query.append("ON b.comp_no = c.comp_no                                          ");
        query.append("AND b.intfuserexec_id = c.intfuserexec_id                         ");
        query.append("AND a.intfmap_id = c.intfmap_id                                   ");
        query.append("WHERE 1=1                                                         ");
        query.append("AND a.comp_no = ?                                                 ");
        query.append("AND a.intf_id = ?                                                 ");
        query.append("AND a.intf_param_type = 'INPUT'                                   ");
        query.append("ORDER BY a.param_seq                                              ");
        
        Object[] objects = new Object[] {
                user.getUserId()
                ,user.getCompNo()
                ,mgrIntfCommonDTO.getIntfId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects);
    }
    
    @Override
    public IntfUserExecVO selectTAINTFUSEREXEC(MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                    ");
        query.append("    comp_no           AS compNo           ");
        query.append("    ,intfuserexec_id  AS intfUserExecId   ");
        query.append("    ,intf_id          AS intfId           ");
        query.append("    ,exe_by           AS exeBy            ");
        query.append("FROM TAINTFUSEREXEC                       ");
        query.append("WHERE comp_no = ?                         ");
        query.append("AND intf_id   = ?                         ");
        query.append("AND exe_by    = ?                         ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,mgrIntfCommonDTO.getIntfId()
                ,user.getUserId()
        };
        
        return (IntfUserExecVO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new IntfUserExecVO()));
    }

    @Override
    public int insertTAINTFUSEREXEC(IntfUserExecVO intfUserExecVO) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAINTFUSEREXEC (                  ");
        query.append("  comp_no     ,intfuserexec_id    ,intf_id    ");
        query.append("  ,exe_by                                     ");
        query.append(") VALUES (                                    ");
        query.append("  ?           ,?                  ,?          ");
        query.append("  ,?                                          ");
        query.append(")                                             ");
        
        Object[] objects = new Object[] {
                intfUserExecVO.getCompNo()
                ,intfUserExecVO.getIntfUserExecId()
                ,intfUserExecVO.getIntfId()
                ,intfUserExecVO.getExeBy()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    @Override
    public IntfUserExecValueVO selectTAINTFUSEREXECVALUE(IntfUserExecValueVO intfUserExecValueVO) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("    comp_no               AS compNo               ");
        query.append("    ,intfuserexecvalue_id AS intfUserExecValueId  ");
        query.append("    ,intfuserexec_id      AS intfUserExecId       ");
        query.append("    ,intf_id              AS intfId               ");
        query.append("    ,intfmap_id           AS intfMapId            ");
        query.append("    ,s_field_value        AS srcFieldValue        ");
        query.append("FROM TAINTFUSEREXECVALUE                          ");
        query.append("WHERE 1 = 1                                       ");
        query.getStringEqualQuery("comp_no", intfUserExecValueVO.getCompNo());
        query.getAndQuery("intfuserexecvalue_id", intfUserExecValueVO.getIntfUserExecValueId());
        query.getAndQuery("intfuserexec_id", intfUserExecValueVO.getIntfUserExecId());
        query.getAndQuery("intf_id", intfUserExecValueVO.getIntfId());
        query.getAndQuery("intfmap_id", intfUserExecValueVO.getIntfMapId());
        
        return (IntfUserExecValueVO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new IntfUserExecValueVO()));
    }

    @Override
    public int insertTAINTFUSEREXECVALUE(IntfUserExecValueVO intfUserExecValueVO) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAINTFUSEREXECVALUE (                         ");
        query.append("  comp_no     ,intfuserexecvalue_id   ,intfuserexec_id    ");
        query.append("  ,intf_id    ,intfmap_id             ,s_field_value      ");
        query.append(") VALUES (                                                ");
        query.append("  ?           ,?                      ,?                  ");
        query.append("  ,?          ,?                      ,?                  ");
        query.append(")                                                         ");
        
        Object[] objects = new Object[] {
                intfUserExecValueVO.getCompNo()
                ,intfUserExecValueVO.getIntfUserExecValueId()
                ,intfUserExecValueVO.getIntfUserExecId()
                ,intfUserExecValueVO.getIntfId()
                ,intfUserExecValueVO.getIntfMapId()
                ,intfUserExecValueVO.getSrcFieldValue()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    @Override
    public int updateTAINTFUSEREXECVALUE(IntfUserExecValueVO intfUserExecValueVO) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAINTFUSEREXECVALUE SET    ");
        query.append("    s_field_value = ?             ");
        query.append("WHERE 1 = 1                       ");
        query.getStringEqualQuery("comp_no", intfUserExecValueVO.getCompNo());
        query.getAndQuery("intfuserexecvalue_id", intfUserExecValueVO.getIntfUserExecValueId());
        query.getAndQuery("intfuserexec_id", intfUserExecValueVO.getIntfUserExecId());
        query.getAndQuery("intf_id", intfUserExecValueVO.getIntfId());
        query.getAndQuery("intfmap_id", intfUserExecValueVO.getIntfMapId());
        
        Object[] objects = new Object[] {
                intfUserExecValueVO.getSrcFieldValue()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
}