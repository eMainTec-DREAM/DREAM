package dream.mgr.cdsys.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.cdsys.dao.MgrCdSysFieldListDAO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.dto.MgrCdSysFieldListDTO;

/**
 * 시스템코드 detail-code 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="mgrCdSysFieldListDAOTarget"
 * @spring.txbn id="mgrCdSysFieldListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrCdSysFieldListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrCdSysFieldListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCommonDTO
     * @param mgrCdSysFieldListDTO
     * @return List
     */
    public List findCodeList(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysFieldListDTO mgrCdSysFieldListDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("    '' 									SEQNO		");
        query.append("    , (SELECT a.menu_no FROM TAPAGE a             	");
        query.append("        WHERE a.page_id = x.page_id                   ");
        query.append("      ) 									MENUNO		");
        query.append("    , (SELECT (SELECT b.key_name 						");
        query.append("                 FROM TALANG b WHERE b.lang = '"+mgrCdSysCommonDTO.getUserLang()+"' 		");
        query.append("                  AND b.key_type = a.key_type 		");
        query.append("                  AND b.key_no = a.key_no) 			");
        query.append("         FROM TAMENU a 								");
        query.append("        WHERE a.menu_no = 							");
        query.append("        			(SELECT b.menu_no FROM TAPAGE b     ");
        query.append("                    WHERE b.page_id = x.page_id       ");
        query.append("                  )									");
        query.append("       ) 									MENUNAME	");
        query.append("    , (SELECT a.file_name FROM TAPAGE a 				");
        query.append("        WHERE a.page_id = x.page_id) 		FILENO		");
        query.append("    , (SELECT a.file_name FROM TAPAGE a 				");
        query.append("        WHERE a.page_id = x.page_id) 		FILENAME	");
        query.append("    , x.field_id 							FIELDID		");
        query.append("    , (SELECT a.key_name 								");
        query.append("         FROM TALANG a WHERE a.lang = '"+mgrCdSysCommonDTO.getUserLang()+"' 		");
        query.append("          AND a.key_type = x.key_type 				");
        query.append("          AND a.key_no = x.key_no) 		FIELDNAME	");
        query.append("    , x.hidden_yn 						ISHIDDEN	");
        query.append("FROM TAPGFIELD x										");
        query.append("WHERE 1=1												");
        query.append(this.getWhere(mgrCdSysCommonDTO,mgrCdSysFieldListDTO));
        
        query.getOrderByQuery("x.pgfield_id","x.page_id, x.ord_no", mgrCdSysFieldListDTO.getOrderBy(), mgrCdSysFieldListDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(mgrCdSysFieldListDTO.getIsLoadMaxCount(), mgrCdSysFieldListDTO.getFirstRow()));
    }
    
    private String getWhere(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysFieldListDTO mgrCdSysFieldListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	if(!"".equals(mgrCdSysCommonDTO.getCdSysMId()))
    	{
    		query.append("AND x.code_list_Type IN (SELECT a.list_type 		");
    		query.append("                           FROM TACDSYSD a 		");
    		query.append("                          WHERE a.cdsysm_id = '"+mgrCdSysCommonDTO.getCdSysMId()+"'		");
    		query.append("                        )							");
    	}
    	
    	return query.toString();
    }

    @Override
    public String findTotalCount(MgrCdSysCommonDTO mgrCdSysCommonDTO,
            MgrCdSysFieldListDTO mgrCdSysFieldListDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                ");
        query.append("    COUNT(1)                                          ");
        
        query.append("FROM TAPGFIELD x                                      ");
        query.append("WHERE 1=1                                             ");
        query.append(this.getWhere(mgrCdSysCommonDTO,mgrCdSysFieldListDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}