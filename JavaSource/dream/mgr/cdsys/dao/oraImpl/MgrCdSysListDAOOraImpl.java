package dream.mgr.cdsys.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.cdsys.dao.MgrCdSysListDAO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * 시스템코드 - 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="mgrCdSysListDAOTarget"
 * @spring.txbn id="mgrCdSysListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrCdSysListDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrCdSysListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCommonDTO
     * @return List
     */
    public List findListTypeList(MgrCdSysCommonDTO mgrCdSysCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                   					");
        query.append("       '' seqNo,							");
        query.append("       '' isDelCheck,						");
        query.append("       x.cdsysm_id,      					");
        query.append("       x.list_type,      					");
        //query.append("       x.description,    					");
        query.append("       (select aa.key_name                ");
        query.append("         from talang aa                   ");
        query.append("         where  lang = '"+mgrCdSysCommonDTO.getUserLang()+"'         ");
        query.append("             and x.key_type = aa.key_type ");
        query.append("             and x.key_no = aa.key_no) as description,   ");
        query.append("       x.remark         					");
        query.append("FROM   TACDSYSM x        					");
        query.append("WHERE  1=1               					");
        query.append(this.getWhere(mgrCdSysCommonDTO));
        
        query.getOrderByQuery("x.cdsysm_id","x.list_type", mgrCdSysCommonDTO.getOrderBy(), mgrCdSysCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrCdSysCommonDTO.getIsLoadMaxCount(), mgrCdSysCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MgrCdSysCommonDTO mgrCdSysCommonDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.is_system", "N");
        query.getAndQuery("x.is_use", "Y");
        
        if (!"".equals(mgrCdSysCommonDTO.getCdSysMId()))
        {
            query.getAndQuery("x.cdsysm_id", mgrCdSysCommonDTO.getCdSysMId());
            return query.toString();
        }
        query.getLikeQuery("x.list_type", mgrCdSysCommonDTO.getFilterListType());
        query.getLikeQuery("x.description", mgrCdSysCommonDTO.getFilterListTypeDesc());
        
        return query.toString();
    }

    @Override
    public String findTotalCount(MgrCdSysCommonDTO mgrCdSysCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                    ");
        query.append("       count(1)                           ");
        query.append("FROM   TACDSYSM x                         ");
        query.append("WHERE  1=1                                ");
        query.append(this.getWhere(mgrCdSysCommonDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}