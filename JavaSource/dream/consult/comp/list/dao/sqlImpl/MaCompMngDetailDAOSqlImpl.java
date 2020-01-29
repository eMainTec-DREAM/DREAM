package dream.consult.comp.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.comp.list.dao.MaCompMngDetailDAO;
import dream.consult.comp.list.dto.MaCompMngCommonDTO;
import dream.consult.comp.list.dto.MaCompMngDetailDTO;

/**
 * 회사설정 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaCompMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maCompMngDetailDAOTarget"
 * @spring.txbn id="maCompMngDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaCompMngDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaCompMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaCompMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public MaCompMngDetailDTO findDetail(MaCompMngCommonDTO maCompMngCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 		                                               							 ");
        query.append("       x.comp_id          compId                                                       ");
        query.append("       ,x.comp_no			compNo		                                                 ");
        query.append("       ,x.description		compDesc	                                                 ");
        query.append("       ,x.ct_path			ctPath		                                                 ");
        query.append("		 ,(SELECT dbo.SFACODE_TO_DESC(x.ct_path,'CT_PATH','SYS','','"+user.getLangId()+"')) ctPathDesc");
        query.append("		 ,x.init_ct_path_yn	initCtPathYn 	                                             ");
        query.append("       ,x.is_use          isUse				                                         ");
        query.append("       ,x.comp_id         compId				                                         ");
        query.append("FROM   TACOMP x																		 ");
        query.append("WHERE  1 = 1                                                                           ");
        query.append("AND  x.comp_id = '"+maCompMngCommonDTO.getCompId()+"'												");
        
        MaCompMngDetailDTO maCompMngDetailDTO = 
        		(MaCompMngDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaCompMngDetailDTO()));
        
        return maCompMngDetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaCompMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCompMngDetailDTO
     * @return
     */
    public int insertDetail(MaCompMngDetailDTO maCompMngDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TACOMP(								                        ");
    	query.append("	comp_no			,description											");
    	query.append("  ,ct_path		,init_ct_path_yn										");
    	query.append("  ,is_use  		,comp_id												");
    	query.append("	) VALUES(										                        ");
    	query.append("	?				,?														");
    	query.append("	,?				,?														");
    	query.append("	,?				,?														");
    	query.append("	)												                        ");
    	
    	Object[] objects = new Object[] {
    			maCompMngDetailDTO.getCompNo()
    			,maCompMngDetailDTO.getCompDesc()
    			,maCompMngDetailDTO.getCtPath()
    			,maCompMngDetailDTO.getInitCtPathYn()
    			,maCompMngDetailDTO.getIsUse()
    			,maCompMngDetailDTO.getCompId()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaCompMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCompMngDetailDTO
     * @return
     */
    public int updateDetail(MaCompMngDetailDTO maCompMngDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TACOMP SET		        ");
    	query.append("	description	        = ?	    ");
    	query.append("	,init_ct_path_yn	= ?	    ");
    	query.append("	,ct_path		    = ?		");
    	query.append("	,is_use		        = ?		");
    	query.append("WHERE comp_id = ?		        ");
    	
    	Object[] objects = new Object[] {
    			maCompMngDetailDTO.getCompDesc()
    			,maCompMngDetailDTO.getInitCtPathYn()
    			,maCompMngDetailDTO.getCtPath()
    			,maCompMngDetailDTO.getIsUse()
    			,maCompMngDetailDTO.getCompId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}