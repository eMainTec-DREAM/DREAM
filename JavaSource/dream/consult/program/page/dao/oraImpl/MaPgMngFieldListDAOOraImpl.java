package dream.consult.program.page.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.program.page.dao.MaPgMngFieldListDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldListDTO;

/**
 * 화면별 필드 목록 dao
 * @author  kim21017
 * @version $Id: MaPgMngFieldListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPgMngFieldListDAOTarget"
 * @spring.txbn id="maPgMngFieldListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPgMngFieldListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPgMngFieldListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPgMngFieldListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param maPgMngFieldListDTO
     * @return List
     */
    public List findFieldList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																				");
        query.append("       '' 																seqNo		");
        query.append("       ,'' 																isDelCheck	");
        query.append("       ,(SELECT a.file_name FROM TAPAGE a WHERE a.page_id = x.page_id) 	fileName	");
        query.append("       ,(SELECT a.description FROM TAPAGE a WHERE a.page_id = x.page_id) 	pageDesc   	");
        query.append("       ,(SELECT a.is_use FROM TAPAGE a WHERE a.page_id = x.page_id) 		isPageUse  	");
        query.append("       ,x.page_id 														pageId		");
        query.append("       ,x.pgfield_id 														pgFieldId	");
        query.append("       ,x.field_id   														fieldId		");
        query.append("       ,x.description 													fieldDesc	");
        query.append("       ,x.hidden_yn 														hiddenYn	");
        query.append("       ,x.ord_no 															ordNo		");
        query.append("       ,x.display_yn 														displayYn	");
        query.append("       ,SFACODE_TO_DESC(x.display_yn,'READONLY_YN','SYS','','"+maPgMngCommonDTO.getUserLang()+"')  readonlyYn ");
        query.append("       ,x.key_no 															keyNo		");
        query.append("		 ,SFACODE_TO_DESC(x.field_option,'FIELD_OPTION','SYS','','"+maPgMngCommonDTO.getUserLang()+"')	fieldOptionDesc	");
