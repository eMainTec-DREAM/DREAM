package dream.consult.comp.cdsys.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.cdsys.dao.ConsultCdSysFieldListDAO;
import dream.consult.comp.cdsys.dto.ConsultCdSysFieldListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드 detail-code 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="consultCdSysFieldListDAOTarget"
 * @spring.txbn id="consultCdSysFieldListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCdSysFieldListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCdSysFieldListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param maCdSysCommonDTO
     * @param consultCdSysFieldListDTO
     * @return List
     */
    public List findCodeList(MaCdSysCommonDTO maCdSysCommonDTO, ConsultCdSysFieldListDTO consultCdSysFieldListDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("    '' 									SEQNO		");
        query.append("    , (SELECT a.menu_no FROM TAPAGE a             	");
        query.append("        WHERE a.page_id = x.page_id                   ");
        query.append("      ) 									MENUNO		");
        query.append("    , (SELECT (SELECT b.key_name 						");
        query.append("                 FROM TALANG b WHERE b.lang = '"+maCdSysCommonDTO.getUserLang()+"' 		");
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
        query.append("    , (SELECT a.description FROM TAPAGE a 			");
        query.append("        WHERE a.page_id = x.page_id) 		FILENAME	");
        query.append("    , x.field_id 							FIELDID		");
        query.append("    , (SELECT a.key_name 								");
        query.append("         FROM TALANG a WHERE a.lang = '"+maCdSysCommonDTO.getUserLang()+"' 		");
        query.append("          AND a.key_type = x.key_type 				");
        query.append("          AND a.key_no = x.key_no) 		FIELDNAME	");
        query.append("    , x.hidden_yn 						ISHIDDEN	");
        query.append("FROM TAPGFIELD x										");
        query.append("WHERE 1=1												");
        query.append(this.getWhere(maCdSysCommonDTO,consultCdSysFieldListDTO));
        query.append("ORDER BY x.page_id, x.ord_no							");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    private String getWhere(MaCdSysCommonDTO maCdSysCommonDTO, ConsultCdSysFieldListDTO consultCdSysFieldListDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	if(!"".equals(maCdSysCommonDTO.getCdSysMId()))
    	{
    		query.append("AND x.code_list_Type IN (SELECT a.list_type 		");
    		query.append("                           FROM TACDSYSD a 		");
    		query.append("                          WHERE a.cdsysm_id = '"+maCdSysCommonDTO.getCdSysMId()+"'		");
    		query.append("                        )							");
    	}
    	return query.toString();
    }
}