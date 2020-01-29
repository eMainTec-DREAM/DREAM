package dream.mgr.rpt.cp.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.rpt.cp.dao.LovMgrRptCpDAO;
import dream.mgr.rpt.cp.dto.LovMgrRptCpDTO;
import dream.mgr.rpt.cp.dto.LovMgrRptCpLogDTO;

/**
 * 출력물 선택
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovMgrRptCpDAOTarget"
 * @spring.txbn id="lovMgrRptCpDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovMgrRptCpDAOOraImpl extends BaseJdbcDaoSupportOra implements LovMgrRptCpDAO
{
    @Override
    public List findList(LovMgrRptCpDTO lovMgrRptCpDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                                        ");
        query.append("    ''                                                                                                    AS SEQNO            ");
        query.append("    ,a.rptcpfile_id                                                                                       AS RPTCPFILEID      ");
        query.append("    ,a.rptcplist_id                                                                                       AS RPTCPLISTID      ");
        query.append("    ,a.rptlist_no                                                                                         AS RPTLISTNO        ");
        query.append("    ,a.rptlist_name                                                                                       AS RPTLISTNAME      ");
        query.append("    ,a.rptfile_type                                                                                       AS RPTFILETYPE      ");
        query.append("    ,(SELECT SFACODE_TO_DESC(a.rptfile_type,'RPTFILE_TYPE','SYS','','"+user.getLangId()+"') FROM DUAL)    AS RPTFILETYPEDESC  ");
        query.append("    ,NVL(a.svr_addr, c.param1)                                                                            AS SVRADDR          ");
        query.append("    ,a.design_file                                                                                        AS DESIGNFILE       ");
        query.append("    ,a.query_file                                                                                         AS QUERYFILE        ");
        query.append("    ,a.reversion_no                                                                                       AS REVERSIONNO      ");
        query.append("    ,a.use_from                                                                                           AS USEFROM          ");
        query.append("    ,a.use_to                                                                                             AS USETO            ");
        query.append("    ,a.is_use                                                                                             AS ISUSE            ");
        query.append("    ,(SELECT SFACODE_TO_DESC(a.is_use,'IS_USE','SYS','','"+user.getLangId()+"') FROM DUAL)                AS ISUSEDESC        ");
        query.append("    ,a.tl_txt                                                                                             AS TLTXT            ");
        query.append("    ,a.tc_txt                                                                                             AS TCTXT            ");
        query.append("    ,a.tr_txt                                                                                             AS TRTXT            ");
        query.append("    ,a.bl_txt                                                                                             AS BLTXT            ");
        query.append("    ,a.bc_txt                                                                                             AS BCTXT            ");
        query.append("    ,a.br_txt                                                                                             AS BRTXT            ");
        query.append("    ,a.remark                                                                                             AS REMARK           ");
        query.append("FROM TARPTCPFILE a INNER JOIN TARPTCPLIST b                                                                                   ");
        query.append("ON a.comp_no = b.comp_no AND a.rptcplist_id = b.rptcplist_id                                                                  ");
        query.append("LEFT OUTER JOIN TACDSYSD c                                                                                                    ");
        query.append("ON c.list_type = 'RPTFILE_TYPE' AND a.rptfile_type = c.cdsysd_no                                                              ");
        query.append("WHERE 1=1															                                                            ");
        query.append(this.getWhere(lovMgrRptCpDTO, user));
        query.getOrderByQuery("a.reversion_no", lovMgrRptCpDTO.getOrderBy(), lovMgrRptCpDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovMgrRptCpDTO.getIsLoadMaxCount(), lovMgrRptCpDTO.getFirstRow()));
    }
    
    private String getWhere(LovMgrRptCpDTO lovMgrRptCpDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        
        if(!"".equals(lovMgrRptCpDTO.getRptCpFileId())){
            query.getAndQuery("a.rptcpfile_id", lovMgrRptCpDTO.getRptCpFileId());
            return query.toString();
        }
        
        query.getAndQuery("b.rptlist_no", lovMgrRptCpDTO.getRptListNo());
        
        query.getAndQuery("a.is_use", lovMgrRptCpDTO.getIsUse());
        query.getAndQuery("b.is_use", lovMgrRptCpDTO.getIsUse());
        query.getAndQuery("c.is_use", lovMgrRptCpDTO.getIsUse());
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(LovMgrRptCpDTO lovMgrRptCpDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                        ");
        query.append("       COUNT(1)                                               ");
        query.append("FROM TARPTCPFILE a INNER JOIN TARPTCPLIST b                   ");
        query.append("ON a.comp_no = b.comp_no AND a.rptcplist_id = b.rptcplist_id  ");
        query.append("LEFT OUTER JOIN TACDSYSD c                                    ");
        query.append("ON c.list_type = 'RPTFILE_TYPE' AND a.rptfile_type = c.cdsysd_no  ");
        query.append("WHERE  1=1                                                    ");
        query.append(this.getWhere(lovMgrRptCpDTO, user));
        query.append("ORDER BY 1                                                    ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

    @Override
    public String[][] executeQuery(String query) throws Exception
    {
        List resultList = getJdbcTemplate().queryForList(query);
        return QueryBuffer.toStringArray(resultList);
    }

    @Override
    public int log(LovMgrRptCpLogDTO lovMgrRptCpLogDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO TARPTCPFILEACCLOG(                                                        ");
        query.append("    comp_no           , rptcpfileacclog_id    , rptcpfile_id          , rptcplist_id  ");
        query.append("  , user_no           , user_name             , exe_date              , exe_time      ");
        query.append("  , terminal_no       , param_value                                                   ");
        query.append(")VALUES(                                                                              ");
        query.append("    ?                 , ?                     , ?                     , ?             ");
        query.append("  , ?                 , ?                     , ?                     , ?             ");
        query.append("  , ?                 , ?                                                             ");
        query.append(")                                                                                     ");
        
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,lovMgrRptCpLogDTO.getRptCpFileAccLogId()
                ,lovMgrRptCpLogDTO.getRptCpFileId()
                ,lovMgrRptCpLogDTO.getRptCpListId()
                ,user.getUserId()
                ,user.getUserName()
                ,lovMgrRptCpLogDTO.getExeDate()
                ,lovMgrRptCpLogDTO.getExeTime()
                ,lovMgrRptCpLogDTO.getTerminalNo()
                ,lovMgrRptCpLogDTO.getParamValue()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
}