//        query.append("       ,x.readonly_yn 													readonlyYn	");
        query.append("       ,(SELECT key_name																");
        query.append("        FROM TALANG																	");
        query.append("        WHERE key_no = x.key_no														");
        query.append("          AND key_type = x.key_type													");
        query.append("          AND lang = '"+maPgMngCommonDTO.getUserLang()+"'								");
        query.append("        ) 																keyName		");
        query.append("       ,(SELECT key_name                                           					");
    	query.append("        FROM TALANG                                                					");
    	query.append("        WHERE key_no = x.group_key_no						          					");
    	query.append("          AND key_type = x.group_key_type							   					");
    	query.append("          AND lang = '"+maPgMngCommonDTO.getUserLang()+"' 							");
    	query.append("        ) 																groupKeyName	");
        query.append("       ,SFACODE_TO_DESC(x.group_option,'GROUP_OPTION','SYS','','"+maPgMngCommonDTO.getUserLang()+"')	groupOptionDesc	");
        query.append("       ,(SELECT y.description FROM TATABLE y WHERE y.table_id=x.table_id)	tableDesc	");
        query.append("       ,(SELECT y.description                                        					");
    	query.append("         FROM TATABCOL y                                            					");
    	query.append("         WHERE y.table_id=x.table_id                                 					");
    	query.append("          AND y.column_name=x.column_name                           					");
    	query.append("         )																columnDesc	");
        query.append("FROM   TAPGFIELD x																	");
        query.append("WHERE  1=1																			");
        query.append(this.getWhere(maPgMngCommonDTO,maPgMngFieldListDTO));
        query.getOrderByQuery("(SELECT a.file_name FROM TAPAGE a WHERE a.page_id = x.page_id)"
                + ", x.ord_no", maPgMngCommonDTO.getOrderBy(), maPgMngCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPgMngCommonDTO.getIsLoadMaxCount(), maPgMngCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPgMngFieldListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteFieldList(String id)
    {
    	QueryBuffer query = new QueryBuffer();

    	String pgFieldId=id;
    	
    	query.append("DELETE FROM TAPGFIELD						");
    	query.append("WHERE  pgfield_id = '"+pgFieldId+"'		");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getAndQuery("x.page_id", maPgMngCommonDTO.getPageId());
    	
    	if (!"".equals(maPgMngFieldListDTO.getPgFieldId()))
        {
            query.getAndQuery("x.pgfield_id", maPgMngFieldListDTO.getPgFieldId());
            return query.toString();
        }
    	
    	// 화면 ID
    	if(!"".equals(maPgMngCommonDTO.getFilterFileName()))
        {
        	query.append("AND x.page_id IN (                    ");
        	query.append("                SELECT a.page_id      ");
        	query.append("                FROM TAPAGE a         ");
        	query.append("                WHERE 1=1             ");
        	query.getLikeQuery("a.file_name", maPgMngCommonDTO.getFilterFileName());
        	query.append("                 )                    ");
        }
    	else if(!"".equals(maPgMngCommonDTO.getFilterPageDesc()))
        {
    	    query.append("AND x.page_id IN (                    ");
    	    query.append("                SELECT a.page_id      ");
    	    query.append("                FROM TAPAGE a         ");
    	    query.append("                WHERE 1=1             ");
    	    query.getLikeQuery("a.description", maPgMngCommonDTO.getFilterPageDesc());
    	    query.append("                 )                    ");
        }
        
    	// Input Property ID
    	query.getLikeQuery("x.field_id", maPgMngCommonDTO.getPgFieldId());
    	// 항목설명
    	query.getLikeQuery("x.description", maPgMngCommonDTO.getFilterPgFieldDesc());
    	// 시스템 숨김여부
        query.getSysCdQuery("x.hidden_yn", maPgMngCommonDTO.getFilterHiddenYn(), maPgMngCommonDTO.getFilterHiddenYn(), "IS_USE","", maPgMngCommonDTO.getUserLang());
    	// 화면 Display 여부
    	query.getSysCdQuery("x.display_yn", maPgMngCommonDTO.getFilterDisplayYn(), maPgMngCommonDTO.getFilterDisplayYn(), "IS_USE","", maPgMngCommonDTO.getUserLang());
    	// Label ID
        query.getLikeQuery("x.key_no", maPgMngCommonDTO.getFilterKeyNo());
    	// Label 명
        if(!"".equals(maPgMngCommonDTO.getFilterKeyName()))
        {
            query.append("AND x.key_type IN (                           ");
            query.append("                SELECT a.key_type             ");
            query.append("                FROM TALANG a                 ");
            query.append("                WHERE a.key_no = x.key_no     ");
            query.append("                AND a.key_type = x.key_type   ");
            query.append("                AND lang ='"+maPgMngCommonDTO.getUserLang()+"'  ");
            query.getLikeQuery("a.key_name", maPgMngCommonDTO.getFilterKeyName());
            query.append("                 )                            ");
        }
    	// 항목옵션
        query.getSysCdQuery("x.field_option", maPgMngCommonDTO.getFilterFieldOptionId(), maPgMngCommonDTO.getFilterFieldOptionDesc(), "FIELD_OPTION","", maPgMngCommonDTO.getUserLang());

    	// 읽기전용여부
        query.getSysCdQuery("x.readonly_yn", maPgMngCommonDTO.getFilterReadonlyYn(), maPgMngCommonDTO.getFilterReadonlyYnDesc(), "READONLY_YN","", maPgMngCommonDTO.getUserLang());
    	
    	return query.toString();
    }
    
    public int sysYColList(String id)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAPGFIELD SET display_yn = 'N', hidden_yn = 'Y'       ");
        query.append("WHERE  pgfield_id   = '"+id+"'        ");
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
    public int sysNColList(String id)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPGFIELD SET display_yn = 'Y', hidden_yn = 'N'       ");
        query.append("WHERE  pgfield_id   = '"+id+"'        ");
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
    public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT              ");
        query.append("   COUNT(1)         ");
        query.append("FROM TAPGFIELD x    ");
        query.append("WHERE 1=1           ");
        query.append(this.getWhere(maPgMngCommonDTO,maPgMngFieldListDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}