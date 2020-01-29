package dream.consult.program.page.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.consult.program.page.dao.MaGrdMngListDAO;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 목록 dao
 * @author  jung7126
 * @version $Id: MaGrdMngListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maGrdMngListDAOTarget"
 * @spring.txbn id="maGrdMngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaGrdMngListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaGrdMngListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaGrdMngListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngCommonDTO
     * @return List
     */
    public List findGrdList(MaPgMngCommonDTO maPgMngCommonDTO,MaGrdMngCommonDTO maGrdMngCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = maGrdMngCommonDTO.getUserLang();

        query.append("SELECT                                                    ");
        query.append("        '' seqNo,                                         ");
        query.append("        '' isDelCheck,                                    ");
        query.append("        (SELECT a.file_name                               ");
        query.append("         FROM   TAPAGE a                                  ");
        query.append("         WHERE  x.page_id = a.page_id ) fileName,         ");
        query.append("        (SELECT a.description                             ");
        query.append("         FROM   TAPAGE a                                  ");
        query.append("         WHERE  x.page_id = a.page_id ) pgDesc,           ");
//        query.append("		  DECODE((SELECT (SELECT key_name 				");
//        query.append("		  					FROM TALANG 				");
//        query.append("		  					WHERE key_type='MENU' 		");
//        query.append("		  					AND key_no=a.key_no 		");
//        query.getAndQuery("lang", lang);
//        query.append("							  )							");
//        query.append("		  		FROM   TAMENU a  						");
//        query.append("		  		WHERE a.page_id = x.page_id AND rownum=1 ),'',		");
//        query.append("		  		(SELECT (SELECT key_name 				");
//        query.append("		  				 FROM TALANG 					");
//        query.append("		  				WHERE key_type=a.key_type 		");
//        query.append("		  				AND key_no=a.key_no 			");
//        query.getAndQuery("lang", lang);
//        query.append("							  )							");
//        query.append("		  		FROM   TAPGPAGE a						");
//        query.append("		 		WHERE a.c_page_id = x.page_id	 		");
//        query.append("		  		AND ROWNUM=1) ,			 				");
//        query.append("		  		(SELECT (SELECT key_name 				");
//        query.append("		  				 FROM TALANG 					");
//        query.append("		  				 WHERE key_type='MENU' 			");
//        query.append("		  				 AND key_no=a.key_no 			");
//        query.getAndQuery("lang", lang);
//        query.append("							  )							");
//        query.append("		  		FROM   TAMENU a							");
//        query.append("		  		WHERE a.page_id = x.page_id AND rownum=1 )) pgDesc,");
        query.append("        grid_obj_id gridObjId,                            ");
        query.append("        description,                                      ");
        query.append("        pggrid_id pgGridId                                ");
        query.append("FROM   TAPGGRID x                                         ");
        query.append("WHERE  1=1               								    ");
        query.append(this.getWhere(maPgMngCommonDTO, maGrdMngCommonDTO));
        query.getOrderByQuery("x.pggrid_id","x.description", maPgMngCommonDTO.getOrderBy(), maPgMngCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPgMngCommonDTO.getIsLoadMaxCount(), maPgMngCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaGrdMngListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngDTOList
     * @return
     */
    public int deletePage(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	String pageId=id;
    	
    	query.append("DELETE FROM TAPGGRID				");
    	query.append("WHERE pggrid_id = '"+pageId+"'    ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TAPGGRIDCOL				");
    	query.append("WHERE pggrid_id = '"+pageId+"'		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  jung7126
     * @version $Id: MaGrdMngListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPgMngCommonDTO maPgMngCommonDTO, MaGrdMngCommonDTO maGrdMngCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndNumKeyQuery("x.page_id", maPgMngCommonDTO.getPageId());
        if (!"".equals(maGrdMngCommonDTO.getPgGridId()))
        {
            query.getAndQuery("x.pggrid_id", maGrdMngCommonDTO.getPgGridId());
            return query.toString();
        }
        query.getLikeQuery("(SELECT a.description FROM TAPAGE a WHERE  x.page_id = a.page_id )", maGrdMngCommonDTO.getPageDesc());
        query.getLikeQuery("(SELECT a.file_name FROM TAPAGE a WHERE  x.page_id = a.page_id )", maGrdMngCommonDTO.getFileName());
        query.getLikeQuery("x.grid_obj_id", maGrdMngCommonDTO.getGridObjId());
        return query.toString();
    }

	@Override
	public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, MaGrdMngCommonDTO maGrdMngCommonDTO, User user)
			throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT                   			 	");
        query.append("       COUNT(1)                       ");
        query.append("FROM   TAPGGRID x                     ");
        query.append("WHERE  1=1               				");
        query.append(this.getWhere(maPgMngCommonDTO, maGrdMngCommonDTO));
        

        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}