package dream.consult.comp.cdsys.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.comp.cdsys.dao.MaCdSysListDAO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드 - 목록 dao
 * @author  kim21017
 * @version $Id: MaCdSysListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maCdSysListDAOTarget"
 * @spring.txbn id="maCdSysListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaCdSysListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaCdSysListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaCdSysListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCommonDTO
     * @return List
     */
    public List findListTypeList(MaCdSysCommonDTO maCdSysCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                   					");
        query.append("       '' seqNo,							");
        query.append("       '' isDelCheck,						");
        query.append("       x.cdsysm_id,      					");
        query.append("       x.list_type,      					");
        //query.append("       x.description,    					");
        query.append("       (select aa.key_name                ");
        query.append("         from talang aa                   ");
        query.append("         where  lang = '"+maCdSysCommonDTO.getUserLang()+"'         ");
        query.append("             and x.key_type = aa.key_type ");
        query.append("             and x.key_no = aa.key_no) as description,   ");
        query.append("       x.remark,         					");
        query.append("       x.list_categ                as listCateg,         ");
        query.append("		 x.is_use	is_use					");
        query.append("		,x.is_system	IS_SYSTEM			");
        query.append("FROM   TACDSYSM x        					");
        query.append("WHERE  1=1               					");
        query.append(this.getWhere(maCdSysCommonDTO));
        query.append("ORDER by x.list_type       				");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaCdSysListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaCdSysCommonDTO maCdSysCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        if (!"".equals(maCdSysCommonDTO.getCdSysMId()))
        {
            query.getAndQuery("x.cdsysm_id", maCdSysCommonDTO.getCdSysMId());
            return query.toString();
        }
        query.getLikeQuery("x.list_type", maCdSysCommonDTO.getFilterListType());
        query.getLikeQuery("x.description", maCdSysCommonDTO.getFilterListTypeDesc());
        query.getLikeQuery("x.list_categ", maCdSysCommonDTO.getFilterListTypeCateg());
        //설정매뉴에서 파라미터를 던지면 그 값은 고정으로 셋팅되어야 함.
        query.getLikeQuery("x.list_categ", maCdSysCommonDTO.getParamListTypeCateg());
        //시스템코드
        query.getLikeQuery("x.is_system", maCdSysCommonDTO.getFilterSyscode());
        //사용여부
        query.getLikeQuery("x.is_use", maCdSysCommonDTO.getFilterIsuse());
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaCdSysListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteListType(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	String cdsysMId = id;
    	
    	query.append("DELETE FROM TACDSYSD				");
    	query.append("WHERE cdsysm_id = '"+cdsysMId+"'	");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TACDSYSM				");
    	query.append("WHERE cdsysm_id = '"+cdsysMId+"'	");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
}